package com.bridgelabz.fundoouser.utility;

import org.springframework.stereotype.Component;

import com.bridgelabz.fundoouser.model.Rabbitmq;

@Component
public class Util {
	public static Rabbitmq  getRabbitMq(String link,String email ,String token) {

		Rabbitmq rm=new Rabbitmq();
		rm.setBody(link+token);
		rm.setEmail(email);
		rm.setSubject("Verfication link");
		return rm;
		}
}
