package it.ranauro.gln;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File f = new File("login.txt");

        FileOutputStream fs = new FileOutputStream(f, true);
        PrintStream ps = new PrintStream(fs);

        ps.println("Nome: "+ request.getParameter("Nome"));
        ps.println("Cognome: "+ request.getParameter("Cognome"));
        ps.println("E-mail: "+ request.getParameter("Email"));
        ps.println("Username: "+ request.getParameter("Username"));
        ps.println("Password: "+ request.getParameter("Password"));


        response.setContentType("text/html");

        PrintWriter pw = response.getWriter();

        String output = "<html"+
                "<head>" +
                "<title>" +
                    "Registrazione" +
                "</title>" +
                "</head>" +
                "<body>" +
                "<h1>" +
                    request.getParameter("Nome") + ", la tua registrazione � andata a buon fine." +
                "</h1>" +
                "</body>" +
                "</html>";

        pw.println(output);
        pw.flush();
        pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
