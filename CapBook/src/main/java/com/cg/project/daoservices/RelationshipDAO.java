package com.cg.project.daoservices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.project.beans.Relationship;

public interface RelationshipDAO extends JpaRepository<Relationship, String>{
	
	@Query(value="SELECT * from RELATIONSHIP r WHERE r.user_one_id=:userOneId AND r.user_two_id=:userTwoId",nativeQuery = true)
	Relationship findByIds(@Param("userOneId") String userOneId,@Param("userTwoId") String userTwoId );
	
	@Query(value="UPDATE Relationship r SET r.status=:status AND r.user_action_id=:id WHERE r.user_one_id=:userOneId AND r.user_two_id=:userTwoId",nativeQuery = true)
	Relationship updateStatus(@Param("userOneId") String userOneId,@Param("userTwoId") String userTwoId,@Param("status") int status,@Param("id") String id);
	
	@Query(value="SELECT * from Relationship r WHERE r.user_one_id=:userOneId",nativeQuery = true)
	List<Relationship> findAllByEmailId(@Param("userOneId") String userOneId);
	
}