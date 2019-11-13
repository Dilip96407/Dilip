package de.westlb.mgb.client.mask.view.shared;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import de.westlb.mgb.client.application.LocalUserSettings;
import de.westlb.mgb.client.mask.model.shared.NewJobModel;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SButton;
import de.westlb_systems.xaf.swing.SFileDialog;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.ui.components.VComboBox;
import de.westlb_systems.xaf.ui.components.VTextField;
import de.westlb_systems.xaf.ui.components.event.ValueChangeEvent;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.EditDialog;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.util.Debug;

/**
 * @author WSY4148
 *
 * Dialog to enter a new manual state of a trade.
 */
public class NewJobDialog extends AbstractView implements EditDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5591654653761935929L;

	/**
	 * PropertyHandler for the model view synchronization.
	 */
	private String RESOURCE_BASE = getResourceBase();
	
	private PropertyHandler propertyHandler = new PropertyHandler();
	
	private SLabel lSourceSystem		= new SLabel();
	private SLabel lFilePath			= new SLabel();
	
	private VComboBox cbSourceSystem	= new VComboBox();
	private VTextField tfFilePath		= new VTextField();
	
	private SButton bFileDialog 		= new SButton();
	private SFileDialog fileDialog		= null;

	/**
	 * Listener fuer alle Events
	 */
	private Listener listener = new Listener();

	/**
	 * Listener fuer alle Events
	 */
	private class Listener implements ActionListener {
		@SuppressWarnings("unused")
        public void valueChanged(ValueChangeEvent event){
		}
		
        /**
         * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
        	if (e.getSource() == bFileDialog) {
        		showFileDialog();
        	}
        }

	}
	
	/**
	 * Constructor for CheckStateView.
	 */
	public NewJobDialog() {
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
	 * Return the casted model.
	 */
	public NewJobModel getNewJobModel() {
		return getModel() != null ? (NewJobModel)getModel() : null;
	}
	
	
	/**
	 * Constructor which sets the model
	 */
	public NewJobDialog(Model model) {
		this();
		setModel(model);
	}
	
    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (!(getModel() instanceof NewJobModel)) {
            return;
        }
        NewJobModel model = (NewJobModel)getModel();
        propertyHandler.syncFields();
        if (model.isSourceSystemFixed()) {
        	cbSourceSystem.setEditable(false);
        	cbSourceSystem.setEnabled(false);
        }
    }


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(NewJobModel.P_SOURCE_SYSTEM_CB_MODEL,	cbSourceSystem);	
		propertyHandler.add(NewJobModel.P_FILE_PATH,				tfFilePath);
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
    	lSourceSystem.setText	(getResourceString(RESOURCE_BASE + "L_SOURCE_SYSTEM"));
    	lFilePath.setText		(getResourceString(RESOURCE_BASE + "L_FILE_PATH"));
		bFileDialog.setText		(getResourceString(RESOURCE_BASE + "B_SELECT"));
    }


    /**
     * Method initLayout.
     */
    private void initLayout() {
    	add(lSourceSystem,	new GridBagConstraints2(0, 0, 2, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
    	add(cbSourceSystem, new GridBagConstraints2(0, 1, 2, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
    	add(lFilePath, 		new GridBagConstraints2(0, 2, 2, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
    	add(tfFilePath,		new GridBagConstraints2(0, 3, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
    	add(bFileDialog,	new GridBagConstraints2(1, 3, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		add(new SLabel(),	new GridBagConstraints2(1, 4, 1, 1, 0.0, 1.0, NWEST, BOTH, insets8800, 0, 0));
    }

    /**
     * Method initComponents.
     */
    private void initComponents() {
    	cbSourceSystem.setMandatory(true);
		tfFilePath.setMandatory(true);
    	bFileDialog.addActionListener(listener);
    }

	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof NewJobModel)) {
	        throw new IllegalArgumentException("Model not instance of NewJobModel");
	    }
	    super.setModel(newModel);
	    propertyHandler.setModel(newModel);
	    fillView();
	}

    private void showFileDialog() {
        if (fileDialog == null) {
            fileDialog = new SFileDialog(this);
        }

 		fileDialog.setDirectory(LocalUserSettings.getInstance().getString("jobDir"));
        fileDialog.setMode(SFileDialog.LOAD);
        fileDialog.setTitle(getResourceString(RESOURCE_BASE + "T_FILE"));

        fileDialog.show();
        if ( fileDialog.getDirectory() != null) {
			LocalUserSettings.getInstance().setString("jobDir", fileDialog.getDirectory());
        }

        if (fileDialog.getFile() != null) {
            File file = new File(fileDialog.getDirectory(), fileDialog.getFile());
            if (file.canRead()) {
                String filepath = file.getPath();
                tfFilePath.setValue(filepath);
                // Synchronize model, because propertyHandler updates the model
                // only on focusLost-Events
                propertyHandler.syncModel();
            } else {
                logMessage(SHOW_ERROR, getResourceString(RESOURCE_BASE + "E_004"));
                return;
            }

        }

        for (java.awt.Container parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof java.awt.Window) {
                ((java.awt.Window) parent).toFront();
                break;
            }
        }
       	bFileDialog.requestFocus();
    }

}
