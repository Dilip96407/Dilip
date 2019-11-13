package de.westlb.mgb.exchange;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import de.westlb.mgb.exchange.MgbPagCommentLoader;
import de.westlb.mgb.exchange.MgbPagExcelReader;

//import java.nio.file.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;

public class ImportCommentsJob implements Job {

    private static final String PARAM_INPUTDIR = "inputdir";
    private static final String PARAM_FILENAME = "inputfilename";
    private static final String PARAM_USERID = "userId";
    private static final String PARAM_REPORTLOCATION = "reportLocation";
    private static final String PARAM_CLIENT = "client";
    
    private static Logger logger = Logger.getLogger(ImportCommentsJob.class);
    
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        if (jobDataMap == null) {
            throw new JobExecutionException("Missing Job Parameters");
        }
        
        String userId = (String)jobDataMap.get(PARAM_USERID);
        if (StringUtils.isEmpty(userId)) {
            throw new JobExecutionException("Missing Job Parameter " + PARAM_USERID);
        } 
        
        String reportLocation = (String)jobDataMap.get(PARAM_REPORTLOCATION);
        if (StringUtils.isEmpty(reportLocation)) {
            throw new JobExecutionException("Missing Job Parameter " + PARAM_REPORTLOCATION);
        }
        
        String client = (String)jobDataMap.get(PARAM_CLIENT);
        if (StringUtils.isEmpty(client)) {
            throw new JobExecutionException("Missing Job Parameter " + PARAM_CLIENT);
        }   
        
        String inputdir = (String)jobDataMap.get(PARAM_INPUTDIR);
        if (StringUtils.isEmpty(inputdir)) {
            throw new JobExecutionException("Missing Job Parameter " + PARAM_INPUTDIR);
        }
        
        String inputfilename = (String)jobDataMap.get(PARAM_FILENAME);
        if (StringUtils.isEmpty(inputfilename)) {
            throw new JobExecutionException("Missing Job Parameter " + PARAM_FILENAME);
        }

		Object datesuffix = new SimpleDateFormat("yyyyMMdd").format(new Date());
		inputfilename=inputfilename.replaceAll("<YYYYMMDD>", datesuffix.toString());
		String infilename= StringUtils.join(new Object[] {inputdir, inputfilename}, File.separator);
		
		File f = new File(infilename);
		
		if (f.exists()) {
				
			try {

				MgbPagCommentLoader loader = new MgbPagCommentLoader(userId,reportLocation,client);
				MgbPagExcelReader poiReader = new MgbPagExcelReader(loader);
				poiReader.read(infilename);
	
				if (poiReader.getErrorCount() == 0) {
			        File oldfile =new File(infilename);
			        Object timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			        String archivefilename= StringUtils.join(new Object[] {inputdir, "archive", inputfilename}, File.separator);
			        File newfile =new File(archivefilename+ "_" + timestamp.toString());
	
			        try {
			        	oldfile.renameTo(newfile);
			            System.out.println("File moved to :" + infilename+ "_" + timestamp.toString());
			        } catch (Exception e) {
			        	logger.error("The file can't be moved", e);
			        }
				} else {
					System.out.println(poiReader.getErrorDetails());
				}
				
			} catch (Exception e) {
				logger.error("TradeExtract job failed!", e);
				throw new JobExecutionException();
			}
		
		}
		
	}
}
