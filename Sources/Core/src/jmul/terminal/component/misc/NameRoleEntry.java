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


/**
 * This class represents a pair of a name and a role.
 *
 * @author Kristian Kutin
 */
public class NameRoleEntry {

    /**
     * A name.
     */
    public final String name;

    /**
     * The role associated with the name.
     */
    public final String role;

    /**
     * Creates a new entry according to the specified parameters.
     *
     * @param name
     *        a name
     * @param role
     *        the role qassociated with the name
     */
    public NameRoleEntry(String name, String role) {

        super();

        this.name = check(name);
        this.role = check(role);
    }

    /**
     * Checks the specified string and return the specified string.
     *
     * @param s
     *        a string
     *
     * @return the specified name
     */
    private static String check(String s) {

        if (s == null) {

            throw new IllegalArgumentException("No string (null) was specified!");
        }

        return s;
    }

    /**
     * Returns a string representation for this entry.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.format("%s:%s", name, role);
    }

}
