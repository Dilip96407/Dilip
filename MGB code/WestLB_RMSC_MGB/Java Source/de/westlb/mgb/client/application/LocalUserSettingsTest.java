/*
 * Created on Feb 27, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.client.application;

import java.rmi.RemoteException;

import junit.framework.TestCase;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb_systems.xaf.util.PropertyFactory;

/**
 * @author WSY4148
 *
 * Folgendes auswählen, um die Schablone für den erstellten Typenkommentar zu ändern:
 * Fenster&gt;Benutzervorgaben&gt;Java&gt;Codegenerierung&gt;Code und Kommentare
 */
public class LocalUserSettingsTest extends TestCase {

    /**
     * 
     */
    public LocalUserSettingsTest() {
        super();
    }

    /**
     * @param arg0
     */
    public LocalUserSettingsTest(String arg0) {
        super(arg0);
    }
	public void testGetProperty() throws RemoteException {
		PropertyFactory.put("mgb.svc_provider", "Faked");
        MgbServiceFactory.getService().login("wsy4148", null, null);
		LocalUserSettings.getInstance().setString("jobDir", "C:\\Temp\\");
		assertEquals("C:\\Temp\\", LocalUserSettings.getInstance().getString("jobDir"));
	}
	    
    public void testSetProperty() throws RemoteException {
		PropertyFactory.put("mgb.svc_provider", "Faked");
		MgbServiceFactory.getService().login("wsy4148", null, null);
		LocalUserSettings.getInstance().setString("name1", "value1");
		assertEquals("value1", LocalUserSettings.getInstance().getString("name1"));
    }

}
