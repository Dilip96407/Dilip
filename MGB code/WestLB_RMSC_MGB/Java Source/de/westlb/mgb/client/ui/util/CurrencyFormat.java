package de.westlb.mgb.client.ui.util;

/*
 * Created on Dec 15, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import de.westlb_systems.xaf.util.SLocale;
import de.westlb_systems.xaf.util.text.FormatSymbols;

/**
 * Formater und Parser fuer einen Waehrungsbetrag
 *
 * Als Object Typ wird BigDecimal verwendet!

 */
public class CurrencyFormat extends java.text.Format {

    /**
     * 
     */
    private static final long serialVersionUID = 1687873183912301541L;

    private DecimalFormat decimalFormat = new DecimalFormat();

    private static final int		DEFAULT_MAXIMUM_FRACTION_DIGITS = 10;
    private static final int		DEFAULT_MINIMUM_FRACTION_DIGITS = 4;
    private static final boolean	DEFAULT_GROUPING_USED 			= true;
    private static final int 		DEFAULT_GROUPING_SIZE			= 3;

    /**
     * Default Konstruktor
     *
     * Creation date: (7/10/00 10:24:51 AM)
     *
     */
    public CurrencyFormat() {
        init();
        
		FormatSymbols s = FormatSymbols.getInstance(SLocale.getDefaultLocale());
		DecimalFormatSymbols ds = new DecimalFormatSymbols();
		ds.setDecimalSeparator(s.getDecimalSeparator());
		ds.setGroupingSeparator(s.getGroupingSeparator());
		decimalFormat.setDecimalFormatSymbols(ds);
    }

    public CurrencyFormat(int minimumFractionDigit, int maximumFractionDigits, boolean isGroupingUsed, int groupingSize) {
        setMinimumFractionDigits(minimumFractionDigit);
        setMaximumFractionDigits(maximumFractionDigits);
        setGroupingUsed(isGroupingUsed);
        setGroupingSize(groupingSize);

        FormatSymbols s = FormatSymbols.getInstance(SLocale.getDefaultLocale());
        DecimalFormatSymbols ds = new DecimalFormatSymbols();
        ds.setDecimalSeparator(s.getDecimalSeparator());
        ds.setGroupingSeparator(s.getGroupingSeparator());
        decimalFormat.setDecimalFormatSymbols(ds);
    }
    
    @Override
    public StringBuffer format(Object obj, StringBuffer toAppendTo, java.text.FieldPosition pos) {

        if (obj == null) {
            return toAppendTo;
        }

        if (obj instanceof Float ) {
            obj = new BigDecimal(((Float) obj).doubleValue());
        }

        if (obj instanceof Double) {
            obj = new BigDecimal(((Double) obj).doubleValue());
        }

        if (obj instanceof Integer) {
            obj = new BigDecimal(((Integer) obj).intValue());
        }

        if (!(obj instanceof BigDecimal)) {
            throw new IllegalArgumentException("Unexpected argument type " + obj.getClass().getName() + " of CurrencyFormat.format");
        }

        return decimalFormat.format(obj, toAppendTo, pos);
    }


    /**
     * Insert the method's description here.
     * Creation date: (2/15/02 10:52:46 AM)
     * @return int
     */
    public int getMaximumFractionDigits() {
        return decimalFormat.getMaximumFractionDigits();
    }

    /**
     * Insert the method's description here.
     * Creation date: (2/15/02 10:52:46 AM)
     * @return int
     */
    public int getMinimumFractionDigits() {
        return decimalFormat.getMinimumFractionDigits();
    }

    /**
     * Insert the method's description here.
     * Creation date: (2/15/02 10:52:46 AM)
     * @return int
     */
    public int getGroupingSize() {
        return decimalFormat.getGroupingSize();
    }

    /**
     * Insert the method's description here.
     * Creation date: (2/15/02 11:05:50 AM)
     * @param newLocale java.util.Locale
     */
    private void init() {
    	setMaximumFractionDigits(DEFAULT_MAXIMUM_FRACTION_DIGITS);
		setMinimumFractionDigits(DEFAULT_MINIMUM_FRACTION_DIGITS);
		setGroupingUsed(DEFAULT_GROUPING_USED);
		setGroupingSize(DEFAULT_GROUPING_SIZE);
    	
    }
    /**
     * Insert the method's description here.
     * Creation date: (2/15/02 10:52:46 AM)
     * @return boolean
     */
    public boolean isGroupingUsed() {
        return decimalFormat.isGroupingUsed();
    }
    
    /**
     * Parsen eines String
     *
     * Creation date: (7/10/00 10:31:33 AM)
     *
     * @param: source String der geparst werden soll
     * @param: status Position in source ab der geparst werden soll
     * @return: ein CalendarObject, bei Fehler null
     *          status enthaelt die Position bis der geparst wurde
     */
    @Override
    public Object parseObject(String source, java.text.ParsePosition status) {
        Object numberObject = decimalFormat.parseObject(source, status);
        if (numberObject != null) {
            return new BigDecimal(((Number)numberObject).doubleValue());
        } 
        return null;
    }

	public void setGroupingSize(int newValue) {
		decimalFormat.setGroupingSize(newValue);		
	}
	
    /**
     * Insert the method's description here.
     * Creation date: (2/15/02 10:52:46 AM)
     * @param newDecimalPlaces int
     */
    public void setMinimumFractionDigits(int newValue) {
        decimalFormat.setMinimumFractionDigits(newValue);
    }
    /**
     * Insert the method's description here.
     * Creation date: (2/15/02 10:52:46 AM)
     * @param newGroupingPlaces int
     */
    public void setMaximumFractionDigits(int newValue) {
        decimalFormat.setMaximumFractionDigits(newValue);
    }

    /**
     * Insert the method's description here.
     * Creation date: (2/15/02 10:52:46 AM)
     * @param newUseGrouping boolean
     */
    public void setGroupingUsed(boolean newUseGrouping) {
        decimalFormat.setGroupingUsed(newUseGrouping);
    }
}
