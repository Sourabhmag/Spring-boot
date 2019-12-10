package com.bridgelabz.NoteService.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
	public Response deleteNode(String id, String token);
	public Response update(String id,NoteDto note, String token);
	public Response getNoteList();
	public Response getNoteByUserId(String token);
	public Response sortByTitle(String token);
	public Response sortByDate(String token);
	public Response addColaborator(String noteId,List<String> userNameList,String token);
	public Response removeColaborator(String noteId,List<String> userNameList,String token);
	public Map<String,Note> check();
	public Response pin(String noteId, String token);
	public Response archive(String noteId, String token);
	public Response trash(String noteId, String token);
	public Response addReminder(Date date, String noteId, String token);
	public Response deleteReminder(String noteId, String token);
}
