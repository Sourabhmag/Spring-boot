package com.bridgelabz.NoteService.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.NoteService.dto.LabelDto;
import com.bridgelabz.NoteService.response.Response;
import com.bridgelabz.NoteService.service.LabelService;
/******************************************************************************
 *  Compilation:  javac -d bin LabelController.java
 *  Execution:    
 *              
 *  
 *  Purpose: This class is used as rest controller for label
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   26-11-2019
 *
 ******************************************************************************/



@RestController
@RequestMapping("/label")
public class LabelController {
	@Autowired
	private LabelService labelService;
	
	//Logger logger = LoggerFactory.getLogger(LabelController.class);

	/**
	 * Purpose - Used get list of all labels of perticular user
	 * @param - accepts username
	 * @return - List of labels
	 */
	@PostMapping("/getlabel")
	public ResponseEntity<Response> getLabel(@RequestHeader String token)
	{
		
		return new ResponseEntity<Response>(labelService.getLabelByUsername(token),HttpStatus.OK);
	}
	
	
	/**
	 * Purpose - Used to add label
	 * @param - Accepts label
	 * @param - Accepts token
	 * @return - status
	 */
	@PostMapping("/add")
	public ResponseEntity<Response> addLabel(@RequestBody @Valid LabelDto label,@RequestHeader String token)
	{
		return new ResponseEntity<Response>(labelService.addLabel(label,token),HttpStatus.OK);
	}
	
	/**
	 * Purpose - Used to delete label
	 * @param - Acccepts label id
	 * @return - status
	 */
	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteLabel(@RequestParam String labelId,@RequestHeader String token)
	{
		return new ResponseEntity<Response>(labelService.deleteLabel(labelId,token),HttpStatus.OK);
	}
	
	/**
	 * Purpose - Used To update label
	 * @param - Accepts label id
	 * @param - Accepts new label
	 * @return - Status
	 */
	@PutMapping("/update")
	public ResponseEntity<Response> updateLabel(@RequestParam String labelId,@RequestBody @Valid LabelDto label,@RequestHeader String token)
	{
		return new ResponseEntity<Response>(labelService.updateLabel(labelId, label,token),HttpStatus.OK);
	}
	
	/**
	 * Purpose - Used to assign note to label
	 * @param - accepts noteId
	 * @param - Accepts labelId
	 * @return - status
	 */
	@PostMapping("/assignNote")
	public ResponseEntity<Response> assignNoteToLabel(@RequestParam String noteId,@RequestParam String labelId,@RequestHeader String token)
	{
		return new ResponseEntity<Response>(labelService.assignNoteToLabel(noteId,labelId,token),HttpStatus.OK);
	}
	
	/**
	 * @Purpose - Used to Delete note from label
	 * @param noteId - accepts noteId
	 * @param labelId - Accepts labelId
	 * @return - status
	 */
	@PostMapping("/deleteNote")
	public ResponseEntity<Response> deleteNoteFromLabel(@RequestParam String noteId,@RequestParam String labelId,@RequestHeader String token)
	{
		return new ResponseEntity<Response>(labelService.deleteNoteFromLabel(noteId,labelId,token),HttpStatus.OK);
	}
	
	
	/**
	 * Purpose - Used to list of notes assigned to preticular label
	 * @param - accepts labelId
	 * @return - List of notes
	 */
	@PostMapping("/getNotes")
	public ResponseEntity<Response> getNoteByLabelId(@RequestParam String labelId,@RequestHeader String token)
	{
		return new ResponseEntity<Response>(labelService.getNoteByLabelId(labelId,token),HttpStatus.OK);
	}
}
