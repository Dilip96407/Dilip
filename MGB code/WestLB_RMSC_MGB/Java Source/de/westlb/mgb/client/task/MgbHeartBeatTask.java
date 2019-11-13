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
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import de.westlb.mgb.client.server.Mgb;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.MgbTaskVo;
import de.westlb.mgb.model.definition.MgbConfigurationDef;
import de.westlb.mgb.util.MgbSystemUtils;
import de.westlb_systems.xaf.util.PropertyFactory;

/**
 * @author d055625
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MgbHeartBeatTask extends MgbTask {

	static Logger logger = Logger.getLogger(MgbHeartBeatTask.class);	

	private static Mgb mgbService = null;

	public MgbHeartBeatTask() {
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

    public static void main(String args[]) {
    	String usage = "USAGE: MgbHeartBeatTask <user> <mandantCode> <url> [<taskId>]";
    	if (args == null || args.length < 2) {
    		logger.error("IllegalArgumentException ("+usage + ")");
    		throw new IllegalArgumentException(usage);
    	}
    	MgbHeartBeatTask task = new MgbHeartBeatTask();
    	try {
    		task.run(null, args[0], args[1], args[2], Long.valueOf(args[3]));
    	} catch (NumberFormatException nfe) {
    		logger.error("IllegalArgumentException ("+usage + ") ", nfe);
    		throw new IllegalArgumentException(usage);
    	} catch (JobExecutionException jee) {
    		logger.error("Error while executing MgbHeartBeatTask.", jee);
    		jee.printStackTrace();
    	}
    }


	
	@Override
    protected Long run(JobExecutionContext context, String user, String mandantCode, String urlString, Long taskId) throws JobExecutionException {
		Long newTaskId = taskId;
		String taskName = "task "+taskId+" (owned by user "+user+" ("+mandantCode+")";
		logger.debug("Running "+taskName);
		PropertyFactory.load("struts_client");
		
	    Mgb mgb = getService(urlString);
	    try {
			logger.debug("Logging in...");
	      	mgb.login(user, mandantCode, MgbSystemUtils.getSystemProperties());
	      	
	      	MgbTaskVo task = null;
	      	if (taskId != null && taskId.longValue() > 0) {
				logger.debug("Loading task configuration...");
	      		task = mgb.getMgbTask(taskId);
	      		// Check if a shutdown request is found in the database
	      		if (STATE_STOP.equals(task.getState())) {
			      	task.setStopTime(new GregorianCalendar());
			      	task.setState(STATE_STOPPED);
					logger.debug("Task shutdown requested by "+taskName);
					if (context != null) {
						logger.debug("Saving task...");
				      	newTaskId = mgb.saveMgbTask(task);
				      	removeFromConfigLists(context, mandantCode);
						return newTaskId;
					}
	      		}
	      		// Check if a the task will run on the allowed server
		      	try {
		      		String allowedClient = mgb.getMgbConfigurationValue(MgbConfigurationDef.AUTO_MGB_ALLOWED_CLIENT);
		            if (allowedClient == null) {
                        logger.info("Looking up the default allowed client");
		                allowedClient = mgb.getMgbConfigurationValue(MgbConfigurationDef.AUTO_MGB_DEFAULT_ALLOWED_CLIENT);
		            }
		      		if (!Inet4Address.getLocalHost().getHostName().equalsIgnoreCase(allowedClient)) {
		      			logger.error("Shutting down task due to unallowed host ("+Inet4Address.getLocalHost().getHostName()+" != "+allowedClient+")");
						if (context != null) {
					      	task.setStopTime(new GregorianCalendar());
					      	task.setState(STATE_CANCELLED);
							logger.debug("Saving task...");
					      	newTaskId = mgb.saveMgbTask(task);
					      	removeFromConfigLists(context, mandantCode);
					      	return newTaskId;
						}
		      		}
				} catch (UnknownHostException uhe) {
					logger.error("Error while detecting local host.",uhe);				
				}

				task.setMessage("HeartBeat at " + (new Date()).toString());
		      	
	      	} else {
				logger.debug("Creating new task...");
	      		task = new MgbTaskVo();
				task.setStartTime(new GregorianCalendar());
				task.setMandantCode(mandantCode);
				task.setClient(Inet4Address.getLocalHost().getHostName());
		      	task.setUser(System.getProperty("user.name"));
		      	task.setState(STATE_RUNNING);
		      	task.setTaskName("Mgb HeartBeat task");
		      	task.setThreadName(this.getClass().getName());
	      	}
			logger.debug("Saving task...");
	      	newTaskId = mgb.saveMgbTask(task);
			taskName = "task "+newTaskId+" (owned by user "+user+" ("+mandantCode+")";

	    } catch (Exception e) {
		      try {
				logger.error("Error while running "+taskName,e);
		      	if (taskId != null && taskId.longValue() > 0) {
		      		MgbTaskVo task = mgb.getMgbTask(taskId);
		      		task.setStopTime(new GregorianCalendar());
		      		task.setState(STATE_STOPPED_ERROR);
		      		String message = e.toString()+" ("+e.getMessage()+")";
		      		task.setMessage(message);
		      		mgb.saveMgbTask(task);
		      	}
		      } catch (RemoteException re) {
		      }
		      throw new JobExecutionException(e);	      	
	    }
		logger.debug("Finished "+taskName);
	    return newTaskId;
	}

	
	/* (non-Javadoc)
	 * @see de.westlb.mgb.client.task.MgbTask#removeFromConfigLists(org.quartz.JobExecutionContext, java.lang.String)
	 */
//	protected void removeFromConfigLists(JobExecutionContext context, String mandantCode) {
//		super.removeFromConfigLists(context, mandantCode);
//		try {
//			JobDetail autoCheckTaskDetail = context.getScheduler().getJobDetail(MgbAutoCheckTask.class.getName(),Scheduler.DEFAULT_GROUP);
//			ArrayList autoCheckMandantList = (ArrayList)autoCheckTaskDetail.getJobDataMap().get(MANDANT_CODE_LIST);
//			ArrayList autoCheckTaskIdList = (ArrayList)autoCheckTaskDetail.getJobDataMap().get(TASK_ID_LIST);
//		    if (autoCheckMandantList.contains(mandantCode)) {
//				logger.debug("Removing task for mandant "+mandantCode);
//		      	int index = autoCheckMandantList.indexOf(mandantCode);
//		      	autoCheckMandantList.remove(index);
//		      	autoCheckTaskIdList.remove(index);
//		      	autoCheckTaskDetail.getJobDataMap().put(MANDANT_CODE_LIST,autoCheckMandantList);
//		      	autoCheckTaskDetail.getJobDataMap().put(TASK_ID_LIST,autoCheckTaskIdList);
//		    }
//
//		} catch (SchedulerException se) {
//			logger.error("Error while removing the mandant "+mandantCode+" from the config.", se);
//		}
//	}
}
