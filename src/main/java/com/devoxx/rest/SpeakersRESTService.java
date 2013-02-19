package com.devoxx.rest;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.devoxx.ejb.DevoxxCacheBean;
import com.devoxx.model.Speaker;


@Path("/speakers")
@Produces("application/json")
public class SpeakersRESTService {

	@EJB
	private DevoxxCacheBean devoxxReader;
	
	@GET
	public Speaker[] getSpeakers(){
		return devoxxReader.getSpeakers();
	}

	@GET
	@Path("{id}")
	public Speaker getSpeaker(@PathParam("id") String id){
		return devoxxReader.getSpeaker(id);
	}
	
	
	
}
