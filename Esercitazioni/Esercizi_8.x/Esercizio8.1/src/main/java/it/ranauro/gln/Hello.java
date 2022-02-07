package it.ranauro.gln;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/Hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*response.setContentType("text/plain");	// diciamo al browser che vogliamo inviare del testo semplice
		PrintWriter pw = response.getWriter();
		
		pw.println("Hello servlet world! ");	// stampiamo sullo "stream" associato alla connessione 
		pw.flush(); // puliamo il buffer*/
		
		response.setContentType("text/html");	// diciamo al browser che vogliamo inviare del testo semplice
		PrintWriter pw = response.getWriter();
		
		pw.println("<H1> Hello Servlet! </H1>");	// stampiamo sullo "stream" associato alla connessione 
		pw.flush(); // puliamo il buffer
		
	}

}
