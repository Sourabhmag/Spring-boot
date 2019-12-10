package com.bridgelabz.NoteService.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/******************************************************************************
 *  Compilation:  javac -d bin NoteDto.java
 *  Execution:    
 *              
 *  
 *  Purpose: This class is used as dto for Note
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   26-11-2019
 *
 ******************************************************************************/
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDto {
	@NotBlank(message = "Title is required")
	@Size(min = 3,max = 100)
	private String title;
	
	@NotBlank(message = "Description is required")
	@Size(min = 3)
	private String description;
	
	@NotBlank(message = "Color is required")
	@Size(min = 3,max = 20)
	private String color;
	private boolean pin;
	private boolean archive;
	private boolean trash;
}
