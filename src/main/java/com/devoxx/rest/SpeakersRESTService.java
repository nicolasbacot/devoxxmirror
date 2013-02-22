package com.devoxx.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.devoxx.ejb.DevoxxCache;
import com.devoxx.model.Speaker;


@Path("/speakers")
@Produces("application/json")
@Stateless
public class SpeakersRESTService {

    @Inject
    private DevoxxCache devoxxCache;

    @GET
    public Speaker[] getSpeakers(){
        return devoxxCache.getSpeakers();
    }

    @GET
    @Path("{id}")
    public Speaker getSpeaker(@PathParam("id") String id){
        return devoxxCache.getSpeaker(id);
    }



}
