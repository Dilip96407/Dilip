
package de.westlb.mgb.server.statistic;

import java.io.Serializable;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;


public class SessionHolder implements Serializable,HttpSessionBindingListener {
	private static final long serialVersionUID = 1L;
    private long creationTime;
	private String sessionId;
	private transient ServletContext context;

    public SessionHolder(ServletContext servletcontext){
        context = servletcontext;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent)
    {
        creationTime = System.currentTimeMillis();
        sessionId = httpSessionBindingEvent.getSession().getId();
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent)
    {
        /* Accessing the application statistics through the ServletContext
         * when there's also a Singleton accessor seems strange and also doesn't
         * help a bit across Tomcat restarts, as the ServletContext isn't
         * persisted. I won't change that for now, but after Tomcat is restarted,
         * any sessions loaded from persistent storage will NOT be shown
         * until they have timed-out and are opened anew. To fix that we'd
         * have to persist the application statistics ourselves (for example,
         * keep them in the database from the start). But that's not really
         * a high priority as we have the keepAlive log on the server
         * anyway. -- RS 2015-06-24 */
        long elapsedTime = (System.currentTimeMillis() - creationTime) / 1000L;
        ApplicationStatisticHolder statholder;
        if(context!=null&&(statholder = (ApplicationStatisticHolder)context.getAttribute("STATISTIC_HOLDER")) != null)
            try {
            	statholder.closeSession(sessionId, elapsedTime);
            } catch (Throwable e) {
            	e.printStackTrace();
            }
    }

}
