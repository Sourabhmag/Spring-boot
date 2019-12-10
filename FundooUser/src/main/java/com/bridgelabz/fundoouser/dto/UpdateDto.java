package com.bridgelabz.fundoouser.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateDto {

	@Size(min = 3,max = 20)
	private String name;
	
	@Size(min = 10,max = 10)
	@Pattern(regexp = "^(\\+?\\d{1,4}[\\s-])?(?!0+\\s+,?$)\\d{10}\\s*,?$",
				message = "Phone number must contain numbers")
	private String phoneNo;
}
