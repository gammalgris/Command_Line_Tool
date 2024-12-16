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


import java.util.List;


/**
 * This interface describes a container for commands which also helps evaluating inputs.<br>
 * <br>
 * This container is mutable in order to change it as needed.
 *
 * @author Kristian Kutin
 */
public interface CommandCatalogue extends Iterable<Command> {

    /**
     * Returns the total count of commands within this container.
     *
     * @return a command count
     */
    int size();

    /**
     * Checks if this container for commands is empty.
     *
     * @return <code>true</code> if this container for commands is empty,
     *         else <code>false</code>
     */
    boolean isEmpty();

    /**
     * Adds the specified commands to this container for commands.
     *
     * @param commands
     *        one or more commands
     */
    void addCommands(Command... commands);

    /**
     * Removes the specified commands from this container for commands.
     *
     * @param commands
     *        one or more commands
     */
    void removeCommands(Command... commands);

    /**
     * Returns the command with the specified token.
     *
     * @param token
     *        the token representing a command
     *
     * @return a command
     */
    Command commandByToken(String token);

    /**
     * Returns the commands which are associated with the specified category.
     *
     * @param category
     *        a category
     *
     * @return any number of commands that meet the specified criteria
     */
    List<Command> commandsByCategory(String category);

    /**
     * Evaluates the specified input and identifies a matching command.
     *
     * @param input
     *        some input which is to be processed
     *
     * @return a command
     */
    Command match(String input);

}
