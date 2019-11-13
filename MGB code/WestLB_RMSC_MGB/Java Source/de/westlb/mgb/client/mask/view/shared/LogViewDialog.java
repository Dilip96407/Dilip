package de.westlb.mgb.client.mask.view.shared;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import de.westlb.mgb.client.application.LocalUserSettings;
import de.westlb.mgb.client.mask.model.shared.LogViewModel;
import de.westlb_systems.xaf.application.Synchronizer;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SButton;
import de.westlb_systems.xaf.swing.SFileDialog;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.SPanel;
import de.westlb_systems.xaf.swing.STextArea;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.ui.view.ViewDialog;
import de.westlb_systems.xaf.util.Debug;


/**
 * @author WSY4148
 *
 * Dialog to display all new traders which not assigned to an employee in MGB.
 */
public class LogViewDialog extends AbstractView implements ViewDialog {

    private static final long serialVersionUID = 1304450014465343001L;
    private String RESOURCE_BASE = getResourceBase();
	private PropertyHandler propertyHandler = new PropertyHandler();

    private static final Dimension logTextSize = new Dimension(500,300);
    private static final Dimension badTextSize = new Dimension(500,200);

    GridLayout gridLayout1 = new GridLayout();
    SPanel pDialog = new SPanel();
    private SLabel lLog          = new SLabel(); 
    private SLabel lBad          = new SLabel(); 
    private SLabel lData         = new SLabel(); 
    private SButton bFileDialog  = new SButton();
    private SFileDialog fileDialog = null;

    private STextArea taLog = new STextArea();
    private STextArea taBad = new STextArea();
    
    private Listener listener = new Listener();

	/**
	 * Constructor for CheckStateView.
	 */
	public LogViewDialog() {
		try {
			initComponents();
			initLayout();
			initLabels();
			initProperties();
		} catch (Exception ex) {
			Debug.log(Debug.ERROR, this, "Exception in View-Constuctor");
			Debug.log(ex);
			ex.printStackTrace();
		}
	}
	
	/**
	 * Constructor which sets the model
	 */
	public LogViewDialog(Model model) {
		this();
		setModel(model);
	}

	   private class Listener implements ActionListener {
	        /**
	         * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	         */
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (e.getSource() == bFileDialog) {
	                doSaveAs();
	            }
	        }
	    }

	
    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        propertyHandler.syncFields();
    }

	
    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(LogViewModel.P_LOG_TEXT,	taLog);	
		propertyHandler.add(LogViewModel.P_BAD_TEXT,	taBad);	
    }

    /**
     * Method initLabels.
     */
    private void initLabels() {
		setTitle			    (getResourceString(RESOURCE_BASE + "T_001")	);
		lLog.setText            (getResourceString(RESOURCE_BASE + "L_LOG"));
        lBad.setText            (getResourceString(RESOURCE_BASE + "L_BAD"));
        lData.setText           (getResourceString(RESOURCE_BASE + "L_DATA"));
        bFileDialog.setText     (getResourceString(RESOURCE_BASE + "L_SAVE_AS"));
    }

    /**
     * Method initLayout.
     */
    private void initLayout() {
        taLog.setMinimumSize(logTextSize);
        taLog.setPreferredSize(logTextSize);

        taBad.setMinimumSize(badTextSize);
        taBad.setPreferredSize(badTextSize);

        pDialog.add(lLog,           new GridBagConstraints2(0, 0, 2, 1, 1.0, 0.0, NWEST, HORIZ, insets0000, 0, 0));
        pDialog.add(taLog,			new GridBagConstraints2(0, 1, 2, 1, 1.0, 1.0, NWEST, BOTH, insets0000, 0, 0));
        pDialog.add(lBad,           new GridBagConstraints2(0, 2, 2, 1, 1.0, 0.0, NWEST, HORIZ, insets4000, 0, 0));
        pDialog.add(taBad,			new GridBagConstraints2(0, 3, 2, 1, 1.0, 1.0, NWEST, BOTH, insets0000, 0, 0));
        pDialog.add(lData,          new GridBagConstraints2(0, 4, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4000, 0, 0));
        pDialog.add(bFileDialog,    new GridBagConstraints2(1, 4, 1, 1, 0.0, 0.0, NWEST, NONE, insets4000, 0, 0));

        this.add(pDialog,	new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, new Insets(0, 0, 0, 0), 0, 0));
     }


    /**
     * Method initComponents.
     */
    private void initComponents() {
        bFileDialog.addActionListener(listener);
    }

	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof LogViewModel)) {
	        throw new IllegalArgumentException("Model not instance of LogViewModel");
	    }
	    propertyHandler.setModel(newModel);
	    super.setModel(newModel);
	    fillView();
	}

    /**
     * Return casted model
     * 
     */
    public LogViewModel getLogViewModel() {
        return getModel() instanceof LogViewModel ? (LogViewModel)getModel() : null;
    }

    public boolean doSaveAs() {
        boolean success = false;
        if (fileDialog == null) {
            fileDialog = new SFileDialog(this);
        }

        fileDialog.setDirectory(LocalUserSettings.getInstance().getString("jobDir"));
        fileDialog.setMode(SFileDialog.SAVE);
        fileDialog.setTitle(getResourceString(RESOURCE_BASE + "T_FILE"));

        fileDialog.show();
        if ( fileDialog.getDirectory() != null) {
            LocalUserSettings.getInstance().setString("jobDir", fileDialog.getDirectory());
        }

        if (fileDialog.getFile() != null) {
            File file = new File(fileDialog.getDirectory(), fileDialog.getFile());
            // Synchronize model, because propertyHandler updates the model
            // only on focusLost-Events
            propertyHandler.syncModel();
                
            boolean oldC = Synchronizer.setWaitCursor(true, this);
            success = getLogViewModel().doSaveAs(file);
            Synchronizer.setWaitCursor(oldC, this);

            if (!success) {
                handleModelError();
            } 

            getModel().reload();
            fillView();
            return success;
        }

        for (java.awt.Container parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof java.awt.Window) {
                ((java.awt.Window) parent).toFront();
                break;
            }
        }
        return success;
    }

}
