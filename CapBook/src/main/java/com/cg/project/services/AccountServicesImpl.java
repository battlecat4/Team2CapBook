package com.cg.project.services;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cg.project.beans.Photos;
import com.cg.project.beans.User1;
import com.cg.project.daoservices.PhotosDAO;
import com.cg.project.daoservices.UserDAO;
import com.cg.project.exceptions.IncorrectPasswordException;
import com.cg.project.exceptions.PhotoNotFoundException;
import com.cg.project.exceptions.PhotoStorageException;
import com.cg.project.exceptions.UserDetailsNotFoundException;
@Component("AccountServices")
@Transactional
public class AccountServicesImpl implements AccountServices{
	@Autowired
	UserDAO userDAO;
	@Autowired
	private PhotosDAO photosDAO;
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



	@Override
	public boolean changePassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		return false;
	}
}