package com.devoxx.ejb;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.devoxx.controller.DevoxxJSONReader;
import com.devoxx.model.ScheduledSession;
import com.devoxx.model.Speaker;
import com.devoxx.model.Talk;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@AccessTimeout(unit = TimeUnit.SECONDS, value = 60)
@Startup
public class DevoxxCacheBean implements DevoxxCache {

	private Speaker[] speakers = null;
	private Talk[] talks = null;
	private ScheduledSession[][] scheduledSessions = null;
	private Map<Long, Speaker> mapOfSpeakers = null;
	private Map<Long, Talk> mapOfTalks = null;

	@PostConstruct
	public void init() {
		loadData();
	}

	public Speaker[] getSpeakers() {
		return speakers;
	}

	public void setSpeakers(Speaker[] speakers) {
		this.speakers = speakers;
	}

	public Talk[] getTalks() {
		return talks;
	}

	public void setTalks(Talk[] talks) {
		this.talks = talks;
	}

	
	public void setScheduledSessions(ScheduledSession[][] scheduledSessions) {
		this.scheduledSessions = scheduledSessions;
	}

	public void loadData() {
		Talk[] resultTalks = DevoxxJSONReader.readTalks();
		Speaker[] resultSpeakers = DevoxxJSONReader.readSpeakers();
		ScheduledSession[][] resultScheduledSessions = DevoxxJSONReader
				.readSchedule();
		storeData(resultTalks, resultSpeakers, resultScheduledSessions);
	}

	@Lock(LockType.WRITE)
	void storeData(Talk[] resultTalks, Speaker[] resultSpeakers,
			ScheduledSession[][] resultScheduledSessions) {
		setSpeakers(resultSpeakers);
		setTalks(resultTalks);
		setScheduledSessions(resultScheduledSessions);
		mapOfSpeakers = new HashMap<Long, Speaker>();
		mapOfTalks = new HashMap<Long, Talk>();
		for (Talk curTalk : resultTalks) {
			mapOfTalks.put(curTalk.getId(), curTalk);
		}
		for (Speaker curSpeaker : resultSpeakers) {
			mapOfSpeakers.put(curSpeaker.getId(), curSpeaker);
		}

	}

	public Speaker getSpeaker(String id) {
		return mapOfSpeakers.get(Long.valueOf(id));
	}

	public Talk getTalk(String id) {
		return mapOfTalks.get(Long.valueOf(id));
	}

	@Override
	public ScheduledSession[] getSchedule(int day) {
		ScheduledSession[] out = null;
		if (day <= scheduledSessions.length) {
			out = scheduledSessions[day - 1];
		}
		return out;
	}

}
