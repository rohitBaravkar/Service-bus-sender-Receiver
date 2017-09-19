package com.servicebus.servicebussender.receiver;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.microsoft.azure.servicebus.ExceptionPhase;
import com.microsoft.azure.servicebus.IMessage;
import com.microsoft.azure.servicebus.IMessageHandler;
import com.microsoft.azure.servicebus.MessageHandlerOptions;
import com.microsoft.azure.servicebus.SubscriptionClient;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import com.servicebus.servicebussender.ServicebusSenderApplication;

import ch.qos.logback.classic.Logger;

@Service
public class ServiceBusSubscriptionReceiver {
	
	@Autowired
	private SubscriptionClient subscriptionClient;
	private static final Logger logss = (Logger) LoggerFactory.getLogger(ServiceBusSubscriptionReceiver.class);
	

	@Bean	
	public  String ReceiveSubscriptionMessage() throws ServiceBusException, InterruptedException {
		logss.info("in receiveSubscriptionMessage");
    Duration d= Duration.ofSeconds(2);
		subscriptionClient.registerMessageHandler(new MessageHandler(), new MessageHandlerOptions(2,true,d));

        //TimeUnit.SECONDS.sleep(5);
        //subscriptionClient.close();
		return "returndd";
    }
	
	static class MessageHandler implements IMessageHandler {
        public CompletableFuture<Void> onMessageAsync(IMessage message) {
            final String messageString = new String(message.getBody(), StandardCharsets.UTF_8);
            System.out.println("Received message: " + messageString);
            
            
            return CompletableFuture.completedFuture(null);
        }

        public void notifyException(Throwable exception, ExceptionPhase phase) {
            System.out.println(phase + " encountered exception:" + exception.getMessage());
        }
    }

}
