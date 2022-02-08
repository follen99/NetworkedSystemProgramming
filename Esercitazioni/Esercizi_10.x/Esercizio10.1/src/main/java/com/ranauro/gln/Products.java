package com.ranauro.gln;

import javax.validation.constraints.Past;
import javax.ws.rs.*;

@Produces("application/json")
@Path("/products")
public class Products {
	@GET
	public double getProduct(@QueryParam("a") double a, @QueryParam("b") double b) {
		return a*b;
	}
}
