package com.bridgelabz.NoteService.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
/******************************************************************************
 *  Compilation:  javac -d bin LabelDto.java
 *  Execution:    
 *              
 *  
 *  Purpose: This class is used as dto for Label
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   26-11-2019
 *
 ******************************************************************************/
@Data
public class LabelDto {
	@NotBlank(message = "Title is required")
	@Size(min = 3,max = 20)
	private String title;
}
