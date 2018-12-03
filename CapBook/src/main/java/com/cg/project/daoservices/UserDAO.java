package com.cg.project.daoservices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.project.beans.User1;

public interface UserDAO extends JpaRepository<User1, String>{

}
