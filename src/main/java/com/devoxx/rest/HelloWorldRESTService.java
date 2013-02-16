package com.devoxx.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/helloworld")
@Produces("application/json")
public class HelloWorldRESTService {

	@GET
	public String helloCloud(){
		return "Welcome to the Cloud";
	}
	
}
