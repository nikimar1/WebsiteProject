package com.quizwebsite.core.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.quizwebsite.core.model.ResponseModel;
import com.quizwebsite.core.model.User;
import com.quizwebsite.core.userservice.UserLoginRegistrationService;
import com.quizwebsite.core.userservice.repository.UserRepo;

@RestController
public class RegistrationLoginController {

	@Autowired
	private UserLoginRegistrationService service;
	
	@Autowired
	UserRepo repo;
	
	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<ResponseModel> login(@RequestBody User user)
	{
		service = new UserLoginRegistrationService(user,repo);
		boolean result=false;
		result = service.verifyPassword(user.getUsername(),user.getPass());
		
		ResponseModel response = new ResponseModel();
		response.setTimestamp();
		response.setUser(user);
		
		if(!result)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);	
		else
		{
			response.setSuccess(true);
			return ResponseEntity.ok(response); //returns http 200 and response object
		}
		
	}
	
	@PostMapping("/register")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<ResponseModel> register(@RequestBody User user)
	{
		service = new UserLoginRegistrationService(user,repo);
		boolean result=false;
		
		result = service.createUser(user.getUsername(),user.getPass());
		
		ResponseModel response = new ResponseModel();
		response.setTimestamp();
		response.setUser(user);
		
		if(!result)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);			
		else
		{
			response.setSuccess(true);
			return ResponseEntity.ok(response); //returns http 200 and response object
		}
		
	}
	
	/*
	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	*/

}