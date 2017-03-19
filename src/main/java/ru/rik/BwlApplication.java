package ru.rik;

import java.io.IOException;

import org.asteriskjava.fastagi.AgiScript;
import org.asteriskjava.fastagi.DefaultAgiServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(scanBasePackages = { "ru.rik.services" })
public class BwlApplication {

	 
	@Bean @Autowired
	public DefaultAgiServer defaultAgiServer(AgiScript script) throws IllegalStateException, IOException {
		DefaultAgiServer srv = new DefaultAgiServer(script);
		srv.startup();
		return srv;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BwlApplication.class, args);
	}
}
