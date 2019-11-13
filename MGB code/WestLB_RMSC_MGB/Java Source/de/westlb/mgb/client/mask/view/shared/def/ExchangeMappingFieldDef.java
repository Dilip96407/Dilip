/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.client.mask.view.shared.def;



/**
 * Contains the field definition of an exchange object used for input validation.
 */
public interface ExchangeMappingFieldDef {
	public static final int CURRENCY_MAX_LENGTH = 3;
	public static final int ISIN_MAX_LENGTH = 16;
	public static final int ISIN_PREFIX_MAX_LENGTH = 2;
    public static final int SOURCE_SYSTEM_EXCHANGE_MAX_LENGTH = 10;
}