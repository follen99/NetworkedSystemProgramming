package it.ranauro.gln;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
        String password = request.getParameter("password");

        FileInputStream fis = new FileInputStream("database");
        Scanner sc = new Scanner(fis);
        boolean login = false;
        String line = null;
        do {
            line = sc.nextLine();
            if ((line != null) && line.startsWith("username")){
                String pw = line.substring(line.indexOf(":")+1).trim();
                login = pw.equals(password);

            }
        }while (!login && sc.hasNextLine());

        response.setContentType("text/html; charset=utg-8");

        PrintWriter pw=response.getWriter();
        String output=
                "<HTML>"+
                        "<HEAD>"+
                            "<TITLE>"+
                                "Login"+
                            "</TITLE>"+
                        "</HEAD>"+
                        "<BODY>"+
                            "<H2>"+
                                (login ? "Autenticazione riuscita" : "Autenticazione fallita!\nCredenziali errate.")+
                            "</H2>"+
                        "</BODY>"+
                "</HTML>";

        pw.print(output);
        pw.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
