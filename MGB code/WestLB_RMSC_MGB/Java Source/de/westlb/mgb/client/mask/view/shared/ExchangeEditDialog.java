package de.westlb.mgb.client.mask.view.shared;

import de.westlb.mgb.client.mask.model.shared.ExchangeEditModel;
import de.westlb.mgb.client.mask.view.shared.def.ExchangeFieldDef;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
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
public class ExchangeEditDialog extends AbstractView implements EditDialog {
	/**
     * 
     */
    private static final long serialVersionUID = -3500499407566313626L;

    private String RESOURCE_BASE = getResourceBase();
		
	/** gui components */
	private SPanel pBasicData						= new SPanel();
	
	private SLabel lBloombergId						= new SLabel();
	private SLabel lReutersId						= new SLabel();
	
	private VTextField	tfBloombergId				= new VTextField(ExchangeFieldDef.BLOOMBERG_ID_MAX_LENGTH, DEF_COL_20);
	private VTextField	tfReutersId					= new VTextField(ExchangeFieldDef.REUTERS_ID_MAX_LENGTH, DEF_COL_20);
	
	/** Property handler to synchronize model and view */
	private PropertyHandler propertyHandler = new PropertyHandler();	

	/**
	 * Constructor for CheckStateView.
	 */
	public ExchangeEditDialog() {
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
	public ExchangeEditDialog(Model model) {
		this();
		setModel(model);
	}


    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (getExchangeEditModel() == null) {
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
	public ExchangeEditModel getExchangeEditModel() {
		return getModel() instanceof ExchangeEditModel ? (ExchangeEditModel)getModel() : null;
	}


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(ExchangeEditModel.P_BLOOMBERG_ID,		tfBloombergId);	
		propertyHandler.add(ExchangeEditModel.P_REUTERS_ID,			tfReutersId);	
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
    	pBasicData.setTitle(getResourceString(RESOURCE_BASE + "T_BASICDATA"));

    	lBloombergId.setText					(getResourceString(RESOURCE_BASE + "L_BLOOMBERG_ID"));
    	lReutersId.setText						(getResourceString(RESOURCE_BASE + "L_REUTERS_ID"));
    }
    
    /**
     * Method initLayout.
     */
    private void initLayout() {
		int l, c;
		pBasicData.setBorderPainted(true);
		pBasicData.setMargin(insets0088);

		SPanel panel = new SPanel();
		pBasicData.add(panel,		new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, insets0000, 0, 0));

		l = 0; c = 0; // Column 0
	 	panel.add(lBloombergId,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lReutersId,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	

		l = 0; c = 1; // Column 0
	 	panel.add(tfBloombergId,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 		 	
	 	panel.add(tfReutersId,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));

		this.add(pBasicData,    	new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets0000, 0, 0));
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
	    if (!(newModel instanceof ExchangeEditModel)) {
	        throw new IllegalArgumentException("Model not instance of ExchangeEditModel");
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
