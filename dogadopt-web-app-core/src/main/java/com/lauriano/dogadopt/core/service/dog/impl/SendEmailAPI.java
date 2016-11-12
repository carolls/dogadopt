package com.lauriano.dogadopt.core.service.dog.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("dogAdoptEmail")
public class SendEmailAPI {
	
	@Autowired
	@Qualifier("mailSender")
	protected JavaMailSender mailSender;
	private static final Logger log = LoggerFactory.getLogger(SendEmailAPI.class);
    // for sending simple mails
	public void readyToSendEmail(String toAddress, String name, String msgBody) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
			helper.setFrom("dogadoptproject@gmail.com");
			helper.setTo(toAddress);
			helper.setSubject("Dogadopt Results for "+name);
			mimeMessage.setContent(msgBody, "text/html");
		} catch (MessagingException e) {
			log.info(e.toString());
		}
	
		mailSender.send(mimeMessage);
	}
}
