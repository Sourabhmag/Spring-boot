package com.bridgelabz.fundoouser.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rabbitmq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String subject;
	private String body;
	private String email;
}
