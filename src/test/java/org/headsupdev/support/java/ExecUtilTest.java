/*
 * Copyright 2013 Heads Up Development Ltd.
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

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

/**
 * Test methods for the execution utilities. Let's make sure we're tight on our resources!
 *
 * Created: 12/04/2013
 *
 * @author Andrew Williams
 * @since 1.5
 */
public class ExecUtilTest
    extends TestCase
{
    public void testExec()
            throws IOException
    {
        List<String> commands = Arrays.asList( "echo" );

        int ret = ExecUtil.execute( commands );
        assertEquals( 0, ret );
    }

    public void testExecWithDir()
            throws IOException
    {
        List<String> commands = Arrays.asList( "ls" );

        int ret = ExecUtil.execute( commands, new File( "." ) );
        assertEquals( 0, ret );
    }

    public void testExecWithOutput()
    {
        List<String> commands = Arrays.asList( "echo", "test" );
        StringWriter out = new StringWriter();
        StringWriter err = new StringWriter();

        int ret = ExecUtil.executeLoggingExceptions( commands, new File( "." ), out, err );
        assertEquals( 0, ret );
        assertEquals( "", err.toString() );
        assertEquals( "test\n", out.toString() );
    }

    public void testThrowsException()
    {
        List<String> commands = Arrays.asList( "so-never-gonna-call-a-file-this" );
        StringWriter out = new StringWriter();
        StringWriter err = new StringWriter();

        try
        {
            ExecUtil.execute( commands, new File( "." ), out, err );
        }
        catch ( IOException e )
        {
            return;
        }

        fail();
    }

    public void testLogsException()
    {
        List<String> commands = Arrays.asList( "so-never-gonna-call-a-file-this" );
        StringWriter out = new StringWriter();
        StringWriter err = new StringWriter();

        int ret = ExecUtil.executeLoggingExceptions( commands, new File( "." ), out, err );
        assertTrue( 0 != ret );
        assertTrue( err.toString().contains( "IOException" ) );
        assertEquals( "", out.toString() );
    }
}
