/*
 * Created on Jan 15, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.client.server;

import java.rmi.RemoteException;

import de.westlb.mgb.client.server.vo.SessionInfoVo;

/**
 * @author WSY4148
 *
 * Folgendes auswählen, um die Schablone für den erstellten Typenkommentar zu ändern:
 * Fenster&gt;Benutzervorgaben&gt;Java&gt;Codegenerierung&gt;Code und Kommentare
 */
public class MgbSessionSingleton {

	static SessionInfoVo instance = null;
    /**
     * 
     */
    public MgbSessionSingleton() {
        super();
    }
    
    public static final String getMandantCode() throws RemoteException {
    	return getInstance(false).getEmployee().getMandantCode();
    }
    
    public static final SessionInfoVo getInstance(boolean reload) throws RemoteException {
        synchronized (MgbSessionSingleton.class){
            if (instance == null || reload) {
    			instance = MgbServiceFactory.getService().getSessionInfo();
    		}
    	}
    	
    	return instance;
    }

}
