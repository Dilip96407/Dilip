/*
 * Created on Feb 20, 2004
 * 
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.client.ui.util;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParsePosition;

import de.westlb_systems.xaf.ui.components.AbstractVTextField;
/**
 * @author WSY4148
 * 
 */
public class VArrayField extends AbstractVTextField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4839428374869786796L;
	public static final int DEFAULT_COLUMNS = 10;
	public static final String DEFAULT_DELIMITER = ",";

	private boolean isNumericArray = false;
	
	public VArrayField() {
		this(DEFAULT_COLUMNS, DEFAULT_DELIMITER, false);
	}
	
	public VArrayField(int columns) {
		this(DEFAULT_COLUMNS, DEFAULT_DELIMITER, false);
	}
	
	public VArrayField(int columns, String delimiter) {
		this(DEFAULT_COLUMNS, delimiter, false);
	}
	
	/**
	 *  
	 */
	public VArrayField(int columns, String delimiter, boolean isNumericArray) {
		super();
		setColumns(columns);
		this.isNumericArray = isNumericArray;
		ArrayFormat format = new ArrayFormat(delimiter, isNumericArray);
		setFormater(format);
		setParser(format);
		setDefaultFocusListenerEnabled(true);
	}
	
	@Override
    public Object getValue() {
		if (isNumericArray && super.getValue() instanceof Object[]) {
			Object[] tempValues = (Object[])super.getValue();
			if (tempValues != null && tempValues.length > 0) {
				if (tempValues[0] instanceof Long) {
		        	Long[] values = new Long[tempValues.length];
					for (int i = 0; i < tempValues.length; i++) {
	        			values[i] = (Long)tempValues[i];
					}
					return values;
				}
				if (tempValues[0] instanceof Double) {
					Double[] values = new Double[tempValues.length];
					for (int i = 0; i < tempValues.length; i++) {
	        			values[i] = (Double)tempValues[i];
					}
					return values;
				}
			}
		}
		return super.getValue();
	}

	
	private static class ArrayFormat extends Format {
		/**
         * 
         */
        private static final long serialVersionUID = 1253718234819511616L;
        private String delimiter = ",";
		private boolean isNumericArray = false;
		
		@SuppressWarnings("unused")
        public ArrayFormat() {
			super();
			this.delimiter = ",";
			this.isNumericArray = false;
		}
		/**
		 * @param delimiter
		 */
		public ArrayFormat(String delimiter, boolean isNumericArray) {
			super();
			this.delimiter = delimiter;
			this.isNumericArray = isNumericArray;
		}
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.text.Format#format(java.lang.Object,
		 *      java.lang.StringBuffer, java.text.FieldPosition)
		 */
		@Override
        public StringBuffer format(Object obj, StringBuffer toAppendTo,
				FieldPosition pos) {
			if (!(obj instanceof Object[])) {
				throw new IllegalArgumentException("Must be an Object[]");
			}
			Object[] objArray = (Object[]) obj;
			if (objArray.length > 0) {
				toAppendTo.append(objArray[0]);
			}
			for (int i = 1; i < objArray.length; i++) {
				toAppendTo.append(delimiter).append(objArray[i]);
			}
			return toAppendTo;
		}
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.text.Format#parseObject(java.lang.String,
		 *      java.text.ParsePosition)
		 */
		@Override
        public Object parseObject(String source, ParsePosition pos) {
			try {
				if (isNumericArray) {
					String[] s = source.split(delimiter);
					Object[] result = new Object[s.length]; 
					NumberFormat numberFormat = NumberFormat.getNumberInstance(); 
					for (int i = 0; i < s.length; i++) {
						result[i] = numberFormat.parse(s[i]);
					}
					pos.setIndex(source.length());
					return result;
				}
                Object[] result = source.split(delimiter);
                pos.setIndex(source.length());
                return result;
			} catch (Exception e ) {
				pos.setErrorIndex(0);
				return null;
			}
		}
	}
	
	
	
}