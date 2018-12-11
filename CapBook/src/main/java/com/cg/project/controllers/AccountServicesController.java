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

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.cg.project.beans.Photos;
import com.cg.project.beans.Relationship;
import com.cg.project.beans.Status;
import com.cg.project.beans.User1;
import com.cg.project.exceptions.IncorrectPasswordException;
import com.cg.project.exceptions.IncorrectSecurityAnswerException;
import com.cg.project.exceptions.PhotoStorageException;
import com.cg.project.exceptions.RelationshipExistsException;
import com.cg.project.exceptions.UserDetailsNotFoundException;
import com.cg.project.services.AccountServices;

@RestController
@CrossOrigin
public class AccountServicesController {
	@Autowired
	AccountServices accountServices;

	@RequestMapping(value="/openAccount",method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
	public ResponseEntity<User1> acceptUserDetails(@RequestBody User1 user){
		user=accountServices.openAccount(user);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.GET,value="/getUserDetails",produces=MediaType.APPLICATION_JSON_VALUE,
			headers="Accept=application/json")
	public ResponseEntity<User1> getUserDetails(@RequestParam("emailId") String emailId,
			@RequestParam("password") String password){
		User1 user = null;
		//HashMap<String, Object> model=new HashMap<String,Object>();
		try {
			user = accountServices.getAccountDetails(emailId,password);
			//String sessionId=RequestContextHolder.getRequestAttributes().getSessionId();
			/*model.put("user", user);
			model.put("userId", user.getUserID());
			model.put("sessionId", sessionId);*/
		} catch (IncorrectPasswordException|UserDetailsNotFoundException e) {
			return new ResponseEntity<>(user,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(user,HttpStatus.OK);			
	}
	
	
	@RequestMapping(method=RequestMethod.GET,value="/getDetails",produces=MediaType.APPLICATION_JSON_VALUE)
			//headers="Accept=application/json")
	public ResponseEntity<User1> getDetails(@RequestParam("userId") String userId){
		User1 user=null;
		try {
			user = accountServices.getAccountDetailsUserId(userId);
		} catch (UserDetailsNotFoundException | IncorrectPasswordException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(user, HttpStatus.OK);	
		
	}
	
	
	
	
	@PostMapping("/uploadphoto")
    public String uploadMultipartFile(@RequestParam("file") MultipartFile photo,@RequestParam("emailId") String emailId) {
    	try {
    		Photos photos = new Photos(photo.getOriginalFilename(), photo.getContentType(), photo.getBytes(),emailId);
    		accountServices.storePhoto(photos);
//       		 byte[] bytes = photo.getBytes();
//             Path path = Paths.get(UPLOADED_FOLDER + photo.getOriginalFilename());
//             Files.write(path, bytes);    		
	    	return "File uploaded successfully! -> filename = " + photo.getOriginalFilename();
		} 
    		catch (Exception e) {
			return "FAIL! Maybe You had uploaded the file before or the file's size > 500KB";
		}   
    }
	
	@RequestMapping(value="/retrievephoto",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Photos> getPhoto(@RequestParam("emailId") String emailId){	
			Photos pic=accountServices.retrieveAllPhotos(emailId);			
			if (pic==null)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
			return new ResponseEntity<> (pic, HttpStatus.OK);
	}

	@RequestMapping(value="/changePassword",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> changeOldPassword(@RequestParam("userId") String userId,@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword ){
		try {
			accountServices.changePassword(userId, oldPassword,newPassword);
		} catch (IncorrectPasswordException| UserDetailsNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/sendRequest",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> sendRequestAction(@RequestParam("userOneId") String userOneId, @RequestParam("userTwoId") String userTwoId){
		System.out.println("-- "+userOneId);
		System.out.println("== "+userTwoId);
		boolean status;
			status=accountServices.saveFriendRequest(userOneId, userTwoId);
		if (status==true)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);				
	}
 
	@RequestMapping(value="/updateRequest",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> updateRequestAction(@RequestParam("userOneId") String userOneId, @RequestParam("userTwoId") String userTwoId,@RequestParam("status") String status){
		
		int s=Integer.parseInt(status);
		accountServices.updateFriendRequest(userOneId, userTwoId, s, userTwoId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
 
	@RequestMapping(value="/getFriendList",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User1>> getFriendListAction(@RequestParam("userId") String userId){
		List<Relationship> friendList=accountServices.getFriendList(userId);
		List<User1> friends=new ArrayList<>();
		User1 user=null;
		try {
			for (Relationship relationship : friendList) {
				if(relationship.getUserOneId().equals(userId)) {									
					user=accountServices.getAccount(relationship.getUserTwoId());
					friends.add(user);									
				}
				else {
					user=accountServices.getAccount(relationship.getUserOneId());
					friends.add(user);
				}					
			}					
		} catch (UserDetailsNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(friends,HttpStatus.OK);
	}
	
	@RequestMapping(value="/status",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> postStatusAction(@RequestParam("userId") String userId,@RequestParam("status") String status){
		//int id=Integer.parseInt(userId);
			try {
				accountServices.addStatus(userId, status);
			} catch (UserDetailsNotFoundException e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/getStatus",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Status>> getAllStatusAction(@RequestParam("userId") String userId){
		//int id=Integer.parseInt(userId);
		List<Status> list=accountServices.getStatus();
		List<Status> statusList= new ArrayList<>();
		for (Status str : list) {
			if(str.getUser().getEmailId().equals(userId))
				statusList.add(str);
		}
		if (statusList==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(statusList,HttpStatus.OK);		
	}	
	
	@RequestMapping(value="/getRequestList",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User1>> getRequestListAction(@RequestParam("userTwoId") String userTwoId){
		List<Relationship> list=accountServices.getFriendRequestList(userTwoId);
		List<User1>requestList=new ArrayList<>();
		for (Relationship relationship : list) {
			if(relationship.getStatus()==0)
				try {
					requestList.add(accountServices.getAccount(relationship.getUserOneId()));
				} catch (UserDetailsNotFoundException e) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
		}
		return new ResponseEntity<>(requestList,HttpStatus.OK);
	}
	
	
@RequestMapping(method=RequestMethod.GET,value= "/getAllUserDetails",	produces=MediaType.APPLICATION_JSON_VALUE,
headers="Accept=application/json")
	public ResponseEntity<List<User1>> getAllUserDetailsAction(@RequestParam("userId") String userId){
		List<User1> users= accountServices.getAllAccountDetails();
		List<User1> userList = new ArrayList<>();
		for (User1 str : users) {
			if(!str.getEmailId().equals(userId)) 
				userList.add(str);
		}
		return new ResponseEntity<>(userList,HttpStatus.OK);
	}



@RequestMapping(value="/forgotPassword",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<HttpStatus> forgotPassword(@RequestParam("emailId") String emailId,@RequestParam("securityAnswer") String securityAnswer,@RequestParam("newPassword") String newPassword){
try {
	boolean status=accountServices.forgotPassword(emailId, securityAnswer, newPassword);
} catch (UserDetailsNotFoundException | IncorrectSecurityAnswerException e) {
	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
}
return new ResponseEntity<>(HttpStatus.OK);
}




}