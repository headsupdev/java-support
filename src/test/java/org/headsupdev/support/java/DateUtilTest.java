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

import junit.framework.TestCase;

import java.util.Calendar;
import java.util.Date;

/**
 * TODO Add documentation
 * <p/>
 * Created: 20/04/2012
 *
 * @author roberthewitt
 * @since 1.2
 */
public class DateUtilTest extends TestCase
{
    public void testEndOfDate()
    {
        // test +1 millisecond to the end of date produces a different day
        Calendar calendar = Calendar.getInstance();
        DateUtil.getEndOfToday( calendar );

        final int dayOfWeek = calendar.get( Calendar.DAY_OF_WEEK );
        calendar.add( Calendar.MILLISECOND, 1 );
        assertTrue( dayOfWeek != calendar.get( Calendar.DAY_OF_WEEK ) );
    }

    public void testStartOfDate()
    {
        // test -1 millisecond to the start of date produces a different day
        Calendar calendar = Calendar.getInstance();
        DateUtil.getStartOfToday( calendar );

        final int dayOfWeek = calendar.get( Calendar.DAY_OF_WEEK );
        calendar.add( Calendar.MILLISECOND, -1 );
        assertTrue( dayOfWeek != calendar.get( Calendar.DAY_OF_WEEK ) );
    }

    public void testWeeks()
    {
        Calendar calendar = Calendar.getInstance();
        DateUtil.getStartOfWeekCurrent( calendar );
        final Date startOfThisWeek = calendar.getTime();

        DateUtil.getEndOfWeekCurrent( calendar );
        final Date endOfThisWeek = calendar.getTime();

        assertTrue( endOfThisWeek.after( startOfThisWeek ) );

        // beginning of next week is after end of this week.
        calendar.setTime( new Date() );
        calendar.add( Calendar.WEEK_OF_YEAR, 1 );
        final Date startOfNextWeek = DateUtil.getStartOfWeek( calendar, calendar.getTime() );

        assertTrue( startOfNextWeek.after( endOfThisWeek ) );
    }
}
