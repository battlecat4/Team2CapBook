package com.cg.project.beans;
 
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
 @Entity
public class Status {
 
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private int statusId;
	private String text;
	private String statusDate;
 
	
	@ManyToOne
	@JoinColumn(name="emailId")
	 @JsonBackReference
	private User1 user;
 
	public Status() {
		super();
	} 
 
	public Status(String text, User1 user) {
		super();
		this.text = text;
		this.user = user;
		setStatusDate();
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

	public String getStatusDate() {		 
		return this.statusDate;
	}

	public void setStatusDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  
		this.statusDate = formatter.format(date);
		//this.statusDate=date;
	}
	
}