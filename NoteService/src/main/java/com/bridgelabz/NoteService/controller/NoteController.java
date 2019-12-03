package com.bridgelabz.NoteService.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.NoteService.dto.ColaboratorDto;
import com.bridgelabz.NoteService.dto.NoteDto;
import com.bridgelabz.NoteService.model.Note;
import com.bridgelabz.NoteService.response.Response;
import com.bridgelabz.NoteService.service.NoteService;
/******************************************************************************
 *  Compilation:  javac -d bin NoteController.java
 *  Execution:    
 *              
 *  
 *  Purpose: This class is used as rest controller for Note
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   26-11-2019
 *
 ******************************************************************************/

@RestController
@RequestMapping("note")
public class NoteController {
	
	
	@Autowired
	@Qualifier("noteServiceImp")
	private NoteService noteService;

	/**
	 * Purpose - Used to show list of all notes
	 * @return - Note list
	 */
	@GetMapping("/show")
	public ResponseEntity<Response> listOfNotes()
	{
		return new ResponseEntity<Response>(noteService.getNoteList(),HttpStatus.OK);
	}

	/**
	 * Purpose - Used get list of all notes of perticular user
	 * @param - accepts username
	 * @return - List of notes
	 */
	@PostMapping("/getNotes")
	public ResponseEntity<Response> getNotes(@RequestHeader String username)
	{
		return new ResponseEntity<Response>(noteService.getNoteByUsername(username),HttpStatus.OK);
	}
	
	/**
	 * Purpose - Used to sort all notes by title
	 * @return - list of notes in sorted order
	 */
	@GetMapping("/sortByTitle")
	public ResponseEntity<Response> sortByTitle()
	{
		return new ResponseEntity<Response>(noteService.sortByTitle(),HttpStatus.OK);
	}
	/**
	 * Purpose - Used to add note
	 * @param - Accepts token
	 * @return - status
	 */
	@PostMapping("/add")
	public ResponseEntity<Response> addnote(@RequestBody @Valid NoteDto newNote,@RequestParam String token)
	{
		
		return new ResponseEntity<Response>(noteService.addNote(newNote,token),HttpStatus.OK);
	}
	
	/**
	 * Purpose - Used to delete note
	 * @param - Accepts id of note
	 * @return - status
	 */
	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteNote(@RequestParam String id)
	{
		return new ResponseEntity<Response>(noteService.deleteNode(id),HttpStatus.OK);
	}
	
	/**
	 * Purpose - Used to update note
	 * @param - Accepts Note id
	 * @param - Accepts updated note
	 * @return - status
	 */
	@PutMapping("/update")
	public ResponseEntity<Response> update(@RequestParam String id,@RequestBody @Valid NoteDto note)
	{
		System.out.println(note.getTitle());
		return new ResponseEntity<Response>(noteService.update(id, note),HttpStatus.OK);
	}
	
	/**
	 * Purpose - Used to sort all notes by created date
	 * @return - list of notes in sorted order
	 */
	@GetMapping("/sortByDate")
	public ResponseEntity<Response> sortByDate()
	{
		return new ResponseEntity<Response>(noteService.sortByDate(),HttpStatus.OK);
	}
	
	/**
	 * Purpose - Used to add colaborator
	 * @return - status
	 */
	@PutMapping("/addColaborator")
	public ResponseEntity<Response> addColaborator(@RequestBody @Valid ColaboratorDto colaborator)
	{
		return new ResponseEntity<Response>(noteService.addColaborator(colaborator),HttpStatus.OK);
	}
	
	/**
	 * @Purpose - Used to pin note
	 * @param noteId - Accepts Note Id
	 * @return - Status of operation
	 */
	@PutMapping("/pin")
	public ResponseEntity<Response> pin(@RequestHeader String noteId)
	{
		return new ResponseEntity<Response>(noteService.pin(noteId),HttpStatus.OK);
	}
	
	
	/**
	 * @Purpose - Used to archive note
	 * @param noteId - Accepts Note Id
	 * @return - Status of operation
	 */
	@PutMapping("/archive")
	public ResponseEntity<Response> archive(@RequestHeader String noteId)
	{
		return new ResponseEntity<Response>(noteService.archive(noteId),HttpStatus.OK);
	}
	
	
	/**
	 * @Purpose - Used to trash note
	 * @param noteId - Accepts Note Id
	 * @return - Status of operation
	 */
	@PutMapping("/trash")
	public ResponseEntity<Response> trash(@RequestHeader String noteId)
	{
		return new ResponseEntity<Response>(noteService.trash(noteId),HttpStatus.OK);
	}
	
	
	/**
	 * @Purpose - Used to delete note forever
	 * @param noteId - Accepts Note Id
	 * @return - Status of operation
	 */
	@PutMapping("/deleteForever")
	public ResponseEntity<Response> deleteForever(@RequestHeader String noteId)
	{
		return new ResponseEntity<Response>(noteService.deleteNode(noteId),HttpStatus.OK);
	}
	
	/**
	 * @Purpose - Used to delete note forever
	 * @param noteId - Accepts Note Id
	 * @return - Status of operation
	 */
	@GetMapping("/checkCache")
	public Map<String,Note> check()
	{
		return noteService.check();
	}
	
	/**
	 * @Purpose - Used to add reminder to note
	 * @param date - Date of reminder
	 * @param noteId - Accepts Note Id
	 * @return - Status of operation
	 */
	@PutMapping("/addReminder")
	public ResponseEntity<Response> addReminder(@RequestHeader Date date,@RequestHeader String noteId) throws ParseException
	{ 
		return new ResponseEntity<Response>(noteService.addReminder(date,noteId),HttpStatus.OK);
	}
	
	
	/**
	 * @Purpose - Used to delete reminder to note
	 * @param noteId - Accepts Note Id
	 * @return - Status of operation
	 */
	@GetMapping("/deleteReminder")
	public ResponseEntity<Response> deleteReminder(@RequestHeader String noteId) throws ParseException
	{ 
		return new ResponseEntity<Response>(noteService.deleteReminder(noteId),HttpStatus.OK);
	}
}
