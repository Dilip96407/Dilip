package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;
import java.text.ParseException;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.PriceCheckCategoryVo;
import de.westlb.mgb.client.ui.selection_list.AbstractSelectionList;
import de.westlb.mgb.client.ui.selection_list.ManualSampleStateCodeList;
import de.westlb_systems.xaf.util.table.TablePool;
import de.westlb_systems.xaf.util.table.swing.TablePoolComboBoxModel;

/**
 * Gui model for the trade list view. 
 *
 * @author Manfred Boerner
 */

public class PriceCheckCategoryEditModel extends AbstractModel {
	public static final String P_NAME							= "name";
	public static final String P_TIME_TOLERANCE_MINUTES			= "timeToleranceMinutes";
	public static final String P_PRICE_TOLERANCE_ABSOLUTE	= "priceToleranceAbsolute";
	public static final String P_PRICE_TOLERANCE_PERCENT	= "priceTolerancePercent";
	public static final String P_MANUAL_SAMPLE_PERCENTAGE		= "manualSamplePercentage";
	public static final String P_MANUAL_SAMPLE_CODE				= "manualSampleCode";
	public static final String P_MANUAL_SAMPLE_CODE_CB_MODEL	= "manualSampleCodeCbModel";

	private String RESOURCE_BASE = getResourceBase();
	
	private PriceCheckCategoryVo priceCheckCategoryVo = null;
	
	/** Definition of all properties provided by the model to the view */
	private final String[] stdPropertyNames = new String[] {
		P_NAME, 
		P_TIME_TOLERANCE_MINUTES,
		P_PRICE_TOLERANCE_ABSOLUTE,
		P_PRICE_TOLERANCE_PERCENT,		
		P_MANUAL_SAMPLE_PERCENTAGE,
	};

	private final String[] spcPropertyNames = new String[] {
		P_MANUAL_SAMPLE_CODE,
		P_MANUAL_SAMPLE_CODE_CB_MODEL,
	};

	private TablePool.Item nullItem;

	private ManualSampleStateCodeList manualSampleStateCodeList = new ManualSampleStateCodeList();

	/**
	 * Default constructor to create an empty model
	 */
	public PriceCheckCategoryEditModel() {
		setPropertyNames(stdPropertyNames);
		setPropertyNames(spcPropertyNames);
		nullItem = manualSampleStateCodeList.nullItemWith("");
	}
	
	/**
	 * Constructor filling the model with data from the business object
	 */
	public PriceCheckCategoryEditModel(Object businessObject) {
		this();
		setBusinessObject(businessObject);
	}
	
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		if (getPriceCheckCategoryId() == null) {
			return;
		}

		priceCheckCategoryVo = null;
		try {
            priceCheckCategoryVo = MgbServiceFactory.getService().getPriceCheckCategory(getPriceCheckCategoryId());
        } catch (RemoteException e) {
        	handleRemoteException(e);
        	return;
        }

		setProperty("nullItem", nullItem);

		TablePoolComboBoxModel comboBoxModel = new TablePoolComboBoxModel(manualSampleStateCodeList);
		comboBoxModel.setNullItem(nullItem);
		setProperty(P_MANUAL_SAMPLE_CODE_CB_MODEL, comboBoxModel);

		if (priceCheckCategoryVo != null) {
			propagateProperties(stdPropertyNames, priceCheckCategoryVo);
			setProperty(P_MANUAL_SAMPLE_CODE, AbstractSelectionList.getItemFor(manualSampleStateCodeList, priceCheckCategoryVo.getManualSampleCode()));
		}

		return;
	}

	/**
	 * Returns the casted business model
	 */
	public Long getPriceCheckCategoryId() {
		Object businessObject = getBusinessObject();
		if (!(businessObject instanceof Long)) {
			return null;
		}
		
		return (Long)businessObject;
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

        try {

	        // Check mandatory fields
	        if (getProperty(P_NAME) == null || 
	        	getProperty(P_PRICE_TOLERANCE_ABSOLUTE) == null ||
	        	getProperty(P_PRICE_TOLERANCE_ABSOLUTE) == null ||
	        	getProperty(P_TIME_TOLERANCE_MINUTES) == null) 
	        {
	            setError(PROPERTY_ERROR);
	            logMessage(LOG_ERROR, getResourceString(RESOURCE_BASE + "E_MANDATORY"));
	            return false;
	        }

	        String name = (String)getProperty(P_NAME);
			double priceToleranceMinusPercent	= getDoubleProperty(P_PRICE_TOLERANCE_ABSOLUTE, 0f);
			double priceTolerancePlusPercent	= getDoubleProperty(P_PRICE_TOLERANCE_PERCENT, 0f);
	        int timeToleranceInMinutes			= getIntProperty(P_TIME_TOLERANCE_MINUTES, 0);
			String manualSampleStateCode = (String) AbstractSelectionList.getKeyFrom(getProperty(P_MANUAL_SAMPLE_CODE));
			Integer manualSamplePercentage = null;
			if (manualSampleStateCode != null && manualSampleStateCode.length() > 1) {
				manualSamplePercentage = getIntegerProperty(P_MANUAL_SAMPLE_PERCENTAGE);
			}
			
			if (priceCheckCategoryVo == null ) {
				priceCheckCategoryVo = new PriceCheckCategoryVo();
			}
			
			priceCheckCategoryVo.setName(name);
	        priceCheckCategoryVo.setPriceToleranceAbsolute(priceToleranceMinusPercent);
			priceCheckCategoryVo.setPriceTolerancePercent(priceTolerancePlusPercent);
			priceCheckCategoryVo.setTimeToleranceMinutes(timeToleranceInMinutes);
			priceCheckCategoryVo.setManualSampleCode(manualSampleStateCode);
			priceCheckCategoryVo.setManualSamplePercentage(manualSamplePercentage);
					
            MgbServiceFactory.getService().savePriceCheckCategory(priceCheckCategoryVo);
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

		
	/**
	 * Sets the business object where the model gets its data from.
	 */
	@Override
    public void setBusinessObject(Object newBusinessObject) {
		super.setBusinessObject(newBusinessObject);		
		fillModel();
	}

    @Override
    public void reload() {
    	fillModel();
    }

}
