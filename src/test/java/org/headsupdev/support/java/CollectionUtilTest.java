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

import java.util.Arrays;
import java.util.List;

/**
 * Test methods for the collection utilities.
 * <p/>
 * Created: 19/11/2013
 *
 * @author Andrew Williams
 * @since 1.6
 */
public class CollectionUtilTest
    extends TestCase
{
    public void testNonUniqueListRemoval()
    {
        List<String> items = Arrays.asList( "a", "b", "c", "b" );

        assertEquals( Arrays.asList( "a", "b", "c" ), CollectionUtil.uniqueList( items ) );
    }

    public void testNonUniqueListRetainsOrder()
    {
        List<String> items = Arrays.asList( "a", "b", "b", "c" );

        assertEquals( Arrays.asList( "a", "b", "c" ), CollectionUtil.uniqueList( items ) );
    }

    public void testUniqueListUnchanged()
    {
        List<String> items = Arrays.asList( "a", "b", "c" );

        assertEquals( items, CollectionUtil.uniqueList( items ) );
    }

    public void testIsEmpty()
    {
        assertTrue( CollectionUtil.isEmpty( null ) );
        assertTrue( CollectionUtil.isEmpty( Arrays.asList( ) ) );

        assertFalse( CollectionUtil.isEmpty( Arrays.asList( "string" ) ) );
    }

    public void testIsNotEmpty()
    {
        assertTrue( CollectionUtil.isNotEmpty( Arrays.asList( "string" ) ) );

        assertFalse( CollectionUtil.isNotEmpty( null ) );
        assertFalse( CollectionUtil.isNotEmpty( Arrays.asList( ) ) );
    }
}
