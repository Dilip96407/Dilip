package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.MgbSessionSingleton;
import de.westlb.mgb.client.server.vo.DualControlJobSearchParamsVo;
import de.westlb.mgb.client.server.vo.DualControlJobSearchResultEntryVo;
import de.westlb.mgb.client.ui.tablemodel.TableModel;
import de.westlb.mgb.client.ui.tablemodel.TableModelFactory;
import de.westlb_systems.xaf.swing.SDataModel;

/**
 * Gui model for the dialog which informs about open dual control jobs..
 *
 * @author Manfred Boerner
 */

public class DualControlJobInfoModel extends AbstractModel {

	public static final String P_TABLE_MODEL = "TableModel";
	private String RESOURCE_BASE = getResourceBase();

	private TableModel dualControlJobTableModel = null;	
	private DualControlJobSearchResultEntryVo[] searchResult;
	
	/** Definition of all properties provided by the model to the view */
	private final String[] propertyNames = new String[] {
		P_TABLE_MODEL, 
	};
	
	/**
	 * Default constructor to create an empty model
	 */
	public DualControlJobInfoModel() {
		setPropertyNames(propertyNames);
		fillModel();
	}
	
	
	public Long getDualControlJobId(int row) {
		if (searchResult == null || row >= searchResult.length) {
				return null;
		}
		
		return searchResult[row].getId();
	}

	public Long[] getDualControlJobIds(int[] rows) {
		if (searchResult == null || rows == null) {
				return null;
		}
		
		Long[] ids = new Long[rows.length];
		for (int i=0; i < rows.length; i++) {
			ids[i] = searchResult[rows[i]].getId()	;
		}
		
		return ids;
	}
	
	public void doConfirmDualControlJobIds(int[] rows) {
		Long[] ids = getDualControlJobIds(rows);

		if (isContainedOwnChange(rows)) {
			logMessage(LOG_ERROR, getResourceString(RESOURCE_BASE + "E_002"), null);
			return;				
		}
		
		try {
            MgbServiceFactory.getService().confirmDualControlJobs(ids);
        } catch (RemoteException e) {
        	handleRemoteException(e);
        }
		
	}
	
	public void doDeleteDualControlJobIds(int[] rows) {
		Long[] ids = getDualControlJobIds(rows);
		try {
            MgbServiceFactory.getService().deleteDualControlJobs(ids);
        } catch (RemoteException e) {
        	handleRemoteException(e);
        }
		
	}

	/**
	 * Return the table model of the new instruments.
	 */
	public SDataModel getDataModel() {
		// This method is used to check if the mask is to be displayed, so initialize
		// the model if it has not already been done.
		if (dualControlJobTableModel == null) {
			fillModel();
		}
		return dualControlJobTableModel;
		
	}
	
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		searchResult = new DualControlJobSearchResultEntryVo[0];
		
        try {
        	DualControlJobSearchParamsVo param = new DualControlJobSearchParamsVo();
        	param.setShowCommitedJobs(false);
        	param.setExcludedRequesterNtId(null);
            searchResult = MgbServiceFactory.getService().findDualControlJobs(param);
            if (searchResult.length > 0) {
                dualControlJobTableModel = TableModelFactory.createTableModel("DualControlJobTable", searchResult);
                setProperty(P_TABLE_MODEL, dualControlJobTableModel);
            }
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
						
	}

	public boolean isContainedOwnChange(int[] rows) {
		String ownUserId = null;;
		try {
			ownUserId = MgbSessionSingleton.getInstance(false).getEmployee().getNtId();
        } catch (RemoteException e) {
        	return true;
        }		
				
		for (int i=0; i < rows.length; i++) {
			String requestedByNtId = searchResult[rows[i]].getRequestedByNtId();
			if (ownUserId.equalsIgnoreCase(requestedByNtId)) {
				return true;				
			}
		}
		
		return false;		
	}

    @Override
    public void reload() {
		fillModel();
	}

    public int getSize() {
        return searchResult == null ? 0 : searchResult.length; 
    }

}
