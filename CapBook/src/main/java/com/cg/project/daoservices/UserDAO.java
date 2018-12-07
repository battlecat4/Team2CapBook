package com.cg.project.daoservices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.cg.project.beans.User1;

public interface UserDAO extends JpaRepository<User1, Integer>{
	@Query(value="SELECT * from User1 u WHERE u.email_id=:emailId",nativeQuery = true)
	User1 findByEmailId(@Param("emailId") String emailId);
	
	
}
