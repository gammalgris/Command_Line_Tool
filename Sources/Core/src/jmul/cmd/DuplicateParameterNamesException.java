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
package jmul.cmd;


import java.util.Set;


/**
 * A custom exception for duplicate parameter names.
 *
 * @author Kristian Kutin
 */
public class DuplicateParameterNamesException extends RuntimeException {

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param duplicateNames
     *        all duplicate names
     */
    public DuplicateParameterNamesException(Set<String> duplicateNames) {

        super(createMessage(duplicateNames));
    }

    /**
     * Creates an error message according to the specified parameter.
     *
     * @param duplicateNames
     *        the names of all duplicate parameter names
     *
     * @return an error message
     */
    private static String createMessage(Set<String> duplicateNames) {

        checkDuplicateNames(duplicateNames);

        String message = String.format("Parameters have duplicate names (%s)!", duplicateNames.toString());

        return message;
    }

    /**
     * Checks the specified duplicate parameter names.
     *
     * @param duplicateNames
     *        all duplicate parameter names
     *
     * @return the specified parameter
     */
    private static Set<String> checkDuplicateNames(Set<String> duplicateNames) {

        if (duplicateNames == null) {

            throw new IllegalArgumentException("No duplicate parameter names (null) were provided!");
        }

        if (duplicateNames.isEmpty()) {

            throw new IllegalArgumentException("No duplicate parameter names (empty list) were provided!");
        }

        return duplicateNames;
    }

}
