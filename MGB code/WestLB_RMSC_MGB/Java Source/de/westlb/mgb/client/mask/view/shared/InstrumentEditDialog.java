package de.westlb.mgb.client.mask.view.shared;

import de.westlb.mgb.client.mask.model.shared.InstrumentEditModel;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SComboBox;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.SPanel;
import de.westlb_systems.xaf.swing.STextField;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.EditDialog;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.util.Debug;

/**
 * @author WSY4148
 *
 * Dialog to enter a new manual state of a trade.
 */
public class InstrumentEditDialog extends AbstractView implements EditDialog {
	/**
     * 
     */
    private static final long serialVersionUID = -3610954315564374216L;

    private String RESOURCE_BASE = getResourceBase();

	/** gui components */
	
	/** Selected price check category info panel */
	private SLabel lPriceToleranceLow			= new SLabel();
	private SLabel lPriceToleranceHigh			= new SLabel();
	private SLabel lTimeTolerance				= new SLabel();	
	private STextField tfPriceToleranceLow 	= new STextField(DEF_COL_08);
	private STextField tfPriceToleranceHigh	= new STextField(DEF_COL_08);
	private STextField tfTimeTolerance			= new STextField(DEF_COL_08);

	/** instrument info panel */
	private SPanel pInstrument					= new SPanel();
	private SLabel lIsin						= new SLabel();
	private SLabel lInstrumentName				= new SLabel();
	private SLabel lPriceCheckCategory 			= new SLabel();
	private STextField tfIsin					= new STextField();
	private STextField tfInstrumentName			= new STextField();
	private SComboBox	cbPriceCheckCategory	= new SComboBox();
	
	/** Property handler to synchronize model and view */
	private PropertyHandler propertyHandler = new PropertyHandler();	

	
	/**
	 * Constructor for CheckStateView.
	 */
	public InstrumentEditDialog() {
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
	public InstrumentEditDialog(Model model) {
		this();
		setModel(model);
	}


    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (getInstrumentEditModel() == null) {
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
	public InstrumentEditModel getInstrumentEditModel() {
		return getModel() instanceof InstrumentEditModel ? (InstrumentEditModel)getModel() : null;
	}


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(InstrumentEditModel.P_INSTR_ISIN,					tfIsin);	
		propertyHandler.add(InstrumentEditModel.P_INSTR_NAME,					tfInstrumentName);	
		propertyHandler.add(InstrumentEditModel.P_INSTR_PRICE_CHECK_CB_MODEL, 	cbPriceCheckCategory);
		propertyHandler.add(InstrumentEditModel.P_INSTR_PRICE_CHECK_CATEGORY, 	cbPriceCheckCategory);
		propertyHandler.add(InstrumentEditModel.P_PRICE_TIME_TOLERANCE,			tfTimeTolerance);
		propertyHandler.add(InstrumentEditModel.P_PRICE_TOLERANCE_HIGH,			tfPriceToleranceHigh);
		propertyHandler.add(InstrumentEditModel.P_PRICE_TOLERANCE_LOW,			tfPriceToleranceLow);		
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
    	pInstrument.setTitle(getResourceString(RESOURCE_BASE + "T_INSTRUMENT"));

    	lIsin.setText					(getResourceString(RESOURCE_BASE + "L_ISIN"));
    	lInstrumentName.setText			(getResourceString(RESOURCE_BASE + "L_INSTRUMENT_NAME"));
    	lPriceCheckCategory.setText		(getResourceString(RESOURCE_BASE + "L_PRICE_CHECK_CATEGORY"));
    	lPriceToleranceHigh.setText		(getResourceString(RESOURCE_BASE + "L_PRICE_TOLERANCE_HIGH"));
    	lPriceToleranceLow.setText		(getResourceString(RESOURCE_BASE + "L_PRICE_TOLERANCE_LOW"));
    	lTimeTolerance.setText			(getResourceString(RESOURCE_BASE + "L_TIME_TOLERANCE"));
    }
    
    /**
     * Method initLayout.
     */
    private void initLayout() {
		int l, c;
		pInstrument.setBorderPainted(true);
		pInstrument.setMargin(insets0088);

		SPanel panel = new SPanel();
		pInstrument.add(panel,			new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, insets0000, 0, 0));

		l = 0; c = 0; // Column 0
	 	panel.add(lIsin,				new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, WEST, NONE, insets4800, 0, 0));	 	
	 	panel.add(lInstrumentName,		new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, WEST, NONE, insets4800, 0, 0));	 	
	 	panel.add(lPriceCheckCategory,	new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, WEST, NONE, insets4800, 0, 0));	 	

		l = 0; c = 1; // Column 0
	 	panel.add(tfIsin,				new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, WEST, HORIZ, insets4800, 0, 0));	 		 	
	 	panel.add(tfInstrumentName,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, WEST, HORIZ, insets4800, 0, 0));	 		 	
	 	panel.add(cbPriceCheckCategory,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, WEST, HORIZ, insets4800, 0, 0));

		this.add(pInstrument,    		new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.0, NORTH, HORIZ, insets0000, 0, 0));
    }

    /**
     * Method initComponents.
     */
    private void initComponents() {
    	tfIsin.setEnabled(false);
    	tfIsin.setFocusable(false);
    	tfIsin.setEditable(false);
    	tfInstrumentName.setEnabled(false);
    	tfInstrumentName.setFocusable(false);
    	tfInstrumentName.setEditable(false);
    	cbPriceCheckCategory.setMandatory(true);
    }

	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof InstrumentEditModel)) {
	        throw new IllegalArgumentException("Model not instance of InstrumentEditModel");
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
