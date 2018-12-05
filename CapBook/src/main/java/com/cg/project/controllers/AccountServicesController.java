package com.cg.project.controllers;
 
import org.hibernate.mapping.Map;
import org.openqa.selenium.remote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.*;
 
import com.cg.project.beans.User1;
import com.cg.project.exceptions.IncorrectPasswordException;
import com.cg.project.exceptions.UserDetailsNotFoundException;
import com.cg.project.services.AccountServices;
 
@RestController
@CrossOrigin
public class AccountServicesController {
	@Autowired
	AccountServices accountServices;
 
	@RequestMapping(value="/openAccount",method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
	public ResponseEntity<User1> acceptProductDetails(@RequestBody User1 user){
		System.out.println(user.toString());
		user=accountServices.openAccount(user);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
 
	@RequestMapping(method=RequestMethod.GET,value="/getUserDetails",produces=MediaType.APPLICATION_JSON_VALUE,
			headers="Accept=application/json")
	public ResponseEntity<HashMap<String, Object>> getProductDetails(@RequestParam("emailId") String emailId,
			@RequestParam("password") String password){
		User1 user = null;
		HashMap<String, Object> model=new HashMap<String,Object>();
		try {
			user = accountServices.getAccountDetails(emailId,password);
			String sessionId=RequestContextHolder.getRequestAttributes().getSessionId();
			model.put("user", user);			
			model.put("sessionId", sessionId);
		} catch (IncorrectPasswordException|UserDetailsNotFoundException e) {
			System.out.println("error");
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(model, HttpStatus.OK);
}
}