package com.example.demo;



public class Book {
	public Book(String i, String t, String a) {
		isbn = i;
		title = t;
		author = a;
	}
	
	public Book() {
		
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}


	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + "]";
	}

	private String isbn;
	private String title;
	private String author;
}
