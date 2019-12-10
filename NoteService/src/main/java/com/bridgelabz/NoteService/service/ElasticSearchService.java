package com.bridgelabz.NoteService.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

//import org.elasticsearch.action.delete.DeleteRequest;
//import org.elasticsearch.action.delete.DeleteResponse;
//import org.elasticsearch.action.get.GetRequest;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.update.UpdateRequest;
//import org.elasticsearch.action.update.UpdateResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.NoteService.dto.NoteDto;
import com.bridgelabz.NoteService.exception.custom.note.NoteNotFoundException;
import com.bridgelabz.NoteService.model.Note;
import com.bridgelabz.NoteService.response.Response;
import com.bridgelabz.NoteService.utility.MessegeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
/******************************************************************************
 *  Compilation:  javac -d bin ElasticSearchService.java
 *  Execution:    
 *              
 *  
 *  Purpose: This class is used as Elastic Search Service
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   03-12-2019
 *
 ******************************************************************************/
@Service
public class ElasticSearchService {

//	@Autowired
//	private RestHighLevelClient client;
//
//	@Autowired
//	private ObjectMapper objectMapper;
//
//	@Autowired
//	private ModelMapper modelMapper;
//
//	private static final String INDEX = "note";
//	private static final String TYPE = "notes";
//
//	/**
//	 * @Purpose - Used to add note
//	 * @param note - Accepts note
//	 * @return - Response of operation
//	 * @throws IOException
//	 */
//	@SuppressWarnings("unchecked")
//	public Response addNote(NoteDto note) throws IOException {
//		Note newNote = modelMapper.map(note, Note.class);
//		UUID uuid = UUID.randomUUID();
//		System.out.println(uuid.toString());
//		Map<String, Object> mapNote = objectMapper.convertValue(newNote, Map.class);
//		IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, newNote.getId()).source(mapNote);
//		IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
//
//		return new Response(200, indexResponse.getResult().name(), null);
//	}
//
//	/**
//	 * @purpose - Used to find note by id
//	 * @param id - Accepts id
//	 * @return - Response of operation
//	 * @throws Exception
//	 */
//	public Response findById(String id) throws Exception {
//
//		GetRequest getRequest = new GetRequest(INDEX, TYPE, id);
//
//		GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
//		Map<String, Object> resultMap = (Map<String, Object>) getResponse.getSource();
//		Note note = objectMapper.convertValue(resultMap, Note.class);
//		return new Response(200,"",note);
//
//	}
//
//	
//	/**
//	 * @Purpose - Used to update note
//	 * @param note - Accepts note
//	 * @return - Response of operation
//	 * @throws IOException
//	 */
//	@SuppressWarnings("unchecked")
//	public Response update(NoteDto newNote, String noteId) throws IOException {
//		Note note=null;
//		try {
//			note = (Note) findById(noteId).getData();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		if(note==null)
//			throw new NoteNotFoundException(MessegeReference.NOTE_NOT_FOUND);
//		
//		modelMapper.map(newNote, note);
//		UpdateRequest updateRequest = new UpdateRequest(INDEX, TYPE, noteId);
//		
//		Map<String,Object> updatedNote = objectMapper.convertValue(note, Map.class);
//		updateRequest.doc(updatedNote);
//		
//		UpdateResponse updateResponse = client.update(updateRequest,RequestOptions.DEFAULT);
//		return new Response(200,updateResponse.getResult().name(),null);
//	}
//
//	/**
//	 * @Purpose - Used to delete note
//	 * @param note - Accepts note
//	 * @return - Response of operation
//	 * @throws IOException
//	 */
//	public Response delete(String noteId) throws IOException {
//		DeleteRequest deleteRequest = new DeleteRequest(INDEX,TYPE,noteId);
//		DeleteResponse deleteResponse = client.delete(deleteRequest,RequestOptions.DEFAULT);
//		return new Response(200,deleteResponse.getResult().name(),null);
//	}
//
//	
//	/**
//	 * @purpose - Used to find all notes 
//	 * @return - Response of operation
//	 * @throws Exception
//	 */
//	public Response findAll() throws IOException {
//		SearchRequest searchRequest = new SearchRequest();
//		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//		searchRequest.source(searchSourceBuilder);
//		
//		SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
//		return new Response(200,"",getSearchResult(searchResponse));
//	}
//
//	/**
//	 * @Purpose - Used to extract search result
//	 * @param searchResponse
//	 * @return
//	 */
//	private List<Note> getSearchResult(SearchResponse searchResponse) {
//			SearchHit[] searchHit = searchResponse.getHits().getHits();
//			List<Note> list = new ArrayList<Note>();
//			if(searchHit.length>0)
//			{
//				Arrays.stream(searchHit)
//					.forEach(hit->list.add(objectMapper.convertValue(hit.getSourceAsMap(),Note.class)));
//			}
//		return list;
//	}
//
//	/**
//	 * @purpose - Used to find note by title
//	 * @param searchTitle - search key
//	 * @return
//	 * @throws IOException
//	 */
//	public Response searchByTitle(String searchTitle) throws IOException {
//		SearchRequest searchRequest = new SearchRequest();
//		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//		
//		searchSourceBuilder.query(QueryBuilders
//				.boolQuery().should(QueryBuilders.queryStringQuery(searchTitle)
//						.lenient(true)
//						.field("title")
//						.field("description")));
//		searchRequest.source(searchSourceBuilder);
//		SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
//		return new Response(200,"",getSearchResult(searchResponse));
//	}
}
