package com.bridgelabz.fundoouser.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.bridgelabz.fundoouser.model.Rabbitmq;

public class MessageReceiver {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendMessage(Rabbitmq body)
	{
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(body.getEmail());
		msg.setSubject(body.getSubject());
		msg.setText(body.getBody());
		javaMailSender.send(msg);
	}
}
