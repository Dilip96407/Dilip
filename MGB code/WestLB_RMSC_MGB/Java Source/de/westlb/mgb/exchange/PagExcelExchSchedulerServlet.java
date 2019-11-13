package de.westlb.mgb.exchange;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;

import de.westlb.mgb.exchange.MgbExcelPagSchedulerPropertiesDef;

public class PagExcelExchSchedulerServlet extends HttpServlet implements MgbExcelPagSchedulerPropertiesDef {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(PagExcelExchSchedulerServlet.class);

	private Scheduler sched = null;

	/**
     * @see javax.servlet.http.HttpServlet#void
     *      (javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse) This method should only be
     *      called for the initial request
     */
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	/**
     * @see javax.servlet.http.HttpServlet#void
     *      (javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	private void startScheduler() throws SchedulerException {
	    log.info("Starting PAG scheduler...");
		SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
	    sched = schedFact.getScheduler();
	    sched.start();
	    log.info("Started PAG scheduler.");
	}
	
	/*
	 * 
	 */
	private void initSchedulerJobs(Properties properties) throws Exception {
		Iterator<Object> iterator = properties.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			if (key.startsWith("job.key") && key.length() > 8) {
				initJob(key.substring(8), properties);
			}
		}
	}
	
	private void initJob(String jobKey, Properties properties) throws Exception {
	    log.info("Initialize job " + jobKey);
	    
	    String prefix = "job." + jobKey;
		String startValue = (String)properties.get(prefix + ".start");
		log.debug(prefix + ".start="+startValue);
		if (!"true".equals(startValue)) {
			log.info("Job " + jobKey + " is not configured to run!");
			return;
		}
		String cronValue = (String)properties.get(prefix + ".cron");
        log.debug(prefix + ".cron="+cronValue);
		if (StringUtils.isEmpty(cronValue)) {
			log.error("Missing property " + prefix + ".cron");
			return;
		}
		String className = (String)properties.get(prefix + ".class");
        log.debug(prefix + ".class="+className);
		if (StringUtils.isEmpty(className)) {
			log.error("Missing property " + prefix + ".class");
			return;
		}
		
		JobDataMap jobDataMap = new JobDataMap();
		Iterator<Object> iterator = properties.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			if (key.startsWith(prefix + ".param.") && key.length() > prefix.length() + 7) {
				String paramName = key.substring(prefix.length() + 7);
				String paramValue = properties.getProperty(key);
		        log.debug(paramName + "="+paramValue);
				jobDataMap.put(paramName, paramValue);
			}
		}
		
		Class<?> clazz = Class.forName(className);
	    JobDetail jobDetail = new JobDetail(jobKey, Scheduler.DEFAULT_GROUP, clazz);
	    jobDetail.setJobDataMap(jobDataMap);
	    CronTrigger trigger = new CronTrigger(jobKey, Scheduler.DEFAULT_GROUP, cronValue); 
	    sched.scheduleJob(jobDetail, trigger);
	    
	    log.info("Initialization of job " + jobKey + " finished");
	}
	

	/**
     * @see javax.servlet.GenericServlet#init()
     */
	@Override
    public void init() throws ServletException {
		super.init();
		try {
		    log.info("Initialising PAG scheduler...");
		    
		    if (!("true".equals(getInitParameter("start")))) {
			    log.info("Scheduler is not intended to run");
		    	return;
		    }

			String schedulerPropertiesName = getInitParameter(PARAM_PAG_SCHEDULER_PROPERTIES);
			log.debug("Param " + PARAM_PAG_SCHEDULER_PROPERTIES + " = " + schedulerPropertiesName);
			if (schedulerPropertiesName == null) {
				String msg = "Could not resolve init parameter " + PARAM_PAG_SCHEDULER_PROPERTIES;
				log.fatal(msg);
				throw new ServletException(msg);
			}
			Properties schedulerProperties = readProperties(schedulerPropertiesName);
			startScheduler();
			initSchedulerJobs(schedulerProperties);
		    log.info("Initialised scheduler.");
		} catch (SchedulerException e) {
			log.error("Error while initialising Scheduler.", e);
			throw new ServletException(e);
		} catch (ParseException e) {
			log.error("Error while initialising Scheduler.", e);
		    throw new ServletException(e);
		} catch (IOException e) {
			log.error("Error while initialising Scheduler.", e);
		    throw new ServletException(e);
		} catch (Exception e) {
			log.error("Error while initialising Scheduler.", e);
		    throw new ServletException(e);
		}
	}


	/**
     * @see javax.servlet.Servlet#destroy()
     */
	@Override
    public void destroy() {
        log.debug("Destroying servlet...");        
		super.destroy();
		try {
		    if (sched != null && !sched.isShutdown()) {
		        sched.shutdown();
		    }
	        for (int i = 0; i < 3 && !sched.isShutdown(); i++) {
	            log.debug("Waiting for scheduler to be shutdown...");        
	            Thread.sleep(1000l);
	        }
		} catch (SchedulerException se) {
		    log.error("Error while shutting down the scheduler.", se);
        } catch (InterruptedException e) {
            log.error("Error while shutting down the scheduler.", e);
        }
        log.debug("Destroyed servlet");        
	}

	private Properties readProperties(String propertiesName) throws IOException {
		if (propertiesName != null) {
			Properties result = new Properties();
			InputStream in = getClass().getResourceAsStream("/" + propertiesName);
			result.load(in);
			return result;
		}
        throw new IOException("Can not load properties from an empty propertiesname");
	}

}
