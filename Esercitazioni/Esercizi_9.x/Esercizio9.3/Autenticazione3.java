package unisannio.crovella.es9;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class Autenticazione3
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
					, urlPatterns = { "/protected/*" })
public class Autenticazione3 implements Filter {

    /**
     * Default constructor. 
     */
    public Autenticazione3() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		Cookie[] co = req.getCookies();
		
		boolean autentificazione = false;
		
		if(co != null)
			for(int i=0 ;i<co.length && !autentificazione;i++)
				autentificazione = co[i].getName().equals("Token")&& co[i].getValue().equals("0298871665");
		
		if(autentificazione)
			chain.doFilter(request, response);
		else {
			response.setContentType("text/plain");
			PrintWriter pw = response.getWriter();
			pw.print("non sei autentificato");
	
			}
		}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
