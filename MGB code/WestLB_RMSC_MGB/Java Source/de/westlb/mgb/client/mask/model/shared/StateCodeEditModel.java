package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;
import java.text.ParseException;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.StateCodeVo;
import de.westlb.mgb.client.ui.selection_list.AbstractSelectionList;
import de.westlb.mgb.client.ui.selection_list.ManualSampleStateCodeList;
import de.westlb.mgb.client.ui.selection_list.MarketDataRequestTypeList;
import de.westlb.mgb.client.ui.selection_list.ReclamationStateCodeList;
import de.westlb.mgb.client.ui.selection_list.StateTypeList;
import de.westlb.mgb.model.definition.StateCodeTypeDef;
import de.westlb_systems.xaf.util.table.TablePool;
import de.westlb_systems.xaf.util.table.swing.TablePoolComboBoxModel;

/**
 * Gui model for the employee edit dialog
 *
 * @author Manfred Boerner
 */

public class StateCodeEditModel extends AbstractModel {
	public static final String P_IS_CREATE_NEW				= "isCreateNew";
	public static final String P_STATE_CODE					= "stateCode";
	public static final String P_MANUAL_CHECK_REQUIRED		= "manualCheckRequired";
	public static final String P_RECLAMATION_REQUIRED		= "reclamationRequired";
	public static final String P_FINAL_STATE				= "finalState";
	public static final String P_SHORT_DESCRIPTION			= "shortDescription";
	public static final String P_LONG_DESCRIPTION			= "longDescription";
	public static final String P_COMMENT					= "comment";
	public static final String P_TYPE_CB_MODEL				= "typeComboBoxModel";
	public static final String P_TYPE						= "type";
	public static final String P_RECLAMATION_CODE_CB_MODEL	= "reclamationCodeCbModel";
	public static final String P_RECLAMATION_CODE			= "reclamationCode";
	public static final String P_MARKET_DATA_REQUEST_TYPE	= "marketDataRequestType";
	public static final String P_MARKET_DATA_REQUEST_TYPE_CB_MODEL = "marketDataRequestTypeCbModel";
	public static final String P_MANUAL_SAMPLE_PERCENTAGE	= "manualSamplePercentage";
	public static final String P_MANUAL_SAMPLE_CODE			= "manualSampleCode";
	public static final String P_MANUAL_SAMPLE_CODE_CB_MODEL= "manualSampleCodeCbModel";
	public static final String P_MCC_CHECKED				= "mccChecked";
		
	private String RESOURCE_BASE = getResourceBase();
	private TablePool.Item nullItem;
		
	/** Definition of all properties provided by the model to the view */
	private final String[] stdPropertyNames = new String[] {
		P_STATE_CODE, 
		P_FINAL_STATE,
		P_MANUAL_CHECK_REQUIRED,
		P_RECLAMATION_REQUIRED,
		P_SHORT_DESCRIPTION,
		P_LONG_DESCRIPTION,
		P_COMMENT,
		P_MCC_CHECKED,
		P_MANUAL_SAMPLE_PERCENTAGE,
	};
	
	private final String[] spcPropertyNames = new String[] {
		P_IS_CREATE_NEW,
		P_TYPE_CB_MODEL,
		P_TYPE,
		P_RECLAMATION_CODE_CB_MODEL,
		P_RECLAMATION_CODE,
		P_MARKET_DATA_REQUEST_TYPE,
		P_MARKET_DATA_REQUEST_TYPE_CB_MODEL,
		P_MANUAL_SAMPLE_CODE,
		P_MANUAL_SAMPLE_CODE_CB_MODEL,
	};

	private StateTypeList stateTypeList = new StateTypeList();
	private ManualSampleStateCodeList manualSampleStateCodeList = new ManualSampleStateCodeList();
	private ReclamationStateCodeList reclamationStateCodeList = new ReclamationStateCodeList();
	private MarketDataRequestTypeList marketDataRequestTypeList = new MarketDataRequestTypeList();
	
