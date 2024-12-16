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

package jmul.terminal.component.messages;


import jmul.messaging.StandardMessage;

import jmul.misc.state.State;


/**
 * A message which signals the core component to register the sender as
 * component.
 *
 * @author Kristian Kutin
 * 
 * @deprecated Message type not needed or maybe not yet needed.
 */
@Deprecated
public class RegisterComponentMessage extends StandardMessage {

    /**
     * A topic for this message.
     */
    public static final String REGISTER_COMPONENT_TOPIC;

    /*
     * The static initializer.
     */
    static {

        REGISTER_COMPONENT_TOPIC = "register sender as component";
    }

    /**
     * Creates a new stop message.
     *
     * @param sender
     *        the name of the sender
     * @param recipient
     *        the name of the recipient
     */
    public RegisterComponentMessage(String sender, String recipient) {

        super(sender, recipient, REGISTER_COMPONENT_TOPIC);
    }

    /**
     * Creates a new message and passes the details of the spcified message and
     * adds the specified component state.
     *
     * @param message
     *        a message
     * @param state
     *        the state of the component which processes the messages
     */
    public RegisterComponentMessage(RegisterComponentMessage message, State state) {

        this(message.senderName(), message.receiverName(), message.topic(), state);
    }

    /**
     *Creates a new message.
     *
     * @param sender
     *        the name of a sender
     * @param receiver
     *        the name of a receiver
     * @param topic
     *        the topic of this message
     * @param state
     *        the state of the component which processes the messages
     */
    private RegisterComponentMessage(String sender, String receiver, String topic, State state) {

        super(sender, receiver, topic, EntryHelper.newEntry(CustomPropertyKeys.COMPONENT_STATE, state));
    }

}
