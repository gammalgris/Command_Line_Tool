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

package jmul.commands;


import java.util.Map;


/**
 * This interface represents a command. A command contains general informations about
 * itself (i.e. name and description). It provides the means to match it with a given
 * input and can extract necessary informations from that input.
 *
 * @author Kristian Kutin
 */
public interface Command {

    /**
     * The command token.
     *
     * @return the token (i.e. a character or string) representing this command
     */
    String token();

    /**
     * A description for this command.
     *
     * @return a description
     */
    String description();

    /**
     * Checks if the specified input macthes this command.
     *
     * @param input
     *        some input
     *
     * @return <code>true</code> if the specified input matches this command,
     *         else <code>false</code>
     */
    boolean matches(String input);

    /**
     * Parses the specified input and extracts information which are need to
     * execute this command.
     *
     * @param input
     *        some input
     *
     * @return a map containing informations extracted from the specified input
     */
    Map<String, String> parse(String input);

    /**
     * Checks if this command belongs to the specified category or classification.
     *
     * @param category
     *        a category or classification for this command
     *
     * @return
     */
    boolean isCategory(String category);

    /**
     * Associated this command with the specified categories.
     *
     * @param categories
     *        one or more categories
     */
    void addCategory(String... categories);

    /**
     * Removes the association with the specified categories for this command.
     *
     * @param categories
     *        one or more categories
     */
    void removeCategory(String... categories);

}
