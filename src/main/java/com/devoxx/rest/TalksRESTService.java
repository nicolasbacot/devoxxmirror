package com.devoxx.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.resteasy.annotations.cache.Cache;

import com.devoxx.ejb.DevoxxCache;
import com.devoxx.model.Talk;


@Path("/talks")
@Produces("application/json")
public class TalksRESTService {

    @Inject
    private DevoxxCache devoxxCache;
	
	@GET
    @Cache(maxAge=3600)
	public Talk[] getTalks(){
		return devoxxCache.getTalks();
	}
	
	@GET
	@Path("{id}")
    @Cache(maxAge=3600)
	public Talk getTalk(@PathParam("id") String id){
		return devoxxCache.getTalk(id);
	}

}
