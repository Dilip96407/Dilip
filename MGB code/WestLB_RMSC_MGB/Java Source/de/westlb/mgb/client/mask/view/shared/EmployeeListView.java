package de.westlb.mgb.client.mask.view.shared;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.shared.EmployeeListModel;
import de.westlb_systems.xaf.application.ActionSet;
import de.westlb_systems.xaf.application.Synchronizer;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.SPanel;
import de.westlb_systems.xaf.swing.STable;
import de.westlb_systems.xaf.swing.STableEditorEvent;
import de.westlb_systems.xaf.swing.STableEditorListener;
import de.westlb_systems.xaf.swing.STableSelectionEvent;
import de.westlb_systems.xaf.swing.STableSelectionListener;
import de.westlb_systems.xaf.swing.STextField;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.ui.view.TablePanel;
import de.westlb_systems.xaf.ui.view.TitlePanel;
import de.westlb_systems.xaf.util.Debug;

/**
 * @author WSY4148
 *
 * Dialog to enter a new manual state of a trade.
 */
public class EmployeeListView extends AbstractView {
	/**
     * 
     */
    private static final long serialVersionUID = -8167054104097538407L;

    private String RESOURCE_BASE = getResourceBase();

	public final static int EV_EMPLOYEE_OPEN 	= 0;
	public final static int EV_EMPLOYEE_EDIT 	= 1;
	public final static int EV_EMPLOYEE_NEW		= 2;
	
	
	private ActionSet
		actionEditEmployee = new ActionSet("EDIT_EMPLOYEE", true);
		
	/** gui components */
	private TitlePanel pTitle = new TitlePanel();
	private TablePanel tpFilterPanel	= new TablePanel();
	private TablePanel tpResultPanel	= new TablePanel();
	private SPanel pFilterPanel 		= new SPanel();
	private STable tResult				= new STable();
	
	private SLabel lFirstName			= new SLabel();	
	private SLabel lLastName			= new SLabel();
	private SLabel lTraderId			= new SLabel();
	
	private STextField tfFirstName		= new STextField();
	private STextField tfLastName		= new STextField();
	private STextField tfTraderId		= new STextField();
	
	/** Property handler to synchronize model and view */
	private PropertyHandler propertyHandler = new PropertyHandler();	

	/** Listener fuer alle Events */
	private Listener listener = new Listener();
	
	/**
	 * Listener fuer alle Events
	 */
	private class Listener implements STableSelectionListener, STableEditorListener, ActionListener, KeyListener {
        /**
         * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
        }
        /**
         * @see java.awt.event.KeyListener#keyPressed(KeyEvent)
         */
        @Override
        public void keyPressed(KeyEvent e) {
        	if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        		doSearch();
        	}
        }

        /**
         * @see java.awt.event.KeyListener#keyReleased(KeyEvent)
         */
        @Override
        public void keyReleased(KeyEvent e) {
        }

        /**
         * @see java.awt.event.KeyListener#keyTyped(KeyEvent)
         */
        @Override
        public void keyTyped(KeyEvent e) {
        }

        /**
         * @see de.westlb_systems.xaf.swing.STableSelectionListener#selectionChanged(STableSelectionEvent)
         */
        @Override
        public void selectionChanged(STableSelectionEvent e) {
			if (e.isDoubleClicked()) {
				doEmployeeOpen(e.getRow());
			}			
        }

