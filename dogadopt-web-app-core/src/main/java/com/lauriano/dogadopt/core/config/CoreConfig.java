package com.lauriano.dogadopt.core.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class CoreConfig {
	
    @Bean(name="mailSender")
    public JavaMailSender helloWorld() {
    	JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    	mailSender.setHost("smtp.gmail.com");
    	mailSender.setPort(587);
    	mailSender.setUsername("projectdogadopt");
    	mailSender.setPassword("T-drnpdl");
    	mailSender.setProtocol("smtp");
    	Properties javaMailProperties = new Properties();
  
    	javaMailProperties.put("mail.transport.protocol", "smtp");
    	javaMailProperties.put("mail.smtp.auth", true);
    	javaMailProperties.put("mail.smtp.starttls.enable", true);
    	javaMailProperties.put("mail.smtp.quitwait", false);
    	mailSender.setJavaMailProperties(javaMailProperties);
    	return mailSender;
    }

}
