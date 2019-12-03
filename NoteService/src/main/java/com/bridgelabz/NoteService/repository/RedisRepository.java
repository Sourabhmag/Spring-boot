package com.bridgelabz.NoteService.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.bridgelabz.NoteService.model.Label;
import com.bridgelabz.NoteService.model.Note;
/******************************************************************************
 *  Compilation:  javac -d bin RedisRepository.java
 *  Execution:    
 *              
 *  
 *  Purpose: This class is used as Redis repository
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   03-12-2019
 *
 ******************************************************************************/
@Repository("redisRepository")
public class RedisRepository {
	
	@SuppressWarnings("unused")
	private RedisTemplate<String, ?> redisTemplate;
	private HashOperations<String,String,Object> hashOperations;
	public RedisRepository(RedisTemplate<String, ?> redisTemplate) {
		this.hashOperations = redisTemplate.opsForHash();
		this.redisTemplate = redisTemplate;
	}

	public void save(Note note)
	{
		hashOperations.put("NOTE",note.getId(),note);
	}
	
	public void save(Label label)
	{
		hashOperations.put("Label",label.getId(),label);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Note> findAllNotes()
	{
		return (Map<String, Note>) hashOperations.values("NOTE");
	}
	
//	@SuppressWarnings("unchecked")
//	public List<Object> findAllNotes()
//	{
//		return (List<Object>) hashOperations.values("NOTE");
//	}
	
	public List<Object> findAllLabels()
	{
		return (List<Object>)hashOperations.values("Label");
	}
	
	public Note findNoteById(String id)
	{
		return (Note)hashOperations.get("NOTE",id);
	}
	
	public Label findLabelById(String id)
	{
		return (Label)hashOperations.get("Label",id);
	}
	
	public void update(Note note)
	{
		save(note);
	}
	
	public void update(Label label)
	{
		save(label);
	}
	
	public void deleteNote(String id)
	{
		hashOperations.delete("NOTE",id);
	}
	
	public void deleteLabel(String id)
	{
		hashOperations.delete("Label",id);
	}
}
