package com.bridgelabz.fundoouser.services;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoouser.Response.Response;
import com.bridgelabz.fundoouser.dto.ForgotPassworddto;
import com.bridgelabz.fundoouser.dto.Logindto;
import com.bridgelabz.fundoouser.dto.Registerdto;
import com.bridgelabz.fundoouser.exception.custom.DeleteException;
import com.bridgelabz.fundoouser.exception.custom.RegistrationException;
import com.bridgelabz.fundoouser.model.ForgotPassword;
import com.bridgelabz.fundoouser.model.Login;
import com.bridgelabz.fundoouser.model.Rabbitmq;
import com.bridgelabz.fundoouser.model.Registration;
import com.bridgelabz.fundoouser.repository.UserRepository;
import com.bridgelabz.fundoouser.utility.TokenUtil;
import com.bridgelabz.fundoouser.utility.Util;
/******************************************************************************
 *  Compilation:  javac -d bin UserServices.java
 *  Execution:    
 *      
 *  Purpose:   Used provide services to controller class
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
@Service
public class UserServices {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TokenUtil tokenUtil;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private RabbitTemplate template;
	@Autowired
    private FileStorageService fileStorageService;
	
	public Registration reg;
	public String Token;
	private String path = "/home/user/Documents/workspace-sts-3.9.10.RELEASE/FundooUser/uploads/";
	/**
	 * @Purpose - Returns list of all users
	 * @return List of all users
	 */
	public List<Registration> getUserList() {

		return userRepository.findAll();
	}
	/**
	 * @Purpose - Returns details of specific user
	 * @return details of specific user
	 */
	public Registration getUser(String email)
	{
		//userRepository.findAll().stream().filter(i->i.getEmail().equals(email));
		Registration user = userRepository.findByEmail(email).orElse(null);
		if(user!=null)
			return user;
		return null;
		
	}
	/**
	 * @Purpose - Adds new user into database
	 * @return details of specific user
	 */
	public Response addUser(Registerdto user)
	{
		if(getUser(user.getEmail())!=null) {
			throw new RegistrationException(MessageReference.USER_ALREADY_EXIST);
		}
		Registration userData = mapper.map(user,Registration.class);
		String password = user.getPassword();
		password = encryptPassword(password);
		userData.setPassword(password);
		reg=userData;
		Token = TokenUtil.generateToken(user.getEmail());
		Rabbitmq body = Util.getRabbitMq(MessageReference.VALIDATE_ACCOUNT,user.getEmail(), Token);
		template.convertAndSend("userMessageQueue",body);
		userRepository.save(userData);
		
		return new Response(200,MessageReference.USER_REGISTERED_MAIL_SENT,user);
	}
	public Registration getObject(Registerdto user)
	{
		return mapper.map(user,Registration.class);
	}
	public Login getLoginObj(Logindto user)
	{
		return mapper.map(user,Login.class);
	}
	public ForgotPassword getForgotObj(ForgotPassworddto user)
	{
		return mapper.map(user,ForgotPassword.class);
	}
	/**
	 * @Purpose - updates user information into database
	 * @return details of specific user
	 */
	public Response updateUser(String email, Registration user) {
		
		Registration userUpdate = userRepository.findByEmail(email).orElse(null);
		if(userUpdate==null)
			return new Response(400,MessageReference.USER_NOT_FOUND,null);
		userUpdate =user;
		userRepository.save(userUpdate);
		return new Response(200,MessageReference.USER_DATA_UPDATED,userUpdate);
		
	}
	/**
	 * @return 
	 * @Purpose - delete user from database
	 */
	public Response deleteUser(String email) {
		Registration user = userRepository.findByEmail(email).orElse(null);
		if(user==null)
		{
			throw new DeleteException(MessageReference.USER_NOT_FOUND);
		}
		userRepository.delete(user);
		return new Response(200,MessageReference.USER_DELETED,null);
		
	}
	/**
	 * @Purpose - used to login user
	 * @return details of specific user
	 */
	public Response login(Login loginData) {

		Registration getUserData = userRepository.findByEmail(loginData.getUsername()).orElse(null);
		String password = (decryptPassword(getUserData.getPassword()));
		
		if(getUserData!=null && password.equals(loginData.getPassword()))
		{
			Token = TokenUtil.generateToken(getUserData.getEmail());
			return new Response(200,"Login Successfull",Token);
		}
		return new Response(400,"Login Failed",null);
	}
	/**
	 * @Purpose - Send password reset link to user who requested for password reset
	 * @return status of request
	 */
	public String forgotPassword(String email) {
		Registration user = getUser(email);
		if(user==null)
			return "User is not Registered";
		else if(user.isValidate()==false)
			return "User is not validated";
		reg=user;
		Token = TokenUtil.generateToken(user.getEmail());
		Rabbitmq body = Util.getRabbitMq(MessageReference.PASSWORD_RESET,user.getEmail(), Token);
		template.convertAndSend("userMessageQueue",body);
		return "Password reset link is sent to your registered mail id";
	}
	
	/**
	 * @Purpose - Used to encrypt password
	 * @param accepts password in the form of string
	 * @return password in encrypted format as a string
	 */
	public String encryptPassword(String password)
	{
		return Base64.getEncoder().encodeToString(password.getBytes());
	}
	
	/**
	 * @Purpose - Used to decrypt Password
	 * @param accepts decrypted password in the form of string
	 * @return password in decrypted format as a string
	 */
	public String decryptPassword(String encryptedPassword)
	{
		return new String(Base64.getMimeDecoder().decode(encryptedPassword));
	}
	
	/**
	 * @Purpose - Used to validate user account
	 * @param accepts user name
	 */
	public void validate(String username) {
		
		Registration user = getUser(username);
		user.setValidate(true);
		updateUser(username,user);
	}

	/**
	 * @Purpose - Used to reset password
	 * @param Accepts password
	 * @param Accepts token
	 * @return Status of request
	 */
	public Response verify(ForgotPassword password,String token) {
		
		if(this.Token.equals(token)!=true)
			return new Response(400,"Unauthentic user found", null);
	
		else if(password.getPassword().equals(password.getPasswordCheck())!=true)
			return new Response(400, "Enter password is not matching", null);
		
		String username = (tokenUtil.getUsernameFromToken(token));
		Registration user = getUser(username);
		
		String encryptedPassword = encryptPassword(password.getPassword());
		user.setPassword(encryptedPassword);
		
		updateUser(username, user);
		return new Response(200, "Password reset successfull", user);
	}
	/**
	 * @Purpose - Used to set profile Pic of user
	 * @param file - Accepts image file
	 * @param email - Accepts username
	 * @return - Status of operation
	 */
	public Response uploadImg(MultipartFile file,String email) {
		Registration user = getUser(email);
		if(user == null)
			throw new RegistrationException(MessageReference.USER_NOT_FOUND);
		String fileName = fileStorageService.storeFile(file,email);
		
		String fileDownloadUri = path+fileName;
		
		user.setProfilePic(fileDownloadUri);
		userRepository.save(user);
		return new Response(200,MessageReference.PROFILE_PIC_UPLOADED,fileDownloadUri);
	}
	
	
	/**
	 *  @Purpose - Used to get profile Pic of user
	 * @param email - Accepts username
	 * @param request - Accepts Request
	 * @return - Status of operation
	 */
	public ResponseEntity<Resource> getProfilePic(String email, HttpServletRequest request) {
		Registration user = getUser(email);
		if(user == null)
			throw new RegistrationException(MessageReference.USER_NOT_FOUND);
		Resource resource =	fileStorageService.loadFileAsResource(user.getProfilePic());
		
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(contentType==null)
			contentType="application/octet-stream";
		return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
		
	}
	/**
	 * @Purpose - Used to delete profile Pic of user
	 * @param email - Accepts username
	 * @return - Status of operation
	 */
	public Response deleteProfilePic(String email) {
		Registration user = getUser(email);
		String filePath = user.getProfilePic();
		File file = new File(filePath);
		if(file.delete()) {
			user.setProfilePic(null);
			userRepository.save(user);
			return new Response(200,MessageReference.FILE_DELETED,null);
			}
		return new Response(400,MessageReference.FILE_NOT_FOUND,null);
	}
}
