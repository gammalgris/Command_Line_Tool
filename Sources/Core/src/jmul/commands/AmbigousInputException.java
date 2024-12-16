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


import java.util.ArrayList;
import java.util.List;


/**
 * A custom exception for cases when several commands can process the same input
 * and the software cannot decide which command has precedence.
 *
 * @author Kristian Kutin
 */
public class AmbigousInputException extends RuntimeException {

    /**
     * Creates a new insatnce according to the specified parameter.
     *
     * @param commands
     *        commands that can process the same input
     */
    public AmbigousInputException(List<Command> commands) {

        super(createMessage(commands));
    }

    /**
     * Creates an error message according to the specified parameter.
     *
     * @param commands
     *        commands that can process the same input
     *
     * @return an error message
     */
    private static String createMessage(List<Command> commands) {

        checkCommands(commands);

        return String.format("The input is ambigous! Sevral rules can process it (%s).", commandsToNames(commands));
    }

    /**
     * Extracts the names from the specified commands.
     *
     * @param commands
     *        commands that can process the same input
     *
     * @return the extracted names
     */
    private static List<String> commandsToNames(List<Command> commands) {

        List<String> names = new ArrayList<>();

        for (Command command : commands) {

            names.add(command.token());
        }

        return names;
    }

    /**
     * Checks the specified commands.
     *
     * @param commands
     *        commands that can process the same input
     *
     * @return the specified commands
     */
    private static List<Command> checkCommands(List<Command> commands) {

        if (commands == null) {

            throw new IllegalArgumentException("No commands (null) were specified!");
        }

        if (commands.isEmpty()) {

            throw new IllegalArgumentException("No commands (empty list) were specified!");

        } else if (commands.size() == 1) {

            throw new IllegalArgumentException("There is no ambiguity with one rule!");
        }

        return commands;
    }

}
