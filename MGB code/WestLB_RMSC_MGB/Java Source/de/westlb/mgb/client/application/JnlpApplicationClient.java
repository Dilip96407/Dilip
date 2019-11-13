package de.westlb.mgb.client.application;

import java.io.InputStream;
import java.net.Inet4Address;
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;

import de.westlb.mgb.client.applet.AppletParameterDef;
import de.westlb.mgb.client.server.Mgb;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.MandantVo;
import de.westlb.mgb.client.server.vo.MgbTaskVo;
import de.westlb.mgb.client.task.MgbAutoCheckTask;
import de.westlb.mgb.client.task.MgbHeartBeatTask;
import de.westlb.mgb.client.task.MgbTaskDef;
import de.westlb.mgb.model.definition.MgbConfigurationDef;
import de.westlb.mgb.util.MgbSystemUtils;
import de.westlb_systems.xaf.util.PropertyFactory;
import de.westlb_systems.xaf.util.SLocale;

/**
 * Insert the class' description here.
 * 
 * @author: Manfred Boerner
 *  
 */
public class JnlpApplicationClient {

	static Logger logger = Logger.getLogger(JnlpApplicationClient.class);

	private String urlString = null;
	
	private String defaultCron = null;
	
	private String defaultClient = null;

	private static Mgb mgbService = null;

	/**
	 * After sucessfull initialization, it starts the tasks following these rules:
     * Based on the list of mandant codes, it takes the first mandant and looks for default values
     * using the keys AUTO_MGB_DEFAULT_CRON and AUTO_MGB_DEFAULT_ALLOWED_CLIENT.
     * For each mandant it looks for an AUTO_MGB_ALLOWED_CLIENT entry, which overwrites the value for this mandant.
     * Then it is checked if the local host equals the allowed client. If this is true,
     * the AUTO_MGB_CRON entry is looked up.
     * If at the end no cron entry was found for this client, the default value is taken.
     * At the end the tasks are started.
     * 
	 */
	public JnlpApplicationClient(String serverName, String userId, String mandantCode) {
		try {
			urlString = serverName + "?"+AppletParameterDef.APPLET_PARAM_USER_ID+"=" + userId;
			showStatus("Setting UrlEndPoint: " + urlString);
			MgbServiceFactory.setUrlEndPoint(new URL(urlString));
		} catch (Exception ex) {
			showErrorStatus("Unable to initialize UrlEndPoint: " + ex);
		}

		List<String> mandantCodes = init(userId, mandantCode);

		startTasks(userId, mandantCodes);
	}

    private static Mgb getLocalService() {
    	if (mgbService == null) {
    		mgbService = MgbServiceFactory.createService();
    	}
    	return mgbService;
    }

	/**
	 * @param userId
	 * @param mandantCode
	 */
	private List<String> init(String userId, String mandantCode) {
		
		List<String> mandantCodes = new ArrayList<String>();
		
		// creating Server Connection
		showStatus("Connecting Server...");

		// The XAF components are using the SLocale and we want to have
		// the language settings operating systems dependend.
		SLocale.setDefaultLocale(Locale.getDefault());

		// Load properties from client.properties
		PropertyFactory.load("client");

		// Initializing Logging
		initLog4j(this, true);

		// Login. Create server site session
		showStatus("Logging in... userId = " + userId);

		try {
			Mgb mgb = getLocalService();
			mgb.login(userId, mandantCode, MgbSystemUtils.getSystemProperties());
			showStatus("Logged in userId = " + userId);
			// Initialize the server site session
			mgb.getSessionInfo();
			mgb.getDataSelection();
			defaultCron = mgb.getMgbConfigurationValue(MgbConfigurationDef.AUTO_MGB_DEFAULT_CRON);
            logger.info("Setting default cron to "+defaultCron);              
			defaultClient = mgb.getMgbConfigurationValue(MgbConfigurationDef.AUTO_MGB_DEFAULT_ALLOWED_CLIENT);
            logger.info("Setting default client to "+defaultClient);
            MandantVo[] mandants = mgb.findAllMandants();
            for (MandantVo mandant : mandants ) {
            	if (mandant.getAutoCheck())
            	{
            		mandantCodes.add(mandant.getMandantCode());
            	}
            }
            
		} catch (RemoteException e) {
			e.printStackTrace();
			showErrorStatus("Unable to login!");
		}

		showStatus("Connected to server.");
		
		return mandantCodes;
	}

