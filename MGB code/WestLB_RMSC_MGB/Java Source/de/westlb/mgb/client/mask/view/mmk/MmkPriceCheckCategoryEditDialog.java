package de.westlb.mgb.client.mask.view.mmk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.westlb.mgb.client.mask.model.mmk.MmkPriceCheckCategoryEditModel;
import de.westlb.mgb.client.mask.view.shared.AbstractView;
import de.westlb.mgb.client.mask.view.shared.def.AddonFieldDef;
import de.westlb.mgb.client.ui.util.VDecimalField;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.SPanel;
import de.westlb_systems.xaf.ui.components.VComboBox;
import de.westlb_systems.xaf.ui.components.VTextField;
import de.westlb_systems.xaf.ui.components.event.ValueChangeEvent;
import de.westlb_systems.xaf.ui.components.event.ValueChangeListener;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.EditDialog;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.util.Debug;

/**
 * @author M. Boerner
 *
 * Dialog to enter a create/edit money market price check category
 */

public class MmkPriceCheckCategoryEditDialog extends AbstractView implements EditDialog, AddonFieldDef {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1908887107992636130L;

	private String RESOURCE_BASE = getResourceBase();
		
	/** gui components */
	private SPanel pBasicData				= new SPanel();
	
	private SLabel lName					= new SLabel();
	private SLabel lCurrency1				= new SLabel();
	private SLabel lCurrency2				= new SLabel();
	private SLabel lMaturityCode			= new SLabel();
	private SLabel lPriceTolerancePercent	= new SLabel();
	private SLabel lPriceToleranceAbsolute	= new SLabel();
	
	private VTextField		tfName		 		= new VTextField();
	private VComboBox	cbCurrency1				= new VComboBox();
	private VComboBox	cbCurrency2				= new VComboBox();
	private VComboBox	cbMaturityCode			= new VComboBox();
	private VDecimalField	tfPriceTolerancePercent		= new VDecimalField();
	private VDecimalField	tfPriceToleranceAbsolute	= new VDecimalField();
	
	/** Property handler to synchronize model and view */
	private PropertyHandler propertyHandler = new PropertyHandler();	

	/** Listener fuer alle Events */
	private Listener listener = new Listener();

	/**
	 * Listener fuer alle Events
	 */
	private class Listener implements ActionListener, ValueChangeListener {
        /**
         * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
        }
        /* (Kein Javadoc)
         * @see de.westlb_systems.xaf.ui.components.event.ValueChangeListener#valueChanged(de.westlb_systems.xaf.ui.components.event.ValueChangeEvent)
         */
        @Override
        public void valueChanged(ValueChangeEvent event) {
            if (event.getSource() == cbCurrency1 || 
            	event.getSource() == cbCurrency2 || 
            	event.getSource() == cbMaturityCode) 
            {
            	if (getMmkPriceCheckCategoryEditModel() != null) {
            		getMmkPriceCheckCategoryEditModel().updateName();
            	}
            }

        }

	}
	
	/**
	 * Constructor for CheckStateView.
	 */
	public MmkPriceCheckCategoryEditDialog() {
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
	public MmkPriceCheckCategoryEditDialog(Model model) {
		this();
		setModel(model);
	}


    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (getMmkPriceCheckCategoryEditModel() == null) {
            return;
        }
        propertyHandler.syncFields();
    }
    
    /**
	 * Return casted model
	 */
	public MmkPriceCheckCategoryEditModel getMmkPriceCheckCategoryEditModel() {
		return getModel() instanceof MmkPriceCheckCategoryEditModel ? (MmkPriceCheckCategoryEditModel)getModel() : null;
	}


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(MmkPriceCheckCategoryEditModel.P_NAME,						tfName);	
		propertyHandler.add(MmkPriceCheckCategoryEditModel.P_CURRENCY1_CB_MODEL,		cbCurrency1);	
		propertyHandler.add(MmkPriceCheckCategoryEditModel.P_CURRENCY1,					cbCurrency1);	
		propertyHandler.add(MmkPriceCheckCategoryEditModel.P_CURRENCY2_CB_MODEL,		cbCurrency2);	
		propertyHandler.add(MmkPriceCheckCategoryEditModel.P_CURRENCY2,					cbCurrency2);	
		propertyHandler.add(MmkPriceCheckCategoryEditModel.P_MATURITY_CODE_CB_MODEL,	cbMaturityCode);	
		propertyHandler.add(MmkPriceCheckCategoryEditModel.P_MATURITY_CODE,				cbMaturityCode);	
		propertyHandler.add(MmkPriceCheckCategoryEditModel.P_PRICE_TOLERANCE_PERCENT,			tfPriceTolerancePercent);	
		propertyHandler.add(MmkPriceCheckCategoryEditModel.P_PRICE_TOLERANCE_ABSOLUTE,			tfPriceToleranceAbsolute);	
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
    	pBasicData.setTitle(getResourceString(RESOURCE_BASE + "T_BASICDATA"));

