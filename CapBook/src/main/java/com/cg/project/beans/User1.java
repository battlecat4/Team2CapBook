package com.cg.project.beans;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User1 {
	@Id
	private String emailId;
	private String password;
	private String firstName;
	private String lastName;
	private int age;
	private Date dateOfBirth;
	private String gender;
	
//	@OneToMany(mappedBy="user",targetEntity=Posts.class, fetch=FetchType.EAGER)
//	private List<Posts> posts;
//	@OneToMany(mappedBy="user",targetEntity=Status.class, fetch=FetchType.EAGER)
//	private List<Status> status;
		
	public User1() {
		super();
	}
	public User1(String emailId, String password, String firstName, String lastName, int age, Date dateOfBirth,
			String gender) {
		super();
		this.emailId = emailId;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
//	public List<Posts> getPosts() {
//		return posts;
//	}
//	public void setPosts(List<Posts> posts) {
//		this.posts = posts;
//	}
//	public List<Status> getStatus() {
//		return status;
//	}
//	public void setStatus(List<Status> status) {
//		this.status = status;
//	}
	
	
	
}
