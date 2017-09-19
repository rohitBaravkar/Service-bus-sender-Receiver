package com.servicebus.servicebussender;


import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.qos.logback.classic.Logger;

@SpringBootApplication
public class ServicebusSenderApplication {

	private static final Logger log = (Logger) LoggerFactory.getLogger(ServicebusSenderApplication.class);
	 
	public static void main(String[] args) {
		SpringApplication.run(ServicebusSenderApplication.class, args);
		
		log.info("in spring boot application");
	}
}
