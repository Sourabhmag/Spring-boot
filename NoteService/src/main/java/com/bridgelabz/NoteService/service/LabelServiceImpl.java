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
import com.bridgelabz.NoteService.exception.custom.note.NoteNotFoundException;
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
	private NoteRepository noteRepository;
	
	

	/**
	 * @Purpose - Used to add new Label
	 */
	@Override
	public Response addLabel(LabelDto label, String token) {
		Label newLabel = modelMapper.map(label, Label.class);
		newLabel.setUsername(TokenUtil.getUsernameFromToken(token));
		newLabel.setCreatedDate(LocalDateTime.now());
		newLabel.setUpdatededDate(LocalDateTime.now());
		labelRepo.save(newLabel);
		return new Response(400,MessegeReference.LABEL_ADDED,null);
	}

	/**
	 * @Purpose - Used to delete Label
	 * @return - status
	 */
	@Override
	public Response deleteLabel(String id) {
		Optional<Label> getlabel = labelRepo.findById(id);
		if (getlabel.isEmpty())
			throw new LabelNotFoundException(MessegeReference.LABEL_NOT_FOUND);
		Label label = getlabel.get();
		labelRepo.delete(label);
		return new Response(400,MessegeReference.LABEL_DELETED,null);
	}

	/**
	 * @Purpose - Used to update Label
	 * @return - status
	 */
	@Override
	public Response updateLabel(String id, LabelDto labelDto) {
		Optional<Label> getlabel = labelRepo.findById(id);
		if (getlabel.isEmpty())
			throw new LabelNotFoundException(MessegeReference.LABEL_NOT_FOUND);
		Label label = getlabel.get();
		modelMapper.map(labelDto, label);
		label.setUpdatededDate(LocalDateTime.now());
		labelRepo.save(label);
		return new Response(400,MessegeReference.LABEL_UPDATED,null);
	}

	/**
	 * @purpose - Used to get list of labels of perticular user
	 */
	@Override
	public Response getLabelByUsername(String username) {
		List<Label> list =  labelRepo.findByUsername(username);
		if(list.isEmpty())
			throw new LabelNotFoundException(MessegeReference.LABEL_NOT_FOUND);
		return new Response(400,MessegeReference.LIST_OF_LABEL,list);
	}

	/**
	 * @purpose - Used to assign note to label
	 */
	@Override
	public Response assignNoteToLabel(String noteId, String labelId) {
		Optional<Label> getlabel = labelRepo.findById(labelId);
		if (getlabel.isEmpty())
			throw new LabelNotFoundException(MessegeReference.LABEL_NOT_FOUND);

		Optional<Note> getnote = noteRepository.findById(noteId);
		if (getnote.isEmpty())
			throw new NoteNotFoundException(MessegeReference.NOTE_NOT_FOUND);
		
		Label label = getlabel.get();
		Note note = getnote.get();
		
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
	public Response deleteNoteFromLabel(String noteId, String labelId) {
		Optional<Label> getlabel = labelRepo.findById(labelId);
		if (getlabel.isEmpty())
			throw new LabelNotFoundException(MessegeReference.LABEL_NOT_FOUND);

		Optional<Note> getnote = noteRepository.findById(noteId);
		if (getnote.isEmpty())
			throw new NoteNotFoundException(MessegeReference.NOTE_NOT_FOUND);
		
		Label label = getlabel.get();
		Note note = getnote.get();
		
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
	public Response getNoteByLabelId(String labelId) {
		Optional<Label> getlabel = labelRepo.findById(labelId);
		if (getlabel.isEmpty())
			throw new LabelNotFoundException(MessegeReference.LABEL_NOT_FOUND);
		Label label = getlabel.get();
		List<Note> noteList = label.getNoteList();
		if (noteList.isEmpty())
			throw new NoteNotFoundInLabelException(MessegeReference.NOT_ASSIGNED);
		return new Response(400,MessegeReference.LIST_OF_NOTES, noteList);
	}

	
}
