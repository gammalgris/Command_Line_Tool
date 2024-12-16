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

package jmul.terminal.component.misc;


import jmul.misc.state.State;


/**
 * An implementation of a component state monitor which contains names and associated states.
 * This implementation is suited for a small number of monitored components.
 *
 * @author Kristian Kutin
 */
public class ComponentStateMonitorImpl implements ComponentStateMonitor {

    /**
     * The constant represents 'index not found'.
     */
    private static final int INDEX_NOT_FOUND;

    /*
     * The static initializer.
     */
    static {

        INDEX_NOT_FOUND = -1;
    }

    /**
     * The number of known components.
     */
    private final int componentCount;

    /**
     * A list containing all names.
     */
    private final String[] names;

    /**
     * A list containing all roles.
     */
    private final State[] states;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param names
     *        the names of all known components
     */
    public ComponentStateMonitorImpl(String... names) {

        super();

        check(names);

        this.componentCount = names.length;
        this.names = new String[componentCount];
        this.states = new State[componentCount];

        for (int index = 0; index < componentCount; index++) {

            String name = names[index];
            this.names[index] = name;
            this.states[index] = null;
        }
    }

    /**
     * Checks the specified names.
     *
     * @param names
     *        names of components
     *
     * @return the specified names
     */
    private static String[] check(String... names) {

        if (names == null) {

            throw new IllegalArgumentException("No names (null) were specified!");
        }

        int length = names.length;
        for (int index = 0; index < length; index++) {

            String name = names[index];

            if (name == null) {

                throw new IllegalArgumentException("Invalid names (null) were specified!");
            }
        }

        return names;
    }

    /**
     * Checks if the monitoring entity knows the specified component.
     *
     * @param componentName
     *        the name of a component
     *
     * @return <code>true</code> if the monitoring entity knows the specified component, else <code>false</code>
     */
    @Override
    public boolean existsComponent(String componentName) {

        for (int index = 0; index < names.length; index++) {

            String actualName = names[index];
            if (actualName.equals(componentName)) {

                return true;
            }
        }

        return false;
    }

    /**
     * Returns the current state for the specified component.
     *
     * @param componentName
     *        the name of a component
     *
     * @return the current recorded state for the specified component
     */
    @Override
    public State getState(String componentName) {

        int foundIndex = INDEX_NOT_FOUND;
        for (int index = 0; index < names.length; index++) {

            String actualName = names[index];
            if (actualName.equals(componentName)) {

                foundIndex = index;
                break;
            }
        }

        if (foundIndex <= INDEX_NOT_FOUND) {

            String message = String.format("The monitor doesn't know the component '%s'!", componentName);
            throw new IllegalArgumentException(message);
        }

        return states[foundIndex];
    }

    /**
     * Updates the state for the specified component.
     *
     * @param componentName
     *        the name of a component
     * @param newSate
     *        the new state of the component
     */
    @Override
    public void updateState(String componentName, State newSate) {

        int foundIndex = INDEX_NOT_FOUND;
        for (int index = 0; index < names.length; index++) {

            String actualName = names[index];
            if (actualName.equals(componentName)) {

                foundIndex = index;
                break;
            }
        }

        if (foundIndex <= INDEX_NOT_FOUND) {

            String message = String.format("The monitor doesn't know the component '%s'!", componentName);
            throw new IllegalArgumentException(message);
        }

        State currentState = states[foundIndex];

        if (currentState == null) {

            currentState = newSate;

        } else {

            currentState = currentState.transitionTo(newSate);
        }

        states[foundIndex] = currentState;
    }

    /**
     * Returns the number of known components.
     *
     * @return the number if known components
     */
    @Override
    public int componentCount() {

        return componentCount;
    }

}
