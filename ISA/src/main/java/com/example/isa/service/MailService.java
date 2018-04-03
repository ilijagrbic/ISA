package com.example.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;*/
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class MailService {
/*
	private final MailSender mailSender;

	@Autowired
	public MailService(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Async
	public void sendVerificationMail(String url, String confirmationCode, String emailAddress) {
		final SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("Verify Account");
		message.setFrom("Email address hidden in public repository."); // Email address hidden in public repository.
		message.setTo(emailAddress);
		message.setText(url + "/" + confirmationCode);

		try {
			mailSender.send(message);
		} catch (MailException e) {
			System.out.println(e);
		}
	}*/

}
