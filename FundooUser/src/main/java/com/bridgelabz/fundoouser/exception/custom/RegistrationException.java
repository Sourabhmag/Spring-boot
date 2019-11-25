package com.bridgelabz.fundoouser.exception.custom;

/******************************************************************************
 *  Compilation:  javac -d bin RegistrationException.java
 *  Execution:    
 *      
 *  Purpose:  Used to define registration exception
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   20-11-2019
 *
 ******************************************************************************/
public class RegistrationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public RegistrationException(String message) {
		super(message);
	}
}
