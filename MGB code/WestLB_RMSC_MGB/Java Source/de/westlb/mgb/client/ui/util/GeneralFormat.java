/*
 * Created on Sep 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.client.ui.util;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * @author d055625
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class GeneralFormat extends Format {

	/**
     * 
     */
    private static final long serialVersionUID = 3607444681178591923L;
    protected CurrencyFormat format3GMin4Max10;
	protected CurrencyFormat format3GMin0Max0;
	protected DateFormat timeFormat; 

     public GeneralFormat() {
        super();
		timeFormat = new DateFormat(DateFormat.TIME_FORMAT); 
		
		format3GMin4Max10 = new CurrencyFormat();
		format3GMin4Max10.setMinimumFractionDigits(4);
		format3GMin4Max10.setMaximumFractionDigits(10);
		format3GMin4Max10.setGroupingUsed(true);
		format3GMin4Max10.setGroupingSize(3);
		
		format3GMin0Max0 = new CurrencyFormat();
		format3GMin0Max0.setMinimumFractionDigits(0);
		format3GMin0Max0.setMaximumFractionDigits(0);
		format3GMin0Max0.setGroupingUsed(true);
		format3GMin0Max0.setGroupingSize(3);    
	}    

    /* (non-Javadoc)
     * @see java.text.Format#format(java.lang.Object, java.lang.StringBuffer, java.text.FieldPosition)
     */
    @Override
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        String val = "";
		if (obj != null) {
		    if (obj instanceof Calendar || obj instanceof GregorianCalendar) {
		        val = timeFormat.formatObject(obj);
		    } else if (obj instanceof Float || obj instanceof Double) {
		        val = format3GMin4Max10.format(obj);
		    } else if (obj instanceof Integer) {
		        val = format3GMin0Max0.format(obj);
		    } else {
		        val = obj.toString();
		    }
		}
        return toAppendTo.append(val);
    }

    /* (non-Javadoc)
     * @see java.text.Format#parseObject(java.lang.String, java.text.ParsePosition)
     */
    @Override
    public Object parseObject(String source, ParsePosition pos) {
        Object result = null;
        result = format3GMin4Max10.parseObject(source,pos);
        if (result == null) {
        	result = format3GMin0Max0.parseObject(source,pos);
        }
        if (result == null) {
        	result = timeFormat.parseObject(source,pos);
        }
        if (result == null) {
        	result = source;
        }
        return result;
    }

}
