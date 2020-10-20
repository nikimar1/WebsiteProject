package com.quizwebsite.core.userservice.repository;


import org.springframework.data.repository.CrudRepository;

import com.quizwebsite.core.model.QuestionModel;
import com.quizwebsite.core.model.TestModel;
import java.util.List; 

public interface QuestionRepo extends CrudRepository<QuestionModel, Long> {
	  
	//get user by username
	//List<User> findDistinctUserByUsername(String username);
	

	//for listing all tests. bugged out but I don't even use this so commenting out
	//List<TestModel> findAll(Iterable<Integer> ids);
	
	//for listing all tests based on test model
	List<QuestionModel> findByTestModel(TestModel test);
	
	//for listing all tests based on testmodel and question number
	List<QuestionModel> findByTestModelAndQuestionNumber(TestModel test, int questionNumber);
	
	//create user given username and password
	//List<User> save(String username, String pass);
	@SuppressWarnings("unchecked")
	QuestionModel save (QuestionModel question);
	
	//public boolean addUser(User user);
	//public String getPassword(User user);
	   
}
