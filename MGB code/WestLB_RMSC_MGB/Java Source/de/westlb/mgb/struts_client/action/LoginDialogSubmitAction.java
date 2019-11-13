/*
 * Created on Jan 16, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.struts_client.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.westlb.mgb.server.security.SecurityUtils;
import de.westlb.mgb.struts_client.form.LoginDialogForm;

/**
 * @author WSY4148
 *
 */
public class LoginDialogSubmitAction extends MgbAction {

	private static Logger logger = Logger.getLogger(LoginDialogSubmitAction.class);

    /**
     * 
     */
    public LoginDialogSubmitAction() {
        super();
    }

    /* (Kein Javadoc)
     * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	logger.debug("execute submit");
    	LoginDialogForm loginDialogForm = (LoginDialogForm)form;
    	if (isCancelled(request)) {
    		loginDialogForm.setPassword(null);
    		loginDialogForm.setUser(null);
    		return mapping.findForward("failure");
    	}
        String osUser = loginDialogForm.getOsUser();
        String user = loginDialogForm.getUser();
    	String password = loginDialogForm.getPassword();
    	logger.debug("user="+user+", password="+password+", osUser="+osUser);
    	if (user.equals(password)) {
    		SecurityUtils.getLoginContext(request, user);
    		return mapping.findForward("success");
    	}
        return mapping.findForward("failure");
    }

    public ActionForward executeCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	logger.debug("check");
    	return execute(mapping, form, request, response);
    }

}
