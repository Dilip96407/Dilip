package de.westlb.mgb.client.mask.view.shared;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.shared.AddonListModel;
import de.westlb.mgb.client.mask.model.shared.StornoGroupModel;
import de.westlb.mgb.client.ui.idlistmodel.TradeIdListModel;
import de.westlb_systems.xaf.application.ActionSet;
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
 * The view shows a list of all addons.
 */
public class StornoGroupView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5885804761220715020L;

	private String RESOURCE_BASE = getResourceBase();

	public final static int EV_OPEN 	= 0;
	public final static int EV_NEW	= 2;
	
	
	private ActionSet
		actionEdit = new ActionSet("EDIT_ADDON", true);
		
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
			StornoGroupModel model = getStornoGroupModel();
			if (model == null) {
				return;
			}
			
        	if (STableEditorEvent.OPEN.equals(e.getActionCommand())) {
       			doOpen(e.getRow());
        	} 
		}

	}
	
	/**
	 * Constructor for CheckStateView.
	 */
	public StornoGroupView() {
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
	public StornoGroupView(Model model) {
		this();
		setModel(model);
	}
	
	public boolean doOpen(int row) {
	    Long id = getStornoGroupModel().getTradeId(row);
	    if (id != null) {
	        TradeIdListModel idListModel = new TradeIdListModel(new Object[] {id});
	        idListModel.moveTo(0);
	        fireUserEvent(EV_OPEN, idListModel);
	    }
		return true;
	}

    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (getStornoGroupModel() == null) {
            return;
        }
        propertyHandler.syncFields();
    }
    
    /**
	 * Return casted model
	 * 
	 */
	public StornoGroupModel getStornoGroupModel() {
		return getModel() instanceof StornoGroupModel ? (StornoGroupModel)getModel() : null;
	}


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(AddonListModel.P_SEARCH_RESULT_TABLE_MODEL, 	tResult);
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
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
			
		tpResultPanel.setTable(tResult);
	
		tResult.setPopupProvider(
			STable.OPEN_PROVIDER
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
	    if (!(newModel instanceof StornoGroupModel)) {
	        throw new IllegalArgumentException("Model not instance of StornoGroupModel");
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
	 * Updates all language dependend labels
	 */
	@Override
    public void updateLabels() {
		initLabels();
	}
}
