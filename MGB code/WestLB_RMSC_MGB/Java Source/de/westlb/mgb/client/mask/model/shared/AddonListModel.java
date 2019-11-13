package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.AddonSearchResultEntryVo;
import de.westlb.mgb.client.ui.tablemodel.TableModel;
import de.westlb.mgb.client.ui.tablemodel.TableModelFactory;


/**
 * Gui model for the addon list view.
 *
 * @author Manfred Boerner
 */

public class AddonListModel extends AbstractModel {

	public static final String P_SEARCH_RESULT_TABLE_MODEL	= "searchResultTableModel";

	private String RESOURCE_BASE = getResourceBase();
		
	private final String[] propertyNames = new String[] {
		P_SEARCH_RESULT_TABLE_MODEL,
	};
	
	private AddonSearchResultEntryVo[] searchResult = null;

	/**
	 * Default constructor to create an empty model
	 */
	public AddonListModel() {
		setPropertyNames(propertyNames);
		fillModel();
	}
	
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		searchResult = null;
		try {
            searchResult = MgbServiceFactory.getService().findAllAddons();
            TableModel tableModel = TableModelFactory.createTableModel("AddonTable" ,searchResult);
    		setProperty(P_SEARCH_RESULT_TABLE_MODEL, tableModel);	
        } catch (RemoteException e) {
        	handleRemoteException(e);
        	return;
        }
        
        						
/*		AddonTableModel tableModel = new AddonTableModel();
		tableModel.setData(searchResult);		
		tableModel.setResourceBundle(getResourceBundle());
		tableModel.setResourceBase(RESOURCE_BASE + "TBL_H_");
		setProperty(P_SEARCH_RESULT_TABLE_MODEL, tableModel);	
*/
		return;
	}

	public Long getId(int row) {
		if (searchResult == null || searchResult.length < row) {
			return null;
		}
		
		return searchResult[row].getId();
	}
	
	/**
	 * Executes assignment traderid to selected employee
	 */
	public boolean doDelete(int row) {
		Long id = null;
		if (getId(row) != null) {
			id = getId(row);
		}

		// Check minumum input
		if(id == null){
			logMessage(LOG_ERROR, getResourceString(RESOURCE_BASE +  "E_002"));
			return false;
		}
		
		try {
            MgbServiceFactory.getService().deleteAddon(id);
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
