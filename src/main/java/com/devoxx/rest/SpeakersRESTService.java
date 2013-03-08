package com.devoxx.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.resteasy.annotations.cache.Cache;

import com.devoxx.ejb.DevoxxCache;
import com.devoxx.model.json.Speaker;


@Path("/speakers")
@Produces("application/json")
@Stateless
public class SpeakersRESTService {

    @Inject
    private DevoxxCache devoxxCache;

    @GET
    @Cache(maxAge=3600)
    public Speaker[] getSpeakers(){
        return devoxxCache.getSpeakers();
    }

    @GET
    @Path("{id}")
    @Cache(maxAge=3600)
    public Speaker getSpeaker(@PathParam("id") String id){
        return devoxxCache.getSpeaker(id);
    }



}
