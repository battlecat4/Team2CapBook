//package com.cg.project.daoservices;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//
//import com.cg.project.beans.Message;
//import com.cg.project.beans.User1;
//
//public interface MessageDAO extends CrudRepository<Message, Long>{
//
//	@Query("SELECT m " +"FROM Message m " +"WHERE m.sender = :sender AND m.recipient = :recipient " +
//			"   OR m.sender = :recipient AND m.recipient = :sender " +"ORDER BY m.posted DESC")
//	List<Message> findByRecipientOrSenderOrderByPostedDesc(@Param("sender") User1 sender,@Param("recipient") User1 recipient);
//
//	@Query("SELECT m " +"FROM Message m " +"WHERE m.id IN (" +"   SELECT MAX(l.id) " +"   FROM Message l " +
//			"   WHERE l.sender = :user OR l.recipient = :user " +"   GROUP BY " +"       CASE " +"           WHEN l.recipient = :user THEN l.sender " +"           WHEN l.sender = :user THEN l.recipient " +
//			"           ELSE :user " +"       END) " +"ORDER BY m.posted DESC")
//	List<Message> findLastMessagesByUser(@Param("user") User1 user);
//}
