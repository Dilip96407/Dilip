package de.westlb.mgb.client.mask.view.shared;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.shared.ReclamationEmployeeListModel;
import de.westlb_systems.xaf.application.event.LogMessageEvent;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SButton;
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
 * Dialog to enter a new manual state of a trade.
 */
public class ReclamationEmployeeListView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5685174220067159980L;

	public String RESOURCE_BASE = getResourceBase();
	
	public static final int EV_PREVIEW	= 1;
	public static final int EV_REMARK_RECL = 2;
		
	/** gui components */
	private TitlePanel pTitle = new TitlePanel();
	private TablePanel tpEmployees	= new TablePanel();
	private STable tEmployees			= new STable();

	private ButtonPanel pButtons 		= new ButtonPanel();
 	private SButton bPreview			= new SButton();
 	private SButton bRemarkReclamation   = new SButton();

	private SMenuItem pItemPreview	= new SMenuItem();	
	private SMenuItem pItemCloseReclamation	= new SMenuItem();	
	
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
        	if (e.getSource() == bRemarkReclamation || e.getSource() == pItemCloseReclamation) {
        		doCloseReclamation();
        	} else if (e.getSource() == bPreview || e.getSource() == pItemPreview) {
        		doPreview();
        	}
        }

        /**
         * @see de.westlb_systems.xaf.swing.STableSelectionListener#selectionChanged(STableSelectionEvent)
         */
        @Override
        public void selectionChanged(STableSelectionEvent e) {
			if (e.isDoubleClicked()) {
				doPreview();
			}			
        }

        /**
         * @see de.westlb_systems.xaf.swing.STableEditorListener#invokeEditor(STableEditorEvent)
         */
        @Override
        public void invokeEditor(STableEditorEvent e) {
			ReclamationEmployeeListModel model = getReclamationEmployeeListModel();
			if (model == null) {
				return;
			}
			
        	if (STableEditorEvent.OPEN.equals(e.getActionCommand())) {
       			doPreview();
        	} 
		}

	}
	
	/**
	 * Constructor for CheckStateView.
	 */
	public ReclamationEmployeeListView() {
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
	public ReclamationEmployeeListView(Model model) {
		this();
		setModel(model);
	}
	
	public int[] getSelectedRows() {
		return tEmployees.getSelectedRows();	
	}
	
	public boolean doPreview() {
		// If no row selected (may be table is empty) log error message
		if (getSelectedRows().length < 1) {
			logMessage(LogMessageEvent.ERROR, getResourceString(RESOURCE_BASE + "E_001"));
			return false;
		}		
		
		fireUserEvent(EV_PREVIEW, this);	
		return true;
	}

	public boolean doCloseReclamation() {
		// If no row selected (may be table is empty) log error message
		if (getSelectedRows().length < 1) {
			logMessage(LogMessageEvent.ERROR, getResourceString(RESOURCE_BASE + "E_001"));
			return false;
		}		
		
		fireUserEvent(EV_REMARK_RECL, this);	
		return true;
	}

    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (getReclamationEmployeeListModel() == null) {
            return;
        }
        propertyHandler.syncFields();
    }
    
    /**
	 * Return casted model
	 */
	public ReclamationEmployeeListModel getReclamationEmployeeListModel() {
		return getModel() instanceof ReclamationEmployeeListModel ? (ReclamationEmployeeListModel)getModel() : null;
	}


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(ReclamationEmployeeListModel.P_EMPLOYEE_TABLE_MODEL, 	tEmployees);
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
    	this.setTitle					(getResourceString(RESOURCE_BASE + "T_001"));
		tpEmployees.setText				(getResourceString(RESOURCE_BASE + "T_EMPLOYEE_LIST"));
    	bPreview.setText				(getResourceString(RESOURCE_BASE + "B_PREVIEW"));
    	bRemarkReclamation.setText		(getResourceString(RESOURCE_BASE + "B_REMARK_RECL"));
		pItemPreview.setText			(bPreview.getText() );
		pItemCloseReclamation.setText	(bRemarkReclamation.getText() );
    }
    
    /**
     * Method initLayout.
     */
    private void initLayout() {
		int l, c;
		
		// Layout total view
		l = 0; c = 0; 
	 	this.add(pTitle,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets0000, 0, 0));	 	
	 	this.add(tpEmployees,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 1.0, CENTER, BOTH, insets8808, 0, 0));	 	
	 	this.add(pButtons,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets8888, 0, 0));	 	

		setBackgroundForAllPanels(UIManager.getColor("DetailsPanel.background"));

    }	

    /**
     * Method initComponents.
     */
    private void initComponents() {
		pTitle.setHeaderTitleColor(UIManager.getColor("SendReclamationListView.foreground"));
		pTitle.setColorProgress(
			UIManager.getColor("SendReclamationListView.titleBackground1"), 
			UIManager.getColor("SendReclamationListView.titleBackground2"));
			
		tpEmployees.setTable(tEmployees);
	
		tEmployees.setPopupProvider(
			STable.OPEN_PROVIDER		
		);
		
		tEmployees.addTableEditorListener(listener);
		tEmployees.addSTableSelectionListener(listener);
		tEmployees.setGridEnabled(true);
		tEmployees.setSelectionMode(STable.MULTIPLE_SELECTION);

		bPreview.addActionListener(listener);
		bRemarkReclamation.addActionListener(listener);
		pButtons.addButton(bPreview);
		pButtons.addButton(bRemarkReclamation);

		// Initialize table popup menu
		pItemPreview.addActionListener(listener);
		pItemCloseReclamation.addActionListener(listener);
		SPopupProvider provider = tEmployees.getPopupProvider();
		SPopupMenu menu = provider.getPopupMenu();
		Component[] items = menu.getComponents();	
		menu.removeAll();	
		menu.add(pItemPreview);
		menu.add(pItemCloseReclamation);
		menu.addSeparator();
		if (items!=null) {
			for (int i=0;i<items.length;i++) {
				menu.add(items[i]);
			}
		}
		// setActions(new ActionSet[]{ actionEditEmployee });
    }

	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof ReclamationEmployeeListModel)) {
	        throw new IllegalArgumentException("Model not instance of SendReclamationListModel");
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
