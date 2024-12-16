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

package jmul.terminal.component.core;


import jmul.concurrent.threads.ThreadHelper;

import jmul.messaging.MessageQueryResult;
import jmul.messaging.QueuingMessagebus;
import jmul.messaging.StandardMessage;

import jmul.misc.state.IllegalStateTransitionException;
import jmul.misc.state.State;

import jmul.terminal.Resources;
import jmul.terminal.component.ComponentBase;
import jmul.terminal.component.ComponentRoles;
import jmul.terminal.component.ComponentStates;
import jmul.terminal.component.SystemStates;
import jmul.terminal.component.messages.ComponentReadyMessage;
import jmul.terminal.component.messages.ComponentStoppedMessage;
import jmul.terminal.component.messages.LogEntryMessage;
import jmul.terminal.component.messages.MessageHelper;
import jmul.terminal.component.messages.StopMessage;
import jmul.terminal.component.messaging.ComponentMessageHandler;
import jmul.terminal.component.messaging.MessageHandler;
import jmul.terminal.component.messaging.MessageProcessingRule;
import jmul.terminal.component.misc.ComponentDictionary;
import jmul.terminal.component.misc.ComponentDictionaryImpl;
import jmul.terminal.component.misc.ComponentStateMonitor;
import jmul.terminal.component.misc.ComponentStateMonitorImpl;
import jmul.terminal.component.misc.NameRoleEntry;
import jmul.terminal.component.state.ComponentStateHandler;
import jmul.terminal.component.state.StateHandler;
import jmul.terminal.component.state.StateUpdateRule;
import jmul.terminal.utils.logging.LogLevel;
import jmul.terminal.utils.logging.LogLevels;


/**
 * This class represents the core thread for a system. It provides a messaging queue
 * which can be used by all components (i.e. threads). It handles the overall system
 * state.
 *
 * @author Kristian Kutin
 */
public class CoreComponent extends ComponentBase {

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
     * A dicitonary which contains all components and their roles this component
     * needs to know.
     */
    private final ComponentDictionary componentDictionary;

    /**
     * An entity which tracks the state of all known components. The states may not alyways
     * be up to date and the core component might need to send a message and wait for the
     * response.
     */
    private final ComponentStateMonitor stateMonitor;

    /**
     * An entity that prcoesses messages.
     */
    private final MessageHandler messageHandler;

    /**
     * An entity that updates state.
     */
    private final StateHandler stateHandler;

    /**
     * The overall system state.
     */
    private volatile State state;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param name
     *        the name of this component
     * @param componentEntries
     *        names and roles of components this component needs to know
     */
    public CoreComponent(String name, NameRoleEntry... componentEntries) {

        super(name);

        state = SystemStates.UNINITIALIZED;

        connect(new QueuingMessagebus());

        componentDictionary = new ComponentDictionaryImpl(componentEntries);
        stateMonitor = new ComponentStateMonitorImpl(componentDictionary.names());
        initializeComponentStates(componentDictionary.names());

        messageHandler = new ComponentMessageHandler();
        ((ComponentMessageHandler) messageHandler)
            .addRule(SystemStates.INITIALIZATION, ComponentReadyMessage.class, new ComponentReadyRule());
        ((ComponentMessageHandler) messageHandler)
            .addRule(SystemStates.RUNNING, StopMessage.class, new StopComponentsRule());
        ((ComponentMessageHandler) messageHandler)
            .addRule(SystemStates.STOPPING, ComponentStoppedMessage.class, new ComponentStoppedRule());
        //TODO add missing rules

        stateHandler = new ComponentStateHandler();
        ((ComponentStateHandler) stateHandler).addRule(SystemStates.INITIALIZATION, new UpdateToInitializedRule());
        ((ComponentStateHandler) stateHandler).addRule(SystemStates.INITIALIZED, new UpdateToStartingRule());
        ((ComponentStateHandler) stateHandler).addRule(SystemStates.STARTING, new UpdateToRunningRule());
        ((ComponentStateHandler) stateHandler).addRule(SystemStates.STOPPING, new UpdateToStoppedRule());
        //TODO add missing rules

        updateSystemState(SystemStates.INITIALIZATION);
    }

    /**
     * Initializes the states for the specified components.
     *
     * @param componentNames
     *        the names of one or several components
     */
    private void initializeComponentStates(String... componentNames) {

        for (String componentName : componentNames) {

            initializeComponentState(componentName);
        }
    }

    /**
     * Initializes the state for the specified component.
     *
     * @param componentName
     *        the name of a component
     */
    private void initializeComponentState(String componentName) {

        try {

            stateMonitor.updateState(componentName, ComponentStates.UNKNOWN);

        } catch (IllegalStateTransitionException e) {

            logMesssage(LogLevels.ERROR, e.getMessage());
            //TODO How to handle this case?

        } catch (IllegalArgumentException e) {

            logMesssage(LogLevels.ERROR, e.getMessage());
            //TODO How to handle this case?
        }
    }

