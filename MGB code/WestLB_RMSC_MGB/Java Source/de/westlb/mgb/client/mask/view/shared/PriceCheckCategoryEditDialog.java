package de.westlb.mgb.client.mask.view.shared;

import de.westlb.mgb.client.mask.model.shared.PriceCheckCategoryEditModel;
import de.westlb.mgb.client.mask.view.shared.def.AddonFieldDef;
import de.westlb.mgb.client.ui.util.VDecimalField;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SComboBox;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.SPanel;
import de.westlb_systems.xaf.ui.components.VIntegerField;
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
public class PriceCheckCategoryEditDialog extends AbstractView implements EditDialog, AddonFieldDef {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6680990037293077376L;

	private String RESOURCE_BASE = getResourceBase();
		
	/** gui components */
	private SPanel pBasicData				= new SPanel();
	
	private SLabel lName					= new SLabel();
	private SLabel lPriceToleranceAbsolute	= new SLabel();
	private SLabel lPriceTolerancePercent	= new SLabel();
	private SLabel lTimeTolerance			= new SLabel();
	private SLabel lManualSampleCode		= new SLabel();
	private SLabel lManualSamplePercentage	= new SLabel();
	
	private VTextField		tfName		 			= new VTextField();
	private VDecimalField	tfPriceToleranceAbsolute= new VDecimalField();
	private VDecimalField	tfPriceTolerancePercent	= new VDecimalField();
	private VIntegerField	tfTimeTolerance			= new VIntegerField();
	private SComboBox 		cbManualSampleCode		= new SComboBox();
	private VIntegerField 	tfManualSamplePercentage	= new VIntegerField();
	
	/** Property handler to synchronize model and view */
	private PropertyHandler propertyHandler = new PropertyHandler();	

	
	/**
	 * Constructor for CheckStateView.
	 */
	public PriceCheckCategoryEditDialog() {
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
	public PriceCheckCategoryEditDialog(Model model) {
		this();
		setModel(model);
	}


    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (getPriceCheckCategoryEditModel() == null) {
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
	public PriceCheckCategoryEditModel getPriceCheckCategoryEditModel() {
		return getModel() instanceof PriceCheckCategoryEditModel ? (PriceCheckCategoryEditModel)getModel() : null;
	}


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(PriceCheckCategoryEditModel.P_NAME,								tfName);	
		propertyHandler.add(PriceCheckCategoryEditModel.P_PRICE_TOLERANCE_PERCENT,			tfPriceTolerancePercent);	
		propertyHandler.add(PriceCheckCategoryEditModel.P_PRICE_TOLERANCE_ABSOLUTE,			tfPriceToleranceAbsolute);	
		propertyHandler.add(PriceCheckCategoryEditModel.P_TIME_TOLERANCE_MINUTES,			tfTimeTolerance);	
		propertyHandler.add(PriceCheckCategoryEditModel.P_MANUAL_SAMPLE_CODE_CB_MODEL,		cbManualSampleCode);
		propertyHandler.add(PriceCheckCategoryEditModel.P_MANUAL_SAMPLE_CODE,				cbManualSampleCode);
		propertyHandler.add(PriceCheckCategoryEditModel.P_MANUAL_SAMPLE_PERCENTAGE,			tfManualSamplePercentage);
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
    	pBasicData.setTitle(getResourceString(RESOURCE_BASE + "T_BASICDATA"));

    	lName.setText					(getResourceString(RESOURCE_BASE + "L_NAME"));    	
    	lPriceToleranceAbsolute.setText	(getResourceString(RESOURCE_BASE + "L_PRICE_TOLERANCE_ABSOLUTE"));
    	lPriceTolerancePercent.setText	(getResourceString(RESOURCE_BASE + "L_PRICE_TOLERANCE_PERCENT"));
     	lTimeTolerance.setText			(getResourceString(RESOURCE_BASE + "L_TIME_TOLERANCE_MINUTES"));
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
		
		SPanel panel = new SPanel();
		pBasicData.add(panel,				new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, insets0000, 0, 0));

		l = 0; c = 0; // Column 0
	 	panel.add(lName,					new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lTimeTolerance,			new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lPriceToleranceAbsolute,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lPriceTolerancePercent,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lManualSampleCode,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lManualSamplePercentage,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	

		l = 0; c = 1; // Column 0
	 	panel.add(tfName,					new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 		 	
	 	panel.add(tfTimeTolerance,			new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));
	 	panel.add(tfPriceToleranceAbsolute,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));
	 	panel.add(tfPriceTolerancePercent,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));
	 	panel.add(cbManualSampleCode,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(tfManualSamplePercentage,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	

		this.add(pBasicData,    			new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets0000, 0, 0));
    }

    /**
     * Method initComponents.
     */
    private void initComponents() {
    	tfName.setMandatory(true);
    	tfPriceToleranceAbsolute.setMandatory(true);
    	tfPriceTolerancePercent.setMandatory(true);
    	tfTimeTolerance.setMandatory(true);
    	
    	tfPriceToleranceAbsolute.setDecimalPlaces			(PRICE_TOLERANCE_MAX_DECIMAL_PLACES);
    	tfPriceToleranceAbsolute.setMaximumNumberOfDigits	(PRICE_TOLERANCE_MAX_DIGITS);
    	tfPriceToleranceAbsolute.setGrenzen				(PRICE_TOLERANCE_MIN_VALUE, PRICE_TOLERANCE_MAX_VALUE);
    	tfPriceToleranceAbsolute.setUseClipping(true);

    	tfPriceTolerancePercent.setDecimalPlaces			(PRICE_TOLERANCE_MAX_DECIMAL_PLACES);
    	tfPriceTolerancePercent.setMaximumNumberOfDigits	(PRICE_TOLERANCE_MAX_DIGITS);
    	tfPriceTolerancePercent.setGrenzen				(PRICE_TOLERANCE_MIN_VALUE, PRICE_TOLERANCE_MAX_VALUE);
    	tfPriceTolerancePercent.setUseClipping(true);
    	    	
    	tfName.setMaxLength(CONDITION_MAX_LENGTH);
    	tfTimeTolerance.setMaxLength(TIME_TOLERANCE_MAX_DIGITS);
    }

	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof PriceCheckCategoryEditModel)) {
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
