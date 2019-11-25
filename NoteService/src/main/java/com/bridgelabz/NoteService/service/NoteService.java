package com.bridgelabz.NoteService.service;

import java.util.List;

import com.bridgelabz.NoteService.dto.ColaboratorDto;
import com.bridgelabz.NoteService.dto.NoteDto;
import com.bridgelabz.NoteService.model.Note;

public interface NoteService {
	public void addNote(NoteDto newNote,String token);
	public String deleteNode(String id);
	public String update(String id,NoteDto note);
	public List<Note> getNoteList();
	public List<Note> getNoteByUsername(String username);
	public List<Note> sortByTitle();
	public List<Note> sortByDate();
	public String addColaborator(ColaboratorDto colaborator);
}
