package de.westlb.mgb.client.mask.view.shared;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.westlb.mgb.client.application.ApplicationDefinitions;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SBorder;
import de.westlb_systems.xaf.swing.SBorderFactory;
import de.westlb_systems.xaf.swing.SButton;
import de.westlb_systems.xaf.swing.SDialog;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.SPanel;
import de.westlb_systems.xaf.swing.SUtilities;
import de.westlb_systems.xaf.util.SLocale;


/**
 */
//public class ProgressDialogImpl extends ProgressDialog {
public class ProgressDialogImpl extends SDialog implements Runnable, ActionListener, ProgressDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -907477443643814459L;
	// UI-Komponenten
	private Insets insets0 = new Insets(0,  0, 0,  0);
	private Insets insets1 = new Insets(4,  8, 4,  8);
	private Insets insets2 = new Insets(0,  8, 4,  8);

	private SPanel pContent  = new SPanel();
	private SLabel lInfo     = new SLabel(" ");
	private SLabel lDetails1 = new SLabel(" ");
	private SLabel lDetails2 = new SLabel(" ");
	private SPanel pProgress = new SPanel();
	private SPanel lProgress = new SPanel();
	
	private SPanel  pBorder  = new SPanel();
	private SBorder border   = SBorderFactory.createSlimBevelBorder(SBorderFactory.BEVEL_LOWERED);

	private SButton bCancel  = new SButton();
	private boolean canceled = false;

	private OptionDialog optionDialog = null;

//****************		

	private boolean doProgress   = false;
	private boolean doTitle      = false;
	private boolean doInfo       = false;
	private boolean doDetails    = false;
	private boolean doClose      = false;
	private boolean doCancelable = false;

	private int    progress = 0;
	private String title    = null;
	private String info     = null;
	private String details2 = null;
	private String details1 = null;
	private boolean cancelable = false;

	
/**
 * ProgressDialog constructor comment.
 * @param owner java.awt.Component
 */
public ProgressDialogImpl(Component owner) {
	super(owner, "Information", true);
	
	setResizable(false);

	setPreferredSize(new Dimension(400,160));
	setMinimumSize(new Dimension(400,160));

	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

	setTitle(getResourceString("PROGRESS_T_001"));
	bCancel.setText(getResourceString("PROGRESS_B_ABBRECHEN"));
	bCancel.addActionListener(this);
	
	try{
		jbInit();
	} catch (Exception ex) {
		ex.printStackTrace();
	}

}
/**
 * actionPerformed method comment.
 */
@Override
public void actionPerformed(ActionEvent event) {
	
	if(event.getSource() == bCancel) {
		if(optionDialog == null) {
			optionDialog = new OptionDialog(this);
		}
		if(optionDialog.showDialog(OptionDialog.CANCEL_OPTION) == OptionDialog.OPTION_YES) {
			canceled = true;
		}
	}
	
}
/**
 * Insert the method's description here.
 *
 * @param maxValue long
 * @param curentValue long
 */
@Override
public synchronized void close() {

	doClose = true;

	invokeLater();
	
}

    /**
     * Returnes the name of the class without package prefix as
     * name for the resource file.
     */
    @Override
    protected String getResourceName() {
        String className = getClass().getName();
        String name = ApplicationDefinitions.LABEL_PATH + className.substring(className.lastIndexOf(".") + 1);

        return name;
    }


/**
 * Insert the method's description here.
 * Creation date: (11/16/01 4:04:32 PM)
 */
private void invokeLater() {
	javax.swing.SwingUtilities.invokeLater(this);
}
/**
 * Insert the method's description here.
 * 
 *  Creation date: (14.01.02 16:56:19)
 * @return boolean
 */
@Override
public synchronized boolean isCanceled() {
	return canceled;
}
/**
 * Insert the method's description here.
 * Creation date: (9/12/01 10:55:04 AM)
 */
private void jbInit() {

	int WEST = GridBagConstraints2.WEST;
	int CENT = GridBagConstraints2.CENTER;
	int NONE = GridBagConstraints2.NONE;
	int HORI = GridBagConstraints2.HORIZONTAL;
	int BOTH = GridBagConstraints2.BOTH;
	
	setContentPane(pContent);
	pContent.setMargin(insets2);

	pContent.add(lInfo,     new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, WEST, BOTH, insets1, 0, 0));
	pContent.add(lDetails1, new GridBagConstraints2(0, 1, 1, 1, 0.0, 0.0, WEST, HORI, insets1, 0, 0));
	pContent.add(lDetails2, new GridBagConstraints2(0, 2, 1, 1, 0.0, 0.0, WEST, HORI, insets2, 0, 0));
	pContent.add(pBorder,   new GridBagConstraints2(0, 3, 1, 1, 0.0, 0.0, WEST, HORI, insets1, 0, 0));
	pContent.add(bCancel,   new GridBagConstraints2(0, 4, 1, 1, 0.0, 0.0, CENT, NONE, insets1, 0, 0));

	pBorder.add(pProgress,  new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, CENT, BOTH, insets0, 0, 0));
	pBorder.setBorder(border);
	
	pProgress.add(lProgress);
	pProgress.setLayout(null);
	lProgress.setBounds(0, 0, 0, 20);

	pProgress.setPreferredSize(new Dimension(100, 20));
	lProgress.setBackground(java.awt.Color.blue.darker());
	
	lInfo.setIconTextGap(16);
	lInfo.setIcon(javax.swing.UIManager.getIcon("OptionPane.informationIcon"));

	bCancel.setEnabled(false);
	
}
/**
 * Insert the method's description here.
 * Creation date: (11/16/01 4:12:47 PM)
 * @param args java.lang.String[]
 */
