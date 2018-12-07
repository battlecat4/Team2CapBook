package com.cg.project.beans;
 
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
 @Entity
public class Status {
 
	 @Id
	 private int statusId;
	private String text;
 
	//@ManyToOne
	//private User1 user;
 
	public Status() {
		super();
	}
	public Status(String text) {
		super();
		this.text = text;
	}
 
 
	public String getText() {
		return text;
	}
 
	public void setText(String text) {
		this.text = text;
	}
 
	/*public User1 getUser() {
		return user;
	}
 
	public void setUser(User1 user) {
		this.user = user;
	}*/
 
 
}