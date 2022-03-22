package com.myroom.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myroom.model.GenerateOtp;
import com.myroom.model.Users;
import com.myroom.service.EmailService;

@RestController
public class EmailController {
	@Autowired
	EmailService emailService;
	@PostMapping("/sendOtp")
	public void sendEmail(@RequestBody GenerateOtp otp) throws MessagingException
	{
		emailService.sendEmail(otp.getEmailId(),otp.getOtp());
	}
}
