package de.westlb.mgb.client.mask.view.derivative;

import java.awt.Dimension;

import de.westlb.mgb.client.mask.model.derivative.DerivativeTradeOvTradePanelModel;
import de.westlb.mgb.client.mask.view.shared.AbstractView;
import de.westlb.mgb.client.ui.util.DateFormat;
import de.westlb.mgb.client.ui.util.VDateField;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SCheckBox;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.STextArea;
import de.westlb_systems.xaf.swing.STextField;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.util.Debug;


/**
 * @author M. Boerner
 *
 * View to display a trade overview in equity mandant.
 */
public class DerivativeTradeOvTradePanelView extends AbstractView {
	/**
     * 
     */
    private static final long serialVersionUID = -3030315372450026907L;

    private String RESOURCE_BASE = getResourceBase();

	private static final Dimension descriptionSize = new Dimension(200,70);

	/**
	 * The PropertyHandler for the model view synchronization.
	 */
	private PropertyHandler propertyHandler = new PropertyHandler();

	private SLabel  lTradeId 		= new SLabel();
	private SLabel  lDealId 		= new SLabel();
	private SLabel  lStructure 		= new SLabel();
	private SLabel	lTradeType		= new SLabel();
	private SLabel  lInstrumentName	= new SLabel();
	private SLabel  lTraderName		= new SLabel();
	private SLabel	lTraderId 		= new SLabel();
	private SLabel  lBook			= new SLabel();
	private SLabel  lCompany		= new SLabel();
	private SLabel  lTradeTime		= new SLabel();
	private SLabel  lUpdateTime		= new SLabel();
	private SLabel	lCounterparty	= new SLabel();
	private SLabel	lDescription	= new SLabel();
    private SLabel  lInternal       = new SLabel();
    private SLabel  lAmended        = new SLabel();
    private SLabel  lTraderLocation = new SLabel();
    private SLabel  lReportLocation = new SLabel();

	// UI - Components
	private STextField  tfTradeId 			= new STextField(DEF_COL_10);
	private STextField  tfDealId 			= new STextField(DEF_COL_10);
	private STextField  tfStructure 		= new STextField(DEF_COL_08);
	private STextField	tfTradeType			= new STextField(DEF_COL_10);
	private STextField  tfInstrumentName	= new STextField(DEF_COL_10);
	private STextField	tfTraderId 			= new STextField(DEF_COL_10);
	private STextField  tfTraderName		= new STextField(DEF_COL_10);
	private STextField  tfBook				= new STextField(DEF_COL_10);
	private STextField  tfCompany			= new STextField(DEF_COL_10);
	private STextField  tfTradeTime			= new VDateField(DEF_COL_10, DateFormat.TIME_FORMAT_LONG);
	private STextField  tfUpdateTime		= new VDateField(DEF_COL_10, DateFormat.TIME_FORMAT_LONG);
	private STextField	tfCounterparty		= new STextField(DEF_COL_10);
    private STextField  tfTraderLocation    = new STextField(DEF_COL_10);
    private STextField  tfReportLocation    = new STextField(DEF_COL_10);
	private STextArea	taDescription		= new STextArea();
    private SCheckBox   ckInternal          = new SCheckBox();
    private SCheckBox   ckAmended           = new SCheckBox();

			
	/**
	 * Constructor for CheckStateView.
	 */
	public DerivativeTradeOvTradePanelView() {
		try {
			initComponents();
			initLabels();
			initLayout();
			initProperties();
		} catch (Exception ex) {
			Debug.log(Debug.ERROR, this, "Exception in View-Constuctor");
			Debug.log(ex);
			ex.printStackTrace();
		}
	}

	/**
	 * Constructor for TradeOvTradePanelView.
	 * @param model
	 */
	public DerivativeTradeOvTradePanelView(Model model) {
		super(model);
	}

	/**
	 * Felder des Views mit Werten aus dem Model fuellen
	 */
	private void fillView() {
		DerivativeTradeOvTradePanelModel model = getTradeOvTradePanelModel();
		if (model == null) {
			return;
		}
		
		propertyHandler.syncFields();
	}
	
