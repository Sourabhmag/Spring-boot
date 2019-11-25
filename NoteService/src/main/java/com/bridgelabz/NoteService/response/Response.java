package com.bridgelabz.NoteService.response;

import lombok.AllArgsConstructor;
import lombok.Data;
/******************************************************************************
 *  Compilation:  javac -d bin Response.java
 *  Execution:    
 *      
 *  Purpose:   Used to send response to user
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
@Data
@AllArgsConstructor
public class Response {
	private int statusCode;
	private String message;
	private Object data;
}
