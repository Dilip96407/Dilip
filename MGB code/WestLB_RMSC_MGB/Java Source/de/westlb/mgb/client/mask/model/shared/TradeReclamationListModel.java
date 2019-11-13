/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.MailVo;
import de.westlb.mgb.client.server.vo.SendMailParamsVo;
import de.westlb.mgb.client.server.vo.TradeReclRequiredVo;
import de.westlb.mgb.client.ui.tablemodel.TableModel;
import de.westlb.mgb.client.ui.tablemodel.TableModelFactory;
import de.westlb.mgb.model.definition.MailTypeDef;


/**
 * Gui model for TradeReclamationListViews. Contains functions on trades which need to be
 * reclamated.
 *
 * @author Manfred Boerner
 */
public class TradeReclamationListModel extends AbstractModel {
    public static final String P_TRADE_TABLE_MODEL = "tradeTableModel";

    /** Definition of all properties provided by the model to the view */
    private final String[] propertyNames = new String[] { P_TRADE_TABLE_MODEL, };
    private TradeReclRequiredVo[] searchResult = null;

    /**
     * Default constructor to create an empty model
     */
    public TradeReclamationListModel() {
        setPropertyNames(propertyNames);
        fillModel();
    }

    /**
     * DOCUMENT ME!
    
     *
     * @param rows DOCUMENT ME!
     */
    public boolean doSendEmails(int[] rows) {
		boolean success = false;
    	if (rows == null) {
    		return false;
    	}


		// Fill an array with SendMailParams for the requested trade rows	
		SendMailParamsVo[] pArray = new SendMailParamsVo[rows.length];
		for (int i = 0; i < rows.length; i++) {
			SendMailParamsVo mailParamsVo = new SendMailParamsVo();
			mailParamsVo.setType(MailTypeDef.CONTROLLER_RECLAMATION);
			mailParamsVo.setTradeId(getTradeId(rows[i]));
			pArray[i] = mailParamsVo;
		}
		
		// Send all mails in one step
		try {
			SendMailParamsVo params = new SendMailParamsVo();
			params.setType(MailTypeDef.CONTROLLER_RECLAMATION);

			MgbServiceFactory.getService().prepareAndSendMail(pArray);
			success =true;
		} catch (RemoteException e) {
			handleRemoteException(e);
		}
		
		return success;
    }

    /**
     * Method doSendEmail.
     *
     * @param row
     */
    @SuppressWarnings("unused")
    private void doSendEmail(int row) {
        try {
            SendMailParamsVo params = new SendMailParamsVo();
            params.setType(MailTypeDef.CONTROLLER_RECLAMATION);

            MailVo mailVo = MgbServiceFactory.getService().prepareMail(params);
            MgbServiceFactory.getService().sendMail(mailVo, null);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    /**
     * Fills the model from the business model.
     */
    private void fillModel() {
        try {
            searchResult = MgbServiceFactory.getService().findTradesReclRequired();
            if (searchResult != null) {
                TableModel tableModel = TableModelFactory.createTableModel("TradeReclRequiredTable", searchResult);
                setProperty(P_TRADE_TABLE_MODEL, tableModel);
            }
        } catch (RemoteException e) {
            handleRemoteException(e);
        }

        return;
    }

    /**
     * Sets the business object where the model gets its data from.
     *
     * @param newBusinessObject DOCUMENT ME!
     */
    @Override
    public void setBusinessObject(Object newBusinessObject) {
        super.setBusinessObject(newBusinessObject);
        fillModel();
    }

    /**
     * DOCUMENT ME!
    
     */
    @Override
    public void reload() {
        fillModel();
    }

	/**
	 * @return
	 */
	public Long getTradeId(int row) {
		return searchResult[row].getId();
	}
	
	/**
	 * @return
	 */
	public Long getEmployeeId(int row) {
		return searchResult[row].getEmployeeId();
	}

}