/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.struts_client.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.westlb.mgb.client.server.Mgb;
import de.westlb.mgb.client.server.vo.MailVo;
import de.westlb.mgb.client.server.vo.TradeOverviewVo;


/**
 * Action that handels the submit of the TraderResponse form.
 *
 * @author WSY4148 
 */
public class TraderResponseSubmitAction extends MgbAction {
    /**
         *
         */
    public TraderResponseSubmitAction() {
        super();
    }

    /* 
     * Creates an email for the trader response. The email is sent by smtp
     * to the controller and stored in the database.
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
		String userId = assertNtlmAuthentication(request, response);

		String mailIdStr = request.getParameter("id");
		String text = request.getParameter("text");
		
		Mgb mgb = getMgbService(request, response, null);
		Long mailId =  new Long(mailIdStr);
		MailVo mailVo = mgb.getMail(mailId.longValue());
		TradeOverviewVo tradeVo = mgb.getTradeOverview(mailVo.getTradeId());

		if (Boolean.TRUE.equals(tradeVo.getReclamationIsClosed())) {
			return mapping.findForward("reclamationClosed");
		} else if (!mgb.isTradeAccessGranted(mailVo.getTradeId(), userId)){
			return mapping.findForward("tradeAccessDenied");
		} 
		
		mgb.sendTraderResponseMail(mailId, text);		
		return mapping.findForward("success");
    }
}