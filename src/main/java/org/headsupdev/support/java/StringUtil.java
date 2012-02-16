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

/**
 * Various utility methods for working with strings
 *
 * @author Andrew Williams
 * @since 1.0
 */
public class StringUtil
{
    /**
     * Discover if the passed string is empty.
     * An empty string is one that is either zero characters in length or containing only whitespace.
     * A null string is also considered as empty and so will return true also.
     *
     * @param string The string to test
     * @return true if the string is null, of zero length or containing only whitespace.
     */
    public static boolean isEmpty( String string )
    {
        if ( string == null )
        {
            return true;
        }

        if ( string.length() == 0 )
        {
            return true;
        }

        return string.trim().length() == 0;
    }

    /**
     * Convert the string to title case - that is a string that starts with an upper case letter and all other
     * characters are in lower case.
     *
     * Note that this is identical to calling <code>StringUtil.toTitleCase( string, true )</code>.
     *
     * @param string The string to convert to title case
     * @return A new string with the case converted to title case
     */
    public static String toTitleCase( String string )
    {
        return toTitleCase( string, true );
    }

    /**
     * Convert the string to title case - that is a string that starts with an upper case letter and all other
     * characters are (optionally) in lower case.
     *
     * The second parameter specifies whether the remaining string should be converted to lower case.
     *
     * @param string The string to convert to title case
     * @param lower Determines if the remaining (all but first) characters should be forced to lower case.
     *   Passing false means that the source string's case will be respected.
     * @return A new string with the case converted to title case
     */
    public static String toTitleCase( String string, boolean lower )
    {
        if ( StringUtil.isEmpty( string ) )
        {
            return string;
        }

        StringBuffer out = new StringBuffer();
        out.append( string.substring( 0, 1 ).toUpperCase() );
        if ( lower )
        {
            out.append( string.substring( 1 ).toLowerCase() );
        }
        else
        {
            out.append( string.substring( 1 ) );
        }

        return out.toString();
    }
}
