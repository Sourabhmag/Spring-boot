package com.bridgelabz.NoteService.service;

import java.util.Date;
import java.util.Map;

import com.bridgelabz.NoteService.dto.ColaboratorDto;
import com.bridgelabz.NoteService.dto.NoteDto;
import com.bridgelabz.NoteService.model.Note;
import com.bridgelabz.NoteService.response.Response;
/******************************************************************************
 *  Compilation:  javac -d bin NoteService.java
 *  Execution:    
 *              
 *  
 *  Purpose: This interface is used to define services of note
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   26-11-2019
 *
 ******************************************************************************/

public interface NoteService {
	public Response addNote(NoteDto newNote,String token);
	public Response deleteNode(String id);
	public Response update(String id,NoteDto note);
	public Response getNoteList();
	public Response getNoteByUsername(String username);
	public Response sortByTitle();
	public Response sortByDate();
	public Response addColaborator(ColaboratorDto colaborator);
	public Response removeColaborator(ColaboratorDto colaborator);
	public Map<String,Note> check();
	public Response pin(String noteId);
	public Response archive(String noteId);
	public Response trash(String noteId);
	public Response addReminder(Date date, String noteId);
	public Response deleteReminder(String noteId);
}
