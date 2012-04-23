package org.headsupdev.support.java;

import junit.framework.TestCase;

import java.util.Calendar;

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
}
