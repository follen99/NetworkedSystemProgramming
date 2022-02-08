package com.example.demo;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



public class StringsClient {
	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		WebTarget endpoint = client.target("http://127.0.0.1:8080");
		
		WebTarget resource = endpoint.path("/strings");
		String string = new String("second");
		Response response = resource.request().post(Entity.entity(string, MediaType.TEXT_PLAIN));
		
		// POST a book
		
		System.out.println("Stringa di richiesta: " + Entity.entity(string, MediaType.TEXT_PLAIN));
		System.out.println("Hai creato una stringa: " + string + "\n" + response.getLocation());
		System.out.println();
		
		// GET a book
		resource = endpoint.path("/strings/1");
		String b = resource.request().accept("text/plain").get(String.class);
		System.out.println("Stringa richiesta: " + b);

	}

}
