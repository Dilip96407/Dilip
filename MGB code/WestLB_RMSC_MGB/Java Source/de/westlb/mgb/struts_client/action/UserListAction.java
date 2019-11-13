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
import de.westlb.mgb.client.server.vo.EmployeeSearchParamsVo;
import de.westlb.mgb.client.server.vo.EmployeeVo;
import de.westlb.mgb.struts_client.RequestKeys;


/**
 * This action provides a list of all MGB user. Filters the
 * list according to the request parameter "mandant".
 * 
 * @author WSY4148 
 */
public class UserListAction extends MgbAction {
    /**
         *
         */
    public UserListAction() {
        super();
    }

    /* (Kein Javadoc)
     * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response) throws Exception {

        EmployeeSearchParamsVo param = new EmployeeSearchParamsVo();
        String mandantCode = null;
        if (request.getParameter("mandantCode") != null) {
            mandantCode = request.getParameter("mandantCode");
            param.setMandantCode(mandantCode);
        }
        Mgb mgb = getMgbService(request, response, mandantCode);
        EmployeeVo[] employee = mgb.findAllEmployees(param);
        List<EmployeeVo> employeeList = Arrays.asList(employee);
        request.setAttribute(RequestKeys.MANDANT_CODE, mandantCode);
        request.setAttribute(RequestKeys.EMPLOYEE_LIST, employeeList);

        if (mandantCode == null) {
            return mapping.findForward("home");
        } else if (employeeList.isEmpty()) {
            return mapping.findForward("empty");
        }
        return mapping.findForward("success");
    }
}