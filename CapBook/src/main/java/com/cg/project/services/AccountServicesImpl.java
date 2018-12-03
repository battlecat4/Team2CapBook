package com.cg.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.project.beans.User1;
import com.cg.project.daoservices.UserDAO;
import com.cg.project.exceptions.UserDetailsNotFoundException;
@Component("AccountServices")
public class AccountServicesImpl implements AccountServices{
	@Autowired
	UserDAO userDAO;
	
	@Override
	public User1 openAccount(User1 user) {
		return userDAO.save(user);
	}

	@Override
	public User1 getAccountDetails(String emailId) throws UserDetailsNotFoundException {
		return userDAO.findById(emailId).orElseThrow(()->new UserDetailsNotFoundException("User details not found"));
	}

}
