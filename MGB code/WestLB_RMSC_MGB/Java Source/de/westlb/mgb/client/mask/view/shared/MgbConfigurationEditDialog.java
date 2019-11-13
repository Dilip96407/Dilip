package de.westlb.mgb.client.mask.view.shared;

import java.awt.Dimension;

import de.westlb.mgb.client.mask.model.shared.MgbConfigurationEditModel;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SCheckBox;
import de.westlb_systems.xaf.swing.SComboBox;
import de.westlb_systems.xaf.swing.SComponent;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.SPanel;
import de.westlb_systems.xaf.ui.components.VTextArea;
import de.westlb_systems.xaf.ui.components.VTextField;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.EditDialog;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.util.Debug;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MgbConfigurationEditDialog extends AbstractView implements EditDialog {
	/**
     * 
     */
    private static final long serialVersionUID = 516744481680048976L;
    private static final Dimension VALUE_DIM = new Dimension(350,300);
	private String RESOURCE_BASE = getResourceBase();
	private static final int MAX_LENGTH_KEY = 100;
	private static final int MAX_LENGTH_VALUE = 4000;
		
	/** gui components */
	private SPanel pBasicData				= new SPanel();
	
	private SLabel lKey					= new SLabel();
	private SLabel lValue				= new SLabel();
	
	private VTextField		tfKey		 			= new VTextField(MAX_LENGTH_KEY,	DEF_COL_20);
	private VTextArea		tfValue		 			= new VTextArea(MAX_LENGTH_VALUE);
	
	/** Property handler to synchronize model and view */
	private PropertyHandler propertyHandler = new PropertyHandler();	

	
	/**
	 * Constructor for CheckStateView.
	 */
	public MgbConfigurationEditDialog() {
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
	public MgbConfigurationEditDialog(Model model) {
		this();
		setModel(model);
	}


    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (getMgbConfigurationEditModel() == null) {
            return;
        }
       	setEnabled(tfKey, getMgbConfigurationEditModel().createNew());
        
        propertyHandler.syncFields();
    }
    
    /**
	 * Return casted model
	 * 
	 * Creation date: (8/31/00 10:43:03 AM)
	 * f
	 */
	public MgbConfigurationEditModel getMgbConfigurationEditModel() {
		return getModel() instanceof MgbConfigurationEditModel ? (MgbConfigurationEditModel)getModel() : null;
	}


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(MgbConfigurationEditModel.P_KEY,								tfKey);	
		propertyHandler.add(MgbConfigurationEditModel.P_VALUE,								tfValue);	
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
    	pBasicData.setTitle(getResourceString(RESOURCE_BASE + "T_BASICDATA"));

    	lKey.setText					(getResourceString(RESOURCE_BASE + "L_key"));    	
    	lValue.setText					(getResourceString(RESOURCE_BASE + "L_value"));    	
    }
    
    /**
     * Method initLayout.
     */
    private void initLayout() {
		int l, c;
		
		tfValue.setMinimumSize(VALUE_DIM);
		tfValue.setPreferredSize(VALUE_DIM);
		
		pBasicData.setBorderPainted(true);
		pBasicData.setMargin(insets0088);
		
		SPanel panel = new SPanel();
		pBasicData.add(panel,			new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, insets0000, 0, 0));

		l = 0; c = 0; // Column 0
	 	panel.add(lKey,					new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lValue,				new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	

		l = 0; c = 1; // Column 1
	 	panel.add(tfKey,				new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 		 	
	 	panel.add(tfValue,				new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));

		this.add(pBasicData,    		new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets0000, 0, 0));
    }

    /**
     * Method initComponents.
     */
    private void initComponents() {
    	tfKey.setMandatory(true);
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
	    if (!(newModel instanceof MgbConfigurationEditModel)) {
	        throw new IllegalArgumentException("Model not instance of PriceCheckCategoryEditModel");
	    }
	    propertyHandler.setModel(newModel);
	    super.setModel(newModel);
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
