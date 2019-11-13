package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.StateCodeVo;
import de.westlb.mgb.client.ui.tablemodel.TableModel;
import de.westlb.mgb.client.ui.tablemodel.TableModelFactory;


/**
 * Gui model for the trade list view. 
 *
 * @author Manfred Boerner
 */

public class StateCodeListModel extends AbstractModel {

	public static final String P_SEARCH_RESULT_TABLE_MODEL	= "searchResultTableModel";

	public static final String P_TITLE = "title";
	
	private String RESOURCE_BASE = getResourceBase();
		
	private final String[] propertyNames = new String[] {
		P_SEARCH_RESULT_TABLE_MODEL,
	};
	
	private StateCodeVo[] searchResult = null;

	/**
	 * Default constructor to create an empty model
	 */
	public StateCodeListModel() {
		setPropertyNames(propertyNames);
		fillModel();
	}
	
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		searchResult = null;
		try {
            searchResult = MgbServiceFactory.getService().getStateCodes(null);
    		TableModel tableModel = TableModelFactory.createTableModel("StateCodeTable", searchResult);
    		setProperty(P_SEARCH_RESULT_TABLE_MODEL, tableModel);	
        } catch (RemoteException e) {
        	handleRemoteException(e);
        	return;
        }
        
        						
/*		StateCodeTableModel tableModel = new StateCodeTableModel();
		tableModel.setData(searchResult);		
		tableModel.setResourceBundle(getResourceBundle());
		tableModel.setResourceBase(RESOURCE_BASE + "TBL_H_");		
		setProperty(P_SEARCH_RESULT_TABLE_MODEL, tableModel);	
		return;
*/	}

	public String getStateCode(int row) {
		if (searchResult == null || searchResult.length < row) {
			return null;
		}
		
		return searchResult[row].getStateCode();
	}
	
	/**
	 * Executes assignment traderid to selected employee
	 */
	public boolean doDeleteStateCode(int row) {
		String stateCode = null;
		if (getStateCode(row) != null) {
			stateCode = getStateCode(row);
		}

		// Check minumum input
		if(stateCode == null){
			logMessage(LOG_ERROR, getResourceString(RESOURCE_BASE +  "E_002"));
			return false;
		}
		
		try {
            MgbServiceFactory.getService().deleteStateCode(stateCode);
        } catch (RemoteException e) {
        	handleRemoteException(e);
        	return false;
        }
        
		return true;
	}
		
	
	/**
	 * Sets the business object where the model gets its data from.
	 */
	@Override
    public void setBusinessObject(Object newBusinessObject) {
		super.setBusinessObject(newBusinessObject);		
		fillModel();
	}


    
    @Override
    public void reload() {
    	fillModel();
    }

}
