package de.westlb.mgb.bloomberg.model;

import java.util.Calendar;

import de.westlb.mgb.model.impl.HistoricPriceImpl;
import de.westlb.mgb.model.impl.IntervalPriceImpl;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface PricingRequester {

	public HistoricPriceImpl performHistoricRequest(
		String requestString,
		String currency,
		Calendar requestDate,
		String[] sources,
		double referencePrice)
		throws PricingRequestException;

	public IntervalPriceImpl performBidAskRequest(
			String requestString,
			String currency,
			Calendar requestDate,
			String[] sources,
			double referencePrice)
			throws PricingRequestException;

	public IntervalPriceImpl performHighLowRequest(
		String requestString,
		String currency,
		Calendar requestDate,
		String[] sources,
		double referencePrice)
		throws PricingRequestException;

	public HistoricPriceImpl performRequest(
		String requestString,
		String currency,
		Calendar requestDate,
		double referencePrice)
		throws PricingRequestException;

	public HistoricPriceImpl performRequest(
			String requestString, 
			String currency, 
			Calendar requestDate, 
			int daysGoBack,
			double referencePrice)
			throws PricingRequestException;

	public String[] getLastRequestedSourceArray();
	
	public String getLastRequestedString();
}