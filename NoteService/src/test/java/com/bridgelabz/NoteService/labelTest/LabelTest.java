package com.bridgelabz.NoteService.labelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bridgelabz.NoteService.dto.LabelDto;
import com.bridgelabz.NoteService.model.Label;
import com.bridgelabz.NoteService.model.Note;
import com.bridgelabz.NoteService.repository.LabelRepository;
import com.bridgelabz.NoteService.repository.NoteRepository;
import com.bridgelabz.NoteService.response.Response;
import com.bridgelabz.NoteService.service.LabelServiceImpl;
import com.bridgelabz.NoteService.utility.TokenUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LabelTest {
	@Mock
	public LabelRepository labelRepository;
	
	@Mock
	NoteRepository noteRepository;
	
	@Mock
	public ModelMapper mapper;

	
	@Mock
	public TokenUtil tokenutility;

	@InjectMocks
	public LabelServiceImpl service;
	
	public String token = "eyJhbGciOiJIUzI1NiJ9"
			+ ".eyJzdWIiOiI1ZGU5ZjAxYWU0NGZhYzYwZDMwOWVjNDUiLCJpYXQiOjE1NzU2MTI0NDJ9"
			+ ".r9eeRJ3OAAnHg_YmRmY_Vh93PcZH4ZFI9GAgYzqccb0";

	public String email = "sourabh161196@gmail.com";
	public Label label = new Label();
	public Note note = new Note();
	public LabelDto labelDto = new LabelDto();
	public String labelId="5dede4c805d38471eeaf6c45";
	public String noteId= "svg";
	public LocalDateTime date ;
	String userId="kbvgs";
	
	Optional<Note> noteOptional = Optional.of(note);
	
	Optional<Label> labelOptional = Optional.of(label);
	
	
	@Test
	public void addLabelTest()
	{
		when(mapper.map(labelDto, Label.class)).thenReturn(label);
		label.setCreatedDate(date);
		label.setUpdatededDate(date);
		when(labelRepository.save(label)).thenReturn(label);
		Response resp = service.addLabel(labelDto, token);
		assertEquals(200, resp.getStatusCode());
	}
	
	@Test
	public void deleteLabelTest()
	{
		when(service.getLabel(labelId, token)).thenReturn(labelOptional);
		labelRepository.delete(label);
		Response resp = service.deleteLabel(labelId, token);
		assertEquals(200, resp.getStatusCode());
	}
	
	@Test
	public void updateLabelTest()
	{
		when(service.getLabel(labelId, token)).thenReturn(labelOptional);	
		when(mapper.map(labelDto, Label.class)).thenReturn(label);
		label.setUpdatededDate(date);
		when(labelRepository.save(label)).thenReturn(label);
		Response resp = service.updateLabel(labelId,labelDto, token);
		assertEquals(200, resp.getStatusCode());
	}
}
