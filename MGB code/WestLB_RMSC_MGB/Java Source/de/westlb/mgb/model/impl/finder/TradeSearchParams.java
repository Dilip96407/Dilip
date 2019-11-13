package de.westlb.mgb.model.impl.finder;

import java.util.Calendar;

import de.westlb.mgb.model.impl.EmployeeImpl;
import de.westlb.mgb.model.impl.JobImpl;
import de.westlb.mgb.model.impl.MandantImpl;
import de.westlb.mgb.model.impl.StateIdImpl;
import de.westlb.mgb.model.impl.statistic.ReportSearchParams;

public class TradeSearchParams extends SearchParams implements ReportSearchParams {

	private StateIdImpl stateId;

	private Calendar fromDate;

	private Calendar toDate;

	private JobImpl[] jobs;

	private EmployeeImpl employee;

	private Boolean isSampleChecked;

	private Boolean isManualCheckRequired;

	private Boolean isManualCheckRequiredButNotHandled;

	private Boolean isReclamationRequiredButNotHandled;
	
	private Boolean isCurrentReclamationStateNull;

	private Boolean isReclamationClosed;

	private Boolean isMarketDataRequestRequired;

	private Boolean isLateEntry;

	private Boolean isSmallCustomer;
	
	private Boolean isStorno;

    private Boolean hasPreDecessor;

    private Boolean hasLowTurnover;

	private String tradeGroupId;
	
	private int maxResults = -1;
	
    private String[] tradeIds;

    private String[] instruments;

    private String[] locations;
	
	private Long sparkassenId;

    private String sparkassenReportType;

    private String[] jobStatus;
    
    private Calendar fromJobCobDate;

    private Calendar toJobCobDate;
        
    private Calendar fromJobCreationDate;

    private Calendar toJobCreationDate;
        
		
	public TradeSearchParams() {
	}

	public TradeSearchParams(
		MandantImpl mandant,
		JobImpl[] job,
		Calendar fromDate,
		Calendar toDate) {
		super(mandant);
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.jobs = job;
	}
	
	/**
	 * Returns the fromDate.
	 * @return Calendar
	 */
	@Override
    public Calendar getFromDate() {
		return fromDate;
	}

	/**
	 * Returns the toDate.
	 * @return Calendar
	 */
	@Override
    public Calendar getToDate() {
		return toDate;
	}

