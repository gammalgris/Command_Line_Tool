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


/**
 * A custom exception for cases when a command container doesn't know a rule.
 *
 * @author Kristian Kutin
 */
public class UnknownCommandException extends RuntimeException {

    /**
     * Creates a new instance accpording to the specified parameter.
     *
     * @param name
     *        the name of a command
     */
    public UnknownCommandException(String name) {

        super(createMessage(name));
    }

    /**
     * Creates an error message according to the specified parameter.
     *
     * @param name
     *        the name of a command
     *
     * @return an error message
     */
    private static String createMessage(String name) {

        checkName(name);

        return String.format("No command with the specified name (%s) exists!", name);
    }

    /**
     * Check the specified name.
     *
     * @param name
     *        the name of a command
     *
     * @return the specified name
     */
    private static String checkName(String name) {

        if (name == null) {

            throw new IllegalArgumentException("No name (null) was specified!");
        }

        String normalizedName = name.trim();
        if (normalizedName.isEmpty()) {

            throw new IllegalArgumentException("No name (empty string) was specified!");
        }

        return name;
    }

}
