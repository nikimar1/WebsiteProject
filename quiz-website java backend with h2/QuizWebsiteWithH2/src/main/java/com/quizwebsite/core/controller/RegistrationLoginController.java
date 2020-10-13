package com.quizwebsite.core.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.quizwebsite.core.model.ResponseModel;
import com.quizwebsite.core.model.TestModel;
import com.quizwebsite.core.model.User;
import com.quizwebsite.core.userservice.UserLoginRegistrationService;
import com.quizwebsite.core.userservice.repository.TestRepo;
import com.quizwebsite.core.userservice.repository.UserRepo;

//import com.nimbusds.jwt.JWTClaimsSet;

@RestController
public class RegistrationLoginController {

	@Autowired
	private UserLoginRegistrationService service;
	
	@Autowired
	UserRepo repo;
	
	@Autowired
	TestRepo testrepo;
	
	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<ResponseModel> login(@RequestBody User user)
	{
		service = new UserLoginRegistrationService(user,repo,testrepo);
		boolean result=false;
		result = service.verifyPassword(user.getUsername(),user.getPass());
		
		ResponseModel response = new ResponseModel();
		response.setTimestamp();
		response.setUser(user);
		
		//JWTClaimsSet.Builder.expirationTime(response.timestamp);
		
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
		service = new UserLoginRegistrationService(user,repo,testrepo);
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
	@GetMapping(value = "tests/completed/{completed}")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<TestModel> findByCompleted(@PathVariable boolean completed, 
			@RequestHeader (name = "Authorization") String token) {
		
		//System.out.println(token);
		//System.out.println("this is the username from the header below");
		//System.out.println(headers);
		
		//Set<String> temp = headers.keySet();
		
		//temp.forEach(System.out::println);
		
		//System.out.println(token);
		
		//headers.get();
	 
	    List<TestModel> tests = service.listTests(completed, token);
	    		
	    		
	    //testrepo.findByTestCompleted(completed);
	    //for (int i = 0;i<tests.size(); i++)
	    //{
	    //    System.out.println(tests.get(i));
	    //}
	
	    return tests;
	}
	
	/*
	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	*/

}