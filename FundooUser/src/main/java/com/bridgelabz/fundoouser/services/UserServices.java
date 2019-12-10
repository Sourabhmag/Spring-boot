package com.bridgelabz.fundoouser.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoouser.Response.Response;
import com.bridgelabz.fundoouser.dto.ForgotPassworddto;
import com.bridgelabz.fundoouser.dto.Logindto;
import com.bridgelabz.fundoouser.dto.Registerdto;
import com.bridgelabz.fundoouser.dto.UpdateDto;
import com.bridgelabz.fundoouser.exception.custom.PasswordNotMatchException;
import com.bridgelabz.fundoouser.exception.custom.ProfilePicNotExistException;
import com.bridgelabz.fundoouser.exception.custom.RegistrationException;
import com.bridgelabz.fundoouser.exception.custom.UserNotFoundException;
import com.bridgelabz.fundoouser.exception.custom.ValidationException;
import com.bridgelabz.fundoouser.model.ForgotPassword;
import com.bridgelabz.fundoouser.model.Login;
import com.bridgelabz.fundoouser.model.Rabbitmq;
import com.bridgelabz.fundoouser.model.Registration;
import com.bridgelabz.fundoouser.repository.UserRepository;
import com.bridgelabz.fundoouser.utility.TokenUtil;
import com.bridgelabz.fundoouser.utility.Util;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

/******************************************************************************
 * Compilation: javac -d bin UserServices.java Execution:
 * 
 * Purpose: Used provide services to controller class
 *
 * @author Sourabh Magdum
 * @version 1.0
 * @since 19-11-2019
 *
 ******************************************************************************/
@Service
@CacheConfig(cacheNames = "user")
public class UserServices {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private RabbitTemplate template;

	@Autowired
	private TokenUtil tokenUtil;

	public Registration reg;
	public String Token;

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

	public Registration getUser(String token) {
		String userId = tokenUtil.getUsernameFromToken(token);
		Optional<Registration> user = userRepository.findById(userId);
		if (user.isEmpty())
			throw new UserNotFoundException(MessageReference.USER_NOT_FOUND);
		return user.get();

	}

	@Cacheable(key = "#token")
	public Response getUser1(String token) {
		String userId = tokenUtil.getUsernameFromToken(token);
		Optional<Registration> user = userRepository.findById(userId);
		if (user.isEmpty())
			throw new UserNotFoundException(MessageReference.USER_NOT_FOUND);
		return new Response(200, "data", user.get());

	}

	public Registration getUserByEmail(String email) {
		Optional<Registration> user = userRepository.findByEmail(email);
		if (user.isEmpty())
			throw new UserNotFoundException(MessageReference.USER_NOT_FOUND);
		return user.get();

	}

	/**
	 * @Purpose - Adds new user into database
	 * @return details of specific user
	 */
	public Response addUser(Registerdto user) {
		Optional<Registration> getUser = userRepository.findByEmail(user.getEmail());
		if (getUser.isPresent()) {
			throw new RegistrationException(MessageReference.USER_ALREADY_EXIST);
		}
		Registration userData = mapper.map(user, Registration.class);
		String password = user.getPassword();
		password = encryptPassword(password);
		userData.setPassword(password);
		userRepository.save(userData);
		Optional<Registration> getUserNew = userRepository.findByEmail(user.getEmail());
		Token = TokenUtil.generateToken(getUserNew.get().getId());
		Rabbitmq body = Util.getRabbitMq(MessageReference.VALIDATE_ACCOUNT, user.getEmail(), Token);
		template.convertAndSend("userMessageQueue", body);

		return new Response(200, MessageReference.USER_REGISTERED_MAIL_SENT, Token);
	}

	public Registration getObject(Registerdto user) {
		return mapper.map(user, Registration.class);
	}

	public Login getLoginObj(Logindto user) {
		return mapper.map(user, Login.class);
	}

	public ForgotPassword getForgotObj(ForgotPassworddto user) {
		return mapper.map(user, ForgotPassword.class);
	}

	/**
	 * @Purpose - updates user information into database
	 * @return details of specific user
	 */
	public Response updateUser(String token, UpdateDto updatedUser) {

		Registration user = getUser(token);
		if (updatedUser.getName() != null)
			user.setName(updatedUser.getName());

		if (updatedUser.getPhoneNo() != null)
			user.setPhoneNo(updatedUser.getPhoneNo());

		userRepository.save(user);
		return new Response(200, MessageReference.USER_DATA_UPDATED, token);

	}

	/**
	 * @return
	 * @Purpose - delete user from database
	 */
	public Response deleteUser(String token) {
		Registration user = getUser(token);
		userRepository.delete(user);
		return new Response(200, MessageReference.USER_DELETED, null);

	}

