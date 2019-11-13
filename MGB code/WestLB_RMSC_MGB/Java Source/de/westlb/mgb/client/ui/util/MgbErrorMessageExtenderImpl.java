package de.westlb.mgb.client.ui.util;

import de.westlb_systems.xaf.ui.view.ErrorMessageExtender;
import de.westlb_systems.xaf.util.SResourceBundle;

/**
 * @author WSY4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MgbErrorMessageExtenderImpl implements ErrorMessageExtender {

   private SResourceBundle  resourceBundle;
   private String           resourceName = "ErrorBoxResource";

    /**
     * Constructor for MgbErrorMessageExtenderImpl.
     */
    public MgbErrorMessageExtenderImpl() {
        super();
    }

    /**
     * @see de.westlb.mgb.client.mask.view.common.util.ErrorMessageExtender#extendMsg(Object, String)
     */
    @Override
    public String extendMsg(Throwable errorDetails, String msg) {
 		String errorDetailsMsg = errorDetails.getMessage();

		if ( errorDetailsMsg == null ) {
			return null;
		}   	
		
    	String extend = checkChildRecordFoundProblem(errorDetailsMsg);
    	if (extend == null) {
    		extend = errorDetailsMsg;
    	}
		
		if (msg != null && !msg.equalsIgnoreCase("No Message") && !msg.equals(extend)) {
	        StringBuffer strBuf = new StringBuffer();
			strBuf.append(msg);
			strBuf.append(" - ");
			strBuf.append(extend);
	        return strBuf.toString();
		}
		return null;		
    }
    
    /**
	 * Checks if message is the child record not found problem.
	 * 
	 * @param test error message
	 * @return translated error message, null if it is an other problem.
	 * 
	 */
    private String checkChildRecordFoundProblem(String msg) {
    	if (msg.indexOf ( "child record found" ) != -1) {
    		return getResourceString ("STR_E_CHILD_RECORD_FOUND");
    	}
		return null;
    }
    
    
   	public String getResourceName() {
   		return resourceName;
   	}
    
    public String getResourceString(String key){
	  if(resourceBundle == null) {
		 resourceBundle = new SResourceBundle(getResourceName());
	  }
	  if(resourceBundle != null) {
		 return resourceBundle.getResourceString(key);
	  }
    return key;
   }   
    

	/**
	 * Sets the resourceName.
	 * @param resourceName The resourceName to set
	 */
	public void setResourceName(String resourceName) {
	    this.resourceName = resourceName;
	}

}
