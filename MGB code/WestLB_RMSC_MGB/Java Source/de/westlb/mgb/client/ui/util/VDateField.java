/*
 * Created on Feb 20, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.client.ui.util;

import java.util.Calendar;

import de.westlb_systems.xaf.ui.components.AbstractVTextField;

/**
 * @author WSY4148
 *
 */
public class VDateField extends AbstractVTextField {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 7851402784553768899L;

	public VDateField() {
		this(10); 
	}
	
    /**
     * 
     */
    public VDateField(int columns) {
        super();
		DateFormat format = new DateFormat(DateFormat.DATE_FORMAT);
		setFormater(format);
		setParser(format);
		setColumns(columns);
		setDefaultFocusListenerEnabled(true);
    }

    /**
     * @param columns
     * @param dateFormat defined in DateFormat
     */
    public VDateField(int columns, int dateFormat) {
        super();
		DateFormat format = new DateFormat(dateFormat);
		setFormater(format);
		setParser(format);
		setColumns(columns);
		setDefaultFocusListenerEnabled(true);
    }

	/**
	 * Setzen eines neuen Datums
	 *
	 * Ueberschreibt Methode der Superklasse um mehr ObjectTypen zu 
	 * unterstuetzen
	 * 
	 */
	@Override
    public void setValue(Object newValue) {
		if(newValue instanceof String){
			super.setValue(newValue);
		} else
		if(newValue instanceof Calendar) {
			super.setValue(newValue);
		} else {
			super.setValue(null);
		}
	}

}
