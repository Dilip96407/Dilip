package de.westlb.mgb.client.reports;

/**
 * @author WSY4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ReportException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8187109319611287568L;
	public static final String SERVICE_EXCEPTION = "Error reading data from MGB service";
	public static final String REPORT_EXCEPTION = "Error creating report";
	public static final String OPEN_VIEWER = "Error opening viewer";
	

    /**
     * Constructor for ReportException.
     */
    public ReportException() {
        super();
    }

    /**
     * Constructor for ReportException.
     * @param message
     */
    public ReportException(String message) {
        super(message);
    }

    /**
     * Constructor for ReportException.
     * @param message
     * @param cause
     */
    public ReportException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for ReportException.
     * @param cause
     */
    public ReportException(Throwable cause) {
        super(cause);
    }

}
