package com.devoxx.rest;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.devoxx.ejb.DevoxxCacheBean;
import com.devoxx.model.Talk;


@Path("/talks")
@Produces("application/json")
public class TalksRESTService {

	@EJB
	private DevoxxCacheBean devoxxReader;
	
	@GET
	public Talk[] getTalks(){
		return devoxxReader.getTalks();
	}
	
	@GET
	@Path("{id}")
	public Talk getTalk(@PathParam("id") String id){
		return devoxxReader.getTalk(id);
	}

}
