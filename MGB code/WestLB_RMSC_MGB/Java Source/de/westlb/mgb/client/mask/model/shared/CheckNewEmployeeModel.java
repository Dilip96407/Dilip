package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import org.apache.commons.lang3.StringUtils;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.EmployeeSearchParamsVo;
import de.westlb.mgb.client.server.vo.EmployeeSearchResultEntryVo;
import de.westlb.mgb.client.server.vo.EmployeeVo;
import de.westlb.mgb.client.server.vo.TraderSearchResultEntryVo;
import de.westlb.mgb.client.server.vo.TraderVo;
import de.westlb.mgb.client.ui.tablemodel.TableModel;
import de.westlb.mgb.client.ui.tablemodel.TableModelFactory;


/**
 * Gui model for the trade list view. 
 *
 * @author Manfred Boerner
 */

public class CheckNewEmployeeModel extends AbstractModel implements EmployeeListModel {

	public static final String P_FILTER_LAST_NAME			= "lastName";
	public static final String P_FILTER_FIRST_NAME			= "firstName";
	public static final String P_FILTER_TRADER_CODE			= "traderCode";
	public static final String P_SEARCH_RESULT_TABLE_MODEL	= "searchResultTableModel";

	public static final String P_TITLE = "title";
	
	private String RESOURCE_BASE = getResourceBase();
		
	/** Definition of all properties provided by the model to the view */
	private final String[] stdPropertyNames = new String[] {
		P_FILTER_LAST_NAME, 
		P_FILTER_FIRST_NAME,
		P_FILTER_TRADER_CODE,
		P_TITLE,
	};
	
	private final String[] spcPropertyNames = new String[] {
		P_SEARCH_RESULT_TABLE_MODEL,
	};
	
	private TraderSearchResultEntryVo traderInfo = null;
	private EmployeeSearchParamsVo searchParams = null;
	private EmployeeSearchResultEntryVo[] searchResult = null;

	/**
	 * Default constructor to create an empty model
	 */
	public CheckNewEmployeeModel() {
		setPropertyNames(stdPropertyNames);
		setPropertyNames(spcPropertyNames);
	}
	
	/**
	 * Constructor filling the model with data from the business object
	 */
	public CheckNewEmployeeModel(Object businessObject) {
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
			setProperty(P_FILTER_FIRST_NAME, searchParams.getFirstName());
			setProperty(P_FILTER_LAST_NAME, searchParams.getLastName());
		}
		
		setPropertyTitle();		
		return;
	}

	private EmployeeSearchResultEntryVo getEmployee(int row) {
		if (searchResult == null || searchResult.length < row || row < 0) {
			return null;
		}
		
		return searchResult[row];
	}
	
	/**
	 * Returnes an EmployeeVo object with the data entered for search
	 */
	public EmployeeVo getEmployeeFromSearchParams() {
		EmployeeVo employee = new EmployeeVo();
		employee.setFirstName((String)getProperty(P_FILTER_FIRST_NAME));
		employee.setLastName((String)getProperty(P_FILTER_LAST_NAME));
		
		if (traderInfo != null) {
			TraderVo traderId = new TraderVo();
			traderId.setId(traderInfo.getId());
			traderId.setSourceSystemCode(traderInfo.getSourceSystemCode());
			traderId.setTraderCode(traderInfo.getTraderCode());

			TraderVo[] traderIds = new TraderVo[1];
			traderIds [0] = traderId;
			employee.setTraderIds(traderIds);
		}
		
		return employee;
	}
	
	private Long getTraderIdLong() {
		if (traderInfo == null) {
			return null;
		}
		
		return traderInfo.getId();	
	}
	
	private void setPropertyTitle() {
		String title = null;
		
		if (traderInfo == null) {
			title = getResourceString(RESOURCE_BASE + "T_001");
		} else  {
			title = getResourceString(RESOURCE_BASE + "T_002");
			title = StringUtils.replace(title, "<traderId>", traderInfo.getTraderCode());
			title = StringUtils.replace(title, "<sourceSystem>", traderInfo.getSourceSystemCode());
		}
		
		setProperty(P_TITLE, title);		
	}

	/**
	 * Executes assignment traderid to selected employee
	 */
	public boolean doAssignTraderToEmployee(int row) {
		Long employeeId = null;
		if (getEmployee(row) != null) {
			employeeId = getEmployee(row).getEmployeeId();
		}
		Long traderId = getTraderIdLong();
				
		// Check minumum input
		if(employeeId == null || traderId == null){
			logMessage(LOG_ERROR, getResourceString(RESOURCE_BASE +  "E_002"));
			return false;
		}
		
		try {
            MgbServiceFactory.getService().assignTraderToEmployee(employeeId, traderId);
        } catch (RemoteException e) {
        	handleRemoteException(e);
        	return false;
        }
        
		return true;
	}
	
	/**
	 * Executes assignment traderid to selected employee
	 */
	@Override
    public boolean doDeleteEmployee(int row) {
		Long employeeId = null;
		if (getEmployee(row) != null) {
			employeeId = getEmployee(row).getEmployeeId();
		}

		// Check minumum input
		if(employeeId == null){
			logMessage(LOG_ERROR, getResourceString(RESOURCE_BASE +  "E_002"));
			return false;
		}
		
		try {
            MgbServiceFactory.getService().deleteEmployee(employeeId);
        } catch (RemoteException e) {
        	handleRemoteException(e);
        	return false;
        }
        
		return true;
	}
		
	/**
	 * Executes employee the employee search
	 */
	@Override
    public boolean doSearch() {
		if (searchParams == null) {
			searchParams = new EmployeeSearchParamsVo();
		}
		
		searchParams.setFirstName((String)getProperty(P_FILTER_FIRST_NAME));
		searchParams.setLastName((String)getProperty(P_FILTER_LAST_NAME));
		searchParams.setTraderCode((String)getProperty(P_FILTER_TRADER_CODE));
	
		// Check minumum input
		// if(searchParams.getFirstName() == null && searchParams.getLastName() == null){
		//	logMessage(LOG_ERROR, getResourceString(RESOURCE_BASE +  "E_001"));
		//	return false;
		//}
		
		searchResult = null;
		try {
            searchResult = MgbServiceFactory.getService().findEmployees(searchParams);
            TableModel tableModel = TableModelFactory.createTableModel("EmployeeTable", searchResult);
    		setProperty(P_SEARCH_RESULT_TABLE_MODEL, tableModel);	
        } catch (RemoteException e) {
        	handleRemoteException(e);
        	return false;
        }
        
       						
/*		EmployeeTableModel tableModel = new EmployeeTableModel();
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
	 */
	@Override
    public void setBusinessObject(Object newBusinessObject) {
		// Expects to get a traderID
		if (newBusinessObject instanceof TraderSearchResultEntryVo) {
			traderInfo = (TraderSearchResultEntryVo)newBusinessObject;
		}
		super.setBusinessObject(newBusinessObject);		
		fillModel();
	}

    /**
     * @see de.westlb.mgb.client.mask.model.EmployeeListModel#getEmployeeId(int)
     */
    @Override
    public Long getEmployeeId(int row) {
        if (getEmployee(row) == null) {
        	return null;
        }
        
        return getEmployee(row).getEmployeeId();
    }
    
    @Override
    public void reload() {
    	if (searchResult != null) {
    		doSearch();
    	}
    	fillModel();
    }

}
