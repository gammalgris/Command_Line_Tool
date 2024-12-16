/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * e-mail: kristian.kutin@arcor.de
 */

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.terminal.component.messages;


import java.util.Map;

import jmul.messaging.PropertyKey;


/**
 * A utility class to create entry instances.
 *
 * @author Kristian Kutin
 */
public final class EntryHelper {

    /**
     * The default constructor.
     */
    private EntryHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new entry with the specified key and value.
     *
     * @param key
     *        a property key
     * @param value
     *        a value
     *
     * @return a new entry
     */
    public static Map.Entry<PropertyKey, Object> newEntry(PropertyKey key, Object value) {

        return new Map.Entry<PropertyKey, Object>() {
            @Override
            public PropertyKey getKey() {

                return key;
            }

            @Override
            public Object getValue() {

                return value;
            }

            @Override
            public Object setValue(Object value) {

                return null;
            }
        };
    }


}

