package com.bridgelabz.NoteService.NoteTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bridgelabz.NoteService.dto.NoteDto;
import com.bridgelabz.NoteService.model.Note;
import com.bridgelabz.NoteService.repository.NoteRepository;
import com.bridgelabz.NoteService.response.Response;
import com.bridgelabz.NoteService.service.NoteServiceImp;
import com.bridgelabz.NoteService.utility.TokenUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NoteServiceImplTest {
	@Mock
	public NoteRepository noteRepository;

	@Mock
	public ModelMapper mapper;

	@Mock
	public TokenUtil tokenutility;

	@InjectMocks
	public NoteServiceImp service = new NoteServiceImp();

	public String token = "eyJhbGciOiJIUzI1NiJ9"
			+ ".eyJzdWIiOiI1ZGU5ZjAxYWU0NGZhYzYwZDMwOWVjNDUiLCJpYXQiOjE1NzU2MTI0NDJ9"
			+ ".r9eeRJ3OAAnHg_YmRmY_Vh93PcZH4ZFI9GAgYzqccb0";

	public String email = "sourabh161196@gmail.com";
	@Mock
	public Note note = new Note();
	@Mock
	public NoteDto noteDto = new NoteDto();
	public String noteId="5de8c52d711372356c78d0e7";
	public LocalDateTime date ;
	
	Optional<Note> noteOptional = Optional.of(note);
	
	@Test
	public void addNoteTest() throws IOException {
		when(mapper.map(noteDto, Note.class)).thenReturn(note);
		note.setCreatedDate(date);
		note.setUpdatedDate(date);
        note.setUserId(noteId);
        when(noteRepository.save(note)).thenReturn(note);
        
        Response resp = service.addNote(noteDto, token);
		assertEquals(200, resp.getStatusCode());

	}

	@Test
	public void deleteNoteTest()
	{
		note.setTrash(false);
		when(service.getNote(noteId, token)).thenReturn(noteOptional);
		noteRepository.delete(note);
		
		Response resp = service.deleteNode(noteId, token);
		assertEquals(400, resp.getStatusCode());
	}
	
	@Test
	public void updateTest()
	{
		when(service.getNote(noteId, token)).thenReturn(noteOptional);
		mapper.map(noteDto, note);
		when(noteRepository.save(note)).thenReturn(note);
		
		Response resp = service.update(noteId, noteDto,token);
		assertEquals(400, resp.getStatusCode());
	}
}
