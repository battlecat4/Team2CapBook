package com.cg.project.services;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.status.StatusData;
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
import com.cg.project.daoservices.StatusDAO;
import com.cg.project.daoservices.UserDAO;
import com.cg.project.exceptions.IncorrectPasswordException;
import com.cg.project.exceptions.IncorrectSecurityAnswerException;
import com.cg.project.exceptions.PhotoNotFoundException;
import com.cg.project.exceptions.PhotoStorageException;
import com.cg.project.exceptions.RelationDetailsNotFoundException;
import com.cg.project.exceptions.RelationshipExistsException;
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
	@Autowired
	private StatusDAO statusDAO;
	
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

	@Override
	public List<User1> getAllAccountDetails() {
		return (ArrayList<User1>)userDAO.findAll();
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
	public Photos storePhoto(Photos photo) throws PhotoStorageException {
		Photos photo1=photosDAO.findByEmailId(photo.getEmailId());
		if(photo1!=null) {
			photosDAO.deleteById(photo1.getPhotoId());
		}
		String photoName = StringUtils.cleanPath(photo.getPhotoName());
		try {
			if(photoName.contains("..")) {
				throw new PhotoStorageException("Filename contains invalid path sequence ");
			}			
			photo=photosDAO.save(photo);
			return photo;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new PhotoStorageException("Could not store file.");
		}
	}

	@Override
	public Photos retrieveAllPhotos(String emailId){
		return photosDAO.findByEmailId(emailId);
	}

	public boolean changePassword(String userId,String oldPassword, String newPassword) throws UserDetailsNotFoundException, IncorrectPasswordException {
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
 
	@Override
	public User1 addStatus(String userId,String status) throws UserDetailsNotFoundException{
		User1 user= userDAO.findById(userId).orElseThrow(()->(new UserDetailsNotFoundException("No such emailId found")));
		Status s=new Status(status, user);
		statusDAO.save(s);
		return user;
	}
	
	@Override
	public User1 getAccount(String userId) throws UserDetailsNotFoundException {
		User1 user=userDAO.findById(userId).orElseThrow(()->new UserDetailsNotFoundException("User details not found"));
		return user;
	}
 
	/*@Override
	public User1 addPost(String postById,String postOnId, String post) throws UserDetailsNotFoundException {
		User1 user1= userDAO.findById(postById).orElseThrow(()->(new UserDetailsNotFoundException("No such emailId found")));
		User1 user2=userDAO.findById(postOnId).orElseThrow(()->(new UserDetailsNotFoundException("No such emailId found")));
		//user2.setPosts(new Posts(postById, post, user2));
		return user1;
	}*/
 
	@Override
	public boolean saveFriendRequest(String userOneId, String userTwoId){
		Relationship relation=relationshipDAO.findByIds(userOneId, userTwoId);
		String temp=userOneId;
		userOneId=userTwoId;
		userTwoId=temp;
		Relationship relation1=relationshipDAO.findByIds(userOneId,userTwoId);
		if (relation==null) {
			if (relation1==null) {
				Relationship relation2=new Relationship(userTwoId,userOneId, 0, userTwoId);
				Relationship relation3=relationshipDAO.save(relation2);
				return true;	
			}								
		}
		return false;		
	}
		
	
 
	@Override
	public Relationship updateFriendRequest(String userOneId, String userTwoId, int status, String id) {			
		Relationship relation=new Relationship(userOneId, userTwoId, status, id);
		Relationship relation1=relationshipDAO.save(relation);
		if(status==2) {
			relationshipDAO.delete(relation1);
			return null;
		}			
		return relation1;
	}
 
	
 
	@Override
	public List<Relationship> getFriendList(String userId) {
		List<Relationship> relations = relationshipDAO.findFriends(userId,1);
		return relations;
	}
	
	@Override
	public List<Relationship> getFriendRequestList(String userTwoId) {
		List<Relationship> relations = relationshipDAO.findAllByEmailId(userTwoId);
		return relations;
	}



	@Override
	public User1 getAccountDetailsUserId(String userId) throws UserDetailsNotFoundException, IncorrectPasswordException {
		return userDAO.findById(userId).get();
	}

	@Override
	public User1 addPost(String userId, String friendId, String post) throws UserDetailsNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Status> getStatus() {
		List<Status> statusList=new ArrayList<>();
		statusList=statusDAO.findAll();
		return statusList;
	}
	
	@Override
	public boolean forgotPassword(String emailId, String securityAnswer, String newPassword) throws UserDetailsNotFoundException, IncorrectSecurityAnswerException {
				User1 user= userDAO.findByEmailId(emailId); 
				if (user==null)
					throw new UserDetailsNotFoundException("User details not found");
				if(securityAnswer.equals(user.getSecurityAnswer())) {
					String saltedPassword = SALT + newPassword;
					String hashedPassword = generateHash(saltedPassword);
					user.setPassword(hashedPassword);
					userDAO.save(user);
				}
				else 
					throw new IncorrectSecurityAnswerException("Please enter the correct answer");
				return true;
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