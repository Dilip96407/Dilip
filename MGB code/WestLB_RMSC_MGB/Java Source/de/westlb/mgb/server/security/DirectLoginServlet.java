package de.westlb.mgb.server.security;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import de.westlb.mgb.client.application.MgbProperty;
import de.westlb.mgb.client.server.Mgb;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.EmployeeSearchParamsVo;
import de.westlb.mgb.client.server.vo.EmployeeVo;
import de.westlb_systems.xaf.util.PropertyFactory;


/**
 * @version         1.0
 * @author
 */
public class DirectLoginServlet extends HttpServlet implements MgbProperty {
    /**
     * 
     */
    private static final long serialVersionUID = 8214857191617658507L;
    private static final Logger log = Logger.getLogger(DirectLoginServlet.class);
    private String jspPath = "/WEB-INF/struts/tiles/";

    @Override
    public void init() throws ServletException {
        super.init();
        String endpoint = getInitParameter(MGB_ENDPOINT);
        if (endpoint == null || endpoint.length() == 0) {
            throw new ServletException("Parameter "+MGB_ENDPOINT+" must be set as initial parameter");
        }
        String serviceProvider = getInitParameter(MGB_SVC_PROVIDER);
        if (serviceProvider == null || serviceProvider.length() == 0) {
            throw new ServletException("Parameter "+MGB_SVC_PROVIDER+" must be set as initial parameter");
        }
        
        PropertyFactory.put(MGB_SVC_PROVIDER, serviceProvider);
        try {
            MgbServiceFactory.setUrlEndPoint(new URL(endpoint));
        } catch (MalformedURLException e) {
            throw new ServletException("Unable to set endpoint "+endpoint, e);
        }
    }

    /**
    * @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
    * This method should only be called for the initial request
    */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        performTask(req, resp);
    }

    /**
    * @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
    */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        performTask(req, resp);
    }

    /**
    */
    private void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	log.debug("direct login (queryString="+req.getQueryString()+")");
//    	Enumeration e = req.getParameterNames();
//    	while (e.hasMoreElements()) {
//			log.debug("par " +e.nextElement());
//		}
    	
    	String newlogin = req.getParameter("newlogin");
    	String newPasswordUserAccount = req.getParameter("newPasswordUserAccount");
    	if (newlogin != null || newPasswordUserAccount != null) {
    		sendNewPassword(req, resp);
    	} else {
    		login(req, resp);
    	}
    }
 
    /**
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void sendNewPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String newPasswordUserAccount = req.getParameter("newPasswordUserAccount");
       	log.debug("newPasswordUserAccount="+newPasswordUserAccount);

       	String forward=jspPath+"showNewPassword.jsp";

       	if (newPasswordUserAccount == null || newPasswordUserAccount.length() == 0) {
       		forward=jspPath+"newPassword.jsp";
       	}
       	
		req.setAttribute("login.newPassword", createPassword(newPasswordUserAccount));
		log.debug("Forwarding request to: " + forward);
   		req.getRequestDispatcher(forward).forward(req, resp);
	}

	/**
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String osUser = req.getParameter("osUser");
        String user = req.getParameter("user");
	   	String password = req.getParameter("password");
	   	String tradeId = req.getParameter("tradeId");
	   	String mailId = req.getParameter("mailId");
	   	String queryString = "";
	   	if (req.getQueryString() != null) {
	   		queryString = "?"+req.getQueryString();
	   	}
	   	
	   	log.debug("user="+user+", password="+password+", osUser="+osUser);
	
	   	String forward=jspPath+"loginDialog.jsp"+queryString;
	   	
		if (user != null && password != null && password.equals(createPassword(user))) {
		    if (!checkMandant(user)) {
                log.error("Login failed for "+user+". Invalid mandant");
                req.setAttribute("login.message", "Login failed for "+user+".");
		    } else {
    			SecurityUtils.getLoginContext(req, user);
    	   		if (mailId != null) {
    	   			forward = "/traderResponse.do"+queryString;
    	   	   	} else if (tradeId != null) {
    	    		forward = "/startApplet.do"+queryString;       	   			
    	   	   	} else {
    	    		forward = "/welcome.do";       	   			
    	   	   	}
		    }
		} else {
			if (user != null && user.length() > 0) {
				log.error("Login failed for "+user+". '"+password+"' instead of '"+createPassword(user)+"'");
				req.setAttribute("login.message", "Login failed for "+user+".");
			}
		}
		log.debug("Forwarding request to: " + forward);
		req.getRequestDispatcher(forward).forward(req, resp);
	}

	public static String createPassword(String user) {
//    	return "20"+user+"06";
		if (user != null) {
			char[] ca = user.toCharArray();
			char[] password = new char[ca.length]; 
			for (int i = 0; i < ca.length; i++) {
				int integer = ca[i];
				integer+=i;
				integer%=26;
				integer+='a';
				password[i] = (char)integer;
			}
			return new String(password);
		}
        return "";
		
   }
	
	private boolean checkMandant(String user) {
    	Mgb mgbService = MgbServiceFactory.createService();
    	try {
            mgbService.login(user, null, null);
            EmployeeSearchParamsVo param = new EmployeeSearchParamsVo(null, null, null, user, null); 
            EmployeeVo[] employees = mgbService.findAllEmployees(param);
            for (int i = 0; i < employees.length; i++) {
                if (employees[i].getEmail() != null && employees[i].getEmail().endsWith(".lu")) {
                    return true;
                }
                log.error("Non Luxemburg user "+user+" due to mail without *.lu ");
            }
            log.error("No valid user "+user+" found");
            
            return false;
        } catch (RemoteException e) {
            log.error("Error while checking the user "+user, e);
            return false;
        }
	}
}
