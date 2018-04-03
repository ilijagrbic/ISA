package com.example.isa.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	public MailService() {}

	@Async
	public void sendVerificationMail(String url, String confirmationCode, String emailAddress) {
		// final SimpleMailMessage message = new SimpleMailMessage();
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		
		final String username = "mail.isaprojekat@gmail.com";
		final String password = "projekatisa";
		
		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		MimeMessage message = new MimeMessage(session);

		try {
			message.setSubject("Verify Account");
			message.setFrom(new InternetAddress("mail.isaprojekat@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
			message.setText(url + "/" + confirmationCode);

			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
