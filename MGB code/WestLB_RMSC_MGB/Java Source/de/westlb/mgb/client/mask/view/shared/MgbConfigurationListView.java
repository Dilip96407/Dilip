package de.westlb.mgb.client.mask.view.shared;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.shared.MgbConfigurationListModel;
import de.westlb_systems.xaf.application.ActionSet;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SMenuItem;
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
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MgbConfigurationListView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8284142797131765728L;

	private String RESOURCE_BASE = getResourceBase();

	public final static int EV_OPEN 	= 0;
	public final static int EV_EDIT 	= 1;
	public final static int EV_NEW		= 2;
	
	private SMenuItem pItemImport	= new SMenuItem();	
	
	private ActionSet
		actionEdit = new ActionSet("EDIT_MGB_CONFIGURATION", true);
		
	/** gui components */
	private TitlePanel pTitle 			= new TitlePanel();
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
			if (getPriceCheckCategoryListModel() == null) {
				return;
			}
			
        	if (STableEditorEvent.OPEN.equals(e.getActionCommand())) {
       			doOpen(e.getRow());
        	} else if (STableEditorEvent.EDIT.equals(e.getActionCommand())) {
       			doEdit(e.getRow());
        	} else if (STableEditorEvent.INSERT.equals(e.getActionCommand())) {
        		doNew();
        	}
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
		 */
		@Override
        public void actionPerformed(ActionEvent e) {
		}

	}
	
	/**
	 * Constructor for CheckStateView.
	 */
	public MgbConfigurationListView() {
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
	public MgbConfigurationListView(Model model) {
		this();
		setModel(model);
	}
	
	public boolean doEdit(int row) {
	    if (row < 0) {
	        return false;
	    }
        fireUserEvent(EV_EDIT, getPriceCheckCategoryListModel().getObject(row));	
        return true;
	}


	public boolean doOpen(int row) {
	    if (row < 0) {
	        return false;
	    }
        fireUserEvent(EV_OPEN, getPriceCheckCategoryListModel().getObject(row));	
        return true;
	}
	

	public boolean doNew() {
		fireUserEvent(EV_NEW, getPriceCheckCategoryListModel());	
		return true;
	}


    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (getPriceCheckCategoryListModel() == null) {
            return;
        }
        propertyHandler.syncFields();
    }
    
    /**
	 * Return casted model
	 * 
	 */
	public MgbConfigurationListModel getPriceCheckCategoryListModel() {
		return getModel() instanceof MgbConfigurationListModel ? (MgbConfigurationListModel)getModel() : null;
	}


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(MgbConfigurationListModel.P_SEARCH_RESULT_TABLE_MODEL, 	tResult);
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
    	this.setTitle			(getResourceString(RESOURCE_BASE + "T_001"));
		tpResultPanel.setText	(getResourceString(RESOURCE_BASE + "T_RESULT"));
		pItemImport.setText		(getResourceString(RESOURCE_BASE + "B_IMPORT"));
    }
    
    /**
     * Method initLayout.
     */
    private void initLayout() {
		int l, c;
					
		// Layout total view
		l = 0; c = 0; 
	 	this.add(pTitle,			new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets0000, 0, 0));	 	
	 	this.add(tpResultPanel,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 1.0, CENTER, BOTH, insets8888, 0, 0));	 	

		setBackgroundForAllPanels(UIManager.getColor("DetailsPanel.background"));

    }	

    /**
     * Method initComponents.
     */
    private void initComponents() {
		pTitle.setHeaderTitleColor(UIManager.getColor("PriceCheckCategoryListView.foreground"));
		pTitle.setColorProgress(
			UIManager.getColor("PriceCheckCategoryListView.titleBackground1"), 
			UIManager.getColor("PriceCheckCategoryListView.titleBackground2"));

		pItemImport.addActionListener(listener);
					
		tpResultPanel.setTable(tResult);
		tResult.setPopupProvider(
			STable.ADD_PROVIDER |
			STable.EDIT_PROVIDER		
		);
		
		tResult.addTableEditorListener(listener);
		tResult.addSTableSelectionListener(listener);
		tResult.setGridEnabled(true);

		setActions(new ActionSet[]{ actionEdit });
    }
    
    @SuppressWarnings("unused")
    private boolean isImportEnabled() {
    	Boolean isImportEnabled = (Boolean)UIManager.get("PriceCheckCategoryListView.isImportEnabled");
    	return Boolean.TRUE.equals(isImportEnabled);
    }

	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof MgbConfigurationListModel)) {
	        throw new IllegalArgumentException("Model not instance of MgbConfigurationListView");
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
