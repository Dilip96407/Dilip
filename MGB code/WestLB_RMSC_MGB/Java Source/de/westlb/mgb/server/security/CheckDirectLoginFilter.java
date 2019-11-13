package de.westlb.mgb.server.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import jcifs.http.NtlmHttpFilter;

import org.apache.log4j.Logger;

/**
 * @author WSY4148
 *
 * This class checks if the user has been authenticated
 * using the LoginServlet.
 * 
 */
public class CheckDirectLoginFilter implements Filter {
    private Logger logger = Logger.getLogger(this.getClass());
    private final static String wisHeader = "iv-user"; 
	
	private NtlmHttpFilter filter;

	public CheckDirectLoginFilter() {
		filter = new NtlmHttpFilter();
	}

//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        if (!(req instanceof HttpServletRequest)) {
//            logger.error("Request must be an HttpServletRequest");
//            return;
//        }
//        
//        HttpServletRequest httpReq = (HttpServletRequest) req;
//
//		logger.debug("filter request "+httpReq.getServletPath()+".");
//        if (httpReq.getSession().getAttribute("LoginContext") != null ) {
//        	LoginContext lc = (LoginContext)httpReq.getSession().getAttribute("LoginContext");
//    		logger.debug("LoginContext "+lc.getNtDomain()+lc.getNtUserId());
//        	if ("DIRECT_LOGIN".equals(lc.getNtDomain()) && lc.getNtUserId() != null) {
//        		logger.debug("direct logged in");
//                chain.doFilter(req, res);        		
//        	} else {
//        		logger.debug("ntlm1");
//    			filter.doFilter(req, res, chain);
//        	}
//        } else {
//    		logger.debug("ntlm2");
//			filter.doFilter(req, res, chain);
//        }
//    }

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

		logger.debug("filter request "+httpReq.getServletPath()+".");
		if (httpReq.getHeader(wisHeader) != null) {
			String userName = httpReq.getHeader(wisHeader);
    		logger.debug("direct login via WIS: " +userName);
	    	SecurityUtils.getLoginContext(httpReq, userName);
            chain.doFilter(req, res);        		
		} else if (httpReq.getSession().getAttribute("LoginContext") != null ) {
        	LoginContext lc = (LoginContext)httpReq.getSession().getAttribute("LoginContext");
        	String userName = lc.getNtDomain()+"/"+lc.getNtUserId();
        	if ("DIRECT_LOGIN".equals(lc.getNtDomain()) && lc.getNtUserId() != null) {
        		logger.debug("direct login: "+userName);
                chain.doFilter(req, res);        		
        	} else {
        		logger.debug("ntlm login: "+userName);
    			filter.doFilter(req, res, chain);
        	}
		} else {
    		logger.debug("ntlm login");
			filter.doFilter(req, res, chain);
		}
    }

	/**
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
    public void destroy() {
		filter.destroy();
	}
	
	/**
	 * @see jcifs.http.NtlmHttpFilter#getFilterConfig()
	 */
	public FilterConfig getFilterConfig() {
		return filter.getFilterConfig();
	}
	
	/**
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
    public void init(FilterConfig arg0) throws ServletException {
		filter.init(arg0);
	}

	/**
	 * @see jcifs.http.NtlmHttpFilter#setFilterConfig(javax.servlet.FilterConfig)
	 */
	public void setFilterConfig(FilterConfig arg0) {
		filter.setFilterConfig(arg0);
	}
}
