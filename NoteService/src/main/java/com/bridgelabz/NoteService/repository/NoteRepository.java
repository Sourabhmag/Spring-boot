package com.bridgelabz.NoteService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.NoteService.model.Note;
/******************************************************************************
 *  Compilation:  javac -d bin NoteRepository.java
 *  Execution:    
 *              
 *  
 *  Purpose: This class is used as repository for Note
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   26-11-2019
 *
 ******************************************************************************/
@Repository
public interface NoteRepository extends MongoRepository<Note,String>{
	List<Note> findByUserId(String username);
	Optional<Note> findByIdAndUserId(String noteId,String userId);
}
