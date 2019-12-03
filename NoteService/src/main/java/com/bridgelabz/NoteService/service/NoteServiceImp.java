package com.bridgelabz.NoteService.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.NoteService.dto.ColaboratorDto;
import com.bridgelabz.NoteService.dto.NoteDto;
import com.bridgelabz.NoteService.exception.custom.note.ListIsEmpty;
import com.bridgelabz.NoteService.exception.custom.note.NoteNotFoundException;
import com.bridgelabz.NoteService.model.Note;
import com.bridgelabz.NoteService.repository.NoteRepository;
import com.bridgelabz.NoteService.repository.RedisRepository;
import com.bridgelabz.NoteService.response.Response;
import com.bridgelabz.NoteService.utility.MessegeReference;
import com.bridgelabz.NoteService.utility.TokenUtil;

/******************************************************************************
 *  Compilation:  javac -d bin NoteServiceImp.java
 *  Execution:    
 *              
 *  
 *  Purpose: This Class is used to implement services of note
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   26-11-2019
 *
 ******************************************************************************/
@Service("noteServiceImp")
//@Cacheable
public class NoteServiceImp implements NoteService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private NoteRepository noteRepository;

	private RedisRepository redisRepository;
	public NoteServiceImp(RedisRepository redisRepository) {
		this.redisRepository=redisRepository;
	}
	

	/**
	 * @Purpose - Used to add new Note
	 */
	@Override
	public Response addNote(NoteDto newNote, String token) {
		
		Note getNewNote = modelMapper.map(newNote, Note.class);
		LocalDateTime createdDate = LocalDateTime.now();
		getNewNote.setCreatedDate(createdDate);
		getNewNote.setUpdatedDate(createdDate);
		String username = TokenUtil.getUsernameFromToken(token);
		getNewNote.setUsername(username);
		noteRepository.save(getNewNote);
		
		return new Response(200,MessegeReference.NOTE_ADDED,getNewNote);
	}

	/**
	 * @Purpose - Used to delete note
	 * @return - status
	 */
	
	@Override
	public Response deleteNode(String noteId) {
		
//		if(redisRepository.findNoteById(noteId)!=null)
//			redisRepository.deleteNote(noteId);
		Optional<Note> getnote = noteRepository.findById(noteId);
		if (getnote.isEmpty()) {
			throw new NoteNotFoundException(MessegeReference.NOTE_NOT_FOUND);
		}
		
		Note note = getnote.get();
		noteRepository.delete(note);
		return new Response(400,MessegeReference.NOTE_DELETED, null);
	}

	/**
	 * @Purpose - Used to update note
	 * @return - status
	 */
	//@CachePut(key = "#id",value = "abcd")
	@Override
	public Response update(String id, NoteDto updatedNote) {
		
		Optional<Note> getNote = noteRepository.findById(id);
		
		if (getNote.isEmpty())
			throw new NoteNotFoundException(MessegeReference.NOTE_NOT_FOUND);
		
		Note note = getNote.get();
		modelMapper.map(updatedNote, note);
		
//		if(redisRepository.findNoteById(id)!=null)
//			redisRepository.update(note);
			
		LocalDateTime updatedDate = LocalDateTime.now();
		note.setUpdatedDate(updatedDate);
		
		System.out.println(note.toString());
		noteRepository.save(note);
		
		return new Response(400,MessegeReference.NOTE_UPDATED, null);
	}

	/**
	 * @Purpose - Used to get list of all notes
	 * @return - list of notes
	 */

	@Override
	public Response getNoteList() {
		List<Note> list = noteRepository.findAll();
		if(list.isEmpty())
			 throw new ListIsEmpty(MessegeReference.LIST_IS_EMPTY);
		return new Response(200,MessegeReference.LIST_OF_NOTES,list);
	}

	/**
	 * @Purpose - Used to get list of all notes of perticular user
	 * @return - list of notes
	 */
	
	@Override
	public Response getNoteByUsername(String username) {
		List<Note> list = noteRepository.findByUsername(username);
		if(list.isEmpty())
			 throw new ListIsEmpty(MessegeReference.LIST_IS_EMPTY);
		return new Response(200,MessegeReference.LIST_OF_NOTES,list);
	}

	/**
	 * @Purpose - Used to sort all notes by title
	 * @return - sorted list of notes
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Response sortByTitle() {
		List<Note> listOfNote = (List<Note>) getNoteList().getData();
		 listOfNote.stream()
				.sorted((listOfNote1, listOfNote2) -> listOfNote1.getTitle().compareTo(listOfNote2.getTitle()))
				.collect(Collectors.toList());
		 if(listOfNote.isEmpty())
			 throw new ListIsEmpty(MessegeReference.LIST_IS_EMPTY);
			return new Response(200,MessegeReference.LIST_OF_NOTES,listOfNote);
	}

	/**
	 * @Purpose - Used to sort all notes by created date
	 * @return - sorted list of notes
	 */
	
	@Override
	public Response sortByDate() {
		@SuppressWarnings("unchecked")
		List<Note> listOfNote = (List<Note>) getNoteList().getData();
		 listOfNote.stream().sorted(
				(listOfNote1, listOfNote2) -> listOfNote1.getCreatedDate().compareTo(listOfNote2.getCreatedDate()))
				.collect(Collectors.toList());
		 if(listOfNote.isEmpty())
			 throw new ListIsEmpty(MessegeReference.LIST_IS_EMPTY);
		return new Response(200,MessegeReference.LIST_OF_NOTES,listOfNote);
	}

	/**
	 * @purpose - Used to add colaborator to a note
	 * @return - status
	 */
	@Override
	public Response addColaborator(ColaboratorDto colaborator) {
		Optional<Note> getNote = noteRepository.findById(colaborator.getOwnerId());
		if (getNote.isEmpty())
			throw new NoteNotFoundException(MessegeReference.NOTE_NOT_FOUND);
		Note ownerNote = getNote.get();
		List<String> list = new ArrayList<String>();
		list = ownerNote.getColaborators();
		System.out.println(colaborator.getColaboratorId());
		list.add(colaborator.getColaboratorId());
		ownerNote.setColaborators(list);
		noteRepository.save(ownerNote);
		return new Response(200,MessegeReference.COLABORATOR_ADD, null);
	}

	@Override
	public Response removeColaborator(ColaboratorDto colaborator) {
		Optional<Note> note;
		 note = noteRepository.findById(colaborator.getOwnerId());
		if (note.isPresent() == false)
			throw new NoteNotFoundException(MessegeReference.NOTE_NOT_FOUND);
		Note ownerNote = note.get();
		List<String> list = new ArrayList<String>();
		list = ownerNote.getColaborators();
		System.out.println(list);
		System.out.println(colaborator.getColaboratorId());
		list.remove(colaborator.getColaboratorId());
		ownerNote.setColaborators(list);
		noteRepository.save(ownerNote);
		return new Response(400,MessegeReference.COLABORATOR_REMOVED, null);
	}


	@Override
	public Map<String,Note> check()
	{
		return redisRepository.findAllNotes();
	}


	@Override
	public Response pin(String noteId) {
		Optional<Note> getNote = noteRepository.findById(noteId);
		if(getNote.isEmpty())
			return new Response(400,MessegeReference.NOTE_NOT_FOUND,null);
		Note note = getNote.get();
		if(note.isPin())
			note.setPin(false);
		note.setPin(true);
		note.setArchive(false);
		noteRepository.save(note);
		
		return new Response(200,MessegeReference.NOTE_UPDATED,null);
	}


	@Override
	public Response archive(String noteId) {
		Optional<Note> getNote = noteRepository.findById(noteId);
		if(getNote.isEmpty())
			return new Response(400,MessegeReference.NOTE_NOT_FOUND,null);
		Note note = getNote.get();
		if(note.isArchive())
			note.setArchive(false);
		if(note.isTrash())
			return new Response(400,MessegeReference.NOTE_TRASHED,null);
		note.setArchive(true);
		note.setPin(false);
		noteRepository.save(note);
		return new Response(200,MessegeReference.NOTE_UPDATED,null);
	}


	@Override
	public Response trash(String noteId) {
		Optional<Note> getNote = noteRepository.findById(noteId);
		if(getNote.isEmpty())
			return new Response(400,MessegeReference.NOTE_NOT_FOUND,null);
		Note note = getNote.get();
		if(note.isTrash())
			note.setTrash(false);
		note.setTrash(true);
		note.setPin(false);
		note.setReminder(null);
		noteRepository.save(note);
		return new Response(200,MessegeReference.NOTE_UPDATED,null);
	}


	@Override
	public Response addReminder(Date date,String noteId) {
		Optional<Note> getNote = noteRepository.findById(noteId);
		if(getNote.isEmpty())
			throw new NoteNotFoundException(MessegeReference.NOTE_NOT_FOUND);
		Note note = getNote.get();
		note.setReminder(date);
		noteRepository.save(note);
		return new Response(200, MessegeReference.REMINDER_ADDED,null);
	}
	
	@Override
	public Response deleteReminder(String noteId) {
		Optional<Note> getNote = noteRepository.findById(noteId);
		if(getNote.isEmpty())
			throw new NoteNotFoundException(MessegeReference.NOTE_NOT_FOUND);
		Note note = getNote.get();
		note.setReminder(null);
		noteRepository.save(note);
		return new Response(200, MessegeReference.REMINDER_ADDED,null);
	}
//	@Override
//	public List<Object> check()
//	{
//		return redisRepository.findAllNotes();
//	}

}
