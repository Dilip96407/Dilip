package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import de.westlb.mgb.client.server.Mgb;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.EmployeeSearchResultEntryVo;
import de.westlb.mgb.client.server.vo.EmployeeVo;
import de.westlb.mgb.client.server.vo.SessionInfoVo;
import de.westlb.mgb.client.ui.tablemodel.TableModel;
import de.westlb.mgb.client.ui.tablemodel.TableModelFactory;


/**
 * Gui model for the trade list view. 
 *
 * @author Manfred Boerner
 */

public class ReclamationEmployeeListModel extends AbstractModel {

	public static final String P_EMPLOYEE_TABLE_MODEL	= "employeeTableModel";
		
	/** Definition of all properties provided by the model to the view */
	private final String[] propertyNames = new String[] {
		P_EMPLOYEE_TABLE_MODEL, 
	};
	
	private EmployeeSearchResultEntryVo[] employeeSearchResult = null;
	private EmployeeVo currentEmployee = null;

	/**
	 * Default constructor to create an empty model
	 */
	public ReclamationEmployeeListModel() {
		setPropertyNames(propertyNames);
		fillModel();
	}


	public void doRemarkReclamation(int[] rows) {
		for (int i = 0; i < rows.length; i++) {
        	doRemarkReclamation(i);
        	if (getError() != NO_ERROR) {
        		break;
        	}
        }
	}

	@SuppressWarnings("unused")
    private EmployeeVo getCurrentEmployee() throws RemoteException {
		Mgb mgb = MgbServiceFactory.getService();
		if (currentEmployee == null) {
			 SessionInfoVo sessionInfo = mgb.getSessionInfo();
			 currentEmployee = sessionInfo.getEmployee(); 
		}
		
		return currentEmployee;
	}
	
	/**
	 * Method doSendEmail.
	 * @param i
	 */
	private void doRemarkReclamation(int row) {
		try {
			MgbServiceFactory.getService().saveReclStateAfterEmployeeReport(getEmployeeId(row));			
		} catch (RemoteException e) {
			handleRemoteException(e);
		}
	}
		    
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		try {
            employeeSearchResult = MgbServiceFactory.getService().findEmployeesByReclamationRequired();
            if (employeeSearchResult != null) {
    			TableModel tableModel = TableModelFactory.createTableModel("EmployeeTable", employeeSearchResult);
    			setProperty(P_EMPLOYEE_TABLE_MODEL, tableModel);	
            }
        } catch (RemoteException e) {
        	handleRemoteException(e);
        }                
		return;
	}
	
	
	private EmployeeSearchResultEntryVo getEmployee(int row) {
		if (employeeSearchResult == null || employeeSearchResult.length < row) {
			return null;
		}
		
		return employeeSearchResult[row];
	}
	
	/**
	 * Sets the business object where the model gets its data from.
	 */
	@Override
    public void setBusinessObject(Object newBusinessObject) {
		super.setBusinessObject(newBusinessObject);		
		fillModel();
	}

    /**
     * @see de.westlb.mgb.client.mask.model.EmployeeListModel#getEmployeeId(int)
     */
    public Long getEmployeeId(int row) {
        if (getEmployee(row) == null) {
        	return null;
        }
        
        return getEmployee(row).getEmployeeId();
    }
    
    @Override
    public void reload() {
    	fillModel();
    }

}
