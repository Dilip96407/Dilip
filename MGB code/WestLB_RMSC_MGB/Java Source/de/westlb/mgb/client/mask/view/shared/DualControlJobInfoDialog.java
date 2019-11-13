package de.westlb.mgb.client.mask.view.shared;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.shared.DualControlJobInfoModel;
import de.westlb.mgb.client.mask.model.shared.NewInstrumentInfoModel;
import de.westlb_systems.xaf.application.Synchronizer;
import de.westlb_systems.xaf.application.event.LogMessageEvent;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SMenuItem;
import de.westlb_systems.xaf.swing.SPopupMenu;
import de.westlb_systems.xaf.swing.SPopupProvider;
import de.westlb_systems.xaf.swing.STable;
import de.westlb_systems.xaf.swing.STableEditorEvent;
import de.westlb_systems.xaf.swing.STableEditorListener;
import de.westlb_systems.xaf.swing.STableSelectionEvent;
import de.westlb_systems.xaf.swing.STableSelectionListener;
import de.westlb_systems.xaf.swing.STextPane;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.ExtendedDialog;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.util.Debug;


/**
 * @author WSY4148
 *
 * Dialog to display all new traders which not assigned to an employee in MGB.
 */
public class DualControlJobInfoDialog extends AbstractView implements ExtendedDialog {

	/**
     * 
     */
    private static final long serialVersionUID = -3875750243861702409L;

    public static final int EV_OPEN		= 2;

	public static final String CONFIRM 		= "CONFIRM";

	private String RESOURCE_BASE = getResourceBase();
	
	private final String CMD_CONFIRM			= RESOURCE_BASE + "B_Confirm";
	private final String CMD_OPEN				= RESOURCE_BASE + "B_Open";
	private final String CMD_DELETE			= RESOURCE_BASE + "B_Delete";
	private final String CMD_CANCEL			= RESOURCE_BASE + "B_Cancel";
	
	/**
	 * PropertyHandler for the model view synchronization.
	 */

	private Dimension minSize = new Dimension(460, 190);	
	private PropertyHandler propertyHandler = new PropertyHandler();
	private STable tDualControlJobs = new STable();

	private SMenuItem popupItemConfirm = new SMenuItem();
		
	private STextPane textPane = new STextPane() {
	        private static final long serialVersionUID = 7361449016624722591L;

            @Override
            public void setText(String newText) {
				tarea.setBackground(UIManager.getColor("DetailsPanel.background"));
				super.setText(newText);
			}
  	};  
	
	/**
	 * Listener for all events fired in the view
	 */
	private Listener listener = new Listener();
	private class Listener implements ActionListener, STableSelectionListener, STableEditorListener {
        /**
         * @see de.westlb_systems.xaf.swing.STableSelectionListener#selectionChanged(STableSelectionEvent)
         */
        @Override
        public void selectionChanged(STableSelectionEvent e) {
			if (e.isDoubleClicked()) {
				doDualControlJobOpen();
			}			
        }

        /**
         * @see de.westlb_systems.xaf.swing.STableEditorListener#invokeEditor(STableEditorEvent)
         */
        @Override
        public void invokeEditor(STableEditorEvent e) {
        	if (STableEditorEvent.OPEN.equals(e.getActionCommand())) {
       			doDualControlJobOpen();
        	} else if (STableEditorEvent.DELETE.equals(e.getActionCommand())) {
        		doDualControlJobDelete();
        	} 
		}


