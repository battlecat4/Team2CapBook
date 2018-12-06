//package com.cg.project.beans;
// 
//import java.util.Date;
// 
//import javax.persistence.Entity;
//import javax.persistence.Id;
// 
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
// 
//@Entity
//public class Message {
//	@Id
//	private long id;
//	private User1 sender;
//	private User1 recepient;
//	private String body;
//	private Date posted= new Date();
// 
//	public Message() {
//		super();
//	}
//	public Message(long id, User1 sender, User1 recepient, String body, Date posted) {
//		super();
//		this.id = id;
//		this.sender = sender;
//		this.recepient = recepient;
//		this.body = body;
//		this.posted = posted;
//	}
//	public long getId() {
//		return id;
//	}
//	public void setId(long id) {
//		this.id = id;
//	}
//	public User1 getSender() {
//		return sender;
//	}
//	public void setSender(User1 sender) {
//		this.sender = sender;
//	}
//	public User1 getRecepient() {
//		return recepient;
//	}
//	public void setRecepient(User1 recepient) {
//		this.recepient = recepient;
//	}
//	public String getBody() {
//		return body;
//	}
//	public void setBody(String body) {
//		this.body = body;
//	}
//	public Date getPosted() {
//		return posted;
//	}
//	public void setPosted(Date posted) {
//		this.posted = posted;
//	}
// 
//}