    	lName.setText					(getResourceString(RESOURCE_BASE + "L_NAME"));    	
    	lPriceTolerancePercent.setText			(getResourceString(RESOURCE_BASE + "L_PRICE_TOLERANCE_PERCENT"));
    	lPriceToleranceAbsolute.setText			(getResourceString(RESOURCE_BASE + "L_PRICE_TOLERANCE_ABSOLUTE"));
    	lCurrency1.setText				(getResourceString(RESOURCE_BASE + "L_CURRENCY1"));
    	lCurrency2.setText				(getResourceString(RESOURCE_BASE + "L_CURRENCY2"));
		lMaturityCode.setText			(getResourceString(RESOURCE_BASE + "L_MATURITY_CODE"));
    }
    
    /**
     * Method initLayout.
     */
    private void initLayout() {
		int l, c;
		pBasicData.setBorderPainted(true);
		pBasicData.setMargin(insets0088);
		
		SPanel panel = new SPanel();
		pBasicData.add(panel,			new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, insets0000, 0, 0));

		l = 0; c = 0; // Column 0
	 	panel.add(lName,				new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lCurrency1,			new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lCurrency2,			new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lMaturityCode,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
		panel.add(lPriceTolerancePercent,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
		panel.add(lPriceToleranceAbsolute,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	

		l = 0; c = 1; // Column 0
	 	panel.add(tfName,				new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 		 	
	 	panel.add(cbCurrency1,			new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));
	 	panel.add(cbCurrency2,			new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));
	 	panel.add(cbMaturityCode,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));
		panel.add(tfPriceTolerancePercent,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));
		panel.add(tfPriceToleranceAbsolute,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));

		this.add(pBasicData,    		new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets0000, 0, 0));
    }

    /**
     * Method initComponents.
     */
    private void initComponents() {
    	tfName.setEnabled(false);
    	
    	tfPriceTolerancePercent.setMandatory(true);
    	cbCurrency1.setMandatory(true);
		cbMaturityCode.setMandatory(true);
		
		cbCurrency1.addValueChangeListener(listener);
		cbCurrency2.addValueChangeListener(listener);
		cbMaturityCode.addValueChangeListener(listener);
    	
    	tfPriceTolerancePercent.setDecimalPlaces			(PRICE_TOLERANCE_MAX_DECIMAL_PLACES);
    	tfPriceTolerancePercent.setMaximumNumberOfDigits	(PRICE_TOLERANCE_MAX_DIGITS);
    	tfPriceTolerancePercent.setGrenzen					(PRICE_TOLERANCE_MIN_VALUE, PRICE_TOLERANCE_MAX_VALUE);
    	tfPriceTolerancePercent.setUseClipping(true);

    	tfPriceToleranceAbsolute.setDecimalPlaces			(PRICE_TOLERANCE_MAX_DECIMAL_PLACES);
    	tfPriceToleranceAbsolute.setMaximumNumberOfDigits	(PRICE_TOLERANCE_MAX_DIGITS);
    	tfPriceToleranceAbsolute.setGrenzen					(PRICE_TOLERANCE_MIN_VALUE, PRICE_TOLERANCE_MAX_VALUE);
    	tfPriceToleranceAbsolute.setUseClipping(true);
}

	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof MmkPriceCheckCategoryEditModel)) {
	        throw new IllegalArgumentException("Model not instance of MmkPriceCheckCategoryEditModel");
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
