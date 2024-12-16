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

package test.jmul.terminal.component.misc;


import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import jmul.terminal.component.misc.ComponentDictionary;
import jmul.terminal.component.misc.ComponentDictionaryImpl;
import jmul.terminal.component.misc.NameRoleEntry;


/**
 * This test suite tests a component dictionary.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class ComponentDictionaryTest {

    /**
     * Tests creating an empty dictionary. Empty dictionaries are allowed.
     */
    @Test
    public void testCreateEmptyDictionary() {

        ComponentDictionary dictionary = new ComponentDictionaryImpl();
        assertEquals(0, dictionary.size());
    }

    /**
     * Tests creating a dictionary with one entry.
     */
    @Test
    public void testCreateDictionaryWithOneEntry() {

        String name = "a";
        String role = "b";

        NameRoleEntry entry = new NameRoleEntry(name, role);

        ComponentDictionary dictionary = new ComponentDictionaryImpl(entry);
        assertEquals(1, dictionary.size());

        assertTrue(dictionary.existsName(name));
        assertTrue(dictionary.existsRole(role));

        assertEquals(name, dictionary.getNameByRole(role));
        assertEquals(role, dictionary.getRoleByName(name));
    }

    /**
     * Tests creating a dictionary with two entries.
     */
    @Test
    public void testCreateDictionaryWithTwoEntries() {

        String name1 = "a";
        String role1 = "b";
        String name2 = "c";
        String role2 = "d";

        NameRoleEntry entry1 = new NameRoleEntry(name1, role1);
        NameRoleEntry entry2 = new NameRoleEntry(name2, role2);

        ComponentDictionary dictionary = new ComponentDictionaryImpl(entry1, entry2);
        assertEquals(2, dictionary.size());

        assertTrue(dictionary.existsName(name1));
        assertTrue(dictionary.existsName(name2));
        assertTrue(dictionary.existsRole(role1));
        assertTrue(dictionary.existsRole(role2));

        assertEquals(name1, dictionary.getNameByRole(role1));
        assertEquals(name2, dictionary.getNameByRole(role2));
        assertEquals(role1, dictionary.getRoleByName(name1));
        assertEquals(role2, dictionary.getRoleByName(name2));
    }

    /**
     * Tests creating a dictionary with invalid parameters (i.e. a <code>null</code> entry).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateDictionaryWithNullEntry() {

        NameRoleEntry entry = null;

        new ComponentDictionaryImpl(entry);
    }

    /**
     * Tests creating a dictionary with invalid parameters (i.e. a <code>null</code> role).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateDictionaryWithNullRole() {

        String name = "a";
        String role = null;

        new NameRoleEntry(name, role);
    }

    /**
     * Tests creating a dictionary with invalid parameters (i.e. a <code>null</code> name).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateDictionaryWithNullName() {

        String name = null;
        String role = "b";

        new NameRoleEntry(name, role);
    }

    /**
     * Tests getting all names from the dictioanry.
     */
    @Test
    public void testGettingNames() {

        String name1 = "a";
        String role1 = "b";
        String name2 = "c";
        String role2 = "d";

        NameRoleEntry entry1 = new NameRoleEntry(name1, role1);
        NameRoleEntry entry2 = new NameRoleEntry(name2, role2);

        ComponentDictionary dictionary = new ComponentDictionaryImpl(entry1, entry2);
        assertEquals(2, dictionary.size());

        String[] names = dictionary.names();
        assertEquals(2, names.length);

        assertEquals(name1, names[0]);
        assertEquals(name2, names[1]);
    }

}
