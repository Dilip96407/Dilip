package de.westlb.mgb.model.impl.finder;

import java.util.Calendar;

import de.westlb.mgb.model.impl.MandantImpl;
import de.westlb.mgb.model.impl.SourceSystemImpl;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class JobSearchParams extends SearchParams {

	private String[] status;

	private Calendar cob;

	private Calendar importDayFrom;

	private Long[] jobIds;

	private SourceSystemImpl sourceSystem;

	private boolean isDefaultSelection;

	/**
	 * Constructor for JobSearchParams.
	 */
	public JobSearchParams() {
		this.isDefaultSelection = false;
	}

	/**
	 * Constructor for JobSearchParams.
	 * @param mandant
	 */
	public JobSearchParams(
		MandantImpl mandant,
		SourceSystemImpl sourceSystem,
		Calendar cob,
		String[] status,
		boolean isDefaultSelection) {
		super(mandant);
		this.cob = cob;
		this.sourceSystem = sourceSystem;
		this.status = status;
		this.isDefaultSelection = isDefaultSelection;
	}

	/**
	 * Returns the businessDay.
	 * @return Calendar
	 */
	public Calendar getCob() {
		return cob;
	}

	public Calendar getImportDayFrom() {
		return importDayFrom;
	}

	public void setImportDayFrom(Calendar importDayFrom) {
		this.importDayFrom = importDayFrom;
	}

	/**
	 * Returns the sourceSystem.
	 * @return SourceSystemImpl
	 */
	public SourceSystemImpl getSourceSystem() {
		return sourceSystem;
	}

	/**
	 * Returns the status.
	 * @return String[]
	 */
	public String[] getStatus() {
		return status;
	}

	/**
	 * Sets the businessDay.
	 * @param businessDay The businessDay to set
	 */
	public void setCob(Calendar cob) {
		this.cob = cob;
	}

	/**
	 * Sets the sourceSystem.
	 * @param sourceSystem The sourceSystem to set
	 */
	public void setSourceSystem(SourceSystemImpl sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	/**
	 * Sets the status.
	 * @param status The status to set
	 */
	public void setStatus(String[] status) {
		this.status = status;
	}

	/**
	 * Returns the isDefaultSelection.
	 * @return boolean
	 */
	public boolean isDefaultSelection() {
		return isDefaultSelection;
	}

	/**
	 * Sets the isDefaultSelection.
	 * @param isDefaultSelection The isDefaultSelection to set
	 */
	public void setIsDefaultSelection(boolean isDefaultSelection) {
		this.isDefaultSelection = isDefaultSelection;
	}

	public Long[] getJobIds() {
		return jobIds;
	}
	public void setJobIds(Long[] jobIds) {
		this.jobIds = jobIds;
	}
}
