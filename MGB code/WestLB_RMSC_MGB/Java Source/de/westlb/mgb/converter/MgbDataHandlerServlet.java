package de.westlb.mgb.converter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import de.westlb.mgb.model.definition.ImportPropertiesDef;
import de.westlb.mgb.model.impl.SourceSystemImpl;
import de.westlb.mgb.persistence.Session;
import de.westlb.mgb.persistence.StoreSingleton;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MgbDataHandlerServlet extends HttpServlet implements ImportPropertiesDef {

	private static final long serialVersionUID = 506703452920644711L;

	private static final Logger log = Logger.getLogger(MgbDataHandlerServlet.class);

    public static final String IMPORT_THREAD_STATUS_ARRAY = "threadStatusArray";

	private MgbImportThread[] mgbImportThreads = null;

	private Properties importProperties;
	private Properties hibernateProperties;

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
	 * 
	*/
	private void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String parameter = req.getParameter("action");
		if (parameter != null) {
			try {
    			if ("stop".equalsIgnoreCase(parameter)) {
    				stopThreads();
    			} else if ("restart".equalsIgnoreCase(parameter)) {
    				stopThreads();
    				/* Reload properties too, so that it is possible to change
    				 * file name patterns with the restart action */
    				readImportProperties();
    				initializeThreads();
    				startThreads();
    			}
			} catch (Exception e){
				log.error("error while stopping/starting a thread",e);
				throw new ServletException(e);
			}
		}
		MgbImportThreadStatus[] threadStatusArray = getThreadStatus();
        req.setAttribute(IMPORT_THREAD_STATUS_ARRAY,threadStatusArray);
		RequestDispatcher disp = req.getRequestDispatcher("status.jsp");
		disp.forward(req, resp);
	}

	/**
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
    public void init() throws ServletException {
		super.init();
		try {
		    readImportProperties();

			String hibernatePropertiesName = getParameterFromProperties(importProperties, HIBERNATE_PROPERTIES);
			hibernateProperties = readProperties(hibernatePropertiesName);

			initializeThreads();
			startThreads();

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}

	}
	
	protected void readImportProperties()
	throws ServletException,IOException
	{
        String importPropertiesName = getInitParameter(PARAM_IMPORT_PROPERTIES);
        log.debug("Param " + PARAM_IMPORT_PROPERTIES + " = " + importPropertiesName);
        if (importPropertiesName == null) {
            String msg = "could not resolve init parameter " + PARAM_IMPORT_PROPERTIES;
            log.fatal(msg);
            throw new ServletException(msg);
        }
        importProperties = readProperties(importPropertiesName);
	}

	private String getParameterFromProperties(Properties properties, String parameter) throws ServletException {
		String propertiesvalue = properties.getProperty(parameter);
		log.debug("Param " + parameter + " = " + propertiesvalue);
		if (propertiesvalue == null) {
			String msg = "could not resolve parameter " + parameter;
			log.fatal(msg);
			throw new ServletException(msg);
		}
		return propertiesvalue;
	}

	private Properties readProperties(String propertiesName) throws IOException {
	    InputStream in = null;
		if (propertiesName != null) {
		    try {
		        Properties result = new Properties();
		        in = getClass().getResourceAsStream("/" + propertiesName);
		        result.load(in);
		        return result;
		    } finally {
		        if (in != null) {
		            in.close();
		        }
		    }
		}
        throw new IOException("Can not load properties from an empty propertiesname");
	}

	private void initializeThreads() throws Exception {
        log.debug("initialize threads ");
		Session sess = StoreSingleton.getUniqueInstance().openBatchSession();
		List<SourceSystemImpl> sourceSystems = (List<SourceSystemImpl>) sess.select(SourceSystemImpl.class);
		ArrayList<MgbImportThread> list = new ArrayList<MgbImportThread>();
		for (SourceSystemImpl sourceSystem : sourceSystems) {
			if (sourceSystem.getEnabled())
			{
				log.debug("initialize " + sourceSystem.getCode());
				try {
					MgbImportThread thread = new MgbImportThread(importProperties, hibernateProperties, sourceSystem.getCode());
					list.add(thread);
					log.debug("Initialized thread " + thread.getName());
				} catch (Exception e) {
					log.debug("Could not initialize a thread for system " + sourceSystem.getCode(), e);
				}				
			}
			else {
				log.debug("disabled " + sourceSystem.getCode());
			}
		}
		mgbImportThreads = list.toArray(new MgbImportThread[0]);
	}

	private void startThreads() {
		if (mgbImportThreads != null) {
			for (int i = 0; i < mgbImportThreads.length; i++) {
				if (!mgbImportThreads[i].isAlive()) {
					log.debug("Starting thread " + mgbImportThreads[i].getName());
					mgbImportThreads[i].start();
				}
			}
		}
	}

	private int getNumberOfRunningThreads() {
	    int result = 0;
        if (mgbImportThreads != null) {
            for (int i = 0; i < mgbImportThreads.length; i++) {
                if (mgbImportThreads[i].getStatus().isRunning()) {
                    result++;
                }
            }
        }
        return result;
	}

	private MgbImportThreadStatus[] getThreadStatus() {
		if (mgbImportThreads != null) {
			MgbImportThreadStatus[] result = new MgbImportThreadStatus[mgbImportThreads.length];
			for (int i = 0; i < mgbImportThreads.length; i++) {
				result[i] = mgbImportThreads[i].getStatus();
			}
			return result;
		}
        return null;
	}

	private void stopThreads() {
		if (mgbImportThreads != null) {
			for (int i = 0; i < mgbImportThreads.length; i++) {
				if (mgbImportThreads[i].isAlive()) {
					log.debug("Stopping thread " + mgbImportThreads[i].getName());
					try {
						mgbImportThreads[i].setStopRequested(true);
						/* Set interrupted flag because the threads likely
						 * hang in a sleep() */
						mgbImportThreads[i].interrupt();
					} catch (SecurityException se) {
						log.error("Error while interupting " + mgbImportThreads[i].getName(), se);
					}						
				}
			}
		}
	}

	/**
	 * @see javax.servlet.Servlet#destroy()
	 */
	@Override
    public void destroy() {
        log.debug("Destroying servlet...");	    
		super.destroy();
		stopThreads();
		int maxRetry = 20;
		long waitOffset = 10000l;
		long waitInterval = (MgbImportThread.POLLING_INTERVAL+waitOffset)/20;
		
		int numberOfRunningThreads = getNumberOfRunningThreads();
		for (int retry = 0; retry < maxRetry && numberOfRunningThreads != 0; retry++) {
	        log.debug("Waiting for "+numberOfRunningThreads+" threads to finish...");        
		    try {
		        Thread.sleep(waitInterval);
		    } catch (InterruptedException e) {
		    }
            numberOfRunningThreads = getNumberOfRunningThreads();
		}
        log.debug("Destroyed servlet");        
	}
}