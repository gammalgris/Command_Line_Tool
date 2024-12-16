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

package test.jmul.terminal.component.messaging;


import jmul.messaging.StandardMessage;

import jmul.misc.state.State;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import jmul.terminal.component.ComponentStates;
import jmul.terminal.component.SystemStates;
import jmul.terminal.component.messages.StopMessage;
import jmul.terminal.component.messaging.ComponentMessageHandler;
import jmul.terminal.component.messaging.MessageHandler;
import jmul.terminal.component.messaging.MessageProcessingRule;
import jmul.terminal.component.state.ComponentStateHandler;
import jmul.terminal.component.state.StateHandler;
import jmul.terminal.component.state.StateUpdateRule;


/**
 * This test suite tests the behaviour of a component message handler.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class ComponentMessageHandlerTest {

    /**
     * Tests creating a new component message handler without any processing rules.
     */
    @Test
    public void testCreatingMessageHandler() {

        MessageHandler handler = new ComponentMessageHandler();

        assertEquals(0, handler.ruleCount());
    }

    /**
     * Tests adding a new rule with a <code>null</code> rule.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddingNullRule() {

        MessageHandler handler = new ComponentMessageHandler();
        assertEquals(0, handler.ruleCount());

        State state = ComponentStates.UNKNOWN;
        Class messageType = StopMessage.class;
        MessageProcessingRule rule = null;

        ((ComponentMessageHandler) handler).addRule(state, messageType, rule);
        assertEquals(1, handler.ruleCount());
    }

    /**
     * Tests adding a new rule with a <code>null</code> message type.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddingNullMessageType() {

        MessageHandler handler = new ComponentMessageHandler();
        assertEquals(0, handler.ruleCount());

        State state = ComponentStates.UNKNOWN;
        Class messageType = null;
        MessageProcessingRule rule = new DoNothingRule();

        ((ComponentMessageHandler) handler).addRule(state, messageType, rule);
        assertEquals(1, handler.ruleCount());
    }

    /**
     * Tests adding a new rule with a <code>null</code> state.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddingRuleForNullState() {

        MessageHandler handler = new ComponentMessageHandler();
        assertEquals(0, handler.ruleCount());

        State state = null;
        Class messageType = StopMessage.class;
        MessageProcessingRule rule = new DoNothingRule();

        ((ComponentMessageHandler) handler).addRule(state, messageType, rule);
        assertEquals(1, handler.ruleCount());
    }

    /**
     * Tests processing a message with missing essential prerequisites (i.e. component state).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testProcessingMessageWithoutStateWithEmptyMessageHandler() {

        MessageHandler handler = new ComponentMessageHandler();

        StandardMessage message = new StopMessage("sender", "receiver");

        handler.processMessage(message);
    }

    /**
     * Tests processing a message without any rule that can process the message.
     */
    @Test
    public void testProcessingMessageWithEmptyMessageHandler() {

        MessageHandler handler = new ComponentMessageHandler();

        StandardMessage message = new StopMessage("sender", "receiver");
        message = new StopMessage((StopMessage) message, SystemStates.ERROR);

        boolean result = handler.processMessage(message);
        assertEquals(false, result);
    }

    /**
     * Tests processing a message with a rule that can process the message.
     */
    @Test
    public void testSuccessfullyProcessMessage() {

        MessageHandler handler = new ComponentMessageHandler();
        ((ComponentMessageHandler) handler).addRule(SystemStates.RUNNING, StopMessage.class, new DoNothingRule());
        assertEquals(1, handler.ruleCount());

        StandardMessage message = new StopMessage("sender", "receiver");
        message = new StopMessage((StopMessage) message, SystemStates.RUNNING);

        boolean result = handler.processMessage(message);
        assertEquals(true, result);
    }

    /**
     * Tests processing a message with a rule that can process the message but the message
     * has a different state.
     */
    @Test
    public void testProcessingMessageWithDifferentState() {

        MessageHandler handler = new ComponentMessageHandler();
        ((ComponentMessageHandler) handler).addRule(SystemStates.RUNNING, StopMessage.class, new DoNothingRule());
        assertEquals(1, handler.ruleCount());

        StandardMessage message = new StopMessage("sender", "receiver");
        message = new StopMessage((StopMessage) message, SystemStates.INITIALIZATION);

        boolean result = handler.processMessage(message);
        assertEquals(false, result);
    }

    /**
     * Tests processing a message with a rule that cannot process the message.
     */
    @Test
    public void testProcessingMessageWithFailue() {

        MessageHandler handler = new ComponentMessageHandler();
        ((ComponentMessageHandler) handler).addRule(SystemStates.RUNNING, StopMessage.class, new FailProcessingRule());
        assertEquals(1, handler.ruleCount());

        StandardMessage message = new StopMessage("sender", "receiver");
        message = new StopMessage((StopMessage) message, SystemStates.RUNNING);

        boolean result = handler.processMessage(message);
        assertEquals(false, result);
    }

    /**
     * Tests processing a message with a rule that throws an exception.
     */
    @Test(expected = RuntimeException.class)
    public void testProcessingMessageWithException() {

        MessageHandler handler = new ComponentMessageHandler();
        ((ComponentMessageHandler) handler).addRule(SystemStates.RUNNING, StopMessage.class, new ErrorProcessingRule());
        assertEquals(1, handler.ruleCount());

        StandardMessage message = new StopMessage("sender", "receiver");
        message = new StopMessage((StopMessage) message, SystemStates.RUNNING);

        handler.processMessage(message);
    }

}


/**
 * A simple message processing rule that does nothing.
 *
 * @author Kristian Kutin
 */
class DoNothingRule implements MessageProcessingRule {

    @Override
    public boolean processMessage(StandardMessage message) {

        return true;
    }

}


/**
 * A simple message processing rule that cannot process messages.
 *
 * @author Kristian Kutin
 */
class FailProcessingRule implements MessageProcessingRule {

    @Override
    public boolean processMessage(StandardMessage message) {

        return false;
    }

}


/**
 * A simple message processing rule that throws an exception.
 *
 * @author Kristian Kutin
 */
class ErrorProcessingRule implements MessageProcessingRule {

    @Override
    public boolean processMessage(StandardMessage message) {

        throw new RuntimeException();
    }

}
