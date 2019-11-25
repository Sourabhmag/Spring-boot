package com.bridgelabz.fundoouser.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
/******************************************************************************
 *  Compilation:  javac -d bin EmailService.java
 *  Execution:    
 *      
 *  Purpose:  Used to prepare message to send email
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
@Component
public class EmailService {
	 public static SimpleMailMessage sendMail(String email,String token,String link) {
		 //Message msg = new MimeMessage();
		 SimpleMailMessage msg = new SimpleMailMessage();
		 msg.setTo(email);
		 msg.setFrom("sourabh.com");
		 msg.setSubject("test");
		 msg.setText("hello \n You are successfully registered\n"+link+token);
		 System.out.println(msg);
		 return msg;

		 }
}