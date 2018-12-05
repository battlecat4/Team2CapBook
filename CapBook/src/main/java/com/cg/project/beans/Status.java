package com.cg.project.beans;
 
import javax.persistence.ManyToOne;
 
public class Status {
 
	private String text;
 
	@ManyToOne
	private User1 user;
 
	public Status() {
		super();
	}
	public Status(String text, User1 user) {
		super();
		this.text = text;
		this.user = user;
	}
 
 
	public String getText() {
		return text;
	}
 
	public void setText(String text) {
		this.text = text;
	}
 
	public User1 getUser() {
		return user;
	}
 
	public void setUser(User1 user) {
		this.user = user;
	}
 
 
}