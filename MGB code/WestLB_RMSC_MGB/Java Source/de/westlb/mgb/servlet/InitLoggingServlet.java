package de.westlb.mgb.servlet;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.StoreSingleton;

public class InitLoggingServlet extends HttpServlet {
	/**
     * 
     */
    private static final long serialVersionUID = 2859540592843660002L;
	/* 
	 * Initializes the log4j Logging for the MGB application.
	 * 
	 * @see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
	 */
	@Override
    public void init(ServletConfig arg0) throws ServletException {
		super.init(arg0);
		String prefix = getServletContext().getRealPath("/");
		String logFileName = getInitParameter("log4j_properties");
		if (logFileName == null) {
			logFileName = "log4j.properties";
		}
		// the configureAndWatch method creates a thread that can not be stopped properly. 
		// this might be introduced in later versions of log4j
		PropertyConfigurator.configure(prefix +logFileName);
		System.out.println("Using logging config "+logFileName);
	}

	   @Override
	    public void destroy() {
	       super.destroy();
	       try {
	           StoreSingleton.close();
	           Thread.sleep(5000l);
	           Enumeration<Driver> drivers = DriverManager.getDrivers();
	           while (drivers.hasMoreElements()) {
	               DriverManager.deregisterDriver(drivers.nextElement());
	           }
	       } catch (PersistenceException e) {
	           e.printStackTrace();
	       } catch (InterruptedException e) {
	           e.printStackTrace();
	       } catch (SQLException e) {
            e.printStackTrace();
        }

//	       log.debug("The logger is now shutdown. Later warnings like 'WARN No appenders could be found for logger' can be ignored.");        
//	       LogManager.shutdown();
	   }

}
