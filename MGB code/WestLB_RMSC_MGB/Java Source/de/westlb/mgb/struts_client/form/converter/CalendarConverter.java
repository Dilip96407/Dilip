package de.westlb.mgb.struts_client.form.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

public class CalendarConverter implements ITextConverter {
    private static final Logger logger = Logger.getLogger(CalendarConverter.class);
    private final static String formatString = "dd.MM.yyyy";
	private DateFormat inputDateFormat = new SimpleDateFormat(formatString);
	private DateFormat outputDateFormat = inputDateFormat;
		
	@Override
    public Object stringToObject(String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        
        Calendar cal = new GregorianCalendar();
        
        try {
            cal.setTime(inputDateFormat.parse(string));
        } catch (ParseException e) {
            logger.error("Wrong format of date string "+string+". Should be "+formatString);
        	cal = null;
        }
        
        return cal;	
    }

	@Override
    public String objectToString(Object object) {
		if (object == null) {
			return null;
		}
        
        if (!(object instanceof Calendar)) {
            throw new IllegalArgumentException(
                    "Argument must be of type java.util.Calendar");
        }
        
        return outputDateFormat.format(((Calendar) object).getTime());	
	}

	@Override
    public boolean isValid(String text) {
		try {
			inputDateFormat.parse(text);
		} catch (ParseException e) {
		    logger.error("Wrong format of date string "+text+". Should be "+formatString);
			return false;
		}
		return true;
	}
}
