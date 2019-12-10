package com.bridgelabz.fundoouser.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoouser.Response.Response;
import com.bridgelabz.fundoouser.dto.ForgotPassworddto;
import com.bridgelabz.fundoouser.dto.Logindto;
import com.bridgelabz.fundoouser.dto.Registerdto;
import com.bridgelabz.fundoouser.dto.UpdateDto;
import com.bridgelabz.fundoouser.model.Registration;
import com.bridgelabz.fundoouser.services.UserServices;

/******************************************************************************
 * Compilation: javac -d bin UserController.java Execution:
 * 
 * Purpose: Used as a controller class of user actions
 *
 * @author Sourabh Magdum
 * @version 1.0
 * @since 19-11-2019
 *
 ******************************************************************************/
@RestController
@RequestMapping("/user")
//@CacheConfig(cacheNames = "sourabh")
public class UserController {
	@Autowired
	private UserServices userServices;

	// Method to print all data
	@GetMapping("/getAllUsers")
	public List<Registration> getUsersData() {
		return userServices.getUserList();
	}

	// Method to print information of specific user
	@GetMapping("/byToken")
	public Response filterUser(@RequestHeader String token) {
		return userServices.getUser1(token);
	}

	// Method to add new User
	@PostMapping("/register")
	public ResponseEntity<Response> addUser(@Valid @RequestBody Registerdto user) {
		return new ResponseEntity<Response>(userServices.addUser(user), HttpStatus.OK);

	}

	// Method to update data of existing user
	@PutMapping("/update")
	public ResponseEntity<Response> updateUser(@Valid @RequestBody UpdateDto user, @RequestHeader String token) {
		return new ResponseEntity<Response>(userServices.updateUser(token,user),
				HttpStatus.OK);
	}

	// Method to delete existing user
	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteUser(@RequestHeader String token) {
		return new ResponseEntity<Response>(userServices.deleteUser(token), HttpStatus.OK);
	}

	// Method to login existing user
	@PostMapping("/login")
	public ResponseEntity<Response> login(@Valid @RequestBody Logindto loginData) {
		return new ResponseEntity<Response>(userServices.login(userServices.getLoginObj(loginData)), HttpStatus.OK);
	}

	// Method to forgot password
	@PutMapping("/forgot")
	public ResponseEntity<Response> forgotPassword(@RequestParam String email) {
		return new ResponseEntity<Response>(userServices.forgotPassword(email),HttpStatus.OK);
	}

	// Method to verify and reset the password
	@PostMapping("/verify")
	public ResponseEntity<Response> verify(@RequestParam String token, @RequestBody ForgotPassworddto password) {
		return new ResponseEntity<Response>(userServices.verify(userServices.getForgotObj(password), token),
				HttpStatus.OK);
	}

	// Method to validate user account
	@GetMapping("/validate")
	public ResponseEntity<Response> userVerification(@RequestParam String token) {	
		return new ResponseEntity<Response>(userServices.validate(token),HttpStatus.OK);
	}

	// Used to add profile pic of user
	@PostMapping(value = "/uploadImg", consumes = "multipart/form-data")
	public ResponseEntity<Response> uploadImg(@RequestHeader String token, MultipartFile file) {
		return new ResponseEntity<Response>(userServices.uploadImg(file, token), HttpStatus.OK);
	}

	// Used to retrive profile pic of user
	@PutMapping("/getProfilePic/{email:.+}")
	public ResponseEntity<Response> getProfilePic(@PathVariable String email, HttpServletRequest request,@RequestHeader String token) {
		return new ResponseEntity<Response>(userServices.getProfilePic(token, request),HttpStatus.OK);
	}

	// Used to delete profile pic of user
	@PutMapping("/deleteProfilePic")
	public ResponseEntity<Response> deleteProfilePic(@RequestHeader String token) {
		return new ResponseEntity<Response>(userServices.deleteProfilePic(token), HttpStatus.OK);
	}

}
