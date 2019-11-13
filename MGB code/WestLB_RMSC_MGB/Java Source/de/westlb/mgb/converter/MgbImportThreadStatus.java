package de.westlb.mgb.converter;

import java.io.Serializable;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import de.westlb.mgb.model.impl.SourceSystemImpl;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MgbImportThreadStatus implements Serializable {
    

    /**
     * 
     */
    private static final long serialVersionUID = 2L;
    
    public final static String INITIALIZED = "Initialized.";
    public final static String RECEIVING = "Receiving.";
    public final static String PROCESSING = "Processing.";
    public final static String WAITING = "Waiting.";
    public final static String STOP_REQUESTED = "Stop requested.";
    public final static String STOPPED = "Stopped.";

    static Logger logger = Logger.getLogger(MgbImportThreadStatus.class);

	private String status;
	private Calendar startTime;
	private Calendar lastImportTime;
	private String dbUser = null;
	private String dbPassword = null;
	private String dbHost = null;
	private String sqlLoaderPathName = null;
	private String loaderCtlDir = null;
	private String workingDir = null;
	private String archiveDir = null;
	private String importDir = null;
	private boolean stopRequested = false;
	private SourceSystemImpl sourceSystem;

	public SourceSystemImpl getSourceSystem() {
		return sourceSystem;
	}

	public void setSourceSystem(SourceSystemImpl sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	public void debug() {
		logger.debug("sourceSystemName      : "+getSourceSystemName());
		logger.debug("status                : "+status);
		if (startTime != null) {
			logger.debug("startTime             : "+startTime.getTime());
		} else {
			logger.debug("startTime             : ");			
		}
		if (lastImportTime != null) {
			logger.debug("lastImportTime        : "+lastImportTime.getTime());
		} else {
			logger.debug("lastImportTime        : ");
		}
		logger.debug("dbUser                : "+dbUser);
//		logger.debug("dbPassword            : "+dbPassword);
		logger.debug("dbHost                : "+dbHost);
		logger.debug("sqlLoaderPathName     : "+sqlLoaderPathName);
		logger.debug("loaderCtlDir          : "+loaderCtlDir);
		logger.debug("workingDir            : "+workingDir);
		logger.debug("archiveDir            : "+archiveDir);
		logger.debug("importDir	            : "+importDir);
		String[] importFiles = getImportFiles();
		for (int i=0; i<importFiles.length; i++) { 
		    logger.debug("importFile            : "+importFiles[i]);
		}
        logger.debug("importFileExtension   : "+getImportFileExtension());
        logger.debug("converterClass	   : "+getConverterClass());
        logger.debug("lastLineDateFormat    : "+getLastLineDateFormat());
        logger.debug("sourceSystemCobDepends: "+getSourceSystemCobDepends());
		logger.debug("maxLoadErrors         : "+getMaxLoadErrors());
		logger.debug("maxConvertErrors      : "+getMaxConvertErrors());
		logger.debug("maxCheckDifferences   : "+getMaxCheckDifferences());		
	}
	
	public boolean isRunning() {
	    return !INITIALIZED.equals(getStatus()) && !STOPPED.equals(getStatus());
	}
	
	/**
	 * Returns the archiveDir.
	 * @return String
	 */
	public String getArchiveDir() {
		return archiveDir;
	}

	/**
	 * Returns the dbHost.
	 * @return String
	 */
	public String getDbHost() {
		return dbHost;
	}

	/**
	 * Returns the dbPassword.
	 * @return String
	 */
	public String getDbPassword() {
		return dbPassword;
	}

	/**
	 * Returns the dbUser.
	 * @return String
	 */
	public String getDbUser() {
		return dbUser;
	}

	/**
	 * Returns the importDir.
	 * @return String
	 */
	public String getImportDir() {
		return importDir;
	}

	/**
	 * Returns the importFile.
	 * @return String
	 */
	public String[] getImportFiles() {
		String[] result = new String[]{}; 
		String filenames = sourceSystem.getFilename();
		if (filenames != null && filenames.length() >0 )
		{
			result = StringUtils.stripAll(StringUtils.split(filenames, ","));
		}
		return result;
	}

	/**
	 * Returns the loaderCtlDir.
	 * @return String
	 */
	public String getLoaderCtlDir() {
		return loaderCtlDir;
	}

	/**
	 * Returns the maxCheckDifferences.
	 * @return int
	 */
	public int getMaxCheckDifferences() {
		return sourceSystem.getMaxCheckDiff();
	}

	/**
	 * Returns the maxConvertErrors.
	 * @return int
	 */
	public int getMaxConvertErrors() {
		return sourceSystem.getMaxConvertErrors();
	}

	/**
	 * Returns the maxLoadErrors.
	 * @return int
	 */
	public int getMaxLoadErrors() {
		return sourceSystem.getMaxLoadErrors();
	}

	/**
	 * Returns the oracleDir.
	 * @return String
	 */
	public String getSqlLoaderPathName() {
		return sqlLoaderPathName;
	}

	/**
	 * Returns the sourceSystemName.
	 * @return String
	 */
	public String getSourceSystemName() {
		return sourceSystem.getCode();
	}

	public String getSourceSystemCobDepends()
    {
        return sourceSystem.getCobDepends();
    }

    /**
	 * Returns the startTime.
	 * @return Calendar
	 */
	public Calendar getStartTime() {
		return startTime;
	}

	/**
	 * Returns the status.
	 * @return String
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Returns the workingDir.
	 * @return String
	 */
	public String getWorkingDir() {
		return workingDir;
	}

	/**
	 * Sets the archiveDir.
	 * @param archiveDir The archiveDir to set
	 */
	public void setArchiveDir(String archiveDir) {
		this.archiveDir = archiveDir;
	}

	/**
	 * Sets the dbHost.
	 * @param dbHost The dbHost to set
	 */
	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}

	/**
	 * Sets the dbPassword.
	 * @param dbPassword The dbPassword to set
	 */
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	/**
	 * Sets the dbUser.
	 * @param dbUser The dbUser to set
	 */
	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	/**
	 * Sets the importDir.
	 * @param importDir The importDir to set
	 */
	public void setImportDir(String importDir) {
		this.importDir = importDir;
	}

	/**
	 * Sets the loaderCtlDir.
	 * @param loaderCtlDir The loaderCtlDir to set
	 */
	public void setLoaderCtlDir(String loaderCtlDir) {
		this.loaderCtlDir = loaderCtlDir;
	}

	/**
	 * Sets the oracleDir.
	 * @param oracleDir The oracleDir to set
	 */
	public void setSqlLoaderPathName(String pathName) {
		this.sqlLoaderPathName = pathName;
	}

	/**
	 * Sets the startTime.
	 * @param startTime The startTime to set
	 */
	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	/**
	 * Sets the status.
	 * @param status The status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Sets the workingDir.
	 * @param workingDir The workingDir to set
	 */
	public void setWorkingDir(String workingDir) {
		this.workingDir = workingDir;
	}

	/**
	 * Returns the stopRequested.
	 * @return boolean
	 */
	public boolean isStopRequested() {
		return stopRequested;
	}

	/**
	 * Sets the stopRequested.
	 * @param stopRequested The stopRequested to set
	 */
	public void setStopRequested(boolean stopRequested) {
		this.stopRequested = stopRequested;
	}

	/**
	 * Returns the lastImportTime.
	 * @return Calendar
	 */
	public Calendar getLastImportTime() {
		return lastImportTime;
	}

	/**
	 * Sets the lastImportTime.
	 * @param lastImportTime The lastImportTime to set
	 */
	public void setLastImportTime(Calendar lastImportTime) {
		this.lastImportTime = lastImportTime;
	}
        
    public String getImportFileExtension() {
        return sourceSystem.getFileExt();
    }
    
    public String getConverterClass()
    {
        return sourceSystem.getConverter();
    }

    public String getLastLineDateFormat()
    {
        return sourceSystem.getLastLineDateFormat();
    }

    public String getLastLineFormat()
    {
        return sourceSystem.getLastLineFormat();
    }

	public long getMaxFileAge() {
		return sourceSystem.getMaxFileAge();
	}

}
