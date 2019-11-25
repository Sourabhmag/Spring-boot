package com.bridgelabz.NoteService.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.NoteService.model.Label;

@Repository
public interface LabelRepository extends MongoRepository<Label,String>{
	//Label findByUsername(String username);
	List<Label> findByUsername(String username);

}
