/*
 * Created on Jan 16, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
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
import de.westlb.mgb.model.definition.MailTypeDef;
import de.westlb.mgb.struts_client.RequestKeys;

/**
 * @author WSY4148
 *
 */
public class TraderResponseAction extends MgbAction {

    /**
     * 
     */
    public TraderResponseAction() {
        super();
    }

    /* (Kein Javadoc)
     * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = assertNtlmAuthentication(request, response);

        Mgb mgb = getMgbService(request, response, null);
		String mailId = request.getParameter("mailId");
		
		MailVo mailVo = mgb.getMail(Long.parseLong(mailId));
		TradeOverviewVo tradeVo = mgb.getTradeOverview(mailVo.getTradeId());
		MailVo[] mailChildList = mgb.getChildMails(mailVo.getId(), false, new String[] { MailTypeDef.TRADER_RESPONSE} );

		if (Boolean.TRUE.equals(tradeVo.getReclamationIsClosed())) {
			return mapping.findForward("reclamationClosed");
		} else if (!mgb.isTradeAccessGranted(mailVo.getTradeId(), userId)){
			return mapping.findForward("tradeAccessDenied");
		} 
		
		request.setAttribute(RequestKeys.MAIL_VO, mailVo);
		request.setAttribute(RequestKeys.TRADE_VO, tradeVo);
		request.setAttribute(RequestKeys.MAIL_CHILD_LIST, mailChildList);
        
        return  mapping.findForward("success");
    }

}
