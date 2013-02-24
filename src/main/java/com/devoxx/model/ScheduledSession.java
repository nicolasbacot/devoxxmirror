package com.devoxx.model;

public class ScheduledSession {

	private String speaker;

	private String presentationUri;

	private String code;

	private String fromTime;

	private boolean partnerSlot;

	private EmbeddedSpeaker[] speakers;

	private String type;

	private String kind;

	private String toTime;

	private Long id;

	private String title;

	private String speakerUri;

	private Long speakerId;

	private Long presentationId;

	private String room;

	public String getSpeaker() {
		return speaker;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	public String getPresentationUri() {
		return presentationUri;
	}

	public void setPresentationUri(String presentationUri) {
		this.presentationUri = presentationUri;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public boolean isPartnerSlot() {
		return partnerSlot;
	}

	public void setPartnerSlot(boolean partnerSlot) {
		this.partnerSlot = partnerSlot;
	}

	public EmbeddedSpeaker[] getSpeakers() {
		return speakers;
	}

	public void setSpeakers(EmbeddedSpeaker[] speakers) {
		this.speakers = speakers;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
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

	public Long getSpeakerId() {
		return speakerId;
	}

	public void setSpeakerId(Long speakerId) {
		this.speakerId = speakerId;
	}

	public Long getPresentationId() {
		return presentationId;
	}

	public void setPresentationId(Long presentationId) {
		this.presentationId = presentationId;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

}
