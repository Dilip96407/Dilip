package de.westlb.mgb.server.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;

/**
 * @author WSY4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class AxisNtlmAuthenticationHandler extends BasicHandler {

	/**
     * 
     */
    private static final long serialVersionUID = 1006911380122837539L;
    private static final String PROPERTY_HTTP_REQUEST = "transport.http.servletRequest";
    /**
     * @see org.apache.axis.Handler#invoke(MessageContext)
     */
    @Override
    public void invoke(MessageContext msgContext) throws AxisFault {
    	boolean accessAllowed = false;
    	
    	try {
    		System.out.println("Checking NTLM authentication");
    		
    		// To cast seems to be the only to get access to the HttpRequest,
    		// May be this part has to be changed in a future version 
    		// of Axis!!
   			HttpServletRequest request = (HttpServletRequest) msgContext.getProperty(PROPERTY_HTTP_REQUEST);
			System.out.println("remoteUser=" + request.getRemoteUser());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	if (!accessAllowed) {
    		throw new AxisFault("Access denied!");
    	}
    }

}