	/** The existing state code that we are editing */	
	private String stateCode;
	
	private StateCodeVo stateCodeVo;
		
	/**
	 * Default constructor to create an empty model
	 */
	public StateCodeEditModel() {
		setPropertyNames(stdPropertyNames);
		setPropertyNames(spcPropertyNames);
		nullItem = marketDataRequestTypeList.nullItemWith("");
	}
	
	/**
	 * Constructor filling the model with data from the business object
	 */
	public StateCodeEditModel(Object businessObject) {
		this();
		setBusinessObject(businessObject);
	}
	
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		if (stateCode != null) {
			try {
                stateCodeVo = MgbServiceFactory.getService().getStateCode(stateCode);
                setProperty(P_IS_CREATE_NEW, Boolean.FALSE);
            } catch (RemoteException e) {
            	handleRemoteException(e);
            }
		} else {
			setProperty(P_IS_CREATE_NEW, Boolean.TRUE);
		}

		setProperty("nullItem", nullItem);

		TablePoolComboBoxModel comboBoxModel = new TablePoolComboBoxModel(stateTypeList);
		setProperty(P_TYPE_CB_MODEL, comboBoxModel);

		comboBoxModel = new TablePoolComboBoxModel(reclamationStateCodeList);
		comboBoxModel.setNullItem(nullItem);
		setProperty(P_RECLAMATION_CODE_CB_MODEL, comboBoxModel);

		comboBoxModel = new TablePoolComboBoxModel(manualSampleStateCodeList);
		comboBoxModel.setNullItem(nullItem);
		setProperty(P_MANUAL_SAMPLE_CODE_CB_MODEL, comboBoxModel);

		comboBoxModel = new TablePoolComboBoxModel(marketDataRequestTypeList);
		comboBoxModel.setNullItem(nullItem);
		setProperty(P_MARKET_DATA_REQUEST_TYPE_CB_MODEL, comboBoxModel);
				
		if (stateCodeVo != null) {
			propagateProperties(stdPropertyNames, stateCodeVo);			
			// do special conversions
		
			setProperty(P_TYPE, AbstractSelectionList.getItemFor(stateTypeList, stateCodeVo.getType()));
			setProperty(P_RECLAMATION_CODE, AbstractSelectionList.getItemFor(reclamationStateCodeList, stateCodeVo.getReclamationCode()));
			setProperty(P_MANUAL_SAMPLE_CODE, AbstractSelectionList.getItemFor(manualSampleStateCodeList, stateCodeVo.getManualSampleCode()));

			setProperty(P_MARKET_DATA_REQUEST_TYPE, AbstractSelectionList.getItemFor(marketDataRequestTypeList, stateCodeVo.getMarketDataRequestType()));
		}
		
