package com.bridgelabz.NoteService.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.NoteService.dto.NoteDto;
import com.bridgelabz.NoteService.response.Response;
import com.bridgelabz.NoteService.service.ElasticSearchService;
/******************************************************************************
 *  Compilation:  javac -d bin ElasticSearchController.java
 *  Execution:    
 *              
 *  
 *  Purpose:       main purpose this class create for 
 *  				configuration modelmapper and token
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   26-11-2019
 *
 ******************************************************************************/
@RestController
@RequestMapping("/elastic")
public class ElasticSearchController {

//	@Autowired
//	private ElasticSearchService elasticSearchService;
//	
//	/**
//	 * @Pupose - Used to get all notes 
//	 * @param id - Accepts note id
//	 * @return - Returns note
//	 * @throws Exception
//	 */
//	@PostMapping("/ela/{id}")
//	public ResponseEntity<Response> getNote(@PathVariable String id) throws Exception
//	{
//		return new ResponseEntity<Response>(elasticSearchService.findById(id),HttpStatus.OK);
//	}
//	
//	/**
//	 * @Pupose - Used to add note
//	 * @param note - Accepts note
//	 * @return - Status of operation
//	 * @throws IOException
//	 */
//	@PostMapping("/addNote")
//	public ResponseEntity<Response> addNote(@RequestBody NoteDto note) throws IOException
//	{
//		return new ResponseEntity<Response>(elasticSearchService.addNote(note),HttpStatus.OK);
//	}
//	
//	/**
//	 * @Pupose - Used to update note
//	 * @param newNote - Accepts note
//	 * @param noteId - Accepts note id
//	 * @return - Status of operation
//	 * @throws IOException
//	 */
//	@PutMapping("/update")
//	public ResponseEntity<Response> update(@RequestBody NoteDto newNote,@RequestHeader String noteId) throws IOException
//	{
//		return new ResponseEntity<Response>(elasticSearchService.update(newNote,noteId),HttpStatus.OK);
//	}
//	
//	/**
//	 * @Pupose - Used to delete note
//	 * @param noteId - Accepts note id
//	 * @return - Status of operation
//	 * @throws IOException
//	 */
//	@PutMapping("/delete")
//	public ResponseEntity<Response> delete(@RequestHeader String noteId) throws IOException
//	{
//		return new ResponseEntity<Response>(elasticSearchService.delete(noteId),HttpStatus.OK);
//	}
//	
//	/**
//	 * @Pupose - Used to get all notes
//	 * @return - Status of operation
//	 * @throws IOException
//	 */
//	@GetMapping("/getAllNotes")
//	public ResponseEntity<Response> getAllNotes() throws IOException
//	{
//		return new ResponseEntity<Response>(elasticSearchService.findAll(),HttpStatus.OK);
//	}
//	
//	/**
//	 * @Pupose - Used to search note by title
//	 * @param title - Accepts title
//	 * @return - Status of operation
//	 * @throws IOException
//	 */
//	@PutMapping("/searchByTitle")
//	public ResponseEntity<Response> searchByTitle(@RequestHeader String title) throws IOException
//	{
//		return new ResponseEntity<Response>(elasticSearchService.searchByTitle(title),HttpStatus.OK);
//	}
}
