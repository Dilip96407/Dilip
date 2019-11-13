package de.westlb.mgb.client.mask.model.mmk;

import java.rmi.RemoteException;
import java.text.ParseException;

import de.westlb.mgb.client.mask.model.shared.AbstractModel;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.PriceCheckCategoryVo;
import de.westlb.mgb.client.ui.selection_list.BloombergCurrencyCodeList;
import de.westlb.mgb.client.ui.selection_list.MoneyMarketTimePeriodList;
import de.westlb.mgb.client.ui.util.MmkInstrumentCode;
import de.westlb.mgb.client.ui.util.MmkInstrumentCodeFormat;
import de.westlb_systems.xaf.util.table.TablePool;
import de.westlb_systems.xaf.util.table.swing.TablePoolComboBoxModel;


/**
 * Gui model for the price check category edit dialog in the mmk mandant.
 *
 * @author Manfred Boerner
 */

public class MmkPriceCheckCategoryEditModel extends AbstractModel {
	public static final String P_NAME					= "name";
	public static final String P_PRICE_TOLERANCE_PERCENT	= "priceTolerancePercent";
	public static final String P_PRICE_TOLERANCE_ABSOLUTE	= "priceToleranceAbsolute";
	public static final String P_CURRENCY1				= "currency1";
	public static final String P_CURRENCY1_CB_MODEL		= "currency1CbModel";
	public static final String P_CURRENCY2				= "currency2";
	public static final String P_CURRENCY2_CB_MODEL		= "currency2CbModel";
	public static final String P_MATURITY_CODE			= "maturityCode";
	public static final String P_MATURITY_CODE_CB_MODEL	= "maturityCodeCbModel";

	private String RESOURCE_BASE = getResourceBase();
	
	private PriceCheckCategoryVo priceCheckCategoryVo = null;
	private MmkInstrumentCodeFormat mmkInstrumentCodeFormat = new MmkInstrumentCodeFormat();
	private BloombergCurrencyCodeList bloombergCurrencyCodeList = new BloombergCurrencyCodeList();
	private MoneyMarketTimePeriodList maturityCodeList = new MoneyMarketTimePeriodList();
	private TablePool.Item nullItem;
	
	/** Definition of all properties provided by the model to the view */
	private final String[] stdPropertyNames = new String[] {
		P_NAME, 
	};

	private final String[] spcPropertyNames = new String[] {
		P_PRICE_TOLERANCE_PERCENT, 
		P_PRICE_TOLERANCE_ABSOLUTE, 
		P_CURRENCY1,
		P_CURRENCY1_CB_MODEL,
		P_CURRENCY2,
		P_CURRENCY2_CB_MODEL,
		P_MATURITY_CODE,
		P_MATURITY_CODE_CB_MODEL,
	};
	
	/**
	 * Default constructor to create an empty model
	 */
	public MmkPriceCheckCategoryEditModel() {
		setPropertyNames(stdPropertyNames);
		setPropertyNames(spcPropertyNames);
		nullItem = bloombergCurrencyCodeList.nullItemWith("  ");
	}
	
	/**
	 * Constructor filling the model with data from the business object
	 */
	public MmkPriceCheckCategoryEditModel(Object businessObject) {
		this();
		setBusinessObject(businessObject);
	}
	
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		TablePoolComboBoxModel comboBoxModel;
		
		comboBoxModel = new TablePoolComboBoxModel(bloombergCurrencyCodeList);
		setProperty(P_CURRENCY1_CB_MODEL, comboBoxModel);
		
		comboBoxModel = new TablePoolComboBoxModel(bloombergCurrencyCodeList);
		comboBoxModel.setNullItem(nullItem);
		setProperty(P_CURRENCY2_CB_MODEL, comboBoxModel);

		comboBoxModel = new TablePoolComboBoxModel(maturityCodeList);
		setProperty(P_MATURITY_CODE_CB_MODEL, comboBoxModel);
		
