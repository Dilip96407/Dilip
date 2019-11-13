/*
 * Created on Dec 9, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.client.ui.util.poi;

/**
 * @author WSY4148
 *
 */
public class PoiException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6653977450087872857L;

	/**
     * 
     */
    public PoiException() {
        super();
    }

    /**
     * @param message
     */
    public PoiException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public PoiException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public PoiException(Throwable cause) {
        super(cause);
    }

}
