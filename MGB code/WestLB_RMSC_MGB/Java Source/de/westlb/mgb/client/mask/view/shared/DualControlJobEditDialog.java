package de.westlb.mgb.client.mask.view.shared;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;

import de.westlb.mgb.client.mask.model.shared.DualControlJobEditModel;
import de.westlb.mgb.client.ui.util.DateFormat;
import de.westlb.mgb.client.ui.util.VDateField;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.SPanel;
import de.westlb_systems.xaf.swing.STable;
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
public class DualControlJobEditDialog extends AbstractView implements EditDialog {
	/**
     * 
     */
    private static final long serialVersionUID = -7625976981514985901L;

    private String RESOURCE_BASE = getResourceBase();

	/** gui components */
	private SPanel pBasicData			= new SPanel();
	private SLabel lEntityType			= new SLabel();
	private SLabel lOperation			= new SLabel();
	private SLabel lRequestedBy		= new SLabel();
	private SLabel lRequestDate		= new SLabel();
	
	private STextField	tfEntityType	= new STextField(DEF_COL_20);
	private STextField	tfOperation		= new STextField(DEF_COL_20);
	private STextField	tfRequestedBy	= new STextField(DEF_COL_20);
	private STextField	tfRequestDate	= new VDateField(DEF_COL_10, DateFormat.TIME_FORMAT_LONG);
	
	private SPanel pDifferences = new SPanel();
	private STable tDifferences = new STable();
	
	private boolean isTableVisible = false;
	
	/** Property handler to synchronize model and view */
	private PropertyHandler propertyHandler = new PropertyHandler();	

	
	/**
	 * Constructor for CheckStateView.
	 */
	public DualControlJobEditDialog() {
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
	public DualControlJobEditDialog(Model model) {
		this();
		setModel(model);
	}


    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (getDualControlJobEditModel() == null) {
            return;
        }
        showOrHideTable();
        propertyHandler.syncFields();
    }

	@SuppressWarnings("unused")
    private Dialog getDialog() {
		Component parent = this;
		while (parent != null && !(parent instanceof Dialog)) {
        	parent = parent.getParent();
        } 
        
        return (Dialog)parent;
	}
    
    /**
	 * Return casted model
	 * 
	 * Creation date: (8/31/00 10:43:03 AM)
	 * f
	 */
	public DualControlJobEditModel getDualControlJobEditModel() {
		return getModel() instanceof DualControlJobEditModel ? (DualControlJobEditModel)getModel() : null;
	}


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(DualControlJobEditModel.P_DIFFERENCE_TABLE_MODEL,	tDifferences);	
		propertyHandler.add(DualControlJobEditModel.P_ENTITY_TYPE, 				tfEntityType);
		propertyHandler.add(DualControlJobEditModel.P_OPERATION,				tfOperation);
		propertyHandler.add(DualControlJobEditModel.P_REQUEST_DATE,				tfRequestDate);
		propertyHandler.add(DualControlJobEditModel.P_REQUESTED_BY,				tfRequestedBy);
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
    	pBasicData.setTitle(getResourceString(RESOURCE_BASE + "T_BASICDATA"));
    	pDifferences.setTitle(getResourceString(RESOURCE_BASE + "T_DIFFERENCES"));

    	lEntityType.setText		(getResourceString(RESOURCE_BASE + "L_ENTITY_TYPE"));
    	lOperation.setText		(getResourceString(RESOURCE_BASE + "L_OPERATION"));
    	lRequestDate.setText	(getResourceString(RESOURCE_BASE + "L_REQUESTED_DATE"));
    	lRequestedBy.setText	(getResourceString(RESOURCE_BASE + "L_REQUESTED_BY"));
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
	 	panel.add(lEntityType,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lRequestedBy,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	
	 	
	 	l = 0; c = 1; // Column 1 	
	 	panel.add(tfEntityType,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(tfRequestedBy,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	

	 	l = 0; c = 2; // Column 2
	 	panel.add(lOperation,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lRequestDate,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	

	 	l = 0; c = 3; // Column 3
	 	panel.add(tfOperation,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(tfRequestDate,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	


		pDifferences.setBorderPainted(true);
		pDifferences.setMargin(insets0088);
		pDifferences.add(tDifferences,		new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, insets4800, 0, 0));

		this.add(pBasicData,    	new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.0, NORTH, HORIZ, insets0000, 0, 0));
		
		this.setReadOnly();
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
	    if (!(newModel instanceof DualControlJobEditModel)) {
	        throw new IllegalArgumentException("Model not instance of DualControlJobEditModel");
	    }
	    propertyHandler.setModel(newModel);
	    super.setModel(newModel);
	    fillView();
	}
	
	/**
	 * Anzeigen des Detail-Bereichs.
	 * Erstellungsdatum: (05.10.00 11:42:44)
	 */
	private void showOrHideTable() {
//		Dialog dialog = getDialog();
//		if (dialog == null) {
//			return;
//		}
		
		Object tableModel = getModel().getProperty(DualControlJobEditModel.P_DIFFERENCE_TABLE_MODEL);
		boolean hasTable = tableModel != null;

		// öffnen des Details-Bereichs
		if (hasTable && !isTableVisible) {
			this.add(pDifferences, 	new GridBagConstraints2(0, 1, 1, 1, 1.0, 1.0, SOUTH, BOTH, insets0000, 0, 0));
			pDifferences.setMinimumSize(new Dimension(0,100));
		} else if (!hasTable && isTableVisible) {
			this.remove(pDifferences);
			isTableVisible = false;
		}
	}
	
	/**
	 * Updates all language dependend labels
	 */
	@Override
    public void updateLabels() {
		initLabels();
	}
 

}
