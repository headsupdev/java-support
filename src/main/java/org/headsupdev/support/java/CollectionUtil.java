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
}
