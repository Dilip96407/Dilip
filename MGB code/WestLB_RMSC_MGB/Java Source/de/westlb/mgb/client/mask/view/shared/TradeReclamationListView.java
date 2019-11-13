/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.client.mask.view.shared;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.shared.TradeReclamationListModel;
import de.westlb.mgb.client.server.vo.SendMailParamsVo;
import de.westlb.mgb.model.definition.MailTypeDef;
import de.westlb_systems.xaf.application.Synchronizer;
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
 * This view represents a list of trades for sending
 * reclamation to the trader.
 *
 * @author WSY4148 
 */
public class TradeReclamationListView extends AbstractView {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6054301884209870729L;
	public static final int EV_PREVIEW			= 1;
	public static final int EV_SHOW_TRADE		= 3;
	public static final int EV_EDIT_EMPLOYEE	= 4;
	
    public String RESOURCE_BASE = getResourceBase();

    /** gui components */
    private TitlePanel pTitle = new TitlePanel();
    private TablePanel tPanel = new TablePanel();
    private STable table = new STable();
    private ButtonPanel pButtons = new ButtonPanel();
    private SButton bPreview = new SButton();
    private SButton bSend = new SButton();
    
    private SMenuItem pItemPreview = new SMenuItem();
    private SMenuItem pItemSend = new SMenuItem();
	private SMenuItem pItemShowTrade = new SMenuItem();
	private SMenuItem pItemEditEmployee = new SMenuItem();    

    /** Property handler to synchronize model and view */
    private PropertyHandler propertyHandler = new PropertyHandler();

    /** Listener fuer alle Events */
    private Listener listener = new Listener();

    /**
     * Constructor for CheckStateView.
     */
    public TradeReclamationListView() {
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
     *
     * @param model a TradeReclamationListModel
     */
    public TradeReclamationListView(Model model) {
        this();
        setModel(model);
    }

    /**
     * Returnes an array of the currently selected rows.
     *
     * @return the indexes of the selected rows.
     */
    public int[] getSelectedRows() {
        return table.getSelectedRows();
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public boolean doPreview() {
        // If no row selected (may be table is empty) log error message
        if (getSelectedRows().length != 1) {
            logMessage(LogMessageEvent.ERROR, getResourceString(RESOURCE_BASE + "E_002"));
            return false;
        }
		SendMailParamsVo params = new SendMailParamsVo();
		params.setType(MailTypeDef.CONTROLLER_RECLAMATION);
		params.setTradeId(getTradeReclamationListModel().getTradeId(getSelectedRows()[0]));
		fireUserEvent(EV_PREVIEW, params);			

        return true;
    }

    /**
     * Sends emails for the currently selected trades to
     * the trader.
     *
     * @return true if the operation finished successful.
     */
    public boolean doSend() {
        // If no row selected (may be table is empty) log error message
        if (getSelectedRows().length < 1) {
            logMessage(LogMessageEvent.ERROR, getResourceString(RESOURCE_BASE + "E_001"));
            return false;
        }

		boolean oldC = Synchronizer.setWaitCursor(true, this);
		boolean done = true;
		if (getTradeReclamationListModel() != null) {
			done = getTradeReclamationListModel().doSendEmails(getSelectedRows());
			
		}

		setReturnvalue((getModel() != null) ? getModel().getReturnvalue() : 0);
		Synchronizer.setWaitCursor(oldC, this);

		if (!done) {
			handleModelError();
		} else {
			setDataChanged(false);
		}

		return done;
    }

    /**
     * Fill the fields with the values of the model
     */
    private void fillView() {
        if (getTradeReclamationListModel() == null) {
            return;
        }

        propertyHandler.syncFields();
    }

    /**
     * Return casted model
     *
     * @return the casted model
     */
    public TradeReclamationListModel getTradeReclamationListModel() {
        return (getModel() instanceof TradeReclamationListModel)  ? (TradeReclamationListModel) getModel() : null;
    }

    /**
     * Method initProperties.
     */
    private void initProperties() {
        propertyHandler.add(TradeReclamationListModel.P_TRADE_TABLE_MODEL, table);
    }

    /**
     * Method initLabels.
     */
    private void initLabels() {
        this.setTitle(getResourceString(RESOURCE_BASE + "T_001"));
        tPanel.setText(getResourceString(RESOURCE_BASE + "T_TRADE_LIST"));
        bPreview.setText(getResourceString(RESOURCE_BASE + "B_PREVIEW"));
        bSend.setText(getResourceString(RESOURCE_BASE + "B_SEND"));

        pItemPreview.setText(bPreview.getText());
        pItemSend.setText(bSend.getText());
		pItemShowTrade.setText(getResourceString(RESOURCE_BASE + "B_SHOW_TRADE"));
		pItemEditEmployee.setText(getResourceString(RESOURCE_BASE + "B_EDIT_EMPLOYEE"));
    }

    /**
     * Method initLayout.
     */
    private void initLayout() {
		// Layout total view
		int l = 0;
        int c = 0;
        this.add(pTitle,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets0000, 0, 0));
        this.add(tPanel,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 1.0, CENTER, BOTH, insets8808, 0, 0));
        this.add(pButtons,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets8888, 0, 0));

