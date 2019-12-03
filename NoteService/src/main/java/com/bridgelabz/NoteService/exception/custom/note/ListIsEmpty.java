package com.bridgelabz.NoteService.exception.custom.note;
/******************************************************************************
 *  Compilation:  javac -d bin ListIsEmpty.java
 *  Execution:    
 *              
 *  
 *  Purpose: This class is used to throw List Is Empty
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   03-12-2019
 *
 ******************************************************************************/
public class ListIsEmpty extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ListIsEmpty(String message)
	{
		super(message);
	}

}
