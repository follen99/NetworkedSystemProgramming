package it.ranauro.gln;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Echo
 */
@WebServlet("/Echo")
public class Echo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Echo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		
		
		String responseHTML = "<H1>"
				+ "Ti chiami:"
				+ "</H1>"
				+ "" + firstname + " " + lastname;
		
		pw.println(responseHTML);
	}

}
