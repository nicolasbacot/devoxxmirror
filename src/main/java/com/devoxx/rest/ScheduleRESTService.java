package com.devoxx.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.devoxx.ejb.DevoxxCache;
import com.devoxx.model.json.ScheduledRoom;
import com.devoxx.model.json.ScheduledSession;

import org.jboss.resteasy.annotations.cache.Cache;

@Path("/schedule")
@Produces("application/json")
public class ScheduleRESTService {

	@Inject 
	DevoxxCache devoxxCache;
	
    @GET
    @Path("/day/{id}")
    @Cache(maxAge=3600)
    public ScheduledSession[] getSchedule(@PathParam("id") int id){
        return devoxxCache.getSchedule(id);
    }

    @GET
    @Path("/rooms")
    @Cache(maxAge=3600)
    public ScheduledRoom[] getScheduleRooms(){
        return devoxxCache.getRooms();
    }

    @GET
    @Path("/rooms/{roomId}")
    @Cache(maxAge=3600)
    public ScheduledRoom getScheduleRoom(@PathParam("roomId") String roomId){
        return devoxxCache.getRoom(roomId);
    }

    @GET
    @Path("/day/{day}/room/{roomId}")
    @Cache(maxAge=3600)
    public ScheduledSession[] getScheduleByRoom(@PathParam("day") int day, @PathParam("roomId") String roomId){
        return devoxxCache.getScheduleByRoom(day, roomId);
    }
}
