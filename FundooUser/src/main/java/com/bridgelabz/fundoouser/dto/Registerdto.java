package com.bridgelabz.fundoouser.dto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/******************************************************************************
 *  Compilation:  javac -d bin Registerdto.java
 *  Execution:    
 *      
 *  Purpose:  Used as a dto class of user registration
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
import lombok.Data;
@Data
public class Registerdto {
	
	@NotBlank(message = "Name is required")
	@Size(min = 3,max = 20)
	private String name;
	
	@NotBlank(message = "phoneNo is required")
	@Size(min = 10,max = 10)
	@Pattern(regexp = "^(\\+?\\d{1,4}[\\s-])?(?!0+\\s+,?$)\\d{10}\\s*,?$",
				message = "Phone number must contain numbers")
	private String phoneNo;
	
	@NotBlank(message = "email is required")
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\."
			+ "[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="mail id format wrong")
	private String email;
	
	@NotBlank(message = "password is required")
	@Size(min = 3,max = 20)
	private String password;
	
	@NotBlank(message = "checkPassword is required")
	@Size(min = 3,max = 20)
	private String checkPassword;
	
	private boolean validate;
	
	public boolean getV()
	{
		return validate;
	}

}
