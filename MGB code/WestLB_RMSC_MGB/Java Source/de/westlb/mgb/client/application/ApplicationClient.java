package de.westlb.mgb.client.application;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.UIManager;

import org.apache.log4j.Logger;

import de.westlb.mgb.client.applet.AppletParameterDef;
import de.westlb.mgb.client.application.lookandfeel.LookAndFeel;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb_systems.xaf.application.ApplicationControl;
import de.westlb_systems.xaf.application.Synchronizer;
import de.westlb_systems.xaf.swing.SFrame;


/**
 * Insert the class' description here.
 *
 * @author: Manfred Boerner
 *
 */
public class ApplicationClient extends AbstractClient {

    static Logger logger = Logger.getLogger(ApplicationClient.class.getName());

	private SFrame frame = null;

    /**
     * Method init Look and Feel.
     */
    static {
        try {
            UIManager.setLookAndFeel(new LookAndFeel());
        } catch (Exception e) {
            System.err.println("Set LookAndFeel failed: " + e);
        }
    }

    /**
     * Konstruktor
     */
    public ApplicationClient(String serverName, String userId, String mandantCode) {
    	try {
    		String urlString = serverName + "?"+AppletParameterDef.APPLET_PARAM_USER_ID+"=" + userId;
            logger.info("setUrlEndPoint: " + urlString);
            MgbServiceFactory.setUrlEndPoint(new URL(urlString));
        } catch (Exception ex) {
            logger.error("Unable to initialize EndPoint: " + ex, ex);
        }
        super.init(null, serverName, userId, mandantCode);
    }
    
    @Override
    protected ApplicationControl createComponents() {
		ApplicationControl appControl = super.createComponents();

		if (frame == null) {
			frame = new SFrame();
			frame.addWindowListener(new WindowAdapter() {
				@Override
                public void windowClosing(WindowEvent e) {
					dispose();
				}
			});
		}
		
		frame.setContentPane(appControl.getApplicationPanel());
		frame.setSize(1024, 768);
		Synchronizer.setCursorRootComponent(frame);
		frame.setVisible(true);

		return appControl;

    }
    @Override
    public void dispose() {
        Synchronizer.setCursorRootComponent(null);

        super.dispose();
    }
    
    /**
     * Opens an URL in Internet Explorer  Creation date: (11.08.00 10:30:42)
     * 
     * @param url String
     * 
     * @return true falls Aufruf erfolgreich
     */
    @Override
    public boolean openURLImpl(String url) {
        logger.info("Open URL:" + url);
        
        /**
         * M. Boerner 
         * Hard coded because the Application client ist not intended to be used
         * in production environment.
         */
        try {
            Runtime.getRuntime().exec("C:\\Programme\\Internet Explorer\\IEXPLORE.EXE" + " " + url);
        } catch (java.io.IOException ex1) {
            try {
                Runtime.getRuntime().exec("C:\\Program Files\\Internet Explorer\\IEXPLORE.EXE" + " " + url);
            } catch (java.io.IOException ex2) {
                return false;
            }
        }

        return true;
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
        logger.error(status);
    }
    @Override
    protected void showStatus(String status) {
       logger.info(status);
    }
    
    public static void main(String[] args) {
    	if (args != null && args.length > 0) {
    		new ApplicationClient("http://zpsx2220:8092/mgbUat", "GSA-WLB/wsy5384", args[0]);
    	} else {
    		new ApplicationClient("http://localhost:8080/mgb/services/Mgb", "GSA-WLB/wsy5384", null);
    	}
    }
}
