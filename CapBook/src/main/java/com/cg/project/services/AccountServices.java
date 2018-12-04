package com.cg.project.services;

import org.springframework.web.multipart.MultipartFile;

import com.cg.project.beans.Photos;
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
	boolean changePassword(String oldPassword, String newPassword);
}
