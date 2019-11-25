package com.bridgelabz.fundoouser.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
/******************************************************************************
 *  Compilation:  javac -d bin ForgotPassworddto.java
 *  Execution:    
 *      
 *  Purpose:  Used as a dto class of forgot password
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
@Data
public class ForgotPassworddto {
	
	@NotBlank(message = "password is required")
	@Size(min = 3,max = 20)
	private String password;
	
	@NotBlank(message = "passwordCheck is required")
	@Size(min = 3,max = 20)
	private String passwordCheck;
}
