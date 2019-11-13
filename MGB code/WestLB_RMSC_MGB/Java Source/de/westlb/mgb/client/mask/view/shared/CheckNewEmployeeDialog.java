package de.westlb.mgb.client.mask.view.shared;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.westlb.mgb.client.application.ApplicationComponentBox;
import de.westlb.mgb.client.application.ContentID;
import de.westlb.mgb.client.mask.model.shared.CheckNewEmployeeModel;
import de.westlb_systems.xaf.application.ContentSet;
import de.westlb_systems.xaf.application.Synchronizer;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SBorder;
import de.westlb_systems.xaf.swing.SBorderFactory;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.SPanel;
import de.westlb_systems.xaf.swing.STabbedPane;
import de.westlb_systems.xaf.swing.STable;
import de.westlb_systems.xaf.swing.STableSelectionEvent;
import de.westlb_systems.xaf.swing.STableSelectionListener;
import de.westlb_systems.xaf.swing.STextField;
import de.westlb_systems.xaf.ui.components.event.ValueChangeEvent;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.DialogViewer;
import de.westlb_systems.xaf.ui.view.ExtendedDialog;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.util.Debug;

/**
 * @author WSY4148
 *
 * Dialog to enter a new manual state of a trade.
 */
public class CheckNewEmployeeDialog extends AbstractView implements ExtendedDialog {
	/**
     * 
     */
    private static final long serialVersionUID = -4207597936992886138L;

    private String RESOURCE_BASE = getResourceBase();
	
	/*
	 * Constant definitions for user events
	 */
	public static final int APPLY_PERFORMED = 1;
	
	public final String CMD_SEARCH				= RESOURCE_BASE + "B_Search";
	public final String CMD_ASSIGN_TRADER_ID	= RESOURCE_BASE + "B_AssignTraderId";
	public final String CMD_NEW_EMPLOYEE		= RESOURCE_BASE + "B_NewEmployee";
	public final String CMD_CLEAR				= RESOURCE_BASE + "B_Clear";
	public final String CMD_APPLY				= RESOURCE_BASE + "B_Apply";
	public final String CMD_CANCEL				= RESOURCE_BASE + "B_Cancel";
		
	private final String[] ASSIGN_MODE_COMMANDS = new String[] { 
		CMD_SEARCH , 
		CMD_CLEAR ,         	
		CMD_ASSIGN_TRADER_ID, 
		CMD_NEW_EMPLOYEE,
		CMD_CANCEL,
	};
	
	private final String[] NORMAL_MODE_COMMANDS = new String[] { 
		CMD_SEARCH , 
		CMD_CLEAR ,         	
		CMD_APPLY ,         	
		CMD_CANCEL,
	};

	
	// Flag that indicates if dialog runs in assign trader id mode. In normal
	// search mode we have the buttons 'ok' and 'cancel' only.
	private boolean assignMode = true;
	
	private boolean searchResultOn = false;

	/*
	 * Property handler to synchronize model and view
	 */
	private PropertyHandler propertyHandler = new PropertyHandler();

	
	private STabbedPane	tbSearchNewEmployee	= new STabbedPane();
	private SPanel			pSearchResult		= new SPanel();

	private SPanel			pSearchByName  		= new SPanel();
	private SLabel			lSubTitle			= new SLabel();

	private SLabel			lLastName			= new SLabel();
	private SLabel			lFirstName			= new SLabel();
	private STextField		tfLastName			= new STextField(DEF_COL_12);
	private STextField		tfFirstName			= new STextField(DEF_COL_12);
	
	private STable			tSearchResult		= new STable();

	private SBorder border = SBorderFactory.createEmptyBorder(insets4088);

	/**
	 * Listener fuer alle Events
	 */
	private Listener listener = new Listener();
	
	private String selectedAction = null;

	/**
	 * Listener fuer alle Events
	 */
	private class Listener implements ActionListener, STableSelectionListener {
		@SuppressWarnings("unused")
        public void valueChanged(ValueChangeEvent event){
		}
		
        /**
         * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
        }

		/* (non-Javadoc)
		 * @see de.westlb_systems.xaf.swing.STableSelectionListener#selectionChanged(de.westlb_systems.xaf.swing.STableSelectionEvent)
		 */
		@Override
        public void selectionChanged(STableSelectionEvent ev) {
			if (ev.isDoubleClicked()) {
				fireUserEvent(new UserEvent(CheckNewEmployeeDialog.this, APPLY_PERFORMED, getEmployeeIdAtRow(ev.getRow())));
			}
		}
	}
	
