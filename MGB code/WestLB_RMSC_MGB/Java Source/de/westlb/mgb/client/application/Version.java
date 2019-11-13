package de.westlb.mgb.client.application;

import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Diese Klasse lädt die Version aus einer Properties-Datei 
 * mit Namen version.properties, die irgendwo im Classpath liegen muss.
 * dort sollten folgende properties gesetzt werden BSP: MGB 2.0 (Build 100):
 * <ul>
 * 		<li>product=MGB</li>
 * 		<li>version.major=2</li>
 * 		<li>version.minor=0</li>
 * 		<li>build=100</li>
 * </ul>
 */
public class Version {
	private static Logger logger = Logger.getLogger(Version.class);
	private static Version instance = null;
	private final String product;
	private String versionDateString;
	private final int versionMajor;
	private final int versionMinor;
	private final int versionUpdate;
	private final int build;

	private Version() {
		Properties versionProps = new Properties();
		try {
			versionProps.load(getClass().getResourceAsStream("/version.properties"));
		} catch (Exception e) {
			logger.error("Exception caught. Could not load version.properties!", e);
		}
		product = versionProps.getProperty("product", "MGB");
		versionMajor = Integer.parseInt(versionProps.getProperty("version.major", "2"));
		versionMinor = Integer.parseInt(versionProps.getProperty("version.minor", "2"));
		versionUpdate = Integer.parseInt(versionProps.getProperty("version.update", "2"));
		versionDateString = versionProps.getProperty("version.date", "");
		build = Integer.parseInt(versionProps.getProperty("build", "100"));
	}

	/**
	 * Method instance.
	 * Creates and returns an Instance of the Singleton Version.
	 * @return Version
	 */
	public static Version instance() {
		if (instance == null)
			instance = new Version();
		return instance;
	}

	/**
	 * Method getVersionString.
	 * Returns a String like "WAVE 2.0 (Build 100)"
	 * @return String
	 */
	public final String getVersionString() {
		return product + " " + versionMajor + "." + versionMinor + "." + versionUpdate + " (Build " + build + ") " + versionDateString;
	}

}
