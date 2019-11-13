package de.westlb.mgb.client.application;

import de.westlb_systems.xaf.util.SResourceBundle;
;

/**
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ApplicationResourceHandler {
	
	private static final String defaultResourceName = "label/Application";
	private static final String RESOURCE_BASE = "Application_";
	
	private de.westlb_systems.xaf.util.SResourceBundle resourceBundle = null;
	
	private String resourceName = defaultResourceName;
	
	static ApplicationResourceHandler applicationResourceHandler = null;
	
	protected ApplicationResourceHandler() {
	}
	
	public static ApplicationResourceHandler getInstance() {
		if (applicationResourceHandler==null) {
			applicationResourceHandler = new ApplicationResourceHandler();
		}
		return applicationResourceHandler;
	}

	public String getResourceName() {
        return resourceName;
    }

    public String getResourceString(String key) {
        if (resourceBundle == null) {
            resourceBundle = new SResourceBundle(getResourceName());
        }

        if (resourceBundle != null) {
            return resourceBundle.getResourceString(RESOURCE_BASE + key);
        }
        return key;
    }
    
    public void setResourceName(String bundleName) {
        resourceName = bundleName;
    }

}
