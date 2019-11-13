package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import de.westlb.mgb.client.application.RefreshHelper;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.EmployeeVo;
import de.westlb.mgb.client.ui.tablemodel.TableModel;
import de.westlb.mgb.client.ui.tablemodel.TableModelFactory;

/**
 * Gui model for the employee edit dialog
 *
 * @author Manfred Boerner
 */

public class EmployeeEditModel extends AbstractModel implements EmployeeOverviewModel {
	public static final String P_LAST_NAME		= "lastName";
	public static final String P_FIRST_NAME		= "firstName";
	public static final String P_NT_ID			= "ntId";
	public static final String P_TELEFON		= "phone";
	public static final String P_EMAIL			= "email";
	public static final String P_TRADER			= "trader";
	public static final String P_TRADER_IDS_TABLE_MODEL	= "traderIdsTableModel";

		
	private String RESOURCE_BASE = getResourceBase();
		
	/** Definition of all properties provided by the model to the view */
	private final String[] stdPropertyNames = new String[] {
		P_LAST_NAME, 
		P_FIRST_NAME,
		P_NT_ID,
		P_TELEFON,
		P_EMAIL,
		P_TRADER,
	};
	
	private final String[] spcPropertyNames = new String[] {
		P_TRADER_IDS_TABLE_MODEL,
	};
	
	private EmployeeVo employee;
	private Long employeeId;
		
	/**
	 * Default constructor to create an empty model
	 */
	public EmployeeEditModel() {
		setPropertyNames(stdPropertyNames);
		setPropertyNames(spcPropertyNames);
	}
	
	/**
	 * Constructor filling the model with data from the business object
	 */
	public EmployeeEditModel(Object businessObject) {
		this();
		setBusinessObject(businessObject);
	}
	
	/**
	 * Delete a trader id.
	 */
	@Override
    public void doDeleteTrader(int row) {
        if (employee == null || employee.getTraderIds() == null || employee.getTraderIds().length < row) {
        	return ;
        }
        
		try {
            MgbServiceFactory.getService().deleteTrader(employee.getTraderIds()[row].getId());
        } catch (RemoteException e) {
        	handleRemoteException(e);
        }
	}
	
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		if (employeeId != null) {
			try {
                employee = MgbServiceFactory.getService().getEmployee(employeeId);
            } catch (RemoteException e) {
            	handleRemoteException(e);
            }
		}
		
		
		if (employee != null) {
			if (employee.getEmployeeId() != null) {
			       RefreshHelper.registerCache(this, RefreshHelper.EMPLOYEE, getBusinessObject());
	
			}
			
			propagateProperties(stdPropertyNames, employee);

			try {
			    TableModel tableModel = TableModelFactory.createTableModel("TraderTable", employee.getTraderIds());
			    setProperty(P_TRADER_IDS_TABLE_MODEL, tableModel);
            } catch (RemoteException e) {
            	handleRemoteException(e);
            }

/*			TraderTableModel tableModel = new TraderTableModel();
			tableModel.setData(employee.getTraderIds());		
			tableModel.setResourceBundle(getResourceBundle());
			tableModel.setResourceBase(RESOURCE_BASE + "TBL_1_H_");		
			setProperty(P_TRADER_IDS_TABLE_MODEL, tableModel);
*/		}
		
		return;
	}
	
        /**
        * Save Model Data
        */
        @Override
        public boolean saveModel() {
            if (!isModelChanged()) {
                logMessage(LOG_INFO, null);
                return true;
            }

            String lastName		= (String) getProperty(P_LAST_NAME);
            String firstName	= (String) getProperty(P_FIRST_NAME);
            String phone		= (String) getProperty(P_TELEFON);
            String eMail		= (String) getProperty(P_EMAIL);
            String ntId			= (String) getProperty(P_NT_ID);
            Boolean trader		= (Boolean) getProperty(P_TRADER);
 
            // Check mandatory fields
            if (lastName == null || firstName == null || eMail == null) {
                setError(PROPERTY_ERROR);
                logMessage(LOG_ERROR, getResourceString(RESOURCE_BASE + "E_002"));
                return false;
            }

			if (employee == null) {
				employee = new EmployeeVo();
			}

			employee.setLastName(lastName);
			employee.setFirstName(firstName);
			employee.setPhone(phone);
			employee.setEmail(eMail);
			employee.setNtId(ntId);
			if (trader != null) {
				employee.setTrader(trader.booleanValue());
			} else {
				employee.setTrader(false);
			}
			
            try {
            	Long newId = MgbServiceFactory.getService().saveEmployee(employee);
            	if (employee.getEmployeeId() != null) {
	                RefreshHelper.registerUpdate(this, RefreshHelper.EMPLOYEE, employee.getEmployeeId());
            	} else {
            		if (employee.getTraderIds() != null) {
	            		for (int i = 0; i < employee.getTraderIds().length; i++) {
		            		MgbServiceFactory.getService().assignTraderToEmployee(newId, employee.getTraderIds()[i].getId());
						}
					}
            	}
            } catch (RemoteException e) {
	            handleRemoteException(e);
                return false;
            }
            return true;
        }

	@Override
    public void reload() {
		fillModel();
	}
	
	/**
	 * Sets the business object where the model gets its data from.
	 */
	@Override
    public void setBusinessObject(Object newBusinessObject) {
		// Expects to get a traderID
		if (newBusinessObject instanceof EmployeeVo) {
			employee = (EmployeeVo)newBusinessObject;
		} else if (newBusinessObject instanceof Long) {
			employeeId = (Long)newBusinessObject;			
		}
		super.setBusinessObject(newBusinessObject);		
		fillModel();
	}

    /**
     * @see de.westlb.mgb.client.mask.model.EmployeeOverviewModel#getEmployeeId()
     */
    @Override
    public Long getEmployeeId() {
        return (employee == null) ? null : employee.getEmployeeId();
    }

}
