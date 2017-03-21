package ru.rik.bwl.service;

import org.asteriskjava.fastagi.AgiChannel;
import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.BaseAgiScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BwlScript extends BaseAgiScript {
	private static final Logger logger = LoggerFactory.getLogger(BwlScript.class);
	
	@Autowired ShortBuff shortBuff;
	@Autowired WlRepo wlrepo;
	
	public BwlScript() {
		System.out.println("Bwl Asterisk creating");
	}

	@Override
	public void service(AgiRequest request, AgiChannel channel) throws AgiException {
		String peerName = channel.getVariable("CHANNEL(peername)");
		String ip = channel.getVariable("CHANNEL(peerip)");
		logger.info("{} peer {} ip {}", request.toString(), peerName, ip);
		channel.getVariable("peername");
	
		if (shortBuff.in(request.getExtension())) {
			logger.info("reject {} too early", request.getExtension());
			hangup();
			return;
		}	
		
		if (wlrepo.findOne(request.getExtension()) != null) {
			logger.info("allow {}  exists in wl", request.getExtension());	
			channel.setContext("a2billing");
		} else {
			logger.info("not allow {} not in wl", request.getExtension());		
			hangup();

		}		
	}
	

}
