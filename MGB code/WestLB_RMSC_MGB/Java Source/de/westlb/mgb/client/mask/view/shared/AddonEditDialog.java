package de.westlb.mgb.client.mask.view.shared;

import java.awt.Dimension;

import de.westlb.mgb.client.mask.model.shared.AddonEditModel;
import de.westlb.mgb.client.mask.view.shared.def.AddonFieldDef;
import de.westlb.mgb.client.ui.util.VDecimalField;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
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
public class AddonEditDialog extends AbstractView implements EditDialog, AddonFieldDef {
	/**
     * 
     */
    private static final long serialVersionUID = -1400911601978562496L;

    private String RESOURCE_BASE = getResourceBase();
		
	/** gui components */
	private SPanel pBasicData				= new SPanel();
	
	private SLabel lPriceTolerance			= new SLabel();
	private SLabel lTimeTolerance			= new SLabel();
	private SLabel lComment				= new SLabel();
	private SLabel lCondition				= new SLabel();
	
	private VTextArea		taComment		 = new VTextArea();
	private VTextField 	tfCondition		 = new VTextField();
	private VDecimalField	tfPriceTolerance = new VDecimalField();
	private VIntegerField	tfTimeTolerance	 = new VIntegerField();
	
	/** Property handler to synchronize model and view */
	private PropertyHandler propertyHandler = new PropertyHandler();	

	private static final Dimension commentSize = new Dimension(250,100);
	

	/**
	 * Constructor for CheckStateView.
	 */
	public AddonEditDialog() {
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
	public AddonEditDialog(Model model) {
		this();
		setModel(model);
	}


    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (getAddonEditModel() == null) {
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
	public AddonEditModel getAddonEditModel() {
		return getModel() instanceof AddonEditModel ? (AddonEditModel)getModel() : null;
	}


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(AddonEditModel.P_COMMENT,						taComment);	
		propertyHandler.add(AddonEditModel.P_CONDITION,						tfCondition);	
		propertyHandler.add(AddonEditModel.P_PRICE_TOLERANCE_IN_PERCENT,	tfPriceTolerance);	
		propertyHandler.add(AddonEditModel.P_TIME_TOLERANCE_IN_MINUTES,		tfTimeTolerance);	
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
    	pBasicData.setTitle(getResourceString(RESOURCE_BASE + "T_BASICDATA"));

    	lPriceTolerance.setText	(getResourceString(RESOURCE_BASE + "L_priceTolerancePercent"));
    	lTimeTolerance.setText	(getResourceString(RESOURCE_BASE + "L_timeToleranceMinutes"));
    	lCondition.setText		(getResourceString(RESOURCE_BASE + "L_condition"));
    	lComment.setText		(getResourceString(RESOURCE_BASE + "L_comment"));    	
    }
    
    /**
     * Method initLayout.
     */
    private void initLayout() {
		int l, c;
		pBasicData.setBorderPainted(true);
		pBasicData.setMargin(insets0088);
		
		taComment.setMinimumSize(commentSize);
		taComment.setPreferredSize(commentSize);


		SPanel panel = new SPanel();
		pBasicData.add(panel,			new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, insets0000, 0, 0));

		l = 0; c = 0; // Column 0
	 	panel.add(lCondition,			new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lTimeTolerance,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lPriceTolerance,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lComment,				new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	

		l = 0; c = 1; // Column 0
	 	panel.add(tfCondition,			new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 		 	
	 	panel.add(tfTimeTolerance,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));
	 	panel.add(tfPriceTolerance,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));
	 	panel.add(taComment,			new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));

		this.add(pBasicData,    		new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets0000, 0, 0));
    }

    /**
     * Method initComponents.
     */
    private void initComponents() {
    	tfCondition.setMandatory(true);
    	
    	tfPriceTolerance.setDecimalPlaces			(PRICE_TOLERANCE_MAX_DECIMAL_PLACES);
    	tfPriceTolerance.setMaximumNumberOfDigits	(PRICE_TOLERANCE_MAX_DIGITS);
    	tfPriceTolerance.setGrenzen					(PRICE_TOLERANCE_MIN_VALUE, PRICE_TOLERANCE_MAX_VALUE);
    	tfPriceTolerance.setUseClipping(true);
    	
    	tfCondition.setMaxLength(CONDITION_MAX_LENGTH);
    	tfTimeTolerance.setMaxLength(TIME_TOLERANCE_MAX_DIGITS);
    }

	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof AddonEditModel)) {
	        throw new IllegalArgumentException("Model not instance of AddonEditModel");
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
