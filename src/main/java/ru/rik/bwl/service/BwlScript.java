package ru.rik.bwl.service;

import java.io.File;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.asteriskjava.fastagi.AgiChannel;
import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.BaseAgiScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Component;

import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import ru.rik.bwl.domain.Port;
import ru.rik.bwl.domain.WhiteNumber;

@Component
public class BwlScript extends BaseAgiScript {
	private static final Logger logger = LoggerFactory.getLogger(BwlScript.class);
	
	@Autowired ShortBuff shortBuff;
	@Autowired Requests requests;
	@Autowired PortRepo ports;
	@Autowired CounterService counters;

	private final Meter reqPerSec;
	private final Timer timer;
	final CsvReporter reporter ;
	
	@Autowired
	public BwlScript(MetricRegistry metrics) {
		System.out.println("Bwl Asterisk creating");
		reqPerSec = metrics.meter("requests");
		timer = metrics.timer("timer");
		reporter = CsvReporter.forRegistry(metrics)
	            .formatFor(Locale.US)
	            .convertRatesTo(TimeUnit.SECONDS)
	            .convertDurationsTo(TimeUnit.MILLISECONDS)
	            .build(new File("/home/bwl/data/"));
		reporter.start(10, TimeUnit.MINUTES);
	}

	
	@Override
	public void service(AgiRequest r, AgiChannel ch) throws AgiException {
		String exten = r.getExtension();
		String src = r.getCallerIdNumber();
		String peer = ch.getVariable("CHANNEL(peername)");
		counters.increment("bwl.incalls");
		counters.increment("bwl." + peer + ".incalls");
		reqPerSec.mark();
		
		final Timer.Context context = timer.time();
		try {
			if (shortBuff.in(exten)) {
				logger.info("peer:{} src:{} dst:{} reject too early", peer, src, exten);
				exec("hangup", "34");
				hangup();
				counters.increment("bwl.early");
				counters.increment("bwl." + peer + ".early");
				return;
			}	
			
			WhiteNumber wn = requests.requestWl(exten);
			if (wn == null) {
				logger.info("peer:{} src:{} dst:{} reject is not whitelisted", peer, src, exten);
				exec("hangup", "34");
				hangup();
				counters.increment("bwl.notwl");
				counters.increment("bwl." + peer + ".notwl");
				return;
			}
			counters.increment("bwl.inwl");
			counters.increment("bwl." + peer + ".inwl");
			
			Port p = ports.findOne(exten.substring(1));
			if (p != null) 
				ch.setExtension(p.getMncFormatted() + exten);
			
			
			logger.info("peer:{} src:{} dst:{} tag: {} allow whitelisted", peer, src, exten, wn.getTag());
			ch.setContext("a2billing");
	
		} finally {
			context.stop();
		}
	}
	

}
