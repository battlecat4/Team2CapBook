package com.cg.project.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Relationship {
	@Id
	private String userOneId;
	private String userTwoId;
	private int status;
	private String userActionId;
	public Relationship() {
		super();
	}
	public Relationship(String userOneId, String userTwoId, int status, String userActionId) {
		super();
		this.userOneId = userOneId;
		this.userTwoId = userTwoId;
		this.status = status;
		this.userActionId = userActionId;
	}
	public String getUserOneId() {
		return userOneId;
	}
	public void setUserOneId(String userOneId) {
		this.userOneId = userOneId;
	}
	public String getUserTwoId() {
		return userTwoId;
	}
	public void setUserTwoId(String userTwoId) {
		this.userTwoId = userTwoId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUserActionId() {
		return userActionId;
	}
	public void setUserActionId(String userActionId) {
		this.userActionId = userActionId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + status;
		result = prime * result + ((userActionId == null) ? 0 : userActionId.hashCode());
		result = prime * result + ((userOneId == null) ? 0 : userOneId.hashCode());
		result = prime * result + ((userTwoId == null) ? 0 : userTwoId.hashCode());
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
		Relationship other = (Relationship) obj;
		if (status != other.status)
			return false;
		if (userActionId == null) {
			if (other.userActionId != null)
				return false;
		} else if (!userActionId.equals(other.userActionId))
			return false;
		if (userOneId == null) {
			if (other.userOneId != null)
				return false;
		} else if (!userOneId.equals(other.userOneId))
			return false;
		if (userTwoId == null) {
			if (other.userTwoId != null)
				return false;
		} else if (!userTwoId.equals(other.userTwoId))
			return false;
		return true;
	}
	
	
	
	
}
