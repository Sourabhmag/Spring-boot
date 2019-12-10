package com.bridgelabz.fundoouser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bridgelabz.fundoouser.Response.Response;
import com.bridgelabz.fundoouser.dto.Registerdto;
import com.bridgelabz.fundoouser.model.Registration;
import com.bridgelabz.fundoouser.repository.UserRepository;
import com.bridgelabz.fundoouser.services.UserServices;
import com.bridgelabz.fundoouser.utility.TokenUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest {
	@Mock
	public UserRepository userRepository;
	
	@Mock
	public ModelMapper mapper;
	
	@Mock
	public TokenUtil tokenutility;
	
	@InjectMocks
	public UserServices userServices;
	
	@Mock
	public Registerdto registerDto = new Registerdto();
	
	@Mock
	public Registration register = new Registration();
	
	Optional<Registration> userOptional = Optional.of(register);
	public String token = "eyJhbGciOiJIUzI1NiJ9"
			+ ".eyJzdWIiOiI1ZGViMjdjMzg1MmMxOTJkYjRhOTY1MTciLCJpYXQiOjE1NzU4OTAzNjd9"
			+ ".uqDF3n4kn5glXvZC79-b_l8nk6Wuy8BbNpiZ0C5jkDg\"";
	@Test
	public void addUserTest()
	{
		when(userRepository.findByEmail(registerDto.getEmail())).thenReturn(userOptional);
		when(mapper.map(registerDto, Registration.class)).thenReturn(register);
		when(userRepository.save(register)).thenReturn(register);
		
		Response resp = userServices.addUser(registerDto);
		assertEquals(200, resp.getStatusCode());
	}
}
