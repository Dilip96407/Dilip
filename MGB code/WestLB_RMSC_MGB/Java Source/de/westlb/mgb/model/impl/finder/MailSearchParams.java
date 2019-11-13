/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.model.impl.finder;

/**
 * Parameter object that can be used for the mail search.
 *
 * @author WSY4148 
 */
public class MailSearchParams {
    // The nt id of the recipient. 
    private String recipientNtId;
    // Indicates if only mails corresponding to a trade
    // with open relcamation should be returned.
    private boolean reclamationOpenOnly;
    // The mgb id of the corresponding trade.
    private Long tradeId;

	/**
	 * Getter for property recipientNtId
	 */
	public String getRecipientNtId() {
		return recipientNtId;
	}

	/**
	 * Getter for property reclamationOpenOnly
	 */
	public boolean isReclamationOpenOnly() {
		return reclamationOpenOnly;
	}

	/**
	 * Setter for property recipientNtId
	 */
	public void setRecipientNtId(String string) {
		recipientNtId = string;
	}

	/**
	 * Setter for property reclamationOpenOnly
	 */
	public void setReclamationOpenOnly(boolean b) {
		reclamationOpenOnly = b;
	}


	/**
	 * @return
	 */
	public Long getTradeId() {
		return tradeId;
	}

	/**
	 * @param long1
	 */
	public void setTradeId(Long long1) {
		tradeId = long1;
	}

}