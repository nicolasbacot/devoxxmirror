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
import com.devoxx.model.Speaker;
import com.devoxx.model.Talk;


@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@AccessTimeout (unit=TimeUnit.SECONDS, value=60)
@Startup
public class DevoxxCacheBean {

	private Speaker[] speakers = null;
	private Talk[] talks = null;
	private Map<Long,Speaker> mapOfSpeakers = null;
	private Map<Long,Talk> mapOfTalks = null;
	
	@PostConstruct
	public void init(){
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
	
	public void loadData(){
		Talk[] resultTalks = DevoxxJSONReader.readTalks();
		Speaker[] resultSpeakers = DevoxxJSONReader.readSpeakers();		
		storeData(resultTalks, resultSpeakers);
	}

	@Lock(LockType.WRITE)
	private void storeData(Talk[] resultTalks, Speaker[] resultSpeakers){
		setSpeakers(resultSpeakers);
		setTalks(resultTalks);
		mapOfSpeakers = new HashMap<Long, Speaker>();
		mapOfTalks = new HashMap<Long, Talk>();
		for (Talk curTalk : resultTalks){
			mapOfTalks.put(curTalk.getId(), curTalk);
		}
		for (Speaker curSpeaker: resultSpeakers){
			mapOfSpeakers.put(curSpeaker.getId(), curSpeaker);
		}

	}

	public Speaker getSpeaker(String id) {
		return mapOfSpeakers.get(Long.valueOf(id));
	}

	public Talk getTalk(String id) {
		return mapOfTalks.get(Long.valueOf(id));
	}

}
