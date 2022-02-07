package it.ranauro.gln;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MyContacts
 */
@WebServlet("/MyContacts")
public class MyContacts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyContacts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int myContacts = 0;
		HttpSession session = request.getSession();
		
		// se la sessione non è nuova
		if(!session.isNew()) {
			myContacts = (Integer) session.getAttribute("myContacts");
		}
		// se la sessione è nuova
		session.setAttribute("myContacts", ++myContacts);
		
		response.setContentType("text/plain");
		PrintWriter pw = response.getWriter();
		pw.println("Hai contattato il server " + myContacts + " volte.");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
