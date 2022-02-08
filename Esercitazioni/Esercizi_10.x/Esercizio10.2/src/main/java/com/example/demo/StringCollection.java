package com.example.demo;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Consumes("text/plain")
@Produces("text/plain")
@Path("/strings")
public class StringCollection {
	private List<String> strings = new ArrayList<String>();
	
	public StringCollection() {
		strings.add("first");
	}
	
	@GET
	@Path("/{id}")
	public Response getString(@PathParam("id") int id) {
		return Response.ok(strings.get(id)).build();
	}
	
	@POST
	public Response setString(String s) {
		strings.add(s);
		
		URI uri = null;
		try {
			uri = new URI("/strings/" + (strings.size()-1));
		}catch(Exception e) {
			System.err.println("Errore nella creazione della URI");
		}
		
		return Response.created(uri).build();
	}
}