	/**
	 * Constructor for CheckStateView.
	 */
	public CheckNewEmployeeDialog() {
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
	public CheckNewEmployeeDialog(Model model) {
		this(model, true);
	}
	
	/**
	 * Constructor which sets the model
	 */
	public CheckNewEmployeeDialog(Model model, boolean isAssignMode) {
		this();
		setModel(model);
		assignMode = isAssignMode;
	}

	public boolean cancel() {
		return false;
	}
	
	public void doClear() {
		tfFirstName.setValue("");
		tfLastName.setValue("");
		if (getSearchNewEmployeeModel() != null) {
			getSearchNewEmployeeModel().clearSearchResult();
		}
		showOrHideSearchResult();
	}
	
	/**
	 * Try to assign selected trader to selected employee
	 */
	public boolean doAssign() {
		boolean success = false;
		// Ensure model to be uptodate
		propertyHandler.syncModel();

        boolean oldC = Synchronizer.setWaitCursor(true, this);
        if (getSearchNewEmployeeModel() != null) {
        	int row = tSearchResult.getSelectedRow();
        	success = getSearchNewEmployeeModel().doAssignTraderToEmployee(row);
        }        
        Synchronizer.setWaitCursor(oldC, this);

        if (!success) {
            int error = getModel().getError();
            if ((error == Model.APPLICATION_ERROR) || (error == Model.DATABASE_ERROR)) {
                Object message = getModel().getErrorMessage();
                Object details = getModel().getErrorDetails();
                showMessageBox(SHOW_ERROR, message, details);
            }
        } 

		fillView();
		return success;
	}	

	/**
	 * Start dialog to enter a new user
	 */
	public boolean doCreateNew() {
		Object parameter = null;
		if (getSearchNewEmployeeModel() != null) {
			parameter = getSearchNewEmployeeModel().getEmployeeFromSearchParams();
		}
        return showDialog(ContentID.MGB_EDIT_EMPLOYEE, parameter, this);
	}	

	
	/**
	 * Starts the search
	 */
	public boolean doSearch() {
		boolean success = false;
		// Ensure model to be uptodate
		propertyHandler.syncModel();

        boolean oldC = Synchronizer.setWaitCursor(true, this);
        if (getSearchNewEmployeeModel() != null) {
        	success = getSearchNewEmployeeModel().doSearch();
        }        
        Synchronizer.setWaitCursor(oldC, this);

        if (!success) {
            int error = getModel().getError();
            if ((error == Model.APPLICATION_ERROR) || (error == Model.DATABASE_ERROR)) {
                Object message = getModel().getErrorMessage();
                Object details = getModel().getErrorDetails();
                showMessageBox(SHOW_ERROR, message, details);
            }
        } 

		fillView();
		tSearchResult.setSelectedRow(0);
		return success;
	}
	
	/**
	 * Returns the result of the dialog. The result
	 * is one of the string constants:
	 * 
	 * 	CMD_ASSIGN_TRADER_ID, 
	 * 	CMD_NEW_EMPLOYEE,
	 *	CMD_CANCEL
	 * 
	 */
	public String getSelectedAction() {
		return selectedAction;
	}
	
    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (getSearchNewEmployeeModel() == null) {
            return;
        }
        propertyHandler.syncFields();
        showOrHideSearchResult();
        updateSubTitle();
    }
    
