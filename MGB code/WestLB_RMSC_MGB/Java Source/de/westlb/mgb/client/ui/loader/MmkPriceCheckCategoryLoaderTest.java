/*
 * Created on Dec 9, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.client.ui.loader;

import java.rmi.RemoteException;

import junit.framework.TestCase;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.ui.util.poi.PoiException;
import de.westlb.mgb.client.ui.util.poi.PoiLoader;
import de.westlb.mgb.client.ui.util.poi.PoiReader;
import de.westlb_systems.xaf.util.PropertyFactory;

/**
 * @author WSY4148
 *
 * Folgendes auswählen, um die Schablone für den erstellten Typenkommentar zu ändern:
 * Fenster&gt;Benutzervorgaben&gt;Java&gt;Codegenerierung&gt;Code und Kommentare
 */
public class MmkPriceCheckCategoryLoaderTest extends TestCase {

    /**
     * Constructor for MmkPriceCheckCategoryLoaderTest.
     * @param arg0
     */
    public MmkPriceCheckCategoryLoaderTest(String arg0) {
        super(arg0);
    }
    
    public void testRead() throws PoiException, RemoteException {
    	PropertyFactory.load("client");
    	MgbServiceFactory.getService().login("wsy4148", null, null);
    	PoiLoader loader = new MmkPriceCheckCategoryLoader();
    	PoiReader poiReader = new PoiReader(loader);
    	poiReader.read("../../resource/test/PriceCheckCategoryImportTest.xls");
    }

}
