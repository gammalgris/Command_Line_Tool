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

package jmul.terminal.component.logging;


import jmul.concurrent.threads.ThreadHelper;

import jmul.messaging.MessageQueryResult;
import jmul.messaging.StandardMessage;

import jmul.misc.state.State;

import jmul.terminal.Resources;
import jmul.terminal.component.ComponentRoles;
import jmul.terminal.component.ComponentStates;
import jmul.terminal.component.IndependentComponentBase;
import jmul.terminal.component.messages.ComponentReadyMessage;
import jmul.terminal.component.messages.ComponentStoppedMessage;
import jmul.terminal.component.messages.CustomPropertyKeys;
import jmul.terminal.component.messages.LogEntryMessage;
import jmul.terminal.component.messages.MessageHelper;
import jmul.terminal.component.messages.StopMessage;
import jmul.terminal.component.messaging.ComponentMessageHandler;
import jmul.terminal.component.messaging.MessageHandler;
import jmul.terminal.component.messaging.MessageProcessingRule;
import jmul.terminal.component.misc.ComponentDictionary;
import jmul.terminal.component.misc.ComponentDictionaryImpl;
import jmul.terminal.component.misc.NameRoleEntry;
import jmul.terminal.component.state.ComponentStateHandler;
import jmul.terminal.component.state.StateHandler;
import jmul.terminal.utils.logging.LogLevel;
import jmul.terminal.utils.logging.LogLevels;


/**
 * This component prints log statements to the console.
 *
 * @author Kristian Kutin
 */
public class ConsoleLoggingComponent extends IndependentComponentBase {

    /**
     * A constant sleep time.
     */
    private static final long DEFAULT_SLEEP_TIME;

    /*
     * The static initializer.
     */
    static {

        DEFAULT_SLEEP_TIME = 50L;
    }

    /**
     * An entity that prcoesses messages.
     */
    private final MessageHandler messageHandler;

    /**
     * An entity that updates state.
     */
    private final StateHandler stateHandler;

    /**
     * The overall component state.
     */
    private volatile State state;

    /**
     * A dicitonary which contains all components and their roles this component
     * needs to know.
     */
    private final ComponentDictionary componentDictionary;

    /**
     * Creates a new logging component according to the specified parameters.
     *
     * @param name
     *        the name for this component
     * @param logLevel
     *        the log level determines which log entries get shown
     * @param componentEntries
     *        names and roles of components this component needs to know
     */
    public ConsoleLoggingComponent(String name, LogLevel logLevel, NameRoleEntry... componentEntries) {

        super(name);

        Resources.LOGGER.changeLogLevel(logLevel);

        state = ComponentStates.UNKNOWN;

        componentDictionary = new ComponentDictionaryImpl(componentEntries);

        messageHandler = new ComponentMessageHandler();
        ((ComponentMessageHandler) messageHandler)
            .addRule(ComponentStates.READY, LogEntryMessage.class, new ProcessLogEntryMessageRule());
        ((ComponentMessageHandler) messageHandler)
            .addRule(ComponentStates.READY, StopMessage.class, new ProcessStopMessageRule());
        //TODO add missing rules

        stateHandler = new ComponentStateHandler();
        //TODO add missing rules
    }

    /**
     * Returns the current state of this component.
     *
     * @return the current state
     */
    public State componentState() {

        return state;
    }

    /**
     * The logging component will wait
     */
    @Override
    public void run() {

        while (!isConnected()) {

            ThreadHelper.sleep(DEFAULT_SLEEP_TIME);
        }


        updateComponentState(ComponentStates.READY);
        sendReadyMessage();


        StandardMessage message = null;
        MessageQueryResult queryResult = null;
        while (ComponentStates.STOPPED != componentState()) {

            if (message == null) {

                queryResult = fetchMessage();
            }

            if (queryResult.existsResult()) {

                if (message == null) {

                    message = (StandardMessage) queryResult.result();
                    message = MessageHelper.rewrapMessageWithState(message, componentState());
                }

                boolean result = messageHandler.processMessage(message);
                if (result) {

                    String messageProcessed = "processed 1 message (" + message + ")";
                    logMesssage(LogLevels.DEBUG, name(), messageProcessed);

                    message = null;
                    queryResult = null;

                } else {

                    String messageNotProcessed = "message not processed (" + message + ")!";
                    logMesssage(LogLevels.DEBUG, name(), messageNotProcessed);
                }
            }


            stateHandler.updateState(componentState());


            ThreadHelper.sleep(DEFAULT_SLEEP_TIME);
        }


        sendStoppedMessage();
    }

    /**
     * Sends a ready message to the core component.
     */
    private void sendReadyMessage() {

        String recipient = componentDictionary.getNameByRole(ComponentRoles.CORE);
        ComponentReadyMessage message = new ComponentReadyMessage(senderName(), recipient);
        sendMessage(message);
    }

    /**
     * Sends a stopped message to the core component.
     */
    private void sendStoppedMessage() {

        String recipient = componentDictionary.getNameByRole(ComponentRoles.CORE);
        ComponentStoppedMessage message = new ComponentStoppedMessage(senderName(), recipient);
        sendMessage(message);
    }

    /**
     * Writes the specified informations to the console.
     *
     * @param logLevel
     *        a log level
     * @param componentName
     *        the name of the component which sent the log message
     * @param message
     *        a log message
     */
    private void logMesssage(LogLevel logLevel, String componentName, String message) {

        Resources.LOGGER.log(logLevel, componentName, message);
    }

    /**
     * Update the state of this component.
     *
     * @param newState
     *        the new state
     */
    private void updateComponentState(State newState) {

        state = state.transitionTo(newState);
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param messageType
     *        a message type
     *
     * @return an exception
     */
    static IllegalArgumentException createWrongMessageTypeException(Class messageType) {

        String message = String.format("The specified message is not of type %s!", messageType.toString());
        return new IllegalArgumentException(message);
    }

    /**
     * Checks if the specified message is of the specified type. If not an exception
     * is thrown.
     *
     * @param message
     *        a message
     * @param expectedType
     *        the expected message type
     */
    static void checkMessageType(StandardMessage message, Class expectedType) {

        Class actualType = message.getClass();
        if (expectedType.isAssignableFrom(actualType)) {

            return;
        }

        throw createWrongMessageTypeException(expectedType);
    }

    /**
     * Extracts the log level from the specified message.
     *
     * @param message
     *        a message
     *
     * @return a log level
     */
    static LogLevel extractLogLevel(StandardMessage message) {

        checkMessageType(message, LogEntryMessage.class);
        return (LogLevel) message.get(CustomPropertyKeys.LOG_LEVEL);
    }

    /**
     * Extracts the log entry from the specified message.
     *
     * @param message
     *        a message
     *
     * @return a log entry
     */
    static String extractLogEntry(StandardMessage message) {

        checkMessageType(message, LogEntryMessage.class);
        return (String) message.get(CustomPropertyKeys.LOG_ENTRY);
    }


    /* message handling rules */

    class ProcessLogEntryMessageRule implements MessageProcessingRule {

        @Override
        public boolean processMessage(StandardMessage message) {

            String sender = message.senderName();
            LogLevel logLevel = extractLogLevel(message);
            String logEntry = extractLogEntry(message);

            logMesssage(logLevel, sender, logEntry);

            return true;
        }

    }


    class ProcessStopMessageRule implements MessageProcessingRule {

        @Override
        public boolean processMessage(StandardMessage message) {

            updateComponentState(ComponentStates.STOPPED);

            return true;
        }

    }

}
