package de.westlb.mgb.client.applet;

import java.net.MalformedURLException;
import java.net.URL;

import de.westlb.mgb.client.application.AbstractClient;
import de.westlb_systems.xaf.application.ApplicationControl;
import de.westlb_systems.xaf.application.Synchronizer;
import de.westlb_systems.xaf.help.HelpBroker;

/**
 * Insert the class' description here.
 *
 * @author: Manfred Boerner
 *
 */
public class AppletClient extends AbstractClient {
    private Applet applet = null;

    /**
     * Konstruktor
     */
    public AppletClient(Applet applet, String serverName, String userId, String mandantCode, String startContentId, Object startContentParameters) {
    	super(startContentId, startContentParameters);
        this.applet = applet;
        //IconKatalog.setApplet(applet);
        HelpBroker.setAppletContext(applet.getAppletContext());

        try {
            setCodeBase(applet.getCodeBase());
        } catch (java.net.MalformedURLException ex) {
            ex.printStackTrace();
        }

        super.init(applet, serverName, userId, mandantCode);

    }
    @Override
    protected ApplicationControl createComponents() {
        ApplicationControl appControl = super.createComponents();

        Synchronizer.setCursorRootComponent(applet);
        applet.setContentPane(appControl.getApplicationPanel());
        applet.invalidate();
        applet.validate();
        applet.repaint();

        return appControl;
    }
    
    @Override
    public void dispose() {
        dispose("Applet finished.");
    }
    
    @Override
    protected void dispose(String status) {
        Applet merker = applet;
        if (merker != null)
            merker.resetContent();

        Synchronizer.setCursorRootComponent(null);
        // IconKatalog.setApplet(null);
        applet = null;

        super.dispose();
        
//@TODO check if a gc is usefull
//        System.gc();
        if (merker != null)
            merker.showStatus(status);
    }

	/**
	 * Start a web site
	 * 
	 * @param url valid url address
	 * @return boolean
	 */
	@Override
    public boolean openURLImpl(String urlString) {
		if(this.applet != null) {
			URL url = null;
            try {
                url = new URL(urlString);
            } catch (MalformedURLException e) {
            	e.printStackTrace();
            }
            
			if (url != null) {
				applet.getAppletContext().showDocument(url, "_blank");
				return true;
			}
		}

		return false;
	}
    
    /**
     * Insert the method's description here.
     * 
     * @param mailAdresse java.lang.String
     */
    @Override
    public boolean sendMailImpl(String mailAdresse) {
        return false;
    }

    @Override
    protected void showErrorStatus(String status) {
        applet.showErrorStatus(status);
    }
    @Override
    protected void showStatus(String status) {
        applet.showStatus(status);
    }
}
