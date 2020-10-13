package com.quizwebsite.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Date;
import java.util.TimeZone;

//import org.springframework.boot.autoconfigure.security.oauth2.;
//import org.springframework.security*;
//import org.springframework.boot.autoconfigure.security.oauth2.

@SpringBootApplication
//authorization server is deprecated. figure out another way to do security/tokens
//@EnableAuthorizationServer
//@EnableResourceServer
public class QuizWebsiteWithH2Application {

	public static void main(String[] args) {
		
		//set timezone of backend to est. used to later timestamp user registration or login
		TimeZone.setDefault(TimeZone.getTimeZone("EST"));
		SpringApplication.run(QuizWebsiteWithH2Application.class, args);
	}

}
