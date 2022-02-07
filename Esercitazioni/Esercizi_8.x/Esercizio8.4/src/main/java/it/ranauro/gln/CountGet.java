package it.ranauro.gln;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CountGet
 */
@WebServlet("/CountGet")
public class CountGet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int contacts;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountGet() {
        super();
        // TODO Auto-generated constructor stub
        this.contacts = 0;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.contacts +=1;
		
		response.setContentType("text/plain");
		PrintWriter pw = response.getWriter();
		
		pw.println("Questo server è stato contattato " + this.contacts + " volte");
		
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
