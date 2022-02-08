package com.example.demo;



public class Order {
	public Order()	{}
	public Order(String isbn, int i) { 
		this.isbn = isbn; 
		id = i;
		}
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public int getId () {
		return id;
	}
	
	public void setId(int o) {
		id =o;
	}
	
	public String toString() {return "Ordine n. " + id + " relativo al libro con isbn " + isbn;}
	
	private String isbn;
	private int id;
}
