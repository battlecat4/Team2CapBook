package com.cg.project.controllers;


import org.hibernate.mapping.Map;
import org.openqa.selenium.remote.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.context.request.RequestContextHolder;

import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import com.cg.project.beans.Photos;
import com.cg.project.beans.Relationship;
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
			model.put("userId", user.getUserID());
			model.put("sessionId", sessionId);
		} catch (IncorrectPasswordException|UserDetailsNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(model, HttpStatus.OK);			
	}
	
	
	@RequestMapping(method=RequestMethod.GET,value="/getDetails",produces=MediaType.APPLICATION_JSON_VALUE)
			//headers="Accept=application/json")
	public ResponseEntity<User1> getDetails(@RequestParam("userId") int userId){
		System.out.println(userId);
		User1 user=null;
		try {
			user = accountServices.getAccountDetailsUserId(userId);
		} catch (UserDetailsNotFoundException | IncorrectPasswordException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(user, HttpStatus.OK);	
		
	}
	
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(AccountServicesController.class);
	@PostMapping("/uploadFile")
	public String uploadMultipartFile(@RequestParam("photo") MultipartFile photo) {
		try {
			Photos photos = new Photos(photo.getOriginalFilename(), photo.getContentType(), photo.getBytes());
			accountServices.storePhoto(photo);
			return "File uploaded successfully! -> filename = " + photo.getOriginalFilename();
		} catch (	Exception e) {
			return "FAIL! Maybe You had uploaded the file before or the file's size > 500KB";
		}   
	}

	@RequestMapping(value="/changePassword",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> changeOldPassword(@RequestParam("userId") String userId,@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword ){
		try {
			System.out.println("heyyyyy"+userId);
			int id=Integer.parseInt(userId);
			accountServices.changePassword(id, oldPassword,newPassword);
		} catch (IncorrectPasswordException| UserDetailsNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/sendRequest",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
	public ResponseEntity<HttpStatus> sendRequestAction(@RequestParam("userOneId") int userOneId, @RequestParam("userTwoId") int userTwoId){
		boolean status=accountServices.saveFriendRequest(userOneId, userTwoId);
		if(status==true)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
 
	@RequestMapping(value="/updateRequest",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
	public ResponseEntity<HttpStatus> updateRequestAction(@RequestParam("userOneId") int userOneId, @RequestParam("userTwoId") int userTwoId,
			@RequestParam("status")int status){
		accountServices.updateFriendRequest(userOneId, userTwoId, status, userOneId);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
 
	@RequestMapping(value="/getFriendList",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
	public ResponseEntity<List<Relationship>> getFriendListAction(@RequestParam("userOneId") int userOneId){
		List<Relationship> friendList=accountServices.getFriendList(userOneId);
		return new ResponseEntity<>(friendList,HttpStatus.OK);
	}
}