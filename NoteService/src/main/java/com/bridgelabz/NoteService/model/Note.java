package com.bridgelabz.NoteService.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/******************************************************************************
 *  Compilation:  javac -d bin Note.java
 *  Execution:    
 *              
 *  
 *  Purpose: This class is used as model for Note
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   26-11-2019
 *
 ******************************************************************************/
//@Document(indexName = "Note",shards = 2,type = "Note")
@Document(collection = "Note")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {
	
	@Id
	private String id;
	private String username;
	private String title;
	private String description;
	private String color;
	private boolean pin;
	private boolean archive;
	private boolean trash;
	private Date reminder;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	@JsonIgnore
	private List<String> colaborators = new ArrayList<String>();
	@JsonIgnore
	@DBRef
	private List<Label> labelList = new ArrayList<Label>();
}
