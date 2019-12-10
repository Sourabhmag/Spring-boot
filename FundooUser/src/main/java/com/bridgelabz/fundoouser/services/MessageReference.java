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
	public static final String VALIDATE_ACCOUNT = "http://localhost:8081/user/validate?token=";
	public static final String PASSWORD_RESET = "http://localhost:8081/user/verify?token=";
	public static final String USER_ALREADY_EXIST = "user is already registered";
	public static final String USER_NOT_FOUND= "user is not registered";
	public static final String USER_DATA_UPDATED = "user data updated successfully";
	public static final String USER_DELETED = "User Deleted Successfully";
	public static final String USER_REGISTERED_MAIL_SENT = "User Registered \n validation email sent on registered mail id";
	public static final String NO_DIRECTORY = "Could not create the directory where the uploaded files will be stored.";
	public static final String FILE_STORE_ERROR = "Could not store file ";
	public static final String INVALID_PATH = "Sorry! Filename contains invalid path sequence ";
	public static final String FILE_DELETED = "File Deleted Successfully";
	public static final String FILE_NOT_FOUND = "Requested file not found";
	public static final String PROFILE_PIC_UPLOADED = "Profile picture uploaded successfully";
	public static final String LOGIN_SUCCESSFULL = "You have logged in successfully";
	public static final String LOGIN_FAILED = "Login failed";
	public static final String USER_NOT_VALIDATED = "User is not validated";
	public static final String PASSWORD_NOT_MATCH = "Enter password is not matching";
	public static final String PASSWORD_RESET_MSG = "Password reset link is sent to your registered mail id";
	public static final String PASSWORD_RESET_SUCCESS = "Password reset successfull";
	public static final String USER_VERIFICATION = "User verification Successful";
	public static final String PROFILE_PIC_NOT_EXIST = "Profile pic not exist";
	public static final String PROFILE_PIC_NOT_UPLOADED = "Problem to upload Profile Picture";
}
