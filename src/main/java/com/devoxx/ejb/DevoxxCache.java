package com.devoxx.ejb;

import com.devoxx.model.json.ScheduledRoom;
import com.devoxx.model.json.ScheduledSession;
import com.devoxx.model.json.Speaker;
import com.devoxx.model.json.Talk;

public interface DevoxxCache {

    Speaker[] getSpeakers();

    Speaker getSpeaker(String id);

    Talk[] getTalks();

    void loadData();

    Talk getTalk(String id);
    
    ScheduledSession[] getSchedule(int day);

    ScheduledRoom[] getRooms();

    ScheduledRoom getRoom(String id);

    ScheduledSession[] getScheduleByRoom(int day, String roomId);

}
