package ru.rik.services;

import org.asteriskjava.fastagi.AgiChannel;
import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.BaseAgiScript;
import org.springframework.stereotype.Component;

@Component
public class BwlScript extends BaseAgiScript {
//	http://another-lazy-blogger.blogspot.ru/2008/11/asterisk-java-spring.html
	public BwlScript() {
		System.out.println("Bwl Asterisk creating");
	}

	@Override
	public void service(AgiRequest request, AgiChannel channel) throws AgiException {
		verbose("!! hello world ", 1);
		hangup();
	}
	
//	@Override
//	 protected AgiScript createAgiScriptInstance(String beanName) {
//	        Object bean = applicationContext.getBean(beanName)
//	        if(bean == null) {
//	           throw new IllegalArgumentException(\"No bean with name: [\" + beanName +\"] found. Make sure that you have all beans defined which are there in your fastagi-mapping.properties\");
//	        }
//	 
//	        if(!(bean instanceof AgiScript)) {
//	          throw new IllegalArgumentException(\"spring bean : \" + beanName + \" must implement org.asteriskjava.fastagi.AgiScript interface\");
//	        }
//	 
//	        return (AgiScript) bean;
//	}

}
