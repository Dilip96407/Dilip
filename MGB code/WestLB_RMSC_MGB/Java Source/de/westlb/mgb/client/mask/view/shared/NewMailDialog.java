package de.westlb.mgb.client.mask.view.shared;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import de.westlb.mgb.client.mask.model.shared.CheckNewEmployeeModel;
import de.westlb.mgb.client.mask.model.shared.NewMailModel;
import de.westlb.mgb.client.mask.view.shared.def.MailFieldDef;
import de.westlb.mgb.client.ui.util.DateFormat;
import de.westlb.mgb.client.ui.util.VDateField;
import de.westlb_systems.xaf.application.Synchronizer;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.application.event.UserEventListener;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SBorder;
import de.westlb_systems.xaf.swing.SBorderFactory;
import de.westlb_systems.xaf.swing.SButton;
import de.westlb_systems.xaf.swing.SCheckBox;
import de.westlb_systems.xaf.swing.SFileDialog;
import de.westlb_systems.xaf.swing.SGridLayout;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.SPanel;
import de.westlb_systems.xaf.swing.STabbedPane;
import de.westlb_systems.xaf.swing.STable;
import de.westlb_systems.xaf.swing.STableEditorEvent;
import de.westlb_systems.xaf.swing.STableEditorListener;
import de.westlb_systems.xaf.ui.components.VComboBox;
import de.westlb_systems.xaf.ui.components.VTextArea;
import de.westlb_systems.xaf.ui.components.VTextField;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.DialogViewer;
import de.westlb_systems.xaf.ui.view.EditDialog;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.util.Debug;

/**
 * @author WSY4148
 *
 * Dialog to send a mail to a trader.
 */
