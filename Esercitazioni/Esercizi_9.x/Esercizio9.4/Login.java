package it.unisannio.esercizio9;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.FileInputStream;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		
		//creo scanner per il fileSearcher
		FileInputStream fis = new FileInputStream("C:\\Users\\panne\\eclipse-workspace\\ex8.5\\WebContent\\login.dati");
		Scanner inFile = new Scanner (fis);
		FileSearcher fs = new FileSearcher(inFile);
		Boolean found = fs.comparePassForNickName(name, password);
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		if (found) {
			pw.println("<H1>Ti sei loggato correttamente!!</H1>");
			HttpSession session = request.getSession();
			session.setAttribute("username", name);
			session.setAttribute("password", password);
			response.setStatus(200);
		}
		
		else {
			pw.println("<H1>credenziali errate</H1>");
			response.setStatus(404);
		}
		
		inFile.close();
		
		
	}

}
