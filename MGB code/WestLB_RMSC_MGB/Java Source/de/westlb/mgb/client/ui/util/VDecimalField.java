package de.westlb.mgb.client.ui.util;

import java.math.BigDecimal;
import java.text.ParseException;

import de.westlb_systems.xaf.ui.components.AbstractVTextField;
import de.westlb_systems.xaf.ui.components.text.DecimalDocument;

/**
 * A field to enter a float value {nn}.{nn}
 *
 * The text is parsed at focus lost. On error the input is ignored
 * and replaced by the latest valid content.
 * 
 * @author: Herbert Benkhoff
 */
public class VDecimalField extends AbstractVTextField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4319042858888558944L;

	public static final int DEFAULT_COLUMNS = 8;

	private BigDecimal minValue = null;
	private BigDecimal maxValue = null;
	private boolean clipGrenzen = false;
	
	private Object value = null;
	private CurrencyFormat format = null;

/**
 * Default Konstruktor
 *
 * Creation date: (7/10/00 10:46:14 AM)
 *
 */
public VDecimalField() {
	super();
	setDocument(new DecimalDocument());

	format = new CurrencyFormat();
	format.setMinimumFractionDigits(0);
	setDecimalPlaces(format.getMaximumFractionDigits());
	setFormater(format);
	setParser(format);
	setColumns(DEFAULT_COLUMNS);
	setDefaultFocusListenerEnabled(true);
	setHorizontalAlignment(RIGHT);

}

public VDecimalField(int columns, int decimalPlaces) {
	this();
	setColumns(columns);
	this.setDecimalPlaces(decimalPlaces);
}

/**
 * Rueckgabe des Feldinhalts als Object
 * 
 * Creation date: (03.07.00 15:31:21)
 *
 * @param parameter String
 * @return java.lang.Object
 */
@Override
public Object getValue() {
	return value;
}
/**
 * Insert the method's description here.
 * Creation date: (2/15/02 12:16:12 PM)
 * @param minValue java.math.BigDecimal
 * @param maxValue java.math.BigDecimal
 */
public void setGrenzen(BigDecimal newMinValue, BigDecimal newMaxValue) {
	minValue = newMinValue;
	maxValue = newMaxValue;
}
/**
 * Insert the method's description here.
 * Creation date: (2/15/02 1:03:52 PM)
 * @param doClip boolean
 */
public void setUseClipping(boolean useClipping) {
	clipGrenzen = useClipping;
}
/**
 * Setzen eines neuen Datums
 *
 * Ueberschreibt Methode der Superklasse um mehr ObjectTypen zu 
 * unterstuetzen
 * 
 * Creation date: (11.08.00 09:23:02)
 * @param newValue neues Datum
 */
@Override
public void setValue(Object newValue) {
	
	Object oValue = value;
	Object nValue = null;
	Object oldValue  = getValue();

	if (newValue instanceof String) {
		// Falls ein String uebergeben wurde -> parsen
		String text = ((String) newValue).trim();
		if (text.length() > 0) {
			if (getParser() != null) {
				try {
					nValue = getParser().parseObject(text);
				} catch (ParseException ex) {
					nValue = oValue;
				}
			} else {
				nValue = text;
			}
		}
	}

	if (newValue instanceof Number && !(newValue instanceof BigDecimal)) {
		nValue = new BigDecimal(((Number)newValue).doubleValue());	
	} else if (newValue instanceof Long) {
		nValue = new BigDecimal(((Long)newValue).longValue());
	}
	
	if (!(nValue instanceof BigDecimal)) {
		value = null;
		return;
	}

	if(minValue != null && minValue.compareTo((BigDecimal)nValue) > 0) {
		nValue = clipGrenzen ? minValue : oldValue;
	}
	if(maxValue != null && maxValue.compareTo((BigDecimal)nValue) < 0) {
		nValue = clipGrenzen ? maxValue : oldValue;
	}

	// neuen Wert sichern
	value = nValue;

	// Ausgabe aktualisieren
	if (value == null) {
		setText("");
	} else {
		if (getFormater() != null) {
			try {
				setText(getFormater().format(value));
			} catch (IllegalArgumentException ex) {
				setText(value.toString());
			}
		} else {
			setText(value.toString());
		}
	}
	setCaretPosition(0);

	fireValueChanged(oldValue, getValue());		
}

	public void setMaximumLength(int length) {
		((DecimalDocument)getDocument()).setMaxLength(length);
	}
	
	public void setDecimalPlaces(int places) {
		format.setMaximumFractionDigits(places);
		((DecimalDocument)getDocument()).setDecimalPlaces(places);	
	}
	
	public void setMaximumNumberOfDigits(int nrOfDigits) {
		((DecimalDocument)getDocument()).setMaxNrOfDigits(nrOfDigits);
	}
	
	public int getMaximumNumberOfDigits(int nrOfDigits) {
		return ((DecimalDocument)getDocument()).getMaxNrOfDigits();
	}

}
