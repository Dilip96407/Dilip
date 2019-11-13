package de.westlb.mgb.server.scheduler.jobs;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import de.westlb.mgb.server.service.archive.ArchiveService;

public class ArchiveJob implements Job {
	private static final String PARAM_ARCHIVEDIR = "archiveDir";
	private static final String PARAM_DAYS_TO_KEEP = "daysToKeep";
	private static final String PARAM_MAX_EXEC_TIME_IN_MINUTES = "maxExecTimeInMinutes";
	private static final String PARAM_DELETE = "delete";
	
	private static Logger logger = Logger.getLogger(ArchiveJob.class);

	@Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		if (jobDataMap == null) {
			throw new JobExecutionException("Missing Job Parameters");
		}
		String archiveDir = (String)jobDataMap.get(PARAM_ARCHIVEDIR);
		if (StringUtils.isEmpty(archiveDir)) {
			throw new JobExecutionException("Missing Job Parameter " + PARAM_ARCHIVEDIR);
		}

		String str = (String)jobDataMap.get(PARAM_DAYS_TO_KEEP);
		if (!StringUtils.isNumeric(str)) {
			throw new JobExecutionException("Missing or invalid Job Parameter " + PARAM_DAYS_TO_KEEP);
		}
		int daysToKeep = new Integer(str).intValue();

		str = (String)jobDataMap.get(PARAM_MAX_EXEC_TIME_IN_MINUTES);
		if (!StringUtils.isNumeric(str)) {
			throw new JobExecutionException("Missing or invalid Job Parameter " + PARAM_MAX_EXEC_TIME_IN_MINUTES);
		}
		int maxExecTimeInMinutes = new Integer(str).intValue();
		
		boolean delete = "true".equals(jobDataMap.get(PARAM_DELETE));

		
		try {
            new ArchiveService().archiveJobs(daysToKeep, maxExecTimeInMinutes, archiveDir, delete);
		} catch (Exception e) {
			logger.error("Archive jobs failed!", e);
			throw new JobExecutionException();
		}

	}
}
