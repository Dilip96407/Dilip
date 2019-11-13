package de.westlb.mgb.client.mask.view.shared;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.shared.NewTradeReclamationModel;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SButton;
import de.westlb_systems.xaf.swing.SFileDialog;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.ui.components.VComboBox;
import de.westlb_systems.xaf.ui.components.VTextArea;
import de.westlb_systems.xaf.ui.components.VTextField;
import de.westlb_systems.xaf.ui.components.event.ValueChangeEvent;
import de.westlb_systems.xaf.ui.components.event.ValueChangeListener;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.EditDialog;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.util.Debug;


/**
 * @author WSY4148
 *
 * Dialog to enter a new manual state of a trade.
 */
public class NewTradeReclamationDialog extends AbstractView implements EditDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7978690132113037210L;

	private static final int MAX_LENGTH_COMMENT = 255;

	/**
	 * PropertyHandler for the model view synchronization.
	 */
	private static final Dimension commentSize = new Dimension(250,100);
	private String RESOURCE_BASE = getResourceBase();
	
	private PropertyHandler propertyHandler = new PropertyHandler();
	
	private SLabel lState				= new SLabel();
	
	private SLabel lComment				= new SLabel();
	private SLabel lAttachment			= new SLabel();
	private SLabel lLevel 				= new SLabel();
	
	private VComboBox cbState			= new VComboBox();
	private VTextArea taComment			= new VTextArea(MAX_LENGTH_COMMENT);
	private VTextField tfAttachment		= new VTextField();
	private ReclamationLevelPanelView pLevel = new ReclamationLevelPanelView();
	
	private SButton bFileDialog 		= new SButton();
	private SFileDialog fileDialog		= null;
	private static String dialogPath	= null;

	/**
	 * Listener fuer alle Events
	 */
	private Listener listener = new Listener();

	/**
	 * Listener fuer alle Events
	 */
	private class Listener implements ActionListener, ValueChangeListener, PropertyChangeListener {
		@Override
        public void valueChanged(ValueChangeEvent event){
		}
		
		@Override
        public void propertyChange(PropertyChangeEvent event) {
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
	public NewTradeReclamationDialog() {
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
	public NewTradeReclamationDialog(Model model) {
		this();
		setModel(model);
	}
	
    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (!(getModel() instanceof NewTradeReclamationModel)) {
            return;
        }
        propertyHandler.syncFields();
    }
	
    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(NewTradeReclamationModel.STATE_CB_MODEL,	cbState);	
		propertyHandler.add(NewTradeReclamationModel.COMMENT, 			taComment);
		propertyHandler.add(NewTradeReclamationModel.ATTACHMENT_PATH,	tfAttachment);
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
    	lState.setText			(getResourceString(RESOURCE_BASE + "L_STATE"));
    	lAttachment.setText		(getResourceString(RESOURCE_BASE + "L_ATTACHMENT"));
		lComment.setText		(getResourceString(RESOURCE_BASE + "L_COMMENT"));
		bFileDialog.setText		(getResourceString(RESOURCE_BASE + "B_SELECT"));
    }


    /**
     * Method initLayout.
     */
    private void initLayout() {
    	// Line 1
    	add(lState,		 		new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
    	add(lLevel,		 		new GridBagConstraints2(1, 0, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
    	// Line 2
    	add(cbState, 			new GridBagConstraints2(0, 1, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
    	add(pLevel, 			new GridBagConstraints2(1, 1, 1, 5, 0.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
    	// Line 3
    	add(lComment, 			new GridBagConstraints2(0, 2, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
    	// Line 4
    	add(taComment,	 		new GridBagConstraints2(0, 3, 1, 1, 1.0, 1.0, NWEST, BOTH, insets8800, 0, 0));
    	// Line 5
    	add(lAttachment, 		new GridBagConstraints2(0, 4, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
    	// Line 6
    	add(tfAttachment, 		new GridBagConstraints2(0, 5, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
    	add(bFileDialog, 		new GridBagConstraints2(1, 5, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));

		taComment.setMinimumSize(commentSize);
		taComment.setPreferredSize(commentSize);
		
		setBackgroundForAllPanels(UIManager.getColor("controlBackground"));
    }


    /**
     * Method initComponents.
     */
    private void initComponents() {
    	cbState.setMandatory(true);
    	bFileDialog.addActionListener(listener);
    }


	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof NewTradeReclamationModel)) {
	        throw new IllegalArgumentException("Model not instance of NewTradeReclamationModel");
	    }
	    propertyHandler.setModel(newModel);
	    pLevel.setModel(newModel);
	    super.setModel(newModel);
	    fillView();
	}

    private void showFileDialog() {
        if (fileDialog == null) {
            fileDialog = new SFileDialog(this);
        }

        fileDialog.setDirectory(dialogPath);
        fileDialog.setMode(SFileDialog.LOAD);
        fileDialog.setTitle(getResourceString(RESOURCE_BASE + "T_FILE"));

        fileDialog.show();

        if (fileDialog.getFile() != null) {
            dialogPath = fileDialog.getDirectory();
            File file = new File(fileDialog.getDirectory(), fileDialog.getFile());
            if (file.canRead()) {
                String filepath = file.getPath();
                tfAttachment.setValue(filepath);
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
