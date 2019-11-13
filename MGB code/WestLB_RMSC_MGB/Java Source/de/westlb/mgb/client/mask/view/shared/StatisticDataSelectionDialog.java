package de.westlb.mgb.client.mask.view.shared;

import de.westlb.mgb.client.mask.model.shared.StatisticDataSelectionModel;
import de.westlb.mgb.client.ui.util.VDateFieldSupportingPopup;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.SPanel;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.EditDialog;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.util.Debug;

/**
 * @author WSY4148
 *
 * Dialog to enter a new manual state of a trade.
 */
public class StatisticDataSelectionDialog extends AbstractView implements EditDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2542693354398491978L;

	private String RESOURCE_BASE = getResourceBase();
	
	public static final int EV_EXECUTE = 1;
		
	/** gui components */
	private SPanel pPeriodData			= new SPanel();
	
	private SLabel lFromDate			= new SLabel();
	private SLabel lToDate				= new SLabel();
	
	private VDateFieldSupportingPopup	tfFromDate		= new VDateFieldSupportingPopup();
	private VDateFieldSupportingPopup	tfToDate		= new VDateFieldSupportingPopup();
	
	/** Property handler to synchronize model and view */
	private PropertyHandler propertyHandler = new PropertyHandler();	
	
	/**
	 * Constructor for CheckStateView.
	 */
	public StatisticDataSelectionDialog() {
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
	public StatisticDataSelectionDialog(Model model) {
		this();
		setModel(model);
	}


    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (getStatisticDataSelectionModel() == null) {
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
	public StatisticDataSelectionModel getStatisticDataSelectionModel() {
		return getModel() instanceof StatisticDataSelectionModel ? (StatisticDataSelectionModel)getModel() : null;
	}


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(StatisticDataSelectionModel.P_FROM_DATE,	tfFromDate);	
		propertyHandler.add(StatisticDataSelectionModel.P_TO_DATE,		tfToDate);	
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
    	pPeriodData.setTitle				(getResourceString(RESOURCE_BASE + "T_PERIOD_DATA"));
    	lFromDate.setText					(getResourceString(RESOURCE_BASE + "L_FROM_DATE"));
    	lToDate.setText						(getResourceString(RESOURCE_BASE + "L_TO_DATE"));
    }
    
    /**
     * Method initLayout.
     */
    private void initLayout() {
		int l, c;
		pPeriodData.setBorderPainted(true);
		pPeriodData.setMargin(insets0088);

		SPanel panel = new SPanel();
		pPeriodData.add(panel,	new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, insets0000, 0, 0));

		l = 0; c = 0; // Column 0
	 	panel.add(lFromDate,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	
	 	panel.add(lToDate,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 	

		l = 0; c = 1; // Column 0
	 	panel.add(tfFromDate,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));	 		 	
	 	panel.add(tfToDate,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4800, 0, 0));

		this.add(pPeriodData,   new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets0000, 0, 0));
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
	    if (!(newModel instanceof StatisticDataSelectionModel)) {
	        throw new IllegalArgumentException("Model not instance of StatisticModel");
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
    /* (Kein Javadoc)
     * @see de.westlb_systems.xaf.application.ViewContent#saveData()
     */
    @Override
    public boolean saveData() {
		if (getStatisticDataSelectionModel() == null) {
			return false;
		}
		
    	propertyHandler.syncModel();
		getStatisticDataSelectionModel().saveModel();
    	fireUserEvent(EV_EXECUTE, this);
    	return true;
    }

}