    /**
     * Logs the specified log entry.
     *
     * @param logLevel
     *        a log level
     * @param logMessage
     *        a log message
     */
    private void logMesssage(LogLevel logLevel, String logMessage) {

        if (componentDictionary.existsRole(ComponentRoles.LOGGER)) {

            String loggerName = componentDictionary.getNameByRole(ComponentRoles.LOGGER);

            LogEntryMessage message = new LogEntryMessage(senderName(), loggerName, logLevel, logMessage);
            sendMessage(message);

            return;
        }

        Resources.LOGGER.log(logLevel, name(), logMessage);
    }

    /**
     * Updates the state for the specified component.
     *
     * @param componentName
     *        the name of a component
     * @param newState
     *        the new state
     */
    private void updateComponentState(String componentName, State newState) {

        try {

            stateMonitor.updateState(componentName, newState);

        } catch (IllegalStateTransitionException e) {

            logMesssage(LogLevels.ERROR, e.getMessage());
            //TODO How to handle this case?

        } catch (IllegalArgumentException e) {

            logMesssage(LogLevels.ERROR, e.getMessage());
            //TODO How to handle this case?
        }
    }

    /**
     * Returns the current state of the core component (i.e. the state of the
     * overall system).
     *
     * @return the current state
     */
    public State systemState() {

        return state;
    }

    /**
     * The core watches over initialization and the system state. The core also
     * coordinates stopping this system.
     */
    public void run() {

        StandardMessage message = null;
        MessageQueryResult queryResult = null;
        while (SystemStates.STOPPED != systemState()) {

            if (message == null) {

                queryResult = fetchMessage();
            }

            logMesssage(LogLevels.DEBUG, "check messages...");

            if (queryResult.existsResult()) {

                if (message == null) {

                    message = (StandardMessage) queryResult.result();
                    message = MessageHelper.rewrapMessageWithState(message, systemState());
                }

                boolean result = messageHandler.processMessage(message);
                if (result) {

                    String messageProcessed = "processed 1 message (" + message + ")";
                    logMesssage(LogLevels.DEBUG, messageProcessed);

                    message = null;
                    queryResult = null;

                } else {

                    String messageNotProcessed = "message not processed (" + message + ")!";
                    logMesssage(LogLevels.DEBUG, messageNotProcessed);
                }
            }


            stateHandler.updateState(systemState());


            ThreadHelper.sleep(DEFAULT_SLEEP_TIME);
        }
    }


    /**
     * Checks if all components are ready.
     *
     * @return <code>true</code> if all components are ready, else <code>false</code>
     */
    private boolean checkComponentsAreReady() {

        for (String name : componentDictionary.names()) {

            State currentState = stateMonitor.getState(name);

            if (!currentState.equals(ComponentStates.READY)) {

                return false;
            }
        }

        return true;
    }

    /**
     * Checks if all components have stopped.
     *
     * @return <code>true</code> if all components have stopped, else <code>false</code>
     */
    private boolean checkComponentsHaveStopped() {

        for (String name : componentDictionary.names()) {

            State currentState = stateMonitor.getState(name);

            if (!currentState.equals(ComponentStates.STOPPED)) {

                return false;
            }
        }

        return true;
    }

    /**
     * Update the state of this core component.
     *
     * @param newState
     *        the new state
     */
    private void updateSystemState(State newState) {

        state = state.transitionTo(newState);
    }

    /**
     * Send a stop message to all registered components.
     */
    private void sendStopMessageToAllComponents() {

        for (String name : componentDictionary.names()) {

            StopMessage message = new StopMessage(this.senderName(), name);
            sendMessage(message);
        }
    }


    /* message handling rules */

    class ComponentReadyRule implements MessageProcessingRule {

        @Override
        public boolean processMessage(StandardMessage message) {

            String sender = message.senderName();
            updateComponentState(sender, ComponentStates.READY);

            return true;
        }

    }


    class StopComponentsRule implements MessageProcessingRule {

        @Override
        public boolean processMessage(StandardMessage message) {

            sendStopMessageToAllComponents();
            updateSystemState(SystemStates.STOPPING);

            return true;
        }

    }


    class ComponentStoppedRule implements MessageProcessingRule {

        @Override
        public boolean processMessage(StandardMessage message) {

            String componentName = message.senderName();
            updateComponentState(componentName, ComponentStates.STOPPED);

            return true;
        }
    }


    /* state update rules */

    class UpdateToInitializedRule implements StateUpdateRule {

        @Override
        public boolean updateState(State state) {

            if (checkComponentsAreReady()) {

                updateSystemState(SystemStates.INITIALIZED);
                return true;
            }

            return false;
        }
    }


    class UpdateToStartingRule implements StateUpdateRule {

        @Override
        public boolean updateState(State state) {

            updateSystemState(SystemStates.STARTING);
            return true;
        }
    }


    class UpdateToRunningRule implements StateUpdateRule {

        @Override
        public boolean updateState(State state) {

            updateSystemState(SystemStates.RUNNING);
            return true;
        }
    }


    class UpdateToStoppedRule implements StateUpdateRule {

        @Override
        public boolean updateState(State state) {

            if (checkComponentsHaveStopped()) {

                updateSystemState(SystemStates.STOPPED);
                return true;
            }

            return false;
        }
    }

}
