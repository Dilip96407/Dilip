package de.westlb.mgb.server.security;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import de.westlb.mgb.client.applet.AppletParameterDef;


/**
 * @version         1.0
 * @author
 */
public class NtlmLoginServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8876796074062237000L;
	private static final Logger log = Logger.getLogger(NtlmLoginServlet.class);

    /**
    * @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
    * This method should only be called for the initial request
    */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        performTask(req, resp);
    }

    /**
    * @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
    */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        performTask(req, resp);
    }

    /**
    */
    private void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginContext lc = null;
		try {
			lc = SecurityUtils.checkAuthenticationThrowsException(req, resp);
		} catch (AuthorizationException e) {
			resp.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		} catch (AuthenticationException e) {
			resp.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
        
		if (lc != null) {
			// After NTLM authentication IE does not send form post data
			// until it recalculated the password hash.
			// We can break authentication sending a 401 error code
			// 401 requires to send a WWW-Authenticate header as well.
			// After this sending post data works fine. (RA)			
			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			resp.addHeader("WWW-Authenticate", "None");
			
			URL url = new URL(req.getScheme(), req.getServerName(), req.getServerPort(), "");
			
			HttpSession session = req.getSession();
	        session.setAttribute(AppletParameterDef.APPLET_PARAM_USER_ID, lc.getNtUserId());
	        session.setAttribute(AppletParameterDef.APPLET_PARAM_KEY, lc.getSessionKey());
	        session.setAttribute(AppletParameterDef.APPLET_PARAM_URL, url.toString());
	
			String startUpJsp = SecurityUtils.getSsoProperty("startupView");
			log.debug("Forwarding request to: " + startUpJsp);
			req.getRequestDispatcher(startUpJsp).forward(req, resp);
		}
    }



   
}
