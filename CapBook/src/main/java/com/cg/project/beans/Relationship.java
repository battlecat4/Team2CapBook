package com.cg.project.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Relationship {
	@Id
	private int userOneId;
	private int userTwoId;
	private int status;
	private int userActionId;
	public Relationship() {
		super();
	}
	public Relationship(int userOneId, int userTwoId, int status, int userActionId) {
		super();
		this.userOneId = userOneId;
		this.userTwoId = userTwoId;
		this.status = status;
		this.userActionId = userActionId;
	}
	public int getUserOneId() {
		return userOneId;
	}
	public void setUserOneId(int userOneId) {
		this.userOneId = userOneId;
	}
	public int getUserTwoId() {
		return userTwoId;
	}
	public void setUserTwoId(int userTwoId) {
		this.userTwoId = userTwoId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getUserActionId() {
		return userActionId;
	}
	public void setUserActionId(int userActionId) {
		this.userActionId = userActionId;
	}
	
	
}
