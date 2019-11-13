package de.westlb.mgb.client.mask.model.shared;

import java.io.File;
import java.io.FileOutputStream;
import java.rmi.RemoteException;

import de.westlb.mgb.client.server.MgbServiceFactory;

/**
 * Gui model for the employee edit dialog
 *
 * @author Manfred Boerner
 */

public class LogViewModel extends AbstractModel {
	public static final String P_LOG_TEXT	= "logText";
	public static final String P_BAD_TEXT	= "badText";

		
	/** Definition of all properties provided by the model to the view */
	private final String[] propertyNames = new String[] {
	        P_LOG_TEXT, 
	        P_BAD_TEXT,
	};
	
	/**
	 * Default constructor to create an empty model
	 */
	public LogViewModel() {
		setPropertyNames(propertyNames);
	}
	
    /**
     * Sets the business object where the model gets its data from.
     */
    @Override
    public void setBusinessObject(Object newBusinessObject) {
        super.setBusinessObject(newBusinessObject);
        if (newBusinessObject instanceof Long) {
            Long jobId = (Long)newBusinessObject;
            try {
                byte[] logText = MgbServiceFactory.getService().downloadJobFile(jobId, "log");
                if (logText != null) {
                    setProperty(P_LOG_TEXT, new String(logText));
                } else {
                    setProperty(P_LOG_TEXT,"No log file could be read for job "+jobId);
                }

                byte[] badText = MgbServiceFactory.getService().downloadJobFile(jobId, "bad");
                if (badText != null) {
                    setProperty(P_BAD_TEXT, new String(badText));
                }
            } catch (RemoteException e) {
                handleRemoteException(e);
            }
        }
    }


    public Long getJobId() {
        return (Long)getBusinessObject();
    }
    
    public boolean doSaveAs(File file) {
        boolean result = true;
        FileOutputStream fileOutputStream = null;
        try {
            byte[] buffer = MgbServiceFactory.getService().downloadJobFile(getJobId(), "dat");
            if (buffer == null) {
                throw new RemoteException("No data file could be read for job "+getJobId());
            }            
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(buffer);
        } catch (Exception e) {
            handleRemoteException(e);
            result = false;
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                }
            }
        }
        return result;
    }

}
