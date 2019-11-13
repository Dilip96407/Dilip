package de.westlb.mgb.client.application;

import org.apache.log4j.Logger;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb_systems.xaf.util.PropertyFactory;

/**
 * Thread to send keep alive messages. Avoids timeout for session close on
 * server side.
 */
public class KeepAliveThread extends Thread {

	static Logger logger = Logger.getLogger(KeepAliveThread.class);

	// Default Keep alive intervall is 5 Minutes
	private static final int DEFAULT_INTERVALL = 5*60*1000; 
	// Keep alive intervall in millis. 
	private int keepAliveIntervall = DEFAULT_INTERVALL; 

	/**
     * Konstruktor
     */
    public KeepAliveThread() {
        init();
    }

    /**
     * Initialisierung der Klasse
     */
    private void init() {
		int intervall = PropertyFactory.getIntProperty("mgb.keepAliveIntervall");
		if (intervall > 0) {
			this.keepAliveIntervall = intervall;
		}
		setDaemon(true);
    }

    /**
     * Implementierung of runnable which will be called from 
     * Thread in <code>postLoad</code> aufgerufen
     * @see #postLoad()
     */
    @Override
    public void run() {

        try {
			while (!isInterrupted()) {
				sleep(keepAliveIntervall);
				MgbServiceFactory.getService().keepAlive();
			}
        } catch (InterruptedException ie) {
        	logger.debug("Thread stopped");
        } catch (Exception ex) {
			ex.printStackTrace();
        } finally {
        }
    }

    /**
     * @return
     */
    public int getKeepAliveIntervall() {
        return keepAliveIntervall;
    }

    /**
     * @param i
     */
    public void setKeepAliveIntervall(int i) {
        keepAliveIntervall = i;
    }

}
