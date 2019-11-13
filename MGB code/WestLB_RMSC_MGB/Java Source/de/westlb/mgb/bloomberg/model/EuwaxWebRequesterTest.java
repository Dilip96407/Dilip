package de.westlb.mgb.bloomberg.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

import org.apache.commons.codec.binary.Base64;

import de.westlb.mgb.model.HistoricPrice;
import de.westlb_systems.xaf.util.PropertyFactory;

public class EuwaxWebRequesterTest extends TestCase  {
	public void test() throws Exception {
		EuwaxWebRequester euwaxWebRequester = new EuwaxWebRequester();
		euwaxWebRequester.setDomainUser("d055625");
		euwaxWebRequester.setWlbPassword(Base64.encodeBase64("Moin0710".getBytes()));
		euwaxWebRequester.init();
		Calendar date = new GregorianCalendar();
		date.set(Calendar.MONTH, Calendar.JUNE);
		date.set(Calendar.DAY_OF_MONTH, 21);
		date.set(Calendar.HOUR_OF_DAY, 16);
		date.set(Calendar.MINUTE, 56);
		
		HistoricPrice price = euwaxWebRequester.performHistoricRequest("WLB2VR", "EUR", date, null, 6);
		assertNotNull(price);
		System.out.println("price=" + price);
	}

	@Override
    protected void setUp() throws Exception {
		super.setUp();
		PropertyFactory.put("mgb.euwaxRequester.requestIntervals", "5,15,30,90");
		PropertyFactory.put("mgb.euwaxRequester.host", "http://www.euwax-archiv.de");
		PropertyFactory.put("mgb.euwaxRequester.url", "https://www.boerse-stuttgart.de/de/toolsundservices/euwaxarchiv/gesamt-archiv.html?");
		PropertyFactory.put("mgb.euwaxRequester.wlbProxy.host", "proxy-http.westlb.sko.de");
		PropertyFactory.put("mgb.euwaxRequester.wlbProxy.port", "8080");
		PropertyFactory.put("mgb.euwaxRequester.requestParam.wkn", "wp_search");
		PropertyFactory.put("mgb.euwaxRequester.requestParam.day", "sel_crit_day");
		PropertyFactory.put("mgb.euwaxRequester.requestParam.month", "sel_crit_month");
		PropertyFactory.put("mgb.euwaxRequester.requestParam.year", "sel_crit_year");
		PropertyFactory.put("mgb.euwaxRequester.requestParam.beginHour", "sel_crit_hour_begin");
		PropertyFactory.put("mgb.euwaxRequester.requestParam.endHour", "sel_crit_hour_end");
		PropertyFactory.put("mgb.euwaxRequester.requestParam.beginMinute", "sel_crit_minute_begin");
		PropertyFactory.put("mgb.euwaxRequester.requestParam.endMinute", "sel_crit_minute_end");
		PropertyFactory.put("mgb.euwaxRequester.request.delayMillies", "5000");
		PropertyFactory.put("mgb.euwaxRequester.response.dateFormat", "HH:mm:ss");
		PropertyFactory.put("mgb.euwaxRequester.response.decimalSeparator", ",");
		PropertyFactory.put("mgb.euwaxRequester.response.timeHeader", "Uhrzeit");
		PropertyFactory.put("mgb.euwaxRequester.response.marketHeader", "Referenzmarkt");
		PropertyFactory.put("mgb.euwaxRequester.response.headerLineOffset", "3");
		PropertyFactory.put("mgb.euwaxRequester.response.idxTime", "0");
		PropertyFactory.put("mgb.euwaxRequester.response.idxBid", "1");
		PropertyFactory.put("mgb.euwaxRequester.response.idxAsk", "2");
		PropertyFactory.put("mgb.euwaxRequester.response.noDataYetMessage", "Noch keine Archive f\u00fcr dieses Datum");
	}
	
	public static void main(String[] args) {
	    try {
	        (new EuwaxWebRequesterTest()).test();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
