package it.unisannio.esercizio9;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DataBase
 */
@WebServlet("/DataBase")
public class DataBase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataBase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Boolean check=true;
		Boolean nickNameAlreadyUsed = false;
		//creo il file in cui scrivere
		File file = new File ("DataBase.dati");
		FileWriter fw = new FileWriter(file, true); //apro il file  senza eliminare vecchi dati
		PrintWriter outToFile = new PrintWriter(fw);
		
		//ora devo recuperare i dati
		String nickName = request.getParameter("nickName");
		if (nickName.length()==0) check = false;
		FileSearcher fs = new FileSearcher(new Scanner (file));
		if (fs.containsNickName(nickName)) nickNameAlreadyUsed = true; //se ho uno gia registrato non va bene
		String name = request.getParameter("name");
		if (name.length() == 0) check = false;
		String surname = request.getParameter("surname");
		if (surname.length() == 0) check = false;
		String email = request.getParameter("e-mail");
		if (email.length() == 0) check = false;
		String password = request.getParameter("password");
		if (password.length() == 0) check = false;
		String nazione = request.getParameter("nazione");
		if (nazione.length() == 0) check = false;
		String date = request.getParameter("data");
		if (date.length() == 0) check = false;
		String paese = request.getParameter("paese");
		if (paese.length() == 0) check = false;
		//creo per scrittura
		PrintWriter pw = response.getWriter();
		
		//valuto cosa scrivere
		if (check && !nickNameAlreadyUsed) {
			//stampo i valori sul file
			outToFile.println("nickName: "+nickName);
			outToFile.println("password: "+password);
			outToFile.println("name: "+name);
			outToFile.println("surname: "+surname);
			outToFile.println("email: "+email);
			outToFile.println("nazione: "+nazione);
			outToFile.println("date: "+date);
			outToFile.println("paese: "+paese);
			response.setContentType("text/plain");
			pw.println("file creato correttamente");
			response.setStatus(201);
		}
		else {
			if(nickNameAlreadyUsed) {
				response.setContentType("text/html");
				pw.println("<H1>nickName already registered, pls try using a different one</H1><br>");
				response.setStatus(400);
	        }
			else {
				response.setContentType("text/html");
				pw.println("<H1>please try again, there are missing parameters</H1><br>");
				response.setStatus(400);
			}
			
		}
		fw.close();
		outToFile.close();
		pw.close();
	}

}
