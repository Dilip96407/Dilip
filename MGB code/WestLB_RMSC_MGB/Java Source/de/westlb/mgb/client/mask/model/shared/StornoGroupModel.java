package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.StornoGroupVo;
import de.westlb.mgb.client.ui.tablemodel.TableModel;
import de.westlb.mgb.client.ui.tablemodel.TableModelFactory;


/**
 * Gui model for the addon list view.
 *
 * @author Manfred Boerner
 */

public class StornoGroupModel extends AbstractModel {

	public static final String P_SEARCH_RESULT_TABLE_MODEL	= "searchResultTableModel";

	public static final String P_TITLE = "title";
	
	private final String[] propertyNames = new String[] {
		P_SEARCH_RESULT_TABLE_MODEL,
	};
	
	private StornoGroupVo[] searchResult = null;

	/**
	 * Default constructor to create an empty model
	 */
	public StornoGroupModel() {
		setPropertyNames(propertyNames);
		fillModel();
	}
	
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		if (getReferenceTradeId() == null) {
			return;
		}
		
		searchResult = null;
		try {
            searchResult = MgbServiceFactory.getService().getStornoGroup(getReferenceTradeId());
    		TableModel tableModel = TableModelFactory.createTableModel("StornoGroupTable", searchResult);
    		setProperty(P_SEARCH_RESULT_TABLE_MODEL, tableModel);	
        } catch (RemoteException e) {
        	handleRemoteException(e);
        	return;
        }
        
	}

	public Long getTradeId(int row) {
		if (searchResult == null || searchResult.length < row) {
			return null;
		}
		
		return searchResult[row].getId();
	}
	
	
	public Long getReferenceTradeId() {
		if (!(getBusinessObject() instanceof Long)) {
			return null;
		}
		
		return (Long)getBusinessObject();	
	}
	
	/**
	 * Sets the business object where the model gets its data from.
	 * Expects to get a trade id.
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
