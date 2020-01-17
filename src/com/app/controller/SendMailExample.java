package com.app.controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailExample {
	public static void main(String[] args) {

		final String username = "padmashri1795@gmail.com";
		final String password = "SHri@123";

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("padmashri1795@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("pratik.dandavate@gmail.com"));
			message.setSubject("Testing Gmail TLS");
			message.setText("Dear Tingu," + "\n\n Please do not spam my email!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}