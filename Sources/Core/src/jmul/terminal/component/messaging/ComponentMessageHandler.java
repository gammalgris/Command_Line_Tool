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

package jmul.terminal.component.messaging;


import java.util.HashMap;
import java.util.Map;

import jmul.messaging.StandardMessage;

import jmul.misc.state.State;

import jmul.terminal.component.messages.CustomPropertyKeys;


/**
 * This entity contains rules to process messages. The rules are divided into
 * subcategories (i.e. the component state and the message type). This entity
 * checks for each message if there is an appropriate processing rule and
 * then applies it.
 *
 * @author Kristian Kutin
 */
public class ComponentMessageHandler implements MessageHandler {

    /**
     * A container for message processing rules.
     */
    private final Map<State, Map<Class, MessageProcessingRule>> rulesContainer;

    /**
     * The default constructor.
     */
    public ComponentMessageHandler() {

        super();

        rulesContainer = new HashMap<>();
    }

    /**
     * A rule for processing messages.
     *
     * @param message
     *        a message
     *
     * @return <code>true</code> if the message was processed successfully, else <code>false</code>
     */
    @Override
    public boolean processMessage(StandardMessage message) {

        if (message == null) {

            throw new IllegalArgumentException("No message (null) was specified!");
        }

        if (!message.contains(CustomPropertyKeys.COMPONENT_STATE)) {

            throw new IllegalArgumentException("The specified message doesn't contain a state!");
        }

        State state = (State) message.get(CustomPropertyKeys.COMPONENT_STATE);
        Class messageType = message.getClass();

        Map<Class, MessageProcessingRule> rulesByState = rulesContainer.get(state);
        if (rulesByState == null) {

            return false;
        }

        MessageProcessingRule rule = rulesByState.get(messageType);
        if (rule == null) {

            return false;
        }

        return rule.processMessage(message);
    }

    /**
     * Adds the specified rule to this component message handler.
     *
     * @param state
     *        a component state (i.e. the component has to be in this state in order to
     *        process the specified message)
     * @param messageType
     *        a message type (i.e. the message has to be of the specified type in order
     *        to process the specified message)
     * @param rule
     *        the new processing rule which is to be added
     *
     * @return the previous processing rule or <code>null</code> if no previous processing
     *         rules exists
     */
    public MessageProcessingRule addRule(State state, Class messageType, MessageProcessingRule rule) {

        if (state == null) {

            throw new IllegalArgumentException("No state (null) was specified!");
        }

        if (messageType == null) {

            throw new IllegalArgumentException("No message type (null) was specified!");
        }

        if (rule == null) {

            throw new IllegalArgumentException("No rule (null) was specified!");
        }

        Map<Class, MessageProcessingRule> rulesByState = rulesContainer.get(state);
        if (rulesByState == null) {

            rulesByState = new HashMap<>();
            rulesContainer.put(state, rulesByState);
        }

        return rulesByState.put(messageType, rule);
    }

    /**
     * Returns the current number of processing rules this message handler knows.
     *
     * @return a rule count (i.e. an integer which is zero or positive)
     */
    @Override
    public int ruleCount() {

        int count = 0;

        for (Map<Class, MessageProcessingRule> rulesByState : rulesContainer.values()) {

            count += rulesByState.size();
        }

        return count;
    }

}
