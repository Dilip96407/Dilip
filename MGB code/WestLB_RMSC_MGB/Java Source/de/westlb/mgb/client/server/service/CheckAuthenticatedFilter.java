/*
 * Created on Oct 22, 2007
 *
 */
package de.westlb.mgb.client.server.service;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.log4j.helpers.ISO8601DateFormat;

import de.westlb.mgb.model.definition.RoleDef;
import de.westlb.mgb.server.security.LoginContext;

/**
 * @author boernema
 *
 * Checks if the user has passed the authentication procedure.
 * 
 */
public class CheckAuthenticatedFilter implements Filter { 
	private static Logger logger = Logger.getLogger(CheckAuthenticatedFilter.class);
    private ISO8601DateFormat format = new ISO8601DateFormat();

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
    public void init(FilterConfig pConfig) throws ServletException {
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
    public void doFilter(ServletRequest pRequest, ServletResponse pResponse, FilterChain pFilterChain) throws IOException, ServletException {
        if (!(pRequest instanceof HttpServletRequest)) {
            logger.error("Request must be an HttpServletRequest");
            return;
        }
        
        HttpServletRequest httpReq = (HttpServletRequest) pRequest;

        if (httpReq.getSession().getAttribute("LoginContext") == null && httpReq.getParameter("userId") != null && RoleDef.AUTO_CONTROLLER_ID.equalsIgnoreCase(httpReq.getParameter("userId"))) {
        	LoginContext lc = new LoginContext();
        	lc.setErrorText("");
        	lc.setNtUserId(RoleDef.AUTO_CONTROLLER_ID);
        	httpReq.getSession().setAttribute("LoginContext", lc);
        	logger.info(format.format(new Date()) + " Logging in " + RoleDef.AUTO_CONTROLLER_ID + " as " + RoleDef.AUTO_CONTROLLER);
        }
        
        if (httpReq.getSession().getAttribute("LoginContext") == null) {
			throw new ServletException("User is no longer logged in - user is not associated to the examined session!");        
		}
        pFilterChain.doFilter(pRequest, pResponse);	
    }
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
    public void destroy() {
	}

}
