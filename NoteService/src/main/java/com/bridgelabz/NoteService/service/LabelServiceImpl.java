package com.bridgelabz.NoteService.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.NoteService.dto.LabelDto;
import com.bridgelabz.NoteService.exception.custom.label.LabelNotFoundException;
import com.bridgelabz.NoteService.exception.custom.label.NoteNotFoundInLabelException;
import com.bridgelabz.NoteService.model.Label;
import com.bridgelabz.NoteService.model.Note;
import com.bridgelabz.NoteService.repository.LabelRepository;
import com.bridgelabz.NoteService.repository.NoteRepository;
import com.bridgelabz.NoteService.response.Response;
import com.bridgelabz.NoteService.utility.MessegeReference;
import com.bridgelabz.NoteService.utility.TokenUtil;

@Service
public class LabelServiceImpl implements LabelService{

	@Autowired
	private LabelRepository labelRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	public NoteRepository noteRepository;
	
	public Optional<Label> getLabel(String labelId,String token)
	{
		String userId = TokenUtil.getUsernameFromToken(token);
	    Optional<Label> label = labelRepo.findByIdAndUserId(labelId, userId);
		
//		if (label.isEmpty())
//			throw new LabelNotFoundException(MessegeReference.LABEL_NOT_FOUND);
		
		return label;
	}
	
	public Optional<Note> getNote(String noteId,String token)
	{
		String userId = TokenUtil.getUsernameFromToken(token);
		Optional<Note> note = noteRepository.findByIdAndUserId(noteId, userId);
//		if (note.isEmpty())
//			throw new NoteNotFoundException(MessegeReference.NOTE_NOT_FOUND);
		return note;
	}

	/**
	 * @purpose - Used to get list of labels of perticular user
	 */
	@Override
	public Response getLabelByUsername(String token) {
		String userId = TokenUtil.getUsernameFromToken(token);
		List<Label> list =  labelRepo.findByUserId(userId);
		if(list.isEmpty())
			throw new LabelNotFoundException(MessegeReference.LABEL_NOT_FOUND);
		return new Response(400,MessegeReference.LIST_OF_LABEL,list);
	}

	/**
	 * @Purpose - Used to add new Label
	 */
	@Override
	public Response addLabel(LabelDto label, String token) {
		Label newLabel = modelMapper.map(label, Label.class);
		newLabel.setUserId(TokenUtil.getUsernameFromToken(token));
		newLabel.setCreatedDate(LocalDateTime.now());
		newLabel.setUpdatededDate(LocalDateTime.now());
		labelRepo.save(newLabel);
		return new Response(200,MessegeReference.LABEL_ADDED,null);
	}

	
	/**
	 * @Purpose - Used to delete Label
	 * @return - status
	 */
	@Override
	public Response deleteLabel(String labelId,String token) {
		Label label = getLabel(labelId, token).get();
		labelRepo.delete(label);
		return new Response(200,MessegeReference.LABEL_DELETED,null);
	}

	/**
	 * @Purpose - Used to update Label
	 * @return - status
	 */
	@Override
	public Response updateLabel(String labelId, LabelDto labelDto,String token) {
		Label label = getLabel(labelId, token).get();
		modelMapper.map(labelDto, label);
		label.setUpdatededDate(LocalDateTime.now());
		labelRepo.save(label);
		return new Response(200,MessegeReference.LABEL_UPDATED,null);
	}

	
	/**
	 * @purpose - Used to assign note to label
	 */
	@Override
	public Response assignNoteToLabel(String noteId, String labelId,String token) {
	
		Optional<Label> getLabel = getLabel(labelId, token);
		Optional<Note> getNote = getNote(noteId, token);
		
		Label label = getLabel.get();
		Note note = getNote.get();
		
		List<Note> listOfNote = new ArrayList<Note>();
		List<Label> listOfLabel = new ArrayList<Label>();

		listOfNote = label.getNoteList();
		listOfLabel = note.getLabelList();

		listOfLabel.add(label);
		listOfNote.add(note);

		label.setNoteList(listOfNote);
		note.setLabelList(listOfLabel);

		noteRepository.save(note);
		labelRepo.save(label);

		return new Response(200,MessegeReference.LABEL_ASSIGNED,null);

	}

	/**
	 * @Purpose - User to delete note of label
	 */
	@Override
	public Response deleteNoteFromLabel(String noteId, String labelId,String token) {
		Optional<Label> getLabel = getLabel(labelId, token);
		Optional<Note> getNote = getNote(noteId, token);
		
		Label label = getLabel.get();
		Note note = getNote.get();
		
		List<Note> listOfNote = new ArrayList<Note>();
		List<Label> listOfLabel = new ArrayList<Label>();

		listOfNote = label.getNoteList();
		listOfLabel = note.getLabelList();

		listOfLabel.remove(label);
		listOfNote.remove(note);

		label.setNoteList(listOfNote);
		note.setLabelList(listOfLabel);

		noteRepository.save(note);
		labelRepo.save(label);

		return new Response(400,MessegeReference.NOTE_DELETE_FROM_LABEL,null);
	}

	/**
	 * @purpose - Used to get list of notes of perticular label
	 */
	@Override
	public Response getNoteByLabelId(String labelId,String token) {
		
		Label label = getLabel(labelId, token).get();
		List<Note> noteList = label.getNoteList();
		if (noteList.isEmpty())
			throw new NoteNotFoundInLabelException(MessegeReference.NOT_ASSIGNED);
		return new Response(400,MessegeReference.LIST_OF_NOTES, noteList);
	}

	
}
