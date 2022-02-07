package it.ranauro.gln;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Calculate
 */
@WebServlet("/Calculate")
public class Calculate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Calculate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Float op1 = Float.parseFloat(request.getParameter("op1"));	// converto le stringhe in float
		Float op2 = Float.parseFloat(request.getParameter("op2"));
		
		String operation = request.getParameter("operation");
		
		float result = 0;
		
		/*if(operation == "+") {
			result = op1+op2;
		}else if(operation == "-"){
			result = op1-op2;
		}else if(operation == "*") {
			result = op1*op2;
		}else if(operation == "/") {
			if(op2 == 0) {
				// cant divide for zero
				result = 0;
				return;
			}
			result = op1/op2;
		}else {
			// not allowed
			result = 0;
		}*/
		
		switch(operation) {
		case "+": result = op1+op2; break;
		case "-": result = op1-op2; break;
		case "*": result = op1*op2; break;
		case "/": if(op2!=0) result = op1/op2; result = 0; break;
		}
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.println("il risultato di " + op1 + " " + operation + " " + op2 + " = " + result); 
		
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
