package com.bridgelabz.NoteService.exception.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.NoteService.exception.custom.label.LabelNotFoundException;
import com.bridgelabz.NoteService.exception.custom.label.NoteNotFoundInLabelException;
import com.bridgelabz.NoteService.exception.custom.note.NoteNotFoundException;
import com.bridgelabz.NoteService.response.Response;
/******************************************************************************
 *  Compilation:  javac -d bin GlobalException.java
 *  Execution:    
 *              
 *  
 *  Purpose: This class is used to throw Global Exception
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   03-12-2019
 *
 ******************************************************************************/
@ControllerAdvice
public class GlobalException {
	/**
	 * @Purpose - Used to throw Global Exception
	 * @param e - Exception
	 * @return - Exception
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> exception(Exception e)
	{
		return new ResponseEntity<Response>(new Response(401,e.getMessage(),null),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	/**
	 * @Purpose - Used to throw Label Not Found Exception
	 * @param e - Exception
	 * @return - Exception
	 */
	@ExceptionHandler(LabelNotFoundException.class)
	public ResponseEntity<Response> labelNotFoundException(Exception e)
	{
		return new ResponseEntity<Response>(new Response(400,e.getMessage(),null),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	  @Purpose - Used to throw note Not Found Exception
	 * @param e - Exception
	 * @return - Exception
	 */
	@ExceptionHandler(NoteNotFoundException.class)
	public ResponseEntity<Response> noteNotFoundException(Exception e)
	{
		return new ResponseEntity<Response>(new Response(400,e.getMessage(),null),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	  @Purpose - Used to throw Note Not Found In Label Exception
	 * @param e - Exception
	 * @return - Exception
	 */
	@ExceptionHandler(NoteNotFoundInLabelException.class)
	public ResponseEntity<Response> noteNotFoundInLabelException(Exception e)
	{
		return new ResponseEntity<Response>(new Response(400,e.getMessage(),null),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
