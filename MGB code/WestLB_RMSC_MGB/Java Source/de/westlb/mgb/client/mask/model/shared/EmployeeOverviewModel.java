package de.westlb.mgb.client.mask.model.shared;




/**
 * Gui model for the employee overview view.
 *
 * @author Manfred Boerner
 */

public interface EmployeeOverviewModel  {
	public static final String 
		P_LAST_NAME		= EmployeeEditModel.P_LAST_NAME,
		P_FIRST_NAME	= EmployeeEditModel.P_FIRST_NAME,
		P_NT_ID			= EmployeeEditModel.P_NT_ID,
		P_TELEFON		= EmployeeEditModel.P_TELEFON,
		P_EMAIL			= EmployeeEditModel.P_EMAIL,
		P_TRADER		= EmployeeEditModel.P_TRADER,
		P_TRADER_IDS_TABLE_MODEL = EmployeeEditModel.P_TRADER_IDS_TABLE_MODEL
	;

	public void doDeleteTrader(int row);
	public Long getEmployeeId();
	public void reload();
}
