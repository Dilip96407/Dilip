/*
 * Created on Dec 9, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.client.ui.loader;

import java.rmi.RemoteException;

import de.westlb.mgb.client.server.Mgb;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.PriceCheckCategoryVo;
import de.westlb.mgb.client.ui.selection_list.BloombergCurrencyCodeList;
import de.westlb.mgb.client.ui.selection_list.MoneyMarketTimePeriodList;
import de.westlb.mgb.client.ui.util.MmkInstrumentCode;
import de.westlb.mgb.client.ui.util.MmkInstrumentCodeFormat;
import de.westlb.mgb.client.ui.util.poi.PoiLoader;

/**
 * @author WSY4148
 *
 * Folgendes auswählen, um die Schablone für den erstellten Typenkommentar zu ändern:
 * Fenster&gt;Benutzervorgaben&gt;Java&gt;Codegenerierung&gt;Code und Kommentare
 */
public class MmkPriceCheckCategoryLoader implements PoiLoader {
	private static final int COL_CURRENCY1 					= 0;
	private static final int COL_CURRENCY2					= 1;
	private static final int COL_MATURITY_CODE				= 2;
	private static final int COL_TOLERANCE					= 3;
	private static final int COL_IS_GENERATED_CROSS_CURRENCY = 4;
	private static final int COL_COUNT						= 5;
	
	private Mgb mgb = null;
	private MmkInstrumentCodeFormat format = new MmkInstrumentCodeFormat();
	private BloombergCurrencyCodeList bloombergCurrencyCodeList = new BloombergCurrencyCodeList();
	private MoneyMarketTimePeriodList maturityCodeList = new MoneyMarketTimePeriodList();
	
    /**
     * 
     */
    public MmkPriceCheckCategoryLoader() {
        super();
    }

    /* (Kein Javadoc)
     * @see de.westlb.mgb.client.ui.util.poi.PoiLoader#endLoad()
     */
    @Override
    public void endLoad() {
    }

    /* (Kein Javadoc)
     * @see de.westlb.mgb.client.ui.util.poi.PoiLoader#getColumnCount()
     */
    @Override
    public int getColumnCount() {
        return COL_COUNT;
    }

    /* (Kein Javadoc)
     * @see de.westlb.mgb.client.ui.util.poi.PoiLoader#getColumnDefaultValue(int)
     */
    @Override
    public Object getColumnDefaultValue(int col) {
        return null;
    }

    /* (Kein Javadoc)
     * @see de.westlb.mgb.client.ui.util.poi.PoiLoader#getColumnType(int)
     */
    @Override
    public int getColumnType(int col) {
    	int type;
		switch (col) {
        	case COL_TOLERANCE:
        		type = COLUMN_TYPE_DOUBLE; 
        		break;
			case COL_IS_GENERATED_CROSS_CURRENCY:
				type = COLUMN_TYPE_BOOLEAN; 
				break;
        	default:
				type = COLUMN_TYPE_STRING; 
        		break;
        }
        return type;
    }

    /* (Kein Javadoc)
     * @see de.westlb.mgb.client.ui.util.poi.PoiLoader#isColumnMandatory(int)
     */
    @Override
    public boolean isColumnMandatory(int col) {
        return col != COL_CURRENCY2 && col != COL_MATURITY_CODE;
    }
    

    /* (Kein Javadoc)
     * @see de.westlb.mgb.client.ui.util.poi.PoiLoader#startLoad()
     */
    @Override
    public void startLoad() {
    	mgb = MgbServiceFactory.getService();
    }

    /* (Kein Javadoc)
     * @see de.westlb.mgb.client.ui.util.poi.PoiLoader#storeRecord(java.lang.Object[])
     */
    @Override
    public String storeRecord(Object[] record) {
		// Don't import the automatic imported cross currency values    	
    	if (Boolean.TRUE.equals(record[COL_IS_GENERATED_CROSS_CURRENCY])) {
    		return null;
    	}
		String message = null;
		PriceCheckCategoryVo priceCheckCategory = new PriceCheckCategoryVo();
    	
    	MmkInstrumentCode code = new MmkInstrumentCode();
    	code.setCurrencyCode1((String)record[COL_CURRENCY1]);
		code.setCurrencyCode2((String)record[COL_CURRENCY2]);
		code.setMaturityCode((String)record[COL_MATURITY_CODE]);
    	
    	String name = format.format(code);
    	Double tolerance = (Double)record[COL_TOLERANCE];
    	
    	priceCheckCategory.setName(name);
    	priceCheckCategory.setPriceToleranceAbsolute(tolerance.doubleValue());
		priceCheckCategory.setPriceTolerancePercent(tolerance.doubleValue());

		try {
			mgb.savePriceCheckCategory(priceCheckCategory);
		} catch (RemoteException e) {
			message = "Save record failed!";
		}
		
        return message;
    }

    /* (Kein Javadoc)
     * @see de.westlb.mgb.client.ui.util.poi.PoiLoader#validateRecord(java.lang.Object[])
     */
    @Override
    public String validateRecord(Object[] record) {
    	String errorMsg = null;
    	
    	String currency1 = (String)record[COL_CURRENCY1];
    	String currency2 = (String)record[COL_CURRENCY2];
    	String maturityCode = (String)record[COL_MATURITY_CODE];
    	
    	if (bloombergCurrencyCodeList.get(currency1) == null) {
    		errorMsg = "Unknown currency < " + currency1 + ">";	
    	}

		if (currency2 != null && bloombergCurrencyCodeList.get(currency2) == null) {
			errorMsg = "Unknown currency < " + currency2 + ">";	
		}

		if (maturityCode != null && maturityCodeList.get(maturityCode) == null) {
			errorMsg = "Unknown maturity code < " + maturityCode + ">";	
		}

    	
        return errorMsg;
    }

}
