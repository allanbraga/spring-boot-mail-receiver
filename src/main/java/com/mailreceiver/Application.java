package com.mailreceiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

@SpringBootApplication
public class Application {	

	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("/META-INF/spring/integration/gmail-imap-idle-config.xml");
		DirectChannel inputChannel = ac.getBean("receiveChannel", DirectChannel.class);
		inputChannel.subscribe(new MessageHandler() {
			@Override
			public void handleMessage(org.springframework.messaging.Message<?> arg0) throws MessagingException {
				System.out.println(arg0);				
			}
		});
		
        SpringApplication.run(Application.class, args);        
    }
}
