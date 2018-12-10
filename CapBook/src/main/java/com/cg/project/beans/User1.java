package com.cg.project.beans;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class User1 {
		
	@Id
	/*@GeneratedValue(strategy=GenerationType.AUTO)
	private int userID; */
	private String emailId;
	private String password;
	private String firstName;
	private String lastName;
	private int age;
	private Date dateOfBirth;
	private String gender;
	private String securityAnswer;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@MapKey
	@JsonManagedReference
	private  List<Status>status;
	
//	@OneToMany(mappedBy="user",targetEntity=Posts.class, fetch=FetchType.EAGER)
//	private List<Posts> posts;
//	@OneToMany(mappedBy="user",targetEntity=Status.class, fetch=FetchType.EAGER)
//	private List<Status> status;
		
	public User1() {
		super();
	}
	
	

	public User1(String emailId, String password, String firstName, String lastName, int age, Date dateOfBirth,
		String gender, String securityAnswer) {
	super();
	this.emailId = emailId;
	this.password = password;
	this.firstName = firstName;
	this.lastName = lastName;
	this.age = age;
	this.dateOfBirth = dateOfBirth;
	this.gender = gender;
	this.securityAnswer = securityAnswer;
}



	public User1(String emailId, String password, String firstName, String lastName, int age, Date dateOfBirth,
		String gender, String securityAnswer, List<Status> status) {
	super();
	this.emailId = emailId;
	this.password = password;
	this.firstName = firstName;
	this.lastName = lastName;
	this.age = age;
	this.dateOfBirth = dateOfBirth;
	this.gender = gender;
	this.securityAnswer = securityAnswer;
	this.status = status;
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
	/*public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}*/	
	

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	public List<Status> getStatus() {
		return status;
	}
	public void setStatus(List<Status> status) {
		this.status = status;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((securityAnswer == null) ? 0 : securityAnswer.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User1 other = (User1) obj;
		if (age != other.age)
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (securityAnswer == null) {
			if (other.securityAnswer != null)
				return false;
		} else if (!securityAnswer.equals(other.securityAnswer))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}



	
	
//	public List<Posts> getPosts() {
//		return posts;
//	}
//	public void setPosts(List<Posts> posts) {
//		this.posts = posts;
//	}
	
	
	
	
	
}
