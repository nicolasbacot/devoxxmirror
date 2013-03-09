package com.devoxx.model.json;

public class Talk {

	private String summary;

	private Tag[] tags;

	private String speaker;

	private String track;

	private String type;

	private EmbeddedSpeaker[] speakers;

	private Long id;

	private String title;

	private String speakerUri;

	private String speakerId;

	private String experience;

	private String language;

	private String room;
	
	private Long nbVotes;

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Tag[] getTags() {
		return tags;
	}

	public void setTags(Tag[] tags) {
		this.tags = tags;
	}

	public String getSpeaker() {
		return speaker;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	public String getTrack() {
		return track;
	}

	public void setTrack(String track) {
		this.track = track;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public EmbeddedSpeaker[] getSpeakers() {
		return speakers;
	}

	public void setSpeakers(EmbeddedSpeaker[] speakers) {
		this.speakers = speakers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSpeakerUri() {
		return speakerUri;
	}

	public void setSpeakerUri(String speakerUri) {
		this.speakerUri = speakerUri;
	}

	public String getSpeakerId() {
		return speakerId;
	}

	public void setSpeakerId(String speakerId) {
		this.speakerId = speakerId;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public Long getNbVotes() {
		return nbVotes;
	}

	public void setNbVotes(Long nbVotes) {
		this.nbVotes = nbVotes;
	}
	

}
