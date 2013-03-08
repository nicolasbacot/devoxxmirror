package com.devoxx.ejb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.devoxx.model.json.ScheduledRoom;
import com.devoxx.model.json.ScheduledSession;
import com.devoxx.model.json.Speaker;
import com.devoxx.model.json.Talk;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@AccessTimeout(unit = TimeUnit.SECONDS, value = 60)
@Startup
public class DevoxxCacheBean implements DevoxxCache {

	private Speaker[] speakers = null;
	private Talk[] talks = null;
	private ScheduledSession[][] scheduledSessions = null;
    private Map<Long, ScheduledRoom> mapOfRooms = null;
	private Map<Long, Speaker> mapOfSpeakers = null;
	private Map<Long, Talk> mapOfTalks = null;

	@PostConstruct
	public void init() {
		loadData();
	}

    @Override
    public Speaker[] getSpeakers() {
		return speakers;
	}

    @Override
    public Talk[] getTalks() {
		return talks;
	}

	public void loadData() {
		Talk[] resultTalks = DevoxxJSONReader.readTalks();
		Speaker[] resultSpeakers = DevoxxJSONReader.readSpeakers();
		ScheduledSession[][] resultScheduledSessions = DevoxxJSONReader.readSchedule();
        ScheduledRoom[] resultRooms = DevoxxJSONReader.readRooms();
		storeData(resultTalks, resultSpeakers, resultScheduledSessions, resultRooms);
	}

	@Lock(LockType.WRITE)
	void storeData(Talk[] resultTalks, Speaker[] resultSpeakers, ScheduledSession[][] resultScheduledSessions, ScheduledRoom[] resultRooms) {
		speakers = resultSpeakers;
        talks = resultTalks;
		mapOfSpeakers = new HashMap<Long, Speaker>();
		mapOfTalks = new HashMap<Long, Talk>();
        mapOfRooms = new HashMap<Long, ScheduledRoom>();
        Map<String, Long> mapOfRoomNames = new HashMap<String, Long>();

        for (Talk curTalk : resultTalks) {
			mapOfTalks.put(curTalk.getId(), curTalk);
		}
		for (Speaker curSpeaker : resultSpeakers) {
			mapOfSpeakers.put(curSpeaker.getId(), curSpeaker);
		}
        for (ScheduledRoom curRoom : resultRooms) {
            mapOfRooms.put(curRoom.getId(), curRoom);
            mapOfRoomNames.put(curRoom.getName(), curRoom.getId());
        }
        scheduledSessions = resultScheduledSessions;
        for (int i = 0; i< scheduledSessions.length; i++) {
            for (int j = 0; j<scheduledSessions[i].length; j++) {
                Long roomId = mapOfRoomNames.get(scheduledSessions[i][j].getRoom());
                scheduledSessions[i][j].setRoomId(roomId);
            }
        }
    }

    @Override
    public Speaker getSpeaker(String id) {
		return mapOfSpeakers.get(Long.valueOf(id));
	}

    @Override
    public Talk getTalk(String id) {
		return mapOfTalks.get(Long.valueOf(id));
	}

    @Override
    public ScheduledRoom getRoom(String id) {
        return mapOfRooms.get(Long.valueOf(id));
    }

	@Override
	public ScheduledSession[] getSchedule(int day) {
		ScheduledSession[] out = null;
		if (day <= scheduledSessions.length) {
			out = scheduledSessions[day - 1];
		}
		return out;
	}

    @Override
    public ScheduledRoom[] getRooms() {
        return mapOfRooms.values().toArray(new ScheduledRoom[0]);
    }

    @Override
    public ScheduledSession[] getScheduleByRoom(int day, String roomId) {
        ScheduledSession[] scheduleSessions = getSchedule(day);
        ScheduledRoom scheduledRoom = mapOfRooms.get(Long.valueOf(roomId));
        String name = scheduledRoom.getName();
        List<ScheduledSession> toReturn = new ArrayList<ScheduledSession>();
        for (ScheduledSession session : scheduleSessions) {
            if (name.equals(session.getRoom())) {
                toReturn.add(session);
            }
        }
        return toReturn.toArray(new ScheduledSession[0]);
    }

}
