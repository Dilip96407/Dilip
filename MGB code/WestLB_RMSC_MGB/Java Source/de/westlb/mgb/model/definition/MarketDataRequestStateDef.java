package de.westlb.mgb.model.definition;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface MarketDataRequestStateDef {

	public static final String OK = "OK";

	public static final String OK_PRICE_UNVALIDATED = OK+"_PRICE_UNVALIDATED";

	public static final String OK_NO_PRICE = OK+"_NO_PRICE";

	public static final String TIMEOUT = "TIMEOUT";

	public static final String WARNING = "WARNING";

	public static final String ERROR = "ERROR";

	public static final String COMERROR = "COMERROR";

	public static final String RETRY = "RETRY";

}