public class NewMailDialog extends AbstractView implements EditDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3874624034350178168L;
	private static final Dimension commentSize = new Dimension(350,300);
	private static final int MAX_LENGTH_COMMENT = 255;
	private String RESOURCE_BASE = getResourceBase();
	private static String dialogPath	= null;
		
	/** Top level panels of the tabbed pane */
	private SPanel pBasicData					= new SPanel();
	private SPanel pReclamation					= new SPanel();
	private SPanel pCopyRecipients				= new SPanel();

	/** The tabbed pane */
	private STabbedPane tbNewMail = new STabbedPane();
	
	/** Components of the basic data panel */
	private SLabel lMailType			 		= new SLabel();
	private VComboBox cbMailType				= new VComboBox();
	private SLabel lToEmployeeName				= new SLabel();
	private SLabel lToEmployeeEMail				= new SLabel();
	private SLabel lSubject						= new SLabel();
	private SLabel lText						= new SLabel();
	private SLabel lCreationDate				= new SLabel(); 
	private VTextField	tfRecipientName			= new VTextField();
	private VTextField	tfRecipientMailAdress	= new VTextField();
	private VTextArea	taText		 			= new VTextArea();
	private VTextField	tfSubject		 		= new VTextField();
	private VDateField  tfCreationDate			= new VDateField(DEF_COL_10, DateFormat.TIME_FORMAT);

	/** Components of the reclamation panel */
	private SLabel		lRemarkReclamation		= new SLabel();
	private SCheckBox	ckRemarkReclamation		= new SCheckBox();
	private SLabel lState						= new SLabel();
	private SLabel lComment						= new SLabel();
	private SLabel lAttachment					= new SLabel();
	private VComboBox cbState					= new VComboBox();
	private VTextArea taComment					= new VTextArea(MAX_LENGTH_COMMENT);
	private VTextField tfAttachment				= new VTextField();
	private SButton bFileDialog 				= new SButton();
	private SFileDialog fileDialog				= null;
	
	/** Components of the copy recipients panel */
	private STable tCopyRecipients				= new STable();
	private SPanel pCopyRecipientsButtons		= new SPanel();
	private SButton bAdd						= new SButton();
	private SButton bRemove						= new SButton();
	
	private CheckNewEmployeeDialog employeeSearchView;

	private static final int I_TAB_MAIL = 0;
	private static final int I_TAB_RECL = 1;
	
	// Current value of the checkbox 
	private Boolean remarkReclamation = Boolean.TRUE;
	
	/** Property handler to synchronize model and view */
	private PropertyHandler propertyHandler = new PropertyHandler();	

	/** Listener fuer alle Events */
	private Listener listener = new Listener();
	
	/**
	 * Listener fuer alle Events
	 */
	private class Listener implements ActionListener, UserEventListener, PropertyChangeListener, STableEditorListener {
        /**
         * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
			if (e.getSource() == bFileDialog) {
				showFileDialog();
			} else if (e.getSource() == bAdd) {
				invokeSearchDialog();
			} else if (e.getSource() == bRemove) {
				removeCopyRecipient(tCopyRecipients.getSelectedRow());
			}
        }
        
		/**
		 * @see java.beans.PropertyChangeListener#propertyChange(PropertyChangeEvent)
		 */
		@Override
        public void propertyChange(PropertyChangeEvent event) {
			if (NewMailModel.P_RECL_REMARK.equals(event.getPropertyName())) {
				recalcEnabled();
			} 
		}

		/* (non-Javadoc)
		 * @see de.westlb_systems.xaf.application.event.UserEventListener#actionRequested(de.westlb_systems.xaf.application.event.UserEvent)
		 */
		@Override
        public void actionRequested(UserEvent ev) {
			if (ev.getSource() == employeeSearchView && ev.getAction() == CheckNewEmployeeDialog.APPLY_PERFORMED) {
				addCopyRecipient((Long)ev.getParameters());
			}			
		}

		/* (non-Javadoc)
		 * @see de.westlb_systems.xaf.swing.STableEditorListener#invokeEditor(de.westlb_systems.xaf.swing.STableEditorEvent)
		 */
		@Override
        public void invokeEditor(STableEditorEvent e) {
			if (STableEditorEvent.INSERT == e.getActionCommand()) {
				invokeSearchDialog();
			} else if (STableEditorEvent.DELETE == e.getActionCommand()) {
				removeCopyRecipient(e.getRow());
			}
		}
	}
	
	/**
	 * Constructor for CheckStateView.
	 */
	public NewMailDialog() {
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
	public NewMailDialog(Model model) {
		this();
		setModel(model);
	}


    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (getNewMailModel() == null) {
            return;
        }
        propertyHandler.syncFields();
    }
    
    /**
	 * Return casted model
	 * 
	 * Creation date: (8/31/00 10:43:03 AM)
	 * f
	 */
	public NewMailModel getNewMailModel() {
		return getModel() instanceof NewMailModel ? (NewMailModel)getModel() : null;
	}


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(NewMailModel.P_TEXT,						taText);	
		propertyHandler.add(NewMailModel.P_SUBJECT,						tfSubject);	
		propertyHandler.add(NewMailModel.P_RECIPIENT_EMPLOYEE_NAME,		tfRecipientName);	
		propertyHandler.add(NewMailModel.P_RECIPIENT_EMAIL,				tfRecipientMailAdress);
		propertyHandler.add(NewMailModel.P_RECL_CODE_CB_MODEL,			cbState);	
		propertyHandler.add(NewMailModel.P_RECL_CODE,					cbState);	
		propertyHandler.add(NewMailModel.P_RECL_COMMENT, 				taComment);
		propertyHandler.add(NewMailModel.P_RECL_ATTACHMENT_PATH,		tfAttachment);
		propertyHandler.add(NewMailModel.P_RECL_REMARK,					ckRemarkReclamation);
		propertyHandler.add(NewMailModel.P_MAIL_TYPE_CB_MODEL,			cbMailType);
		propertyHandler.add(NewMailModel.P_MAIL_TYPE,					cbMailType);
		propertyHandler.add(NewMailModel.P_CREATION_DATE,				tfCreationDate);
		propertyHandler.add(NewMailModel.P_COPY_RECIPIENTS_TABLE_MODEL, tCopyRecipients);
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
		tbNewMail.setTitleAt(0, getResourceString(RESOURCE_BASE + "T_BASICDATA"));
		tbNewMail.setTitleAt(1, getResourceString(RESOURCE_BASE + "T_RECLAMATION"));
		tbNewMail.setTitleAt(2, getResourceString(RESOURCE_BASE + "T_COPY_RECIPIENTS"));

		lMailType.setText			(getResourceString(RESOURCE_BASE + "L_MAIL_TYPE"));
		lToEmployeeName.setText		(getResourceString(RESOURCE_BASE + "L_employeeName"));    	
		lToEmployeeEMail.setText	(getResourceString(RESOURCE_BASE + "L_employeeEMail"));
    	lSubject.setText			(getResourceString(RESOURCE_BASE + "L_subject"));
    	lText.setText				(getResourceString(RESOURCE_BASE + "L_text"));
    	lCreationDate.setText		(getResourceString(RESOURCE_BASE + "L_CREATION_DATE"));
    	
		lRemarkReclamation.setText	(getResourceString(RESOURCE_BASE + "L_REMARK_RECLAMATION"));
		lState.setText				(getResourceString(RESOURCE_BASE + "L_STATE"));
		lAttachment.setText			(getResourceString(RESOURCE_BASE + "L_ATTACHMENT"));
		lComment.setText			(getResourceString(RESOURCE_BASE + "L_COMMENT"));
		bFileDialog.setText			(getResourceString(RESOURCE_BASE + "B_SELECT"));
		
		bAdd.setText				(getResourceString(RESOURCE_BASE + "B_ADD_COPY_RECIPIENT"));
		bRemove.setText				(getResourceString(RESOURCE_BASE + "B_REMOVE_COPY_RECIPIENT"));
    }
    
    /**
     * Method initLayout.
     */
    private void initLayout() {
		SBorder emptyBorder = SBorderFactory.createEmptyBorder(insets4088);
		
		pBasicData.setBorder(emptyBorder);
		pReclamation.setBorder(emptyBorder);
		
		taText.setMinimumSize(commentSize);
		taText.setPreferredSize(commentSize);

		pBasicData.add(lMailType,			 	new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		pBasicData.add(cbMailType, 				new GridBagConstraints2(0, 1, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		pBasicData.add(lCreationDate, 			new GridBagConstraints2(0, 2, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));		
		pBasicData.add(tfCreationDate, 			new GridBagConstraints2(0, 3, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));		
		pBasicData.add(lToEmployeeName,		 	new GridBagConstraints2(0, 4, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		pBasicData.add(tfRecipientName, 		new GridBagConstraints2(0, 5, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		pBasicData.add(lToEmployeeEMail, 		new GridBagConstraints2(0, 6, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		pBasicData.add(tfRecipientMailAdress,	new GridBagConstraints2(0, 7, 1, 1, 1.0, 0.0, NWEST, BOTH,  insets8800, 0, 0));
		pBasicData.add(lSubject, 				new GridBagConstraints2(0, 8, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		pBasicData.add(tfSubject, 				new GridBagConstraints2(0, 9, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		pBasicData.add(lText, 					new GridBagConstraints2(0, 10, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		pBasicData.add(taText, 					new GridBagConstraints2(0, 11, 1, 1, 1.0, 1.0, NWEST, HORIZ, insets8800, 0, 0));

		pReclamation.add(lRemarkReclamation,	new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		pReclamation.add(ckRemarkReclamation,	new GridBagConstraints2(0, 1, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		pReclamation.add(lState,		 		new GridBagConstraints2(0, 2, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		pReclamation.add(cbState, 				new GridBagConstraints2(0, 3, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		pReclamation.add(lComment, 				new GridBagConstraints2(0, 4, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		pReclamation.add(taComment,	 			new GridBagConstraints2(0, 5, 1, 1, 1.0, 1.0, NWEST, BOTH,  insets8800, 0, 0));
		pReclamation.add(lAttachment, 			new GridBagConstraints2(0, 6, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		pReclamation.add(tfAttachment, 			new GridBagConstraints2(0, 7, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		pReclamation.add(bFileDialog, 			new GridBagConstraints2(1, 8, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));

		// Layout copy recpients panel
		SGridLayout sGridLayout = new SGridLayout();
		pCopyRecipientsButtons.setLayout(sGridLayout);
		pCopyRecipientsButtons.setOpaque(false);
		pCopyRecipientsButtons.setLayout(sGridLayout);
		pCopyRecipientsButtons.setOpaque(false);
		sGridLayout.setHgap(10);

		pCopyRecipients.add(tCopyRecipients, 		new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, NWEST, BOTH, insets8888, 0, 0));
		pCopyRecipients.add(pCopyRecipientsButtons, new GridBagConstraints2(0, 1, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8888, 0, 0));
		pCopyRecipientsButtons.add(bAdd, null);
		pCopyRecipientsButtons.add(bRemove, null);

		
		taComment.setMinimumSize(commentSize);
		taComment.setPreferredSize(commentSize);
		
		this.add(tbNewMail,    				new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, insets0000, 0, 0));
    }

    /**
     * Method initComponents.
     */
    private void initComponents() {
    	tfSubject.setMandatory(true);
    	    	
    	tfSubject.setMaxLength(MailFieldDef.SUBJECT_MAX_LENGTH);
    	tfRecipientMailAdress.setMaxLength(MailFieldDef.TEXT_MAX_LENGTH);
    	
    	tfRecipientMailAdress.setEditable(false);
    	tfRecipientName.setEditable(false);
    	
    	ckRemarkReclamation.setValue(remarkReclamation);
		bFileDialog.addActionListener(listener);

		tbNewMail.addTab(null, pBasicData);
	    tbNewMail.addTab(null, pReclamation);
	    tbNewMail.addTab(null, pCopyRecipients);
		tbNewMail.setSelectedIndex(I_TAB_MAIL);
		
		tfCreationDate.setEditable(false);
		
		bAdd.addActionListener(listener);
		bRemove.addActionListener(listener);
		
		tCopyRecipients.addTableEditorListener(listener);
    }
    
	private void recalcEnabled() {
		cbMailType.setEditable(false);
		cbMailType.setEnabled(false);

		NewMailModel model = getNewMailModel();
		if (model != null) {
			if (!model.isReclamationMail() || model.isModeDisplayMail()) {
				if (tbNewMail.getTabCount() > I_TAB_RECL) {
					tbNewMail.removeTabAt(I_TAB_RECL);
					tfSubject.setEditable(false);
				}
			} else if (!(remarkReclamation.equals(model.getRemarkReclamation()))) {
				if (tbNewMail.getComponentAt(I_TAB_RECL) == null) {
					tbNewMail.add(pReclamation);
				}
				remarkReclamation = model.getRemarkReclamation();
				cbState.setEditable(false);
				taComment.setEditable(false);
				tfAttachment.setEditable(false);
			}
    		if (model.isModeDisplayMail()) {
    			taText.setEditable(false);
    			bAdd.setEnabled(false);
    			bRemove.setEnabled(false);
    			tCopyRecipients.setPopupProvider(STable.DEFAULT_PROVIDER);
    		} else {
    			tCopyRecipients.setPopupProvider(STable.ADD_REMOVE_PROVIDER);
    		}
		}				
		invalidate();
		validate();
		repaint();
	}

	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof NewMailModel)) {
	        throw new IllegalArgumentException("Model not instance of NewMailModel");
	    }
	    propertyHandler.setModel(newModel);
	    
		if (getModel() != null) {
			getModel().removePropertyChangeListener(listener);
		}
		super.setModel(newModel);
		if (getModel() != null) {
			getModel().addPropertyChangeListener(listener);
		}


	    fillView();
		recalcEnabled();
	}

		
	/**
	 * Updates all language dependend labels
	 */
	@Override
    public void updateLabels() {
		initLabels();
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

	/* (non-Javadoc)
	 * @see de.westlb_systems.xaf.swing.SPanel#getTitle()
	 */
	@Override
    public String getTitle() {
		String title;
		
		if (getNewMailModel() == null) {
			title = super.getTitle();
		} else  {
			title = getNewMailModel().getTitle();
		}
		return title;
	}
	
	/**
	 * Diese Methode fängt die UserEvents der Dialoge <code>KD01Dialog</code>, <code>GP01Dialog</code>,
	 * <code>AP01Dialog</code> und <code>SB04Dialog</code> ab und behandelt sie weiter.
	 *
	 * @see de.westlb_systems.webvis.kunde.view.KD01Dialog
	 * @see de.westlb_systems.webvis.gespraechspartner.view.GP01Dialog
	 * @see de.westlb_systems.webvis.ansprechpartner.view.AP01Dialog
	 * @see de.westlb_systems.webvis.serienbrief.view.SB04Dialog
	 *
	 * <pre>
	 * Erstellungsdatum: (25.07.00 10:29:03)
	 * </pre>
	 */

	private void handleDialogAction(UserEvent ev) {
		if (ev.getSource() instanceof SButton)
			 ((SButton) ev.getSource()).setEnabled(false);
		try {
			// hier die Bearbeitung des Events
			Synchronizer.setWaitCursor(true, this);
		} finally {
			Synchronizer.setWaitCursor(false, this);

			if (ev.getSource() instanceof SButton)
				 ((SButton) ev.getSource()).setEnabled(true);
		}
	}


	/**
	 * This method starts the employee search dialog.
	 */
	private void invokeSearchDialog() {
		CheckNewEmployeeModel model = new CheckNewEmployeeModel();
		employeeSearchView = new CheckNewEmployeeDialog(model, false);
		DialogViewer dialog = DialogViewer.createDialog(employeeSearchView, this);
		dialog.addUserEventListener(
			new UserEventListener() {
				@Override
                public void actionRequested(UserEvent event) {
					handleDialogAction(event);
				}
			}
		);
		
		employeeSearchView.addUserEventListener(listener);
		dialog.showDialog();
		employeeSearchView = null;
	}

	/**
	 * Add employee to copy recipients.
	 */
	private void addCopyRecipient(Long employeeId) {
		if (getNewMailModel() == null) {
			return;
		}
		getNewMailModel().addCopyRecipient(employeeId);
	}
	
	/**
	 * Remove the currently selected employee.
	 */
	private void removeCopyRecipient(int row) {
		if (getNewMailModel() == null) {
			return;
		}
		getNewMailModel().removeCopyRecipient(row);
	}
}
