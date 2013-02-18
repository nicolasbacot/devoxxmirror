package com.devoxx.ejb;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

import com.devoxx.controller.DevoxxJSONReader;
import com.devoxx.model.Speaker;


@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class DevoxxReaderBean {

	@Lock(LockType.READ)
	public Speaker[] getSpeakers() {
		return speakers;
	}
	
	@Lock(LockType.WRITE)
	public void setSpeakers(Speaker[] speakers) {
		this.speakers = speakers;
	}

	private Speaker[] speakers;
	
	public void loadSpeakers(){
		Speaker[] result = DevoxxJSONReader.readSpeakers();
		setSpeakers(result);
	}
	
	
}
