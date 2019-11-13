/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */

package de.westlb.mgb.struts_client.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import de.westlb.mgb.struts_client.MessageKeys;

/**
 * Form bean for the trader response page.  This form has the following fields,
 * with default values in square brackets:
 * <ul>
 * <li><b>text</b> - The message text of the response.  [REQUIRED]
 * </ul>
 *
 * @author WSY4148 
 */
public class TraderResponseForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3493252139718348981L;
	private String text;
	
    /* (non-Javadoc)
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void reset(ActionMapping arg0, HttpServletRequest request) {
        text = "";
    }

    /* (non-Javadoc)
     * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		if ((text == null) || (text.length() < 1)) {
				errors.add("text",  new ActionMessage(MessageKeys.ERROR_TEXT_REQUIRED));
		}
		
		return errors;
				
    }
	/**
	 * @return
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param string
	 */
	public void setText(String string) {
		text = string;
	}

}