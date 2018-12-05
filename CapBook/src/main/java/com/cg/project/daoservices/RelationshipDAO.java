package com.cg.project.daoservices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.project.beans.Relationship;

public interface RelationshipDAO extends JpaRepository<Relationship, Integer>{
	
	@Query(value="SELECT r from Relationship WHERE r.userOneId=:userOneId AND r.userTwoId=:userTwoId",nativeQuery = true)
	Relationship findByIds(@Param("userOneId") int userOneId,@Param("userTwoId") int userTwoId );
	
	@Query(value="UPDATE Relationship r SET r.status=:status AND r.userActionId=:id WHERE r.userOneId=:userOneId AND r.userTwoId=:userTwoId",nativeQuery = true)
	Relationship updateStatus(@Param("userOneId") int userOneId,@Param("userTwoId") int userTwoId,@Param("status") int status,@Param("id") int id);
	
}
