package com.devoxx.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.devoxx.ejb.DevoxxCache;
import com.devoxx.model.ScheduledSession;


@Path("/schedule")
@Produces("application/json")
public class ScheduleRESTService {

	@Inject 
	DevoxxCache devoxxCache;
	
    @GET
    @Path("/day/{id}")
    public ScheduledSession[] getSchedule(@PathParam("id") int id){
        return devoxxCache.getSchedule(id);
    }

	
	
}