	private DerivativeTradeOvTradePanelModel getTradeOvTradePanelModel() {
		if (!(getModel() instanceof DerivativeTradeOvTradePanelModel)) {
			return null;
		}
		
		return (DerivativeTradeOvTradePanelModel) getModel();	
	}
	
	/**
	 * Method initComponents.
	 */
	private void initComponents() {
		ckInternal.setEditable(false);		
	}

	public void setReadWrite() {
		tfInstrumentName.setEditable(true);
		tfDealId.setEditable(true);
		tfTradeId.setEditable(true);
	}

	/**
	 * Method initLabels.
	 */
	protected void initLabels() {
		lTradeId.setText		(getResourceString(RESOURCE_BASE + "L_TRADE_ID"));
		lDealId.setText			(getResourceString(RESOURCE_BASE + "L_DEAL_ID"));
		lStructure.setText		(getResourceString(RESOURCE_BASE + "L_STRUCTURE"));
		lTradeType.setText 		(getResourceString(RESOURCE_BASE + "L_TRADE_TYPE"));
		lInstrumentName.setText	(getResourceString(RESOURCE_BASE + "L_INSTRUMENT_NAME"));
		lTraderName.setText		(getResourceString(RESOURCE_BASE + "L_TRADER_NAME"));
		lTraderId.setText 		(getResourceString(RESOURCE_BASE + "L_TRADER_ID"));
		lBook.setText			(getResourceString(RESOURCE_BASE + "L_BOOK"));
		lCompany.setText		(getResourceString(RESOURCE_BASE + "L_COMPANY"));
		lTradeTime.setText		(getResourceString(RESOURCE_BASE + "L_TRADE_TIME"));
		lUpdateTime.setText		(getResourceString(RESOURCE_BASE + "L_UPDATE_TIME"));
		lCounterparty.setText	(getResourceString(RESOURCE_BASE + "L_COUNTERPARTY"));
		lDescription.setText	(getResourceString(RESOURCE_BASE + "L_DESCRIPTION"));
        lInternal.setText       (getResourceString(RESOURCE_BASE + "L_INTERNAL"));
        lAmended.setText        (getResourceString(RESOURCE_BASE + "L_AMENDED"));
        lTraderLocation.setText (getResourceString(RESOURCE_BASE + "L_TRADER_LOCATION"));
        lReportLocation.setText (getResourceString(RESOURCE_BASE + "L_REPORT_LOCATION"));
	}

