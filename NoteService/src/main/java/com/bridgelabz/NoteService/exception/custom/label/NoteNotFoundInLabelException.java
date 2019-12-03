package com.bridgelabz.NoteService.exception.custom.label;

/******************************************************************************
 *  Compilation:  javac -d bin NoteNotFoundInLabelException.java
 *  Execution:    
 *              
 *  
 *  Purpose: This class is used to throw Note Not Found In Label Exception
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   03-12-2019
 *
 ******************************************************************************/
public class NoteNotFoundInLabelException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NoteNotFoundInLabelException(String message)
	{
		super(message);
	}
}
