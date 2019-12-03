package com.bridgelabz.NoteService.service;

import org.springframework.stereotype.Service;

import com.bridgelabz.NoteService.dto.LabelDto;
import com.bridgelabz.NoteService.response.Response;
/******************************************************************************
 *  Compilation:  javac -d bin LabelService.java
 *  Execution:    
 *              
 *  
 *  Purpose: This interface is used to define services of label
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   26-11-2019
 *
 ******************************************************************************/
@Service
public interface LabelService {

	Response addLabel(LabelDto label, String token);
	Response deleteLabel(String id);
	Response updateLabel(String id, LabelDto note);
	Response getLabelByUsername(String username);
	Response assignNoteToLabel(String noteId,String labelId);
	Response deleteNoteFromLabel(String noteId,String labelId);
	Response getNoteByLabelId(String labelId);

}
