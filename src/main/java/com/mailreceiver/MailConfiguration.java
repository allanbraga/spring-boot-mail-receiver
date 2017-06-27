package com.mailreceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.annotation.ServiceActivator;

@Configuration
//@ImportResource(value = "classpath:/META-INF/spring/integration/gmail-imap-idle-config.xml")
@ImportResource(value = "classpath:/META-INF/spring/integration/gmail-pop3-config.xml")
public class MailConfiguration {
	
	@Autowired
	MailProcessor mailProcessor;

	@Bean
	@ServiceActivator(inputChannel = "receiveChannel")
	public MailProcessor mailMessageHandler() {
		return mailProcessor;
	}	
}
