package de.westlb.mgb.client.application;

import de.westlb_systems.xaf.application.event.ContentRequestEvent;
import de.westlb_systems.xaf.application.event.ContentRequestListener;
import de.westlb_systems.xaf.application.event.ContentRequestSource;

/**
 * Thread to starts automatically dialogs and initzialization after
 * login. 
 */
public class ApplicationStartThread extends Thread implements ContentRequestSource {

    private ContentRequestListener contentRequestListener = null;

    private String[] autoStartCIDs = { 
    	ContentID.MGB_DUAL_CONTROL_INFO,
    	ContentID.MGB_INSTRUMENT_INFO_NEW,
    	ContentID.MGB_LOCATION_INFO_NEW,
//    	ContentID.MGB_TRADER_INFO_NEW, 
    };

    /**
     * Konstruktor
     */
    public ApplicationStartThread() {
        init();
    }
    /**
     * Sets the ContentRequestListener
     * Only one listener is possible!
     * @param: listener ContentRequestListener
     */
    @Override
    public void addContentRequestListener(ContentRequestListener listener) {
        contentRequestListener = listener;
    }
    /**
     * Fires a ContentRequestEvent.
     * 
     * @param id Content ID
     * @param parameters parameters send to the listener.
     */
    protected void fireContentRequestEvent(String contentId, Object parameters) {
        if (contentRequestListener != null) {
            ContentRequestEvent event = new ContentRequestEvent(this, contentId, parameters);
            contentRequestListener.contentRequested(event);
        }
    }
    /**
     * Initialisierung der Klasse
     */
    private void init() {
    	setDaemon(true);
    }

    /**
     * Removes the ContentRequestListener 
     * @param: listener ContentRequestListener
     */
    @Override
    public void removeContentRequestListener(ContentRequestListener listener) {
        if (contentRequestListener == listener) {
            contentRequestListener = null;
        }
    }
    /**
     * Implementierung of runnable which will be called from 
     * Thread in <code>postLoad</code> aufgerufen
     * @see #postLoad()
     */
    @Override
    public void run() {

        try {
            fireContentRequestEvent(ContentID.APP_ACTION_LOCK, null);

            // kleine Pause damit ContentRequest aus StartView sicher abgeschlossen
            Thread.sleep(100);

            for (int i = 0; i < autoStartCIDs.length; i++) {
				fireContentRequestEvent(autoStartCIDs[i], null);
            }
        } catch (Exception ex) {
			ex.printStackTrace();
        } finally {
            // ActionHandling wieder freigeben
            fireContentRequestEvent(ContentID.APP_ACTION_UNLOCK, null);
        }
    }

}
