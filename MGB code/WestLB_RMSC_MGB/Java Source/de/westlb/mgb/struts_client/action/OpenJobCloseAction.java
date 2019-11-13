package de.westlb.mgb.struts_client.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.westlb.mgb.client.server.Mgb;
import de.westlb.mgb.model.definition.JobStateDef;

public class OpenJobCloseAction extends MgbAction {

    static Logger logger = Logger.getLogger(OpenJobCloseAction.class);

    public OpenJobCloseAction() {
        super();
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        logger.info("trying to execute close job");

        String sJobId = request.getParameter("jobId");
        String mandant = request.getParameter("mandant");
        String action = request.getParameter("action");

        logger.info("trying to close job "+sJobId+" for mandant "+mandant);
    	    			
        if (sJobId != null && sJobId.length() > 0) {
            try {
                Long jobId = new Long(sJobId);
                if (mandant == null) {
                    return mapping.findForward("failure");                	
                }
                Mgb mgb = getMgbService(request, response, mandant);

                if ("close".equals(action)) {
                    mgb.setJobStatus(jobId, JobStateDef.JOB_CLOSED_STATUS);                	
                } else if ("ignore".equals(action)) {
                	mgb.setJobStatus(jobId, JobStateDef.JOB_IGNORE_STATUS);
                }
                return mapping.findForward("success");
            } catch (NumberFormatException nfe){
                nfe.printStackTrace();
                return mapping.findForward("failure");
            }
        } else {
            return mapping.findForward("failure");
        }
      
    }
}