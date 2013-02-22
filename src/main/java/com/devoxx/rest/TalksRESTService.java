package com.devoxx.rest;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.devoxx.ejb.DevoxxCache;
import com.devoxx.ejb.DevoxxCacheBean;
import com.devoxx.model.Talk;


@Path("/talks")
@Produces("application/json")
public class TalksRESTService {

    @Inject
    private DevoxxCache devoxxCache;
	
	@GET
	public Talk[] getTalks(){
		return devoxxCache.getTalks();
	}
	
	@GET
	@Path("{id}")
	public Talk getTalk(@PathParam("id") String id){
		return devoxxCache.getTalk(id);
	}

}