        /**
         * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
			if (e.getSource() == popupItemConfirm) {
				doDualControlJobConfirm();
			}
        }
	}
	
	/**
	 * Constructor for DualControlJobInfoDialog.
	 */
	public DualControlJobInfoDialog() {
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
	public DualControlJobInfoDialog(Model model) {
		this();
		setModel(model);

	}

	@SuppressWarnings("unused")
    private void selectFirstRowIfNoSelection() {
		// If no row selected, select first row
		if (tDualControlJobs.getTable().getFirstSelectedRow() < 0) {
			tDualControlJobs.setSelectedRow(0);
		}		
	}
		
	private int getNrOfRowsSelected () {
		return tDualControlJobs.getTable().getFirstSelectedRow() < 0 ? -1 : tDualControlJobs.getSelectedRows().length;
	}
	
	public boolean doDualControlJobConfirm() {
		DualControlJobInfoModel model = getDualControlJobInfoModel();
		
		// If no row selected (may be table is empty) log error message
		if (model == null || getNrOfRowsSelected() < 0) {
			logMessage(LogMessageEvent.ERROR, getResourceString(RESOURCE_BASE + "E_001"));
			return false;
		}

        boolean oldC = Synchronizer.setWaitCursor(true, this);
       	model.doConfirmDualControlJobIds(tDualControlJobs.getSelectedRows());
        Synchronizer.setWaitCursor(oldC, this);
        boolean success = model.getError() == Model.NO_ERROR;
		if (!success) {
			handleModelError();
		} else {
			model.reload();	
		}

		fillView();
		
		return success;
	}
	
    /**
     * Method doDualControlJobDelete.
     */
    private boolean doDualControlJobDelete() {
		if (!showMessageBox(SHOW_QUESTION, getResourceString(RESOURCE_BASE + "Q_DELETE"))) {
			return false;
		};

		DualControlJobInfoModel model = getDualControlJobInfoModel();
		
		// If no row selected (may be table is empty) log error message
		if (model == null || getNrOfRowsSelected() < 0) {
			logMessage(LogMessageEvent.ERROR, getResourceString(RESOURCE_BASE + "E_001"));
			return false;
		}

        boolean oldC = Synchronizer.setWaitCursor(true, this);
       	model.doDeleteDualControlJobIds(tDualControlJobs.getSelectedRows());
        Synchronizer.setWaitCursor(oldC, this);

        boolean success = model.getError() == Model.NO_ERROR;
		if (!success) {
			handleModelError();
		} else {
			model.reload();	
		}

		fillView();
		
		return success;
    }

    /**
     * Method doDualControlJobOpen.
     */
    private boolean doDualControlJobOpen() {
		DualControlJobInfoModel model = getDualControlJobInfoModel();

		// If no row selected (may be table is empty) log error message
		if (model == null || getNrOfRowsSelected() < 0) {
			logMessage(LogMessageEvent.ERROR, getResourceString(RESOURCE_BASE + "E_001"));
			return false;
		}
		
		fireUserEvent(EV_OPEN, model.getDualControlJobId(tDualControlJobs.getSelectedRows()[0]));		
		return true;
    }	
	
    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (!(getModel() instanceof DualControlJobInfoModel)) {
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
        	CMD_CONFIRM, 
        	CMD_OPEN, 
        	CMD_DELETE, 
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
	public DualControlJobInfoModel getDualControlJobInfoModel() {
		if (!(getModel() instanceof DualControlJobInfoModel)) {
			return null;
		}
		
		return (DualControlJobInfoModel)getModel();
	}
	    
    /**
     * @see de.westlb_systems.xaf.ui.view.ExtendedDialog#handleAdditionalCommand(String)
     */
    @Override
    public boolean handleAdditionalCommand(String command) {
		if (CMD_CONFIRM.equals(command)) {
			doDualControlJobConfirm();
			return false;
		} else 

		if (CMD_DELETE.equals(command)) {
			doDualControlJobDelete();
			return false;
		} else 

		if (CMD_OPEN.equals(command)) {
			doDualControlJobOpen();			
			return false;
		} else 
		
		if (CMD_CANCEL.equals(command)) {
			return true;
		} 
		
		else {
        	return true;
		}
    }

    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(NewInstrumentInfoModel.P_TABLE_MODEL,	tDualControlJobs);	
    }

    /**
     * Method initLabels.
     */
    private void initLabels() {
		setTitle					(getResourceString(RESOURCE_BASE + "T_001")	);
		textPane.setText			(getResourceString(RESOURCE_BASE + "T_002")	);
		popupItemConfirm.setText	(getResourceString(CMD_CONFIRM) );
    }

    /**
     * Method initLayout.
     */
    private void initLayout() {
 		setMinimumSize(minSize);
		textPane.setPreferredSize(new Dimension(minSize.width, 40));
		
		this.add(textPane, 	new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.4, NORTH, BOTH, insets4800, 0, 0));
		this.add(tDualControlJobs,	new GridBagConstraints2(0, 1, 1, 1, 1.0, 1.0, SOUTH, BOTH, insets4800, 0, 0));
    }


    /**
     * Method initComponents.
     */
    private void initComponents() {
		//this.setBorder(border);
		textPane.setBackground(UIManager.getColor("DetailsPanel.background"));
		textPane.setEditable(false);
		
		tDualControlJobs.setSelectionMode(STable.MULTIPLE_SELECTION);

		tDualControlJobs.setPopupProvider(
			STable.OPEN_PROVIDER|
			STable.REMOVE_PROVIDER
		);
		tDualControlJobs.addSTableSelectionListener(listener);
		tDualControlJobs.addTableEditorListener(listener);
		popupItemConfirm.addActionListener(listener);

		SPopupProvider provider = tDualControlJobs.getPopupProvider();
		SPopupMenu menu = provider.getPopupMenu();
		Component[] items = menu.getComponents();	
		menu.removeAll();	
		menu.add(popupItemConfirm);
		menu.addSeparator();
		if (items!=null) {
			for (int i=0;i<items.length;i++) {
				menu.add(items[i]);
			}
		}


    }

	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof DualControlJobInfoModel)) {
	        throw new IllegalArgumentException("Model not instance of DualControlJobInfoModel");
	    }
	    propertyHandler.setModel(newModel);
	    super.setModel(newModel);
	    fillView();
	}
    /**
     * @see de.westlb_systems.xaf.application.ViewContent#refresh()
     */
    @Override
    public void refresh() {
        super.refresh();
        fillView();
    }

}
