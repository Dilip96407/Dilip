package de.westlb.mgb.client.mask.model.shared;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;

import de.westlb.mgb.client.application.ApplicationDefinitions;
import de.westlb.mgb.client.server.Mgb;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb_systems.xaf.util.SResourceBundle;

/**
 * Class to perform file operations.
 * 
 *  @author Manfred Boerner
 */
public class FileHandlerModel {

    /** Verzeichnis in das temporaerte Dateien angelegt werden*/
    private static String CHECKOUT_PATH = ApplicationDefinitions.PATH_MGB_TEMP;

    /** Size of data package for the network transfer */
    public static final int PACKAGE_SIZE = 4096;

    public static final String RESOURCE_BASE = "FILEHANDLER_";
    private SResourceBundle bundle = null;

    private Progress progress = null;

    public static final int ERROR_IO_EXCEPTION = 1;
    public static final int ERROR_DB_EXCEPTION = 2;
    public static final int ERROR_PROTOCOL = 4;
    public static final int ERROR_CANCELED = 5;
    public static final int ERROR_UNKNOWN = 7;

    public static final int STATUS_ERROR = 6;

    private int errorcode = 1;
    private Object errorDetails = null;

    /**
     * DateiHandler constructor comment.
     */
    public FileHandlerModel() {
        super();
    }

    /**
     * Erzeugt ein Verzeichnis falls es noch nicht existiert
     * 
     * @param dirpath Verzeichnis Pfad
     * @return true falls das Verzeichnis existiert oder angelegt wurde
     */
    private boolean createDirectory(String dirpath) {

        File dir = new File(dirpath);
        if (dir.exists()) {
            return dir.isDirectory();
        }

        return dir.mkdirs();
    }
    
    public boolean createFile(String filename, byte[] content) {
        try {
            ensureDirectory(filename);
            FileOutputStream out = new FileOutputStream(filename);
            out.write(content);
            out.close();
        } catch (IOException ex) {
            setError(-1, ex);
            //		Debug.log(Debug.ERROR, new DateiHandlerModel(), "createFile("+filename+") failed!");
            return false;
        }

        return true;
    	
    }
    
    
    /**
     * Erzeugen einer Datei der Laenge 0
     * <p>
     * Falls die Datei bereits existiert, wird die Laenge auf 0 gesetzt!
     * 
     * @param filename Name der Datei
     */
    public boolean createFile(String filename) {
        return createFile(filename, new byte[0]);
    }
    /**
     * Erzeugt einen eindeutigen Verzeichnisnamen
     * <p>
     * Der Dateiname wird gebildet dem aktuellen Auslagerungspfad und der Systemzeit
     * 
     * @param name Ein Dateiname
     * @return Neuer, eindeitiger Dateiname
     */
    public static String createUniquePath() {

        long time = System.currentTimeMillis();

        return new File(getCheckoutPath(), Long.toString(time)).getPath();

    }
    /**
     * Stellt sicher das das Verzeichnis einer Datei existiert
     *
     * @param filename Dateiname dessen Verzeichnis geprueft werden soll
     */
    public boolean ensureDirectory(String filename) {
        File file = new File(filename);
        String dir = file.getParent();

        boolean result = dir != null && createDirectory(dir);
        if (!result) {
            setError(ERROR_IO_EXCEPTION, "Could not create directory '" + dir + "'!");
        }
        return result;
    }
    /**
     * Export an attachment from the mgb database to the local file system
     * 
     * @param id of the file in mgb
     * @param directory Directory in the local file system
     * @return boolean true when the export was successfull
     */
    public boolean exportFile(Object id, String filename) {
        FileOutputStream out = null;

        try {
            if (!ensureDirectory(filename)) {
                return false;
            }

            out = new FileOutputStream(filename);

            if (progress != null) {
                Mgb mgb = MgbServiceFactory.getService();
                long nBytes = mgb.startReadAttachment((Long)id);
                progress.setProgress(0L, nBytes);
                if (progress.isCanceled()) {
                    setError(ERROR_CANCELED, "Operation Canceled");
                    return false;
                }
                long iBytes = 0L;
                while (iBytes < nBytes) {
                    byte[] buffer = mgb.readAttachment((Long)id, iBytes, iBytes + PACKAGE_SIZE);
                    iBytes += PACKAGE_SIZE;
                    out.write(buffer);
                    progress.setProgress(iBytes, nBytes);
                }
            }

            out.flush();
            return true;
        } catch (RemoteException e) {
            setError(ERROR_PROTOCOL, e);
        } catch (IOException e) {
            setError(ERROR_IO_EXCEPTION, e);
        } catch (Exception e) {
            setError(ERROR_UNKNOWN, e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception ex) {
                }
            }
        }

        return false;

    }
    /**
     * Gibt den Auslagerunspfad fuer temporaere Dateien zurueck.
     * 
     * @return der Auslagerugnspfad
     */
    public static String getCheckoutPath() {
        return CHECKOUT_PATH;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/21/01 2:15:36 PM)
     * @return int
     */
    public int getError() {
        return errorcode;
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/21/01 2:15:36 PM)
     * @return int
     */
    public Object getErrorDetails() {
        return errorDetails;
    }

    /**
     * Gibt eine String aus dem ResourceBundle zureuck.
     * <p>
     * Der ResourceKey wird gebildet aus RESOURCE_BASE = key!
     * 
     * @param key der lezte Teil des ResourceKeys
     * @return ein ResourceString oder der ResourceKey falls kein String verfuegbar
     */
    public String getResourceString(String key) {
        if (bundle == null) {
			String className = getClass().getName();
			String name = className.substring(className.lastIndexOf(".") + 1);
			if (name.endsWith("View")) {
				name = name.substring(0, name.length()-4);
			} else if (name.endsWith("Model")) {
				name = name.substring(0, name.length()-5);			
			} else if (name.endsWith("Dialog")) {
				name = name.substring(0, name.length()-6);
			}

			name = ApplicationDefinitions.LABEL_PATH + name;
    		bundle = new SResourceBundle(name);
    	}
		
       	return bundle.getResourceString(RESOURCE_BASE + key);

    }
    /**
     * Setzt den Auslagerungspfad fuer temporaere Dateien.
     * 
     * @return ein Verzeichnispfad (der CRM-User muss Schreibrechte auf dieses Verzeichnis haben!
     */
    public static void setCheckoutPath(String path) {
        CHECKOUT_PATH = path;
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/21/01 3:51:27 PM)
     * @param code int
     * @param details java.lang.Object
     */
    private void setError(int code, Object details) {

        errorcode = code;
        errorDetails = details;
    }

    /**
     * Setzen des DateiHandlerDialogs
     * <p>
     * Wird benoetigt fuer ProgressBar
     *
     * @param newDialog der DateiHandlerDialog des Models
     */
    public void setProgress(Progress newProgress) {
        progress = newProgress;
    }

}