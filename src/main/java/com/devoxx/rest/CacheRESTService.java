package com.devoxx.rest;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.devoxx.ejb.DevoxxCacheBean;

@Path("/cache")
public class CacheRESTService {

	@EJB
	DevoxxCacheBean devoxxReaderBean;
	
	@GET
	@Path("/reload")
	public void reload(){
		devoxxReaderBean.loadData();
	}
}
