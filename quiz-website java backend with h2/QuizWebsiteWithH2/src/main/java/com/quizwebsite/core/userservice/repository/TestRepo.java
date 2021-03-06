package com.quizwebsite.core.userservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.quizwebsite.core.model.TestModel;
import com.quizwebsite.core.model.User;

import java.util.List; 

public interface TestRepo extends CrudRepository<TestModel, Long> {
	  
	//get user by username
	//List<User> findDistinctUserByUsername(String username);
	

	//for listing all tests. bugged out but I don't even use this so commenting out
	//List<TestModel> findAll(Iterable<Integer> ids);
	
	//for listing all tests based on completed boolean and user the test is associated with
	List<TestModel> findByTestCompletedAndUser(boolean completed, User user);
	
	//find by name of the test
	List<TestModel> findByTestTitle(String title);
	
	//find by name of test and user
	List<TestModel> findByTestTitleAndUser(String title, User user);
	
	//create user given username and password
	//List<User> save(String username, String pass);
	@SuppressWarnings("unchecked")
	TestModel save (TestModel test);
	
	//public boolean addUser(User user);
	//public String getPassword(User user);
	   
}
