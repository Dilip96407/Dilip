package de.westlb.mgb.client.mask.view.shared;

import java.awt.Component;
import java.io.File;
import java.util.Date;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import de.westlb.mgb.client.application.ApplicationDefinitions;
import de.westlb.mgb.client.mask.model.shared.FileHandlerModel;
import de.westlb_systems.xaf.application.Synchronizer;
import de.westlb_systems.xaf.application.event.LogMessageEvent;
import de.westlb_systems.xaf.ui.view.ErrorBox;
import de.westlb_systems.xaf.ui.view.PopUpDialog;
import de.westlb_systems.xaf.util.PropertyFactory;
import de.westlb_systems.xaf.util.SResourceBundle;

/**
 * Insert the type's description here.
 * @author: Manfred Boerner
 */
public class FileHandler {

    private static Logger logger = Logger.getLogger(FileHandler.class);

    static {
        // check the temporary directory and delete all files which are older than 4 weeks
        long nrDays = PropertyFactory.getIntProperty("number.of.days.to.keep.copies");

        if (nrDays == Integer.MIN_VALUE)
            nrDays = 1; // default value

        String tmpPath = ApplicationDefinitions.PATH_MGB_TEMP;
        logger.debug("Starting to clean temporary directory: " + tmpPath);
        long currentTime = System.currentTimeMillis();
        long lastModified = currentTime - nrDays * 24 * 60 * 60 * 1000; // 30 days
        logger.debug("Deleting all files in " + tmpPath + " older than " + new Date(lastModified));

        File file = new File(tmpPath);
        if (file.exists()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteFile(files[i], lastModified);
            }
        }
    }

    private static final String RESOURCE_BASE = FileHandlerModel.RESOURCE_BASE;
    private SResourceBundle bundle = null;
    private Component dialogOwner = null;
    private boolean success = true;

    private FileHandlerModel model = null;
    private ProgressDialog progressDialog = null;
    private OptionDialog optionDialog = null;

    private static final int OVERWRITE_YES = OptionDialog.OPTION_YES;

    private static final int EXPORT_THREAD = 1;
    private static final int EDITOR_THREAD = 5;

    private class HandlerThread extends Thread {

        private int threadType = 0;
        private Object iFileId;
        private String iFilepath;
        private boolean iSave = false;
        private boolean iDelete = false;
        private boolean iForceSave = false;

        public HandlerThread(int type, String name) {
            super(name);
            threadType = type;
            setPriority(Thread.MIN_PRIORITY);
            setDaemon(true);
        }

        public HandlerThread(int type, Object fileId, String filePath) {
            this(EXPORT_THREAD, "EXPORT_THREAD");
            iFileId = fileId;
            iFilepath = filePath;
        }

        public HandlerThread(int type, Object fileId, String filepath, boolean save, boolean delete, boolean forceSave) {
            this(EDITOR_THREAD, "EDITOR_THREAD");
            iFileId = fileId;
            iFilepath = filepath;
            iSave = save;
            iDelete = delete;
        }

        @Override
        public void run() {
        	logger.debug("Thread <" + threadType + "> started");
            switch (threadType) {
                case EXPORT_THREAD :
                    setSuccess(exportDatei0(iFileId, iFilepath));
                    progressDialog.close();
                    break;
                case EDITOR_THREAD :
                    setSuccess(editDatei0(iFileId, iFilepath, iSave, iDelete, iForceSave));
                    break;
                default :
                    break;
            }
        }

    }

    private static class ThreadInvoker implements Runnable {
        private Thread invokerThread;
        public ThreadInvoker(Thread thread) {
            invokerThread = thread;
        }
        @Override
        public void run() {
            invokerThread.start();
        }
    }

    private class SaveDeleteInvoker implements Runnable {
        @SuppressWarnings("unused")
        private Object iFileId = null;
        private String iFilepath = null;
        @SuppressWarnings("unused")
        private boolean iDelete = false;
        private boolean iSave = false;

        public SaveDeleteInvoker(Object fileId, String filepath, boolean save, boolean delete) {
            iFileId = fileId;
            iFilepath = filepath;
            iDelete = delete;
            iSave = save;
        }
        @Override
        public void run() {
        	if (iSave) {
				 getOptionDialog().showDialog(OptionDialog.TYPE_TEMP_CHANGED, iFilepath);
        	}
        	
            if (!deleteFileOnExit(iFilepath)) {
                logger.error("Unable to delete "+iFilepath);
            }
            return;
        }
    }

    /**
     * DateiHandler
     *
     * @param owner Componente auf die der Dialog angezeigt werden soll
     */
    public FileHandler(Component owner) {
        super();
        dialogOwner = owner;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/21/01 9:44:41 AM)
     * @return int
     * @param filepath java.lang.String
     */
    @SuppressWarnings("unused")
    private int checkFileOverwrite(Component dialog, String filepath) {

        File file = new File(filepath);

        if (file.exists()) {
            return getOptionDialog().showDialog(OptionDialog.TYPE_OVERWRITE, filepath);
        }
        return OVERWRITE_YES;

    }

    /**
     * Insert the method's description here.
     * Creation date: (11/21/01 9:44:41 AM)
     * @return int
     * @param filepath java.lang.String
     */
    @SuppressWarnings("unused")
    private boolean checkFileRead(String dir, String filename) {

        File file = new File(dir, filename);

        if (!file.exists()) {
            showError(getResourceString("E_005"), file.getPath());
            return false;
        }
        if (!file.canRead()) {
            showError(getResourceString("E_006"), file.getPath());
            return false;
        }

        return true;

    }

    /**
     * Bildet aus Verzeichnis und Dateiname eine Dateipfad
     *
     * @param filename Dateiname
     * @param directory Verzeichnis
     * @return Dateipfad
     */
    public String createPath(String directory, String filename) {
        return new File(directory, filename).getPath();
    }
    /**
     * Loeschen einer Datei vom Dateisystem
     * 
     * @param filename Name der Datei
     * @return true falls die Datei geloescht wurde, false sonst
     */
    public boolean deleteFile(String filepath) {
        File file = new File(filepath);
        File dir = file.getParentFile();

        if (file.exists()) {
            boolean success = file.delete();
            if (dir.compareTo(new File(FileHandlerModel.getCheckoutPath())) != 0) {
                success = success && dir.delete();
            }
            return success;
        }

        return true;

    }
    /**
     * Loeschen einer Datei vom Dateisystem
     * 
     * @param filename Name der Datei
     * @return true falls die Datei geloescht wurde, false sonst
     */
    public boolean deleteFileOnExit(String filepath) {
        File file = new File(filepath);
        File dir = file.getParentFile();

        if (!file.exists()) {
            return false;
        }
        if (dir.compareTo(new File(FileHandlerModel.getCheckoutPath())) != 0) {
            dir.deleteOnExit();
        }
        file.deleteOnExit();

        return true;
    }

    /**
     * File edit
     *
     * @param fileId id of the file in the database
     * @return true if the editor started successfully
     */
    public boolean editFile(Object fileId) {
        // Standard
        String filepath = createPath(FileHandlerModel.createUniquePath(), fileId.toString());
        boolean read_only = false;
        boolean force_save = false;
        boolean delete = true;

        boolean oldCursor = Synchronizer.setWaitCursor(true, dialogOwner);
        try {
            getProgressDialog().setInfo(getResourceString("I_OP1"));
            HandlerThread exportThread = new HandlerThread(EXPORT_THREAD, fileId, filepath);
            SwingUtilities.invokeLater(new ThreadInvoker(exportThread));
            getProgressDialog().show();
            if (!success) {
                showFileError(filepath);
            }

            // Editor starten
            if (success) {
                HandlerThread editorThread = new HandlerThread(EDITOR_THREAD, fileId, filepath, !read_only, delete, force_save);
                SwingUtilities.invokeLater(new ThreadInvoker(editorThread));
            }
            return success;
        } finally {
            Synchronizer.setWaitCursor(oldCursor, dialogOwner);
        }

    }

    /**
     * Editieren einer Datei
     *
     * @param fileId Id of the file in the database.
     * @return true if the editor started successfully.
     */
    private boolean editDatei0(Object fileId, String filepath, boolean save, boolean delete, boolean forceSave) {

        File file = new File(filepath);
        long last = file.lastModified();

        //boolean success = getRemoteEditor().editFile(filepath, null);
        boolean success = true;

        try {
        	logger.debug("Runtime.getRuntime().exec(" + filepath + ")");
//            Process process = Runtime.getRuntime().exec("cmd.exe /c \"\"" + filepath + "\"\"");
            Process process = Runtime.getRuntime().exec("cmd.exe /c \"" + filepath + "\"");
            process.waitFor();
        } catch (Exception e) {
            showError(getString("E_009", filepath), e);
            success = false;
        }

        save = (file.lastModified() > last) || forceSave;

		if (success) {
        	// Falls Editor erfolgreich//	
            Runnable runnable = new SaveDeleteInvoker(fileId, filepath, save, delete);
            SwingUtilities.invokeLater(runnable);
        }

        return success;

    }

    /**
     * Insert the method's description here.
     * Creation date: (11/16/01 11:58:56 AM)
     * @return boolean
     * @param datei_oid java.lang.Object
     */
    private boolean exportDatei0(Object fileId, String filepath) {

        getModel().setProgress(getProgressDialog());
        getProgressDialog().setDetails(getString("I_EX2", filepath));
        boolean success = getModel().exportFile(fileId, filepath);
        getProgressDialog().setDetails(getResourceString("I_EX3"));

        return success;

    }

    /**
     * Gibt den Auslagerunspfad fuer temporaere Dateien zurueck.
     * 
     * @return der Auslagerugnspfad
     */
    public String getCheckoutPath() {
        return FileHandlerModel.getCheckoutPath();
    }

    /**
     * Returns the FileHandlerModel.
     * Creates a new instance if it does not exist.
     *
     * @return FileHandlerModel
     */
    public FileHandlerModel getModel() {
        if (model == null) {
            model = new FileHandlerModel();
        }
        return model;
    }

    /**
     * Liefert den OptionDialog.
     * <p>
     * Falls noch nicht vorhanden, wird eine neues Instanz des OptionDialog erzeugt.
     *
     * @return der ProgressDialog
     */
    private OptionDialog getOptionDialog() {
        if (optionDialog == null) {
            optionDialog = new OptionDialog(dialogOwner);
        }
        return optionDialog;
    }
    /**
     * Liefert den ProgressDialog.
     * <p>
     * Falls noch nicht vorhanden, wird eine neues Instanz des ProgressDialog erzeugt.
     *
     * @return der ProgressDialog
     */
    private ProgressDialog getProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialogImpl(dialogOwner);
        }
        return progressDialog;
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
			} else if (name.endsWith("Dialog")) {
				name = name.substring(0, name.length()-6);
			} else if (name.endsWith("Model")) {
				name = name.substring(0, name.length()-5);			
			}

			name = ApplicationDefinitions.LABEL_PATH + name;
    		bundle = new SResourceBundle(name);
    	}
		
       	return bundle.getResourceString(RESOURCE_BASE + key);
    }
    /**
     * Liefert einen ResourceString in den noch ein String eingefuegt werden kann.
     * <p>
     * Falls der ResurceString ein "%1" enthaelt, so wird diese durch 'inlay' ersetzt.
     *
     * @param key ein ResourceKey
     * @param inlay ein String der in den ResourceString eingefuert werden soll
     * @return ein ResourceString
     */
    private String getString(String key, String inlay) {

        String base = getResourceString(key);

        return OptionDialog.insertInlays(base, new String[] { inlay });

    }

    /**
     * View file
     *
     * The method exports a file into a temporary file and opens it with an
     * editor.
     * 
     * The file will not be written back to the database and
     * deleted after editing.
     * 
     * @param fileId The id of the file.
     * @return true when export and editor start was sucessfull.
     */
    public boolean viewFile(byte[] data, String fileName) {
        String filepath = createPath(FileHandlerModel.createUniquePath(), fileName);
		getModel().createFile(filepath, data);
	  	HandlerThread thread = new HandlerThread(EDITOR_THREAD, null, filepath, false, true, false);
      	SwingUtilities.invokeLater(new ThreadInvoker(thread));
		return true;
    }


    /**
     * Opens a file
     *
     * The method exports a file into a temporary file and opens it with an
     * editor.
     * 
     * The file will not be written back to the database and
     * deleted after editing.
     * 
     * @param fileId The id of the file.
     * @return true when export and editor start was sucessfull.
     */
    public boolean openFile(Object fileId, String fileName) {
        // Standard
        String filepath = createPath(FileHandlerModel.createUniquePath(), fileName);

        // Exportieren	
        getProgressDialog().setInfo(getResourceString("I_OP1"));
        HandlerThread thread = new HandlerThread(EXPORT_THREAD, fileId, filepath);
        SwingUtilities.invokeLater(new ThreadInvoker(thread));
        getProgressDialog().show();

        if (!success) {
            showFileError(filepath);
        }

        // Editor starten
        if (success) {
            // save delete release forcesave
            thread = new HandlerThread(EDITOR_THREAD, fileId, filepath, false, true, false);
            SwingUtilities.invokeLater(new ThreadInvoker(thread));
        }

        return success;
    }



    /**
     * Setzen der Instanzvariable 'success'.
     *
     * Success wird von den verschiedenen Handler Threads gesetzt
     * 
     * @param value true zeigt an dass eine parallele Aktion erfolgreich war.
     */
    private synchronized void setSuccess(boolean value) {
        success = value;
    }
    /**
     * Anzeigen einer Fehlermeldung.
     * 
     * @param message Fehlermeldung
     * @param details Object fuer den Detail-Bereich der ErrorBox
     * @see de.westlb_systems.webvis.ui.view.ErrorBox
     */
    private void showError(String message, Object details) {

        new ErrorBox(dialogOwner, message, details).show();

    }
    /**
     * Anzeigen einer Fehlermeldung.
     *
     * Die Meldung ist abhaengign vom Fehlercode im DateiHandlerModel.
     * Die Details werden direkt aus dem DateiHandlerModel gelesen.
     *
     * @param filepath ein Dateiname (Wird bei IO-Exceptions ind eie Meldung integriert)
     * @see #showError(String)
     * @see DateiHandlerModel
     */
    private void showFileError(String filepath) {

        if (getModel().getError() == FileHandlerModel.ERROR_IO_EXCEPTION) {
            //		new OptionDialog(dialogOwner).showDialog(OptionDialog.TYPE_FILE_ACCESS_DENIED, filepath);
            showError(getString("E_001", filepath), getResourceString("I_001"));
        } else if (getModel().getError() == FileHandlerModel.ERROR_DB_EXCEPTION) {
            showError(getResourceString("E_002"), getModel().getErrorDetails());
        } else if (getModel().getError() == FileHandlerModel.ERROR_CANCELED) {
            showInfo(getResourceString("I_002"), getModel().getErrorDetails());

        } else if (getModel().getError() == FileHandlerModel.ERROR_PROTOCOL) {
            showError(getResourceString("E_003"), getModel().getErrorDetails());

        } else {
            showError(getString("E_004", String.valueOf(getModel().getError())), getModel().getErrorDetails());
        }

    }
    /**
     * Anzeigen einer Fehlermeldung.
     * 
     * @param message Fehlermeldung
     * @param details Object fuer den Detail-Bereich der ErrorBox
     */
    private void showInfo(String message, Object details) {

        PopUpDialog dialog = new PopUpDialog(dialogOwner);

        dialog.showMessage(message, LogMessageEvent.INFO);

    }

    /**
     * Delete the file denoted by file. If file is a directory, recursively delete all files
     * contained in that directory
     * @param file the filename
     * @param lastModified the last modified time compared to which the last modified time of the file
     *         should be older in order for the file to be deleted
     */
    private static void deleteFile(File file, long lastModified) {

        if (!file.exists())
            return;

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteFile(files[i], lastModified);
            }
        }

        long fileLastModified = file.lastModified();
        if (fileLastModified < lastModified) {
            logger.debug("Deleting file: " + file);
            if (!file.delete()) {
                logger.error("Could not delete file: " + file);
            }
        }
    }

//    public static void main(String args[]) {
//        FileHandler handler = new FileHandler(null);
//    }
}
