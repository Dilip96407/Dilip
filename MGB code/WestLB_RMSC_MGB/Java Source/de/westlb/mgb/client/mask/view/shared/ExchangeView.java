package de.westlb.mgb.client.mask.view.shared;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.shared.ExchangeModel;
import de.westlb_systems.xaf.application.ActionSet;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SButton;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.SPanel;
import de.westlb_systems.xaf.swing.STable;
import de.westlb_systems.xaf.swing.STableEditorEvent;
import de.westlb_systems.xaf.swing.STableEditorListener;
import de.westlb_systems.xaf.swing.STableSelectionEvent;
import de.westlb_systems.xaf.swing.STableSelectionListener;
import de.westlb_systems.xaf.swing.STextField;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.EditDialog;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.ui.view.TablePanel;
import de.westlb_systems.xaf.ui.view.TitlePanel;
import de.westlb_systems.xaf.util.Debug;

/**
 * @author WSY4148
 *
 * Dialog to enter a new manual state of a trade.
 */
public class ExchangeView extends AbstractView implements EditDialog {
	/**
     * 
     */
    private static final long serialVersionUID = -319491905698644222L;

    private String RESOURCE_BASE = getResourceBase();

	public final static int EV_EXCHANGE_EDIT		= 1;
	public final static int EV_MAPPING_NEW		= 2;
	public final static int EV_MAPPING_EDIT		= 3;
	
	private ActionSet
		actionNewTraderId = new ActionSet("NEW_EXCHANGE_MAPPING", true);
		
	/** gui components */
	private TitlePanel pTitle = new TitlePanel();

	private TablePanel tpBasicData		= new TablePanel();
	private TablePanel tpMappings		= new TablePanel();

	private STable tMappings			= new STable();

	/** pBasicData components */
	private SPanel pBasicData	 		= new SPanel();
	private SLabel lBloombergId		= new SLabel();
	private SLabel lReutersId			= new SLabel();
	private STextField	tfBloombergId	= new STextField(DEF_COL_20);
	private STextField	tfReutersId		= new STextField(DEF_COL_20);
	