		// No PriceCheckCategoryId means we are editing a new entity. 
		if (getPriceCheckCategoryId() == null) {
			priceCheckCategoryVo = new PriceCheckCategoryVo();
			return;
		}

		priceCheckCategoryVo = null;
		try {
            priceCheckCategoryVo = MgbServiceFactory.getService().getPriceCheckCategory(getPriceCheckCategoryId());
        } catch (RemoteException e) {
        	handleRemoteException(e);
        	return;
        }
        
		setDoubleProperty(P_PRICE_TOLERANCE_PERCENT, priceCheckCategoryVo.getPriceTolerancePercent(), format3GMin0Max10);
		setDoubleProperty(P_PRICE_TOLERANCE_ABSOLUTE, priceCheckCategoryVo.getPriceToleranceAbsolute(), format3GMin0Max10);
 		try {
            MmkInstrumentCode code = (MmkInstrumentCode)mmkInstrumentCodeFormat.parseObject(priceCheckCategoryVo.getName());
            setProperty(P_CURRENCY1, bloombergCurrencyCodeList.itemFor(code.getCurrencyCode1()));
			setProperty(P_MATURITY_CODE, maturityCodeList.itemFor(code.getMaturityCode()));
            if (code.getCurrencyCode2() != null) {
				setProperty(P_CURRENCY2, bloombergCurrencyCodeList.itemFor(code.getCurrencyCode2()));
            }
            updateName();
        } catch (ParseException e1) {
            handleRemoteException(e1);
        }
 		       
        
        propagateProperties(stdPropertyNames, priceCheckCategoryVo);				
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
	
	public void updateName() {
		String currencyCode1 = (String)BloombergCurrencyCodeList.getKeyFrom(getProperty(P_CURRENCY1));
		String currencyCode2 = (String)BloombergCurrencyCodeList.getKeyFrom(getProperty(P_CURRENCY2));
		String maturityCode = (String)MoneyMarketTimePeriodList.getKeyFrom(getProperty(P_MATURITY_CODE));
		
		MmkInstrumentCode mmkInstrumentCode = new MmkInstrumentCode();		
		mmkInstrumentCode.setCurrencyCode1(currencyCode1);
		mmkInstrumentCode.setCurrencyCode2(currencyCode2);
		mmkInstrumentCode.setMaturityCode(maturityCode);
		setProperty(P_NAME,mmkInstrumentCodeFormat.format(mmkInstrumentCode));
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
			// Ensure name property to be uptodate
			updateName();
			
			String name = (String)getProperty(P_NAME) ;
			Double priceTolerancePercent = getDoubleProperty(P_PRICE_TOLERANCE_PERCENT, format3GMin0Max10);
			Double priceToleranceAbsolute = getDoubleProperty(P_PRICE_TOLERANCE_ABSOLUTE, format3GMin0Max10);

	        // Check mandatory fields
	        if (name == null || (priceTolerancePercent == null && priceToleranceAbsolute == null)) {
	            setError(PROPERTY_ERROR);
	            logMessage(LOG_ERROR, getResourceString(RESOURCE_BASE + "E_MANDATORY"));
	            return false;
	        }
	        
			priceCheckCategoryVo.setName(name);
			if (priceToleranceAbsolute != null) {
			    priceCheckCategoryVo.setPriceToleranceAbsolute(priceToleranceAbsolute.doubleValue());
			}
			if (priceTolerancePercent != null) {
			    priceCheckCategoryVo.setPriceTolerancePercent(priceTolerancePercent.doubleValue());
			}
            MgbServiceFactory.getService().savePriceCheckCategory(priceCheckCategoryVo);
        } catch (RemoteException e) {
            handleRemoteException(e);
            return false;
        } catch (ParseException pE) {
        	setError(APPLICATION_ERROR);
            setErrorDetails(pE);
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
