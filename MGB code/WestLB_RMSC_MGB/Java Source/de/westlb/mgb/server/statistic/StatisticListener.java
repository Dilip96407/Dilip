
package de.westlb.mgb.server.statistic;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class StatisticListener implements HttpSessionListener
{
    public StatisticListener()
    {
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent)
    {
		HttpSession httpSession = httpSessionEvent.getSession();
		ServletContext servletcontext = httpSession.getServletContext();
		ApplicationStatisticHolder statHolder = (ApplicationStatisticHolder)servletcontext.getAttribute("STATISTIC_HOLDER");
		if (statHolder == null) {
			statHolder = ApplicationStatisticHolder.getInstance();
			servletcontext.setAttribute("STATISTIC_HOLDER",  statHolder);
		}
		
		// Use time holder to call closeSession, because the session is already invalid when
		// sessionDestroyed is called. 
		httpSession.setAttribute("SESSION_HOLDER", new SessionHolder(httpSession.getServletContext()));
		statHolder.openSession(httpSession);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) { 
    }
    
}