    /**
	 * Return casted model
	 * 
	 * Creation date: (8/31/00 10:43:03 AM)
	 * f
	 */
	public CheckNewEmployeeModel getSearchNewEmployeeModel() {
		return getModel() instanceof CheckNewEmployeeModel ? (CheckNewEmployeeModel)getModel() : null;
	}


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(CheckNewEmployeeModel.P_FILTER_FIRST_NAME,			tfFirstName);	
		propertyHandler.add(CheckNewEmployeeModel.P_FILTER_LAST_NAME, 			tfLastName);
		propertyHandler.add(CheckNewEmployeeModel.P_SEARCH_RESULT_TABLE_MODEL,	tSearchResult);
		propertyHandler.add(CheckNewEmployeeModel.P_SEARCH_RESULT_TABLE_MODEL,	tSearchResult);
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
		if(tbSearchNewEmployee.getTabCount() >= 1) {
			tbSearchNewEmployee.setTitleAt(0, getResourceString(RESOURCE_BASE + "L_SEARCH_NAME"));
		}
    	lFirstName.setText	(getResourceString(RESOURCE_BASE + "L_FIRST_NAME"));
    	lLastName.setText	(getResourceString(RESOURCE_BASE + "L_LAST_NAME"));   	    	
    }
    
    private void updateSubTitle() {
    	lSubTitle.setText((String)getSearchNewEmployeeModel().getProperty(CheckNewEmployeeModel.P_TITLE));
    }


    /**
     * Method initLayout.
     */
    private void initLayout() {
	 	this.add(lSubTitle,			new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets4088, 0, 0));

	 	this.add(tbSearchNewEmployee,	new GridBagConstraints2(0, 1, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets0000, 0, 0));
	 	// this.add(pSearchResult,		new GridBagConstraints2(0, 1, 1, 1, 1.0, 1.0, CENTER, BOTH,  insets0000, 0, 0));	
	 	
		tbSearchNewEmployee.addTab(null, pSearchByName);		
		pSearchByName.setBorder(border);
		//Spalte 0
		pSearchByName.add(lLastName,		new GridBagConstraints2(0, 0, 1, 1, 0.0, 0.0, WEST, HORIZ, insets4800, 0, 0));
		pSearchByName.add(lFirstName,		new GridBagConstraints2(0, 1, 1, 1, 0.0, 0.0, WEST, HORIZ, insets4800, 0, 0));
		//Spalte 1
		pSearchByName.add(tfLastName, 		new GridBagConstraints2(1, 0, 1, 1, 1.0, 0.0, WEST, HORIZ, insets4800, 0, 0));
		pSearchByName.add(tfFirstName,		new GridBagConstraints2(1, 1, 1, 1, 0.0, 0.0, WEST, HORIZ, insets4800, 0, 0));
		
	 	pSearchResult.setBorder(border);
	 	pSearchResult.add(tSearchResult,	new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, insets0000, 0, 0));
	 	
    }

    /**
     * Method initComponents.
     */
    private void initComponents() {
		tbSearchNewEmployee.removeAll();
		tSearchResult.addSTableSelectionListener(listener);
    }

	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof CheckNewEmployeeModel)) {
	        throw new IllegalArgumentException("Model not instance of CheckNewEmployeeModel");
	    }
	    propertyHandler.setModel(newModel);
	    super.setModel(newModel);
	    fillView();
	}
	
	public Dialog getDialog() {
		Component parent = this;
		while (parent != null && !(parent instanceof Dialog)) {
        	parent = parent.getParent();
        } 
        
        return (Dialog)parent;
	}
	
	/**
	 * Anzeigen des Detail-Bereichs.
	 * Erstellungsdatum: (05.10.00 11:42:44)
	 */
	private void showOrHideSearchResult() {
		Dialog dialog = getDialog();
		if (dialog == null) {
			return;
		}
		
		Object tableModel = getModel().getProperty(CheckNewEmployeeModel.P_SEARCH_RESULT_TABLE_MODEL);
		boolean searchResult = tableModel != null;

		// öffnen des Details-Bereichs
		if (searchResult && !searchResultOn) {
			this.add(pSearchResult, new GridBagConstraints2(0, 2, 1, 1, 1.0, 1.0, CENTER, BOTH, insets0000, 0, 0));
			dialog.setSize(dialog.getSize().width, 300);
			searchResultOn = true;
			dialog.invalidate();
			dialog.validate();
			dialog.repaint();
		} else if (!searchResult && searchResultOn) {
			this.remove(pSearchResult);
			dialog.setSize(dialog.getSize().width, dialog.getPreferredSize().height);
			searchResultOn = false;
			dialog.invalidate();
			dialog.validate();
			dialog.repaint();
		}


	}
	
	/**
	 * Updates all language dependend labels
	 */
	@Override
    public void updateLabels() {
		initLabels();
	}
    /**
     * @see de.westlb_systems.xaf.ui.view.ExtendedDialog#getAdditionalCommands()
     */
    @Override
    public String[] getAdditionalCommands() {
    	return assignMode ? ASSIGN_MODE_COMMANDS : NORMAL_MODE_COMMANDS;
    }

    /**
     * @see de.westlb_systems.xaf.ui.view.ExtendedDialog#getDefaultCommand()
     */
    @Override
    public String getDefaultCommand() {
        return CMD_SEARCH; 
    }

    /**
     * @see de.westlb_systems.xaf.ui.view.ExtendedDialog#handleAdditionalCommand(String)
     */
    @Override
    public boolean handleAdditionalCommand(String command) {
    	selectedAction = command;
    	
		if (CMD_SEARCH.equals(command)) {
			doSearch();
			return false;
		} else if (CMD_NEW_EMPLOYEE.equals(command)) {
			return doCreateNew();
		} else if (CMD_ASSIGN_TRADER_ID.equals(command)) {
			return doAssign();
		} else if (CMD_CLEAR.equals(command)) {
			doClear();
			return false;
		} else if (CMD_CANCEL.equals(command)) {
			cancel();
			return true;
		} else if (CMD_APPLY.equals(command)) {
			fireUserEvent(new UserEvent(this, APPLY_PERFORMED, getSelectedEmployeeId()));
			return false;
		}
		
		return false;
    }
    
    public void setAssignMode(boolean newValue) {
    	assignMode = newValue;
    }
    
    /**
     * Returns the search result of the dialog as a employee id
     *  return employee id 
     */
    public Long getSelectedEmployeeId() {
    	return getEmployeeIdAtRow(tSearchResult.getSelectedRow());
    }
    
    private Long getEmployeeIdAtRow(int row) {
		CheckNewEmployeeModel model = (CheckNewEmployeeModel)getModel();
		if (model == null || tSearchResult.getSelectedRow() < 0 ) {
			return null;
		}
    	
		return model.getEmployeeId(tSearchResult.getSelectedRow());
    }
    
    
    /**
	 * Display a dialog
	 * 
	 * @param contentID int
	 * @param parameter java.lang.Object
	 * @param view Owner Componente
	 */
	private boolean showDialog(String contentID, Object parameter, Component view) {
		
		ApplicationComponentBox box = new ApplicationComponentBox();
	
		// Dialog starten
		if (contentID != null) {
			ContentSet set = box.createContentSet(contentID, parameter, view);
			if (set != null && set.getViewDialog() instanceof DialogViewer) {
//				getDialog().hide();
				return ((DialogViewer) set.getViewDialog()).showDialog();
			}
		}
		return false;
	}
}
