/*
 * Created on Jan 31, 2006
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.client.ui.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import de.westlb.mgb.client.server.Mgb;
import de.westlb.mgb.client.server.service.MgbServiceImplProxy;
import de.westlb.mgb.server.security.LoginContext;
import de.westlb.mgb.server.security.SecurityUtils;

/**
 * @author d055625
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DummyUserIdFilter implements Filter {
	private static Mgb service = null;
	private static Logger logger = Logger.getLogger(DummyUserIdFilter.class);

//	public final static String DUMMY_USER = "GSA-WLB\\wsy4148";
	public final static String DUMMY_USER = "GSA-WLB\\fuhsp";
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
    public void destroy() {
	
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(request) {
			@Override
            public String getRemoteUser() {
				return DUMMY_USER;	
			}
		};
		
		try {
			LoginContext lc = SecurityUtils.checkAuthenticationThrowsException(wrapper, response);
			request.getSession().setAttribute("LoginContext", lc);
			if (service == null) {
				service = MgbServiceImplProxy.getProxy(false);
			}
			request.getSession().setAttribute("mgb_service", service);
		} catch (de.westlb.mgb.server.security.SecurityException  e) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		
		logger.debug("Request "+req.toString()+" passed.");
		chain.doFilter(wrapper, res);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
    public void init(FilterConfig arg0) throws ServletException {
	}

}
