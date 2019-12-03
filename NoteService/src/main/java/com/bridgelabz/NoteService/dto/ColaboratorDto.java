package com.bridgelabz.NoteService.dto;

import lombok.Data;
/******************************************************************************
 *  Compilation:  javac -d bin ColaboratorDto.java
 *  Execution:    
 *              
 *  
 *  Purpose: This class is used as dto for collaborator
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   26-11-2019
 *
 ******************************************************************************/
@Data
public class ColaboratorDto {
	private String ownerId;
	private String colaboratorId;
}
