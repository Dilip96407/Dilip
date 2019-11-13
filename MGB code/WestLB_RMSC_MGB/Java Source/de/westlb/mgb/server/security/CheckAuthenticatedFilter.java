package de.westlb.mgb.server.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.helpers.ISO8601DateFormat;

import de.westlb.mgb.model.definition.RoleDef;

/**
 * @author WSY4148
 *
 * This class checks if the user has been authenticated
 * using the LoginServlet.
 * 
 */
public class CheckAuthenticatedFilter implements Filter {
    private Logger logger = Logger.getLogger(this.getClass());
    private ISO8601DateFormat format = new ISO8601DateFormat();

    /**
     * @see javax.servlet.Filter#init(FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * @see javax.servlet.Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (!(req instanceof HttpServletRequest)) {
            logger.error("Request must be an HttpServletRequest");
            return;
        }
        
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpRes = (HttpServletResponse) res;

        if (httpReq.getSession().getAttribute("LoginContext") == null && httpReq.getParameter("userId") != null && RoleDef.AUTO_CONTROLLER_ID.equalsIgnoreCase(httpReq.getParameter("userId"))) {
        	LoginContext lc = new LoginContext();
        	lc.setErrorText("");
        	lc.setNtUserId(RoleDef.AUTO_CONTROLLER_ID);
        	httpReq.getSession().setAttribute("LoginContext", lc);
//        	System.out.println(format.format(new Date()) + " Logging in "+RoleDef.AUTO_CONTROLLER_ID+" as "+RoleDef.AUTO_CONTROLLER);
        	logger.info(format.format(new Date()) + " Logging in "+RoleDef.AUTO_CONTROLLER_ID+" as "+RoleDef.AUTO_CONTROLLER);
        }
        
        // User is not authenticated. Redirect user to LoginServlet
        if (httpReq.getSession().getAttribute("LoginContext") == null) {
        	// String loginJsp = "client/startApplet.jsp";
            // httpReq.getRequestDispatcher("login").forward(req, res);
        	// ((HttpServletResponse) res).sendRedirect(loginJsp);
			generateSOAPException(httpReq, httpRes,	
				"NotAuthorized",
				"User is no longer logged in - user is not associated to the examined session!");
        } else {
            chain.doFilter(req, res);
        }
    }

	/**
	 * Method generateSOAPException genarates a SOAP error formatted 
	 * response message an writes it to the client.
	 * @param request the http request object
	 * @param response the http response object for this request
	 * @param faultcode the code of the error
	 * @param faultstring the corresponding message of the error
	 * @throws IOException in case of an io exception
	 */
	private void generateSOAPException(
		HttpServletRequest request,
		HttpServletResponse response,
		String faultcode,
		String faultstring)
		throws IOException {

		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		out.println("<?xml version='1.0' encoding='UTF-8'?>");
		out.println("<SOAP-ENV:Envelope "
					+ "xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" "
					+ "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
					+ "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">");
		out.println("  <SOAP-ENV:Body>");
		out.println("    <SOAP-ENV:Fault>");
		out.println("      <faultcode>" + faultcode + "</faultcode>");
		out.println("      <faultstring>" + faultstring + "</faultstring>");
		out.println("      <faultactor>/activitylist" + request.getServletPath() + "</faultactor>");
		out.println("    </SOAP-ENV:Fault>");
		out.println("  </SOAP-ENV:Body>");
		out.println("</SOAP-ENV:Envelope>");
		out.flush();
		out.close();
		return;
	}
    
    /**
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
    }
}
