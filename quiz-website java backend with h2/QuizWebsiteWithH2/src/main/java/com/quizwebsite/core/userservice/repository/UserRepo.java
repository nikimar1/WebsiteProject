package com.quizwebsite.core.userservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.quizwebsite.core.model.User;

import java.util.List; 

public interface UserRepo extends CrudRepository<User, Long> {
	  
	//get user by username
	List<User> findDistinctUserByUsername(String username);
	
	//is it ok that it is unchecked?
	@SuppressWarnings("unchecked")
	//create user given username and password
	//List<User> save(String username, String pass);
	User save (User user);
	
	//public boolean addUser(User user);
	//public String getPassword(User user);
	   
}
