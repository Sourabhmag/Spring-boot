package com.bridgelabz.fundoouser.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
/******************************************************************************
 *  Compilation:  javac -d bin Registration.java
 *  Execution:    
 *      
 *  Purpose:  Used as a model class of registration
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
@Document(collection = "User")
@Data
public class Registration {
	@Id
	private String id;
	private String name;
	private String phoneNo;
	private String email;
	private String password;
	@Transient
	private String checkPassword;
	private boolean validate;
	public Registration() {
		
	}
	public Registration(String name, String phoneNo, String email, String password, String checkPassword) {
		super();
		this.name = name;
		this.phoneNo = phoneNo;
		this.email = email;
		this.password = password;
		this.checkPassword = checkPassword;
	}
}
