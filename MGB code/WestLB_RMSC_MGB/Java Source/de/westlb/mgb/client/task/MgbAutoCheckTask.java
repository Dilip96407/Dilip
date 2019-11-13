/*
 * Created on Apr 4, 2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.client.task;

import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import de.westlb.mgb.client.application.KeepAliveThread;
import de.westlb.mgb.client.mask.model.shared.PriceFetcherModel;
import de.westlb.mgb.client.mask.view.shared.ProgressDialogDummyImpl;
import de.westlb.mgb.client.server.Mgb;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.DataSelectionVo;
import de.westlb.mgb.client.server.vo.JobVo;
import de.westlb.mgb.client.server.vo.MgbTaskVo;
import de.westlb.mgb.model.definition.MgbConfigurationDef;
import de.westlb.mgb.model.impl.BloombergRequestImpl;
import de.westlb.mgb.util.MgbSystemUtils;
import de.westlb_systems.xaf.util.PropertyFactory;

/**
 * @author d055625
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MgbAutoCheckTask extends MgbTask {

	static Logger logger = Logger.getLogger(MgbAutoCheckTask.class);

	private static Mgb mgbService = null;

	public MgbAutoCheckTask() {
	}

	private static Mgb getService(String urlString) throws JobExecutionException {
    	if (mgbService == null) {
    		try {
    			MgbServiceFactory.setUrlEndPoint(new URL(urlString));
    			mgbService = MgbServiceFactory.createService();
    		} catch (MalformedURLException mfue) {
    			throw new JobExecutionException(mfue);
    		}
    	}
    	return mgbService;
    }

	public static Mgb getService() {
    	if (mgbService == null) {
   			logger.error("First set the UrlEndPoint");;
    	}
    	return mgbService;
    }

    public static void main(String args[]) {
    	String usage = "USAGE: MgbAutoCheckTask <user> <mandantCode> <url> [<taskId>]";
    	if (args == null || args.length < 2) {
    		logger.error("IllegalArgumentException ("+usage + ")");
    		throw new IllegalArgumentException(usage);
    	}
    	MgbAutoCheckTask task = new MgbAutoCheckTask();
    	try {
    		task.run(null, args[0], args[1], args[2], Long.valueOf(args[3]));
    	} catch (NumberFormatException nfe) {
    		logger.error("IllegalArgumentException ("+usage + ") ", nfe);
    		throw new IllegalArgumentException(usage);
    	} catch (JobExecutionException jee) {
    		logger.error("Error while executing MgbAutoCheckTask.", jee);
    		jee.printStackTrace();
    	}
    }

	
	@Override
    protected Long run(JobExecutionContext context, String user, String mandantCode, String urlString, Long taskId) throws JobExecutionException {
		KeepAliveThread keepAliveThread = null;
		Long resultZeroId = Long.valueOf(0); // A task is not run twice, to the id is not used outside
		Long newTaskId = taskId;
		String taskName = "task "+taskId+" (owned by user "+user+" ("+mandantCode+")";
		logger.debug("Running "+taskName);

		PropertyFactory.load("struts_client");
		
	    Mgb mgb = getService(urlString);
	    try {
			logger.debug("Loading configuration...");
	      	mgb.login(user, mandantCode, MgbSystemUtils.getSystemProperties());

      		keepAliveThread = new KeepAliveThread();
    		logger.debug("Started keepAliveThread for "+mandantCode);
    		keepAliveThread.setKeepAliveIntervall(30*1000);
      		keepAliveThread.start();
	      	
	      	String proxyDir = mgb.getMgbConfigurationValue(MgbConfigurationDef.MARKET_DATA_PROXY_DIRECTORY);
			mgb.setMarketDataProxyDirectory(proxyDir);
	      	
	      	JobVo[] jobs = mgb.getRecentJobs(mandantCode);
	      	
	      	MgbTaskVo task = null;
	      	if (taskId != null && taskId.longValue() > 0) {
	      		task = mgb.getMgbTask(taskId);
	      	} else {
	      		task = new MgbTaskVo();
				task.setStartTime(new GregorianCalendar());
				task.setMandantCode(mandantCode);
				task.setClient(Inet4Address.getLocalHost().getHostName());
		      	task.setThreadName(this.getClass().getName());
	      	}
      		task.setUser(System.getProperty("user.name"));
	      	task.setState(STATE_RUNNING);
	      	String jobsOfTasks = "Automatic Check for job(s)";
	      	for (int i = 0; i < jobs.length; i++) {
	      		jobsOfTasks = jobsOfTasks + " " + jobs[i].getJobId();
			}
	      	task.setTaskName(jobsOfTasks);
			logger.debug(jobsOfTasks);
 
      		// Check if a the task will run on the allowed server
			String allowedClient = mgb.getMgbConfigurationValue(MgbConfigurationDef.AUTO_MGB_ALLOWED_CLIENT);
			if (allowedClient == null) {
                logger.info("Looking up the default allowed client");
			    allowedClient = mgb.getMgbConfigurationValue(MgbConfigurationDef.AUTO_MGB_DEFAULT_ALLOWED_CLIENT);
			}
      		if (!Inet4Address.getLocalHost().getHostName().equalsIgnoreCase(allowedClient)) {
      			logger.error("Shutting down task due to unallowed host ("+Inet4Address.getLocalHost().getHostName()+" != "+allowedClient+")");
				if (context != null) {
			      	task.setState(STATE_CANCELLED);
			      	task.setStopTime(new GregorianCalendar());
					logger.debug("Saving task...");
			      	newTaskId = mgb.saveMgbTask(task);
					removeFromConfigLists(context, mandantCode);
					return resultZeroId;
				}
      		}

			logger.debug("Saving task...");
	      	newTaskId = mgb.saveMgbTask(task);
	      	task.setTaskId(newTaskId);
			taskName = "task "+newTaskId+" (owned by user "+user+" ("+mandantCode+")";
	      	
			// prepare the session by setting the job selection
	      	DataSelectionVo selection = new DataSelectionVo();
	      	selection.setMandantCode(mandantCode);
	      	selection.setSelectedJobs(jobs);
	      	mgb.setDataSelection(selection);
	      	
	      	String dir = mgb.getMgbConfigurationValue(MgbConfigurationDef.MARKET_DATA_PROXY_DIRECTORY);
	      	mgb.setMarketDataProxyDirectory(dir);
			logger.debug("Loaded configuration successfull");
	
			// run the price check
	      	PriceFetcherModel model = new PriceFetcherModel(false);
	      	model.setProgress(new ProgressDialogDummyImpl());
	      	boolean success = false;
	      	success = model.fetchBloombergPrices();
	      	if (!success) {
	      		success = model.checkInconsistantRequests(BloombergRequestImpl.class.getName());
	      	}
	      	if (success) {
		      	task.setState(STATE_STOPPED_OK);
		      	task.setStopTime(new GregorianCalendar());
				logger.debug("Saving task...");
		      	mgb.saveMgbTask(task);
				logger.info("Finished "+taskName);
	      	} else {
	      		throw new RemoteException("The price fetching was not successful.");
	      	}

	    } catch (Exception e) {
		      try {
				logger.error("Error while running "+taskName,e);
				if (newTaskId != null && taskId.longValue() > 0) {
					MgbTaskVo task = mgb.getMgbTask(newTaskId);
					task.setState(STATE_STOPPED_ERROR);
					task.setStopTime(new GregorianCalendar());
					String message = e.toString()+" ("+e.getMessage()+")";
					task.setMessage(message);
					logger.debug("Saving task...");
					mgb.saveMgbTask(task);
				}
		      } catch (RemoteException re) {
				logger.error("Unable to write error to database: "+re.getMessage());
		      }
		      throw new JobExecutionException(e);	      	
	    } finally {
	    	if (keepAliveThread != null) {
	    		keepAliveThread.interrupt();
	    	}	    	
	    }
	    return resultZeroId;
	}

}
