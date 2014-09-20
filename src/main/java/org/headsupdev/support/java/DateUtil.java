/*
 * Copyright 2010-2011 Heads Up Development Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.headsupdev.support.java;

import java.util.Calendar;
import java.util.Date;

/**
 * TODO Add documentation
 *
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
     *
     * @param calendar
     * @return a date object set to the start of current week, eg. yyyy-mm-dd 00:00:00 000
     */
    public static Date getStartOfWeekCurrent( Calendar calendar )
    {
        return getStartOfWeekCurrent( calendar, Calendar.MONDAY );
    }

    /**
     * @param calendar
     * @param dayStartOfWeek this is the same constants as Calendar.MONDAY etc
     * @return a date object set to the start of current week, eg. yyyy-mm-dd 00:00:00 000
     */
    public static Date getStartOfWeekCurrent( Calendar calendar, int dayStartOfWeek )
    {
        return getStartOfWeek( calendar, new Date(), dayStartOfWeek );
    }

    /**
     * convenience wrapper assuming the start of the week is monday
     *
     * @param calendar
     * @param date
     * @return a date object set to the start of the week, eg. yyyy-mm-dd 00:00:00 000
     */
    public static Date getStartOfWeek( Calendar calendar, Date date )
    {
        return getStartOfWeek( calendar, date, Calendar.MONDAY );
    }

    /**
     * this will return the start of the week for any date passed in
     *
     * @param calendar
     * @param date
     * @param dayStartOfWeek this is the same constants as Calendar.MONDAY etc
     * @return a date object set to the start of the week, eg. yyyy-mm-dd 00:00:00 000
     */
    public static Date getStartOfWeek( Calendar calendar, Date date, int dayStartOfWeek )
    {
        ensureStateValid( calendar, date );

        calendar.setTime( date );
        setDateToZeroHour( calendar );
        while ( calendar.get( Calendar.DAY_OF_WEEK ) != dayStartOfWeek )
        {
            calendar.add( Calendar.DATE, -1 );
        }
        return calendar.getTime();
    }

    /**
     * convenience wrapper assuming the start of the week is monday
     *
     * @param calendar
     * @return a date object set to the end of current week, eg. yyyy-mm-dd 23:59:59 999
     */
    public static Date getEndOfWeekCurrent( Calendar calendar )
    {
        return getEndOfWeekCurrent( calendar, Calendar.MONDAY );
    }

    /**
     * @param calendar
     * @param dayStartOfWeek this is the same constants as Calendar.MONDAY etc
     * @return a date object set to the end of current week, eg. yyyy-mm-dd 23:59:59 999
     */
    public static Date getEndOfWeekCurrent( Calendar calendar, int dayStartOfWeek )
    {
        return getEndOfWeek( calendar, new Date(), dayStartOfWeek );
    }

    /**
     * convenience wrapper assuming the start of the week is monday
     *
     * @param calendar
     * @param date
     * @return a date object set to the end of the week, eg. yyyy-mm-dd 23:595:59 999
     */
    public static Date getEndOfWeek( Calendar calendar, Date date )
    {
        return getEndOfWeek( calendar, date, Calendar.MONDAY );
    }

    /**
     * @param calendar
     * @param date
     * @param dayStartOfWeek this is the same constants as Calendar.MONDAY etc
     * @return a date object set to the end of the week, eg. yyyy-mm-dd 23:59:59 999
     */
    public static Date getEndOfWeek( Calendar calendar, Date date, int dayStartOfWeek )
    {
        ensureStateValid( calendar, date );
        calendar.setTime( date );
        setDateToZeroHour( calendar );
        do
        {
            calendar.add( Calendar.DATE, 1 );
        }
        while ( calendar.get( Calendar.DAY_OF_WEEK ) != dayStartOfWeek );
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
     *
     * @param calendar
     * @param date     for which we require the beginning of
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
     *
     * @param calendar
     * @param date
     * @return
     */
    public static Date getEndOfDate( Calendar calendar, Date date )
    {
        getStartOfDate( calendar, date );
        calendar.add( Calendar.DATE, 1 );
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
