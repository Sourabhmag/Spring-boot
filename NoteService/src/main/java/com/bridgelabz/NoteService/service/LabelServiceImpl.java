package com.bridgelabz.NoteService.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.NoteService.dto.LabelDto;
import com.bridgelabz.NoteService.model.Label;
import com.bridgelabz.NoteService.model.Note;
import com.bridgelabz.NoteService.repository.LabelRepository;
import com.bridgelabz.NoteService.repository.NoteRepository;
import com.bridgelabz.NoteService.response.Response;
import com.bridgelabz.NoteService.utility.TokenUtil;
@Service
public class LabelServiceImpl implements LabelService{

	@ Autowired
	private LabelRepository labelRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private NoteRepository noteRepository;
	@Override
	public void addLabel(LabelDto label, String token) {
		Label newLabel = modelMapper.map(label, Label.class);
		newLabel.setUsername(TokenUtil.getUsernameFromToken(token));
		newLabel.setCreatedDate(LocalDateTime.now());
		newLabel.setUpdatededDate(LocalDateTime.now());
		labelRepository.save(newLabel);	
	}

	@Override
	public String deleteLabel(String id) {
		Label label = labelRepository.findById(id).get();
		if(label==null)
			return "Label Not found";
		labelRepository.delete(label);
		return "Label Deleted Successfully";
	}

	@Override
	public String updateLabel(String id, LabelDto labelDto) {
		Label label = labelRepository.findById(id).get();
		if(label==null)
			return "Label not found";
		modelMapper.map(labelDto, label);
		label.setUpdatededDate(LocalDateTime.now());
		labelRepository.save(label);
		return "Label Updated Successfully";
	}

	@Override
	public List<Label> getLabelByUsername(String username) {
			return labelRepository.findByUsername(username);
	}

	@Override
	public String assignNote(String noteId, String labelId) {
		Label label = labelRepository.findById(labelId).get();
		if(label==null)
			return "Label not found";
		
		Note note = noteRepository.findById(noteId).get();
		if(note==null)
			return "Note not found";
		List<Note> listOfNote = new ArrayList<Note>();
		List<Label> listOfLabel = new ArrayList<Label>();
		
		listOfNote = label.getNoteList();
		listOfLabel = note.getLabelList();
		
		listOfLabel.add(label);
		listOfNote.add(note);
		
		label.setNoteList(listOfNote);
		note.setLabelList(listOfLabel);
		
		noteRepository.save(note);
		labelRepository.save(label);
		
		return "Note assigned Successfully";
		
	}
	@Override
	public String deleteNote(String noteId, String labelId) {
		Label label = labelRepository.findById(labelId).get();
		if(label==null)
			return "Label not found";
		
		Note note = noteRepository.findById(noteId).get();
		if(note==null)
			return "Note not found";
		List<Note> listOfNote = new ArrayList<Note>();
		List<Label> listOfLabel = new ArrayList<Label>();
		
		listOfNote = label.getNoteList();
		listOfLabel = note.getLabelList();
		
		listOfLabel.remove(label);
		listOfNote.remove(note);
		
		label.setNoteList(listOfNote);
		note.setLabelList(listOfLabel);
		
		noteRepository.save(note);
		labelRepository.save(label);
		
		return "Note Deleted Successfully";
	}

	@Override
	public Response getNoteByLabelId(String labelId) {
		Label label = labelRepository.findById(labelId).get();
		if(label==null)
			return new Response(400,"Label not found",null);
		List<Note>noteList = label.getNoteList();
		if(noteList.isEmpty())
			return new Response(400,"No notes assigned for this label",null);
		return new Response(400,"List on Notes",noteList);
	}	
}
