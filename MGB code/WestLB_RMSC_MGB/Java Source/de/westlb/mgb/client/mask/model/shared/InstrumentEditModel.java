package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.InstrumentVo;
import de.westlb.mgb.client.ui.selection_list.PriceCheckCategoryList;
import de.westlb_systems.xaf.util.table.TableLookup;
import de.westlb_systems.xaf.util.table.swing.TablePoolComboBoxModel;

/**
 * Gui model for the employee edit dialog
 *
 * @author Manfred Boerner
 */

public class InstrumentEditModel extends AbstractModel {
	public static final String P_PRICE_TOLERANCE_HIGH		= "lastName";
	public static final String P_PRICE_TOLERANCE_LOW		= "firstName";
	public static final String P_PRICE_TIME_TOLERANCE		= "ntId";

	public static final String P_INSTR_ISIN					= "isin";
	public static final String P_INSTR_NAME					= "instrumentName";
	public static final String P_INSTR_PRICE_CHECK_CB_MODEL	= "priceCheckComboBoxModel";
	public static final String P_INSTR_PRICE_CHECK_CATEGORY	= "priceCheckCategory";

	private String RESOURCE_BASE = getResourceBase();
	private Long instrumentId = null;
		
	
	/** Definition of all properties provided by the model to the view */
	private final String[] stdInstrumentPropertyNames = new String[] {
		P_INSTR_ISIN, 
		P_INSTR_NAME, 
	};

	private final String[] stdPriceCheckCategoryPropertyNames = new String[] {
		P_PRICE_TOLERANCE_HIGH, 
		P_PRICE_TOLERANCE_LOW,
		P_PRICE_TIME_TOLERANCE,
	};

	private final String[] specPropertyNames = new String[] {
		P_INSTR_PRICE_CHECK_CB_MODEL,
		P_INSTR_PRICE_CHECK_CATEGORY,
	};
	
	private PriceCheckCategoryList priceCheckCategoryList;		
	
	
	/**
	 * Default constructor to create an empty model
	 */
	public InstrumentEditModel() {
		priceCheckCategoryList = new PriceCheckCategoryList();
		setPropertyNames(stdInstrumentPropertyNames);
		setPropertyNames(stdPriceCheckCategoryPropertyNames);
		setPropertyNames(specPropertyNames);
	}
	
	/**
	 * Constructor filling the model with data from the business object
	 */
	public InstrumentEditModel(Object businessObject) {
		this();
		setBusinessObject(businessObject);
	}
		
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		InstrumentVo instrumentVo = null;
		if (instrumentId != null) {
			try {
                instrumentVo = MgbServiceFactory.getService().getInstrument(instrumentId);
            } catch (RemoteException e) {
            	handleRemoteException(e);
            }
		}

		setProperty(P_INSTR_PRICE_CHECK_CB_MODEL, new TablePoolComboBoxModel(priceCheckCategoryList));
		
		setProperty(P_INSTR_PRICE_CHECK_CATEGORY, new TablePoolComboBoxModel(priceCheckCategoryList));
		
		if (instrumentVo != null) {
			Object item = priceCheckCategoryList.itemFor(instrumentVo.getPriceCheckCategoryId());
			setProperty(P_INSTR_PRICE_CHECK_CATEGORY, item);
			propagateProperties(stdInstrumentPropertyNames, instrumentVo);
		}
				
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

            TableLookup.Item priceCheckCategoryItem = (TableLookup.Item)getProperty(P_INSTR_PRICE_CHECK_CATEGORY);
            // Check mandatory fields
            if (priceCheckCategoryItem == null) {
                setError(PROPERTY_ERROR);
                logMessage(LOG_ERROR, getResourceString(RESOURCE_BASE + "E_002"));
                return false;
            }
            
            Long priceCheckCategoryId = (Long)priceCheckCategoryItem.getObject();
			
           try {
            	MgbServiceFactory.getService().assignPriceCheckCategoryToInstrument(priceCheckCategoryId, instrumentId);
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
		// Expects to get a Instrument id
		if (newBusinessObject instanceof Long) {
			instrumentId = (Long)newBusinessObject;			
		}
		super.setBusinessObject(newBusinessObject);		
		fillModel();
	}

}