	/** Button panel */
	private ButtonPanel pButtons 		= new ButtonPanel();
 	private SButton bEdit 				= new SButton();
	
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
        	if (e.getSource() == bEdit) {
        		doExchangeEdit();
        	}
        }

        /**
         * @see de.westlb_systems.xaf.swing.STableSelectionListener#selectionChanged(STableSelectionEvent)
         */
        @Override
        public void selectionChanged(STableSelectionEvent e) {
			if (e.isDoubleClicked()) {
				doMappingEdit(e.getRow());
			}			
        }

        /**
         * @see de.westlb_systems.xaf.swing.STableEditorListener#invokeEditor(STableEditorEvent)
         */
        @Override
        public void invokeEditor(STableEditorEvent e) {
			if (STableEditorEvent.DELETE == e.getActionCommand()) {
				doMappingDelete(e.getRow());
			}  else 
			
			if (STableEditorEvent.INSERT == e.getActionCommand()) {
				doMappingNew();
			} else       

			if (STableEditorEvent.EDIT == e.getActionCommand()) {
				doMappingEdit(e.getRow());
			}        

		}

	}
	
	/**
	 * Constructor for CheckStateView.
	 */
	public ExchangeView() {
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
	public ExchangeView(Model model) {
		this();
		setModel(model);
	}
	
	public boolean doExchangeEdit() {
		ExchangeModel model = getExchangeModel();
		if (model == null) {
			return false;
		}
		
		fireUserEvent(EV_EXCHANGE_EDIT, model.getExchangeId());		
		return true;
	}

	public boolean doMappingDelete(int row) {
		ExchangeModel model = getExchangeModel();
		if (model == null) {
			return false;
		}
		
		model.doDeleteMapping(row);
        model.reload();
    	fillView();
        int error = getModel().getError();
        if ((error == Model.APPLICATION_ERROR) || (error == Model.DATABASE_ERROR)) {
            Object message = getModel().getErrorMessage();
            Object details = getModel().getErrorDetails();
            showMessageBox(SHOW_ERROR, message, details);
            return false;
        }
		return true;
	}
	
	/**
	 * Method doMappingEdit.
	 * @param i
	 */
	private boolean doMappingEdit(int row) {
		ExchangeModel model = getExchangeModel();
		if (model == null) {
			return false;
		}

		fireUserEvent(EV_MAPPING_EDIT, model.getExchangeMappingVo(row));		
		return true;				
	}

	
	public boolean doMappingNew() {
		ExchangeModel model = getExchangeModel();
		if (model == null) {
			return false;
		}
		
		fireUserEvent(EV_MAPPING_NEW, model.getExchangeId());
		return true;
	}

    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (getExchangeModel() == null) {
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
	public ExchangeModel getExchangeModel() {
		return getModel() instanceof ExchangeModel ? (ExchangeModel)getModel() : null;
	}


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(ExchangeModel.P_BLOOMBERG_ID,			tfBloombergId);	
		propertyHandler.add(ExchangeModel.P_REUTERS_ID, 			tfReutersId);
		propertyHandler.add(ExchangeModel.P_MAPPING_TABLE_MODEL, 	tMappings);	
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
    	this.setTitle			(getResourceString(RESOURCE_BASE + "T_001"));
    	tpBasicData.setText		(getResourceString(RESOURCE_BASE + "T_BASICDATA"));	
    	tpMappings.setText		(getResourceString(RESOURCE_BASE + "T_MAPPINGS"));	

    	lBloombergId.setText	(getResourceString(RESOURCE_BASE + "L_BLOOMBERG_ID"));
    	lReutersId.setText		(getResourceString(RESOURCE_BASE + "L_REUTERS_ID"));

    	bEdit.setText			(getResourceString(RESOURCE_BASE + "B_EDIT"));
    }
    
    /**
     * Method initLayout.
     */
    private void initLayout() {
		int l, c;

		pButtons.addButton(bEdit);
		
		// Layout basic data panel
		l = 0; c = 0; // Row 0
	 	pBasicData.add(lBloombergId,	new GridBagConstraints2(c++, l, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));	 	
	 	pBasicData.add(lReutersId,		new GridBagConstraints2(c++, l, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8808, 0, 0));	 	
		
		l++; c = 0; 
	 	pBasicData.add(tfBloombergId,	new GridBagConstraints2(c++, l, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4880, 0, 0));	 	
	 	pBasicData.add(tfReutersId,		new GridBagConstraints2(c++, l, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4888, 0, 0));	 	
		
				
		// Layout total view
		l = 0; c = 0; 
	 	this.add(pTitle,				new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets0000, 0, 0));	 	
	 	this.add(tpBasicData,			new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets8808, 0, 0));	 	
	 	this.add(tpMappings,			new GridBagConstraints2(c, l++, 1, 1, 1.0, 1.0, CENTER, BOTH,  insets8808, 0, 0));	 	
	 	this.add(pButtons,				new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets8888, 0, 0));	 	

		setReadOnly();
		setBackgroundForAllPanels(UIManager.getColor("DetailsPanel.background"));

    }	

    /**
     * Method initComponents.
     */
    private void initComponents() {
		pTitle.setHeaderTitleColor(UIManager.getColor("EmployeeListView.foreground"));
		pTitle.setColorProgress(
			UIManager.getColor("EmployeeOverviewView.titleBackground1"), 
			UIManager.getColor("EmployeeListView.titleBackground2"));
			
		tpBasicData.setTable(pBasicData);
		tpMappings.setTable(tMappings);

		tMappings.setPopupProvider(STable.ADD_PROVIDER|STable.EDIT_PROVIDER|STable.REMOVE_PROVIDER);
		tMappings.addTableEditorListener(listener);
		tMappings.addSTableSelectionListener(listener);
		tMappings.setGridEnabled(true);

		bEdit.addActionListener(listener);

		setActions(new ActionSet[]{ actionNewTraderId });
    }

	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof ExchangeModel)) {
	        throw new IllegalArgumentException("Model not instance of ExchangeModel");
	    }
	    propertyHandler.setModel(newModel);
	    super.setModel(newModel);
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
