package com.bridgelabz.NoteService.exception.custom.label;


/******************************************************************************
 *  Compilation:  javac -d bin LabelNotFoundException.java
 *  Execution:    
 *              
 *  
 *  Purpose: This class is used to throw Label Not Found Exception
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   03-12-2019
 *
 ******************************************************************************/
public class LabelNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 4741183192461497447L;
	public LabelNotFoundException(String message)
	{
		super(message);
	}
}
