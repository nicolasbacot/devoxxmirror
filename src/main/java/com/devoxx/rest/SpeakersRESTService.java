package com.devoxx.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.devoxx.model.Speaker;


@Path("/speakers")
public class SpeakersRESTService {

	@GET
	@Produces("application/json")
	public List<Speaker> getSpeakers(){
		List<Speaker> out = new ArrayList<Speaker>();
		Speaker test1 = new Speaker();
		test1.setBio("bla bla bla");
		test1.setCompany("Test corp");
		test1.setId(1L);
		out.add(test1);
		return out;
	}
	
}
