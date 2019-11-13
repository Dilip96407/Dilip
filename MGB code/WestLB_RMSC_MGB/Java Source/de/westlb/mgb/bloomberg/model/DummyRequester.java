/* 
 * 
 */

package de.westlb.mgb.bloomberg.model;
import java.text.Format;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import de.westlb.mgb.client.ui.util.DateFormat;
import de.westlb.mgb.model.impl.HistoricPriceImpl;
import de.westlb.mgb.model.impl.IntervalPriceImpl;
import de.westlb.mgb.util.DateTimeUtils;

public class DummyRequester implements PricingRequester {

	static Logger logger = Logger.getLogger(DummyRequester.class);

	private final Format dateTimeFormat = new DateFormat(DateFormat.DATETIME_FORMAT_LONG);

	private int i = 0;

	private int numberOfDummyException = 10;

	private double decimalPercent = 0.01d;

	private int minutes = 15;
	
	private int numberOfNullReturn = 7;
	
	private String requestString = null;

	public DummyRequester() {
	}

	public DummyRequester(double decimalPercent, int minutes) {
		this.decimalPercent = decimalPercent;
		this.minutes = minutes;
	}


	@Override
    public String[] getLastRequestedSourceArray() {
		return null;
	}

	@Override
    public String getLastRequestedString() {
		return requestString;
	}

	@Override
    public HistoricPriceImpl performHistoricRequest(String requestString, String currency, Calendar requestDate, String[] sources, double referencePrice) throws PricingRequestException {
		this.requestString = requestString;
		logger.debug(
			"Start requesting: '"
				+ requestString
				+ "' between "
				+ dateTimeFormat.format(requestDate.getTime())
				+ " and "
				+ dateTimeFormat.format(requestDate.getTime()));
		logger.debug("Request currency: '"+currency+"'");
		if (sources != null) {
		    logger.debug("Request sources "+Arrays.asList(sources));
		}
		sleep();
		i++;
		if ((i % numberOfNullReturn) == 0) {
		    logger.debug("Setting dummy price to null");
	        return null;
	    }
		if ((i % numberOfDummyException) == 0) {
			throw new PricingRequestException("Just a dummy exception (each " + numberOfDummyException + "th)");
		}
		Random random = new Random();

		double f = (random.nextDouble() * 2.0 - 1.0) * decimalPercent;
		double dummyPrice = Math.max(0.0, referencePrice * (1 + f));

		int timediff = random.nextInt(2 * minutes) - minutes;
		GregorianCalendar dummyCal = (GregorianCalendar) requestDate.clone();
		dummyCal.add(Calendar.MINUTE, timediff);

		HistoricPriceImpl price = new HistoricPriceImpl(dummyCal, dummyPrice);
		return price;
	}

	@Override
    public IntervalPriceImpl performHighLowRequest(String requestString, String currency, Calendar requestDate, String[] sources, double referencePrice) throws PricingRequestException {
		this.requestString = requestString;
		logger.debug("Start requesting: '" + requestString + "' on " + dateTimeFormat.format(requestDate.getTime()));
		logger.debug("Request currency: '"+currency+"'");
		if (sources != null) {
		    logger.debug("Request sources "+Arrays.asList(sources));
		}
		sleep();
		i++;
		if ((i % numberOfNullReturn) == 0) {
		    logger.debug("Setting dummy price to null");
	        return null;
	    }
		if ((i % numberOfDummyException) == 0) {
			throw new PricingRequestTimeOutException("Just a dummy exception (each " + numberOfDummyException + "th)");
		}
		Random random = new Random();

		double f = random.nextDouble() * decimalPercent;
		double dummyMinPrice = Math.max(0.0, referencePrice * (1 - f));
		double dummyMaxPrice = Math.max(0.0, referencePrice * (1 + f));

		Calendar dummyCal = DateTimeUtils.startOfDayCal(requestDate, TimeZone.getTimeZone("Europe/Berlin"));
		IntervalPriceImpl price = new IntervalPriceImpl(dummyCal, dummyMinPrice, dummyMaxPrice);
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
		this.requestString = requestString;
		logger.debug("Start requesting: '" + requestString + "' '" + "' on " + dateTimeFormat.format(requestDate.getTime()));
		logger.debug("Request currency: '"+currency);
		sleep();
		i++;
		if ((i % numberOfNullReturn) == 0) {
		    logger.debug("Setting dummy price to null");
		    return null;
	    }
		if ((i % numberOfDummyException) == 0) {
			throw new PricingRequestException("Just a dummy exception (each " + numberOfDummyException + "th)");
		}
		Random random = new Random();

		double f = (random.nextDouble() * 2.0 - 1.0) * decimalPercent;
		double dummyPrice = Math.max(0.0, referencePrice * (1 + f));

		int timediff = random.nextInt(2 * minutes) - minutes;
		GregorianCalendar dummyCal = (GregorianCalendar) requestDate.clone();
		dummyCal.add(Calendar.MINUTE, timediff);
		HistoricPriceImpl price = new HistoricPriceImpl(dummyCal, dummyPrice);
		return price;
	}

	private void sleep() {
		try {
			Thread.sleep(20l);
		} catch (InterruptedException e) {
		}
	}

	@Override
    public IntervalPriceImpl performBidAskRequest(
			String requestString, String currency, Calendar requestDate, String[] sources, double referencePrice)
			throws PricingRequestException {
		return performHighLowRequest(requestString, currency, requestDate, sources, referencePrice);
	}

}