		return;
	}
	
	/**
     * Gets the currently selected statetype code
     */
    public String getType() {
    	Object typeProperty = getProperty(P_TYPE);
    
    	return (typeProperty == null) ? null : (String) AbstractSelectionList.getKeyFrom(typeProperty);
    }
    

	/**
     * Gets the currently selected statetype code
     */
    public String getReclamationCode() {
    	Object reclamationCodeProperty = getProperty(P_RECLAMATION_CODE);
    
    	return (reclamationCodeProperty == null) ? null : (String) AbstractSelectionList.getKeyFrom(reclamationCodeProperty);
    }
    
    /**
     * Returnes true, if edit model is used in create new entry mode and false in update mode.
     */
    public boolean isCreateNew() {
    	return Boolean.TRUE.equals(getProperty(P_IS_CREATE_NEW));
    }
	
    /**
     * Save Model Data
     */
    @Override
    public boolean saveModel() {
    	try {
    		if (!isModelChanged()) {
    			logMessage(LOG_INFO, null);
    			return true;
    		}
    		
    		Boolean finalState			= (Boolean) getProperty(P_FINAL_STATE);
    		Boolean reclamationRequired	= (Boolean) getProperty(P_RECLAMATION_REQUIRED);
    		Boolean manualCheckRequired = (Boolean) getProperty(P_MANUAL_CHECK_REQUIRED);
    		Boolean mccChecked			= (Boolean) getProperty(P_MCC_CHECKED);
    		String stateCode			= (String) getProperty(P_STATE_CODE);
    		String shortDescription		= (String) getProperty(P_SHORT_DESCRIPTION);
    		String longDescription 		= (String) getProperty(P_LONG_DESCRIPTION);
    		String comment		 		= (String) getProperty(P_COMMENT);
    		String type					= (String) AbstractSelectionList.getKeyFrom(getProperty(P_TYPE));
    		String reclamationStateCode = (String) AbstractSelectionList.getKeyFrom(getProperty(P_RECLAMATION_CODE));
			String manualSampleStateCode = (String) AbstractSelectionList.getKeyFrom(getProperty(P_MANUAL_SAMPLE_CODE));
			Integer manualSamplePercentage = null;
			if (manualSampleStateCode != null && manualSampleStateCode.length() > 1) {
				manualSamplePercentage = getIntegerProperty(P_MANUAL_SAMPLE_PERCENTAGE);
			} 
			String marketDataRequestType = (String) AbstractSelectionList.getKeyFrom(getProperty(P_MARKET_DATA_REQUEST_TYPE));
        // 	Check mandatory fields
			if (type == null || shortDescription == null || stateCode == null) {
				setError(PROPERTY_ERROR);
				logMessage(LOG_ERROR, getResourceString(RESOURCE_BASE + "E_001"));
            return false;
			}
			if (StateCodeTypeDef.AUTO.equals(type)) {
				marketDataRequestType = (String) AbstractSelectionList.getKeyFrom(getProperty(P_MARKET_DATA_REQUEST_TYPE));
			}
		
			if (StateCodeTypeDef.MANUAL.equals(type) || StateCodeTypeDef.SAMPLE.equals(type)) {
				manualCheckRequired = null;	
			} else if (StateCodeTypeDef.RECLAMATION.equals(type)) { 
				reclamationRequired = null;
				reclamationStateCode = null;
			} 
		
		// If reclamation is required, then the user has to enter a default reclamation code			
			if (Boolean.TRUE.equals(reclamationRequired) && reclamationStateCode == null)  {
				setError(PROPERTY_ERROR);
				logMessage(LOG_ERROR, getResourceString(RESOURCE_BASE + "E_001"));				
			}

			if (stateCodeVo == null) {
				stateCodeVo = new StateCodeVo();
				stateCodeVo.setCreateNew(true);
			}

			stateCodeVo.setFinalState(finalState);
			stateCodeVo.setReclamationRequired(reclamationRequired);
			stateCodeVo.setManualCheckRequired(manualCheckRequired);
			stateCodeVo.setShortDescription(shortDescription);
			stateCodeVo.setLongDescription(longDescription);
			stateCodeVo.setComment(comment);
			stateCodeVo.setType(type);
			stateCodeVo.setStateCode(stateCode);
			stateCodeVo.setMarketDataRequestType(marketDataRequestType);
			stateCodeVo.setReclamationCode(reclamationStateCode);
			stateCodeVo.setMccChecked(mccChecked);
			stateCodeVo.setManualSampleCode(manualSampleStateCode);
			stateCodeVo.setManualSamplePercentage(manualSamplePercentage);
			
        	MgbServiceFactory.getService().saveStateCode(stateCodeVo);
        } catch (RemoteException e) {
            handleRemoteException(e);
            return false;
        } catch (ParseException pe) {
        	setError(APPLICATION_ERROR);
            setErrorDetails(pe);
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
		if (newBusinessObject instanceof String) {
			stateCode = (String)newBusinessObject;			
		}
		super.setBusinessObject(newBusinessObject);		
		fillModel();
	}

    /**
     */
    public String getStateCode() {
        return (stateCode == null) ? null : stateCodeVo.getStateCode();
    }

}
