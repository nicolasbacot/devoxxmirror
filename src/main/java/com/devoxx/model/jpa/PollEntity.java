package com.devoxx.model.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity(name = "Poll")
public class PollEntity {

	@Id
	@GeneratedValue
	private Long id;

	String userName = "anonymous";

	Date creationDate;

	@PrePersist
	public void onCreate() {
		creationDate = new Date();
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getId() {
		return id;
	}
	
	

}