	/**
	 * Sets the fromDate.
	 * @param fromDate The fromDate to set
	 */
	public void setFromDate(Calendar fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * Sets the toDate.
	 * @param toDate The toDate to set
	 */
	public void setToDate(Calendar toDate) {
		this.toDate = toDate;
	}

	/**
	 * Returns the job.
	 * @return JobImpl
	 */
	@Override
    public JobImpl[] getJobs() {
		return jobs;
	}

	/**
	 * Sets the job.
	 * @param job The job to set
	 */
	public void setJobs(JobImpl[] job) {
		this.jobs = job;
	}

	public void setJobIds(Long[] ids) {
		if (ids != null) {
			this.jobs = new JobImpl[ids.length];
			for (int i = 0; i < ids.length; i++) {
				jobs[i] = new JobImpl(ids[i]);
			}
		}
	}

	/**
	 * Returns the state.
	 * @return StateImpl
	 */
	public StateIdImpl getStateId() {
		return stateId;
	}

	/**
	 * Sets the state.
	 * @param state The state to set
	 */
	public void setStateId(StateIdImpl state) {
		this.stateId = state;
	}

	/**
	 * Returns the isManualCheckRequired.
	 * @return Boolean
	 */
	public Boolean getIsManualCheckRequired() {
		return isManualCheckRequired;
	}

	/**
	 * Returns the isReclamationCheckRequired.
	 * @return Boolean
	 */
	public Boolean getIsReclamationRequiredButNotHandled() {
		return isReclamationRequiredButNotHandled;
	}

	/**
	 * Returns the isReclamationClosed.
	 * @return Boolean
	 */
	public Boolean getIsReclamationClosed() {
		return isReclamationClosed;
	}

	/**
	 * Sets the isManualCheckRequired.
	 * @param isManualCheckRequired The isManualCheckRequired to set
	 */
	public void setIsManualCheckRequired(Boolean isManualCheckRequired) {
		this.isManualCheckRequired = isManualCheckRequired;
	}

	/**
	 * Sets the isReclamationCheckRequired.
	 * @param isReclamationCheckRequired The isReclamationCheckRequired to set
	 */
	public void setIsReclamationRequiredButNotHandled(Boolean isReclamationCheckRequired) {
		this.isReclamationRequiredButNotHandled = isReclamationCheckRequired;
	}

	/**
	 * Sets the isReclamationClosed.
	 * @param isReclamationClosed The isReclamationClosed to set
	 */
	public void setIsReclamationClosed(Boolean isReclamationClosed) {
		this.isReclamationClosed = isReclamationClosed;
	}

	/**
	 * Returns the isOpenAutomaticCheck.
	 * @return Boolean
	 */
	public Boolean getIsMarketDataRequestRequired() {
		return isMarketDataRequestRequired;
	}

	/**
	 * Sets the isOpenAutomaticCheck.
	 * @param isOpenAutomaticCheck The isOpenAutomaticCheck to set
	 */
	public void setIsMarketDataRequestRequired(Boolean isOpenAutomaticCheck) {
		this.isMarketDataRequestRequired = isOpenAutomaticCheck;
	}

	/**
	 * Returns the employee.
	 * @return EmployeeImpl
	 */
	public EmployeeImpl getEmployee() {
		return employee;
	}

	/**
	 * Sets the employee.
	 * @param employee The employee to set
	 */
	public void setEmployee(EmployeeImpl employee) {
		this.employee = employee;
	}

	/**
	 * Returns the isLateEntry.
	 * @return Boolean
	 */
	public Boolean getIsLateEntry() {
		return isLateEntry;
	}

	/**
	 * Sets the isLateEntry.
	 * @param isLateEntry The isLateEntry to set
	 */
	public void setIsLateEntry(Boolean isLateEntry) {
		this.isLateEntry = isLateEntry;
	}

	/**
	 * Returns the transactionReference.
	 * @return String
	 */
	public String getTradeGroupId() {
		return tradeGroupId;
	}

	/**
	 * Sets the transactionReference.
	 * @param transactionReference The transactionReference to set
	 */
	public void setTradeGroupId(String transactionReference) {
		this.tradeGroupId = transactionReference;
	}


	/**
	 * Returns the isManualCheckRequiredNotHandled.
	 * @return Boolean
	 */
	public Boolean getIsManualCheckRequiredButNotHandled() {
		return isManualCheckRequiredButNotHandled;
	}

	/**
	 * Sets the isManualCheckRequiredNotHandled.
	 * @param isManualCheckRequiredNotHandled The isManualCheckRequiredNotHandled to set
	 */
	public void setIsManualCheckRequiredButNotHandled(Boolean isManualCheckRequiredNotHandled) {
		this.isManualCheckRequiredButNotHandled = isManualCheckRequiredNotHandled;
	}

	/**
	 * @return
	 */
	@Override
    public int getMaxResults() {
		return maxResults;
	}

	/**
	 * @param i
	 */
	@Override
    public void setMaxResults(int i) {
		maxResults = i;
	}

	/**
	 * @return Returns the isSmallCustomer.
	 */
	public Boolean getIsSmallCustomer() {
		return isSmallCustomer;
	}
	/**
	 * @param isSmallCustomer The isSmallCustomer to set.
	 */
	public void setIsSmallCustomer(Boolean isSmallCustomer) {
		this.isSmallCustomer = isSmallCustomer;
	}
	/**
	 * @return Returns the isStorno.
	 */
	public Boolean getIsStorno() {
		return isStorno;
	}
	/**
	 * @param isStorno The isStorno to set.
	 */
	public void setIsStorno(Boolean isStorno) {
		this.isStorno = isStorno;
	}
	/**
	 * @return Returns the tradeIds.
	 */
	public String[] getTradeIds() {
		return tradeIds;
	}
	/**
	 * @param tradeIds The tradeIds to set.
	 */
	public void setTradeIds(String[] tradeIds) {
		this.tradeIds = tradeIds;
	}
	/**
	 * @return
	 */
	public Boolean getIsCurrentReclamationStateNull() {
		return isCurrentReclamationStateNull;
	}

	/**
	 * @param boolean1
	 */
	public void setIsCurrentReclamationStateNull(Boolean boolean1) {
		isCurrentReclamationStateNull = boolean1;
	}

    /**
     * @return Returns the hasLowTurnover.
     */
    public Boolean getHasLowTurnover() {
        return hasLowTurnover;
    }
    /**
     * @param hasLowTurnover The hasLowTurnover to set.
     */
    public void setHasLowTurnover(Boolean hasLowTurnover) {
        this.hasLowTurnover = hasLowTurnover;
    }
    /**
     * @return Returns the hasPreDecessor.
     */
    public Boolean getHasPreDecessor() {
        return hasPreDecessor;
    }
    /**
     * @param hasPreDecessor The hasPreDecessor to set.
     */
    public void setHasPreDecessor(Boolean hasPreDecessor) {
        this.hasPreDecessor = hasPreDecessor;
    }	
	
	/**
	 * @return Returns the jobStatus.
	 */
	public String[] getJobStatus() {
		return jobStatus;
	}
	/**
	 * @param jobStatus The jobStatus to set.
	 */
	public void setJobStatus(String[] jobStatus) {
		this.jobStatus = jobStatus;
	}
	
	/**
	 * @return Returns the sparkassenId.
	 */
	public Long getSparkassenId() {
		return sparkassenId;
	}
	/**
	 * @param sparkassenId The sparkassenId to set.
	 */
	public void setSparkassenId(Long sparkassenId) {
		this.sparkassenId = sparkassenId;
	}
	
	public Boolean getIsSampleChecked() {
		return isSampleChecked;
	}
	
	public void setIsSampleChecked(Boolean isSampleChecked) {
		this.isSampleChecked = isSampleChecked;
	}

    
    public String[] getLocations() {
        return locations;
    }

    
    public void setLocations(String[] locations) {
        this.locations = locations;
    }

    
    public String getSparkassenReportType() {
        return sparkassenReportType;
    }

    
    public void setSparkassenReportType(String sparkassenReportType) {
        this.sparkassenReportType = sparkassenReportType;
    }

    
    public String[] getInstruments() {
        return instruments;
    }

    
    public void setInstruments(String[] instruments) {
        this.instruments = instruments;
    }

    public void setFromJobCobDate(Calendar fromJobCobDate) {
        this.fromJobCobDate = fromJobCobDate;
    }

    public Calendar getFromJobCobDate() {
        return fromJobCobDate;
    }

    public void setToJobCobDate(Calendar toJobCobDate) {
        this.toJobCobDate = toJobCobDate;
    }

    public Calendar getToJobCobDate() {
        return toJobCobDate;
    }

    public Calendar getFromJobCreationDate()
    {
        return fromJobCreationDate;
    }

    public void setFromJobCreationDate(Calendar fromJobCreationDate)
    {
        this.fromJobCreationDate = fromJobCreationDate;
    }

    public Calendar getToJobCreationDate()
    {
        return toJobCreationDate;
    }

    public void setToJobCreationDate(Calendar toJobCreationDate)
    {
        this.toJobCreationDate = toJobCreationDate;
    }
}
