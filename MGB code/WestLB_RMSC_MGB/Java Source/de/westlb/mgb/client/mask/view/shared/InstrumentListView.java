package de.westlb.mgb.client.mask.view.shared;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.shared.InstrumentListModel;
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
public class InstrumentListView extends AbstractView {
	/**
     * 
     */
    private static final long serialVersionUID = 3420748256225346682L;

    private String RESOURCE_BASE = getResourceBase();

	public final static int EV_OPEN 	= 1;
	public final static int EV_EDIT 	= 2;
		
	private ActionSet
		actionEditInstrument = new ActionSet("EDIT_INSTRUMENT", true);
		
	/** gui components */
	private TitlePanel pTitle = new TitlePanel();
	private TablePanel tpFilterPanel	= new TablePanel();
	private TablePanel tpResultPanel	= new TablePanel();
	private SPanel pFilterPanel 		= new SPanel();
	private STable tResult				= new STable();
	
	private SLabel lIsin			= new SLabel();	
	
	private STextField tfIsin		= new STextField();
	
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
				doOpen(e.getRow());
			}			
        }

        /**
         * @see de.westlb_systems.xaf.swing.STableEditorListener#invokeEditor(STableEditorEvent)
         */
        @Override
        public void invokeEditor(STableEditorEvent e) {			
        	if (STableEditorEvent.OPEN.equals(e.getActionCommand())) {
       			doOpen(e.getRow());
        	} else if (STableEditorEvent.EDIT.equals(e.getActionCommand())) {
       			doEdit(e.getRow());
        	}
		}

	}
	
	/**
	 * Constructor for CheckStateView.
	 */
	public InstrumentListView() {
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
	public InstrumentListView(Model model) {
		this();
		setModel(model);
	}
	
	public boolean doEdit(int row) {
	    if (row < 0) {
	        return false;
	    }
        fireUserEvent(EV_EDIT, getInstrumentListModel().getInstrumentId(row));	
        return true;
	}

	public boolean doOpen(int row) {
	    if (row < 0) {
	        return false;
	    }
        fireUserEvent(EV_OPEN, getInstrumentListModel().getInstrumentId(row));	
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
        if (getInstrumentListModel() != null) {
        	success = getInstrumentListModel().doSearch();
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
        if (getInstrumentListModel() == null) {
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
	public InstrumentListModel getInstrumentListModel() {
		return getModel() instanceof InstrumentListModel ? (InstrumentListModel)getModel() : null;
	}


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(InstrumentListModel.P_FILTER_ISIN,			tfIsin);	
		propertyHandler.add(InstrumentListModel.P_SEARCH_RESULT_TABLE_MODEL, 	tResult);
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
    	this.setTitle			(getResourceString(RESOURCE_BASE + "T_001"));
		tpFilterPanel.setText	(getResourceString(RESOURCE_BASE + "T_FILTER"));
		tpResultPanel.setText	(getResourceString(RESOURCE_BASE + "T_RESULT"));

    	lIsin.setText			(getResourceString(RESOURCE_BASE + "L_ISIN"));
    }
    
    /**
     * Method initLayout.
     */
    private void initLayout() {
		int l, c;
		
		// Layout filterPanel
		l = 0; c = 0; // Row 0
	 	pFilterPanel.add(lIsin,	new GridBagConstraints2(c++, l, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8888, 0, 0));	 	

		l++; c = 0; 
	 	pFilterPanel.add(tfIsin,	new GridBagConstraints2(c++, l, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4888, 0, 0));	 	
				
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
		pTitle.setHeaderTitleColor(UIManager.getColor("InstrumentListView.foreground"));
		pTitle.setColorProgress(
			UIManager.getColor("InstrumentListView.titleBackground1"), 
			UIManager.getColor("InstrumentListView.titleBackground2"));
			
		tpFilterPanel.setTable(pFilterPanel);
		tpResultPanel.setTable(tResult);
		tfIsin.addKeyListener(listener);
	
		tResult.setPopupProvider(
			STable.OPEN_PROVIDER|
			STable.EDIT_PROVIDER
		);
		tResult.addTableEditorListener(listener);
		tResult.addSTableSelectionListener(listener);
		tResult.setGridEnabled(true);

		setActions(new ActionSet[]{ actionEditInstrument });
    }

	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof InstrumentListModel)) {
	        throw new IllegalArgumentException("Model not instance of InstrumentListModel");
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
