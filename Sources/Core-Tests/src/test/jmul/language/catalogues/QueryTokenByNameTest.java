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


import jmul.language.catalogues.StringTokenCatalogue;
import jmul.language.catalogues.TokenCatalogue;

import jmul.query.QueryResult;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


/**
 * This test suite tests querying the catalogue.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class QueryTokenByNameTest {

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
     * Tests performing a query.
     */
    @Test
    public void testOneTokenOneMatch() {

        String name = "point";
        String token = ".";
        String context1 = "punctuation mark";
        String context2 = "language";
        String[] contexts = { context1, context2 };

        catalogue.addToken(name, token, contexts);

        assertEquals("The catalogue is empty!", 1, catalogue.size());
        assertEquals("The catalogue is empty!", false, catalogue.isEmpty());

        QueryResult<String> queryResult = catalogue.tokenByName("point");
        assertEquals("Query failed!", true, queryResult.existsResult());
        String foundToken = queryResult.result();
        assertEquals("Name anf token don't match!", token, foundToken);
    }

    /**
     * Tests performing a query.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testOneTokenNullQuery() {

        String name = "point";
        String token = ".";
        String context1 = "punctuation mark";
        String context2 = "language";
        String[] contexts = { context1, context2 };

        catalogue.addToken(name, token, contexts);

        assertEquals("The catalogue is empty!", 1, catalogue.size());
        assertEquals("The catalogue is empty!", false, catalogue.isEmpty());

        catalogue.tokenByName(null);
    }

    /**
     * Tests performing a query.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testOneTokenEmptyQuery() {

        String name = "point";
        String token = ".";
        String context1 = "punctuation mark";
        String context2 = "language";
        String[] contexts = { context1, context2 };

        catalogue.addToken(name, token, contexts);

        assertEquals("The catalogue is empty!", 1, catalogue.size());
        assertEquals("The catalogue is empty!", false, catalogue.isEmpty());

        catalogue.tokenByName("");
    }

    /**
     * Tests performing a query.
     */
    @Test
    public void testOneTokenNoMatch() {

        String name = "point";
        String token = ".";
        String context1 = "punctuation mark";
        String context2 = "language";
        String[] contexts = { context1, context2 };

        catalogue.addToken(name, token, contexts);

        assertEquals("The catalogue is empty!", 1, catalogue.size());
        assertEquals("The catalogue is empty!", false, catalogue.isEmpty());

        QueryResult<String> queryResult = catalogue.tokenByName("comma");
        assertEquals("Query failed!", false, queryResult.existsResult());
    }

    /**
     * Tests performing a query.
     */
    @Test
    public void testTwoTokensOneMatch() {

        String name = "point";
        String token1 = ".";
        String context1 = "punctuation mark";
        String context2 = "language";
        String[] contexts = { context1, context2 };

        catalogue.addToken(name, token1, contexts);

        name = "comma";
        String token2 = ",";
        context1 = "punctuation mark";
        context2 = "language";
        contexts = new String[] { context1, context2 };

        catalogue.addToken(name, token2, contexts);

        assertEquals("The catalogue is empty!", 2, catalogue.size());
        assertEquals("The catalogue is empty!", false, catalogue.isEmpty());

        QueryResult<String> queryResult = catalogue.tokenByName("comma");
        assertEquals("Query failed!", true, queryResult.existsResult());
        String foundToken = queryResult.result();
        assertEquals("Tokens don't match!", token2, foundToken);
    }


    /**
     * Tests performing a query.
     */
    @Test
    public void testThreeTokensOneMatch() {

        String name1 = "point";
        String token1 = ".";
        String context1 = "punctuation mark";
        String context2 = "language";
        String[] contexts = { context1, context2 };

        catalogue.addToken(name1, token1, contexts);

        String name2 = "decimal separator";
        String token2 = ".";
        context1 = "number representation";
        context2 = "math";
        contexts = new String[] { context1, context2 };

        catalogue.addToken(name2, token2, contexts);

        String name3 = "comma";
        String token3 = ",";
        context1 = "punctuation mark";
        context2 = "language";
        contexts = new String[] { context1, context2 };

        catalogue.addToken(name3, token3, contexts);

        assertEquals("The catalogue is empty!", 3, catalogue.size());
        assertEquals("The catalogue is empty!", false, catalogue.isEmpty());

        QueryResult<String> queryResult = catalogue.tokenByName("comma");
        assertEquals("Query failed!", true, queryResult.existsResult());
        String foundToken = queryResult.result();
        assertEquals("Tokens don't match!", token3, foundToken);
    }

}
