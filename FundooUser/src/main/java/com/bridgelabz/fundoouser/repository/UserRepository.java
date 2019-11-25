package com.bridgelabz.fundoouser.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoouser.model.Registration;
/******************************************************************************
 *  Compilation:  javac -d bin UserRepository.java
 *  Execution:    
 *      
 *  Purpose:  Used for repository for mongodb database
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
@Repository
public interface UserRepository extends MongoRepository<Registration,Integer> {
	public Optional<Registration> findByEmail(String email);
}