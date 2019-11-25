package com.bridgelabz.NoteService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.NoteService.dto.LabelDto;
import com.bridgelabz.NoteService.model.Label;
import com.bridgelabz.NoteService.response.Response;
import com.bridgelabz.NoteService.service.LabelService;

@RestController
@RequestMapping("/label")
public class LabelController {
	@Autowired
	private LabelService labelService;
	
//	@GetMapping("/show")
//	public List<Note> listOfNotes()
//	{
//		return noteService.getNoteList();
//	}
	@PostMapping("/getlabel")
	public List<Label> getLabel(@RequestParam String username)
	{
		return labelService.getLabelByUsername(username);
	}
	@PostMapping("/add")
	public String addnote(@RequestBody LabelDto label,@RequestParam String token)
	{
		labelService.addLabel(label,token);
		return "Label Added SuccessFully";
	}
	
	@DeleteMapping("/delete")
	public String deleteNote(@RequestParam String id)
	{
		return labelService.deleteLabel(id); 
	}
	
	@PutMapping("/update")
	public String update(@RequestParam String id,@RequestBody LabelDto label)
	{
		return labelService.updateLabel(id, label);
	}
	
	@PostMapping("/assignNote")
	public String assignNote(@RequestParam String noteId,@RequestParam String labelId)
	{
		return labelService.assignNote(noteId,labelId);
	}
	@PostMapping("/getNotes")
	public Response getNoteByLabelId(@RequestParam String labelId)
	{
		return labelService.getNoteByLabelId(labelId);
	}
}