	/**
	 * @Purpose - used to login user
	 * @return details of specific user
	 */
	public Response login(Login loginData) {

		Registration getUserData = getUserByEmail(loginData.getUsername());
		String password = (decryptPassword(getUserData.getPassword()));

		if (password.equals(loginData.getPassword())) {
			Token = TokenUtil.generateToken(getUserData.getId());
			return new Response(200, MessageReference.LOGIN_SUCCESSFULL, Token);
		}
		return new Response(400, MessageReference.LOGIN_FAILED, null);
	}

	/**
	 * @Purpose - Send password reset link to user who requested for password reset
	 * @return status of request
	 */
	public Response forgotPassword(String email) {
		Registration user = getUserByEmail(email);
		if (user.isValidate() == false)
			throw new ValidationException(MessageReference.USER_NOT_VALIDATED);
		Token = TokenUtil.generateToken(user.getId());
		Rabbitmq body = Util.getRabbitMq(MessageReference.PASSWORD_RESET, user.getEmail(), Token);
		template.convertAndSend("userMessageQueue", body);
		return new Response(200, MessageReference.PASSWORD_RESET_MSG, Token);
	}

	/**
	 * @Purpose - Used to encrypt password
	 * @param accepts password in the form of string
	 * @return password in encrypted format as a string
	 */
	public String encryptPassword(String password) {
		return Base64.getEncoder().encodeToString(password.getBytes());
	}

	/**
	 * @Purpose - Used to decrypt Password
	 * @param accepts decrypted password in the form of string
	 * @return password in decrypted format as a string
	 */
	public String decryptPassword(String encryptedPassword) {
		return new String(Base64.getMimeDecoder().decode(encryptedPassword));
	}

	/**
	 * @Purpose - Used to validate user account
	 * @param accepts user name
	 */
	public Response validate(String token) {
		Registration user = getUser(token);
		user.setValidate(true);
		userRepository.save(user);
		return new Response(200, MessageReference.USER_VERIFICATION, null);
	}

	/**
	 * @Purpose - Used to reset password
	 * @param Accepts password
	 * @param Accepts token
	 * @return Status of request
	 */
	public Response verify(ForgotPassword password, String token) {

		if (password.getPassword().equals(password.getPasswordCheck()) != true)
			throw new PasswordNotMatchException(MessageReference.PASSWORD_NOT_MATCH);

		Registration user = getUser(token);

		String encryptedPassword = encryptPassword(password.getPassword());
		user.setPassword(encryptedPassword);

		userRepository.save(user);
		return new Response(200, MessageReference.PASSWORD_RESET_SUCCESS, user);
	}

	/**
	 * @Purpose - Used to set profile Pic of user
	 * @param file  - Accepts image file
	 * @param email - Accepts username
	 * @return - Status of operation
	 */

	public Response uploadImg(MultipartFile file, String token) {
		Registration user = getUser(token);
		String url = "/home/user/Documents/workspace-sts-3.9.10.RELEASE/FundooUser/uploads/";
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap( "cloud_name", "dgvo0h0qt",
				   "api_key", "892658779529167",
				   "api_secret", "IX0wvkxeMh_N_YBm6oAxHLqpVR4"));
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		fileName = user.getEmail()+fileName;
		Path getPath = Paths.get(url);
		Path targetLocation = getPath.resolve(fileName);
		try {
			Files.copy(file.getInputStream(), targetLocation,StandardCopyOption.REPLACE_EXISTING);
			File toUpload = new File(targetLocation.toString());
			@SuppressWarnings("rawtypes")
			Map uploadResult = cloudinary.uploader().upload(toUpload, ObjectUtils.emptyMap());
			System.out.println(uploadResult.toString());
			user.setProfilePic(uploadResult.get("secure_url").toString());
			userRepository.save(user);
			return new Response(200, MessageReference.PROFILE_PIC_UPLOADED, user.getProfilePic());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Response(400, MessageReference.PROFILE_PIC_NOT_UPLOADED, null);
		
	}

	/**
	 * @Purpose - Used to get profile Pic of user
	 * @param email   - Accepts username
	 * @param request - Accepts Request
	 * @return - Status of operation
	 */
	public Response getProfilePic(String token, HttpServletRequest request) {
		Registration user = getUser(token);
		if (user.getProfilePic() == null)
			throw new ProfilePicNotExistException(MessageReference.PROFILE_PIC_NOT_EXIST);
		return new Response(200,"", user.getProfilePic());

	}

	/**
	 * @Purpose - Used to delete profile Pic of user
	 * @param email - Accepts username
	 * @return - Status of operation
	 */
	public Response deleteProfilePic(String token) {
		Registration user = getUser(token);
		String filePath = user.getProfilePic();
		File file = new File(filePath);
		if (file.delete()) {
			user.setProfilePic(null);
			userRepository.save(user);
			return new Response(200, MessageReference.FILE_DELETED, token);
		}
		return new Response(400, MessageReference.FILE_NOT_FOUND, null);
	}
}
