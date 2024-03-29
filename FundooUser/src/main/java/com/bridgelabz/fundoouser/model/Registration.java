package com.bridgelabz.fundoouser.model;

import java.io.Serializable;

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
public class Registration  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5079019982655829051L;
	@Id
	private String id;
	private String name;
	private String phoneNo;
	private String email;
	private String password;
	@Transient
	private String checkPassword;
	private String profilePic;
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
