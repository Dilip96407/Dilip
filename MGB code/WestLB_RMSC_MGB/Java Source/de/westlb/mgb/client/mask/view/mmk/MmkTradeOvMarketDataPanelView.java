package de.westlb.mgb.client.mask.view.mmk;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.mmk.MmkTradeOvMarketDataPanelModel;
import de.westlb.mgb.client.mask.view.shared.AbstractView;
import de.westlb.mgb.client.ui.util.DateFormat;
import de.westlb.mgb.client.ui.util.VDateField;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.STextField;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.util.Debug;


/**
 * @author M. Boerner
 *
 * View to display the statistic of trade checking.
 */
public class MmkTradeOvMarketDataPanelView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2711352395974924018L;

	private String RESOURCE_BASE = getResourceBase();

	/**
	 * The PropertyHandler for the model view synchronization.
	 */
	private PropertyHandler propertyHandler = new PropertyHandler();

	// UI - Components
    private SLabel lPrice1          = new SLabel();
    private SLabel lPrice2          = new SLabel();
    private SLabel lAdditionalPrice = new SLabel();
	private SLabel lRequestDate 	= new SLabel();
	private SLabel lRequestString	= new SLabel();
	private SLabel lSource			= new SLabel();
	private SLabel lTime			= new SLabel();
	
    private STextField tfPrice1         = new STextField(DEF_COL_08);
    private STextField tfPrice2         = new STextField(DEF_COL_08);
    private STextField tfAdditional     = new STextField(DEF_COL_08);
	private STextField tfRequestDate	= new VDateField(DEF_COL_10, DateFormat.TIME_FORMAT_LONG);	
	private STextField tfRequestString	= new STextField(DEF_COL_12);
	private STextField tfSource			= new STextField(DEF_COL_10);
	private STextField tfTime			= new VDateField(DEF_COL_10, DateFormat.TIME_FORMAT_LONG);	
	
	/**
	 * Constructor for CheckStateView.
	 */
	public MmkTradeOvMarketDataPanelView() {
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
	 * Constructor for CheckStateView.
	 * @param model
	 */
	public MmkTradeOvMarketDataPanelView(Model model) {
		super(model);
	}

	/**
	 * Felder des Views mit Werten aus dem Model fuellen
	 */
	private void fillView() {
		if (getModel() == null)
			return;
        setEmphasized(tfPrice1);
        setEmphasized(tfPrice2);
		propertyHandler.syncFields();
	}

	/**
	 * Method initComponents.
	 */
	private void initComponents() {
		this.setBackground(UIManager.getColor("DetailsPanel.background"));
		this.setOpaque(false);
	}

	/**
	 * Method initLabels.
	 */
	protected void initLabels() {
        lPrice1.setText         (getResourceString(RESOURCE_BASE + "L_PRICE1"));
        lPrice2.setText         (getResourceString(RESOURCE_BASE + "L_PRICE2"));
        lAdditionalPrice.setText(getResourceString(RESOURCE_BASE + "L_ADDITIONAL_PRICE"));
		lRequestDate.setText	(getResourceString(RESOURCE_BASE + "L_REQUEST_DATE"));
		lRequestString.setText	(getResourceString(RESOURCE_BASE + "L_REQUEST_STRING"));
		lSource.setText			(getResourceString(RESOURCE_BASE + "L_SOURCE"));
		lTime.setText			(getResourceString(RESOURCE_BASE + "L_TIME"));
	}

	/**
	 * Method initLayout.
	 * 
	 */
	private void initLayout() {		
		// Line 1
		add(lSource, 		new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		add(lRequestString,	new GridBagConstraints2(1, 0, 2, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
        add(lRequestDate,   new GridBagConstraints2(3, 0, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8808, 0, 0));
	
		// Line 2
		add(tfSource, 		new GridBagConstraints2(0, 1, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	
		add(tfRequestString,new GridBagConstraints2(1, 1, 2, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));
        add(tfRequestDate,  new GridBagConstraints2(3, 1, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4808, 0, 0));
		
		// Line 3
        add(lTime,           new GridBagConstraints2(0, 2, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
        add(lPrice1,         new GridBagConstraints2(1, 2, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
        add(lPrice2,         new GridBagConstraints2(2, 2, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
        add(lAdditionalPrice,new GridBagConstraints2(3, 2, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8808, 0, 0));

		// Line 4
		add(tfTime,	 	     new GridBagConstraints2(0, 3, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4880, 0, 0));
        add(tfPrice1,        new GridBagConstraints2(1, 3, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4880, 0, 0));
        add(tfPrice2,        new GridBagConstraints2(2, 3, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4880, 0, 0));
        add(tfAdditional,    new GridBagConstraints2(3, 3, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4888, 0, 0));
	}

	/**
	 * Initialisierung des PropertyHandlers
	 *
	 */
	private void initProperties() {
		int ro = PropertyHandler.READ_ONLY;
        propertyHandler.add(MmkTradeOvMarketDataPanelModel.P_MMK_PRICE1,            tfPrice1, ro);
        propertyHandler.add(MmkTradeOvMarketDataPanelModel.P_MMK_PRICE2,            tfPrice2, ro);
        propertyHandler.add(MmkTradeOvMarketDataPanelModel.P_MMK_ADDITIONAL_PRICE,  tfAdditional, ro);
		propertyHandler.add(MmkTradeOvMarketDataPanelModel.P_MMK_REQUESTDATE,		tfRequestDate, ro);
		propertyHandler.add(MmkTradeOvMarketDataPanelModel.P_MMK_REQUESTSTRING,		tfRequestString, ro);
		propertyHandler.add(MmkTradeOvMarketDataPanelModel.P_MMK_SOURCE,			tfSource, ro);
		propertyHandler.add(MmkTradeOvMarketDataPanelModel.P_MMK_TIME,				tfTime, ro);
	}
	
	
	/**
	 * Setzen des ViewModels
	 */
	@Override
    public void setModel(Model newModel) {
		if (!(newModel instanceof MmkTradeOvMarketDataPanelModel))
			throw new IllegalArgumentException("Model not instance of MmkTradeOvMarketDataPanelModel!");
		propertyHandler.setModel(newModel);
		
		super.setModel(newModel);
		fillView();
	}

}
