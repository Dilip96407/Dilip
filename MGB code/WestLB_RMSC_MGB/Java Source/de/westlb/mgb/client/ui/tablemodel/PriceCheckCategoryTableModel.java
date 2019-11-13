package de.westlb.mgb.client.ui.tablemodel;

import java.lang.reflect.InvocationTargetException;

import de.westlb.mgb.client.server.vo.PriceCheckCategoryVo;
import de.westlb.mgb.client.ui.selection_list.BloombergCurrencyCodeList;
import de.westlb.mgb.client.ui.selection_list.MoneyMarketTimePeriodList;
import de.westlb.mgb.client.ui.util.MmkInstrumentCode;
import de.westlb.mgb.client.ui.util.MmkInstrumentCodeFormat;

/**
 * @author WSY4148
 *
 * Adapter to convert the employee search result returned
 * by the mgb service to a gui table model.
 * 
 * @deprecated
 */
public class PriceCheckCategoryTableModel extends AbstractTableModel {
	/** Column definitions */
	public static final int COLUMN_NAME								= 0;
	public static final int COLUMN_PRICE_TOLERANCE_MINUS_PERCENT	= 1;
	public static final int COLUMN_PRICE_TOLERANCE_PLUS_PERCENT		= 2;
	public static final int COLUMN_TIME_TOLERANCE_MINUTES			= 3;
	public static final int COLUMN_CHANGE_PENDING					= 4;
	public static final int COLUMN_CHANGE_REQUESTED_BY_NAME			= 5;
	public static final int COLUMN_CURRENCY1						= 6;
	public static final int COLUMN_CURRENCY2						= 7;
	public static final int COLUMN_MATURITY_CODE					= 8;
	public static final int COLUMN_IS_GENERATED_CROSS_CURRENCY		= 9;
	
	private String[] headerKeys = new String[]{
		"name",
		"priceToleranceMinusPercent",		
		"priceTolerancePlusPercent",
		"timeToleranceMinutes",
		"changePending",
		"changeRequestedByName",
		"currency1",
		"currency2",
		"maturityCode",
		"isGeneratedCrossCurrency"
	};

	private String[] propertyNames = new String[] {
		"name",
		"priceToleranceMinusPercent",		
		"priceTolerancePlusPercent",
		"timeToleranceMinutes",
		"changePending",
		"changeRequestedByName",
		"",
		"",
		"",
		"",
	};
	
	private MmkInstrumentCodeFormat mmkInstrumentCodeFormat = new MmkInstrumentCodeFormat();
	private MoneyMarketTimePeriodList maturityCodeList = new MoneyMarketTimePeriodList();
	private BloombergCurrencyCodeList bloombergCurrencyCodeList = new BloombergCurrencyCodeList();

    /**
     * @see de.westlb_systems.xaf.swing.SDataModel2#getColumnType(int)
     */
    @Override
    public int getColumnType(int column) {
		int value;
    	switch (getColumnIndexInModel(column)) {
    		case COLUMN_PRICE_TOLERANCE_MINUS_PERCENT:
    		case COLUMN_PRICE_TOLERANCE_PLUS_PERCENT:
    		case COLUMN_TIME_TOLERANCE_MINUTES:
    			value = NUMBER;
    			break;
			case COLUMN_CHANGE_PENDING:
        	case COLUMN_IS_GENERATED_CROSS_CURRENCY:
        		value = BOOLEAN;
        		break;
        	default:
		        value = super.getColumnType(column);	
        		break;
        }
        return value;
    } 	
    /**
     * Constructor for AutomaticStateTableModel.
     */
    public PriceCheckCategoryTableModel() {
        super();

        setHeaderKeys(headerKeys);
        setPropertyNames(propertyNames);
    }
    
    /* (Kein Javadoc)
     * @see de.westlb.mgb.client.ui.tablemodel.AbstractTableModel#calculateTableValue(java.lang.Object, int)
     */
    @Override
    protected Object calculateTableValue(Object businessModelObj, int col)
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Object value = null;
		MmkInstrumentCode code;
        switch (col) {
        	case COLUMN_PRICE_TOLERANCE_MINUS_PERCENT:
				//value = currencyFormat.format(new Double(priceCheckCategory.getPriceToleranceMinusPercent()));
				value = super.calculateTableValue(businessModelObj, col);
				break;
        	case COLUMN_PRICE_TOLERANCE_PLUS_PERCENT:
				//value = currencyFormat.format(new Double(priceCheckCategory.getPriceTolerancePlusPercent()));
				value = super.calculateTableValue(businessModelObj, col);
				break;
        	case COLUMN_CURRENCY1:
        		code = getInstrumentCode(businessModelObj);
        		if (code != null) {
					value = code.getCurrencyCode1();
        		}
        		break;
			case COLUMN_CURRENCY2:
				code = getInstrumentCode(businessModelObj);
				if (code != null) {
					value = code.getCurrencyCode2();
				}
				break;
			case COLUMN_MATURITY_CODE:
				code = getInstrumentCode(businessModelObj);
				if (code != null) {
					value = code.getMaturityCode();
				}
				break;
			case COLUMN_IS_GENERATED_CROSS_CURRENCY:
				code = getInstrumentCode(businessModelObj);
				if (code != null) {
					value = code.isGeneratedCrossCurrency() ? Boolean.TRUE : Boolean.FALSE;
				}
			break;
			
        	default:
        		value = super.calculateTableValue(businessModelObj, col);
        		break;
        }
        
        return value;
    }
    
    private MmkInstrumentCode getInstrumentCode(Object businessObject) {
    	MmkInstrumentCode retCode = null;
    	
		PriceCheckCategoryVo priceCheckCategory = (PriceCheckCategoryVo)businessObject;
		if (priceCheckCategory.getName() != null) {
			try {
				retCode = (MmkInstrumentCode)mmkInstrumentCodeFormat.parseObject(priceCheckCategory.getName());
			} catch (Exception e) {
			}
		}
		
		return retCode;
    }

    /* (Kein Javadoc)
     * @see de.westlb_systems.xaf.swing.SDataModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt(int row, int col) {
		Object value = super.getValueAt(row, col);
		Object newValue = value;	
		switch (getColumnIndexInModel(col)) {
			case COLUMN_CURRENCY1:
				newValue = bloombergCurrencyCodeList.itemFor(value);
				break;
			case COLUMN_CURRENCY2:
				newValue = bloombergCurrencyCodeList.itemFor(value);
				break;
			case COLUMN_MATURITY_CODE:
				newValue = maturityCodeList.itemFor(value);
				break;
			default:
				break;
		}
		return newValue;
    }

}
