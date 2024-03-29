package de.westlb.mgb.client.mask.view.shared;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.shared.ExchangeListModel;
import de.westlb_systems.xaf.application.ActionSet;
import de.westlb_systems.xaf.application.Synchronizer;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.STable;
import de.westlb_systems.xaf.swing.STableEditorEvent;
import de.westlb_systems.xaf.swing.STableEditorListener;
import de.westlb_systems.xaf.swing.STableSelectionEvent;
import de.westlb_systems.xaf.swing.STableSelectionListener;
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
public class ExchangeListView extends AbstractView {
	/**
     * 
     */
    private static final long serialVersionUID = -989335494434033442L;

    private String RESOURCE_BASE = getResourceBase();

	public final static int EV_OPEN 	= 0;
	public final static int EV_EDIT 	= 1;
	public final static int EV_NEW	= 2;
	
	
	private ActionSet
		actionEdit = new ActionSet("EDIT_EXCHANGE", true);
		
	/** gui components */
	private TitlePanel pTitle = new TitlePanel();
	private TablePanel tpResultPanel	= new TablePanel();
	private STable tResult				= new STable();
	
	/** Property handler to synchronize model and view */
	private PropertyHandler propertyHandler = new PropertyHandler();	

	/** Listener fuer alle Events */
	private Listener listener = new Listener();
	
	/**
	 * Listener fuer alle Events
	 */
	private class Listener implements STableSelectionListener, STableEditorListener, ActionListener {
        /**
         * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
        }
        /**
         * @see de.westlb_systems.xaf.swing.STableSelectionListener#selectionChanged(STableSelectionEvent)
         */
        @Override
        public void selectionChanged(STableSelectionEvent e) {
			if (e.isDoubleClicked()) {
				doOpen(e.getRow());
			}			
        }

        /**
         * @see de.westlb_systems.xaf.swing.STableEditorListener#invokeEditor(STableEditorEvent)
         */
        @Override
        public void invokeEditor(STableEditorEvent e) {
			ExchangeListModel model = getExchangeListModel();
			if (model == null) {
				return;
			}
			
        	if (STableEditorEvent.OPEN.equals(e.getActionCommand())) {
       			doOpen(e.getRow());
        	} else if (STableEditorEvent.EDIT.equals(e.getActionCommand())) {
       			doEdit(e.getRow());
        	} else if (STableEditorEvent.DELETE.equals(e.getActionCommand())) {
        		doDelete(e.getRow());
        	} else if (STableEditorEvent.INSERT.equals(e.getActionCommand())) {
        		doNew();
        	}
		}

	}
	
	/**
	 * Constructor for CheckStateView.
	 */
	public ExchangeListView() {
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
	public ExchangeListView(Model model) {
		this();
		setModel(model);
	}
	
	public boolean doEdit(int row) {
	    if (row < 0) {
	        return false;
	    }
        fireUserEvent(EV_EDIT, getExchangeListModel().getId(row));	
        return true;
	}

	public boolean doDelete(int row) {
		if (row < 0 || !showMessageBox(SHOW_QUESTION, getResourceString(RESOURCE_BASE + "Q_DELETE"))) {
			return false;
		};

		boolean success = false;
		// Ensure model to be uptodate
		propertyHandler.syncModel();

        boolean oldC = Synchronizer.setWaitCursor(true, this);
        if (getExchangeListModel() != null) {
        	success = getExchangeListModel().doDelete(row);
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

	public boolean doOpen(int row) {
	    if (row < 0) {
	        return false;
	    }
        fireUserEvent(EV_OPEN, getExchangeListModel().getId(row));	
        return true;
	}

	public boolean doNew() {
		fireUserEvent(EV_NEW, getExchangeListModel());	
		return true;
	}


    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (getExchangeListModel() == null) {
            return;
        }
        propertyHandler.syncFields();
    }
    
    /**
	 * Return casted model
	 * 
	 */
	public ExchangeListModel getExchangeListModel() {
		return getModel() instanceof ExchangeListModel ? (ExchangeListModel)getModel() : null;
	}


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(ExchangeListModel.P_SEARCH_RESULT_TABLE_MODEL, 	tResult);
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
    	this.setTitle			(getResourceString(RESOURCE_BASE + "T_001"));
		tpResultPanel.setText	(getResourceString(RESOURCE_BASE + "T_RESULT"));
    }
    
    /**
     * Method initLayout.
     */
    private void initLayout() {
		int l, c;
					
		// Layout total view
		l = 0; c = 0; 
	 	this.add(pTitle,				new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets0000, 0, 0));	 	
	 	this.add(tpResultPanel,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 1.0, CENTER, BOTH, insets8888, 0, 0));	 	

		setBackgroundForAllPanels(UIManager.getColor("DetailsPanel.background"));

    }	

    /**
     * Method initComponents.
     */
    private void initComponents() {
		pTitle.setHeaderTitleColor(UIManager.getColor("StateCodeList.foreground"));
		pTitle.setColorProgress(
			UIManager.getColor("StateCodeList.titleBackground1"), 
			UIManager.getColor("StateCodeList.titleBackground2"));
			
		tpResultPanel.setTable(tResult);
	
		tResult.setPopupProvider(
			STable.ADD_PROVIDER |
			STable.OPEN_PROVIDER|
			STable.EDIT_PROVIDER|
			STable.REMOVE_PROVIDER
		
		);
		tResult.addTableEditorListener(listener);
		tResult.addSTableSelectionListener(listener);
		tResult.setGridEnabled(true);

		setActions(new ActionSet[]{ actionEdit });
    }

	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof ExchangeListModel)) {
	        throw new IllegalArgumentException("Model not instance of ExchangeListModel");
	    }
	    propertyHandler.setModel(newModel);
	    super.setModel(newModel);
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