public static void main(String[] args) {
	
	try{
		javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
	}catch(Exception ex) {
	}
	
	SLocale.setDefaultLocale(java.util.Locale.GERMAN);
	
	final ProgressDialogImpl dialog = new ProgressDialogImpl(null);

   dialog.setLocation(100, 100);
	dialog.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	dialog.setInfo("Dokument wird exportiert...");
	dialog.setDetails("Lade Datei aus Datenbank");
	dialog.setProgress(30, 100);
	dialog.setCancelable(true);

	new Thread() {
		@Override
        public void run() {
			for(int b = 0; b < 10; b++)
			for(int a = 0; a < 101; a+=2) {
				dialog.setProgress(a, 100);
				dialog.setDetails("Lade Datei vom Server", "Noch "+ (9-b) +" Dateien");
				try{sleep(a == 0 ? 1000 : 5);}catch(InterruptedException ex) {}
			}
			dialog.close();
		}
	}.start();
	
	dialog.show();

	System.exit(0);
		
}
/**
 * run method comment.
 */
@Override
public void run() {
	
	if(doClose) {
		doClose = false;
		if(optionDialog != null) {
			optionDialog.dispose();
		}
		dispose();
		return;
	}

	if(doTitle) {
		doTitle = false;
		super.setTitle(title);
	}

	if(doProgress) {
		doProgress = false;
		int w = pProgress.getWidth();
		w = (w * progress) / 100;
		lProgress.setSize(w, pProgress.getHeight());
		pProgress.repaint();
	}
	
	if(doInfo) {
		doInfo = false;
		lInfo.setText(info);
		lInfo.repaint();
	}
	
	if(doDetails) {
		doDetails = false;
		lDetails1.setText(details1);
		lDetails2.setText(details2);
		lDetails1.repaint();
		lDetails2.repaint();
	}

	if(doCancelable) {
		doCancelable = false;
		bCancel.setEnabled(cancelable);
		bCancel.repaint();
	}
	
}
/**
 * Insert the method's description here.
 *
 * @param maxValue long
 * @param curentValue long
 */
@Override
public synchronized void setCancelable(boolean value) {

	cancelable   = value;
	doCancelable = true;
	
	invokeLater();
	
}
/**
 * Insert the method's description here.
 *
 * @param maxValue long
 * @param curentValue long
 */
@Override
public synchronized void setDetails(String text) {

	setDetails(text, " ");
	
}
/**
 * Insert the method's description here.
 *
 * @param maxValue long
 * @param curentValue long
 */
@Override
public synchronized void setDetails(String text1, String text2) {

	details1  = text1;
	details2  = text2;

	doDetails = true;

	invokeLater();
}
/**
 * Insert the method's description here.
 *
 * @param maxValue long
 * @param curentValue long
 */
@Override
public synchronized void setInfo(String text) {

	info   = text;
	doInfo = true;
	
	invokeLater();
	
}
/**
 * Insert the method's description here.
 *
 * @param maxValue long
 * @param curentValue long
 */
@Override
public synchronized void setMyTitle(String text) {

	title   = text;
	doTitle = true;

	invokeLater();
	
}
/**
 * Insert the method's description here.
 *
 * @param maxValue long
 * @param curentValue long
 */
@Override
public synchronized void setProgress(long currentValue, long maxValue) {

	double max = maxValue;
	double cur = currentValue;
	int    old = progress;
		
	progress   = (int)(cur/max*100+0.5);
	doProgress = true;

//	System.out.println("Progress: "+progress);

	if(old != progress) {
		invokeLater();
	}
	
}
/**
 * Dialog anzeigen (Parent Frame disabled)
 *
 * Creation date: (27.10.00 11:29:13)
 *
 */
@Override
public void show() {
	Component frame = SUtilities.getRootFrame(this);
	boolean oldEnabled = true;
	try{
		if (frame != null) {
			oldEnabled=frame.isEnabled();
			frame.setEnabled(false);
		}
		super.show();
		setProgress(0, 100);

	} finally {
		if (frame != null)
			frame.setEnabled(oldEnabled);
	}
}
}
