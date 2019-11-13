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
import de.westlb.mgb.client.server.vo.EmployeeVo;
import de.westlb.mgb.struts_client.form.UserDetailForm;


/**
 * This action is called before the list of reclamations that
 * had been send by the controller to the trader is presented.
 * The list contains mails for open reclamations only.
 * 
 * @author WSY4148 
 */
public class UserDetailSubmitAction extends MgbAction {
    
    public UserDetailSubmitAction() {
        super();
    }

    /* (Kein Javadoc)
     * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response) throws Exception {

        // Check if cancel button has been pressed!
        if (mapping.findForward("cancel") != null && isCancelled(request)) {
            return mapping.findForward("cancel");
        }

        EmployeeVo employee = buildEmployeeVoFromRequest(request);

        Mgb mgb = getMgbService(request, response, employee.getMandantCode());
        Long savedId = mgb.saveEmployeeAndRoles(employee);
        
        if (savedId == null) {
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }

    public static EmployeeVo buildEmployeeVoFromRequest(HttpServletRequest request) {
        EmployeeVo employee = new EmployeeVo();

        employee.setAdmin(request.getParameter("admin") != null);
        employee.setController(request.getParameter("controller") != null);
        employee.setSystemAdmin(request.getParameter("systemAdmin") != null);
        employee.setTrader(request.getParameter("trader") != null);
        employee.setUserMaintainAdmin(request.getParameter("userMaintainAdmin") != null);
        employee.setReporter(request.getParameter("reporter") != null);
        employee.setSparkassenAdmin(request.getParameter("sparkassenAdmin") != null);
        employee.setReadOnly(request.getParameter("readOnly") != null);
       
        if (request.getParameter("email") != null) {
            employee.setEmail(request.getParameter("email"));
        }
        if (request.getParameter("employeeId") != null && request.getParameter("employeeId").length() > 0) {
            try {
                employee.setEmployeeId(new Long(request.getParameter("employeeId")));
            } catch (NumberFormatException nfe){
                nfe.printStackTrace();
            }
        }
        if (request.getParameter("firstName") != null) {
            employee.setFirstName(request.getParameter("firstName"));
        }
        if (request.getParameter("lastName") != null) {
            employee.setLastName(request.getParameter("lastName"));
        }
        if (request.getParameter("mandantCode") != null) {
            employee.setMandantCode(request.getParameter("mandantCode"));
        }
        if (request.getParameter("ntId") != null) {
            employee.setNtId(request.getParameter("ntId"));
        }
        if (request.getParameter("phone") != null) {
            employee.setPhone(request.getParameter("phone"));
        }
        if (request.getParameter("report") != null) {
            if (UserDetailForm.NULL_STRING.equals(request.getParameter("report"))) {
                employee.setReport(null);
            } else {
                employee.setReport(request.getParameter("report"));
            }
        }
        return employee;
    }
}