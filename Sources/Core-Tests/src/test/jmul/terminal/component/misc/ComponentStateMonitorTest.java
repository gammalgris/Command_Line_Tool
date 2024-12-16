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

package test.jmul.terminal.component.misc;


import jmul.misc.state.IllegalStateTransitionException;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import jmul.terminal.component.ComponentStates;
import jmul.terminal.component.misc.ComponentStateMonitor;
import jmul.terminal.component.misc.ComponentStateMonitorImpl;


/**
 * This test suite tests a component state monitor.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class ComponentStateMonitorTest {

    /**
     * Tests creating a monitor with no components.
     */
    @Test
    public void testCreateEmptyMonitor() {

        ComponentStateMonitor monitor = new ComponentStateMonitorImpl();
        assertEquals(0, monitor.componentCount());
    }

    /**
     * Tests creating a monitor with one component.
     */
    @Test
    public void testCreateMonitorWithOneComponent() {

        String name = "a";

        String[] names = { name };

        ComponentStateMonitor monitor = new ComponentStateMonitorImpl(names);
        assertEquals(1, monitor.componentCount());

        assertTrue(monitor.existsComponent(name));
        assertEquals(null, monitor.getState(name));
    }

    /**
     * Tests creating a monitor with one two components.
     */
    @Test
    public void testCreateMonitorWithTwoComponents() {

        String name1 = "a";
        String name2 = "b";

        String[] names = { name1, name2 };

        ComponentStateMonitor monitor = new ComponentStateMonitorImpl(names);
        assertEquals(2, monitor.componentCount());

        assertTrue(monitor.existsComponent(name1));
        assertEquals(null, monitor.getState(name1));

        assertTrue(monitor.existsComponent(name2));
        assertEquals(null, monitor.getState(name2));
    }

    /**
     * Tests creating a monitor with a <code>null</code> entry.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateMonitorWithNullEntry() {

        String[] names = null;

        new ComponentStateMonitorImpl(names);
    }

    /**
     * Tests creating a monitor with a <code>name</code> name.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateMonitorWithNullName() {

        String name = null;

        String[] names = { name };

        new ComponentStateMonitorImpl(names);
    }

    /**
     * Tests creating a monitor with a <code>name</code> name.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateMonitorWithNullName2() {

        String name1 = "a";
        String name2 = null;

        String[] names = { name1, name2 };

        new ComponentStateMonitorImpl(names);
    }

    /**
     * Tests updating the initial state (i.e. <code>null</code> to a state).
     */
    @Test
    public void testUpdateInitialState() {

        String name = "a";

        String[] names = { name };

        ComponentStateMonitor monitor = new ComponentStateMonitorImpl(names);
        assertEquals(1, monitor.componentCount());

        assertTrue(monitor.existsComponent(name));
        assertEquals(null, monitor.getState(name));

        monitor.updateState(name, ComponentStates.UNKNOWN);
        assertEquals(ComponentStates.UNKNOWN, monitor.getState(name));
    }

    /**
     * Tests updating a state (i.e. state to state).
     */
    @Test
    public void testUpdateState() {

        String name = "a";

        String[] names = { name };

        ComponentStateMonitor monitor = new ComponentStateMonitorImpl(names);
        assertEquals(1, monitor.componentCount());

        assertTrue(monitor.existsComponent(name));
        assertEquals(null, monitor.getState(name));

        monitor.updateState(name, ComponentStates.UNKNOWN);
        assertEquals(ComponentStates.UNKNOWN, monitor.getState(name));

        monitor.updateState(name, ComponentStates.READY);
        assertEquals(ComponentStates.READY, monitor.getState(name));
    }

    /**
     * Tests updating a state (i.e. state to state).
     */
    @Test(expected = IllegalStateTransitionException.class)
    public void testUpdateStateWithError() {

        String name = "a";

        String[] names = { name };

        ComponentStateMonitor monitor = new ComponentStateMonitorImpl(names);
        assertEquals(1, monitor.componentCount());

        assertTrue(monitor.existsComponent(name));
        assertEquals(null, monitor.getState(name));

        monitor.updateState(name, ComponentStates.UNKNOWN);
        assertEquals(ComponentStates.UNKNOWN, monitor.getState(name));

        monitor.updateState(name, ComponentStates.STOPPED);
        assertEquals(ComponentStates.STOPPED, monitor.getState(name));
    }

    /**
     * Tests updating a state (i.e. state to <code>null</code>).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateStateWithError2() {

        String name = "a";

        String[] names = { name };

        ComponentStateMonitor monitor = new ComponentStateMonitorImpl(names);
        assertEquals(1, monitor.componentCount());

        assertTrue(monitor.existsComponent(name));
        assertEquals(null, monitor.getState(name));

        monitor.updateState(name, ComponentStates.UNKNOWN);
        assertEquals(ComponentStates.UNKNOWN, monitor.getState(name));

        monitor.updateState(name, null);
    }

}
