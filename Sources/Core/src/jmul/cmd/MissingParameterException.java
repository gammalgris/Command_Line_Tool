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

package jmul.cmd;


/**
 * A custom exception for missing parameters.
 *
 * @author Kristian Kutin
 */
public class MissingParameterException extends RuntimeException {

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param name
     *        a unique name for a parameter
     */
    public MissingParameterException(String name) {

        super(createErrorMessage(name));
    }

    /**
     * Creates an error message according to the specified parameter.
     *
     * @param name
     *        the name of the missing parameter
     *
     * @return an error message
     */
    private static String createErrorMessage(String name) {

        checkName(name);

        String message = String.format("The parameter %s is missing!", name);

        return message;
    }

    /**
     * Checks the specified name.
     *
     * @param name
     *        a name
     *
     * @return the specified name
     */
    private static String checkName(String name) {

        if (name == null) {

            throw new IllegalArgumentException("No name (null) was specified!");
        }

        return name;
    }

}
