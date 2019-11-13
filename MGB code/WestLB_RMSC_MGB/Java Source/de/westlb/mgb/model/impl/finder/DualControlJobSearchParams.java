package de.westlb.mgb.model.impl.finder;


/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DualControlJobSearchParams extends SearchParams {
	private boolean showCommitedJobs = false;
	private String excludedRequesterNtId;
	
	/**
	 * Returns the excludedRequesterNtId.
	 * @return String
	 */
	public String getExcludedRequesterNtId() {
		return excludedRequesterNtId;
	}

	/**
	 * Returns the showCommitedJobs.
	 * @return boolean
	 */
	public boolean isShowCommitedJobs() {
		return showCommitedJobs;
	}

	/**
	 * Sets the excludedRequesterNtId.
	 * @param excludedRequesterNtId The excludedRequesterNtId to set
	 */
	public void setExcludedRequesterNtId(String excludedRequesterNtId) {
		this.excludedRequesterNtId = excludedRequesterNtId;
	}

	/**
	 * Sets the showCommitedJobs.
	 * @param showCommitedJobs The showCommitedJobs to set
	 */
	public void setShowCommitedJobs(boolean showCommitedJobs) {
		this.showCommitedJobs = showCommitedJobs;
	}

}