        setBackgroundForAllPanels(UIManager.getColor("DetailsPanel.background"));
    }

    /**
     * Method initComponents.
     */
    private void initComponents() {
        pTitle.setHeaderTitleColor(UIManager.getColor("SendReclamationListView.foreground"));
        pTitle.setColorProgress(UIManager.getColor("SendReclamationListView.titleBackground1"),
            UIManager.getColor("SendReclamationListView.titleBackground2"));

        tPanel.setTable(table);

        table.setPopupProvider(0);

        table.addTableEditorListener(listener);
        table.addSTableSelectionListener(listener);
        table.setGridEnabled(true);
        table.setSelectionMode(STable.MULTIPLE_SELECTION);
		table.setRowCounterVisible(true);

        bPreview.addActionListener(listener);
        bSend.addActionListener(listener);
        pButtons.addButton(bPreview);
        pButtons.addButton(bSend);

        // Initialize table popup menu
        pItemPreview.addActionListener(listener);
        pItemSend.addActionListener(listener);
		pItemPreview.addActionListener(listener);
		pItemShowTrade.addActionListener(listener);
		pItemEditEmployee.addActionListener(listener);

        SPopupProvider provider = table.getPopupProvider();
        SPopupMenu menu = provider.getPopupMenu();
        Component[] items = menu.getComponents();

        menu.removeAll();
        menu.add(pItemPreview);
        menu.add(pItemSend);
        menu.addSeparator();
		menu.add(pItemShowTrade);
		menu.add(pItemEditEmployee);
        menu.addSeparator();

        if (items != null) {
            for (int i = 0; i < items.length; i++) {
                menu.add(items[i]);
            }
        }

        // setActions(new ActionSet[]{ actionEditEmployee });
    }

    /**
     * Sets the view model and creates the property handler from model view synchronization
     *
     * @param newModel a new TradeReclamationListModel 
     * @throws IllegalArgumentException If the newModel is not an instance of TradeReclamationListModel
     */
    @Override
    public void setModel(Model newModel) {
        if (!(newModel instanceof TradeReclamationListModel)) {
            throw new IllegalArgumentException("Model not instance of TradeReclamationListModel");
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
     * @param title DOCUMENT ME!
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

    /**
     * Listener fuer alle Events
     */
    private class Listener implements STableSelectionListener, STableEditorListener, ActionListener {
        /**
         * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if ((e.getSource() == bSend) || (e.getSource() == pItemSend)) {
                doSend();
            } else if ((e.getSource() == bPreview) || (e.getSource() == pItemPreview)) {
                doPreview();
            } else if (e.getSource() == pItemEditEmployee) {
            	doEditEmployee();
            } else if (e.getSource() == pItemShowTrade) {
            	doShowTrade();
            }
        }

        /**
		 * 
		 */
		private boolean doShowTrade() {
			// If no row selected (may be table is empty) log error message
			if (getSelectedRows().length != 1) {
				logMessage(LogMessageEvent.ERROR, getResourceString(RESOURCE_BASE + "E_001"));
				return false;
			}

			fireUserEvent(EV_SHOW_TRADE, getTradeReclamationListModel().getTradeId(getSelectedRows()[0]));

			return true;
		}

		/**
		 * 
		 */
		private boolean doEditEmployee() {
			// If no row selected (may be table is empty) log error message
			if (getSelectedRows().length != 1) {
				logMessage(LogMessageEvent.ERROR, getResourceString(RESOURCE_BASE + "E_002"));
				return false;
			}

			fireUserEvent(EV_EDIT_EMPLOYEE, getTradeReclamationListModel().getEmployeeId(getSelectedRows()[0]));

			return true;
		}

		/**
         * @see de.westlb_systems.xaf.swing.STableSelectionListener#selectionChanged(STableSelectionEvent)
         */
        @Override
        public void selectionChanged(STableSelectionEvent e) {
            if (e.isDoubleClicked()) {
                doShowTrade();
            }
        }

        /**
         * @see de.westlb_systems.xaf.swing.STableEditorListener#invokeEditor(STableEditorEvent)
         */
        @Override
        public void invokeEditor(STableEditorEvent e) {
			TradeReclamationListModel model = getTradeReclamationListModel();

            if (model == null) {
                return;
            }

            if (STableEditorEvent.OPEN.equals(e.getActionCommand())) {
                doShowTrade();
            }
        }
    }
}