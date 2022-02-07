package unisannio.crovella.es9;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileInputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login3")
public class Login3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		FileInputStream fi = new FileInputStream("database");
		
		Scanner sc = new Scanner(fi);
		
		boolean login = false;
		String line = null;
		
		do {
			
			line = sc.nextLine();
			
			if ((line != null) && line.startsWith("username")) {
				
				String un = line.substring(line.indexOf(":")+1).trim();
				
				if (un.equals(username)) {
					
					String pw = sc.nextLine().substring(line.indexOf(":")+1).trim();
					login = pw.equals(password);
				
				}
			}
		} while(!login && sc.hasNextLine());
		
		response.setContentType("text/html; charset = utf-8");
		
		if(login) {
			
			Cookie co = new Cookie("Token","0298871665");
			co.setMaxAge(100);
			response.addCookie(co);
					
		}
		
		
		PrintWriter pw = response.getWriter();
		
		String output =
		"<HTML>" +
		"<HEAD>" +
			"<TITLE>" +
					"Login" +
				"</TITLE>" +
			"<HEAD>" +
			"<BODY>" +
				"<H2>" +
					(login ? "La tua autenticazione è stata eseguita coon successo" : "Hai inserito credenziali sbagliate o non sei registrato") +
				"</H2>" +
			"<BODY>" +
		"</HTML>";
		
		pw.print(output);
		pw.flush();
		
	}

}

