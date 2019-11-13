package de.westlb.mgb.client.mask.view.shared;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.westlb.mgb.client.mask.model.shared.StateCodeEditModel;
import de.westlb.mgb.model.definition.StateCodeTypeDef;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SCheckBox;
import de.westlb_systems.xaf.swing.SComboBox;
import de.westlb_systems.xaf.swing.SComponent;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.SPanel;
import de.westlb_systems.xaf.ui.components.VIntegerField;
import de.westlb_systems.xaf.ui.components.VTextArea;
import de.westlb_systems.xaf.ui.components.VTextField;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.EditDialog;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.util.Debug;

/**
 * @author WSY4148
 *
 * Dialog to enter a new manual state of a trade.
 */
public class StateCodeEditDialog extends AbstractView implements EditDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6284817145545765967L;
	private String RESOURCE_BASE = getResourceBase();
	private static final int MAX_LENGTH_SHORT_DESCRIPTION = 40;
	private static final int MAX_LENGTH_STATE_CODE = 50;
	private static final int MAX_LENGTH_LONG_DESCRIPTION = 200;
	private static final int MAX_LENGTH_COMMENT = 1000;
	

	private static final Dimension textAreaSize = new Dimension(250,100);

	/** gui components */
	private SPanel pBasicData	= new SPanel();
	
	private SLabel lFinalState				= new SLabel();
	private SLabel lShortDescription		= new SLabel();
	private SLabel lStateCode				= new SLabel();
	private SLabel lType					= new SLabel();
	private SLabel lLongDescription			= new SLabel();
	private SLabel lComment					= new SLabel();
	private SLabel lManualCheckRequired		= new SLabel();
	private SLabel lReclamationRequired		= new SLabel();
	private SLabel lReclamationCode			= new SLabel();
	private SLabel lMarketDataRequestType  	= new SLabel();
	private SLabel lMccChecked 				= new SLabel();
	private SLabel lManualSampleCode		= new SLabel();
	private SLabel lManualSamplePercentage	= new SLabel();
	
	private VTextField	tfShortDescription		= new VTextField(MAX_LENGTH_SHORT_DESCRIPTION,	DEF_COL_20);
	private VTextField	tfStateCode				= new VTextField(MAX_LENGTH_STATE_CODE,			DEF_COL_20);
	private SComboBox	cbType					= new SComboBox();
	private VTextArea	taLongDescrption		= new VTextArea(MAX_LENGTH_LONG_DESCRIPTION);
	private VTextArea	taComment				= new VTextArea(MAX_LENGTH_COMMENT);
	private SCheckBox	ckManualCheckRequired	= new SCheckBox();
	private SCheckBox	ckReclamationRequired	= new SCheckBox();
	private SCheckBox	ckFinalState			= new SCheckBox();
	private SCheckBox	ckMccChecked			= new SCheckBox();
	private SComboBox	cbReclamationCode		= new SComboBox();
	private SComboBox	cbMarketDataRequestType	= new SComboBox();
	private SComboBox 	cbManualSampleCode		= new SComboBox();
	private VIntegerField 	tfManualSamplePercentage	= new VIntegerField();
	
	/** Property handler to synchronize model and view */
	private PropertyHandler propertyHandler = new PropertyHandler();	

	/** Listener fuer alle Events */
	private Listener listener = new Listener();
	
	/**
	 * Listener fuer alle Events
	 */
	private class Listener implements ActionListener, PropertyChangeListener {
        /**
         * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
        }
 

        /**
         * @see java.beans.PropertyChangeListener#propertyChange(PropertyChangeEvent)
         */
        @Override
        public void propertyChange(PropertyChangeEvent event) {
			if(StateCodeEditModel.P_TYPE.equals(event.getPropertyName())){
				recalcEnabled();
			} else
			if(StateCodeEditModel.P_RECLAMATION_REQUIRED.equals(event.getPropertyName())){
				recalcEnabled();
			}
        }

	}
	
	/**
	 * Constructor for CheckStateView.
	 */
	public StateCodeEditDialog() {
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
	public StateCodeEditDialog(Model model) {
		this();
		setModel(model);
	}


    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (getStateCodeEditModel() == null) {
            return;
        }
		recalcEnabled();
        propertyHandler.syncFields();
    }
    
 
    
    
    /**
	 * Return casted model
	 * 
	 */
	public StateCodeEditModel getStateCodeEditModel() {
		return getModel() instanceof StateCodeEditModel ? (StateCodeEditModel)getModel() : null;
	}


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(StateCodeEditModel.P_FINAL_STATE,						ckFinalState);	
		propertyHandler.add(StateCodeEditModel.P_LONG_DESCRIPTION, 					taLongDescrption);
		propertyHandler.add(StateCodeEditModel.P_COMMENT,		 					taComment);
		propertyHandler.add(StateCodeEditModel.P_MANUAL_CHECK_REQUIRED,				ckManualCheckRequired);
		propertyHandler.add(StateCodeEditModel.P_MCC_CHECKED,						ckMccChecked);
		propertyHandler.add(StateCodeEditModel.P_RECLAMATION_CODE_CB_MODEL,			cbReclamationCode);
		propertyHandler.add(StateCodeEditModel.P_RECLAMATION_CODE,					cbReclamationCode);
		propertyHandler.add(StateCodeEditModel.P_RECLAMATION_REQUIRED,				ckReclamationRequired);
		propertyHandler.add(StateCodeEditModel.P_SHORT_DESCRIPTION,					tfShortDescription);
		propertyHandler.add(StateCodeEditModel.P_STATE_CODE,						tfStateCode);
		propertyHandler.add(StateCodeEditModel.P_TYPE_CB_MODEL,						cbType);
		propertyHandler.add(StateCodeEditModel.P_TYPE,								cbType);
		propertyHandler.add(StateCodeEditModel.P_MARKET_DATA_REQUEST_TYPE_CB_MODEL,	cbMarketDataRequestType);
		propertyHandler.add(StateCodeEditModel.P_MARKET_DATA_REQUEST_TYPE,			cbMarketDataRequestType);
		propertyHandler.add(StateCodeEditModel.P_MANUAL_SAMPLE_CODE_CB_MODEL,		cbManualSampleCode);
		propertyHandler.add(StateCodeEditModel.P_MANUAL_SAMPLE_CODE,				cbManualSampleCode);
		propertyHandler.add(StateCodeEditModel.P_MANUAL_SAMPLE_PERCENTAGE,			tfManualSamplePercentage);
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
    	pBasicData.setTitle(getResourceString(RESOURCE_BASE + "T_BASICDATA"));

    	lFinalState.setText				(getResourceString(RESOURCE_BASE + "L_FINAL_STATE"));
    	lLongDescription.setText		(getResourceString(RESOURCE_BASE + "L_LONG_DESCRIPTION"));
    	lComment.setText				(getResourceString(RESOURCE_BASE + "L_COMMENT"));
    	lStateCode.setText				(getResourceString(RESOURCE_BASE + "L_STATE_CODE"));
       	lShortDescription.setText		(getResourceString(RESOURCE_BASE + "L_SHORT_DESCRIPTION"));
    	lLongDescription.setText		(getResourceString(RESOURCE_BASE + "L_LONG_DESCRIPTION"));
    	lManualCheckRequired.setText	(getResourceString(RESOURCE_BASE + "L_MANUAL_CHECK_REQUIRED"));
		lMccChecked.setText				(getResourceString(RESOURCE_BASE + "L_MCC_CHECKED"));
    	lMarketDataRequestType.setText	(getResourceString(RESOURCE_BASE + "L_MARKET_DATA_REQUEST_TYPE"));
    	lReclamationRequired.setText	(getResourceString(RESOURCE_BASE + "L_RECLAMATION_REQUIRED"));
    	lReclamationCode.setText		(getResourceString(RESOURCE_BASE + "L_RECLAMATION_CODE"));
    	lType.setText					(getResourceString(RESOURCE_BASE + "L_TYPE"));
    	lManualSampleCode.setText		(getResourceString(RESOURCE_BASE + "L_MANUAL_SAMPLE_CODE"));
    	lManualSamplePercentage.setText	(getResourceString(RESOURCE_BASE + "L_MANUAL_SAMPLE_PERCENTAGE"));
    }
    
    /**
     * Method initLayout.
     */
    private void initLayout() {
		int l, c;
		pBasicData.setBorderPainted(true);
		pBasicData.setMargin(insets0088);

		taLongDescrption.setMinimumSize(textAreaSize);
		taLongDescrption.setPreferredSize(textAreaSize);

		taComment.setMinimumSize(textAreaSize);
		taComment.setPreferredSize(textAreaSize);

		SPanel panel = new SPanel();
		pBasicData.add(panel,		new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, insets0000, 0, 0));

		l = 0; c = 0; // Column 0
	 	panel.add(lType,					new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lStateCode,				new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lShortDescription,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lLongDescription,			new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lComment,					new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lManualCheckRequired,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lReclamationRequired,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));
		panel.add(lMccChecked,				new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 		 	
	 	panel.add(lReclamationCode,			new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lManualSampleCode,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lManualSamplePercentage,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lMarketDataRequestType,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	

		l = 0; c = 1; // Column 0
	 	panel.add(cbType,					new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(tfStateCode,				new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(tfShortDescription,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(taLongDescrption,			new GridBagConstraints2(c, l++, 1, 1, 1.0, 1.0, NWEST, BOTH, insets4800, 0, 0));	 	
	 	panel.add(taComment,				new GridBagConstraints2(c, l++, 1, 1, 1.0, 1.0, NWEST, BOTH, insets4800, 0, 0));	 	
	 	panel.add(ckManualCheckRequired,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(ckReclamationRequired,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
		panel.add(ckMccChecked,				new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(cbReclamationCode,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(cbManualSampleCode,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(tfManualSamplePercentage,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(cbMarketDataRequestType,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	

		this.add(pBasicData,    			new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets0000, 0, 0));
    }

    /**
     * Method initComponents.
     */
    private void initComponents() {
    	cbType.setMandatory(true);
    	tfStateCode.setMandatory(true);
    	tfShortDescription.setMandatory(true);
    	
    }
    
    private void recalcEnabled() {
    	StateCodeEditModel model = getStateCodeEditModel();
    	if (model != null) {
    		boolean ckManualCheckRequiredOn = false;
			boolean ckMccCheckedOn = false;
    		boolean ckReclamationRequiredOn = false;
    		boolean cbReclamationCodeOn = true;
    		boolean cbMarketDataRequestTypeOn = false;
    		boolean cbManualSampleCodeOn = false;
    		boolean tfManualSamplePercentageOn = false;
    		 
    		String currentType = model.getType();    		
    		if (StateCodeTypeDef.MANUAL.equals(currentType)) {
	    		ckReclamationRequiredOn = true;
    		} else if (StateCodeTypeDef.SAMPLE.equals(currentType)) {
	    		cbReclamationCodeOn = false;
    		} else if (StateCodeTypeDef.RECLAMATION.equals(currentType)) {
	    		cbReclamationCodeOn = false;
    		} else if (StateCodeTypeDef.AUTO.equals(currentType)) {
    			ckManualCheckRequiredOn = true;
				cbMarketDataRequestTypeOn = true;
				ckMccCheckedOn = true;
		   		cbManualSampleCodeOn = true;
	    		tfManualSamplePercentageOn = true;
	   		}
    		
    		boolean isReclamationRequired = Boolean.TRUE.equals(model.getProperty(StateCodeEditModel.P_RECLAMATION_REQUIRED));
    		if (!isReclamationRequired) {
	    		cbReclamationCodeOn = false;    			
    		}

   			setEnabled(cbManualSampleCode, cbManualSampleCodeOn);
   			setEnabled(tfManualSamplePercentage, tfManualSamplePercentageOn);
   			setEnabled(ckManualCheckRequired, ckManualCheckRequiredOn);
			setEnabled(ckMccChecked, ckMccCheckedOn);
   			setEnabled(ckReclamationRequired, ckReclamationRequiredOn);
   			setEnabled(cbReclamationCode, cbReclamationCodeOn);
   			setEnabled(cbMarketDataRequestType, cbMarketDataRequestTypeOn);
   			// Allow type change in create new mode only
			setEnabled(cbType, model.isCreateNew());   			
			setEnabled(tfStateCode, model.isCreateNew());   			
    	}

		this.invalidate();
		this.validate();
		this.repaint();
    }
    
    private void setEnabled(SComponent component, boolean isReadOnly) {
		component.setEditable(isReadOnly);
		component.setEnabled(isReadOnly);
		if (component instanceof SCheckBox) {
			((SCheckBox)component).setFocusable(isReadOnly);
		} else if (component instanceof SComboBox) {
			((SComboBox)component).setFocusable(isReadOnly);
		}
    }
    

	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
        if (!(newModel instanceof StateCodeEditModel)) {
            throw new IllegalArgumentException("Model not instance of StateCodeEditModel");
        }
        propertyHandler.setModel(newModel);
        if (getModel() != null)
            getModel().removePropertyChangeListener(listener);
        super.setModel(newModel);
        if (getModel() != null)
            getModel().addPropertyChangeListener(listener);

        fillView();
	}
	
	/**
	 * Updates all language dependend labels
	 */
	@Override
    public void updateLabels() {
		initLabels();
	}
}
