package de.westlb.mgb.client.applet;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;

import sun.plugin.javascript.JSObject;
import de.westlb.mgb.client.application.lookandfeel.LookAndFeel;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.MgbSessionSingleton;
import de.westlb.mgb.client.server.vo.SessionInfoVo;
import de.westlb.mgb.util.NativeFileReceiver;
import de.westlb_systems.xaf.application.StatusBar;
import de.westlb_systems.xaf.application.event.UserEvent;

/**
 * Created on 20.08.2003 
 * 
 * Applet.java 
 * 
 * Copyright (c) 2003, WestLB.
 * 
 * All rights reserved This information contained herin may not be 
 * used in whole or in part without the expressed written
 * consent of WestLB Systems 
 * 
 * Description: Applet component of the MGB application
 * 
 * @version 1.0
 * @author M. Boerner
 */
public class Applet extends JApplet implements Runnable {
	/**
     * 
     */
    private static final long serialVersionUID = -9068258618667207842L;

    static {
		Applet.initPLAF();
	}

	static boolean dirty = false;
	protected JPanel pBackground = new JPanel();
	protected JPanel pMeldung = new JPanel();
	protected JPanel pFill = new JPanel();
	protected GridBagLayout gbl_pContentPane = new GridBagLayout();
	protected GridBagLayout gbl_pBackground = new GridBagLayout();
	protected GridBagLayout gbl_pMeldungen = new GridBagLayout();
	protected GridBagLayout gbl_pFill = new GridBagLayout();
	protected Border border = BorderFactory.createEtchedBorder();
	protected JLabel lUeberschrift1 = new JLabel();
	protected JLabel lMessages = new JLabel();
	protected Container origContentPane = null;
	protected Color textFarbe = Color.black;
	private AppletClient client = null;
	protected Color bgFarbe = null;
	private Thread thread = null;

	/**
	 * Erstellt ein neues Applet object.
	 */
	public Applet() {
		System.out.println("instantiating applet");
		getRootPane().putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);

