package com.bridgelabz.fundoouser.exception.custom;
/******************************************************************************
 *  Compilation:  javac -d bin DeleteException.java
 *  Execution:    
 *      
 *  Purpose:  Used to define Delete Exception
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   20-11-2019
 *
 ******************************************************************************/
public class DeleteException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public DeleteException(String message)
	{
		super(message);
	}
}
