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


import java.lang.reflect.InvocationTargetException;

import jmul.messaging.StandardMessage;

import jmul.misc.state.State;

import jmul.reflection.constructors.ConstructorInvoker;


/**
 * A utility class for managing and processing messages.
 *
 * @author Kristian Kutin
 */
public final class MessageHelper {

    /**
     * The default constructor.
     */
    private MessageHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Rewraps a message and adds the specified state.
     *
     * @param message
     *        a message
     * @param state
     *        the state informations which are added to the message
     *
     * @return a new message which contains the original method's data and the additional state
     */
    public static StandardMessage rewrapMessageWithState(StandardMessage message, State state) {

        Class messageType = message.getClass();
        Class[] parameterSignature = { messageType, State.class };
        Object[] parameters = { message, state };

        ConstructorInvoker invoker = new ConstructorInvoker(messageType, parameterSignature);

        StandardMessage newMessage;
        try {

            newMessage = (StandardMessage) invoker.invoke(parameters);

        } catch (IllegalAccessException | InstantiationException | InvocationTargetException |
                 NoSuchMethodException e) {

            throw new IllegalArgumentException("The message couldn't be rewrapped!", e);
        }

        return newMessage;
    }

}
