package com.bridgelabz.NoteService.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
/******************************************************************************
 *  Compilation:  javac -d bin Label.java
 *  Execution:    
 *              
 *  
 *  Purpose: This class is used as model for label
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   26-11-2019
 *
 ******************************************************************************/
//@Document(indexName = "Label",type = "Label",shards = 2)
@Document(collection = "Label")
@Data
public class Label {
	private String id;
	private String title;
	private LocalDateTime createdDate;
	private LocalDateTime updatededDate;
	private String username;
	@JsonIgnore
	@DBRef(lazy = true)
	private List<Note> noteList = new ArrayList<Note>();
}
