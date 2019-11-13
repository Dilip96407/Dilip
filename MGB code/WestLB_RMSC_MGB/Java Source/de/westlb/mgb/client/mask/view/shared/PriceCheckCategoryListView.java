package de.westlb.mgb.client.mask.view.shared;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.shared.PriceCheckCategoryListModel;
import de.westlb.mgb.client.ui.loader.MmkPriceCheckCategoryLoader;
import de.westlb.mgb.client.ui.util.poi.PoiLoader;
import de.westlb.mgb.client.ui.util.poi.PoiReader;
import de.westlb_systems.xaf.application.ActionSet;
import de.westlb_systems.xaf.application.Synchronizer;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SFileDialog;
import de.westlb_systems.xaf.swing.SMenuItem;
import de.westlb_systems.xaf.swing.SPopupMenu;
import de.westlb_systems.xaf.swing.SPopupProvider;
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
 * The view shows a list of all addons.
 */
public class PriceCheckCategoryListView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 18962014211961241L;

	private String RESOURCE_BASE = getResourceBase();

	public final static int EV_OPEN 	= 0;
	public final static int EV_EDIT 	= 1;
	public final static int EV_NEW		= 2;
	
	private SMenuItem pItemImport	= new SMenuItem();	
	
	private ActionSet
		actionEdit = new ActionSet("EDIT_PRICE_CHECK_CATEGORY", true);
		
	/** gui components */
	private TitlePanel pTitle 			= new TitlePanel();
	private TablePanel tpResultPanel	= new TablePanel();
	private STable tResult				= new STable();
	
	/** Property handler to synchronize model and view */
	private PropertyHandler propertyHandler = new PropertyHandler();	

	/** Listener fuer alle Events */
	private Listener listener = new Listener();

	private SFileDialog fileDialog = null;
	private static String dialogPath = null;

	
	/**
	 * Listener fuer alle Events
	 */
	private class Listener implements STableSelectionListener, STableEditorListener, ActionListener {
        /**
         * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
        	if (e.getSource() == pItemImport) {
        		doImport();
        	}
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
			if (getPriceCheckCategoryListModel() == null) {
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
	public PriceCheckCategoryListView() {
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
	public PriceCheckCategoryListView(Model model) {
		this();
		setModel(model);
	}
	
	public boolean doEdit(int row) {
	    if (row < 0) {
	        return false;
	    }
        fireUserEvent(EV_EDIT, getPriceCheckCategoryListModel().getId(row));	
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
        if (getPriceCheckCategoryListModel() != null) {
        	success = getPriceCheckCategoryListModel().doDelete(row);
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
        fireUserEvent(EV_OPEN, getPriceCheckCategoryListModel().getId(row));	
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
	public PriceCheckCategoryListModel getPriceCheckCategoryListModel() {
		return getModel() instanceof PriceCheckCategoryListModel ? (PriceCheckCategoryListModel)getModel() : null;
	}


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(PriceCheckCategoryListModel.P_SEARCH_RESULT_TABLE_MODEL, 	tResult);
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
			STable.EDIT_PROVIDER|
			STable.REMOVE_PROVIDER
		
		);
		
		// The import functionality is for money market mandant implemented only
		if (isImportEnabled()) {
			SPopupProvider provider = tResult.getPopupProvider();
			SPopupMenu menu = provider.getPopupMenu();
//			Component[] items = menu.getComponents();	
			menu.addSeparator();
			menu.add(pItemImport);
		}

		tResult.addTableEditorListener(listener);
		tResult.addSTableSelectionListener(listener);
		tResult.setGridEnabled(true);

		setActions(new ActionSet[]{ actionEdit });
    }
    
    private boolean isImportEnabled() {
    	Boolean isImportEnabled = (Boolean)UIManager.get("PriceCheckCategoryListView.isImportEnabled");
    	return Boolean.TRUE.equals(isImportEnabled);
    }

	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof PriceCheckCategoryListModel)) {
	        throw new IllegalArgumentException("Model not instance of PriceCheckCategoryListView");
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

	private void doImport() {
		if (fileDialog == null) {
			fileDialog = new SFileDialog(this);
		}

		fileDialog.setDirectory(dialogPath);
		fileDialog.setMode(SFileDialog.LOAD);
		fileDialog.setTitle(getResourceString(RESOURCE_BASE + "T_FILE"));
		fileDialog.show();

		if (fileDialog.getFile() != null) {
			dialogPath = fileDialog.getDirectory();
			File file = new File(fileDialog.getDirectory(), fileDialog.getFile());
			if (file.canRead()) {
				String filepath = file.getPath();
				
				try {
					PoiLoader loader = new MmkPriceCheckCategoryLoader();
					PoiReader poiReader = new PoiReader(loader);
					poiReader.read(filepath);
					
					if (poiReader.getErrorCount() == 0) {
						showMessageBox(SHOW_INFO, poiReader.getResultMessage());
					} else {
						showMessageBox(SHOW_ERROR, poiReader.getResultMessage(), poiReader.getErrorDetails());
					}
				} catch (Exception e) {
					showError(e);
				}
				
				// Synchronize model, because propertyHandler updates the model
				// only on focusLost-Events
				propertyHandler.syncModel();
			} else {
				logMessage(SHOW_ERROR, getResourceString(RESOURCE_BASE + "E_004"));
				return;
			}

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
