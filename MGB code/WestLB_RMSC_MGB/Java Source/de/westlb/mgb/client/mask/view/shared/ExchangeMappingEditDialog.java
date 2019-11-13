package de.westlb.mgb.client.mask.view.shared;

import de.westlb.mgb.client.mask.model.shared.ExchangeMappingEditModel;
import de.westlb.mgb.client.mask.view.shared.def.ExchangeMappingFieldDef;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SComboBox;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.SPanel;
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
public class ExchangeMappingEditDialog extends AbstractView implements EditDialog {
	/**
     * 
     */
    private static final long serialVersionUID = 8036232146396654240L;

    private String RESOURCE_BASE = getResourceBase();
		
	/** gui components */
	private SPanel pBasicData						= new SPanel();
	
	private SLabel lCurrency						= new SLabel();
	private SLabel lIsin							= new SLabel();
	private SLabel lIsinCountryPrefix				= new SLabel();
	private SLabel lSourceSystem					= new SLabel();	
	private SLabel lSourceSystemExchangeId			= new SLabel();
	
	private VTextField	tfIsin						= new VTextField(ExchangeMappingFieldDef.ISIN_MAX_LENGTH,			DEF_COL_20);
	private VTextField	tfIsinCountryPrefix			= new VTextField(ExchangeMappingFieldDef.ISIN_PREFIX_MAX_LENGTH,	DEF_COL_20);
	private SComboBox	cbSourceSystem				= new SComboBox();
	private VTextField	tfCurrency					= new VTextField(ExchangeMappingFieldDef.CURRENCY_MAX_LENGTH,		DEF_COL_20);
	private VTextField	tfSourceSystemExchangeId	= new VTextField(ExchangeMappingFieldDef.SOURCE_SYSTEM_EXCHANGE_MAX_LENGTH,DEF_COL_20);
	
	/** Property handler to synchronize model and view */
	private PropertyHandler propertyHandler = new PropertyHandler();	
	
	/**
	 * Constructor for CheckStateView.
	 */
	public ExchangeMappingEditDialog() {
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
	public ExchangeMappingEditDialog(Model model) {
		this();
		setModel(model);
	}


    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (getExchangeMappingEditModel() == null) {
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
	public ExchangeMappingEditModel getExchangeMappingEditModel() {
		return getModel() instanceof ExchangeMappingEditModel ? (ExchangeMappingEditModel)getModel() : null;
	}


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(ExchangeMappingEditModel.P_CURRENCY,					tfCurrency);	
		propertyHandler.add(ExchangeMappingEditModel.P_ISIN,						tfIsin);	
		propertyHandler.add(ExchangeMappingEditModel.P_ISIN_COUNTRY_PREFIX,			tfIsinCountryPrefix);	
		propertyHandler.add(ExchangeMappingEditModel.P_SOURCE_SYSTEM_CB_MODEL,		cbSourceSystem);			
		propertyHandler.add(ExchangeMappingEditModel.P_SOURCE_SYSTEM,				cbSourceSystem);	
		propertyHandler.add(ExchangeMappingEditModel.P_SOURCE_SYSTEM_EXCHANGE_ID,	tfSourceSystemExchangeId);			
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
    	pBasicData.setTitle(getResourceString(RESOURCE_BASE + "T_BASICDATA"));

    	lCurrency.setText					(getResourceString(RESOURCE_BASE + "L_CURRENCY"));
    	lIsin.setText						(getResourceString(RESOURCE_BASE + "L_ISIN"));
    	lIsinCountryPrefix.setText			(getResourceString(RESOURCE_BASE + "L_ISIN_COUNTRY_PREFIX"));
    	lSourceSystem.setText				(getResourceString(RESOURCE_BASE + "L_SOURCE_SYSTEM"));
    	lSourceSystemExchangeId.setText		(getResourceString(RESOURCE_BASE + "L_SOURCE_SYSTEM_EXCHANGE_ID"));
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
	 	panel.add(lIsin,					new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lIsinCountryPrefix,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lCurrency,				new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 		 	
	 	panel.add(lSourceSystem,			new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lSourceSystemExchangeId,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	

		l = 0; c = 1; // Column 0
	 	panel.add(tfIsin,					new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 		 	
	 	panel.add(tfIsinCountryPrefix,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));
	 	panel.add(tfCurrency,				new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(cbSourceSystem,			new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(tfSourceSystemExchangeId,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	

		this.add(pBasicData,    			new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets0000, 0, 0));
    }

    /**
     * Method initComponents.
     */
    private void initComponents() {
    }

	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof ExchangeMappingEditModel)) {
	        throw new IllegalArgumentException("Model not instance of ExchangeMappingEditModel");
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
