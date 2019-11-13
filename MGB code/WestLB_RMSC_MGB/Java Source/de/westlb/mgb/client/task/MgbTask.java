/*
 * Created on Sep 16, 2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.client.task;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

/**
 * @author d055625
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public abstract class MgbTask implements Job, MgbTaskDef {

	protected static final Logger logger = Logger.getLogger(MgbTask.class);

    protected ArrayList<String> mandantList;
    protected ArrayList<Long> taskIdList;

	/**
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
	      JobDataMap dataMap = context.getJobDetail().getJobDataMap();
	      if (dataMap == null || 
				dataMap.get(USER_ID) == null || 
				dataMap.get(URL) == null ) {
	      	String message = "The JobDataMap must contain values for the keys '"+USER_ID+"' and '"+URL+"'.";
    		logger.error("IllegalArgumentException ("+message + ")");
	      	throw new JobExecutionException(message);
	      }
	      String userId = dataMap.getString(USER_ID);
	      String urlString = dataMap.getString(URL);
	      mandantList = (ArrayList<String>)dataMap.get(MANDANT_CODE_LIST);
	      taskIdList = (ArrayList<Long>)dataMap.get(TASK_ID_LIST);

	      int i = 0;
	      int size = mandantList.size();
	      while (i < size) {
	  	      String mandantCode = mandantList.get(i);
	  	      Long taskId = taskIdList.get(i);
	  	      if (taskId.longValue() == 0) {
	  	      	taskId = run(context, userId, mandantCode, urlString);
	  	      	taskIdList.set(i, taskId);
	  	      } else {
	  	      	run(context, userId, mandantCode, urlString, taskId);
	  	      }
	  	      if (size == mandantList.size()) {
	  	      	// Array has not been shrinked
	  	      	i++;
	  	      }
		      size = mandantList.size();
	      }
	      context.getJobDetail().getJobDataMap().put(MANDANT_CODE_LIST, mandantList);
	      context.getJobDetail().getJobDataMap().put(TASK_ID_LIST, taskIdList);
	      
	      try {
	      	if (size == 0) {
	      		context.getScheduler().shutdown(false);	      	
	      	}
	      } catch(SchedulerException se){
	      	throw new JobExecutionException(se);
	      }
	}

	protected void removeFromConfigLists(JobExecutionContext context, String mandantCode) {
	    if (mandantList.contains(mandantCode)) {
			logger.debug("Removing task for mandant "+mandantCode);
	      	int index = mandantList.indexOf(mandantCode);
	      	mandantList.remove(index);
	      	taskIdList.remove(index);
	    }
	}

	protected Long run(JobExecutionContext context, String user, String mandantCode, String urlString) throws JobExecutionException {
		return run(context, user, mandantCode, urlString, null);
	}

	
	protected abstract Long run(JobExecutionContext context, String user, String mandantCode, String urlString, Long taskId) throws JobExecutionException;

}
