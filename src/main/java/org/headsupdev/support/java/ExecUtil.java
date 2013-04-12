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

import java.io.*;
import java.util.List;

/**
 * A utility class that handles with the streaming data and resource management when executing commands.
 * <p/>
 * Created: 12/04/2013
 *
 * @author Andrew Williams
 * @since 1.5
 */
public class ExecUtil
{
    public static final int EXECUTION_FAILED = -1;
    public static final int EXECUTION_INTERRUPTED = -2;

    /**
     * Execute the given command in the current application's working directory.
     *
     * @param commands The command to execute, where item 0 is the executable and the others are parameters
     * @return The return code of the command
     * @throws IOException If the command could not be executed
     */
    public static int execute( List<String> commands )
            throws IOException
    {
        return execute( commands, null );
    }

    /**
     * Execute the given command in the specified working directory.
     *
     * @param commands The command to execute, where item 0 is the executable and the others are parameters
     * @param dir The working directory to execute in
     * @return The return code of the command
     * @throws IOException If the command could not be executed
     */
    public static int execute( List<String> commands, File dir )
            throws IOException
    {
        return execute( commands, dir, null, null );
    }

    /**
     * Execute the given command in the specified working directory.
     *
     * @param commands The command to execute, where item 0 is the executable and the others are parameters
     * @param dir The working directory to execute in
     * @param output A writer for copying the standard output to
     * @param error A writer for copying the standard error to
     * @return The return code of the command
     * @throws IOException If the command could not be executed
     */
    public static int execute( List<String> commands, File dir, Writer output, Writer error )
            throws IOException
    {
        ExecConfig config = new ExecConfig( commands, dir, output, error );
        return config.run();
    }

    /**
     * Execute the given command in the current application's working directory.
     * Any exceptions encountered will be appended to the error writer.
     *
     * @param commands The command to execute, where item 0 is the executable and the others are parameters
     * @param output A writer for copying the standard output to
     * @param error A writer for copying the standard error to
     * @return The return code of the command
     */
    public static int executeLoggingExceptions( List<String> commands, Writer output, Writer error )
    {
        return executeLoggingExceptions( commands, null, output, error );
    }

    /**
     * Execute the given command in the specified working directory.
     * Any exceptions encountered will be appended to the error writer.
     *
     * @param commands The command to execute, where item 0 is the executable and the others are parameters
     * @param dir The working directory to execute in
     * @param output A writer for copying the standard output to
     * @param error A writer for copying the standard error to
     * @return The return code of the command
     */
    public static int executeLoggingExceptions( List<String> commands, File dir, Writer output, Writer error )
    {
        ExecConfig config = new ExecConfig( commands, dir, output, error );
        try
        {
            return config.run();
        }
        catch ( IOException e )
        {
            if ( error != null )
            {
                e.printStackTrace( new PrintWriter( error ) );
            }

            return ExecUtil.EXECUTION_FAILED;
        }
    }
}

class ExecConfig
{
    private List<String> commands;
    private File dir;

    private Writer outWriter, errWriter;

    private StreamGobbler sout, serr;
    private Process process;

    public ExecConfig( List<String> commands, File dir, Writer output, Writer error )
    {
        this.commands = commands;
        this.dir = dir;

        this.outWriter = output;
        this.errWriter = error;
    }

    public int run()
            throws IOException
    {
        int ret = ExecUtil.EXECUTION_FAILED;
        try
        {
            process = Runtime.getRuntime().exec( commands.toArray( new String[commands.size()] ), null, dir );

            sout = new StreamGobbler( new InputStreamReader( process.getInputStream() ), outWriter );
            serr = new StreamGobbler( new InputStreamReader( process.getErrorStream() ), errWriter );

            serr.start();
            sout.start();

            ret = process.waitFor();
        }
        catch ( InterruptedException e )
        {
            // TODO use this hook when we cancel the process
            ret = ExecUtil.EXECUTION_INTERRUPTED;
        }
        finally
        {
            if ( process != null )
            {
                // defensively try to close the gobblers
                waitForStreamGobblersToComplete();

                IOUtil.close( process.getOutputStream() );
                IOUtil.close( process.getErrorStream() );
                IOUtil.close( process.getInputStream() );
                process.destroy();
            }
        }

        return ret;
    }

    private void waitForStreamGobblersToComplete()
    {
        // defensively try to close the gobblers
        // check that our gobblers are finished...
        while ( !isComplete( sout ) || !isComplete( serr ) )
        {
            try
            {
                Thread.sleep( 500 );
            }
            catch ( InterruptedException e )
            {
                // we were just trying to tidy up...
            }
        }
    }

    private boolean isComplete( StreamGobbler gobbler )
    {
        return ( gobbler == null || gobbler.isComplete() );
    }
}

class StreamGobbler
        extends Thread
{
    private Reader in;
    private Writer out;

    private boolean complete = false;

    public StreamGobbler( Reader in, Writer out )
    {
        this.in = in;
        this.out = out;
    }

    public void run()
    {
        try
        {
            BufferedReader reader = new BufferedReader( in );
            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                if ( out != null )
                {
                    out.write( line );
                    out.write( '\n' );
                }
            }
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }

        complete = true;
    }

    public boolean isComplete()
    {
        return complete;
    }
}