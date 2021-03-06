package com.cg.project.daoservices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.project.beans.Photos;
import com.cg.project.beans.Relationship;

public interface PhotosDAO extends JpaRepository<Photos, String> {
	@Query(value="SELECT * from PHOTOS p WHERE p.email_id=:emailId",nativeQuery = true)
	Photos findByEmailId(@Param("emailId") String emailId );
}
