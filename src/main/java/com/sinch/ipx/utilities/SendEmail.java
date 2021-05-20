package com.sinch.ipx.utilities;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	// this is the previous code and it is not working so that I have created new class Mailing to send email with report
	public static void sendMail(String host, final String username, final String password, String from, String to, String subject,
			String emailBody) {

		// mail properties
		Properties properties = System.getProperties();

		properties.setProperty("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		// authentication
		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		// compose the message
		try {

			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(emailBody);

			Transport.send(message);
			System.out.println("It has been sent.");

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

//	public static void main(String[] args) {
//
//		// username without @gmail.com
//		final String username = "testshivanand";
//		final String password = "hogakade44";
//
//		String from = "testshivanand@gmail.com";
//		String to = "";
//
//		String host = "smtp.gmail.com";
//
//		String subject = "Subject";
//		String emailBody = "This is body.";
//
//		System.out.println("Email Start...");
//
//		sendMail(host, username, password, from, to, subject, emailBody);
//
//	}

}
