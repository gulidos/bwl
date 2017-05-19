package ru.rik.bwl;

import java.io.IOException;
import java.net.InetAddress;

import org.asteriskjava.fastagi.AgiScript;
import org.asteriskjava.fastagi.AgiServerThread;
import org.asteriskjava.fastagi.DefaultAgiServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(scanBasePackages = { "ru.rik.bwl.service" })
@EnableCaching
public class BwlApplication implements ApplicationRunner {
	private static final Logger logger = LoggerFactory.getLogger(BwlApplication.class);

	 
	@Bean @Autowired
	public AgiServerThread defaultAgiServer(AgiScript script) throws IllegalStateException, IOException {
		DefaultAgiServer srv = new DefaultAgiServer(script);
		srv.setAddress(InetAddress.getByName("localhost"));
		AgiServerThread thread = new AgiServerThread(srv);
		 
		thread.startup();
		return thread; 
	}
	
	
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BwlApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info(" !!! app is running!!! "); 
  
	} 
}
