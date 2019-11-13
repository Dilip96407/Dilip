/*
 * Copyright (c) 2003, WestLB Systems
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.client.mask.view.derivative;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.derivative.DerivativeTradeOvAssetPanelModel;
import de.westlb.mgb.client.mask.model.shared.TradeOverviewModel;
import de.westlb.mgb.client.mask.view.shared.AbstractView;
import de.westlb.mgb.client.mask.view.shared.TradeOvResultPanelView;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.application.event.UserEventListener;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.ui.view.TablePanel;
import de.westlb_systems.xaf.util.Debug;


/**
 * DOCUMENT ME!
 *
 * @author M. Boerner View to display the most important information that the trade controller
 *         needs for the trade checking.
 */
public class DerivativeTradeOverviewView extends AbstractView {
    /**
     * 
     */
    private static final long serialVersionUID = -5760028345233517188L;
    /* Constant definitions in UserEvents */
    public static final int NEW_MANUAL_STATE = 1;
    public static final int NEW_RECLAMATION_STATE = 2;
    public static final int SEND_RECL_MAIL = 3;
    public static final int CLOSE_RECL = 4;
    private String RESOURCE_BASE = getResourceBase();

    /** Listener for all events */
    private Listener listener = new Listener();

    /** The PropertyHandler for the model view synchronization. */
    private PropertyHandler propertyHandler = new PropertyHandler();

    // UI - Components
    private TablePanel tpTrade = new TablePanel();
    private TablePanel tpMarketData = new TablePanel();
    private TablePanel tpCheckResult = new TablePanel();
    private TablePanel tpAssetResult = new TablePanel();
    private DerivativeTradeOvTradePanelView pTrade = new DerivativeTradeOvTradePanelView();
    private DerivativeTradeOvMarketDataPanelView pMarketData = new DerivativeTradeOvMarketDataPanelView();
    private TradeOvResultPanelView pCheckResult = new TradeOvResultPanelView();
    private DerivativeTradeOvAssetPanelView pAssetResult = new DerivativeTradeOvAssetPanelView();

    /**
     * Constructor for DerivativeTradeOverviewView.
     */
    public DerivativeTradeOverviewView() {
        try {
            initComponents();
            initLayoutInitial();
            initLabels();
            initProperties();
        } catch (Exception ex) {
            Debug.log(Debug.ERROR, this, "Exception in View-Constuctor");
            Debug.log(ex);
            ex.printStackTrace();
        }
    }

    /**
     * Constructor for DerivativeTradeOverviewView.
     *
     * @param model
     */
    public DerivativeTradeOverviewView(Model model) {
        super(model);
    }

    /**
     * Felder des Views mit Werten aus dem Model fuellen
     */
    private void fillView() {
        if (getModel() == null) {
            return;
        }

        propertyHandler.syncFields();
    }

    /**
     * DOCUMENT ME!
    
     *
     * @return DOCUMENT ME!
     */
    public TradeOverviewModel getTradeOverviewModel() {
        if (!(getModel() instanceof TradeOverviewModel)) {
            return null;
        }

        return (TradeOverviewModel) getModel();
    }

    /**
     * Method initComponents.
     */
    private void initComponents() {
        tpTrade.setTable(pTrade);
        tpMarketData.setTable(pMarketData);
        tpCheckResult.setTable(pCheckResult);
        tpAssetResult.setTable(pAssetResult);

        // listener processes the button events of submask
        pCheckResult.addUserEventListener(listener);
    }

    /**
     * Method initLabels.
     */
    protected void initLabels() {
        tpTrade.setText(getResourceString(RESOURCE_BASE + "L_HEADER_TRADE"));
        tpMarketData.setText(getResourceString(RESOURCE_BASE + "L_HEADER_MARKETDATA"));
        tpCheckResult.setText(getResourceString(RESOURCE_BASE + "L_HEADER_CHECKRESULT"));
        tpAssetResult.setText(getResourceString(RESOURCE_BASE + "L_HEADER_ASSETS"));
    }

    /**
     * Initialisierung des PropertyHandlers
     */
    private void initProperties() {
    }

    /**
     * Method initLayout.
     */
    private void initLayoutInitial() {
        add(tpTrade,
            new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4808, 0, 0));
        add(tpMarketData,
			new GridBagConstraints2(0, 1, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4808, 0, 0));
        add(tpCheckResult,
           	new GridBagConstraints2(0, 2, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4808, 0, 0));
        add(tpAssetResult,
            new GridBagConstraints2(0, 3, 1, 1, 1.0, 1.0, CENTER, BOTH, insets4888, 0, 0));

        setReadOnly();
        setBackgroundForAllPanels(UIManager.getColor("DetailsPanel.background"));
        pTrade.setReadWrite();
    }

    /**
     * Setzen des ViewModels
     *
     * @param newModel DOCUMENT ME!
     *
     * @throws IllegalArgumentException if model is not instance of TradeOverviewModel.
     */
    @Override
    public void setModel(Model newModel) {
        if (!(newModel instanceof TradeOverviewModel)) {
            throw new IllegalArgumentException("Model not instance of TradeOverviewModel!");
        }

        super.setModel(newModel);
        propertyHandler.setModel(newModel);
        pMarketData.setModel(newModel);
        pCheckResult.setModel(newModel);
        pTrade.setModel(newModel);
        
        DerivativeTradeOvAssetPanelModel assetListModel= new DerivativeTradeOvAssetPanelModel(newModel);
        pAssetResult.setModel(assetListModel);

        fillView();
    }

    /**
     * Universal Listener fuer alle Events die im View auftreten
     */
    private class Listener implements UserEventListener, PropertyChangeListener {
        public Listener() {
        }

        /* (Kein Javadoc)
         * @see de.westlb_systems.xaf.application.event.UserEventListener#actionRequested(de.westlb_systems.xaf.application.event.UserEvent)
         */
        @Override
        public void actionRequested(UserEvent ev) {
			switch (ev.getAction()) {
				case TradeOvResultPanelView.EV_NEW_MANUAL_STATE:
					fireUserEvent(new UserEvent(this, NEW_MANUAL_STATE, getModel().getBusinessObject()));
					break;
				case TradeOvResultPanelView.EV_NEW_RECLAMATION_STATE:
					fireUserEvent(new UserEvent(this, NEW_RECLAMATION_STATE, getModel().getBusinessObject()));
					break;
				case TradeOvResultPanelView.EV_SEND_RECL_MAIL:
					fireUserEvent(new UserEvent(this, SEND_RECL_MAIL, getModel().getBusinessObject()));
					break;
				case TradeOvResultPanelView.EV_CLOSE_RECL:
					fireUserEvent(new UserEvent(this, CLOSE_RECL, getModel().getBusinessObject()));
					break;
				default:
					break;
			}

        }

        /**
         * @see java.beans.PropertyChangeListener#propertyChange(PropertyChangeEvent)
         */
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
        }
    }
}