package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.CheckStateVo;
import de.westlb.mgb.client.server.vo.DataSelectionVo;
import de.westlb.mgb.client.server.vo.JobSearchParamsVo;
import de.westlb.mgb.client.server.vo.JobVo;
import de.westlb.mgb.client.server.vo.TradeSearchParamsVo;
import de.westlb.mgb.client.ui.tablemodel.TableModel;
import de.westlb.mgb.client.ui.tablemodel.TableModelFactory;

/**
 * GUI-Model for CheckStateView
 *
 * @author Manfred Boerner
 */

public class CheckStateModel extends AbstractModel implements CheckStateOverviewPanelModel {

	public static final String AUTO_STATE_TABLE_MODEL = "AutoStateTableModel";
	public static final String MANUAL_STATE_TABLE_MODEL = "ManualStateTableModel";
	
	private CheckStateVo checkState = null;
	
	/** Definition der Namen aller Properties im Model */
	private final String[] propertyNames = new String[] {
		AUTO_STATE_TABLE_MODEL, MANUAL_STATE_TABLE_MODEL,
		
		// Total values
		NO_TRADES_TOTAL, 
		NO_TRADES_MANUAL_CHECK_REQUIRED, 
		NO_TRADES_OK_AFTER_AUTOCHECK,
		NO_TRADES_RECLAMATION_REQUIRED,
		NO_TRADES_RECLAMATION_OPEN,
		NO_TRADES_RECLAMATION_CLOSED,
		NO_TRADES_LATE_ENTRY,
	};
	
	/**
	 * Default Konstruktor erstellt ein leeres Model
	 *
	 */
	public CheckStateModel() {
		setPropertyNames(propertyNames);
		fillModel();
	}
	
	/**
	 * Konstruktor mit gleichzeitigem Setzen des Business Objects
	 */
	public CheckStateModel(Object businessObject) {
		this();
		setBusinessObject(businessObject);
	}
	
/*	public Object getAutoStateId2(int row) {
		if (checkState == null) {
			return null;
		}
		
		
		Object stateId = null;
		if(getProperty(AUTO_STATE_TABLE_MODEL) instanceof AutoStatesStatisticTableModel) {
			AutoStatesStatisticTableModel table = (AutoStatesStatisticTableModel)getProperty(AUTO_STATE_TABLE_MODEL);
			stateId = table.getUnformattedValueAt(row, AutoStatesStatisticTableModel.STATE);
		}
		return stateId;
	}
	
	public Object getManualStateId2(int row) {
		Object stateId = null;
		if(getProperty(MANUAL_STATE_TABLE_MODEL) instanceof ManualStatesStatisticTableModel) {
			ManualStatesStatisticTableModel table = (ManualStatesStatisticTableModel)getProperty(MANUAL_STATE_TABLE_MODEL);
			stateId = table.getUnformattedValueAt(row, ManualStatesStatisticTableModel.STATE);
		}
		return stateId;	
	}
*/
	public Object getAutoStateId(int row) {
		if (checkState == null) {
			return null;
		}
		Object stateId = null;
		if(getProperty(AUTO_STATE_TABLE_MODEL) instanceof TableModel) {
			TableModel table = (TableModel)getProperty(AUTO_STATE_TABLE_MODEL);
			stateId = table.getUnfomattedKeyValue(row);
		}
		return stateId;
	}

	public Object getManualStateId(int row) {
		Object stateId = null;
		if(getProperty(MANUAL_STATE_TABLE_MODEL) instanceof TableModel) {
		    TableModel table = (TableModel)getProperty(MANUAL_STATE_TABLE_MODEL);
			stateId = table.getUnfomattedKeyValue(row);
		}
		return stateId;
	}

	/**
	 * Befuellen des Models aus dem Business Object
	 *
	 */
	private void fillModel() {
		checkState = null;
		
		try {
            checkState = MgbServiceFactory.getService().getCheckState();
    		TableModel tableModel;
    		tableModel = TableModelFactory.createTableModel("AutoStatesStatisticTable", checkState.getAutomaticStates());
    		setProperty(AUTO_STATE_TABLE_MODEL, tableModel);
    		
    		tableModel = TableModelFactory.createTableModel("ManualStatesStatisticTable", checkState.getManualStates());
    		setProperty(MANUAL_STATE_TABLE_MODEL, tableModel);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
		
		if (checkState == null) {
			return;
		}
		
		setProperty(NO_TRADES_TOTAL, 					Long.valueOf(checkState.getNoTradesToCheck()));
		setProperty(NO_TRADES_MANUAL_CHECK_REQUIRED,	Long.valueOf(checkState.getNoTradesManualCheckRequired()));
		setProperty(NO_TRADES_OK_AFTER_AUTOCHECK,		Long.valueOf(checkState.getNoTradesOkAfterAutoCheck()));
		setProperty(NO_TRADES_RECLAMATION_REQUIRED,		Long.valueOf(checkState.getNoTradesReclRequiredButNotHandled()));
		setProperty(NO_TRADES_RECLAMATION_OPEN,		    Long.valueOf(checkState.getNoTradesReclOpen()));
		setProperty(NO_TRADES_RECLAMATION_CLOSED,		Long.valueOf(checkState.getNoTradesReclClosed()));
		setProperty(NO_TRADES_LATE_ENTRY,				Long.valueOf(checkState.getNoTradesLateEntry()));
		
/*		AbstractTableModel tableModel;
		tableModel = new AutoStatesStatisticTableModel();
		tableModel.setData(checkState.getAutomaticStates());		
		tableModel.setResourceBundle(getResourceBundle());
		tableModel.setResourceBase("TBL_1_H_");		
		setProperty(AUTO_STATE_TABLE_MODEL, tableModel);
		
		tableModel = new ManualStatesStatisticTableModel();
		tableModel.setData(checkState.getManualStates());				
		tableModel.setResourceBundle(getResourceBundle());
		tableModel.setResourceBase("TBL_2_H_");
		setProperty(MANUAL_STATE_TABLE_MODEL, tableModel);
*/	}


	/**
	 * Setzt das Business Object aus dem das Model seine Daten bezieht
	 *
	 * @param newBusinessObject FastSearchParameter
	 */
	@Override
    public void setBusinessObject(Object newBusinessObject) {
		if (newBusinessObject instanceof TradeSearchParamsVo) {
			try {
				JobSearchParamsVo params = new JobSearchParamsVo();
				params.setJobIds(((TradeSearchParamsVo)newBusinessObject).getJobIds());
				JobVo[] jobs = MgbServiceFactory.getService().findAllJobs(params);
				DataSelectionVo dataSelection = MgbServiceFactory.getService().getCurrentDataSelection();
				dataSelection.setSelectedJobs(jobs);
				MgbServiceFactory.getService().setDataSelection(dataSelection);
			} catch (RemoteException e) {
	            handleRemoteException(e);
			}
		}
		super.setBusinessObject(newBusinessObject);
		
		fillModel();
	}
}
