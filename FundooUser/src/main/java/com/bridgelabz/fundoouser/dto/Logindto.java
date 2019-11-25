package com.bridgelabz.fundoouser.dto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
/******************************************************************************
 *  Compilation:  javac -d bin Logindto.java
 *  Execution:    
 *      
 *  Purpose:  Used as a dto class of login user
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
@Data
public class Logindto {
	
	@NotBlank(message = "email is required")
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\."
			+ "[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="mail id format wrong")
	private String username;
	
	@NotBlank(message = "password is required")
	@Size(min = 3,max = 20)
	private String password;
}
