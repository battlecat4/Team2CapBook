package com.cg.project.services;

import com.cg.project.beans.User1;
import com.cg.project.exceptions.IncorrectPasswordException;
import com.cg.project.exceptions.UserDetailsNotFoundException;

public interface AccountServices {
	User1 openAccount(User1 user);
	User1 getAccountDetails(String emailId,String password) throws UserDetailsNotFoundException,IncorrectPasswordException;
}
