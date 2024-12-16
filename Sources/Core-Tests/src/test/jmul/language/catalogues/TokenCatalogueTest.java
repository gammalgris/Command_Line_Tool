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

package test.jmul.language.catalogues;


import java.util.ArrayList;
import java.util.List;

import jmul.language.catalogues.StringTokenCatalogue;
import jmul.language.catalogues.TokenCatalogue;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


/**
 * This test suite tests a token catalogue.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class TokenCatalogueTest {

    /**
     * The object which is tested.
     */
    private TokenCatalogue catalogue;

    /**
     * Prepares everything that is needed for the test.
     */
    @Before
    public void setUp() {

        catalogue = new StringTokenCatalogue();
    }

    /**
     * Cleans up after a test.
     */
    @After
    public void tearDown() {

        catalogue = null;
    }

    /**
     * Checks an empty token catalogue.
     */
    @Test
    public void testEmptyCatalogue() {

        assertEquals("The catalogue is not empty!", 0, catalogue.size());
        assertEquals("The catalogue is not empty!", true, catalogue.isEmpty());
    }

    /**
     * Tests adding a new token with no name (null).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddingTokenWithNullName() {

        String name = null;
        String token = ".";
        String context1 = "punctuation mark";
        String context2 = "language";
        String[] contexts = { context1, context2 };

        catalogue.addToken(name, token, contexts);

        assertEquals("The catalogue is not empty!", 0, catalogue.size());
        assertEquals("The catalogue is not empty!", true, catalogue.isEmpty());
    }

    /**
     * Tests adding a new token with no name (empty string).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddingTokenWithEmptyName() {

        String name = "";
        String token = ".";
        String context1 = "punctuation mark";
        String context2 = "language";
        String[] contexts = { context1, context2 };

        catalogue.addToken(name, token, contexts);

        assertEquals("The catalogue is not empty!", 0, catalogue.size());
        assertEquals("The catalogue is not empty!", true, catalogue.isEmpty());
    }

    /**
     * Tests adding a new token with no token (null).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddingTokenWithNullToken() {

        String name = "point";
        String token = null;
        String context1 = "punctuation mark";
        String context2 = "language";
        String[] contexts = { context1, context2 };

        catalogue.addToken(name, token, contexts);

        assertEquals("The catalogue is not empty!", 0, catalogue.size());
        assertEquals("The catalogue is not empty!", true, catalogue.isEmpty());
    }

    /**
     * Tests adding a new token with no token (empty string).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddingTokenWithEmptyToken() {

        String name = "point";
        String token = "";
        String context1 = "punctuation mark";
        String context2 = "language";
        String[] contexts = { context1, context2 };

        catalogue.addToken(name, token, contexts);

        assertEquals("The catalogue is not empty!", 0, catalogue.size());
        assertEquals("The catalogue is not empty!", true, catalogue.isEmpty());
    }

    /**
     * Tests adding a new token with no contexts (null).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddingTokenWithNullContext() {

        String name = "point";
        String token = ".";
        String[] contexts = null;

        catalogue.addToken(name, token, contexts);

        assertEquals("The catalogue is not empty!", 0, catalogue.size());
        assertEquals("The catalogue is not empty!", true, catalogue.isEmpty());
    }

    /**
     * Tests adding a new token with no contexts (empty array).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddingTokenWithEmptyContext() {

        String name = "point";
        String token = "";
        String[] contexts = { };

        catalogue.addToken(name, token, contexts);

        assertEquals("The catalogue is not empty!", 0, catalogue.size());
        assertEquals("The catalogue is not empty!", true, catalogue.isEmpty());
    }

    /**
     * Tests adding a new token with invalid first context (null).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddingTokenWithNullFirstContext() {

        String name = "point";
        String token = ".";
        String context1 = null;
        String context2 = "language";
        String[] contexts = { context1, context2 };

        catalogue.addToken(name, token, contexts);

        assertEquals("The catalogue is not empty!", 0, catalogue.size());
        assertEquals("The catalogue is not empty!", true, catalogue.isEmpty());
    }

    /**
     * Tests adding a new token with invalid second cintext (null).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddingTokenWithNullSecondContext() {

        String name = "point";
        String token = ".";
        String context1 = "punctuation mark";
        String context2 = null;
        String[] contexts = { context1, context2 };

        catalogue.addToken(name, token, contexts);

        assertEquals("The catalogue is not empty!", 0, catalogue.size());
        assertEquals("The catalogue is not empty!", true, catalogue.isEmpty());
    }

    /**
     * Tests adding a new token with invalid first context (null).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddingTokenWithEmptyFirstContext() {

        String name = "point";
        String token = ".";
        String context1 = "";
        String context2 = "language";
        String[] contexts = { context1, context2 };

        catalogue.addToken(name, token, contexts);

        assertEquals("The catalogue is not empty!", 0, catalogue.size());
        assertEquals("The catalogue is not empty!", true, catalogue.isEmpty());
    }

    /**
     * Tests adding a new token with invalid second cintext (null).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddingTokenWithEmptySecondContext() {

        String name = "point";
        String token = ".";
        String context1 = "punctuation mark";
        String context2 = "";
        String[] contexts = { context1, context2 };

        catalogue.addToken(name, token, contexts);

        assertEquals("The catalogue is not empty!", 0, catalogue.size());
        assertEquals("The catalogue is not empty!", true, catalogue.isEmpty());
    }

    /**
     * Tests adding a new token.
     */
    @Test
    public void testAddingToken() {

        String name = "point";
        String token = ".";
        String context1 = "punctuation mark";
        String context2 = "language";
        String[] contexts = { context1, context2 };

        catalogue.addToken(name, token, contexts);

        assertEquals("The catalogue is empty!", 1, catalogue.size());
        assertEquals("The catalogue is empty!", false, catalogue.isEmpty());
    }

    /**
     * Tests iterating though the catalgue.
     */
    @Test
    public void testIterator() {

        String name1 = "point";
        String token = ".";
        String context1 = "punctuation mark";
        String context2 = "language";
        String[] contexts = { context1, context2 };

        catalogue.addToken(name1, token, contexts);

        String name2 = "decimal separator";
        token = ".";
        context1 = "number representation";
        context2 = "math";
        contexts = new String[] { context1, context2 };

        catalogue.addToken(name2, token, contexts);

        String name3 = "comma";
        token = ",";
        context1 = "punctuation mark";
        context2 = "language";
        contexts = new String[] { context1, context2 };

        catalogue.addToken(name3, token, contexts);

        assertEquals("The catalogue is empty!", 3, catalogue.size());
        assertEquals("The catalogue is empty!", false, catalogue.isEmpty());

        List<String> controlList = new ArrayList<>();
        for (String name : catalogue) {

            controlList.add(name);
        }

        String[] names = { name1, name2, name3 };
        for (String name : names) {

            controlList.remove(name);
        }
        assertEquals("Iterator doesn't work properly!", 0, controlList.size());
    }

}
