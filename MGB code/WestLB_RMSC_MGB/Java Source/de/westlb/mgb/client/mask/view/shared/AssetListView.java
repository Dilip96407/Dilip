package de.westlb.mgb.client.mask.view.shared;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.derivative.DerivativeTradeOvAssetPanelModel;
import de.westlb.mgb.client.mask.model.shared.EmployeeListModel;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.STable;
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
public class AssetListView extends AbstractView {
	/**
     * 
     */
    private static final long serialVersionUID = 9193973940332715538L;

    private String RESOURCE_BASE = getResourceBase();

	/** gui components */
	private TitlePanel pTitle = new TitlePanel();
	private TablePanel tpResultPanel	= new TablePanel();
	private STable tResult				= new STable();
	
	/** Property handler to synchronize model and view */
	private PropertyHandler propertyHandler = new PropertyHandler();	

	
	/**
	 * Constructor for CheckStateView.
	 */
	public AssetListView() {
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
	public AssetListView(Model model) {
		this();
		setModel(model);
	}
	
    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (getAssetListModel() == null) {
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
	public DerivativeTradeOvAssetPanelModel getAssetListModel() {
		return getModel() instanceof DerivativeTradeOvAssetPanelModel ? (DerivativeTradeOvAssetPanelModel)getModel() : null;
	}


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(EmployeeListModel.P_SEARCH_RESULT_TABLE_MODEL, 	tResult);
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
		pTitle.setHeaderTitleColor(UIManager.getColor("EmployeeListView.foreground"));
		pTitle.setColorProgress(
			UIManager.getColor("EmployeeListView.titleBackground1"), 
			UIManager.getColor("EmployeeListView.titleBackground2"));
			
		tpResultPanel.setTable(tResult);

		tResult.setGridEnabled(true);

    }

	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof DerivativeTradeOvAssetPanelModel)) {
	        throw new IllegalArgumentException("Model not instance of AssetListModel");
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
