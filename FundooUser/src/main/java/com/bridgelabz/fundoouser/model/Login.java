package com.bridgelabz.fundoouser.model;

import lombok.Data;
/******************************************************************************
 *  Compilation:  javac -d bin Login.java
 *  Execution:    
 *      
 *  Purpose:  Used as a model class of login
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
@Data
public class Login {
	private String username;
	private String password;
	public Login() {
	}
	public Login(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
	
	
	
}