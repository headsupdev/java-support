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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Various utility methods for working with collections
 *
 * @author Andrew Williams
 * @since 1.6
 */
public class CollectionUtil
{
    /**
     * Make a copy of a list where the items are unique.
     *
     * @param list The list which may contain duplicates
     */
    public static <T> List<T> uniqueList( List<T> list )
    {
        if ( list == null )
        {
            return null;
        }

        List<T> unique = new ArrayList<T>( list.size() );

        for ( T next : list )
        {
            if ( !unique.contains( next ) )
            {
                unique.add( next );
            }
        }

        return unique;
    }

    /**
     * Discover if the passed collection is empty.
     * An empty collection is one that is either null or contains no elements.
     *
     * @param collection The collection to test
     * @return true if the collection is null or contains no elements
     */
    public static <T> boolean isEmpty( Collection<T> collection )
    {
        return collection == null || collection.isEmpty();
    }

    /**
     * Discover if the passed collection is not empty.
     * A non-empty collection is one that is not null and contains at least one element.
     *
     * @param collection The collection to test
     * @return true if the collection is non-null and contains elements
     */
    public static <T> boolean isNotEmpty( Collection<T> collection )
    {
        return !isEmpty( collection );
    }
}
