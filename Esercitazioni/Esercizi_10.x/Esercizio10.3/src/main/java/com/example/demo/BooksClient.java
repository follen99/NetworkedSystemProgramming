package com.example.demo;



import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class BooksClient {
	public static void main(String[] args) {
		
		try {
			Client client = ClientBuilder.newClient();	
			
			WebTarget endpoint = client.target("http://127.0.0.1:8080");
		
			WebTarget resource =  endpoint.path("/books/4321");	
			Book b = resource.request().accept("/application/json").get(Book.class);
			
			System.out.println("Book: "+ b);	
			System.out.println();
			
			//POST /books
			
			resource = endpoint.path("/books");
			Book book = new Book("3467", "Example", "Mario Rossi");
			Response res = resource.request().accept("/application/json").post(Entity.entity(book, MediaType.APPLICATION_JSON));
			
			System.out.println("Libro creato: "+ book + '\n' + res.getLocation());
			System.out.println();
			
			//GET /book/3467
			resource = endpoint.path("/book/3467");
			Book book1 = resource.request().accept("/application/json").get(Book.class);
			
			System.out.println("Book: "+ book1);
			System.out.println();
			
			//GET /books
			resource = endpoint.path("/books");
			Book books = resource.request().accept("/application/json").get(Book.class);
			
			System.out.println("La collezione dei libri: "+ books);
			System.out.println();
			
			//POST /books/3467/orders
			resource = endpoint.path("/books");
			res = resource.request().accept("/application/json").post(Entity.entity(new Order(), MediaType.APPLICATION_JSON));
		
			String uriPath = res.getLocation().getPath();
			System.out.println("Ordine id: "+ uriPath);
			System.out.println();
			
			
			//GET /books/3467/orders/{oid}
			resource = endpoint.path(uriPath);
			Order order = resource.request().accept("/application/json").get(Order.class);
		
			System.out.println(order);
			System.out.println();
		
			//DELETE /books/3467
			resource = endpoint.path("/books/3467");
			resource.request().accept("/application/json").delete();
			
			System.out.println("Book 3467 deleted.");
			System.out.println();
			
			
			//GET /books/3467
			resource = endpoint.path("/books/3467");
			book1 = resource.request().accept("/application/json").get(Book.class);
			
			if(book1==null) {
				System.out.println("Il Book 3467 non esiste.");
			}
			System.out.println();
		
		}catch(NotFoundException e) {
			System.out.println("Resource not found.");
		}
	}
}
