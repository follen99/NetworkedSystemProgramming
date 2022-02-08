package com.example.demo;


import java.util.*;


public class Books {
	public Books() {}
	
	public Books(List<String> bs) {
		collection= bs;
	}
	
	public List<String> getCollection() {
		return collection;
	}
	public void setCollection(List<String> c) {
		collection= c;
	}
	
	public String toString() {
		return collection.toString();
	}
	private List<String> collection;
}
