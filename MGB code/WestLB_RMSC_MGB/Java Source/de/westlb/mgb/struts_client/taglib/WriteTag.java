/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.struts_client.taglib;

import java.util.Calendar;

import javax.servlet.jsp.JspException;

/**
 * Same as struts bean write except that Calendar properties
 * are formatted in the same way as Date properties
 *
 * @author WSY4148 
 */
public class WriteTag extends org.apache.struts.taglib.bean.WriteTag {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4725241717999645885L;

	/*
     * Same as struts bean format value, except that Calendar properties
     * are formatted in the same way as Date properties
     */
    @Override
    protected String formatValue(Object value) throws JspException {
        if (value instanceof Calendar) {
            value = ((Calendar) value).getTime();
        }

        return super.formatValue(value);
    }
}