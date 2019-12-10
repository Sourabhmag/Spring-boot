package com.bridgelabz.NoteService.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * Compilation: javac -d bin NoteServiceImp.java Execution:
 * 
 * 
 * Purpose: This Class is used to implement services of note
 *
 * @author Sourabh Magdum
 * @version 1.0
 * @since 26-11-2019
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
		this.redisRepository = redisRepository;
	}
	
	public NoteServiceImp() {
	}

	public Optional<Note> getNote(String noteId, String token) {
		String userId = TokenUtil.getUsernameFromToken(token);
		Optional<Note> getnote = noteRepository.findByIdAndUserId(noteId, userId);
//		if (getnote.isEmpty()) {
//			throw new NoteNotFoundException(MessegeReference.NOTE_NOT_FOUND);
//		}
		return getnote;
	}

	/**
	 * @Purpose - Used to add new Note
	 */
	@Override
	public Response addNote(NoteDto newNote, String token) {
		System.out.println(token);
		System.out.println(newNote.getDescription());
		Note getNewNote = modelMapper.map(newNote, Note.class);
		LocalDateTime createdDate = LocalDateTime.now();
		getNewNote.setCreatedDate(createdDate);
		getNewNote.setUpdatedDate(createdDate);
		
		String userId = TokenUtil.getUsernameFromToken(token);
		getNewNote.setUserId(userId);
		noteRepository.save(getNewNote);

		return new Response(200, MessegeReference.NOTE_ADDED, token);
	}

	/**
	 * @Purpose - Used to delete note
	 * @return - status
	 */

	@Override
	public Response deleteNode(String noteId, String token) {

//		if(redisRepository.findNoteById(noteId)!=null)
//			redisRepository.deleteNote(noteId);

		Optional<Note> getNote = getNote(noteId, token);
		Note note = getNote.get();
		if (note.isTrash()) {
			noteRepository.delete(note);
			return new Response(200, MessegeReference.NOTE_DELETED, token);
		}
		return new Response(400, MessegeReference.NOTE_UNTRASHED, token);
	}

	/**
	 * @Purpose - Used to update note
	 * @return - status
	 */
	// @CachePut(key = "#id",value = "abcd")
	@Override
	public Response update(String noteId, NoteDto updatedNote, String token) {

		Optional<Note> getNote = getNote(noteId, token);
		Note note = getNote.get();
		modelMapper.map(updatedNote, note);

//		if(redisRepository.findNoteById(id)!=null)
//			redisRepository.update(note);

		LocalDateTime updatedDate = LocalDateTime.now();
		note.setUpdatedDate(updatedDate);

		noteRepository.save(note);
		return new Response(400, MessegeReference.NOTE_UPDATED, token);
	}

	/**
	 * @Purpose - Used to get list of all notes
	 * @return - list of notes
	 */

	@Override
	public Response getNoteList() {
		List<Note> list = noteRepository.findAll();
		if (list.isEmpty())
			throw new ListIsEmpty(MessegeReference.LIST_IS_EMPTY);
		return new Response(200, MessegeReference.LIST_OF_NOTES, list);
	}

	/**
	 * @Purpose - Used to get list of all notes of perticular user
	 * @return - list of notes
	 */

	@Override
	public Response getNoteByUserId(String token) {
		String userId = TokenUtil.getUsernameFromToken(token);
		List<Note> list = noteRepository.findByUserId(userId);
		if (list.isEmpty())
			throw new ListIsEmpty(MessegeReference.LIST_IS_EMPTY);
		return new Response(200, MessegeReference.LIST_OF_NOTES, list);
	}

	/**
	 * @Purpose - Used to sort all notes by title
	 * @return - sorted list of notes
	 */
	@Override
	public Response sortByTitle(String token) {
		@SuppressWarnings("unchecked")
		List<Note> listOfNote = (List<Note>) getNoteByUserId(token).getData();

		listOfNote.sort(
				(listOfNote1, listOfNote2) -> (listOfNote1.getTitle().compareToIgnoreCase(listOfNote2.getTitle())));

		if (listOfNote.isEmpty())
			throw new ListIsEmpty(MessegeReference.LIST_IS_EMPTY);
		return new Response(200, MessegeReference.LIST_OF_NOTES, listOfNote);
	}

	/**
	 * @Purpose - Used to sort all notes by created date
	 * @return - sorted list of notes
	 */

	@Override
	public Response sortByDate(String token) {
		@SuppressWarnings("unchecked")
		List<Note> listOfNote = (List<Note>) getNoteByUserId(token).getData();
		listOfNote.sort(
				(listOfNote1, listOfNote2) -> listOfNote1.getCreatedDate().compareTo(listOfNote2.getCreatedDate()));
		if (listOfNote.isEmpty())
			throw new ListIsEmpty(MessegeReference.LIST_IS_EMPTY);
		return new Response(200, MessegeReference.LIST_OF_NOTES, listOfNote);
	}

	/**
	 * @purpose - Used to add colaborator to a note
	 * @return - status
	 */
	@Override
	public Response addColaborator(String noteId, List<String> userNameList, String token) {

		Optional<Note> getNote = getNote(noteId, token);
		Note ownerNote = getNote.get();

		List<String> list = new ArrayList<String>();
		list = ownerNote.getColaborators();

		list.addAll(userNameList);
		ownerNote.setColaborators(list);

		noteRepository.save(ownerNote);
		return new Response(200, MessegeReference.COLABORATOR_ADD, token);
	}

	@Override
	public Response removeColaborator(String noteId, List<String> userNameList, String token) {
		Optional<Note> getNote = getNote(noteId, token);
		Note note = getNote.get();
		List<String> list = new ArrayList<String>();
		list = note.getColaborators();
		list.removeAll(userNameList);
		note.setColaborators(list);
		noteRepository.save(note);
		return new Response(400, MessegeReference.COLABORATOR_REMOVED, token);
	}

	@Override
	public Map<String, Note> check() {
		return redisRepository.findAllNotes();
	}

	@Override
	public Response pin(String noteId, String token) {
		String userId = TokenUtil.getUsernameFromToken(token);
		Optional<Note> getNote = noteRepository.findByIdAndUserId(noteId, userId);
		if (getNote.isEmpty())
			return new Response(400, MessegeReference.NOTE_NOT_FOUND, token);
		Note note = getNote.get();
		if (note.isTrash())
			return new Response(400, MessegeReference.NOTE_TRASHED, null);
		if (note.isPin())
			note.setPin(false);
		else
			note.setPin(true);
		note.setArchive(false);
		noteRepository.save(note);

		return new Response(200, MessegeReference.NOTE_UPDATED, null);
	}

	@Override
	public Response archive(String noteId, String token) {
		String userId = TokenUtil.getUsernameFromToken(token);
		Optional<Note> getNote = noteRepository.findByIdAndUserId(noteId, userId);
		if (getNote.isEmpty())
			return new Response(400, MessegeReference.NOTE_NOT_FOUND, null);
		Note note = getNote.get();
		if (note.isTrash())
			return new Response(400, MessegeReference.NOTE_TRASHED, null);
		if (note.isArchive())
			note.setArchive(false);
		else
			note.setArchive(true);
		note.setPin(false);
		noteRepository.save(note);
		return new Response(200, MessegeReference.NOTE_UPDATED, null);
	}

	@Override
	public Response trash(String noteId, String token) {
		String userId = TokenUtil.getUsernameFromToken(token);
		Optional<Note> getNote = noteRepository.findByIdAndUserId(noteId, userId);
		if (getNote.isEmpty())
			return new Response(400, MessegeReference.NOTE_NOT_FOUND, null);
		Note note = getNote.get();
		if (note.isTrash())
			note.setTrash(false);
		else
			note.setTrash(true);
		note.setPin(false);
		note.setReminder(null);
		noteRepository.save(note);
		return new Response(200, MessegeReference.NOTE_UPDATED, null);
	}

	@Override
	public Response addReminder(Date date, String noteId, String token) {
		String userId = TokenUtil.getUsernameFromToken(token);
		Optional<Note> getNote = noteRepository.findByIdAndUserId(noteId, userId);
		if (getNote.isEmpty())
			throw new NoteNotFoundException(MessegeReference.NOTE_NOT_FOUND);
		Note note = getNote.get();
		note.setReminder(date);
		noteRepository.save(note);
		return new Response(200, MessegeReference.REMINDER_ADDED, null);
	}

	@Override
	public Response deleteReminder(String noteId, String token) {
		String userId = TokenUtil.getUsernameFromToken(token);
		Optional<Note> getNote = noteRepository.findByIdAndUserId(noteId, userId);
		if (getNote.isEmpty())
			throw new NoteNotFoundException(MessegeReference.NOTE_NOT_FOUND);
		Note note = getNote.get();
		note.setReminder(null);
		noteRepository.save(note);
		return new Response(200, MessegeReference.REMINDER_ADDED, null);
	}
//	@Override
//	public List<Object> check()
//	{
//		return redisRepository.findAllNotes();
//	}

}
