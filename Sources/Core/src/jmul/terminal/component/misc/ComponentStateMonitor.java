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
 * This interface describes an entity which monitors the state of various components.
 *
 * @author Kristian Kutin
 */
public interface ComponentStateMonitor {

    /**
     * Checks if the monitoring entity knows the specified component.
     *
     * @param componentName
     *        the name of a component
     *
     * @return <code>true</code> if the monitoring entity knows the specified component, else <code>false</code>
     */
    boolean existsComponent(String componentName);

    /**
     * Returns the current state for the specified component.
     *
     * @param componentName
     *        the name of a component
     *
     * @return the current recorded state for the specified component
     */
    State getState(String componentName);

    /**
     * Updates the state for the specified component.
     *
     * @param componentName
     *        the name of a component
     * @param newSate
     *        the new state of the component
     */
    void updateState(String componentName, State newSate);

    /**
     * Returns the number of known components.
     *
     * @return the number if known components
     */
    int componentCount();

}
