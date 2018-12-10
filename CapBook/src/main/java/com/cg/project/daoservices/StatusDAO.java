package com.cg.project.daoservices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.project.beans.Status;

public interface StatusDAO extends JpaRepository<Status, Integer>{
	/*@Query(value="SELECT * from Status s WHERE s.user_id=:userId",nativeQuery = true)
	List<Status> findByUserId(@Param("userId") int userId);*/
}
