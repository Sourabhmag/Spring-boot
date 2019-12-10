package com.bridgelabz.NoteService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.NoteService.model.Label;
/******************************************************************************
 *  Compilation:  javac -d bin LabelRepository.java
 *  Execution:    
 *              
 *  
 *  Purpose: This class is used as repository for label
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   26-11-2019
 *
 ******************************************************************************/
@Repository
public interface LabelRepository extends MongoRepository<Label,String>{
	//Label findByUsername(String username);
	List<Label> findByUserId(String userId);
	Optional<Label> findByIdAndUserId(String labelId,String userId);

}
