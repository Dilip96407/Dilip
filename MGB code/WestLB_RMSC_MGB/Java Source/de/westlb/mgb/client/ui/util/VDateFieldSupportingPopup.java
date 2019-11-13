/*
 * Created on Sep 1, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.client.ui.util;

import java.awt.Frame;
import java.util.Calendar;

import de.westlb_systems.xaf.swing.SDialog;

/**
 * @author wsy4148
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class VDateFieldSupportingPopup extends VAbstractComponentSupportingPopup {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8140673516286955391L;

	/**
	 * 
	 */
	public VDateFieldSupportingPopup() {
		super(new VDateField());
	}
	
	/* (non-Javadoc)
	 * @see de.westlb.mgb.client.ui.util.VAbstractFieldWithPopup#openPopup()
	 */
	@Override
    protected void openPopup() {
		Frame frame = SDialog.getRootFrame(this);
//		SDateSelectorPanel dateSelectorPanel = new SDateSelectorPanel((Calendar)getValue());
//		SDateSelectorDialog selector = new SDateSelectorDialog(frame, dateSelectorPanel);
		SDateSelectorDialog selector = new SDateSelectorDialog(frame);
		selector.setLocation(getPopupLocation());
		Calendar date = selector.select();
		setValue(date);
	}

	/* (non-Javadoc)
	 * @see de.westlb.mgb.client.ui.util.VAbstractComponentSupportingPopup#getComponentValue()
	 */
	@Override
    public Object getComponentValue() {
		return ((VDateField)getComponent()).getValue();
	}

	/* (non-Javadoc)
	 * @see de.westlb.mgb.client.ui.util.VAbstractComponentSupportingPopup#setComponentValue(java.lang.Object)
	 */
	@Override
    public void setComponentValue(Object newValue) {
		((VDateField)getComponent()).setValue(newValue);
	}

}
