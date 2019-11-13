package de.westlb.mgb.util;

/**
 * Receives native files like word templates or DLL's from 
 * a remote web server to the local client.
 * 
 * @author Manfred Boerner
 * Created on Nov 13, 2003
 * @version 1.0
 */

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import de.westlb_systems.xaf.util.SProperties;

public class NativeFileReceiver {
	private static final String PROPERTY_FILE_NAME = "native";
	
	private static final String P_DATE		= "date";
	private static final String P_DST_DIR	= "dstDir";
	private static final String P_DELETE		= "delete";  // flag
	private static final String P_FILE_NAME	= "fileName";
	private static final String P_SRC_DIR	= "srcDir";
	
	private static final String V_YES		 = "yes"; // Value for flags
	
	private static NativeFileReceiver instance = null;
		
    private static Logger logger = Logger.getLogger(NativeFileReceiver.class);

    private NativeFileReceiver() {
    }
    
    public static NativeFileReceiver getInstance() {
    	if (instance == null) {
    		instance = new NativeFileReceiver();
    	}
    	
    	return instance;
    }

    private boolean receiveFile(URL srcFileUrl, String dstFileName, String dstDir) {
		boolean success = false;
        DataInputStream in = null;
        FileOutputStream out = null;
        try {
            URLConnection connection = srcFileUrl.openConnection();
            connection.setAllowUserInteraction(true);

            File dir = new File(dstDir);
            if (!dir.mkdirs() && !dir.exists()) {
                throw new IOException("Unable to create directory "+dir.getName()); 
            }
            in = new DataInputStream(connection.getInputStream());
            out = new FileOutputStream(new File(dstDir, dstFileName));
            
            byte[] buffer = new byte[1024];
            int bytesRead;
            do {
                bytesRead = in.read(buffer);
                if (bytesRead > 0) {
                	out.write(buffer, 0, bytesRead);
                }    	
            } while (bytesRead != -1);
            success = true;
        } catch (Exception ex) {
           logger.error("Can not receive file  " + dstFileName +  " from url " + srcFileUrl, ex);
        } finally {
        	try {
                if (in != null) in.close();
                if (out != null) out.close();
            } catch (IOException e) {
                logger.error("Can close streams: "+e.getMessage());
            }
        }
        
        return success;
    } 
      
    private void receiveFiles(URL url, String propertyFileName) {
		int count = 0;
		int successCount = 0;
		int distributedCount = 0;
        try {
        	
        	if (propertyFileName == null) {
        		propertyFileName = PROPERTY_FILE_NAME;
        	}

            // Properties File laden
            SProperties props = new SProperties(propertyFileName);
            if (props.size() == 0) {
                logger.error("Native file description '" + propertyFileName + "' not found or empy!");
                return;
            }

            String defaultSrcDir   = props.getProperty(P_SRC_DIR);
            String defaultDstDir   = props.getProperty(P_DST_DIR);
            String defaultDate     = props.getProperty(P_DATE);
            String defaultDelete   = props.getProperty(P_DELETE);

            count = Integer.parseInt(props.getProperty("count"));
            successCount = 0;
            for (int i = 0; i < count; i++) {
                try {                   
                    String fileName = props.getProperty(P_FILE_NAME + i);
                    String srcDir	= props.getProperty(P_SRC_DIR + i, defaultSrcDir);
                    String dstDir	= props.getProperty(P_DST_DIR + i, defaultDstDir);
                    String date		= props.getProperty(P_DATE + i, defaultDate);
                    String delete	= props.getProperty(P_DELETE + i, defaultDelete);

					dstDir = StringUtils.replace(dstDir, "<user.home>", System.getProperty("user.home"));

                    // Check
                    if (srcDir == null || fileName == null || dstDir == null || date == null) {
                        logger.error("Invalid entry [" + i + "] in property file '" + propertyFileName + "'!");
                    }
                    
                    if (delete != null && V_YES.equals(delete)) {
                        // try to delete file:
                        File f = new File(dstDir + File.separator + fileName);
                        logger.info("Trying to delete: " + f);
                        if (f.exists()) {
                            if (!f.delete()) {
                                logger.error("Unable to delete file "+f.getName());
                            }
                        }
                    } else {
                        if (isUpdateRequired(date, dstDir, fileName)) {
                        	URL destUrl = new URL(url.getProtocol(), url.getHost(), url.getPort(), srcDir + "/" + fileName);
                            receiveFile(destUrl, fileName, dstDir);
                            distributedCount ++;
                        } else {
                            // Load DLL ...
//                            if (file.endsWith(".dll")) {
//                                System.load(dest + File.separator + file);
//                                System.out.println("loading native library: " + dest + File.separator + file);
//                                logger.info("loading native library: " + dest + File.separator + file);
//                            }
                        }
                    }
                    successCount++;
                } catch (Exception ex) {
                   logger.error(ex);
                }
            }

        } catch (NumberFormatException ex) {
            logger.error("Error in native file propeties file '" + propertyFileName + "'!", ex);
        }

        logger.info("end receive native files " + successCount  + " from " + count + " file(s) up to date " + distributedCount + " file(s) distributed");
    } // end of install                                              


    // check if newer version of native file exists and return true or false
    private boolean isUpdateRequired(String date, String destDir, String fileName) {
        GregorianCalendar resourcecal =
            new GregorianCalendar(
                Integer.parseInt(date.substring(0, 4)),			// Year
                Integer.parseInt(date.substring(4, 6)) - 1,		// Month
                Integer.parseInt(date.substring(6, 8)));		// Day

        // Datei suchen und gegebenenfalls Datum pruefen
        File file = new File(destDir, fileName);
        if (file.exists()) {
            GregorianCalendar filecal = new GregorianCalendar();
            filecal.setTime(new Date(file.lastModified()));
            logger.info("File " + file.getPath() + " exists");
            return filecal.before(resourcecal);
        } 
        
        return true;
    }
    
    /*
      // entrypoint if application
      public void main(String[] args) {
    
    	URL myurl = null;
    	try{
    	  myurl = new URL("http","localhost",80,"");
    	  this.install(myurl);
    	}
    	catch (MalformedURLException ex){
    	  ex.printStackTrace();
    	}
      }
    */

	public void receiveFiles(final URL url, final String propertyFileName, boolean background) {
		if (!background) {
			NativeFileReceiver.getInstance().receiveFiles(url, propertyFileName);
		}
		
		Runnable runnable = new Runnable() {
			@Override
            public void run() {
				NativeFileReceiver.getInstance().receiveFiles(url, propertyFileName);
			}
		};
		
		new Thread(runnable).start();
	}
}
