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
 * An implementation of a dictionary which contains names and associated roles.
 * This implementation is suited for small dictionaries.
 *
 * @author Kristian Kutin
 */
public class ComponentDictionaryImpl implements ComponentDictionary {

    /**
     * The constant represents 'index not found'.
     */
    private static final int INDEX_NOT_FOUND;

    /*
     * The static initializer.
     */
    static {

        INDEX_NOT_FOUND = -1;
    }

    /**
     * The size of this dictionary.
     */
    private final int size;

    /**
     * A list containing all names.
     */
    private final String[] names;

    /**
     * A list containing all roles.
     */
    private final String[] roles;

    /**
     * Creates a new dictionary
     * @param entries
     */
    public ComponentDictionaryImpl(NameRoleEntry... entries) {

        super();

        check(entries);

        size = entries.length;
        names = new String[size];
        roles = new String[size];

        for (int index = 0; index < size; index++) {

            NameRoleEntry entry = entries[index];
            names[index] = entry.name;
            roles[index] = entry.role;
        }
    }

    /**
     * Checks the specified entries.
     *
     * @param entries
     *        entry pairs of names and roles
     *
     * @return the specified entries
     */
    private static NameRoleEntry[] check(NameRoleEntry... entries) {

        if (entries == null) {

            throw new IllegalArgumentException("No entries (null) were specified!");
        }

        int length = entries.length;
        for (int index = 0; index < length; index++) {

            NameRoleEntry entry = entries[index];

            if (entry == null) {

                throw new IllegalArgumentException("Invalid entries (null) were specified!");
            }
        }

        return entries;
    }

    /**
     * Checks if the dictionary contains the specified name.
     *
     * @param name
     *        a name
     *
     * @return <code>true</code> if the dictionary contains the specified name, else <code>false</code>
     */
    @Override
    public boolean existsName(String name) {

        for (int index = 0; index < names.length; index++) {

            String actualName = names[index];
            if (actualName.equals(name)) {

                return true;
            }
        }

        return false;
    }

    /**
     * Checks if the dictionary contains the specified role.
     *
     * @param role
     *        a role
     *
     * @return <code>true</code> if the dictionary contains the specified role, else <code>false</code>
     */
    @Override
    public boolean existsRole(String role) {

        for (int index = 0; index < roles.length; index++) {

            String actualRole = roles[index];
            if (actualRole.equals(role)) {

                return true;
            }
        }

        return false;
    }

    /**
     * Returns the name which is associated with the specified role.
     *
     * @param role
     *        a role
     *
     * @return the role associated with the name or <code>null</code>
     */
    @Override
    public String getNameByRole(String role) {

        int foundIndex = INDEX_NOT_FOUND;
        for (int index = 0; index < roles.length; index++) {

            String actualRole = roles[index];
            if (actualRole.equals(role)) {

                foundIndex = index;
                break;
            }
        }

        if (foundIndex <= INDEX_NOT_FOUND) {

            String message = String.format("The dictionary doesn't contain the name '%s'!", role);
            throw new IllegalArgumentException(message);
        }

        return names[foundIndex];
    }

    /**
     * Returns the role which is associated with the specified name.
     *
     * @param name
     *        a name
     *
     * @return the name which is associated with the specified role.
     */
    @Override
    public String getRoleByName(String name) {

        int foundIndex = INDEX_NOT_FOUND;
        for (int index = 0; index < names.length; index++) {

            String actualName = names[index];
            if (actualName.equals(name)) {

                foundIndex = index;
                break;
            }
        }

        if (foundIndex <= INDEX_NOT_FOUND) {

            String message = String.format("The dictionary doesn't contain the name '%s'!", name);
            throw new IllegalArgumentException(message);
        }

        return roles[foundIndex];
    }

    /**
     * Returns all known names.
     *
     * @return all known names
     */
    @Override
    public String[] names() {

        return names.clone();
    }

    /**
     * Returns the size of the dictionary.
     *
     * @return the size of the dictionary
     */
    @Override
    public int size() {

        return size;
    }

}
