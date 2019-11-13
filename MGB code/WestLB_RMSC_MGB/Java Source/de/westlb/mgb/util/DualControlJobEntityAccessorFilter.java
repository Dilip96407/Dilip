/*
 * Created on Dec 16, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.util;

import java.util.Arrays;

/**
 * @author WSY4148
 *
 */
public class DualControlJobEntityAccessorFilter implements MethodFilter {

	private static final DualControlJobEntityAccessorFilter instance = new DualControlJobEntityAccessorFilter();
	
	private String[] excludedAccessors = {
		"getChangedBy",
		"getChangedDate",
		"getClass",
		"getCreatedBy",		
		"getCreationDate",
		"getDualControlJobs",
		"getId",
		"getLongId",
		"getMandant",
		"getMappings",
		"getMgbConfigurationId",	
		"getModifiedDate",
		"getModifiedBy",
	};
			
	
    private DualControlJobEntityAccessorFilter() {
        super();
        Arrays.sort(excludedAccessors);
    }
    
    public static synchronized MethodFilter getInstance() {
    	return instance;
    }

    /* (Kein Javadoc)
     * @see de.westlb.mgb.util.MethodFilter#accept(java.lang.Class, java.lang.String)
     */
    @Override
    public boolean accept(Object object, String methodName) {
        return Arrays.binarySearch(excludedAccessors, methodName) < 0;
    }

    @Override
    public boolean translate(Object object, String methodName) {
    	return false;
    }

}
