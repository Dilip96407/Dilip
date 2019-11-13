package de.westlb.mgb.model.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.Set;

/**
 */
public class JobImpl extends EntityImpl {
	/**
     * 
     */
    private static final long serialVersionUID = 1300935084255313656L;

    // longId2 contains the same information than longId2. It exists, because of
	// Hibernate bug with "group by" statements. 
	private long longId2;

	// Field derived by stopBusinessTime containg the business day in 
	// format 'YYYY-MM-DD' for aggregation/statistic purpose only 
	private String cobDayString;
	
	// Field derived by stopBusinessTime containing the business month
	// in format 'YYYY-MM' for aggregation/statistic purpose only
	private String cobMonthString;
	
	private Calendar startLoadTime;

	private Calendar stopLoadTime;

	private Calendar startConvertTime;

	private Calendar stopConvertTime;

	private Calendar startBusinessTime;

	private Calendar stopBusinessTime;

	private Calendar systemTime;

	private Calendar cob;

    public static final int JOB_STATUS_MAX_LENGTH = 255;

    public static final int JOB_ARCHIVE_FILE_NAME_MAX_LENGTH = 100;
    public static final int JOB_IMPORT_FILE_NAME_MAX_LENGTH = 100;

	private String status;

	private int numberOfTotalRecords;
	
	private int numberOfLoadErrors;
	
	private int numberOfConvertErrors;
	
	private SourceSystemImpl sourceSystem;	

	private Set<TradeImpl> trades;
	
	private boolean archived;
	private String archiveFile;
	public String importFile;

	public JobImpl() {
	}

	public JobImpl(Long JobId) {
		super(JobId);
	}

	public static JobImpl[] getJobs(Long[] ids) {
		if (ids != null) {
			JobImpl[] jobs = new JobImpl[ids.length];
			for (int i = 0; i < ids.length; i++) {
				jobs[i] = new JobImpl(ids[i]);
			}
			
			return jobs;
		}
		
		return null;
	}
	
	@Override
    public Long getLongId() {
		return super.getLongId();
	}

	/**
	 * Returns the startTime.
	 * @return Date
	 */
	public Calendar getStartLoadTime() {
		return startLoadTime;
	}

	/**
	 * Returns the trades.
	 * @return java.util.Set
	 */
	public Collection<TradeImpl> getTrades() {
		return trades;
	}

	/**
	 * Sets the startTime.
	 * @param startTime The startTime to set
	 */
	public void setStartLoadTime(Calendar startTime) {
		this.startLoadTime = startTime;
	}

	/**
	 * Returns the sourceSystem.
	 * @return SourceSystemImpl
	 */
	public SourceSystemImpl getSourceSystem() {
		return sourceSystem;
	}

