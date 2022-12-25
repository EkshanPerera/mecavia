package com.mecavia.site.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailApi {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendMail(String toEmail,String subject,String body) {
		SimpleMailMessage massage = new SimpleMailMessage();
		massage.setFrom("admin@mecavia.com");
		massage.setTo(toEmail);
		massage.setText(body);
		massage.setSubject(subject);
		
		mailSender.send(massage);
		
		System.out.println("massege send");
	}
}
