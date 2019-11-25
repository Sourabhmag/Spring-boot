package com.bridgelabz.NoteService.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Label")
@Data
public class Label {
	private String id;
	private String title;
	private LocalDateTime createdDate;
	private LocalDateTime updatededDate;
	private String username;
	@DBRef(lazy = true)
	private List<Note> noteList = new ArrayList<Note>();
}