		try {
			buildGui();
			initLabels();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gibt AppletParameter zurueck
	 * 
	 * @param key Schluessel
	 * @param defaultValue default Wert
	 * 
	 * @return DOCUMENT ME!
	 */
	public String getParameter(String key, String defaultValue) {
		String value = getParameter(key);

		if (value == null) {
			return defaultValue;
		}

		return value;
	}

	/**
	 * Gibt AppletInfo zurueck
	 * 
	 * @return AppletInfo
	 */
	@Override
    public String getAppletInfo() {
		return "Applet zum Starten der Applet Version vom MGB-Tool";
	}

	/**
	 * Setzten der Labels
	 */
	public void initLabels() {
		lUeberschrift1.setText("New MGB-Tool");
		lMessages.setText("loading...");
	}

	/**
	 * Erstellen der GUI
	 * 
	 * @throws Exception
	 */
	private void buildGui() throws Exception {
		origContentPane = this.getContentPane();
		this.getContentPane().setLayout(gbl_pContentPane);
		pBackground.setLayout(gbl_pBackground);
		pBackground.setBorder(border);
		pMeldung.setLayout(gbl_pMeldungen);
		pMeldung.setBorder(border);
		lUeberschrift1.setText("lUeberschrift");
		lUeberschrift1.setFont(new Font("Verdana", 1, 16));
		lMessages.setText("lMessages");
		this.getContentPane().add(
			pBackground,
			new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		pBackground.add(
			pMeldung,
			new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 100, 0));
		pBackground.add(
			pFill,
			new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 100, 0));
		pMeldung.add(
			lUeberschrift1,
			new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 0, 10), 0, 0));
		pMeldung.add(
			lMessages,
			new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
	}

	/**
	 * Worker Thread Methode
	 */
	@Override
    public void run() {
		// Backgroundcolor des Startfensters
		Color bgC = computeBackgroundColor();
		this.getContentPane().setBackground(bgC);
		pBackground.setBackground(bgC);
		pFill.setBackground(bgC);
		pMeldung.setBackground(bgC);
		lUeberschrift1.setForeground(textFarbe);

		showStatus("getting applet parameters...");
		String serverName = getParameter(AppletParameterDef.APPLET_PARAM_SERVER_NAME);
		String userId = getParameter(AppletParameterDef.APPLET_PARAM_USER_ID);
		String key = getParameter(AppletParameterDef.APPLET_PARAM_KEY);
		String startContentId = getParameter(AppletParameterDef.APPLET_PARAM_CONTENT_ID);
		String startContentParameter = getParameter(AppletParameterDef.APPLET_PARAM_CONTENT_PARAMETER);
		String mandant = getParameter(AppletParameterDef.APPLET_PARAM_MANDANT);
		String cookie = getParameter(AppletParameterDef.APPLET_PARAM_COOKIE);
		
		if (startContentId != null && startContentId.length() == 0) {
			startContentId = null;
		}
		if (startContentParameter != null && startContentParameter.length() == 0) {
			startContentParameter = null;
		}
        showStatus("got applet parameters");

		System.out.println("userId=" + userId);
		System.out.println("key=" + key);
		System.out.println("startContentID=" + startContentId);
		System.out.println("startContentParameter=" + startContentParameter);
		System.out.println("serverName=" + serverName);
		System.out.println("mandant=" + mandant);
		System.out.println("cookie=" + cookie);
		
        StringBuffer newCookie = new StringBuffer();
		if (cookie != null) {
		String cookieDelimiter = "; ";
		String[] cookieList = cookie.split(cookieDelimiter);
		for (int i = 0; i < cookieList.length; i++) {
			if (cookieList[i].startsWith("PD_STATEFUL") || 
					cookieList[i].startsWith("PD-S-SESSION-ID") ||
					cookieList[i].startsWith("PD-H-SESSION-ID") ) {
				if (newCookie.length()>0) {
					newCookie.append(cookieDelimiter);
				}
				newCookie.append(cookieList[i]);
			}
		}
		System.out.println("newcookie=" + newCookie.toString());
		}
		
//		boolean newThread = true;
//		NativeFileReceiver.getInstance().receiveFiles(getCodeBase(), "native", newThread);

		//
		if (serverName == null) {
			showStatus("Error: ServerName not found...");
			return;
		}

		try {
			String urlPoint = getCodeBase().toString() + serverName + ";"+AppletParameterDef.APPLET_PARAM_JSESSION_ID+"=" + key + "?"+AppletParameterDef.APPLET_PARAM_USER_ID+"=" + userId;
			System.out.println("setUrlEndPoint: " +urlPoint);
			MgbServiceFactory.setUrlEndPoint(new URL(urlPoint));
			MgbServiceFactory.setHeaderCookie(newCookie.toString());
		} catch (Exception ex) {
			System.err.println("Unable to initialize EndPoint: " + ex);
		}

		// starting Client
		try {
			showStatus("starting client...");
			client = new AppletClient(this, getCodeBase().toString() + serverName, userId, mandant, startContentId, startContentParameter);
			SessionInfoVo sessionInfo = MgbSessionSingleton.getInstance(false);
			String userName = " " + sessionInfo.getEmployee().getFirstName() + " " + sessionInfo.getEmployee().getLastName() + " ";
			client.setStatusTextAt(StatusBar.USERNAME, userName);
	        NativeFileReceiver.getInstance().receiveFiles(getCodeBase(), "native", true);
	        System.out.println("started client");
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
			showErrorStatus("Error: exception while starting client!");
			return;
		}
		return;
	}

	/**
	 * Zeigt Meldung in der StatusZeile an
	 * 
	 * @param meldung
	 */
	@Override
    public void showStatus(String meldung) {
		lMessages.setForeground(textFarbe);
		lMessages.setText(meldung);

		invalidate();
		validate();
		repaint();

		try {
			Thread.sleep(101);
		} catch (Exception e) {
		};
	}

	/**
	 * @see java.applet.Applet#start()
	 */
	@Override
    public void start() {
		System.out.println("starting...");

		if (dirty) {
			reloadApplet();
			destroy();
			return;
		}
        dirty = true;

		initPLAF();
		setVisible(true);

		if (thread == null) {
			thread = new Thread(this, "Applet Thread");
			thread.setDaemon(true);
			thread.start();
		}
        System.out.println("started");
	}

	/**
	 * Reload 
	 */
	private void reloadApplet() {
		try {
			JSObject win = (JSObject) JSObject.getWindow(this);
			win.eval("javascript:location.reload()");
		} catch (Exception e) {
		}
	}

	/**
	 * Method initPLAF.
	 */
	private static void initPLAF() {
		try {
			UIManager.setLookAndFeel(new LookAndFeel());
		} catch (Exception e) {
			System.err.println("Set LookAndFeel failed: " + e);
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	protected Color computeBackgroundColor() {
		if (bgFarbe != null) {
			return bgFarbe;
		}

		String bg = getParameter("BGCOLOR");

		if ((bg != null) && (bg.length() == 7)) {
			try {
				bgFarbe = Color.decode(bg);
			} catch (NumberFormatException nfe) {
			}
		}

		bgFarbe = (bgFarbe != null) ? bgFarbe : new Color(192, 192, 192);

		if ((bgFarbe.getRed() + bgFarbe.getGreen() + bgFarbe.getBlue()) < 200) {
			textFarbe = Color.white;
		}

		return bgFarbe;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param meldung DOCUMENT ME!
	 */
	public void showErrorStatus(String meldung) {
		showStatus(meldung);
		lMessages.setForeground(Color.red);
	}

	/**
	 * DOCUMENT ME!
	 */
	public void resetContent() {
		client = null;

		showRestartMessage();

		setContentPane(origContentPane);
		validate();
		repaint();
	}

	/**
	 * Insert the method's description here.
	 *
	 * @param
	 * @return
	 */
	private void showRestartMessage() {
		JPanel pMessage = pFill;
		pMessage.removeAll();
		pMessage.setLayout(gbl_pFill);
		pMessage.setBorder(border);

		JLabel[] labels =
			new JLabel[] {
				new JLabel("Um die Anwendung neu zu starten, schließen Sie bitte das "),
				new JLabel("Fenster und starten dann über den Link erneut oder "),
				new JLabel("starten Sie den Internet Explorer neu."),
				new JLabel(""),
				new JLabel("To restart the application, please close the window "),
				new JLabel("and start again with the link or "),
				new JLabel("restart your Internet Explorer."),
				};

		pFill.removeAll();

		for (int a = 0; a < labels.length; a++) {
			labels[a].setForeground(textFarbe);
			pFill.add(
				labels[a],
				new GridBagConstraints(0, a, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		}

		showStatus("");
	}

	@Override
    public void stop()
    {
	    if(this.client!=null)
	    {
	        this.client.actionRequested(new UserEvent(this, 0, "FILE_EXIT"));
	    }
        super.stop();
    }

    /* (non-Javadoc)
	 * @see java.applet.Applet#destroy()
	 */
	@Override
    public void destroy() {
		System.out.println("Destroy...");
		super.destroy();
		if (thread != null) {
			System.out.println("Stopping thread ...");
			thread.interrupt();
			thread = null;
		}
        System.out.println("Stopped thread");
		dirty = false;
        System.out.println("Destroyed");
	}

}