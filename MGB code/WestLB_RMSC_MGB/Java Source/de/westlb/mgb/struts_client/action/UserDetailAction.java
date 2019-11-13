/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */

package de.westlb.mgb.struts_client.action;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.westlb.mgb.client.server.Mgb;
import de.westlb.mgb.client.server.vo.EmployeeVo;
import de.westlb.mgb.struts_client.RequestKeys;
import de.westlb.mgb.struts_client.form.UserDetailForm;

/**
 * Model for the employee details view action.
 * 
 * @author WSY4148
 */
public class UserDetailAction extends MgbAction {

    /**
         *
         */
    public UserDetailAction() {
        super();
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        EmployeeVo employee = null;
        String mandant = request.getParameter("mandant");
        try {
            Mgb mgb = getMgbService(request, response, mandant);
            if (request.getParameter("userId") != null) {
                employee = mgb.getEmployee(new Long(request.getParameter("userId")));
            } else {
                if (mandant != null) {
                    employee = new EmployeeVo();
                    employee.setMandantCode(mandant);
                } else {
                    employee = UserDetailSubmitAction.buildEmployeeVoFromRequest(request);
                }
            }

            if (employee != null) {
                if (employee.getReport() == null) {
                    employee.setReport(UserDetailForm.NULL_STRING);
                }
                List<String> reportList = new ArrayList<String>();
                reportList.add(UserDetailForm.NULL_STRING);
                reportList.add(UserDetailForm.REPORT_ADMIN_STRING);
                reportList.addAll(Arrays.asList(mgb.findAllReportClients()));
                request.setAttribute(RequestKeys.REPORT_LIST, reportList);
                request.setAttribute(RequestKeys.EMPLOYEE_VO, employee);
                return mapping.findForward("success");
            }
        } catch (NumberFormatException nfe) {
            System.out.println(nfe.getMessage());
        } catch (AccessException ae) {
            throw ae;
        } catch (RemoteException re) {
            System.out.println(re.getMessage());
        }
        return mapping.findForward("empty");
    }
}