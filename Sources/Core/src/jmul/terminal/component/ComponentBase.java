/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2023  Kristian Kutin
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

package jmul.terminal.component;


import jmul.messaging.Message;
import jmul.messaging.MessageListener;
import jmul.messaging.MessageQuery;
import jmul.messaging.MessageQueryResult;
import jmul.messaging.MessageSender;
import jmul.messaging.Messagebus;

import jmul.terminal.component.core.ComponentAlreadyConnectedException;
import jmul.terminal.component.core.MissingMessagebusException;


/**
 * A base implementation of a component.
 *
 * @author Kristian Kutin
 */
public abstract class ComponentBase implements MessageListener, MessageSender {

    /**
     * The name for this core component.
     */
    private final String name;

    /**
     * A messaging queue.
     */
    private volatile Messagebus messagebus;

    /**
     * Creates a new instance according to the specified component name.
     *
     * @param name
     *        the name for this component
     */
    public ComponentBase(String name) {

        super();

        this.name = checkName(name);
    }

    /**
     * Checks the specified component name.
     *
     * @param name
     *        the name of a component
     *
     * @return the specified component name
     */
    protected static String checkName(String name) {

        if (name == null) {

            throw new IllegalArgumentException("No name (null) was specified!");
        }

        String normalizedName = name.trim();
        if (normalizedName.isEmpty()) {

            throw new IllegalArgumentException("No component name (empty string) was specified!");
        }

        return name;
    }

    /**
     * The name for this core component.
     *
     * @return a name
     */
    public String name() {

        return name;
    }

    /**
     * Connect this component with a messagebus.
     *
     * @param messagebus
     *        a messagebus
     */
    public void connect(Messagebus messagebus) {

        checkMessagebus(messagebus);

        if (this.messagebus != null) {

            throw new ComponentAlreadyConnectedException();
        }

        this.messagebus = messagebus;
    }

    /**
     * Checks if this component is connected to a messagebus.
     *
     * @return <code>true</code> is conncted to a messagebus, else <code>false</code>
     */
    public boolean isConnected() {

        return messagebus != null;
    }

    /**
     * Returns a reference to the messagebus.
     *
     * @return a reference to the messagebus
     */
    public Messagebus messagebus() {

        return messagebus;
    }

    /**
     * Check the messagebus for new messages.
     *
     * @return the query result
     */
    @Override
    public MessageQueryResult fetchMessage() {

        if (messagebus == null) {

            throw new MissingMessagebusException();
        }

        MessageQuery query = new MessageQuery(this);
        return messagebus.fetch(query);
    }

    /**
     * Sends the specified message (i.e. puts the message into a message queue).
     *
     * @param message
     *        a message
     */
    @Override
    public void sendMessage(Message message) {

        checkMessage(message);

        if (messagebus == null) {

            throw new MissingMessagebusException();
        }

        messagebus.send(message);
    }

    /**
     * Checks the specified message.
     *
     * @param message
     *        a message
     *
     * @return the specified message
     */
    private static Message checkMessage(Message message) {

        if (message == null) {

            throw new IllegalArgumentException("No message (null) was specified!");
        }

        return message;
    }

    /**
     * Checks the specified messagebus.
     *
     * @param messagebus
     *        a messagebus
     *
     * @return the specified messagebus
     */
    private static Messagebus checkMessagebus(Messagebus messagebus) {

        if (messagebus == null) {

            throw new IllegalArgumentException("No messagebus (null) was specified!");
        }

        return messagebus;
    }

    /**
     * Returns the name of this sender.
     */
    @Override
    public String senderName() {

        return name();
    }

    /**
     * Returns the name of this receiver.
     */
    @Override
    public String receiverName() {

        return name();
    }

}
