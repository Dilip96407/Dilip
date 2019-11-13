package de.westlb.mgb.client.mask.view.shared;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.shared.EmployeeOverviewModel;
import de.westlb_systems.xaf.application.ActionSet;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SButton;
import de.westlb_systems.xaf.swing.SCheckBox;
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
public class EmployeeOverviewView extends AbstractView implements EditDialog {
	/**
     * 
     */
    private static final long serialVersionUID = -8138564825546084471L;

    private String RESOURCE_BASE = getResourceBase();

	public final static int EV_EMPLOYEE_EDIT		= 1;
	public final static int EV_TRADERCODE_NEW	= 2;
	
	private ActionSet
		actionNewTraderId = new ActionSet("NEW_TRADER_ID", true);
		
	/** gui components */
	private TitlePanel pTitle = new TitlePanel();

	private TablePanel tpBasicData		= new TablePanel();
	private TablePanel tpTraderIds		= new TablePanel();

	private STable tTraderIds			= new STable();

	/** pBasicData components */
	private SPanel pBasicData	 		= new SPanel();
	private SLabel lTrader				= new SLabel();
	private SLabel lEMail				= new SLabel();
	private SLabel lFirstName			= new SLabel();
	private SLabel lLastName			= new SLabel();
	private SLabel lNtId				= new SLabel();
	private SLabel lTelefon				= new SLabel();
	
	private STextField	tfEMail			= new STextField(DEF_COL_20);
	private STextField	tfFirstName		= new STextField(DEF_COL_20);
	private STextField	tfLastName		= new STextField(DEF_COL_20);
	private STextField	tfNtId			= new STextField(DEF_COL_20);
	private STextField	tfTelefon		= new STextField(DEF_COL_20);
	private SCheckBox	ckTrader		= new SCheckBox();
	
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
        		doEmployeeEdit();
        	}
        }

        /**
         * @see de.westlb_systems.xaf.swing.STableSelectionListener#selectionChanged(STableSelectionEvent)
         */
        @Override
        public void selectionChanged(STableSelectionEvent e) {
			if (e.isDoubleClicked()) {
			}			
        }

        /**
         * @see de.westlb_systems.xaf.swing.STableEditorListener#invokeEditor(STableEditorEvent)
         */
        @Override
        public void invokeEditor(STableEditorEvent e) {
			if (STableEditorEvent.DELETE == e.getActionCommand()) {
				doTraderCodeDelete(e.getRow());
			}       
		}

	}
	
	/**
	 * Constructor for CheckStateView.
	 */
	public EmployeeOverviewView() {
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
	public EmployeeOverviewView(Model model) {
		this();
		setModel(model);
	}
	
	public boolean doEmployeeEdit() {
		EmployeeOverviewModel model = getEmployeeOverviewModel();
		if (model == null) {
			return false;
		}
		
		fireUserEvent(EV_EMPLOYEE_EDIT, model.getEmployeeId());		
		return true;
	}

	public boolean doTraderCodeDelete(int row) {
		EmployeeOverviewModel model = getEmployeeOverviewModel();
		if (model == null || row < 0) {
			return false;
		}
		
		model.doDeleteTrader(row);
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
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (getEmployeeOverviewModel() == null) {
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
	public EmployeeOverviewModel getEmployeeOverviewModel() {
		return getModel() instanceof EmployeeOverviewModel ? (EmployeeOverviewModel)getModel() : null;
	}


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(EmployeeOverviewModel.P_EMAIL,			tfEMail);	
		propertyHandler.add(EmployeeOverviewModel.P_FIRST_NAME, 	tfFirstName);
		propertyHandler.add(EmployeeOverviewModel.P_LAST_NAME,		tfLastName);
		propertyHandler.add(EmployeeOverviewModel.P_NT_ID,			tfNtId);
		propertyHandler.add(EmployeeOverviewModel.P_TELEFON,		tfTelefon);
		propertyHandler.add(EmployeeOverviewModel.P_TRADER,			ckTrader);
		propertyHandler.add(EmployeeOverviewModel.P_TRADER_IDS_TABLE_MODEL,		tTraderIds);
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
    	this.setTitle			(getResourceString(RESOURCE_BASE + "T_001"));
    	tpBasicData.setText		(getResourceString(RESOURCE_BASE + "T_BASICDATA"));	
    	tpTraderIds.setText		(getResourceString(RESOURCE_BASE + "T_TRADER_IDS"));	

    	lTrader.setText			(getResourceString(RESOURCE_BASE + "L_TRADER"));
    	lEMail.setText			(getResourceString(RESOURCE_BASE + "L_EMAIL"));
    	lFirstName.setText		(getResourceString(RESOURCE_BASE + "L_FIRST_NAME"));
    	lLastName.setText		(getResourceString(RESOURCE_BASE + "L_LAST_NAME"));
    	lNtId.setText			(getResourceString(RESOURCE_BASE + "L_NTID"));
    	lTelefon.setText		(getResourceString(RESOURCE_BASE + "L_TELEFON"));
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
	 	pBasicData.add(lLastName,		new GridBagConstraints2(c++, l, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));	 	
	 	pBasicData.add(lFirstName,		new GridBagConstraints2(c++, l, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));	 	
	 	pBasicData.add(lTelefon,		new GridBagConstraints2(c++, l, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8888, 0, 0));	 	
		l++; c = 0; 
	 	pBasicData.add(tfLastName,		new GridBagConstraints2(c++, l, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	pBasicData.add(tfFirstName,		new GridBagConstraints2(c++, l, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	pBasicData.add(tfTelefon,		new GridBagConstraints2(c++, l, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4888, 0, 0));	 	
		l++; c = 0; 
	 	pBasicData.add(lEMail,			new GridBagConstraints2(c++, l, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));	 	
	 	pBasicData.add(lNtId,			new GridBagConstraints2(c++, l, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));	 	
	 	pBasicData.add(lTrader,			new GridBagConstraints2(c++, l, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8888, 0, 0));	 	
		l++; c = 0; 
	 	pBasicData.add(tfEMail,			new GridBagConstraints2(c++, l, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	pBasicData.add(tfNtId,			new GridBagConstraints2(c++, l, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	pBasicData.add(ckTrader,		new GridBagConstraints2(c++, l, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4888, 0, 0));	 	
		
				
		// Layout total view
		l = 0; c = 0; 
	 	this.add(pTitle,				new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets0000, 0, 0));	 	
	 	this.add(tpBasicData,			new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets8808, 0, 0));	 	
	 	this.add(tpTraderIds,			new GridBagConstraints2(c, l++, 1, 1, 1.0, 1.0, CENTER, BOTH,  insets8808, 0, 0));	 	
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
		tpTraderIds.setTable(tTraderIds);

		tTraderIds.setPopupProvider(STable.REMOVE_PROVIDER);
		tTraderIds.addTableEditorListener(listener);
		tTraderIds.addSTableSelectionListener(listener);

		bEdit.addActionListener(listener);

		setActions(new ActionSet[]{ actionNewTraderId });
    }

	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof EmployeeOverviewModel)) {
	        throw new IllegalArgumentException("Model not instance of EmployeeOverviewModel");
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
