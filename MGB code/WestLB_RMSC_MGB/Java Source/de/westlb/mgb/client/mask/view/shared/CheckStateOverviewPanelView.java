package de.westlb.mgb.client.mask.view.shared;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.shared.CheckStateOverviewPanelModel;
import de.westlb.mgb.client.ui.util.VDecimalField;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SButton;
import de.westlb_systems.xaf.swing.SIcon;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.STextField;
import de.westlb_systems.xaf.ui.misc.IconKatalog;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.util.Debug;

/**
 * @author WSY4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CheckStateOverviewPanelView extends AbstractView implements ActionListener {
	/**
     * 
     */
    private static final long serialVersionUID = 1732548846539693991L;

    /**
	 * The PropertyHandler for the model view synchronization.
	 */
	private PropertyHandler propertyHandler = new PropertyHandler();

	private SLabel lTotal = new SLabel();
	private STextField tfTotal = new VDecimalField(DEF_COL_08, 0);
	private SLabel lManual = new SLabel();
	private STextField tfManual = new VDecimalField(DEF_COL_08, 0);
	private SLabel lAuto = new SLabel();
	private STextField tfAuto = new VDecimalField(DEF_COL_08, 0);
	private SLabel lReclRequired = new SLabel();
	private STextField tfReclRequired = new VDecimalField(DEF_COL_08, 0);
	private SLabel lReclOpen = new SLabel();
	private STextField tfReclOpen = new VDecimalField(DEF_COL_08, 0);
	private SLabel lReclClosed = new SLabel();
	private STextField tfReclClosed = new VDecimalField(DEF_COL_08, 0);
	private SLabel lLateEntries = new SLabel();
	private STextField tfLateEntries = new VDecimalField(DEF_COL_08, 0);
	
	private SButton bListAll = new SButton();
	private SButton bListAuto = new SButton();
	private SButton bListManual = new SButton();
	private SButton bListReclRequired = new SButton();
	private SButton bListReclOpen = new SButton();
	private SButton bListReclClosed = new SButton();
	private SButton bListLateEntries = new SButton();

	/** Listener for all events */
	private Listener listener = new Listener();
	
	public static final int LIST_ALL_TRADES = 50;
	public static final int LIST_AUTO_TRADES = 51;
	public static final int LIST_MANUAL_TRADES = 52;
	public static final int LIST_RECL_REQUIRED_TRADES = 53;
	public static final int LIST_RECL_OPEN_TRADES = 54;
	public static final int LIST_RECL_CLOSED_TRADES = 55;
	public static final int LIST_TRADES_LATE_ENTERED = 56;

	/** Universal Listener fuer alle Events die im View auftreten */
	private class Listener implements ActionListener {
		public Listener() {
		}
		@Override
        public void actionPerformed(ActionEvent ae) {
			Object source = ae.getSource();
			if (source == bListAll) {
				fireUserEvent(LIST_ALL_TRADES, null);
			} else if (source == bListAuto) {
				fireUserEvent(LIST_AUTO_TRADES, null);
			} else if (source == bListManual) {
				fireUserEvent(LIST_MANUAL_TRADES, null);
			} else if (source == bListReclRequired) {
				fireUserEvent(LIST_RECL_REQUIRED_TRADES, null);
			} else if (source == bListReclOpen) {
				fireUserEvent(LIST_RECL_OPEN_TRADES, null);
			} else if (source == bListReclClosed) {
				fireUserEvent(LIST_RECL_CLOSED_TRADES, null);
			} else if (source == bListLateEntries) {
				fireUserEvent(LIST_TRADES_LATE_ENTERED, null);	
			}
		}
	}

    /**
     * Constructor for CheckStateSumPanel.
     */
    public CheckStateOverviewPanelView() {
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
     * Constructor for CheckStateSumPanel.
     * @param model
     */
    public CheckStateOverviewPanelView(Model model) {
        super(model);
    }

    /**
     * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
    /**
	 * Befuellen des Panels
	 *
	 */
	private void fillView() {
		if (getModel() == null) {
			return;
		}
		propertyHandler.syncFields();
	}

    
    /**
	 * Method initComponents.
	 */
	private void initComponents() {	
		SIcon editIcon = IconKatalog.getInstance().getIcon("EDIT");
		bListAll.setIcon(editIcon);
		bListAuto.setIcon(editIcon);
		bListManual.setIcon(editIcon);
		bListReclRequired.setIcon(editIcon);
		bListReclOpen.setIcon(editIcon);
		bListReclClosed.setIcon(editIcon);
		bListLateEntries.setIcon(editIcon);
		tfTotal.setEditable(false);
		tfAuto.setEditable(false);
		tfManual.setEditable(false);
		tfReclRequired.setEditable(false);
		tfReclOpen.setEditable(false);
		tfReclClosed.setEditable(false);
		tfLateEntries.setEditable(false);
		
		bListAll.addActionListener(listener);
		bListAuto.addActionListener(listener);
		bListManual.addActionListener(listener);
		bListReclRequired.addActionListener(listener);
		bListReclOpen.addActionListener(listener);
		bListReclClosed.addActionListener(listener);
		bListLateEntries.addActionListener(listener);
	}
	
	/**
	 * Method initLabels.
	 */
	protected void initLabels() {
		lTotal.setText(getResourceString("L_TOTAL"));
		lManual.setText(getResourceString("L_MANUAL"));
		lAuto.setText(getResourceString("L_AUTO"));
		lReclRequired.setText(getResourceString("L_RECL_REQUIRED"));
		lReclOpen.setText(getResourceString("L_RECL_OPEN"));
		lReclClosed.setText(getResourceString("L_RECL_CLOSED"));
		lLateEntries.setText(getResourceString("L_LATE_ENTRIES"));
	}
	
	/**
	 * Method initLayout.
	 */
	private void initLayout() {
		this.setBackground(UIManager.getColor("DetailsPanel.background"));
		this.setOpaque(true);
	
		// Spalte 0
		this.add(lTotal,		new GridBagConstraints2(0, 0, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		this.add(lAuto,			new GridBagConstraints2(0, 1, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));
		this.add(lManual,		new GridBagConstraints2(0, 2, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets4880, 0, 0));
	
		// Spalte 1
		this.add(tfTotal,		new GridBagConstraints2(1, 0, 1, 1, 2.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		this.add(tfAuto,		new GridBagConstraints2(1, 1, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));
		this.add(tfManual,		new GridBagConstraints2(1, 2, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets4880, 0, 0));

		// Spalte 3
		this.add(bListAll,		new GridBagConstraints2(2, 0, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		this.add(bListAuto,		new GridBagConstraints2(2, 1, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		this.add(bListManual,	new GridBagConstraints2(2, 2, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
	
		// Spalte 4
		this.add(lReclRequired,				new GridBagConstraints2(3, 0, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		this.add(lReclOpen,			new GridBagConstraints2(3, 1, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));
		this.add(lReclClosed,			new GridBagConstraints2(3, 2, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets4880, 0, 0));
		this.add(lLateEntries,	new GridBagConstraints2(3, 3, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets4880, 0, 0));
	
		// Spalte 5
		this.add(tfReclRequired,			new GridBagConstraints2(4, 0, 1, 1, 2.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		this.add(tfReclOpen,			new GridBagConstraints2(4, 1, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));
		this.add(tfReclClosed,			new GridBagConstraints2(4, 2, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets4880, 0, 0));
		this.add(tfLateEntries,	new GridBagConstraints2(4, 3, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets4880, 0, 0));
				

		// Spalte 6
		this.add(bListReclRequired,		new GridBagConstraints2(5, 0, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		this.add(bListReclOpen,	new GridBagConstraints2(5, 1, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));
		this.add(bListReclClosed,	new GridBagConstraints2(5, 2, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets4880, 0, 0));		
		this.add(bListLateEntries,	new GridBagConstraints2(5, 3, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets4880, 0, 0));		
	}

	/**
	 * Initialisierung des PropertyHandlers
	 *
	 */
	private void initProperties() {
		int ro = PropertyHandler.READ_ONLY;
		
		propertyHandler.add(CheckStateOverviewPanelModel.NO_TRADES_TOTAL,     tfTotal, ro);
		propertyHandler.add(CheckStateOverviewPanelModel.NO_TRADES_OK_AFTER_AUTOCHECK, tfAuto, ro);
		propertyHandler.add(CheckStateOverviewPanelModel.NO_TRADES_MANUAL_CHECK_REQUIRED, tfManual, ro);
		propertyHandler.add(CheckStateOverviewPanelModel.NO_TRADES_RECLAMATION_REQUIRED, tfReclRequired, ro);
		propertyHandler.add(CheckStateOverviewPanelModel.NO_TRADES_RECLAMATION_OPEN, tfReclOpen, ro);
		propertyHandler.add(CheckStateOverviewPanelModel.NO_TRADES_RECLAMATION_CLOSED, tfReclClosed, ro);
		propertyHandler.add(CheckStateOverviewPanelModel.NO_TRADES_LATE_ENTRY, tfLateEntries, ro);
	}

	/**
	 * Sets the model
	 *
	 */
	@Override
    public void setModel(Model newModel) {
		if (!(newModel instanceof CheckStateOverviewPanelModel))
			throw new IllegalArgumentException("Model not instance of CheckStateOverviewModel!");
		propertyHandler.setModel(newModel);
		super.setModel(newModel);
		fillView();
	}
}
