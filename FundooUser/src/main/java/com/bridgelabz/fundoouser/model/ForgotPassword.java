package com.bridgelabz.fundoouser.model;

import lombok.Data;
/******************************************************************************
 *  Compilation:  javac -d bin ForgotPassword.java
 *  Execution:    
 *      
 *  Purpose:  Used as a model class of forgot password
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
@Data
public class ForgotPassword {
	private String password;
	private String passwordCheck;
}
