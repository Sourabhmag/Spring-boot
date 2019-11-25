package com.bridgelabz.NoteService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.NoteService.dto.ColaboratorDto;
import com.bridgelabz.NoteService.dto.NoteDto;
import com.bridgelabz.NoteService.model.Note;
import com.bridgelabz.NoteService.service.NoteService;

@RestController
@RequestMapping("note")
public class NoteController {
	
	@Autowired
	private NoteService noteService;
	
	@GetMapping("/show")
	public List<Note> listOfNotes()
	{
		return noteService.getNoteList();
	}
	
	@PostMapping("/getNotes")
	public List<Note> getNotes(@RequestParam String username)
	{
		return noteService.getNoteByUsername(username);
	}
	
	@GetMapping("/sortByTitle")
	public List<Note> sortByTitle()
	{
		return noteService.sortByTitle();
	}
	
	@PostMapping("/add")
	public String addnote(@RequestBody NoteDto newNote,@RequestParam String token)
	{
		noteService.addNote(newNote,token);
		return "Note Added SuccessFully";
	}
	
	@DeleteMapping("/delete")
	public String deleteNote(@RequestParam String id)
	{
		return noteService.deleteNode(id); 
	}
	
	@PutMapping("/update")
	public String update(@RequestParam String id,@RequestBody NoteDto note)
	{
		return noteService.update(id, note);
	}
	
	@GetMapping("/sortByDate")
	public List<Note> sortByDate()
	{
		return noteService.sortByDate();
	}
	
	@PostMapping("/addColaborator")
	public String addColaborator(@RequestBody ColaboratorDto colaborator)
	{
		return noteService.addColaborator(colaborator);
	}
}
