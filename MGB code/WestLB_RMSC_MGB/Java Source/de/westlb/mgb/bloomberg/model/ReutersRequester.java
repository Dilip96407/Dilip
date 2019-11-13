/* 
 * 
 */

package de.westlb.mgb.bloomberg.model;
import java.text.Format;
import java.util.Calendar;

import org.apache.log4j.Logger;

import de.westlb.mgb.client.ui.util.DateFormat;
import de.westlb.mgb.model.impl.HistoricPriceImpl;
import de.westlb.mgb.model.impl.IntervalPriceImpl;

public class ReutersRequester implements PricingRequester {

	static Logger logger = Logger.getLogger(ReutersRequester.class);

	private final Format dateTimeFormat = new DateFormat(DateFormat.DATETIME_FORMAT_LONG);


	public ReutersRequester() {
	}

	@Override
    public HistoricPriceImpl performHistoricRequest(String requestString, String currency, Calendar requestDate, String[] sources, double referencePrice) throws PricingRequestException {
		logger.debug("Start requesting: '"+ requestString+ "' between "+ dateTimeFormat.format(requestDate.getTime())+ " and "+ dateTimeFormat.format(requestDate.getTime()));
		logger.debug("Request currency: '"+currency+"'");

		HistoricPriceImpl price = new HistoricPriceImpl(requestDate, 0.0);
		return price;
	}

	@Override
    public IntervalPriceImpl performHighLowRequest(String requestString, String currency, Calendar requestDate, String[] sources, double referencePrice) throws PricingRequestException {
		logger.debug("Start requesting: '" + requestString + "' on " + dateTimeFormat.format(requestDate.getTime()));
		logger.debug("Request currency: '"+currency+"'");

		IntervalPriceImpl price = new IntervalPriceImpl(requestDate, 0.0, 0.0);
		return price;
	}

	@Override
    public HistoricPriceImpl performRequest(String requestString, String currency, Calendar requestDate, int daysGoBack, double referencePrice) throws PricingRequestException {
		int  i = 0;
		HistoricPriceImpl result = null;
		while (i <= daysGoBack) {
			Calendar cal = (Calendar)requestDate.clone();
			cal.add(Calendar.DAY_OF_YEAR,-i);
			result = performRequest(requestString, currency, cal, referencePrice);
			i++;
		}
		return result;
	}

	@Override
    public HistoricPriceImpl performRequest(String requestString, String currency, Calendar requestDate, double referencePrice) throws PricingRequestException {
		logger.debug("Start requesting: '" + requestString + "' '" + "' on " + dateTimeFormat.format(requestDate.getTime()));
		logger.debug("Request currency: '"+currency);

		HistoricPriceImpl price = new HistoricPriceImpl(requestDate, 0.0);
		return price;
	}

	@Override
    public String[] getLastRequestedSourceArray() {
		return null;
	}
	@Override
    public String getLastRequestedString() {
		return null;
	}
	@Override
    public IntervalPriceImpl performBidAskRequest(
			String requestString, String currency, Calendar requestDate, String[] sources, double referencePrice)
			throws PricingRequestException {
		return performHighLowRequest(requestString, currency, requestDate, sources, referencePrice);
	}

}