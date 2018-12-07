package com.cg.project.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cg.project.beans.Photos;
import com.cg.project.beans.Relationship;
import com.cg.project.beans.User1;
import com.cg.project.exceptions.IncorrectPasswordException;
import com.cg.project.exceptions.PhotoNotFoundException;
import com.cg.project.exceptions.PhotoStorageException;
import com.cg.project.exceptions.RelationDetailsNotFoundException;
import com.cg.project.exceptions.UserDetailsNotFoundException;

public interface AccountServices {
	User1 openAccount(User1 user);
	User1 getAccountDetails(String emailId,String password) throws UserDetailsNotFoundException,IncorrectPasswordException;
	User1 getAccountDetailsUserId(int userId) throws UserDetailsNotFoundException,IncorrectPasswordException;
	Photos storePhoto(MultipartFile photo) throws PhotoStorageException;
	Photos getPhoto(String photoId) throws PhotoNotFoundException;
	boolean changePassword(int userId,String oldPassword,String newPassword) throws UserDetailsNotFoundException, IncorrectPasswordException;
	User1 addStatus(String userId,String status)throws UserDetailsNotFoundException;
	User1 addPost(String userId,String friendId,String post) throws UserDetailsNotFoundException;
//	public List<Message> getDialog(User1 user, User1 interlocutor);
//	public List<Message> getLastMessages(User1 user);
//	public Message send(Message message);
	Relationship updateFriendRequest(int userOneId,int userTwoId,int status,int id);
	boolean saveFriendRequest(int userOneId, int userTwoId);
	Relationship getRelation(int userOneId, int userTwoId) throws RelationDetailsNotFoundException;
	List<Relationship> getFriendList(int userOneId);
}
