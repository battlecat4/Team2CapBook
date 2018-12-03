package com.cg.project.controllers;

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

import com.cg.project.beans.User1;
import com.cg.project.exceptions.UserDetailsNotFoundException;
import com.cg.project.services.AccountServices;

@RestController
@CrossOrigin
public class AccountServicesController {
	@Autowired
	AccountServices accountServices;
	
	@RequestMapping(value="/openAccount",method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
	public ResponseEntity<String> acceptProductDetails(@RequestBody User1 user){
		accountServices.openAccount(user);
		return new ResponseEntity<>("Product details successfully added",HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/getUserDetails",produces=MediaType.APPLICATION_JSON_VALUE,
			headers="Accept=application/json")
	public ResponseEntity<User1> getProductDetails(@RequestParam("emailId") String emailId) throws UserDetailsNotFoundException{
		User1 user=accountServices.getAccountDetails(emailId);
		return new ResponseEntity<>(user,HttpStatus.OK);			
	}
	
}
