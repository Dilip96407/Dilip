/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.client.mask.view.shared.def;

import java.math.BigDecimal;


/**
 * Contains the field definition of an addon object used for input validation.
 */
public interface AddonFieldDef {
	public static final int 		CONDITION_MAX_LENGTH				= 40;
	public static final int 		TIME_TOLERANCE_MAX_DIGITS 			= 4;
	public static final BigDecimal	PRICE_TOLERANCE_MIN_VALUE			= new BigDecimal(0);
	public static final BigDecimal 	PRICE_TOLERANCE_MAX_VALUE			= null;
	public static final int			PRICE_TOLERANCE_MAX_DECIMAL_PLACES	= 3;
	public static final int 		PRICE_TOLERANCE_MAX_DIGITS			= 5;
}