package com.mailreceiver;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Assert;
import org.junit.Test;

import com.icegreen.greenmail.junit.GreenMailRule;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;

public class MailReceiverTest {

	
	private GreenMailRule greenMail = null;

	@Test
	public void testSomething() throws MessagingException {
		greenMail = new GreenMailRule(ServerSetupTest.SMTP_IMAP);
		GreenMailUtil.sendTextEmailTest("to@localhost.com", "from@localhost.com", "subject", "body");
		MimeMessage[] emails = greenMail.getReceivedMessages();
		Assert.assertEquals(1, emails.length);
		Assert.assertEquals("subject", emails[0].getSubject());
		Assert.assertEquals("body", GreenMailUtil.getBody(emails[0]));

	}
	
	@Test
	public void testSendEmail() throws MessagingException {
		GreenMailUtil.sendTextEmailTest("test@localhost", "from@localhost", "TEST EMAIL.", "body");		

	}


}
