package com.bridgelabz.NoteService.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Document(collection = "Note")
@Data
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
	private boolean reminder;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private List<String> colaborators = new ArrayList<String>();
	@DBRef
	private List<Label> labelList = new ArrayList<Label>();
}
