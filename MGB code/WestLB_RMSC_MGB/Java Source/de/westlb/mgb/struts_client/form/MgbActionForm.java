/*
 * Created on Jan 16, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.struts_client.form;

import java.util.Locale;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;

import de.westlb.mgb.struts_client.MessageKeys;

/**
 * @author WSY4148
 *
 */
public class MgbActionForm extends ActionForm {
    /**
	 * 
	 */
	private static final long serialVersionUID = 998999648171416374L;
	protected void checkEmptyFields(String fieldValue, ActionErrors errs, String fieldName) {
        if (fieldValue == null || fieldValue.trim().length() == 0) {
            errs.add("error", new ActionMessage(MessageKeys.ERROR_FIELD_REQUIRED, fieldName));
        }
    }
	public void load(Object data, Locale locale) {
	}
}
