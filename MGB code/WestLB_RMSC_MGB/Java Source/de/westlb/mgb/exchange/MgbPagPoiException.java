package de.westlb.mgb.exchange;

public class MgbPagPoiException extends Exception {

	 public MgbPagPoiException() {
	        super();
	    }

	    /**
	     * @param message
	     */
	    public MgbPagPoiException(String message) {
	        super(message);
	    }

	    /**
	     * @param message
	     * @param cause
	     */
	    public MgbPagPoiException(String message, Throwable cause) {
	        super(message, cause);
	    }

	    /**
	     * @param cause
	     */
	    public MgbPagPoiException(Throwable cause) {
	        super(cause);
	    }

	    
}
