package com.devoxx.ejb;

import com.devoxx.model.ScheduledRoom;
import com.devoxx.model.ScheduledSession;
import com.devoxx.model.Speaker;
import com.devoxx.model.Talk;

public class DevoxxFakeCache implements DevoxxCache {

    @Override
    public Speaker[] getSpeakers() {
        return null;
    }

    @Override
    public Speaker getSpeaker(String id) {
        Speaker speaker = new Speaker();
        speaker.setId(new Long(id));
        speaker.setFirstName("Steve");
        speaker.setLastName("Jobs");
        return speaker;
    }

    @Override
    public Talk[] getTalks() {
        return null;
    }

    @Override
    public void loadData() {
    }

    @Override
    public Talk getTalk(String id) {
        return null;
    }

	@Override
	public ScheduledSession[] getSchedule(int day) {
		return null;
	}

    @Override
    public ScheduledRoom[] getRooms() {
        return null;
    }

    @Override
    public ScheduledRoom getRoom(String id) {
        return null;
    }

    @Override
    public ScheduledSession[] getScheduleByRoom(int day, String roomId) {
        return null;
    }

}
