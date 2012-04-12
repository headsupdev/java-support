package org.headsupdev.support.java;

import java.util.Calendar;
import java.util.Date;

/**
 * TODO Add documentation
 * <p/>
 * Created: 10/04/2012
 *
 * @author roberthewitt
 * @since 1.2
 */
public class DateUtil
{

    private static void setDateToZeroHour( Calendar calendar )
    {
        calendar.set( Calendar.HOUR_OF_DAY, 0 );
        calendar.set( Calendar.MINUTE, 0 );
        calendar.set( Calendar.SECOND, 0 );
        calendar.set( Calendar.MILLISECOND, 0 );
    }

    /**
     * Convenience wrapper for assuming the start of the week is monday
     * @param calendar
     * @return
     */
    public static Date getStartOfWeek( Calendar calendar )
    {
        return getStartOfWeek( calendar, Calendar.MONDAY );
    }

    /**
     *
     * @param calendar
     * @param dayStartOfWeek this is the same constants as Calendar.MONDAY etc
     * @return a date object set to the start of the week, eg. 2012-02-14 00:00:00 000
     */
    public static Date getStartOfWeek( Calendar calendar, int dayStartOfWeek )
    {
        ensureStateValid( calendar );

        calendar.setTime( new Date() );
        while ( calendar.get( Calendar.DAY_OF_WEEK ) != dayStartOfWeek )
        {
            calendar.add( Calendar.DAY_OF_YEAR, -1 );
        }
        setDateToZeroHour( calendar );
        return calendar.getTime();
    }

    /**
     * convenience wrapper assuming the start of the week is monday
     * @param calendar
     * @return
     */
    public static Date getEndOfWeek( Calendar calendar )
    {
        return getEndOfWeek( calendar, Calendar.MONDAY );
    }

    /**
     *
     * @param calendar
     * @param dayStartOfWeek this is the same constants as Calendar.MONDAY etc
     * @return a date object set to the end of the week, eg. 2012-02-14 24:00:00 000
     */
    public static Date getEndOfWeek( Calendar calendar, int dayStartOfWeek )
    {
        getStartOfWeek( calendar, dayStartOfWeek );
        calendar.add( Calendar.WEEK_OF_YEAR, 1 );
        calendar.add( Calendar.MILLISECOND, -1 );
        return calendar.getTime();
    }

    public static Date getStartOfToday( Calendar calendar )
    {
        return getStartOfDate( calendar, new Date() );
    }

    public static Date getEndOfToday( Calendar calendar )
    {
        return getEndOfDate( calendar, new Date() );
    }

    /**
     * Both arguments are required and will throw exception if either are null
     * @param calendar
     * @param date for which we require the beginning of
     * @return
     */
    public static Date getStartOfDate( Calendar calendar, Date date )
    {
        ensureStateValid( calendar, date );

        calendar.setTime( date );
        setDateToZeroHour( calendar );
        return calendar.getTime();
    }

    /**
     * Both arguments are required and will throw exception if either are null
     * @param calendar
     * @param date
     * @return
     */
    public static Date getEndOfDate( Calendar calendar, Date date )
    {
        getStartOfDate( calendar, date );
        calendar.add( Calendar.DAY_OF_YEAR, 1 );
        calendar.add( Calendar.MILLISECOND, -1 );
        return calendar.getTime();
    }


    private static void ensureStateValid( Calendar calendar, Date date )
    {
        ensureStateValid( calendar );
        if ( date == null )
        {
            throw new IllegalStateException( "Date object was null, this is required" );
        }
    }

    private static void ensureStateValid( Calendar calendar )
    {
        if ( calendar == null )
        {
            throw new IllegalStateException( "Calendar object was null, this is required" );
        }
    }

}
