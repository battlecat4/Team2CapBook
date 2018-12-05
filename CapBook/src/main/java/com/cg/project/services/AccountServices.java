package com.cg.project.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cg.project.beans.Message;
import com.cg.project.beans.Photos;
import com.cg.project.beans.Relationship;
import com.cg.project.beans.User1;
import com.cg.project.exceptions.IncorrectPasswordException;
import com.cg.project.exceptions.PhotoNotFoundException;
import com.cg.project.exceptions.PhotoStorageException;
import com.cg.project.exceptions.UserDetailsNotFoundException;

public interface AccountServices {
	User1 openAccount(User1 user);
	User1 getAccountDetails(String emailId,String password) throws UserDetailsNotFoundException,IncorrectPasswordException;
	Photos storePhoto(MultipartFile photo) throws PhotoStorageException;
	Photos getPhoto(String photoId) throws PhotoNotFoundException;
	boolean changePassword(String emailId,String oldPassword, String newPassword) throws UserDetailsNotFoundException, IncorrectPasswordException;
	User1 addStatus(String userId,String status)throws UserDetailsNotFoundException;
	User1 addPost(String userId,String friendId,String post) throws UserDetailsNotFoundException;
	public List<Message> getDialog(User1 user, User1 interlocutor);
	public List<Message> getLastMessages(User1 user);
	public Message send(Message message);
	Relationship sendFriendRequest(int userOneId,int userTwoId); 
	Relationship updateFriendRequest(int userOneId,int userTwoId,int status,int id);
}