	/**
	 * Sets the sourceSystem.
	 * @param sourceSystem The sourceSystem to set
	 */
	public void setSourceSystem(SourceSystemImpl sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	/**
	 * Returns the startConvertTime.
	 * @return Date
	 */
	public Calendar getStartConvertTime() {
		return startConvertTime;
	}

	/**
	 * Returns the status.
	 * @return String
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Returns the stopConvertTime.
	 * @return Date
	 */
	public Calendar getStopConvertTime() {
		return stopConvertTime;
	}

	/**
	 * Returns the stopLoadTime.
	 * @return Date
	 */
	public Calendar getStopLoadTime() {
		return stopLoadTime;
	}

	/**
	 * Sets the startConvertTime.
	 * @param startConvertTime The startConvertTime to set
	 */
	public void setStartConvertTime(Calendar startConvertTime) {
		this.startConvertTime = startConvertTime;
	}

	/**
	 * Sets the status.
	 * @param status The status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Sets the stopConvertTime.
	 * @param stopConvertTime The stopConvertTime to set
	 */
	public void setStopConvertTime(Calendar stopConvertTime) {
		this.stopConvertTime = stopConvertTime;
	}

	/**
	 * Sets the stopLoadTime.
	 * @param stopLoadTime The stopLoadTime to set
	 */
	public void setStopLoadTime(Calendar stopLoadTime) {
		this.stopLoadTime = stopLoadTime;
	}

	/**
	 * Sets the trades.
	 * @param trades The trades to set
	 */
	@SuppressWarnings("unused")
    private void setTrades(Set<TradeImpl> trades) {
		this.trades = trades;
	}

	/**
	 * Returns the numberOfConvertErrors.
	 * @return int
	 */
	public int getNumberOfConvertErrors() {
		return numberOfConvertErrors;
	}

	/**
	 * Returns the numberOfLoadErrors.
	 * @return int
	 */
	public int getNumberOfLoadErrors() {
		return numberOfLoadErrors;
	}

	/**
	 * Returns the numberOfTotalRecords.
	 * @return int
	 */
	public int getNumberOfTotalRecords() {
		return numberOfTotalRecords;
	}

	/**
	 * Returns the startBusinessTime.
	 * @return Calendar
	 */
	public Calendar getStartBusinessTime() {
		return startBusinessTime;
	}

	/**
	 * Returns the stopBusinessTime.
	 * @return Calendar
	 */
	public Calendar getStopBusinessTime() {
		return stopBusinessTime;
	}

	/**
	 * Sets the numberOfConvertErrors.
	 * @param numberOfConvertErrors The numberOfConvertErrors to set
	 */
	public void setNumberOfConvertErrors(int numberOfConvertErrors) {
		this.numberOfConvertErrors = numberOfConvertErrors;
	}

	/**
	 * Sets the numberOfLoadErrors.
	 * @param numberOfLoadErrors The numberOfLoadErrors to set
	 */
	public void setNumberOfLoadErrors(int numberOfLoadErrors) {
		this.numberOfLoadErrors = numberOfLoadErrors;
	}

	/**
	 * Sets the numberOfTotalRecords.
	 * @param numberOfTotalRecords The numberOfTotalRecords to set
	 */
	public void setNumberOfTotalRecords(int numberOfTotalRecords) {
		this.numberOfTotalRecords = numberOfTotalRecords;
	}

	/**
	 * Sets the startBusinessTime.
	 * @param startBusinessTime The startBusinessTime to set
	 */
	public void setStartBusinessTime(Calendar startBusinessTime) {
		this.startBusinessTime = startBusinessTime;
	}

	/**
	 * Sets the stopBusinessTime.
	 * @param stopBusinessTime The stopBusinessTime to set
	 */
	public void setStopBusinessTime(Calendar stopBusinessTime) {
		this.stopBusinessTime = stopBusinessTime;
	}

	/**
	 * Returns the systemTime.
	 * @return Calendar
	 */
	public Calendar getSystemTime() {
		return systemTime;
	}

	/**
	 * Sets the systemTime.
	 * @param systemTime The systemTime to set
	 */
	public void setSystemTime(Calendar systemTime) {
		this.systemTime = systemTime;
	}


	/**
	 * Returns the cob.
	 * @return Calendar
	 */
	public Calendar getCob() {
		return cob;
	}

	/**
	 * Sets the cob.
	 * @param cob The cob to set
	 */
	public void setCob(Calendar cob) {
		this.cob = cob;
	}


    /**
     * @return
     */
    public String getCobDayString() {
        return cobDayString;
    }

    /**
     * @param string
     */
    public void setCobDayString(String string) {
        cobDayString = string;
    }

    /**
     * @return
     */
    public long getLongId2() {
        return longId2;
    }

    /**
     * @param l
     */
    public void setLongId2(long l) {
        longId2 = l;
    }

	/**
	 * @return
	 */
	public String getCobMonthString() {
		return cobMonthString;
	}

	/**
	 * @param string
	 */
	public void setCobMonthString(String string) {
		cobMonthString = string;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	public String getArchiveFile() {
		return archiveFile;
	}

	public void setArchiveFile(String archiveFile) {
		this.archiveFile = archiveFile;
	}

    public String getImportFile()
    {
        return importFile;
    }

    public void setImportFile(String importFile)
    {
        this.importFile = importFile;
    }
}
