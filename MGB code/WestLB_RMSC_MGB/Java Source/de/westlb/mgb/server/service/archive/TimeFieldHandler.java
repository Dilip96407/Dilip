package de.westlb.mgb.server.service.archive;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.exolab.castor.mapping.GeneralizedFieldHandler;

public class TimeFieldHandler extends GeneralizedFieldHandler {
	private static Logger logger = Logger.getLogger(TimeFieldHandler.class);
	public final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");

	@Override
    public Object convertUponGet(Object value) {
		if (value instanceof Calendar) {
			Calendar cal = (Calendar) value;
			return format.format(cal.getTime());
		}
        return value;

	}

	@Override
    public Object convertUponSet(Object value) {
		String dateStr = (String) value;
		Object result = null;
		try {
			Date date = format.parse(dateStr);
			Calendar cal = new GregorianCalendar();
			cal.setTime(date);
			result = cal;

		} catch (ParseException e) {
			logger.error(e);
		}
		return result;

	}

	@Override
    @SuppressWarnings("rawtypes")
    public Class getFieldType() {
		return Calendar.class;
	}
}
