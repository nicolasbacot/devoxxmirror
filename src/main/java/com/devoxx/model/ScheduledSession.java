package com.devoxx.model;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;

public class ScheduledSession {

    private static DateTimeFormatter HOUR_FORMATTER = DateTimeFormat.forPattern("HH:mm");
    private static String START_HOUR = "07:00";
    private static DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.S");

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

    private Long roomId;

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

    public String getFromHour() {
        return formatHour(fromTime);
    }

    public String getStartHour() {
        Instant start = HOUR_FORMATTER.parseDateTime(START_HOUR).toInstant();
        Instant from = HOUR_FORMATTER.parseDateTime(getFromHour()).toInstant();
        Duration duration = new Duration(start, from);
        return toHour(duration);
    }

    private String formatHour(String time) {
        DateTime dateTime = FORMATTER.parseDateTime(time);
        int minuteOfHour = dateTime.getMinuteOfHour();
        String minutes = minuteOfHour <10 ? "0"+minuteOfHour : ""+minuteOfHour;
        return dateTime.getHourOfDay() + ":" + minutes;
    }

    public String getDurationMinute() {
        Duration duration = getDuration();
        return Long.toString(duration.getStandardMinutes());
    }

    private Duration getDuration() {
        Instant from = FORMATTER.parseDateTime(fromTime).toInstant();
        Instant to = FORMATTER.parseDateTime(toTime).toInstant();
        return new Duration(from, to);
    }

    public String getDurationHour() {
        Duration duration = getDuration();
        return toHour(duration);
    }

    private String toHour(Duration duration) {
        BigDecimal minutes = new BigDecimal(duration.getStandardMinutes());
        BigDecimal hours = minutes.divide(new BigDecimal(60), 5, BigDecimal.ROUND_HALF_DOWN);
        return hours.toString();
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

    public String getToHour() {
        return formatHour(toTime);
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

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
