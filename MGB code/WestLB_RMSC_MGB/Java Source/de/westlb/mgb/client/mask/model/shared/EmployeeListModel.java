package de.westlb.mgb.client.mask.model.shared;


/**
 * @author wsy4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface EmployeeListModel  {
	public static final String 
		P_FILTER_LAST_NAME			= CheckNewEmployeeModel.P_FILTER_LAST_NAME,
		P_FILTER_FIRST_NAME			= CheckNewEmployeeModel.P_FILTER_FIRST_NAME,
		P_FILTER_TRADER_CODE		= CheckNewEmployeeModel.P_FILTER_TRADER_CODE,
		P_SEARCH_RESULT_TABLE_MODEL	= CheckNewEmployeeModel.P_SEARCH_RESULT_TABLE_MODEL
	;

	/**
	 * Executes the employee search based on current filter properties.
	 */
	public boolean doSearch();
	
	/**
	 * Return the employee search result entry of a row
	 */
	public Long getEmployeeId(int row);
	
	/**
	 * Delete the seleted employee
	 */
	public boolean doDeleteEmployee(int row);
	

}
