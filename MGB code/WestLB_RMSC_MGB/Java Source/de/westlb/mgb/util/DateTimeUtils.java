package de.westlb.mgb.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author WSY4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DateTimeUtils {
	public static final long MILLIS_PER_HOUR = 1000*60*60;
	public static final long MILLIS_PER_DAY = MILLIS_PER_HOUR*24;

    
    private static final int[][] fields = {
            {Calendar.MILLISECOND},
            {Calendar.SECOND},
            {Calendar.MINUTE},
            {Calendar.HOUR_OF_DAY, Calendar.HOUR},
            {Calendar.DATE, Calendar.DAY_OF_MONTH, Calendar.AM_PM 
                /* Calendar.DAY_OF_YEAR, Calendar.DAY_OF_WEEK, Calendar.DAY_OF_WEEK_IN_MONTH */
            },
            {Calendar.MONTH},
            {Calendar.YEAR},
            {Calendar.ERA}};

    /**
     * Truncates the Calendar object by setting HOUR_OF_DAY, MINUTE, SECOND and MILLISECOND to zero. 
     * @param cal
     * @return the cloned truncated Calendar
     */
    public final static Calendar startOfDayCal(Calendar cal) {
		Calendar ret = (Calendar)cal.clone();
		ret.set(Calendar.HOUR_OF_DAY, 0);
		ret.set(Calendar.MINUTE, 0);
		ret.set(Calendar.SECOND, 0);
		ret.set(Calendar.MILLISECOND, 0);
		return ret;
	} 

    public final static Calendar startOfDayCal(Calendar cal, TimeZone timeZone) {
		Calendar ret = new GregorianCalendar(timeZone);
		ret.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		ret.set(Calendar.MONTH, cal.get(Calendar.MONTH));
		ret.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH));
		ret.set(Calendar.HOUR_OF_DAY, 0);
		ret.set(Calendar.MINUTE, 0);
		ret.set(Calendar.SECOND, 0);
		ret.set(Calendar.MILLISECOND, 0);
		return ret;
	} 

    public static Calendar truncate(Calendar date, int field) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar truncated = (Calendar) date.clone();
        modify(truncated, field, false);
        return truncated;
    }

    public static boolean isSameDate(Calendar date1, Calendar date2) {
        return date1 != null && date2 != null &&
        date1.get(Calendar.DAY_OF_YEAR) == date2.get(Calendar.DAY_OF_YEAR) &&
        date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR);
    }

	/**
     * Performs a kind of CEIL-operation on the Calendar object by setting HOUR_OF_DAY=23, MINUTE=59, SECOND=59 and MILLISECOND=999. 
	 * @param cal
	 * @return a cloned and ceiled Calendar 
	 */
	public final static Calendar endOfDayCal(Calendar cal) {
        if (cal == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
		Calendar ret = (Calendar)cal.clone();
		ret.set(Calendar.HOUR_OF_DAY, 23);
		ret.set(Calendar.MINUTE, 59);
		ret.set(Calendar.SECOND, 59);
		ret.set(Calendar.MILLISECOND, 999);
		return ret;
	} 
	
	/**
	 * Substracts one day from the current Calendar.
	 * @return current Calendar substracted by one day
	 */
	public final static Calendar yesterdayCal() {
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DATE, -1);		
		return cal;
	}

    public final static Calendar lastBusinessCal(Calendar cal) {
        Calendar newCal = new GregorianCalendar();
        newCal.setTimeInMillis(cal.getTimeInMillis());
        while (newCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || newCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            newCal.add(Calendar.DATE, -1);
        }
        return newCal;
    }

	/**
	 * @param minCal
	 * @param maxCal
	 * @return the difference in days. 0.0 if one parameter is null.
	 */
	public final static long differenceInDays(Calendar minCal, Calendar maxCal) {
	    if (minCal == null || maxCal == null) {
	        return 0;
	    }
        int tempDifference = 0;
        long difference = 0;
        Calendar earlier = null;
        Calendar later = null;
         
        if (minCal.before(maxCal)) {
            earlier = (Calendar)minCal.clone();
            later = (Calendar)maxCal.clone();
        } else {
            earlier = (Calendar)maxCal.clone();
            later = (Calendar)minCal.clone();
        }
         
        while (earlier.get(Calendar.YEAR) != later.get(Calendar.YEAR)) {
            tempDifference = 365 * (later.get(Calendar.YEAR) - earlier.get(Calendar.YEAR));
            difference += tempDifference;
            earlier.add(Calendar.DAY_OF_YEAR, tempDifference);
        }
         
        if (earlier.get(Calendar.DAY_OF_YEAR) != later.get(Calendar.DAY_OF_YEAR)) {
            tempDifference = later.get(Calendar.DAY_OF_YEAR) - earlier.get(Calendar.DAY_OF_YEAR);
            difference += tempDifference;
        }
        return difference;
	} 

    //-----------------------------------------------------------------------
    /**
     * <p>Internal calculation method.</p>
     * 
     * @param val  the calendar
     * @param field  the field constant
     * @param round  true to round, false to truncate
     * @throws ArithmeticException if the year is over 280 million
     */
    private static void modify(Calendar val, int field, boolean round) {
        if (val.get(Calendar.YEAR) > 280000000) {
            throw new ArithmeticException("Calendar value too large for accurate calculations");
        }
        
        boolean roundUp = false;
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                if (fields[i][j] == field) {
                    //This is our field... we stop looping
                    if (round && roundUp) {
                        //We need at add one to this field since the
                        //  last number causes us to round up
                        val.add(fields[i][0], 1);
                	}
                    return;
                }
            }
            //We have various fields that are not easy roundings
            int offset = 0;
            boolean offsetSet = false;
            //These are special types of fields that require different rounding rules
            switch (field) {
                case Calendar.AM_PM:
                    if (fields[i][0] == Calendar.HOUR_OF_DAY) {
                        //If we're going to drop the HOUR field's value,
                        //  we want to do this our own way.
                        offset = val.get(Calendar.HOUR_OF_DAY);
                        if (offset >= 12) {
                            offset -= 12;
                        }
                        roundUp = offset > 6;
                        offsetSet = true;
                    }
                    break;
            }
            if (!offsetSet) {
                int min = val.getActualMinimum(fields[i][0]);
                int max = val.getActualMaximum(fields[i][0]);
                //Calculate the offset from the minimum allowed value
                offset = val.get(fields[i][0]) - min;
                //Set roundUp if this is more than half way between the minimum and maximum
                roundUp = offset > ((max - min) / 2);
            }
            //We need to remove this field
            val.set(fields[i][0], val.get(fields[i][0]) - offset);
        }
        throw new IllegalArgumentException("The field " + field + " is not supported");

    }

    
    public static long convertDaysToMilliseconds(int days) { 
        return 1000L*3600*24*days; 
    } 
}
