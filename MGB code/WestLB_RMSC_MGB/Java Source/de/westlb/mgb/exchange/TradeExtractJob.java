package de.westlb.mgb.exchange;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import de.westlb.mgb.exchange.MgbExcelExchange;

//import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;

public class TradeExtractJob implements Job {

    private static final String PARAM_OUTPUTDIR = "outputdir";
    private static final String PARAM_FILENAME = "outputfilename";
    private static final String PARAM_DAYSTOINCLUDE = "daysToIclude";
    private static final String PARAM_MANCHECKONLY = "manCheckOnly";
    private static final String PARAM_USECREATIONDATE = "useCreationDate";
    private static final String PARAM_REPORTLOCATION = "reportLocation";
    private static final String PARAM_CLIENT = "client";
    private static final String PARAM_USERID = "userId";
    private static final String PARAM_DEFAULTMANDANT = "defaultMandantCode";

    
    private static Logger logger = Logger.getLogger(TradeExtractJob.class);
    
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        if (jobDataMap == null) {
            throw new JobExecutionException("Missing Job Parameters");
        }
        
        String outputdir = (String)jobDataMap.get(PARAM_OUTPUTDIR);
        if (StringUtils.isEmpty(outputdir)) {
            throw new JobExecutionException("Missing Job Parameter " + PARAM_OUTPUTDIR);
        }
        
        String outputfilename = (String)jobDataMap.get(PARAM_FILENAME);
        if (StringUtils.isEmpty(outputfilename)) {
            throw new JobExecutionException("Missing Job Parameter " + PARAM_FILENAME);
        }
        
        String reportLocation = (String)jobDataMap.get(PARAM_REPORTLOCATION);
        if (StringUtils.isEmpty(reportLocation)) {
            throw new JobExecutionException("Missing Job Parameter " + PARAM_REPORTLOCATION);
        }
        
        String client = (String)jobDataMap.get(PARAM_CLIENT);
        if (StringUtils.isEmpty(client)) {
            throw new JobExecutionException("Missing Job Parameter " + PARAM_CLIENT);
        }       
        
        String userId = (String)jobDataMap.get(PARAM_USERID);
        if (StringUtils.isEmpty(userId)) {
            throw new JobExecutionException("Missing Job Parameter " + PARAM_USERID);
        } 
        
        String defaultMandantCode = (String)jobDataMap.get(PARAM_DEFAULTMANDANT);
        if (StringUtils.isEmpty(defaultMandantCode)) {
            throw new JobExecutionException("Missing Job Parameter " + PARAM_DEFAULTMANDANT);
        } 

		String str = (String)jobDataMap.get(PARAM_DAYSTOINCLUDE);
		if (!StringUtils.isNumeric(str)) {
			throw new JobExecutionException("Missing or invalid Job Parameter " + PARAM_DAYSTOINCLUDE);
		}
		
		int daysToIclude = new Integer(str).intValue();
		
		boolean manCheckOnly = "true".equals(jobDataMap.get(PARAM_MANCHECKONLY));
		
		boolean useCreationDate = "true".equals(jobDataMap.get(PARAM_USECREATIONDATE));
		
		try {
			Object timestamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
			outputfilename=outputfilename.replaceAll("<YYYYMMDD>", timestamp.toString());
			MgbExcelExchange exch = new MgbExcelExchange(userId,defaultMandantCode);
			exch.extractTrades(manCheckOnly, useCreationDate, daysToIclude, outputdir, outputfilename, reportLocation, client);
		} catch (Exception e) {
			logger.error("TradeExtract job failed!", e);
			throw new JobExecutionException();
		}

	}
}
