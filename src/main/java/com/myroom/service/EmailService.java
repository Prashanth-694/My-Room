package com.myroom.service;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.myroom.controller.PdfGeneratorController;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	PdfGeneratorController controller;
	public void sendEmail(Multipart multipart) throws MessagingException {

		 MimeMessage message = mailSender.createMimeMessage();
	     
		    MimeMessageHelper helper = new MimeMessageHelper(message, true);
		    
		    helper.setFrom("my401room@gmail.com");
		    helper.setTo("prashanthmp009@gmail.com");
		    helper.setSubject("this is sub");
		    helper.setText("test text");
		    // Set Subject: subject of the email
           // message.setSubject("This is Subject");
             
            // creating first MimeBodyPart object
            BodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setText("This is body of the mail");
             
            // creating second MimeBodyPart object
            BodyPart messageBodyPart2 = new MimeBodyPart();
            String filename = "attachment.txt";
            DataSource source = new FileDataSource(""); 
            messageBodyPart2.setDataHandler(new DataHandler(source)); 
            messageBodyPart2.setFileName(filename); 
             
            // creating MultiPart object
            Multipart multipartObject = new MimeMultipart(); 
            multipartObject.addBodyPart(messageBodyPart1); 
            multipartObject.addBodyPart(messageBodyPart2);
     
     
     
            // set body of the email.
            message.setContent(multipartObject);
     
		   
		    mailSender.send(message);
	}
}
