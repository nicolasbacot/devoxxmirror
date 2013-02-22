package com.devoxx.model;

public class Speaker {

	private Long id;

	private String bio;

	private String company;

	private String imageURI;

	private String firstName;

	private String lastName;

	private String tweethandle;

	private EmbeddedTalk[] talks;

	public Long getId() {
		return id;
	}

	public String getImageURI() {
		return imageURI;
	}

	public void setImageURI(String imageURI) {
		this.imageURI = imageURI;
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

	public String getTweethandle() {
		return tweethandle;
	}

	public void setTweethandle(String tweethandle) {
		this.tweethandle = tweethandle;
	}

	public EmbeddedTalk[] getTalks() {
		return talks;
	}

	public void setTalks(EmbeddedTalk[] talks) {
		this.talks = talks;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
