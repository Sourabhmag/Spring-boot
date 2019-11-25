package com.bridgelabz.fundoouser.services;
/******************************************************************************
 *  Compilation:  javac -d bin MessageReference.java
 *  Execution:    
 *      
 *  Purpose:  Used to generate link to send email
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
public class MessageReference {
	public static final String VALIDATE_ACCOUNT = "http://localhost:8081/validate?token=";
	public static final String PASSWORD_RESET = "http://localhost:8081/verify?token=";
	public static final String USER_ALREADY_EXIST = "user is already registered";
	public static final String USER_NOT_FOUND= "user is not registered";
	public static final String USER_DATA_UPDATED = "user data updated successfully";
	public static final String USER_DELETED = "User Deleted Successfully";
	public static final String USER_REGISTERED_MAIL_SENT = "User Registered \n validation email sent on registered mail id";
}
