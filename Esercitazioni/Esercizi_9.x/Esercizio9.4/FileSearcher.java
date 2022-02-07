package it.unisannio.esercizio9;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileSearcher {
	private List<String> token ;
	private Scanner inFile;
	
	public FileSearcher(Scanner inFile) {
		this.inFile = inFile;
		this.makeList();
	}
	
	private void makeList() {
		this.token = new LinkedList<String>();
		while (this.inFile.hasNextLine()) 
			this.token.add(inFile.nextLine());
		
	}

		
	public Boolean comparePassForNickName(String username, String password) {
		
		if (!(this.containsNickName(username)))
			return false;
		String passwordFromList;
		
		int indexOfPassword = this.token.indexOf("username: "+username);
		passwordFromList = this.token.get(indexOfPassword+1); //la password sta dopo
		if (passwordFromList.equals("password: "+password))
				return true;

		return false;
			
		}

	public boolean containsNickName(String username) {
		if (this.token.contains("username: "+username))
			return true;
		return false;
	}
		
	
	

}
