package de.westlb.mgb.client.mask.model.shared;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;

import de.westlb.mgb.client.application.RefreshHelper;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.TradeHistoryEntryVo;
import de.westlb.mgb.client.ui.selection_list.AbstractSelectionList;
import de.westlb.mgb.client.ui.selection_list.ReclamationStateCodeList;
import de.westlb.mgb.model.definition.ReclamationLevel;
import de.westlb.mgb.model.definition.StateCodeTypeDef;
import de.westlb_systems.xaf.util.table.swing.TablePoolComboBoxModel;

/**
 * GUI-Model for NewManualStateView
 *
 * @author Manfred Boerner
 */

public class NewTradeReclamationModel extends AbstractModel implements ReclamationLevelPanelModel
{

	private ReclamationStateCodeList reclamationStateCodeList = new ReclamationStateCodeList();
	private String RESOURCE_BASE = getResourceBase();
		
	public static final String 
		STATE_CB_MODEL = "stateComboBoxModel",
		COMMENT = "comment",
		ATTACHMENT_PATH = "attachmentPath"
	;
	
	
	/** Definition der Namen aller Properties im Model */
	private final String[] stdPropertyNames = new String[] {
		COMMENT,
	};
	
	private final String[] specialPropertyNames = new String[] {
		ReclamationLevelPanelModel.P_REM_LEVEL_1,
		ReclamationLevelPanelModel.P_REM_LEVEL_2,
		ReclamationLevelPanelModel.P_REM_ESCALATION,
		ReclamationLevelPanelModel.P_REM_OUTSIDE_SYS,
		STATE_CB_MODEL,
		ATTACHMENT_PATH,	
	};
	

	/**
	 * Default Konstruktor erstellt ein leeres Model
	 *
	 */
	public NewTradeReclamationModel() {
		setPropertyNames(stdPropertyNames);
		setPropertyNames(specialPropertyNames);
	}
	
	/**
	 * Fill the model from the bu>siness model.
	 *
	 */
	private void fillModel() {
		TradeHistoryEntryVo currentState = null;
		Object bo = getBusinessObject();
		if (bo != null) {
			try {
                currentState = MgbServiceFactory.getService().getCurrentReclamationState((Long)bo);
            } catch (RemoteException e) {
                handleRemoteException(e);
            }
		}
		
		// Default-Value for first reclamation
//        int level = ReclamationLevel.REM_LEVEL_1;
        int level = 0;
		
		if (currentState != null) {
			// Copy standard properties from the server value object to the gui model
			propagateProperties(stdPropertyNames, currentState);
			if (currentState.getReclamationLevel() != null) {
				level = currentState.getReclamationLevel().intValue() + 1;
			}
		} 

		setProperty(P_REM_LEVEL_1,		Boolean.valueOf(level == ReclamationLevel.REM_LEVEL_1));
		setProperty(P_REM_LEVEL_2,		Boolean.valueOf(level == ReclamationLevel.REM_LEVEL_2));
		setProperty(P_REM_ESCALATION,	Boolean.valueOf(level == ReclamationLevel.REM_ESCALATION));
		setProperty(P_REM_OUTSIDE_SYS,	Boolean.valueOf(level == ReclamationLevel.REM_LEVEL_OUTSIDE_SYSTEM));
		
		// do special conversions
		TablePoolComboBoxModel comboBoxModel = new TablePoolComboBoxModel(reclamationStateCodeList);
		setProperty(STATE_CB_MODEL, comboBoxModel);
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
		
		if (getBusinessObject() == null || ! (getBusinessObject() instanceof Long)) {
			String msg = getResourceString(RESOURCE_BASE + "E_001");
			logMessage(LOG_ERROR, msg);
			setError(APPLICATION_ERROR, RESOURCE_BASE + "E_001", msg);
			return false;
		}
		
		Long internalTradeId = (Long)getBusinessObject();

		// Properies auslesen
		String stateCode			= (String)AbstractSelectionList.getKeyFrom(getProperty(STATE_CB_MODEL));
		String comment				= (String)getProperty(COMMENT);
		String attachmentPath		= (String)getProperty(ATTACHMENT_PATH);
		
		int level;
		if (Boolean.TRUE.equals(getProperty(P_REM_LEVEL_1))) {
			level = ReclamationLevel.REM_LEVEL_1;
		} else if (Boolean.TRUE.equals(getProperty(P_REM_LEVEL_2))) {
			level = ReclamationLevel.REM_LEVEL_2;
		} else if (Boolean.TRUE.equals(getProperty(P_REM_ESCALATION))) {
			level =  ReclamationLevel.REM_ESCALATION;
		} else if (Boolean.TRUE.equals(getProperty(P_REM_OUTSIDE_SYS))) {
			level = ReclamationLevel.REM_LEVEL_OUTSIDE_SYSTEM;
		} else {
			level = -1;			
		}
		
		String attachmentName = null;
		if (attachmentPath != null) {
			attachmentName = new File(attachmentPath).getName();
		}
		
		// Check input
		if (stateCode == null) {
			setError(PROPERTY_ERROR);
			logMessage(LOG_ERROR, getResourceString(RESOURCE_BASE + "E_002"));
			return false;
		}
		
		TradeHistoryEntryVo tradeHistEntry = new TradeHistoryEntryVo();
		tradeHistEntry.setStateCode(stateCode);
		tradeHistEntry.setComment(comment);
		tradeHistEntry.setInternalTradeId(internalTradeId);		
		tradeHistEntry.setAttachmentName(attachmentName);
		tradeHistEntry.setType(StateCodeTypeDef.RECLAMATION);
		tradeHistEntry.setReclamationLevel(Integer.valueOf(level));

		FileInputStream inputStream = null;
		
		try {
			if (attachmentPath == null) {
           		MgbServiceFactory.getService().saveTradeState(tradeHistEntry, null);
			} else {
			    byte[] attachmentContent = null;
		        try {
		            inputStream = new FileInputStream(attachmentPath);
		            attachmentContent = new byte[(int) new File(attachmentPath).length()];
		            inputStream.read(attachmentContent);
		        } catch (Exception e) {
		        	setErrorDetails(e);
		        	return false;        	
		        }
           		MgbServiceFactory.getService().saveTradeState(tradeHistEntry, attachmentContent);					
			}
			RefreshHelper.registerUpdate(this, RefreshHelper.TRADE, getBusinessObject());
        } catch (RemoteException e) {
        	setErrorDetails(e);
        	return false;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException io){
                }
            }
        }
		return true;
	}

	/**
	 * Sets the business object from which the model gets its data.
	 * It exepts to get an internal trade id.
	 */
	@Override
    public void setBusinessObject(Object newBusinessObject) {
		super.setBusinessObject(newBusinessObject);
		fillModel();
	}
}
