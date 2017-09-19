package com.servicebus.servicebussender.controller;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.nio.charset.StandardCharsets;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.azure.servicebus.Message;
import com.microsoft.azure.servicebus.TopicClient;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;

@RestController
public class TestController {
	
	@Autowired
	private TopicClient topicClient;
	
	@RequestMapping("/")
public String sendMessage() throws InterruptedException, ServiceBusException {
	
		try {
			final String messageBody = "topic message";
	        System.out.println("Sending message: " + messageBody);
	        final Message message = new Message(messageBody.getBytes(StandardCharsets.UTF_8));
	        topicClient.send(message);
	       // topicClient.close();
		} catch (Exception e) {
			// TODO: handle exception
		
			return "Send UnSuccessfully";
		
		}
		
		return "Send Successfully";
}
	
}
