package com.cg.project.services;
 
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cg.project.beans.User1;
import com.cg.project.daoservices.UserDAO;
import com.cg.project.exceptions.IncorrectPasswordException;
import com.cg.project.exceptions.UserDetailsNotFoundException;
@Component("AccountServices")
@Transactional
public class AccountServicesImpl implements AccountServices{
	@Autowired
	UserDAO userDAO;
 
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
		User1 user=userDAO.findById(emailId).orElseThrow(()->new UserDetailsNotFoundException("User details not found"));
		String saltedPassword = SALT + password;
		System.out.println(user.toString());
		String hashedPassword = generateHash(saltedPassword);
		if (hashedPassword.equals(user.getPassword())) {
			System.out.println(user.toString()+"1");
			return user;
		}			
		else {
			System.out.println(user.toString()+" 2");
			throw new IncorrectPasswordException("Incorrect Password");
		}
			
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
}