package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.MgbSessionSingleton;
import de.westlb.mgb.client.server.vo.DualControlJobVo;
import de.westlb.mgb.client.ui.selection_list.DualControlJobEntityTypeList;
import de.westlb.mgb.client.ui.selection_list.DualControlJobOperationList;
import de.westlb.mgb.client.ui.tablemodel.BeanDifferenceTableModel;

/**
 * Gui model for the employee edit dialog
 *
 * @author Manfred Boerner
 */

public class DualControlJobEditModel extends AbstractModel {
	public static final String P_ENTITY_TYPE				= "entityType";
	public static final String P_OPERATION				= "operation";
	public static final String P_REQUESTED_BY			= "requestedByName";
	public static final String P_REQUEST_DATE			= "requestedDate";
	public static final String P_DIFFERENCE_TABLE_MODEL	= "differenceTableModel";


	private DualControlJobOperationList dualControlJobOperationList = new DualControlJobOperationList();
	private DualControlJobEntityTypeList dualControlJobEntityTypeList = new DualControlJobEntityTypeList();
		
	private String RESOURCE_BASE = getResourceBase();
		
	/** Definition of all properties provided by the model to the view */
	private final String[] stdPropertyNames = new String[] {
		P_REQUESTED_BY, 
		P_REQUEST_DATE,
	};
	
	private final String[] spcPropertyNames = new String[] {
		P_ENTITY_TYPE,
		P_OPERATION,
		P_DIFFERENCE_TABLE_MODEL,
	};
	
	private Long dualControlJobId;
	private DualControlJobVo dualControlJobVo = null;
		
	/**
	 * Default constructor to create an empty model
	 */
	public DualControlJobEditModel() {
		setPropertyNames(stdPropertyNames);
		setPropertyNames(spcPropertyNames);
	}
	
	/**
	 * Constructor filling the model with data from the business object
	 */
	public DualControlJobEditModel(Object businessObject) {
		this();
		setBusinessObject(businessObject);
	}
	
	/**
	 * Confirm the Dual control job
	 */
	public void doConfirm() {
		if (dualControlJobId == null) {
			return;
		}
		
		if (isOwnChange()) {
			logMessage(LOG_ERROR, getResourceString(RESOURCE_BASE + "E_002"), null);
			return;				
		}
		
		try {
            MgbServiceFactory.getService().confirmDualControlJobs(new Long[] { dualControlJobId } );
        } catch (RemoteException e) {
        	handleRemoteException(e);
        }
		
	}
		
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		dualControlJobVo = null;
		if (dualControlJobId != null) {
			try {
                dualControlJobVo = MgbServiceFactory.getService().getDualControlJobVo(dualControlJobId);
            } catch (RemoteException e) {
            	handleRemoteException(e);
            }
		}
		
		
		if (dualControlJobVo != null) {
			propagateProperties(stdPropertyNames, dualControlJobVo);
			
			setProperty(P_OPERATION, dualControlJobOperationList.get(dualControlJobVo.getOperation()));
			setProperty(P_ENTITY_TYPE, dualControlJobEntityTypeList.get(dualControlJobVo.getEntityType().toUpperCase()));
			
			BeanDifferenceTableModel tableModel = new BeanDifferenceTableModel(
				dualControlJobVo.getNames(),
				dualControlJobVo.getOldValues(),
				dualControlJobVo.getNewValues()
			);
			
			tableModel.setResourceBundle(getResourceBundle());
			tableModel.setResourceBase(RESOURCE_BASE + "TBL_H_");		
			setProperty(P_DIFFERENCE_TABLE_MODEL, tableModel);
		}
		
		return;
	}

	public boolean isOwnChange() {
		if (dualControlJobVo == null) {
			return false;
		}
		
		String ownUserId = null;
		try {
			ownUserId = MgbSessionSingleton.getInstance(false).getEmployee().getNtId();
        } catch (RemoteException e) {
        	return true;
        }		
				
		return ownUserId.equalsIgnoreCase(dualControlJobVo.getRequestedByNtId());		
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
		if (newBusinessObject instanceof Long) {
			dualControlJobId = (Long)newBusinessObject;			
		}
		super.setBusinessObject(newBusinessObject);		
		fillModel();
	}

    /**
     * @see de.westlb.mgb.client.mask.model.EmployeeOverviewModel#getEmployeeId()
     */
    public Long getDualControlJobId() {
        return dualControlJobId;
    }

}
