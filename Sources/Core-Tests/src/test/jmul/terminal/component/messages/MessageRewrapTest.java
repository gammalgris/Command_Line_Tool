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


import jmul.messaging.StandardMessage;
import jmul.messaging.StandardPropertyKeys;

import jmul.misc.state.State;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import jmul.terminal.component.ComponentStates;
import jmul.terminal.component.messages.ComponentReadyMessage;
import jmul.terminal.component.messages.ComponentStoppedMessage;
import jmul.terminal.component.messages.CustomPropertyKeys;
import jmul.terminal.component.messages.LogEntryMessage;
import jmul.terminal.component.messages.MessageHelper;
import jmul.terminal.component.messages.StopMessage;

import jmul.terminal.utils.logging.LogLevel;
import jmul.terminal.utils.logging.LogLevels;


/**
 * This test suite tests rewrapping messages of various message types.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class MessageRewrapTest {

    /**
     * Test rewrapping a component ready message.
     */
    @Test
    public void testRewrapComponentReadyMessage() {

        String sender = "a";
        String recipient = "b";
        State state = ComponentStates.UNKNOWN;

        ComponentReadyMessage original = new ComponentReadyMessage(sender, recipient);
        StandardMessage rewrapped = MessageHelper.rewrapMessageWithState(original, state);
        assertTrue(rewrapped instanceof ComponentReadyMessage);

        assertEquals(original.get(StandardPropertyKeys.SENDER), rewrapped.get(StandardPropertyKeys.SENDER));
        assertEquals(original.get(StandardPropertyKeys.RECEIVER), rewrapped.get(StandardPropertyKeys.RECEIVER));
        assertTrue(rewrapped.contains(StandardPropertyKeys.CREATION_DATE));
        assertEquals(original.get(StandardPropertyKeys.TOPIC), rewrapped.get(StandardPropertyKeys.TOPIC));

        assertEquals(state, rewrapped.get(CustomPropertyKeys.COMPONENT_STATE));
    }

    /**
     * Test rewrapping a component stopped message.
     */
    @Test
    public void testRewrapComponentStoppedMessage() {

        String sender = "a";
        String recipient = "b";
        State state = ComponentStates.UNKNOWN;

        ComponentStoppedMessage original = new ComponentStoppedMessage(sender, recipient);
        StandardMessage rewrapped = MessageHelper.rewrapMessageWithState(original, state);
        assertTrue(rewrapped instanceof ComponentStoppedMessage);

        assertEquals(original.get(StandardPropertyKeys.SENDER), rewrapped.get(StandardPropertyKeys.SENDER));
        assertEquals(original.get(StandardPropertyKeys.RECEIVER), rewrapped.get(StandardPropertyKeys.RECEIVER));
        assertTrue(rewrapped.contains(StandardPropertyKeys.CREATION_DATE));
        assertEquals(original.get(StandardPropertyKeys.TOPIC), rewrapped.get(StandardPropertyKeys.TOPIC));

        assertEquals(state, rewrapped.get(CustomPropertyKeys.COMPONENT_STATE));
    }

    /**
     * Test rewrapping a stop message.
     */
    @Test
    public void testRewrapStopMessage() {

        String sender = "a";
        String recipient = "b";
        State state = ComponentStates.UNKNOWN;

        StopMessage original = new StopMessage(sender, recipient);
        StandardMessage rewrapped = MessageHelper.rewrapMessageWithState(original, state);
        assertTrue(rewrapped instanceof StopMessage);

        assertEquals(original.get(StandardPropertyKeys.SENDER), rewrapped.get(StandardPropertyKeys.SENDER));
        assertEquals(original.get(StandardPropertyKeys.RECEIVER), rewrapped.get(StandardPropertyKeys.RECEIVER));
        assertTrue(rewrapped.contains(StandardPropertyKeys.CREATION_DATE));
        assertEquals(original.get(StandardPropertyKeys.TOPIC), rewrapped.get(StandardPropertyKeys.TOPIC));

        assertEquals(state, rewrapped.get(CustomPropertyKeys.COMPONENT_STATE));
    }

    /**
     * Test rewrapping a log entry message.
     */
    @Test
    public void testRewrapLogEntryMessage() {

        String sender = "a";
        String recipient = "b";
        LogLevel logLevel = LogLevels.DEBUG;
        String logEntry = "c";
        State state = ComponentStates.UNKNOWN;

        LogEntryMessage original = new LogEntryMessage(sender, recipient, logLevel, logEntry);
        StandardMessage rewrapped = MessageHelper.rewrapMessageWithState(original, state);
        assertTrue(rewrapped instanceof LogEntryMessage);

        assertEquals(original.get(StandardPropertyKeys.SENDER), rewrapped.get(StandardPropertyKeys.SENDER));
        assertEquals(original.get(StandardPropertyKeys.RECEIVER), rewrapped.get(StandardPropertyKeys.RECEIVER));
        assertTrue(rewrapped.contains(StandardPropertyKeys.CREATION_DATE));
        assertEquals(original.get(StandardPropertyKeys.TOPIC), rewrapped.get(StandardPropertyKeys.TOPIC));

        assertEquals(original.get(CustomPropertyKeys.LOG_LEVEL), rewrapped.get(CustomPropertyKeys.LOG_LEVEL));
        assertEquals(original.get(CustomPropertyKeys.LOG_ENTRY), rewrapped.get(CustomPropertyKeys.LOG_ENTRY));
        assertEquals(state, rewrapped.get(CustomPropertyKeys.COMPONENT_STATE));
    }

}
