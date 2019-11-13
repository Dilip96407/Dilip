package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.InstrumentSearchParamsVo;
import de.westlb.mgb.client.server.vo.InstrumentSearchResultEntryVo;
import de.westlb.mgb.client.ui.tablemodel.TableModel;
import de.westlb.mgb.client.ui.tablemodel.TableModelFactory;

/**
 * Gui model for the trade list view. 
 *
 * @author Manfred Boerner
 */

public class InstrumentListModel extends AbstractModel {

	public static final String P_FILTER_ISIN	= "isin";

	public static final String P_SEARCH_RESULT_TABLE_MODEL	= "searchResultTableModel";
		
	/** Definition of all properties provided by the model to the view */
	private final String[] stdPropertyNames = new String[] {
		P_FILTER_ISIN, 
	};
	
	private final String[] spcPropertyNames = new String[] {
		P_SEARCH_RESULT_TABLE_MODEL,
	};
	
	private InstrumentSearchParamsVo searchParams = null;
	private InstrumentSearchResultEntryVo[] searchResult = null;

	/**
	 * Default constructor to create an empty model
	 */
	public InstrumentListModel() {
		setPropertyNames(stdPropertyNames);
		setPropertyNames(spcPropertyNames);
	}
	
	/**
	 * Constructor filling the model with data from the business object
	 */
	public InstrumentListModel(Object businessObject) {
		this();
		setBusinessObject(businessObject);
	}
	
	public void clearSearchResult() {
		searchResult = null;
		setProperty(P_SEARCH_RESULT_TABLE_MODEL, null);	
	}	
	
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		if (searchParams != null) {
			setProperty(P_FILTER_ISIN, searchParams.getIsin());
		}
		
		return;
	}

	
	/**
	 * Executes employee the employee search
	 */
	public boolean doSearch() {
		if (searchParams == null) {
			searchParams = new InstrumentSearchParamsVo();
		}
		
		searchParams.setIsin((String)getProperty(P_FILTER_ISIN));
	
		searchResult = null;
		try {
            searchResult = MgbServiceFactory.getService().findInstruments(searchParams);
    		TableModel tableModel = TableModelFactory.createTableModel("InstrumentTable", searchResult);
    		setProperty(P_SEARCH_RESULT_TABLE_MODEL, tableModel);	
        } catch (RemoteException e) {
        	handleRemoteException(e);
        	return false;
        }
        
        						
/*		InstrumentTableModel tableModel = new InstrumentTableModel();
		tableModel.setData(searchResult);		
		tableModel.setResourceBundle(getResourceBundle());
		tableModel.setResourceBase(RESOURCE_BASE + "TBL_H_");		
		setProperty(P_SEARCH_RESULT_TABLE_MODEL, tableModel);	
*/	
		// Ok to start the search.
		return true;
	}
	
	/**
	 * Sets the business object where the model gets its data from.
	 * Expects to get an business object of type InstrumentSearchParamsVo
	 * or null.
	 */
	@Override
    public void setBusinessObject(Object newBusinessObject) {
		if (newBusinessObject instanceof InstrumentSearchParamsVo) {
			searchParams = (InstrumentSearchParamsVo)newBusinessObject;
		}
		
		super.setBusinessObject(newBusinessObject);		
		fillModel();
	}

    /**
     * @see de.westlb.mgb.client.mask.model.EmployeeListModel#getEmployeeId(int)
     */
    public Long getInstrumentId(int row) {
        if (searchResult == null || row >= searchResult.length || row < 0) {
        	return null;
        }
        
        return searchResult[row].getId();
    }
    
    @Override
    public void reload() {
   		doSearch();
    	fillModel();
    }

}
