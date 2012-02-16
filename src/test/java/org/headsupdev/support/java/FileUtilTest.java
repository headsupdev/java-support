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

import java.io.File;

/**
 * Some testing of the FileUtil class
 *
 * @author Andrew Williams
 * @since 1.0
 */
public class FileUtilTest
    extends TestCase
{
    public void testLookupInPath()
    {
        File javaExe = FileUtil.lookupInPath( "java" );
        assertNotNull( javaExe );
    }

    public void testLookupParentInPath()
    {
        File javaDir = FileUtil.lookupParentInPath( "java" );
        assertNotNull( javaDir );
    }

    public void testLookupGrandparentInPath()
    {
        File javaDir = FileUtil.lookupGrandparentInPath( "java" );
        assertNotNull( javaDir );
    }

    public void testLookupInPathLogic()
    {
        File javaExe = FileUtil.lookupInPath( "java" );
        File javaDir = FileUtil.lookupParentInPath( "java" );
        File javaParentDir = FileUtil.lookupGrandparentInPath( "java" );

        assertEquals( javaExe.getParentFile(), javaDir );
        assertEquals( javaDir.getParentFile(), javaParentDir );
    }
}
