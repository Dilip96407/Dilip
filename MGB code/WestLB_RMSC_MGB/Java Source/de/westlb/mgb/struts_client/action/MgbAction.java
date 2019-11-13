/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.struts_client.action;

import java.rmi.RemoteException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import de.westlb.mgb.client.server.Mgb;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.server.security.AuthenticationException;
import de.westlb.mgb.server.security.AuthorizationException;
import de.westlb.mgb.server.security.LoginContext;
import de.westlb.mgb.server.security.SecurityUtils;


/**
 * Contains common functionality for all MGB struts actions.
 *
 * @author WSY4148
 */
public class MgbAction extends Action {
    private static final String ATTR_MGB_SERVICE = "MGB_SERVICE";
    private static final String ATTR_MGB_MANDANT = "MGB_MANDANT";
    
	public static final String FORWARD_SUCCESS = "success";
	public static final String FORWARD_FAILURE = "failure";

    /**
     * Add global error to saved errors
     *
     * @param request
     * @param property
     * @param error
     */
    protected void addError(HttpServletRequest request, String property, ActionMessage error) {
        ActionErrors savedErrors = (ActionErrors) request.getAttribute(Globals.ERROR_KEY);
        ActionErrors errors = (savedErrors == null) ? new ActionErrors() : savedErrors;
        errors.add(property, error);

        if (savedErrors == null) {
            saveErrors(request, errors);
        }
    }

    /**
     * add global error to saved errors
     *
     * @param request
     * @param error
     */
    protected void addError(HttpServletRequest request, ActionMessage error) {
        addError(request, ActionErrors.GLOBAL_MESSAGE, error);
    }

    /**
     * Asserts the authentication via the NTLM protocol.
     *
     * @param request
     * @param response
     *
     * @return the windows NT id of the user.
     *
     * @throws AuthorizationException Never thrown, because the authentication feature of
     *         SecurityUtils is not used for MGB.
     * @throws AuthenticationException Never thrown, because the authenti
     *
     * @see SecurityUtils
     */
    protected String assertNtlmAuthentication(HttpServletRequest request,
        HttpServletResponse response) throws AuthorizationException, AuthenticationException {
        LoginContext lc = SecurityUtils.checkAuthenticationThrowsException(request, response);

        return (lc == null) ? null : lc.getNtDomain()+"/"+lc.getNtUserId();
    }

    /**
     * Creates MGB service and performs NTML login of the current  user. The MGB service is cached
     * in the http session and returned.
     *
     * @param request
     * @param response
     *
     * @return A mgb service object. The implementation depends on the configuration in
     *         struts_client.properties.
     *
     * @throws AuthorizationException Never thrown, because the authentication feature of
     *         SecurityUtils is not used for MGB.
     * @throws AuthenticationException Occurs if the NTLM login fails.
     * @throws UnknownUserIdException If the user id of the user is unknown to the MGB system.
     * @throws RemoteException Throws in case of any internal problems of the MGB service.
     */
    protected Mgb getMgbService(HttpServletRequest request, HttpServletResponse response, String mandantCode)
        throws AuthorizationException, AuthenticationException, UnknownUserIdException, 
            RemoteException {
        Mgb mgbService = (Mgb) request.getSession().getAttribute(ATTR_MGB_SERVICE);
        String sessionMandantCode = (String) request.getSession().getAttribute(ATTR_MGB_MANDANT);

        if (mgbService != null && sessionMandantCode != null && sessionMandantCode.equals(mandantCode)) {
            return mgbService;
        }

        synchronized (MgbAction.class) {
            // Double check locking pattern
            if (mgbService == null || sessionMandantCode == null || !sessionMandantCode.equals(mandantCode)) {
                String ntId = assertNtlmAuthentication(request, response);
                mgbService = MgbServiceFactory.createService();

                try {
                    mgbService.login(ntId, mandantCode, request.getHeader("User-Agent"));
                } catch (RemoteException e) {
                    if ((e.getMessage() != null) && (e.getMessage().indexOf("NotAuthorized") >= 0)) {
                        throw new UnknownUserIdException(SecurityUtils.getLoginContext(request).getNtUserId());
                    }
                    throw e;
                }

                request.getSession().setAttribute(ATTR_MGB_SERVICE, mgbService);
                request.getSession().setAttribute(ATTR_MGB_MANDANT, mandantCode);
            }
        }

        return mgbService;
    }
}