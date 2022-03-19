package com.myroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myroom.service.EmailService;

@RestController
public class EmailController {
	@Autowired
	EmailService emailService;
	@GetMapping("/sendEmail")
	public void sendEmail()
	{
		//emailService.sendEmail(null, null, null);
	}
}
