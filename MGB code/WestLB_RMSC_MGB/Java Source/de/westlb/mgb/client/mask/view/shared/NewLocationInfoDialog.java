package de.westlb.mgb.client.mask.view.shared;

import java.awt.Dimension;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.shared.NewInstrumentInfoModel;
import de.westlb.mgb.client.mask.model.shared.NewLocationInfoModel;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.STable;
import de.westlb_systems.xaf.swing.STextPane;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.ExtendedDialog;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.util.Debug;


/**
 * @author WSY4148
 *
 * Dialog to display all new locations.
 */
public class NewLocationInfoDialog extends AbstractView implements ExtendedDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4031363246329015263L;

//	public static final int EV_LOCATION_EDIT	= 1;

	private String RESOURCE_BASE = getResourceBase();
//	private final String CMD_EDIT				= RESOURCE_BASE + "B_Edit";
	private final String CMD_CANCEL			= RESOURCE_BASE + "B_Cancel";
	
	/**
	 * PropertyHandler for the model view synchronization.
	 */
	private Dimension minSize = new Dimension(460, 190);	
	private PropertyHandler propertyHandler = new PropertyHandler();
	private STable tTrades = new STable();
	
	private STextPane textPane = new STextPane() {
	        private static final long serialVersionUID = 5595685342910266493L;

            @Override
            public void setText(String newText) {
				tarea.setBackground(UIManager.getColor("DetailsPanel.background"));
				super.setText(newText);
			}
  	};  
	
	/**
	 * Constructor for CheckStateView.
	 */
	public NewLocationInfoDialog() {
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
	public NewLocationInfoDialog(Model model) {
		this();
		setModel(model);
	}
	
//	public void doEditInstrument() {
//		// If no row selected, select first row
//		if (tTrades.getTable().getFirstSelectedRow() < 0) {
//			tTrades.setSelectedRow(0);
//		}
//		NewInstrumentInfoModel model = getNewInstrumentInfoModel();
//		
//		// If no row selected (may be table is empty) log error message
//		if (model == null || tTrades.getTable().getFirstSelectedRow() < 0) {
//			logMessage(LogMessageEvent.ERROR, getResourceString(RESOURCE_BASE + "E_001"));
//			return;
//		}
//						
////		fireUserEvent(EV_INSTRUMENT_EDIT, model.getInstrumentId(tTrades.getSelectedRow()));
//	}
	
    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (!(getModel() instanceof NewLocationInfoModel)) {
            return;
        }
        propertyHandler.syncFields();
    }

    /**
     * @see de.westlb_systems.xaf.ui.view.ExtendedDialog#getAdditionalCommands()
     */
    @Override
    public String[] getAdditionalCommands() {
        return new String[] { 
//        	CMD_EDIT, 
        	CMD_CANCEL, 
        };
    }

    /**
     * @see de.westlb_systems.xaf.ui.view.ExtendedDialog#getDefaultCommand()
     */
    @Override
    public String getDefaultCommand() {
        return CMD_CANCEL; 
    }

	/**
	 * return the casted model
	 */
	public NewLocationInfoModel getNewLocationInfoModel() {
		if (!(getModel() instanceof NewInstrumentInfoModel)) {
			return null;
		}
		
		return (NewLocationInfoModel)getModel();
	}
	    
    /**
     * @see de.westlb_systems.xaf.ui.view.ExtendedDialog#handleAdditionalCommand(String)
     */
    @Override
    public boolean handleAdditionalCommand(String command) {
//		if (CMD_EDIT.equals(command)) {
//			doEditInstrument();
//			return false;
//		} else if (CMD_CANCEL.equals(command)) {
//			return true;
//		} else {
//        	return true;
//		}
    	return true;
    }
	
    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(NewLocationInfoModel.P_TABLE_MODEL,	tTrades);	
    }

    /**
     * Method initLabels.
     */
    private void initLabels() {
		setTitle(			getResourceString(RESOURCE_BASE + "T_001")	);
		textPane.setText(	getResourceString(RESOURCE_BASE + "T_002")	);
    }

    /**
     * Method initLayout.
     */
    private void initLayout() {
 		setMinimumSize(minSize);
		textPane.setPreferredSize(new Dimension(minSize.width, 40));
		
		this.add(textPane, 	new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.4, NORTH, BOTH, insets4800, 0, 0));
		this.add(tTrades,	new GridBagConstraints2(0, 1, 1, 1, 1.0, 1.0, SOUTH, BOTH, insets4800, 0, 0));
    }


    /**
     * Method initComponents.
     */
    private void initComponents() {
		//this.setBorder(border);
		textPane.setBackground(UIManager.getColor("DetailsPanel.background"));
		textPane.setEditable(false);
    }

	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof NewLocationInfoModel)) {
	        throw new IllegalArgumentException("Model not instance of NewLocationInfoModel");
	    }
	    propertyHandler.setModel(newModel);
	    super.setModel(newModel);
	    fillView();
	}
}
