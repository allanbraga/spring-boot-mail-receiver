package com.mailreceiver;

import java.io.IOException;

import javax.mail.Address;
import javax.mail.Message;

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
		pop3Reader();		
        SpringApplication.run(Application.class, args);        
    }
	
	public static void pop3Reader(){
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("/META-INF/spring/integration/gmail-pop3-config.xml");
		DirectChannel inputChannel = ac.getBean("receiveChannel", DirectChannel.class);
		inputChannel.subscribe(new MessageHandler() {
			@Override
			public void handleMessage(org.springframework.messaging.Message<?> message) throws MessagingException {
				System.out.println("MSG........"+message.getPayload());
				javax.mail.Message email = (Message) message.getPayload();				
				try {
					Address[] adress = email.getFrom();
					if(adress != null && adress.length > 0){
						for (Address  adressIt : adress) {							
							System.out.println("FROM ........"+adressIt);
						}
					}
					System.out.println("RECEIVE DATE ........"+email.getReceivedDate());
					System.out.println("SUBJECT ........"+email.getSubject());
					System.out.println("BODY ........"+email.getContent());
					
					
					
				} catch (javax.mail.MessagingException e) {
					// TODO Auto-generated catch block
					System.out.println("ERRO ........");
					e.printStackTrace();
				}catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("ERRO ........");
					e.printStackTrace();
				}	
				
			}
		}); 		
	}
	
	public void imapIdle(){
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("/META-INF/spring/integration/gmail-imap-idle-config.xml");
		DirectChannel inputChannel = ac.getBean("receiveChannel", DirectChannel.class);
		inputChannel.subscribe(new MessageHandler() {
			@Override
			public void handleMessage(org.springframework.messaging.Message<?> message) throws MessagingException {
				System.out.println("MSG........"+message.getPayload());
				javax.mail.Message email = (Message) message.getPayload();
				try {
					Address[] adress = email.getFrom();
					if(adress != null && adress.length > 0){
						for (Address  adressIt : adress) {							
							System.out.println("FROM ........"+adressIt);
						}
					}
					System.out.println("RECEIVE DATE ........"+email.getReceivedDate());
					System.out.println("SUBJECT ........"+email.getSubject());
					System.out.println("BODY ........"+email.getContent());
					
					
					
				} catch (javax.mail.MessagingException e) {
					// TODO Auto-generated catch block
					System.out.println("ERRO ........");
					e.printStackTrace();
				}catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("ERRO ........");
					e.printStackTrace();
				}					
			}
		});
		
	}
}
