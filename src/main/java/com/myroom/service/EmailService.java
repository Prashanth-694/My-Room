package com.myroom.service;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail(String toMail,int otp) throws MessagingException {

		 MimeMessage message = mailSender.createMimeMessage();
	     
		    MimeMessageHelper helper = new MimeMessageHelper(message, true);
		    
		    helper.setFrom("my401room@gmail.com");
		    helper.setTo(toMail);
		    helper.setSubject("Generating Otp");
		    helper.setText("Your Otp is : "+otp);	   
		    mailSender.send(message);
	}
}
