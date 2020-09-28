package com.quizwebsite.core.userservice;

import org.apache.logging.log4j.*;

import com.quizwebsite.core.model.User;
import com.quizwebsite.core.userservice.repository.UserRepo;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//IMPORTANT. REPLACE E stack trace and system out print lin with log4j logging later

@Service
public class UserLoginRegistrationService {
	
	private User currentUser;
	
	//private UserDao serviceDAO = new UserDaoImplementation();
	@Autowired
	private UserRepo repo;
	
	//constructors instantiate user
	public UserLoginRegistrationService()
	{
		super();
		currentUser = new User();
	}
	
	public UserLoginRegistrationService(User current, UserRepo repo)
	{
		super();
		currentUser = current;
		this.repo =repo;
	}
	
	//add salt based on username
	private String AddSalt(String password, String username)
	{
		String saltedOutput = password.concat(username);
		return saltedOutput;
	}
	
	//convert salted passwords to sha256 hash
	private byte[] GetSHA(String input) throws NoSuchAlgorithmException 
	{   
	        MessageDigest md = MessageDigest.getInstance("SHA-256");  
	        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
	} 
	
	//convert sha256 hash to hexadecimal string 
	private String BytesToHex(byte[] hash) 
	{
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) 
		{
			String hex = Integer.toHexString(0xff & hash[i]);
		    if(hex.length() == 1) hexString.append('0');
		        hexString.append(hex);
		}
		    return hexString.toString();
	}
	
	//encrypt password using salt from unique id and sha256. output as hex string
	private String encryptPass(String password, String username)
	{
		String encryptedPass= null;
		try {
			encryptedPass =BytesToHex(GetSHA(AddSalt(password, username)));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return encryptedPass;
	}
	
	public boolean verifyPassword(String username, String password)
	{
		
		currentUser.setUsername(username);
		currentUser.setPass(encryptPass(password, username));
		
		//encrypt user input
		String encryptedPass = encryptPass(password,username);
		
		List<User> tempList = repo.findDistinctUserByUsername(currentUser.getUsername());
		User temp = null;
		
		if(!tempList.isEmpty())
			temp = tempList.get(0);
		
		String passDb = null;
		if(temp != null)
			passDb = temp.getPass();
		
		//change to make it a response object eventually not just a boolean
		
		//verify password input by user 
		if(encryptedPass.equals(passDb))
			return true;
		else
			return false;
		
	}
	
	//create user account with username, unique id, and password
	public boolean createUser(String username, String password)
	{
		currentUser.setUsername(username);
		currentUser.setPass(encryptPass(password, username));
		
		User temp = repo.save(currentUser);
		
		//change to make it a response object eventually not just a boolean
		
		if( temp != null)
			return true;
		else 
			return false;
		
		//return serviceDAO.addUser(currentUser);
	}

	
}
