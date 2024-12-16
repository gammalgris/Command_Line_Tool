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

package jmul.language.catalogues;


/**
 * A custom exception for queries where there is no match. The cause is
 * no matching name in the token catalogue.
 *
 * @author Kristian Kutin
 */
public class NoMatchingNameException extends RuntimeException {

    /**
     * Creates a new exception according to the specified name.
     *
     * @param name
     *        a token name which is not inside a token catalogue
     */
    public NoMatchingNameException(String name) {

        super(createMessage(name));
    }

    /**
     * Creates an error message according to the specified name.
     *
     * @param name
     *        a token name which is not inside a token catalogue
     *
     * @return an error message
     */
    private static String createMessage(String name) {

        checkName(name);

        return String.format("The token catalogue doesn't know a token with the name \"%s\"!", name);
    }

    /**
     * Checks the specified name.
     *
     * @param name
     *        a token name which is not inside a token catalogue
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
