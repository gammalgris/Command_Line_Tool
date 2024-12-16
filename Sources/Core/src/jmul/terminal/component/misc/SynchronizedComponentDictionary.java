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
 * A thread safe implementation of a component dictonary.
 *
 * @author Kristian Kutin
 * 
 * @deprecated Superfluous implementation.
 */
@Deprecated
public class SynchronizedComponentDictionary implements ComponentDictionary {

    /**
     * A reference to a component dictionary that is not thread safe.
     */
    private final ComponentDictionary componentDictionary;

    /**
     * Creates a new instance according to the sprecified parameters.
     *
     * @param componentDictionary
     *        a component dictionary that is not thread safe
     */
    public SynchronizedComponentDictionary(ComponentDictionary componentDictionary) {

        super();

        this.componentDictionary = check(componentDictionary);
    }

    /**
     * Checks the specified parameter. If the parameter is invalid an exception is thrown.
     *
     * @param componentDictionary
     *        a component dictionary
     *
     * @return the specified parameter
     */
    private static ComponentDictionary check(ComponentDictionary componentDictionary) {

        if (componentDictionary == null) {

            throw new IllegalArgumentException("No component dicitonary (null) was specified!");
        }

        return componentDictionary;
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

        synchronized (componentDictionary) {

            return componentDictionary.existsName(name);
        }
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

        synchronized (componentDictionary) {

            return existsRole(role);
        }
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

        synchronized (componentDictionary) {

            return componentDictionary.getNameByRole(role);
        }
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

        synchronized (componentDictionary) {

            return componentDictionary.getRoleByName(name);
        }
    }

    /**
     * Returns all known names.
     *
     * @return all known names
     */
    @Override
    public String[] names() {

        synchronized (componentDictionary) {

            return componentDictionary.names();
        }
    }

    /**
     * Returns the size of the dictionary.
     *
     * @return the size of the dictionary
     */
    @Override
    public int size() {

        synchronized (componentDictionary) {

            return componentDictionary.size();
        }
    }

}
