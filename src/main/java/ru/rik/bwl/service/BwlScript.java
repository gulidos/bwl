package ru.rik.bwl.service;

import org.asteriskjava.fastagi.AgiChannel;
import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.BaseAgiScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.rik.bwl.domain.Port;
import ru.rik.bwl.domain.WhiteNumber;

@Component
public class BwlScript extends BaseAgiScript {
	private static final Logger logger = LoggerFactory.getLogger(BwlScript.class);
	
	@Autowired ShortBuff shortBuff;
	@Autowired Requests requests;
	@Autowired PortRepo ports;
	
	public BwlScript() {
		System.out.println("Bwl Asterisk creating");
	}

	@Override
	public void service(AgiRequest r, AgiChannel ch) throws AgiException {
		String exten = r.getExtension();
		String src = r.getCallerIdNumber();
		String peer = ch.getVariable("CHANNEL(peername)");
	
		if (shortBuff.in(exten)) {
			logger.info("peer:{} src:{} dst:{} reject too early", peer, src, exten);
			exec("hangup", "34");
			hangup();
			return;
		}	
		
		WhiteNumber wn = requests.requestWl(exten);
		if (wn == null) {
			logger.info("peer:{} src:{} dst:{} reject is not whitelisted", peer, src, exten);
			exec("hangup", "34");
			hangup();
			return;
		} 	
		
		Port p = ports.findOne(exten.substring(1));
		if (p != null) 
			ch.setExtension(p.getMncFormatted() + exten);
		
		logger.info("peer:{} src:{} dst:{} tag: {} allow whitelisted", peer, src, exten, wn.getTag());
		ch.setContext("a2billing");
	}
	

}