	/**
	 * Method initLayout.
	 * 
	 */
	private void initLayout() {

		taDescription.setMinimumSize(descriptionSize);
		taDescription.setPreferredSize(descriptionSize);

		int col = 0;
		int row = 0;
		

		// Column 0
		add(lTradeId,			new GridBagConstraints2(row++, col, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lDealId, 			new GridBagConstraints2(row++, col, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lStructure, 		new GridBagConstraints2(row++, col, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lTradeType,			new GridBagConstraints2(row++, col, 2, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		row++;
		add(lInstrumentName, 	new GridBagConstraints2(row++, col, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8808, 0, 0));

		col = 1;
		row = 0;
		// Column 1
		add(tfTradeId,			new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfDealId, 			new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfStructure, 		new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfTradeType,		new GridBagConstraints2(row++, col, 2, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
	    row++;
	    add(tfInstrumentName, 	new GridBagConstraints2(row++, col, 1, 1, 0.0, 0.0, WEST, HORIZ, insets4808, 0, 0));

		col = 2;
		row = 0;
		// Column 2
		add(lTraderId, 			new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));		
		add(lTraderName,		new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lBook, 				new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lCompany,			new GridBagConstraints2(row++, col, 2, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		row++;
		add(lDescription,		new GridBagConstraints2(row++, col, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8808, 0, 0));

		col = 3;
		row = 0;
		// Column 3
		add(tfTraderId, 		new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));	
		add(tfTraderName,		new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));	
		add(tfBook,				new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfCompany,			new GridBagConstraints2(row++, col, 2, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
	    row++;
	    add(taDescription,		new GridBagConstraints2(row++, col, 1, 3, 0.0, 0.0, WEST, HORIZ, insets4888, 0, 0));

		col = 4;
		row = 0;
		// Column 4
		add(lTradeTime, 		new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));		
		add(lUpdateTime,		new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lCounterparty, 		new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lInternal,          new GridBagConstraints2(row++, col, 1, 1, 0.5, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lAmended,           new GridBagConstraints2(row++, col, 1, 1, 0.5, 0.0, WEST, HORIZ, insets8800, 0, 0));
		
		col = 5;
		row = 0;
		// Column 5
		add(tfTradeTime, 		new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));		
		add(tfUpdateTime,		new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfCounterparty, 	new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
        add(ckInternal,         new GridBagConstraints2(row++, col, 1, 1, 0.0, 0.0, WEST, HORIZ, insets4800, 0, 0));
        add(ckAmended,          new GridBagConstraints2(row++, col, 1, 1, 0.0, 0.0, WEST, HORIZ, insets4800, 0, 0));

        col = 6;
        row = 0;
        // Column 6
        add(lTraderLocation,    new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));        
        add(lReportLocation,    new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));

        col = 7;
        row = 0;
        // Column 7
        add(tfTraderLocation,   new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4880, 0, 0));     
        add(tfReportLocation,   new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4880, 0, 0));
	}

	/**
	 * Initialisierung des PropertyHandlers
	 *
	 */
	private void initProperties() {
		int ro = PropertyHandler.READ_ONLY;
		
		propertyHandler.add(DerivativeTradeOvTradePanelModel.P_DVD_TRADE_ID,			tfTradeId, ro);
		propertyHandler.add(DerivativeTradeOvTradePanelModel.P_DVD_DEAL_ID,				tfDealId, ro);
		propertyHandler.add(DerivativeTradeOvTradePanelModel.P_DVD_STRUCTURE,			tfStructure, ro);
		propertyHandler.add(DerivativeTradeOvTradePanelModel.P_DVD_TRADE_TYPE,			tfTradeType, ro);
		propertyHandler.add(DerivativeTradeOvTradePanelModel.P_DVD_INSTRUMENT_NAME,		tfInstrumentName, ro);	

		propertyHandler.add(DerivativeTradeOvTradePanelModel.P_DVD_TRADER_ID,			tfTraderId, ro);
		propertyHandler.add(DerivativeTradeOvTradePanelModel.P_DVD_TRADER_NAME,			tfTraderName, ro);
		propertyHandler.add(DerivativeTradeOvTradePanelModel.P_DVD_BOOK,				tfBook, ro);
		propertyHandler.add(DerivativeTradeOvTradePanelModel.P_DVD_COMPANY,				tfCompany, ro);
		propertyHandler.add(DerivativeTradeOvTradePanelModel.P_DVD_DESCRIPTION,			taDescription, ro);

		propertyHandler.add(DerivativeTradeOvTradePanelModel.P_DVD_TRADE_TIME,			tfTradeTime, ro);
		propertyHandler.add(DerivativeTradeOvTradePanelModel.P_DVD_SYSTEM_TIME,			tfUpdateTime, ro);
		propertyHandler.add(DerivativeTradeOvTradePanelModel.P_DVD_COUNTERPARTY,		tfCounterparty, ro);
        propertyHandler.add(DerivativeTradeOvTradePanelModel.P_DVD_INTERNAL,            ckInternal, ro);
        propertyHandler.add(DerivativeTradeOvTradePanelModel.P_DVD_AMENDED,             ckAmended, ro);

        propertyHandler.add(DerivativeTradeOvTradePanelModel.P_DVD_TRADER_LOCATION,   tfTraderLocation, ro);
        propertyHandler.add(DerivativeTradeOvTradePanelModel.P_DVD_REPORT_LOCATION,   tfReportLocation, ro);
}
	
	
	/**
	 * Setzen des ViewModels
	 */
	@Override
    public void setModel(Model newModel) {
		if (!(newModel instanceof DerivativeTradeOvTradePanelModel))
			throw new IllegalArgumentException("Model not instance of DerivativeTradeOvTradePanelModel!");
		
		propertyHandler.setModel(newModel);
		
		super.setModel(newModel);
		fillView();
	}

}
