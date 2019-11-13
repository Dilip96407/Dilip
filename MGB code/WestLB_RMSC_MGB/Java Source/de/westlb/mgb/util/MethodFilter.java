/*
 * Created on Dec 16, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.util;

/**
 * A filter for abstract accesort method names.
 *
 * <p> Instances of this interface may be passed to the <code>{@link
 * constructor of the <code>{@link de.westlb.mgb.util:PropertyComparator}</code> class.
 *
 */
public interface MethodFilter {
    /**
	 * Tests whether or not the specified method should be
	 * included in result list of the comparison.
	 *
     * @param objClass The class of the compared object.
	 * @param  methodName  The method name to be tested
	 * @return  <code>true</code> if and only if <code>method</code>
	 *          should be included
	 */
	boolean accept(Object object, String methodName);	

    /**
	 * Tests whether or not the result of a specified method should be
	 * translated before comparison.
	 *
     * @param objClass The class of the compared object.
	 * @param  methodName  The method name to be tested
	 * @return  <code>true</code> if and only if <code>method</code>
	 *          should be included
	 */
	boolean translate(Object object, String methodName);	
}
