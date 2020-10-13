package com.quizwebsite.core.userservice;

import org.apache.logging.log4j.*;

import com.quizwebsite.core.model.TestModel;
import com.quizwebsite.core.model.User;
import com.quizwebsite.core.userservice.repository.TestRepo;
import com.quizwebsite.core.userservice.repository.UserRepo;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//IMPORTANT. REPLACE E stack trace and system out print lin with log4j logging later

@Service
public class UserLoginRegistrationService {
	
	private User currentUser;
	
	private TestModel testTemp;
	
	//private UserDao serviceDAO = new UserDaoImplementation();
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private TestRepo testRepo;
	
	//constructors instantiate user
	public UserLoginRegistrationService()
	{
		super();
		currentUser = new User();
	}
	
	public UserLoginRegistrationService(User current, UserRepo repo, TestRepo testRepo)
	{
		super();
		currentUser = current;
		this.repo =repo;
		this.testRepo=testRepo;
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
		
		//this creates a generic test model for testing purposes. 
		//usually I would create many testmodels via a different admin console
		TestModel testOne = new TestModel("TestOne", temp);
		
		TestModel testTwo = new TestModel("TestTwo", temp);
		
		TestModel testThree = new TestModel("TestThree", temp);
		
		TestModel testFour = new TestModel("TestFour", temp);
		
		testOne.setCompleted(true);
		testTwo.setCompleted(false);
		
		testRepo.save(testOne);
		
		testRepo.save(testTwo);
		
		testRepo.save(testThree);
		
		testRepo.save(testFour);
		
		
		
		
		//change to make it a response object eventually not just a boolean
		
		if( temp != null)
			return true;
		else 
			return false;
		
		//return serviceDAO.addUser(currentUser);
	}

	public List<TestModel> listTests(boolean completed, String username) {
		
		
		//System.out.println(username);
		//get user by username passed from the token
		List<User> userFromToken = repo.findDistinctUserByUsername(username);
		
		//get all tests with boolean completed and user user
		List <TestModel> tests = testRepo.findByTestCompletedAndUser(completed, userFromToken.get(0));
		
		//parse to remove tests not belonging to the token user
		//List <TestModel> yourTests =new ArrayList<TestModel>();
		
		//could potentially have a performance issue 
		//so is there a more efficent way to do this? I am unable to pass in a user object with the token. 
		//I could match token string username and findbyusername to get a user then use that as the parameter for findbycompletedanduser
		//
		//for (int i = 0;i<tests.size(); i++)
		//{
		//	
		//	System.out.println(tests.get(i).getUser().getUsername());
		//	System.out.println("while the username from token is "+ username);
		//	if(tests.get(i).getUser().getUsername().equals(username))
		//	{
		//		yourTests.add(tests.get(i));
		//	}
		//}
		
		return tests;
		
		//return tests;
		
	}

	
}
