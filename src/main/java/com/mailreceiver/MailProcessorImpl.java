package com.mailreceiver;

import java.io.IOException;

import javax.mail.Address;
import javax.mail.Message;

import org.springframework.stereotype.Service;

@Service
public class MailProcessorImpl implements MailProcessor {

	@Override
	public void process(Message mail) {	
		System.out.println(mail);						
		try {
			Address[] adress = mail.getFrom();
			if(adress != null && adress.length > 0){
				for (Address  adressIt : adress) {							
					System.out.println("FROM ........"+adressIt);
				}
			}
			System.out.println("RECEIVE DATE ........"+mail.getReceivedDate());
			System.out.println("SUBJECT ........"+mail.getSubject());
			System.out.println("BODY ........"+mail.getContent());
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
	
	

}
