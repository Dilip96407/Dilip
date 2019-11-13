/*
 * Created on Feb 27, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.client.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Properties;

import de.westlb.mgb.client.server.MgbSessionSingleton;

/**
 * @author WSY4148
 *
 * Folgendes auswählen, um die Schablone für den erstellten Typenkommentar zu ändern:
 * Fenster&gt;Benutzervorgaben&gt;Java&gt;Codegenerierung&gt;Code und Kommentare
 */
public class LocalUserSettings {
	private static final String PROPERTY_FILE_PATH = "user.properties";
	private Properties properties = null;
	
	private static LocalUserSettings instance = null;
	
	private LocalUserSettings() {
		init();
	}
	
	public static LocalUserSettings getInstance() {
	 	synchronized (LocalUserSettings.class) {
	 	    if (instance == null) {
	 	        instance = new LocalUserSettings();
	 	        instance.init();
			}
		 }
		 return instance;
	}
	
	private void init() {
		FileInputStream fileInputStream = null;
		try {
			properties = new Properties();
			fileInputStream = new FileInputStream(ApplicationDefinitions.PATH_MGB + "/" + PROPERTY_FILE_PATH);
			properties.load(fileInputStream);
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {fileInputStream.close();} catch (IOException e1) {}
			}
		}		
	}
	
	public String getString(String propertyName) {
		return properties.getProperty(getExpandedPropertyName(propertyName));
	}
	
	private static String getExpandedPropertyName(String propertyName) {
		try {
			if (MgbSessionSingleton.getInstance(false).getEmployee() != null) {
				String mandantCode = MgbSessionSingleton.getInstance(false).getEmployee().getMandantCode();
				if (mandantCode != null) {
					propertyName = mandantCode + "_" + propertyName;
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return propertyName;		
	}
	
	private void saveToFile() {
		FileOutputStream fileOutputStream = null;
		try {
			File file = new File(ApplicationDefinitions.PATH_MGB);
			if (!file.exists()) {
				if (!file.mkdirs()) {
				    throw new IOException("Unable to create directory "+file);
				}
			} 
			fileOutputStream = new FileOutputStream(ApplicationDefinitions.PATH_MGB + "/" + PROPERTY_FILE_PATH);
			properties.store(fileOutputStream, null);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileOutputStream != null) {
				try {fileOutputStream.close();} catch (IOException e1) {}
			}
		}		
	}
	
	public void setString(String name, String value) {
		properties.setProperty(getExpandedPropertyName(name), value);
		saveToFile();
	}
}
