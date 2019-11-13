package de.westlb.mgb.client.mask.model.shared;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;

import de.westlb.mgb.client.application.RefreshHelper;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.TradeHistoryEntryVo;
import de.westlb.mgb.client.ui.selection_list.AbstractSelectionList;
import de.westlb.mgb.client.ui.selection_list.ManualStateCodeList;
import de.westlb.mgb.model.definition.StateCodeTypeDef;
import de.westlb_systems.xaf.util.table.swing.TablePoolComboBoxModel;

/**
 * GUI-Model for NewManualStateView
 *
 * @author Manfred Boerner
 */

public class NewTradeStateModel extends AbstractModel 
{
	private ManualStateCodeList manualStateCodeList = new ManualStateCodeList();
	private String RESOURCE_BASE = getResourceBase();
		
	public static final String 
		P_MANUAL_STATE_CB_MODEL = "stateComboBoxModel",
		P_MANUAL_STATE = "stateCode",
		P_COMMENT = "comment",
		P_ATTACHMENT_PATH = "AttachmentPath",
		P_ATTACHMENT_ID = "attachmentId",
		P_RECLAMATION = "Reclamation"
	;
	
	
	/** Definition der Namen aller Properties im Model */
	private final String[] stdPropertyNames = new String[] {
		P_MANUAL_STATE,
		P_COMMENT,
		P_ATTACHMENT_ID,
	};
	
	private final String[] specialPropertyNames = new String[] {
		P_MANUAL_STATE_CB_MODEL,
		P_ATTACHMENT_PATH,
		P_RECLAMATION,
	};
	

	/**
	 * Default Konstruktor erstellt ein leeres Model
	 *
	 */
	public NewTradeStateModel() {
		setPropertyNames(stdPropertyNames);
		setPropertyNames(specialPropertyNames);
	}

	/**
	 * Fill the model from the bu>siness model.
	 *
	 */
	private void fillModel() {
		TablePoolComboBoxModel comboBoxModel = new TablePoolComboBoxModel(manualStateCodeList);
		setProperty(P_MANUAL_STATE_CB_MODEL, comboBoxModel);

		TradeHistoryEntryVo historyEntry = null;
		Long[] tradeIds = getTradeIds();

		if (tradeIds != null && tradeIds.length > 0) {
			try {
                historyEntry = MgbServiceFactory.getService().getTradeState(tradeIds[0]);
            } catch (RemoteException e) {
                handleRemoteException(e);
            }
		}
		
		if (historyEntry != null) {
			// Copy standard properties from the server value object to the gui model
			propagateProperties(stdPropertyNames, historyEntry);
		}
		
		// do special conversions
		initPropertyReclamation();
	}
	
	private void initPropertyReclamation() {
		if (getProperty(P_MANUAL_STATE_CB_MODEL) != null) {
			String stateCode = (String)AbstractSelectionList.getKeyFrom(getProperty(P_MANUAL_STATE_CB_MODEL));
			if (manualStateCodeList.getStateCodeVo(stateCode) != null) {
				setProperty(P_RECLAMATION, manualStateCodeList.getStateCodeVo(stateCode).getReclamationRequired());
			}
		}
		
	}
	
	public void updatePropertyReclamation() {
		initPropertyReclamation();
	}
	
	public Long[] getTradeIds() {
		return getBusinessObject() instanceof Long[] ? (Long[])getBusinessObject() : null;
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
		
		if (getBusinessObject() == null || ! (getBusinessObject() instanceof Long[])) {
			String msg = getResourceString( RESOURCE_BASE + "E_001");
			logMessage(LOG_ERROR, msg);
			setError(APPLICATION_ERROR, RESOURCE_BASE + "E_001", msg);
			return false;
		}
		
		Long[] internalTradeIds = getTradeIds();

		// Properies auslesen
		String stateCode = (String)AbstractSelectionList.getKeyFrom(getProperty(P_MANUAL_STATE_CB_MODEL));
		String comment   = (String)getProperty(P_COMMENT);
		String attachmentPath = (String)getProperty(P_ATTACHMENT_PATH);
		Long attachmentId = (Long)getProperty(P_ATTACHMENT_ID);
		
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
		tradeHistEntry.setAttachmentName(attachmentName);
		tradeHistEntry.setAttachmentId(attachmentId);
		tradeHistEntry.setType(StateCodeTypeDef.MANUAL);
		
        FileInputStream inputStream = null;
		try {
			if (attachmentPath == null) {
           		MgbServiceFactory.getService().saveTradeState(internalTradeIds, tradeHistEntry, null);
			} else {
			    byte[] attachmentContent = null;
		        File file = new File(attachmentPath);
		        try {
		            inputStream = new FileInputStream(attachmentPath);
		            attachmentContent = new byte[(int) file.length()];
		            inputStream.read(attachmentContent);
		        } catch (Exception e) {
		        	setErrorDetails(e);
		        	return false;        	
		        }
           		MgbServiceFactory.getService().saveTradeState(internalTradeIds, tradeHistEntry, attachmentContent);					
			}
        } catch (RemoteException e) {
            handleRemoteException(e);
        	return false;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException io){
                }
            }
        }
        
        for (int i = 0; i < internalTradeIds.length; i++) {
			RefreshHelper.registerUpdate(this, RefreshHelper.TRADE, internalTradeIds[i]);
        }
		return true;
	}

	/**
	 * Sets the business object from which the model gets its data.
	 * It exepts to get an internal trade id or an array of internal
	 * trade ids.
	 */
	@Override
    public void setBusinessObject(Object value) {
		Object newBusinessObject;
		if (value instanceof Long) {
			newBusinessObject = new Long[] {(Long)value};
		} else {
			newBusinessObject = value;
		}
		super.setBusinessObject(newBusinessObject);
		fillModel();
	}
}
