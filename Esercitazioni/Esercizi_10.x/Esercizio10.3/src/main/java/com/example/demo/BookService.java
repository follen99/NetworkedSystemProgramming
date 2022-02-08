package com.example.demo;


import java.awt.print.Book;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.model.internal.RankedComparator.Order;

@Consumes("application/json")
@Produces("application/json")
@Path("/books")

public class BookService {
	private HashMap<String, Book> bookCollection= new HashMap<String, Book>();
	private HashMap<String, List<Order>> orderCollection= new HashMap<String, List<Order>>();
	
	public BookService() {
		bookCollection.put("1236", new Book("1236", "Distributed Systems", "Colouris"));
		bookCollection.put("4321", new Book("4321", "Software Architecture", "Garland"));
		bookCollection.put("3333", new Book("3333", "Middleware", "Schmidt"));
	}
	
	@GET
	public Books getBookList() {
		return new Books(new ArrayList<String>(bookCollection.keySet()));
	}
	
	@POST
	public Response createBook(Book book) {
		bookCollection.put(book.getIsbn(), book);
		URI uri= null;
		try {
			uri= new URI("/books/"+book.getIsbn());
		}
		catch(URISyntaxException e){	
		}
		return Response.created(uri).build();
	}
	
	@GET
	@Path("/{isbn}")
	public Book getBookDetails(@PathParam("isbn") String isbn) {
		return bookCollection.get(isbn);
	}
	
	@DELETE
	@Path("/{isbn}")
	public void deleteBook(@PathParam("isbn") String isbn) {
		bookCollection.remove(isbn);
	}
	
	
	@POST
	@Path("/{isbn}/orders/")
	public Response orderBook(@PathParam("isbn") String isbn) {
		List<Order> list= orderCollection.get(isbn);
		if(list==null) {
			list= new ArrayList<Order>();
		}
		int oid= orderCollection.get(isbn).size();
		
		Order order= new Order(isbn, oid);
		list.add(oid, order);
		
		URI uri= null;
		try {
			uri= new URI("/books/"+isbn+"/orders/"+oid);
		}
		catch(URISyntaxException e) {
		}
		return Response.created(uri).build();
		
	}
	
	@POST
	@Path("/{isbn}/orders/{oid}")
	public Order getOrder(@PathParam("isbn") String isbn, @PathParam("oid") int oid) {
		List<Order> list= orderCollection.get(isbn);
		if(list != null) return list.get(oid);
		return null;
		
	}
}
