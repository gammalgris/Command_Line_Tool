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

package jmul.terminal.component.state;


import java.util.HashMap;
import java.util.Map;

import jmul.misc.state.State;


/**
 * This entity contains rules to update a component's state. The rules are divided into
 * subcategories (i.e. the component state). This entity checks for each update if there
 * is an appropriate rule and applies it.
 *
 * @author Kristian Kutin
 */
public class ComponentStateHandler implements StateHandler {

    /**
     * A container for state update rules.
     */
    private final Map<State, StateUpdateRule> rulesContainer;

    /**
     * The default constructor.
     */
    public ComponentStateHandler() {

        super();

        rulesContainer = new HashMap<>();
    }

    /**
     * A rule for updating a state.
     *
     * @param state
     *        the component's current state
     *
     * @return <code>true</code> if the state was changed successfully, else <code>false</code>
     */
    @Override
    public boolean updateState(State state) {

        if (state == null) {

            throw new IllegalArgumentException("No state (null) was specified!");
        }

        StateUpdateRule rule = rulesContainer.get(state);
        if (rule == null) {

            return false;
        }

        return rule.updateState(state);
    }

    /**
     * Adds the specified rule to this component message handler.
     *
     * @param state
     *        a component state (i.e. the component has to be in this state in order to
     *        process the specified message)
     * @param rule
     *        the new processing rule which is to be added
     *
     * @return the previous processing rule or <code>null</code> if no previous processing
     *         rules exists
     */
    public StateUpdateRule addRule(State state, StateUpdateRule rule) {

        if (state == null) {

            throw new IllegalArgumentException("No state (null) was specified!");
        }

        if (rule == null) {

            throw new IllegalArgumentException("No rule (null) was specified!");
        }

        return rulesContainer.put(state, rule);
    }

    /**
     * Returns the current number of processing rules this message handler knows.
     *
     * @return a rule count (i.e. an integer which is zero or positive)
     */
    @Override
    public int ruleCount() {

        return rulesContainer.size();
    }

}
