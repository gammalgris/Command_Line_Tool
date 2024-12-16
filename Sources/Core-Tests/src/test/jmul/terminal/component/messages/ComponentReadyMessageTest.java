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


import java.util.Date;

import jmul.concurrent.threads.ThreadHelper;

import jmul.messaging.StandardPropertyKeys;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import jmul.terminal.component.messages.ComponentReadyMessage;


/**
 * This test suite tests the properties of a component ready message.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class ComponentReadyMessageTest {

    /**
     * Tests all standard properties of a component ready message, except the creation date.
     */
    @Test
    public void testCreatingMessage() {

        String sender = "sender";
        String receiver = "receiver";

        ComponentReadyMessage message = new ComponentReadyMessage(sender, receiver);

        assertTrue(message.contains(StandardPropertyKeys.SENDER));
        assertTrue(message.contains(StandardPropertyKeys.RECEIVER));
        assertTrue(message.contains(StandardPropertyKeys.TOPIC));

        assertEquals(sender, message.senderName());
        assertEquals(receiver, message.receiverName());
        assertEquals(ComponentReadyMessage.READY_TOPIC, message.topic());

        assertEquals(sender, message.get(StandardPropertyKeys.SENDER));
        assertEquals(receiver, message.get(StandardPropertyKeys.RECEIVER));
        assertEquals(ComponentReadyMessage.READY_TOPIC, message.get(StandardPropertyKeys.TOPIC));
    }

    /**
     * Tests the creation date property of a component ready message.
     */
    @Test
    public void testCreationDate() {

        Date now = new Date();
        ThreadHelper.sleep(1L);

        String sender = "sender";
        String receiver = "receiver";

        ComponentReadyMessage message = new ComponentReadyMessage(sender, receiver);

        assertTrue(message.contains(StandardPropertyKeys.CREATION_DATE));

        Date date = message.date();
        assertTrue(date.after(now));

        Date date2 = (Date) message.get(StandardPropertyKeys.CREATION_DATE);
        assertTrue(date2.after(now));
        assertEquals(date, date2);
    }

}
