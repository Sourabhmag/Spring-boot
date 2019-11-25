package com.bridgelabz.NoteService.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.NoteService.dto.ColaboratorDto;
import com.bridgelabz.NoteService.dto.NoteDto;
import com.bridgelabz.NoteService.model.Note;
import com.bridgelabz.NoteService.repository.NoteRepository;
import com.bridgelabz.NoteService.utility.TokenUtil;

@Service
public class NoteServiceImp implements NoteService{

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private NoteRepository noteRepository;
	@Override
	public void addNote(NoteDto newNote,String token) {
		Note getNewNote = modelMapper.map(newNote, Note.class);
		LocalDateTime createdDate = LocalDateTime.now();
		getNewNote.setCreatedDate(createdDate);
		getNewNote.setUpdatedDate(createdDate);
		String username = TokenUtil.getUsernameFromToken(token);
		getNewNote.setUsername(username);
		noteRepository.save(getNewNote);
	}
	@Override
	public String deleteNode(String id) {
		Note note = noteRepository.findById(id).get();
		if(note==null)
			return "Note not present";
		noteRepository.delete(note);
		return "Note deletd successfully";
	}

	@Override
	public String update(String id, NoteDto updatedNote) {
		Note note = noteRepository.findById(id).get();
		if(note==null)
			return "No Note found";
		modelMapper.map(updatedNote,note);
		LocalDateTime updatedDate = LocalDateTime.now();
		note.setUpdatedDate(updatedDate);
		System.out.println(note.toString());
		noteRepository.save(note);
		return null;
	}
	@Override
	public List<Note> getNoteList() {
		List<Note> list = noteRepository.findAll();
		return list;
	}
	@Override
	public List<Note> getNoteByUsername(String username) {
		return noteRepository.findByUsername(username);
	}
	@Override
	public List<Note> sortByTitle() {
		List<Note> listOfNote= getNoteList();
		return listOfNote.stream().sorted((listOfNote1,listOfNote2) ->listOfNote1.getTitle().compareTo(listOfNote2.getTitle())).collect(Collectors.toList());
	}
	@Override
	public List<Note> sortByDate() {
	List<Note> listOfNote= getNoteList();
	return listOfNote.stream().sorted((listOfNote1,listOfNote2) ->listOfNote1.getCreatedDate().compareTo(listOfNote2.getCreatedDate())).collect(Collectors.toList());
}
	@Override
	public String addColaborator(ColaboratorDto colaborator) {
		Note ownerNote = noteRepository.findById(colaborator.getOwnerId()).get();
		if(ownerNote==null)
			return "Note not found";
		List<String> list = new ArrayList<String>();
		list = ownerNote.getColaborators();
		System.out.println(list);
		System.out.println(colaborator.getColaboratorId());
		list.add(colaborator.getColaboratorId());
		ownerNote.setColaborators(list);
		noteRepository.save(ownerNote);
		return "Colaborator added successfully";
	}


}
