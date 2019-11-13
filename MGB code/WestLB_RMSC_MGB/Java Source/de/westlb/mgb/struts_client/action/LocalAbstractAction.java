/*
 * Created on 12.07.2012
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.struts_client.action;

import java.rmi.RemoteException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.westlb.mgb.client.server.Mgb;
import de.westlb.mgb.client.server.vo.EmployeeSearchParamsVo;
import de.westlb.mgb.client.server.vo.EmployeeVo;
import de.westlb.mgb.server.security.SecurityUtils;
import de.westlb.mgb.struts_client.RequestKeys;
import de.westlb.mgb.struts_client.form.UserDetailForm;

public abstract class LocalAbstractAction extends MgbAction {

    static Logger logger = Logger.getLogger(LocalAbstractAction.class);

    public LocalAbstractAction() {
        super();
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        Mgb service = getMgbService(request, response, null);
        String userId = SecurityUtils.extractUserFromDomainUser(service.getDomainUser());
        String[] reports = null;
        String[] allReports = service.findAllReports();
        String reportType = getReportType(userId, service);
        logger.info("found reportType " + reportType);
        if (reportType == null ) {
            reports = new String[]{};
        } else if (UserDetailForm.REPORT_ADMIN_STRING.equals(reportType)) {
            reports = allReports;
            setInitialReport(form, reports);
        } else {
            reports = service.findReports(reportType);
            setInitialReport(form, reports);
        }
        request.setAttribute(RequestKeys.REPORT_LIST, reports);
        
        return mapping.findForward("success");
    }

    protected abstract void setInitialReport(ActionForm form, String[] reports);

    private String getReportType(String userId, Mgb service) throws RemoteException {
        EmployeeSearchParamsVo searchParam = new EmployeeSearchParamsVo();
        searchParam.setNtId(userId);
        EmployeeVo[] employees = service.findAllEmployees(searchParam);
        return employees[0].getReport();
    }

}