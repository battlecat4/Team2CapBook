package com.cg.project.beans;
 
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
 @Entity
public class Posts {
	@Id
	private String friendId;
	private String text;
	
	/*@ManyToOne
	private User1 user;*/
 
	public Posts() {
		super();
	}
	/*public Posts(String friendId, String text, User1 user) {
		super();
		this.friendId = friendId;
		this.text = text;
		this.user = user;
	}*/
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
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