package com.bridgelabz.NoteService.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class NoteDto {
	private String title;
	private String description;
	private String color;
	private boolean pin;
	private boolean archive;
	private boolean trash;
	private boolean reminder;
}
