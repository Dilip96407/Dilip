/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.struts_client.action;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.westlb.mgb.client.server.Mgb;
import de.westlb.mgb.client.server.vo.MailVo;
import de.westlb.mgb.struts_client.RequestKeys;


/**
 * This action is called before the list of reclamations that
 * had been send by the controller to the trader is presented.
 * The list contains mails for open reclamations only.
 * 
 * @author WSY4148 
 */
public class ReclamationMailListAction extends MgbAction {
    /**
         *
         */
    public ReclamationMailListAction() {
        super();
    }

    /* (Kein Javadoc)
     * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response) throws Exception {

        Mgb mgb = getMgbService(request, response, null);
        MailVo[] mails = mgb.findTraderInboxMails();
        List<MailVo> mailList = Arrays.asList(mails);
        request.setAttribute(RequestKeys.MAIL_LIST, mailList);

        ActionForward forward = mailList.isEmpty() ? 
        	mapping.findForward("empty"): mapping.findForward("success");

        return forward;
    }
}