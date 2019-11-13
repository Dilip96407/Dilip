/*
 * Created on Jan 16, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.struts_client.action;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.westlb.mgb.client.server.Mgb;
import de.westlb.mgb.client.server.vo.MandantVo;
import de.westlb.mgb.client.server.vo.SessionInfoVo;
import de.westlb.mgb.struts_client.RequestKeys;

/**
 * @author WSY4148
 *
 */
public class NtlmAuthenticationAction extends MgbAction {

    static Logger logger = Logger.getLogger(NtlmAuthenticationAction.class);

    /**
     * 
     */
    public NtlmAuthenticationAction() {
        super();
    }

    /* (Kein Javadoc)
     * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        assertNtlmAuthentication(request, response);

        Mgb mgb = getMgbService(request, response, null);
        SessionInfoVo sessionInfo = mgb.getSessionInfo();
        List<MandantVo> mandants = Arrays.asList(sessionInfo.getMandants());
        request.setAttribute(RequestKeys.MANDANT_LIST, mandants);

        ActionForward forward = mapping.findForward("success");
        return forward;   
    }

}
