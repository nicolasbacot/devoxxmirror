package com.devoxx.model.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Poll")
public class PollEntity {

	@Id
	@GeneratedValue
	private Long id;

	String userName = "anonymous";

	@Temporal(TemporalType.DATE)
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
