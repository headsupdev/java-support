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

import java.io.*;
import java.nio.charset.Charset;

/**
 * Various utility methods for working with input and output
 *
 * @author Andrew Williams
 * @since 1.0
 */
public class IOUtil
{
    /**
     * Close the specified stream without throwing any exceptions.
     * A null stream will be ignored, as will any exceptions thrown when closing the passed stream.
     *
     * @param in The stream to close
     */
    public static void close( InputStream in )
    {
        if ( in == null )
        {
            return;
        }

        try
        {
            in.close();
        }
        catch ( IOException e )
        {
            // ignore
        }
    }

    /**
     * Close the specified reader without throwing any exceptions.
     * A null reader will be ignored, as will any exceptions thrown when closing the passed reader.
     *
     * @param in The reader to close
     */
    public static void close( Reader in )
    {
        if ( in == null )
        {
            return;
        }

        try
        {
            in.close();
        }
        catch ( IOException e )
        {
            // ignore
        }
    }

    /**
     * Close the specified stream without throwing any exceptions.
     * A null stream will be ignored, as will any exceptions thrown when closing the passed stream.
     *
     * @param out The stream to close
     */
    public static void close( OutputStream out )
    {
        if ( out == null )
        {
            return;
        }

        try
        {
            out.close();
        }
        catch ( IOException e )
        {
            // ignore
        }
    }

    /**
     * Copy the bytes from one stream to another.
     *
     * @param in The stream to read from
     * @param out The stream to output to
     * @throws IOException If an exception was encountered whilst reading or writing the streams
     */
    public static void copyStream( InputStream in, OutputStream out )
        throws IOException
    {
        if ( in == null || out == null )
        {
            return;
        }

        byte[] buffer = new byte[4096];
        int read;
        while ( ( read = in.read( buffer ) ) > -1 )
        {
            out.write( buffer, 0, read );
        }
    }

    /**
     * Close the specified writer without throwing any exceptions.
     * A null writer will be ignored, as will any exceptions thrown when closing the passed writer.
     *
     * @param out The writer to close
     */
    public static void close( Writer out )
    {
        if ( out == null )
        {
            return;
        }

        try
        {
            out.close();
        }
        catch ( IOException e )
        {
            // ignore
        }
    }

    /**
     * Read the contents of the given stream to a string.
     * Character conversions will be performed using the UTF-8 character set.
     *
     * @param in The input stream to read
     * @return A string representation of the contents, parsed using UTF-8
     */
    public static String toString( InputStream in )
    {
        return toString( in, 0 );
    }

    /**
     * Read the contents of the given stream to a string.
     * Character conversions will be performed using the UTF-8 character set.
     *
     * @param in The input stream to read
     * @param limit Don't read more than limit number of bytes
     * @return A string representation of the contents, parsed using UTF-8
     */
    public static String toString( InputStream in, long limit )
    {
        return IOUtil.toString( new BufferedReader( new InputStreamReader( in, Charset.forName( "UTF-8" ) ) ), limit );
    }

    /**
     * Read the contents of the given reader to a string.
     *
     * @param in The reader to read
     * @return A string representation of the contents
     */
    public static String toString( Reader in )
    {
        return toString( in, 0 );
    }

    /**
     * Read the contents of the given reader to a string.
     *
     * @param in The reader to read
     * @param limit Don't read more than limit number of characters
     * @return A string representation of the contents
     */
    public static String toString( Reader in, long limit )
    {
        return IOUtil.toString( new BufferedReader( in ), limit );
    }

    private static String toString( BufferedReader in, long limit )
    {
        StringBuffer out = new StringBuffer();

        long total = 0;
        try
        {
            String line;
            while ( ( line = in.readLine() ) != null )
            {
                if ( limit > 0 && total + line.length() > limit )
                {
                    break;
                }
                out.append( line );
                out.append( '\n' );

                total += line.length() + 1;
            }
        }
        catch ( IOException e )
        {
            // TODO report somehow... (need central logging?)
            e.printStackTrace();
        }
        finally
        {
            IOUtil.close( in );
        }

        return out.toString();
    }
}
