/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */



package de.westlb.mgb.struts_client.action;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.westlb.mgb.client.applet.AppletParameterDef;
import de.westlb.mgb.server.security.LoginContext;
import de.westlb.mgb.server.security.SecurityUtils;


/**
 * Action that is executed before the controller applet is started.
 *
 * @author WSY4148 
 */
public class StartAppletAction extends MgbAction {

    public StartAppletAction() {
        super();
    }

    /* (Kein Javadoc)
     * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest req, HttpServletResponse resp)
        throws Exception {
			ActionForward forward = null;
			LoginContext lc = null;
			
			lc = SecurityUtils.checkAuthenticationThrowsException(req, resp);

			if (lc != null) {
			
				HttpSession session = req.getSession();
				session.setAttribute(AppletParameterDef.APPLET_PARAM_USER_ID, lc.getNtDomain()+"/"+lc.getNtUserId());
				session.setAttribute(AppletParameterDef.APPLET_PARAM_KEY, lc.getSessionKey());
				URL url = new URL(req.getScheme(), req.getServerName(), req.getServerPort(), "");
				session.setAttribute(AppletParameterDef.APPLET_PARAM_URL, url.toString());
				
				if (req.getParameter(AppletParameterDef.APPLET_PARAM_TRADE_ID) != null) {
					session.setAttribute(AppletParameterDef.APPLET_PARAM_CONTENT_ID, "MGB_TRADE_TAB");
					session.setAttribute(AppletParameterDef.APPLET_PARAM_CONTENT_PARAMETER, req.getParameter(AppletParameterDef.APPLET_PARAM_TRADE_ID));
				} else {
					session.removeAttribute(AppletParameterDef.APPLET_PARAM_CONTENT_ID);
					session.removeAttribute(AppletParameterDef.APPLET_PARAM_CONTENT_PARAMETER);					
				}
				
				if (req.getParameter(AppletParameterDef.APPLET_PARAM_MANDANT) != null) {
					session.setAttribute(AppletParameterDef.APPLET_PARAM_MANDANT, req.getParameter(AppletParameterDef.APPLET_PARAM_MANDANT));
				}
				
			}

        
			forward = mapping.findForward("success");
			return forward;
	}
}