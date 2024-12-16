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

package test.jmul.terminal.component.state;


import jmul.misc.state.State;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import jmul.terminal.component.ComponentStates;
import jmul.terminal.component.state.ComponentStateHandler;
import jmul.terminal.component.state.StateHandler;
import jmul.terminal.component.state.StateUpdateRule;


/**
 * This test suite tests the behaviour of a state update handler.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class ComponentStateHandlerTest {

    /**
     * Tests creating a new component state handler without any update rules.
     */
    @Test
    public void testCreatingStateHandler() {

        StateHandler handler = new ComponentStateHandler();

        assertEquals(0, handler.ruleCount());
    }

    /**
     * Tests adding a new rule with a <code>null</code> rule.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddingNullRule() {

        StateHandler handler = new ComponentStateHandler();
        assertEquals(0, handler.ruleCount());

        State state = ComponentStates.UNKNOWN;
        StateUpdateRule rule = null;

        ((ComponentStateHandler) handler).addRule(state, rule);
        assertEquals(1, handler.ruleCount());
    }

    /**
     * Tests adding a new rule with a <code>null</code> state.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddingRuleForNullState() {

        StateHandler handler = new ComponentStateHandler();
        assertEquals(0, handler.ruleCount());

        State state = null;
        StateUpdateRule rule = new DoNothingRule();

        ((ComponentStateHandler) handler).addRule(state, rule);
        assertEquals(1, handler.ruleCount());
    }

    /**
     * Test updateing a component state with a specified <code>null</code> state.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateStateWithNullState() {

        StateHandler handler = new ComponentStateHandler();

        State currentState = null;

        handler.updateState(currentState);
    }

    /**
     * Tests updating a component state without any rule that can update the specified state.
     */
    @Test
    public void testUpdateStateWithEmptyStateHandler() {

        StateHandler handler = new ComponentStateHandler();

        State currentState = ComponentStates.UNKNOWN;

        boolean result = handler.updateState(currentState);
        assertEquals(false, result);
    }

    /**
     * Tests updating a component state with a rule that can update the specified state.
     */
    @Test
    public void testSuccessfullyUpdateState() {

        StateHandler handler = new ComponentStateHandler();
        ((ComponentStateHandler) handler).addRule(ComponentStates.UNKNOWN, new DoNothingRule());
        assertEquals(1, handler.ruleCount());

        State currentState = ComponentStates.UNKNOWN;

        boolean result = handler.updateState(currentState);
        assertEquals(true, result);
    }

    /**
     * Tests updating the state but no state change happens.
     */
    @Test
    public void testUpdateStateWithoutStateChange() {

        StateHandler handler = new ComponentStateHandler();
        ((ComponentStateHandler) handler).addRule(ComponentStates.UNKNOWN, new FailUpdateRule());
        assertEquals(1, handler.ruleCount());

        State currentState = ComponentStates.UNKNOWN;

        boolean result = handler.updateState(currentState);
        assertEquals(false, result);
    }

    /**
     * Tests updating the state but an exception is thrown.
     */
    @Test(expected = RuntimeException.class)
    public void testUpdateStateWithError() {

        StateHandler handler = new ComponentStateHandler();
        ((ComponentStateHandler) handler).addRule(ComponentStates.UNKNOWN, new ErrorUpdateRule());
        assertEquals(1, handler.ruleCount());

        State currentState = ComponentStates.UNKNOWN;

        handler.updateState(currentState);
    }

}


/**
 * A simple state update rule that does nothing.
 *
 * @author Kristian Kutin
 */
class DoNothingRule implements StateUpdateRule {

    @Override
    public boolean updateState(State state) {

        return true;
    }

}


/**
 * A simple state update rule that cannot process messages.
 *
 * @author Kristian Kutin
 */
class FailUpdateRule implements StateUpdateRule {

    @Override
    public boolean updateState(State state) {

        return false;
    }

}


/**
 * A simple state update rule that throws an exception.
 *
 * @author Kristian Kutin
 */
class ErrorUpdateRule implements StateUpdateRule {

    @Override
    public boolean updateState(State state) {

        throw new RuntimeException();
    }

}
