/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.model.definition;

/**
 * Contains constants for mail templates located in the mail.properties file.
 * 
 */
public interface MailDefinition {
	public static final String RECLAMATION_CONTENT_TEMPLATE		= "mail.mgb.content";
	public static final String RECLAMATION_SUBJECT_TEMPLATE		= "mail.mgb.subject";

	public static final String FLD_RESPONSE_TIME_TIMIT			= "responseTimeLimit";
	public static final String FLD_SND_FIRST_NAME				= "sndFirstName";
	public static final String FLD_SND_LAST_NAME				= "sndLastName";
	public static final String FLD_SND_FULL_NAME				= "sndFullName";
	public static final String FLD_SND_PHONE					= "sndPhone";
	public static final String FLD_SND_EMAIL					= "sndEmail";
	
	public static final String FLD_TRD_ID						= "trdId";
	public static final String FLD_TRD_TRADE_ID					= "trdTradeId";
    public static final String FLD_TRD_INSTRUMENT_NAME          = "trdInstrumentName";
    public static final String FLD_TRD_COUNTERPARTY             = "trdCounterparty";
	public static final String FLD_TRD_SOURCE_SYSTEM			= "trdSourceSystem";
	public static final String FLD_TRD_TRADE_TIME				= "trdTradeTime";
	public static final String FLD_TRD_SYSTEM_TIME				= "trdSystemTime";
	public static final String FLD_TRD_VOLUMNE					= "trdVolume";
	public static final String FLD_TRD_PRICE					= "trdPrice";
	public static final String FLD_TRD_MARKET_PRICE				= "trdMarketPrice";
	public static final String FLD_TRD_FULL_NAME				= "trdFullName";
	public static final String FLD_TRD_IS_LATE_ENTRY			= "trdIsLateEntry";
	public static final String FLD_TRD_BLOOMBERG_ID				= "trdBloombergId";
	public static final String FLD_TRD_HAS_BLOOMBERG_ID			= "trdHasBloombergId";
	
	public static final String FLD_MAN_STATE_SHORT_DESCR		= "manStateShortDescription";
	public static final String FLD_MAN_STATE_COMMENT			= "manStateComment";
	public static final String FLD_MAN_STATE_FULL_NAME			= "manStateFullname";
	public static final String FLD_MAN_IS_RECLAMATION			= "trdIsReclamation";

	public static final String FLD_RECL_STATE_SHORT_DESCR        = "reclStateShortDescription";
	public static final String FLD_RECL_STATE_COMMENT            = "reclStateComment";
	public static final String FLD_RECL_STATE_FULL_NAME          = "reclStateFullname";

	public static final String FLD_MANDANT						= "mandant";
	public static final String FLD_PARENT_MAIL_SUBJECT			= "parentMailSubject";
	public static final String FLD_PARENT_MAIL_TEXT				= "parentMailText";
}