	private void initLog4j(Object object, boolean on) {
		String log4jConfigFile = "mgbWebStartLog4j.properties";

		showStatus("Taking the file " + log4jConfigFile
				+ " as log4j configuration.");
		try {
			showStatus("Loading log4j configuration...");
			Properties log4jProperties = new Properties();
			InputStream in = object.getClass().getResourceAsStream(
					"/" + log4jConfigFile);
			if (in != null) {
				log4jProperties.load(in);
				PropertyConfigurator.configure(log4jProperties);
				showStatus("Loaded log4j configuration.");
			} else {
				showErrorStatus("Log4j configuration file "
						+ log4jConfigFile + " not found.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param userId
	 * @param mandantCodes
	 */
	private void startTasks(String userId, List<String> mandantCodes) {
		try {
			String localhost = "";
			try {
				localhost = Inet4Address.getLocalHost().getHostName();
			} catch (UnknownHostException uhe) {
				logger.error("Error while detecting local host.",uhe);				
			}

			ArrayList<String> allowedMandantList = new ArrayList<String>();
			String cron = null;
			for (String mandantCode : mandantCodes) {
				getLocalService().login(userId, mandantCode, MgbSystemUtils.getSystemProperties());
				String allowedClient = getLocalService().getMgbConfigurationValue(MgbConfigurationDef.AUTO_MGB_ALLOWED_CLIENT);
				if (allowedClient == null) {
                    logger.info("Taking default client "+defaultClient+" for mandant "+mandantCode);                     
				    allowedClient = defaultClient;
				} else {
                    logger.info("Taking client "+allowedClient+" for mandant "+mandantCode);                     				    
				}
				
				if (localhost.equalsIgnoreCase(allowedClient)) {
                    logger.info("Allowing mandant "+mandantCode+" due to host "+allowedClient);
					allowedMandantList.add(mandantCode);
					if (cron == null) {
						cron = getLocalService().getMgbConfigurationValue(MgbConfigurationDef.AUTO_MGB_CRON);
						if (cron != null) {
                            logger.warn("Taking cron '"+cron+"' from mandant "+mandantCode+" instead of default cron "+defaultCron);						    
						} else {
						    logger.info("Taking default cron '"+defaultCron+"' for mandant "+mandantCode);
						}
					} else {
						logger.info("Ignoring cron from mandant "+mandantCode);						
					}
				} else {
					logger.info("Ignoring mandant "+mandantCode+" due to unallowed host ("+localhost+" != "+allowedClient+")");
				}
			}

	        if (cron == null) {
                logger.info("Taking default cron "+defaultCron);                     
	            cron = defaultCron;
	        }

			if (cron == null) {
				logger.error("Scheduler not started, since no cron was defined for the mandants");
			} else {
				runCronTask(userId, allowedMandantList, cron);
			}

		} catch (RemoteException e) {
			logger.error("Unable to login!",e);
		}
		// creating Server Connection
		showStatus("Connected to server.");
	}

	@SuppressWarnings("unused")
    private void runSingleTask(String user, String mandantCode, Calendar startTime) {
		logger.info("Initializing task ...");
		try {
			logger.info("Setting up task for user " + user + "(" + mandantCode
					+ ") at " + startTime.getTime());
			MgbTaskVo task = new MgbTaskVo();
			task.setStartTime(startTime);
			task.setMandantCode(mandantCode);
			task.setTaskName("AutoCheck");
			task.setClient(Inet4Address.getLocalHost().getHostName());
			Long taskId = getLocalService().saveMgbTask(task);
			logger.info("Set up task with taskID=" + taskId);

			logger.info("Starting scheduler...");
			SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
			Scheduler sched = schedFact.getScheduler();
			sched.start();

			ArrayList<String> mandantList = new ArrayList<String>();
			mandantList.add(task.getMandantCode());
			ArrayList<Long> taskIdList = new ArrayList<Long>();
			taskIdList.add(taskId);
			JobDetail autoCheckJobDetail = buildMgbJobDetail(MgbAutoCheckTask.class, user, mandantList, taskIdList);
			SimpleTrigger autoCheckTrigger = new SimpleTrigger("AutoCheckTrigger",
					Scheduler.DEFAULT_GROUP, task.getStartTime().getTime(),
					null, 0, 0L);
			sched.scheduleJob(autoCheckJobDetail, autoCheckTrigger);

			logger.info("Started scheduler successfully.");

			logger.info("Initialized task successfully.");
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
        } catch (UnknownHostException e) {
            logger.error(e.getMessage(), e);
		} catch (RemoteException e) {
            logger.error(e.getMessage(), e);
        }
	}
	
	private void runCronTask(String user, ArrayList<String> mandantCodeList, String cronString) {
		try {
			logger.info("Starting scheduler...");
			SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
			Scheduler sched = schedFact.getScheduler();
			sched.start();
			logger.info("Started scheduler successfully.");

			
			logger.info("Initializing tasks ...");

			logger.info("Setting up AutoCheckTasks for user " + user + "(" + mandantCodeList + ") at '" + cronString + "'");
			JobDetail autoCheckJobDetail = buildMgbJobDetail(MgbAutoCheckTask.class, user, mandantCodeList, null);
			CronTrigger autoCheckTrigger = new CronTrigger("AutoCheckTrigger",
					Scheduler.DEFAULT_GROUP, cronString);
			sched.scheduleJob(autoCheckJobDetail, autoCheckTrigger);

			String heartbeat = MgbTaskDef.CRON_EVERY_5_MIN_NOT_WEEKEND;
			logger.info("Setting up HeartBeatTask for user " + user + "(" + mandantCodeList + ") at '" + heartbeat + "'");
			JobDetail heartBeatJobDetail = buildMgbJobDetail(MgbHeartBeatTask.class, user, mandantCodeList, null);
			CronTrigger heartBeatTrigger = new CronTrigger("HeartBeatTrigger",
					Scheduler.DEFAULT_GROUP, heartbeat);
			sched.scheduleJob(heartBeatJobDetail, heartBeatTrigger);

			logger.info("Initialized tasks successfully.");
			
		} catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
	}

	/**
	 * @param user
	 * @param task
	 * @param taskId
	 * @return
	 */
	private JobDetail buildMgbJobDetail(Class<?> jobClass, String user, ArrayList<String> mandantCodeList, ArrayList<Long> taskIdList) {
		if (mandantCodeList == null || (taskIdList != null && mandantCodeList.size() != taskIdList.size())) {
			throw new IllegalArgumentException("The mandant array must be set and a non-null taskid array must have corresponding entries with the same length.");
		}
		JobDetail jobDetail = new JobDetail(jobClass.getName(),
				Scheduler.DEFAULT_GROUP, jobClass);
		
		jobDetail.getJobDataMap().put(MgbTaskDef.USER_ID, user);
		jobDetail.getJobDataMap().put(MgbTaskDef.URL, urlString);
		jobDetail.getJobDataMap().put(MgbTaskDef.MANDANT_CODE_LIST, mandantCodeList);
		if (taskIdList == null) {
			taskIdList = new ArrayList<Long>(mandantCodeList.size());
			for (int i = 0; i < mandantCodeList.size(); i++) {
				taskIdList.add(i,Long.valueOf(0));
			}
		}
		jobDetail.getJobDataMap().put(MgbTaskDef.TASK_ID_LIST, taskIdList);

		return jobDetail;
	}

	private void showErrorStatus(String status) {
		System.err.println("ERROR: " + status);
	}

	private void showStatus(String status) {
		System.out.println("INFO:  " + status);
	}

	public static void main(String[] args) {
		if (args.length < 3) {
			throw new IllegalArgumentException("USAGE: JnlpApplicationClient <servername> <username> <mandantCode>");
		}
		new JnlpApplicationClient(args[0], args[1], args[2]);
	}
}