        /**
         * @see de.westlb_systems.xaf.swing.STableEditorListener#invokeEditor(STableEditorEvent)
         */
        @Override
        public void invokeEditor(STableEditorEvent e) {
			EmployeeListModel model = getEmployeeListModel();
			if (model == null) {
				return;
			}
			
        	if (STableEditorEvent.OPEN.equals(e.getActionCommand())) {
       			doEmployeeOpen(e.getRow());
        	} else if (STableEditorEvent.EDIT.equals(e.getActionCommand())) {
       			doEmployeeEdit(e.getRow());
        	} else if (STableEditorEvent.DELETE.equals(e.getActionCommand())) {
        		doEmployeeDelete(e.getRow());
        	} else if (STableEditorEvent.INSERT.equals(e.getActionCommand())) {
        		doEmployeeNew();
        	}
		}

	}
	
	/**
	 * Constructor for CheckStateView.
	 */
	public EmployeeListView() {
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
	public EmployeeListView(Model model) {
		this();
		setModel(model);
	}
	
	public boolean doEmployeeEdit(int row) {
		fireUserEvent(EV_EMPLOYEE_EDIT, getEmployeeListModel().getEmployeeId(row));	
		return true;
	}

	public boolean doEmployeeDelete(int row) {
		if (!showMessageBox(SHOW_QUESTION, getResourceString(RESOURCE_BASE + "Q_DELETE"))) {
			return false;
		};

		boolean success = false;
		// Ensure model to be uptodate
		propertyHandler.syncModel();

        boolean oldC = Synchronizer.setWaitCursor(true, this);
        if (getEmployeeListModel() != null) {
        	success = getEmployeeListModel().doDeleteEmployee(row);
        }        
        Synchronizer.setWaitCursor(oldC, this);

        if (!success && getModel() != null) {
			handleModelError();
        } 

		getModel().reload();
		fillView();
		tResult.setSelectedRow(0);
		return success;				
	}

	public boolean doEmployeeOpen(int row) {
	    if (row < 0) {
	        return false;
	    }
        fireUserEvent(EV_EMPLOYEE_OPEN, getEmployeeListModel().getEmployeeId(row));	
        return true;
	}

	public boolean doEmployeeNew() {
		fireUserEvent(EV_EMPLOYEE_NEW, getEmployeeListModel());	
		return true;
	}

	/**
	 * Starts the search
	 */
	public boolean doSearch() {
		boolean success = false;
		// Ensure model to be uptodate
		propertyHandler.syncModel();

        boolean oldC = Synchronizer.setWaitCursor(true, this);
        if (getEmployeeListModel() != null) {
        	success = getEmployeeListModel().doSearch();
        }        
        Synchronizer.setWaitCursor(oldC, this);

        if (!success && getModel() != null) {
            int error = getModel().getError();
            if ((error == Model.APPLICATION_ERROR) || (error == Model.DATABASE_ERROR)) {
                Object message = getModel().getErrorMessage();
                Object details = getModel().getErrorDetails();
                showMessageBox(SHOW_ERROR, message, details);
            }
        } 

		fillView();
		tResult.setSelectedRow(0);
		return success;
	}

    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (getEmployeeListModel() == null) {
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
	public EmployeeListModel getEmployeeListModel() {
		return getModel() instanceof EmployeeListModel ? (EmployeeListModel)getModel() : null;
	}


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(EmployeeListModel.P_FILTER_FIRST_NAME,			tfFirstName);	
		propertyHandler.add(EmployeeListModel.P_FILTER_LAST_NAME, 			tfLastName);
		propertyHandler.add(EmployeeListModel.P_FILTER_TRADER_CODE,			tfTraderId);
		propertyHandler.add(EmployeeListModel.P_SEARCH_RESULT_TABLE_MODEL, 	tResult);
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
    	this.setTitle			(getResourceString(RESOURCE_BASE + "T_001"));
		tpFilterPanel.setText	(getResourceString(RESOURCE_BASE + "T_FILTER"));
		tpResultPanel.setText	(getResourceString(RESOURCE_BASE + "T_RESULT"));

    	lFirstName.setText		(getResourceString(RESOURCE_BASE + "L_FIRST_NAME"));
    	lLastName.setText		(getResourceString(RESOURCE_BASE + "L_LAST_NAME"));
    	lTraderId.setText		(getResourceString(RESOURCE_BASE + "L_TRADER_ID"));
    }
    
    /**
     * Method initLayout.
     */
    private void initLayout() {
		int l, c;
		
		// Layout filterPanel
		l = 0; c = 0; // Row 0
	 	pFilterPanel.add(lLastName,		new GridBagConstraints2(c++, l, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));	 	
	 	pFilterPanel.add(lFirstName,	new GridBagConstraints2(c++, l, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));	 	
	 	pFilterPanel.add(lTraderId,		new GridBagConstraints2(c++, l, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8888, 0, 0));	 	

		l++; c = 0; 
	 	pFilterPanel.add(tfLastName,	new GridBagConstraints2(c++, l, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	pFilterPanel.add(tfFirstName,	new GridBagConstraints2(c++, l, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	pFilterPanel.add(tfTraderId,	new GridBagConstraints2(c++, l, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4888, 0, 0));	 	
		
				
		// Layout total view
		l = 0; c = 0; 
	 	this.add(pTitle,				new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets0000, 0, 0));	 	
	 	this.add(tpFilterPanel,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets8888, 0, 0));	 	
	 	this.add(tpResultPanel,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 1.0, CENTER, BOTH, insets8888, 0, 0));	 	

		setBackgroundForAllPanels(UIManager.getColor("DetailsPanel.background"));

    }	

    /**
     * Method initComponents.
     */
    private void initComponents() {
		pTitle.setHeaderTitleColor(UIManager.getColor("EmployeeListView.foreground"));
		pTitle.setColorProgress(
			UIManager.getColor("EmployeeListView.titleBackground1"), 
			UIManager.getColor("EmployeeListView.titleBackground2"));
			
		tpFilterPanel.setTable(pFilterPanel);
		tpResultPanel.setTable(tResult);
		tfFirstName.addKeyListener(listener);
		tfLastName.addKeyListener(listener);
		tfTraderId.addKeyListener(listener);
	
		tResult.setPopupProvider(
			STable.ADD_PROVIDER |
			STable.OPEN_PROVIDER|
			STable.EDIT_PROVIDER|
			STable.REMOVE_PROVIDER
		
		);
		tResult.addTableEditorListener(listener);
		tResult.addSTableSelectionListener(listener);
		tResult.setGridEnabled(true);

		setActions(new ActionSet[]{ actionEditEmployee });
    }

	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof EmployeeListModel)) {
	        throw new IllegalArgumentException("Model not instance of EmployeeListModel");
	    }
	    propertyHandler.setModel(newModel);
	    super.setModel(newModel);
	    doSearch();
	    fillView();
	}

     
     /**
      * Update the current content
      */
    @Override
    public void refresh() {
        fillView();
    }	
	/**
	 * Titel setzen
	 *
	 */
	@Override
    public void setTitle(String title) {
		pTitle.setHeaderTitel(title);
		super.setTitle(title);
	}

	
	/**
	 * Updates all language dependend labels
	 */
	@Override
    public void updateLabels() {
		initLabels();
	}
}
