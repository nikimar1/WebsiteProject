package com.quizwebsite.core.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.quizwebsite.core.model.QuestionModel;
import com.quizwebsite.core.model.ResponseModel;
import com.quizwebsite.core.model.TestModel;
import com.quizwebsite.core.model.User;
import com.quizwebsite.core.userservice.UserService;
import com.quizwebsite.core.userservice.repository.QuestionRepo;
import com.quizwebsite.core.userservice.repository.TestRepo;
import com.quizwebsite.core.userservice.repository.UserRepo;

//import com.nimbusds.jwt.JWTClaimsSet;

@RestController
public class QuizWebsiteController {

	@Autowired
	private UserService service;
	
	@Autowired
	UserRepo repo;
	
	@Autowired
	TestRepo testrepo;
	
	@Autowired
	QuestionRepo questionRepo;
	
	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<ResponseModel> login(@RequestBody User user)
	{
		service = new UserService(user,repo,testrepo,questionRepo);
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
		service = new UserService(user,repo,testrepo,questionRepo);
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
	
	@PostMapping( value = "/questions"  /*, produces = "application/json"*/)
	@CrossOrigin(origins = "http://localhost:4200")
	public List<QuestionModel> getQuestion(@RequestBody String testName, 
			@RequestHeader (name = "Authorization") String token)
	{
		//System.out.println(data);
		//System.out.println(data.getTitle());
		
		//System.out.println(testName);
		
		//String testName = input;
		//int questionNumber = 0;
		
		
		/*
		JsonParser springParser = JsonParserFactory.getJsonParser();
		Map<String, Object> map = springParser.parseMap(input);

		//String mapArray[] = new String[map.size()];
		//System.out.println("Items found: " + mapArray.length);

		int i = 0;
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getKey().equals("testTitle"))
				testName = (String) entry.getValue();
			else if (entry.getKey().equals("questionNum"))
			{
				questionNumber = (int) entry.getValue();
			}
			//System.out.println(entry.getKey() + " = " + entry.getValue());
			i++;
		}
		*/
		
		//make getters and setters for all question model members
		//testName= input.getTestTitle();
		//questionNumber = input.getQuestionNumber();
		
		List <QuestionModel> questions = service.getQuestions(testName, token);
		
		
		//print list to confirm that it is correct
		//for (int j = 0; j < questions.size(); j++) {
			
		//	QuestionModel temp = questions.get(j);
		//   System.out.println(temp.getQuestionBody());
		//}
		
		
		return questions;
		
		//System.out.println("it was passed to controller as this value:");
		//System.out.println(question.qetQuestionBody());
		
		//QuestionResponseModel response = new QuestionResponseModel();
		//response.setTimestamp();
		//response.setQuestion(question);
		//response.setSuccess(true);
		
		//com.fasterxml.jackson.databind.ObjectMapper myMapper = new com.fasterxml.jackson.databind.ObjectMapper();
		//myMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		//try {
		//	return myMapper.writeValueAsBytes(ResponseEntity.ok(response));
		//} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		
		//return null;
		
		
		//return ;
		
		//delete later
		//return new QuestionModel();
	}
	
	
	@PostMapping(value = "/answer")
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean sendAnswer(@RequestBody Map<String, Object> data,
			@RequestHeader (name = "Authorization") String token)
	{
		String answer="";
		String testTitle="";
		int questionNumber = 1;
		//int i = 0;
		for (Map.Entry<String, Object> entry : data.entrySet()) {
			//System.out.println(entry.getKey() + " = " + entry.getValue());
		//	i++;
			
			if (entry.getKey().equals("testTitle"))
				testTitle = (String) entry.getValue();
			else if (entry.getKey().equals("answer"))
			{
				answer = (String) entry.getValue();
			}
			else if (entry.getKey().equals("qnNumber"))
			{
				questionNumber= (int) entry.getValue();
			}
		}
		
		//System.out.println(answer + testTitle);
		
		return service.submitAnswer(answer,testTitle,questionNumber, token);
		
	}
	
	@PostMapping(value = "/submission")
	@CrossOrigin(origins = "http://localhost:4200")
	public String submission(@RequestBody String testTitle,
			@RequestHeader (name = "Authorization") String token)
	{
	
	
		//System.out.println("got to submission in controller");
		String result = service.completeTest(testTitle, token);
		System.out.println("result I got returned to controller is: "+ result );
		return result;
	}
	/*
	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	*/

}