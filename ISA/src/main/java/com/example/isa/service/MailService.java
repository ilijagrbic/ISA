package com.example.isa.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.isa.repository.UserRepository;

@Service
public class MailService {
	@Autowired
	private UserRepository userRepository;
	
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
			message.setSubject("Verifikacija naloga");
			message.setFrom(new InternetAddress("mail.isaprojekat@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
			message.setText(url + "/" + confirmationCode);

			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	// Slanje potvrde za rezervaciju
	@Async
	public void sendReservationMail(String url, Long id, Long idHost) {
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
			message.setSubject(userRepository.findById(idHost).getName() + " Vas je pozvao na dogadjaj , kliknite na link za potvrdu rezervacije ");
			message.setFrom(new InternetAddress("mail.isaprojekat@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(userRepository.findById(id).getEmail()));
			message.setText(url);

			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}


}
