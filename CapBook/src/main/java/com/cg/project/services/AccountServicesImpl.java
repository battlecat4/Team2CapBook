package com.cg.project.services;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cg.project.beans.Photos;
import com.cg.project.beans.Posts;
import com.cg.project.beans.Relationship;
import com.cg.project.beans.Status;
import com.cg.project.beans.User1;
import com.cg.project.daoservices.PhotosDAO;
import com.cg.project.daoservices.RelationshipDAO;
import com.cg.project.daoservices.UserDAO;
import com.cg.project.exceptions.IncorrectPasswordException;
import com.cg.project.exceptions.PhotoNotFoundException;
import com.cg.project.exceptions.PhotoStorageException;
import com.cg.project.exceptions.RelationDetailsNotFoundException;
import com.cg.project.exceptions.UserDetailsNotFoundException;
@Component("AccountServices")
@Transactional
public class AccountServicesImpl implements AccountServices{
	
	@Autowired
	UserDAO userDAO;
	@Autowired
	private PhotosDAO photosDAO;
	@Autowired
	private RelationshipDAO relationshipDAO;
//	@Autowired
//	private MessageDAO messageDAO;
	public static final String SALT = "my-salt-text";

	@Override
	public User1 openAccount(User1 user) {
		String saltedPassword = SALT + user.getPassword();
		String hashedPassword = generateHash(saltedPassword);
		user.setPassword(hashedPassword);
		return userDAO.save(user);
	}



	@Override
	public User1 getAccountDetails(String emailId,String password) throws UserDetailsNotFoundException, IncorrectPasswordException {
		User1 user=userDAO.findByEmailId(emailId);
		if (user==null)
			throw new UserDetailsNotFoundException("User details not found");
		String saltedPassword = SALT + password;
		String hashedPassword = generateHash(saltedPassword);
		if (hashedPassword.equals(user.getPassword())) 
			return user;
		else 
			throw new IncorrectPasswordException("Incorrect Password");
					
	}

	public static String generateHash(String input) {
		StringBuilder hash = new StringBuilder();
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
		}
		return hash.toString();
	}

	@Override
	public Photos storePhoto(MultipartFile photo) throws PhotoStorageException {
		String photoName = StringUtils.cleanPath(photo.getOriginalFilename());
		try {
			if(photoName.contains("..")) {
				throw new PhotoStorageException("Sorry! Filename contains invalid path sequence " + photoName);
			}
			Photos photos = new Photos(photoName, photo.getContentType(), photo.getBytes());
			return photosDAO.save(photos);
		} catch (IOException ex) {
			throw new PhotoStorageException("Could not store file. Please try again!");
		}
	}

	@Override
	public Photos getPhoto(String photoId) throws PhotoNotFoundException {
		return photosDAO.findById(photoId).orElseThrow(() -> new PhotoNotFoundException("Photo not found with id " + photoId)) ;
	}

	public boolean changePassword(int userId,String oldPassword, String newPassword) throws UserDetailsNotFoundException, IncorrectPasswordException {
		User1 user= userDAO.findById(userId).orElseThrow(()->(new UserDetailsNotFoundException("User details not found")));
		String saltedPassword = SALT +oldPassword;
		String hashedPassword = generateHash(saltedPassword);
		if (hashedPassword.equals(user.getPassword())) {
			saltedPassword = SALT +newPassword;
			hashedPassword = generateHash(saltedPassword);
			user.setPassword(hashedPassword);
			userDAO.save(user);
			return true;
		}
		else
			throw new IncorrectPasswordException("Incorrect password");		
	} 
 
	/*@Override
	public User1 addStatus(String userId,String status) throws UserDetailsNotFoundException{
		User1 user= userDAO.findById(userId).orElseThrow(()->(new UserDetailsNotFoundException("No such emailId found")));
		//user.setStatus(new Status(status, user));
		userDAO.save(user);
		return user;
	}*/
 
	/*@Override
	public User1 addPost(String postById,String postOnId, String post) throws UserDetailsNotFoundException {
		User1 user1= userDAO.findById(postById).orElseThrow(()->(new UserDetailsNotFoundException("No such emailId found")));
		User1 user2=userDAO.findById(postOnId).orElseThrow(()->(new UserDetailsNotFoundException("No such emailId found")));
		//user2.setPosts(new Posts(postById, post, user2));
		return user1;
	}*/
 
	@Override
	public boolean saveFriendRequest(int userOneId, int userTwoId) {
		Relationship relation;
		try {
			getRelation(userOneId, userTwoId);
		} catch (RelationDetailsNotFoundException e) {
			relation=new Relationship(userOneId, userTwoId, 0, userOneId);
			relationshipDAO.save(relation);
			return true;
		}
		return false;
	}
 
	@Override
	public Relationship updateFriendRequest(int userOneId, int userTwoId, int status, int id) {
		return relationshipDAO.updateStatus(userOneId, userTwoId, status, id);
	}
 
	@Override
	public Relationship getRelation(int userOneId, int userTwoId) throws RelationDetailsNotFoundException {
		Relationship relation = relationshipDAO.findByIds(userOneId, userTwoId);
		if(relation!=null)
			return relation;
		else {
			relation = relationshipDAO.findByIds(userTwoId, userOneId);
			if(relation!=null)
				return relation;
			else
				throw new RelationDetailsNotFoundException("No such relationship exists");
		}
	}
 
	@Override
	public List<Relationship> getFriendList(int userOneId) {
		List<Relationship> relations = relationshipDAO.findAllById(userOneId);		
		return relations;
	}



	@Override
	public User1 getAccountDetailsUserId(int userId) throws UserDetailsNotFoundException, IncorrectPasswordException {
		return userDAO.findById(userId).get();
	}



	@Override
	public User1 addStatus(String userId, String status) throws UserDetailsNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public User1 addPost(String userId, String friendId, String post) throws UserDetailsNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}



	
 
//	@Override
//	public List<Message> getDialog(User1 user, User1 interlocutor) {
//		return messageDAO.findByRecipientOrSenderOrderByPostedDesc(user, interlocutor);
//	}
//
// 
//	@Override
//	public List<Message> getLastMessages(User1 user) {
//		return messageDAO.findLastMessagesByUser(user);
//	}
// 
// 
// 
//	@Override
//	public Message send(Message message) {
//		return messageDAO.save(message);
//	}
	
}