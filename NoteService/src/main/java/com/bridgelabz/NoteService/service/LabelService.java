package com.bridgelabz.NoteService.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.NoteService.dto.LabelDto;
import com.bridgelabz.NoteService.model.Label;
import com.bridgelabz.NoteService.response.Response;

@Service
public interface LabelService {

	void addLabel(LabelDto label, String token);
	String deleteLabel(String id);
	String updateLabel(String id, LabelDto note);
	List<Label> getLabelByUsername(String username);
	String assignNote(String noteId,String labelId);
	String deleteNote(String noteId,String labelId);
	Response getNoteByLabelId(String labelId);

}
