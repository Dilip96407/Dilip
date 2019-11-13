package de.westlb.mgb.client.mask.view.shared;

import de.westlb.mgb.client.mask.model.shared.EmployeeEditModel;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SCheckBox;
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
public class EmployeeEditDialog extends AbstractView implements EditDialog {
	/**
     * 
     */
    private static final long serialVersionUID = 6686844488693210390L;
    private String RESOURCE_BASE = getResourceBase();
	private static final int MAX_LENGTH_FIRST_NAME = 255;
	private static final int MAX_LENGTH_LAST_NAME = 255;
	private static final int MAX_LENGTH_PHONE = 255;
	private static final int MAX_LENGTH_EMAIL = 255;
	private static final int MAX_LENGTH_NT_ID = 10;

	/** gui components */
	private SPanel pBasicData	= new SPanel();
	private SLabel lTrader	= new SLabel();
	private SLabel lEMail		= new SLabel();
	private SLabel lFirstName	= new SLabel();
	private SLabel lLastName	= new SLabel();
	private SLabel lNtId		= new SLabel();
	private SLabel lTelefon	= new SLabel();
	
	private SCheckBox	ckTrader	= new SCheckBox();
	private VTextField	tfEMail			= new VTextField(MAX_LENGTH_EMAIL,		DEF_COL_20);
	private VTextField	tfFirstName		= new VTextField(MAX_LENGTH_FIRST_NAME,	DEF_COL_20);
	private VTextField	tfLastName		= new VTextField(MAX_LENGTH_LAST_NAME,	DEF_COL_20);
	private VTextField	tfNtId			= new VTextField(MAX_LENGTH_NT_ID,		DEF_COL_10);
	private VTextField	tfTelefon		= new VTextField(MAX_LENGTH_PHONE,		DEF_COL_20);
	
	/** Property handler to synchronize model and view */
	private PropertyHandler propertyHandler = new PropertyHandler();	

	/**
	 * Constructor for CheckStateView.
	 */
	public EmployeeEditDialog() {
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
	public EmployeeEditDialog(Model model) {
		this();
		setModel(model);
	}


    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (getEditEmployeeModel() == null) {
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
	public EmployeeEditModel getEditEmployeeModel() {
		return getModel() instanceof EmployeeEditModel ? (EmployeeEditModel)getModel() : null;
	}


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(EmployeeEditModel.P_EMAIL,			tfEMail);	
		propertyHandler.add(EmployeeEditModel.P_FIRST_NAME, 	tfFirstName);
		propertyHandler.add(EmployeeEditModel.P_LAST_NAME,		tfLastName);
		propertyHandler.add(EmployeeEditModel.P_NT_ID,			tfNtId);
		propertyHandler.add(EmployeeEditModel.P_TELEFON,		tfTelefon);
		propertyHandler.add(EmployeeEditModel.P_TRADER,			ckTrader);
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
    	pBasicData.setTitle(getResourceString(RESOURCE_BASE + "T_BASICDATA"));

    	lEMail.setText			(getResourceString(RESOURCE_BASE + "L_EMAIL"));
    	lFirstName.setText		(getResourceString(RESOURCE_BASE + "L_FIRST_NAME"));
    	lLastName.setText		(getResourceString(RESOURCE_BASE + "L_LAST_NAME"));
    	lNtId.setText			(getResourceString(RESOURCE_BASE + "L_NTID"));
       	lTelefon.setText		(getResourceString(RESOURCE_BASE + "L_TELEFON"));
       	lTrader.setText			(getResourceString(RESOURCE_BASE + "L_TRADER"));
    }
    
    /**
     * Method initLayout.
     */
    private void initLayout() {
		int l, c;
		pBasicData.setBorderPainted(true);
		pBasicData.setMargin(insets0088);

		SPanel panel = new SPanel();
		pBasicData.add(panel,	new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, insets0000, 0, 0));

		l = 0; c = 0; // Column 0
	 	panel.add(lLastName,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lFirstName,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lEMail,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lNtId,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lTelefon,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lTrader,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	

		l = 0; c = 1; // Column 0
	 	panel.add(tfLastName,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 		 	
	 	panel.add(tfFirstName,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));
	 	panel.add(tfEMail,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(tfNtId,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(tfTelefon,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(ckTrader,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	

		this.add(pBasicData,    new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets0000, 0, 0));
    }

    /**
     * Method initComponents.
     */
    private void initComponents() {
    	tfLastName.setMandatory(true);
    	tfFirstName.setMandatory(true);
    	tfEMail.setMandatory(true);
    	tfNtId.setMandatory(true);
    	ckTrader.setSelected(true);
    }

	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof EmployeeEditModel)) {
	        throw new IllegalArgumentException("Model not instance of EditEmployeeModel");
	    }
	    // In general a new user will have a trader role 
	    if (newModel.getProperty(EmployeeEditModel.P_NT_ID) == null) {
	    	newModel.setProperty(EmployeeEditModel.P_TRADER, Boolean.TRUE);
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
