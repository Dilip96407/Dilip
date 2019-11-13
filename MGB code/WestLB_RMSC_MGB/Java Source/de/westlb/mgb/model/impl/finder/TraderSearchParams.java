package de.westlb.mgb.model.impl.finder;

import de.westlb.mgb.model.impl.EmployeeImpl;
import de.westlb.mgb.model.impl.SourceSystemImpl;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class TraderSearchParams extends SearchParams {
	private String traderCode;
	private SourceSystemImpl sourceSystem;
	private EmployeeImpl employee;

	/**
	 * Returns the employee.
	 * @return EmployeeImpl
	 */
	public EmployeeImpl getEmployee() {
		return employee;
	}

	/**
	 * Returns the sourceSystem.
	 * @return SourceSystemImpl
	 */
	public SourceSystemImpl getSourceSystem() {
		return sourceSystem;
	}

	/**
	 * Returns the traderId.
	 * @return String
	 */
	public String getTraderCode() {
		return traderCode;
	}

	/**
	 * Sets the employee.
	 * @param employee The employee to set
	 */
	public void setEmployee(EmployeeImpl employee) {
		this.employee = employee;
	}

	/**
	 * Sets the sourceSystem.
	 * @param sourceSystem The sourceSystem to set
	 */
	public void setSourceSystem(SourceSystemImpl sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	/**
	 * Sets the traderId.
	 * @param traderId The traderId to set
	 */
	public void setTraderCode(String traderId) {
		this.traderCode = traderId;
	}

}
