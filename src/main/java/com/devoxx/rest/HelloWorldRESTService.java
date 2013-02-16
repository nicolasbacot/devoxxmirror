package com.devoxx.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/helloworld")
public class HelloWorldRESTService {

	@GET
	@Produces("application/json")
	public String helloCloud(){
		return "Welcome to the Cloud";
	}
	
}
