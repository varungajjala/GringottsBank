package com.softwaresecurity.util;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import pojo.UserInfo;

public class EmailSender {

	private MailSender mailSender;
	//Default constructor
	public EmailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	private void sendEmailWithAttachment(SimpleMailMessage message,String fileName, boolean makeThread) throws MessagingException  {
		final MimeMessage mimeMessage = ((JavaMailSender)mailSender).createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		
		helper.setTo(message.getTo());
		helper.setText(message.getText());
		helper.setSubject(message.getSubject());
		FileSystemResource file = new FileSystemResource(fileName);
		helper.addAttachment(file.getFilename(), file);
		
		if (!makeThread) {
			((JavaMailSender) mailSender).send(mimeMessage);
		try {
			file.getOutputStream().close();
			file.getInputStream().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		} else {
			Thread mailSenderThread = new Thread(new Runnable() {
				public void run() {
					((JavaMailSender) mailSender).send(mimeMessage);
				}
			});
			mailSenderThread.start();
		}
	}
	public void sendCertificateFileEmail(UserInfo userInfo, String PrivateKey, String fileName,HttpSession session) throws MessagingException {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(userInfo.getEmailId());
		message.setSubject("Thank you for Gringotts Bank Sign-up : "+new Timestamp(Calendar.getInstance().getTime().getTime()));

		StringBuilder sb = new StringBuilder();
		sb.append("Hello, \n Welcome to the royal group of Gringotts!!").append(userInfo.getFirstName()).append(",\n\n");
		sb.append("Please find the Certificate File attached with the Mail. \n Also here is your Private key :").append(PrivateKey).append("\n\n\n");
		sb.append("Thank you for your time..!!");
		message.setText(sb.toString());
		
		//************Need to change based on how we want the certificate to be stored**********************
		ServletContext context = session.getServletContext();
        String realContextPath = context.getRealPath("/");
        
        File dir = new File(realContextPath+"/certificates");
          if (!dir.exists())
              dir.mkdirs();
        
        
        String filePath = realContextPath+"/certificates/"+fileName+"_cert.pem";
	 
		
		//String filePath="c://certificates/"+fileName+"_cert.pem";
		sendEmailWithAttachment(message, filePath,false);
		
		
	}
	
}
