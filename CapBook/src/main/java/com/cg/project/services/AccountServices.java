package com.cg.project.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cg.project.beans.Photos;
import com.cg.project.beans.Relationship;
import com.cg.project.beans.Status;
import com.cg.project.beans.User1;
import com.cg.project.exceptions.IncorrectPasswordException;
import com.cg.project.exceptions.IncorrectSecurityAnswerException;
import com.cg.project.exceptions.PhotoNotFoundException;
import com.cg.project.exceptions.PhotoStorageException;
import com.cg.project.exceptions.RelationDetailsNotFoundException;
import com.cg.project.exceptions.RelationshipExistsException;
import com.cg.project.exceptions.UserDetailsNotFoundException;

public interface AccountServices {
	User1 openAccount(User1 user);
	User1 getAccountDetails(String emailId,String password) throws UserDetailsNotFoundException,IncorrectPasswordException;
	User1 getAccountDetailsUserId(String userId) throws UserDetailsNotFoundException,IncorrectPasswordException;
	Photos storePhoto(Photos photo) throws PhotoStorageException;
	boolean changePassword(String userId,String oldPassword,String newPassword) throws UserDetailsNotFoundException, IncorrectPasswordException;
	User1 addStatus(String userId,String status)throws UserDetailsNotFoundException;
	List<Status> getStatus();
	User1 addPost(String userId,String friendId,String post) throws UserDetailsNotFoundException;
//	public List<Message> getDialog(User1 user, User1 interlocutor);
//	public List<Message> getLastMessages(User1 user);
//	public Message send(Message message);
	Relationship updateFriendRequest(String userOneId,String userTwoId,int status,String id);
	boolean saveFriendRequest(String userOneId, String userTwoId);
	List<Relationship> getFriendList(String userOneId);
	User1 getAccount(String userId) throws UserDetailsNotFoundException;
	List<User1> getAllAccountDetails();
	List<Relationship> getFriendRequestList(String userTwoId);
	boolean forgotPassword(String emailId, String securityAnswer, String newPassword) throws UserDetailsNotFoundException, IncorrectSecurityAnswerException;
	Photos retrieveAllPhotos(String emailId);
}
