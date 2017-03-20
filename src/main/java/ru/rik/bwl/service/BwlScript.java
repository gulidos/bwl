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
	
	public BwlScript() {
		System.out.println("Bwl Asterisk creating");
	}

	@Override
	public void service(AgiRequest request, AgiChannel channel) throws AgiException {
		logger.info(request.toString());
		if (shortBuff.in(request.getExtension()))
			logger.info("reject");
		else 
			logger.info("allow");
		
		
		hangup();
	}

}
