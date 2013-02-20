package com.devoxx.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.devoxx.ejb.DevoxxReaderBean;
import com.devoxx.model.Speaker;


@Path("/speakers")
@Stateless
public class SpeakersRESTService {

	@Inject
	private DevoxxReaderBean devoxxReader;
	
	@GET
	@Produces("application/json")
	public Speaker[] getSpeakers(){
		return devoxxReader.getSpeakers();
	}
	
	@GET
	@Path("/reload")
	public void reloadSpeakers(){
		devoxxReader.loadSpeakers();
	}
	
	
}
