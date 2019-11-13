/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.model.definition;

/**
 * DOCUMENT ME!
 *
 * @author WSY4148 
 */
public interface MailTypeDef {
	public static final String CONTROLLER_PREFIX = "CTR";
	public static final String TRADER_PREFIX = "TRD";
	
	// Be carefull to change the constants, because the prefix CTR* and TRD* is 
	// used in the program to determine the trade type (Controller or Trader)
    public static final String CONTROLLER_RECLAMATION = "CTR_RECLAMATION";
    public static final String CONTROLLER_REPLY = "CTR_REPLY";
    public static final String TRADER_RESPONSE = "TRD_RESPONSE";
}