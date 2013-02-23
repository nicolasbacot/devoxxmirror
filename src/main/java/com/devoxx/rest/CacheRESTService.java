package com.devoxx.rest;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.devoxx.ejb.DevoxxCache;
import com.devoxx.ejb.DevoxxCacheBean;

@Path("/cache")
public class CacheRESTService {

    @Inject
    private DevoxxCache devoxxCache;
	
	@GET
	@Path("/reload")
	public void reload(){
        devoxxCache.loadData();
	}
}
