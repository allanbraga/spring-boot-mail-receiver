package com.mailreceiver;

import javax.mail.Message;

public interface MailProcessor {	
	void process(Message mail);
}
