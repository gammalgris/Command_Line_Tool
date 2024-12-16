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

package test.jmul.terminal.component.messages;


import java.util.Map;

import jmul.messaging.PropertyKey;

import jmul.misc.state.State;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import jmul.terminal.component.messages.CustomPropertyKeys;
import jmul.terminal.component.ComponentStates;
import jmul.terminal.component.SystemStates;
import jmul.terminal.component.messages.EntryHelper;


/**
 * This test suite tests the properties of custom entries.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class EntryTest {

    /**
     * Tests creating an entry with a system state value.
     */
    @Test
    public void testCreateEntryWithSystemState() {

        PropertyKey key = CustomPropertyKeys.COMPONENT_STATE;
        State state = SystemStates.ERROR;

        Map.Entry<PropertyKey, Object> entry = EntryHelper.newEntry(key, state);

        assertEquals(key, entry.getKey());
        assertEquals(state, entry.getValue());
    }

    /**
     * Tests creating an entry with a component state value.
     */
    @Test
    public void testCreateEntryWithComponentState() {

        PropertyKey key = CustomPropertyKeys.COMPONENT_STATE;
        State state = ComponentStates.ERROR;

        Map.Entry<PropertyKey, Object> entry = EntryHelper.newEntry(key, state);

        assertEquals(key, entry.getKey());
        assertEquals(state, entry.getValue());
    }

    /**
     * The custom entries are immutable. This test confirms that the entry value
     * cannot be changed.
     */
    @Test
    public void testChangeValue() {

        PropertyKey key = CustomPropertyKeys.COMPONENT_STATE;
        State state1 = ComponentStates.ERROR;
        State state2 = ComponentStates.READY;

        Map.Entry<PropertyKey, Object> entry = EntryHelper.newEntry(key, state1);

        assertEquals(key, entry.getKey());
        assertEquals(state1, entry.getValue());

        entry.setValue(state2);

        assertEquals(key, entry.getKey());
        assertEquals(state1, entry.getValue());
    }

}
