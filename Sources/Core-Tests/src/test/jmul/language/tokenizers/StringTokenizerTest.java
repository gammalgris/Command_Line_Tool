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

package test.jmul.language.tokenizers;


import jmul.language.tokenizers.StringTokenizer;
import jmul.language.tokenizers.Tokenizer;
import jmul.language.types.Sequence;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


/**
 * This test suites tests a string tokenizer.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class StringTokenizerTest {

    /**
     * The object that is tested.
     */
    private Tokenizer<String, String> tokenizer;

    /**
     * Prepares everythingthat is required for the test.
     */
    @Before
    public void setUp() {

        tokenizer = new StringTokenizer();
    }

    /**
     * Cleans up after a test.
     */
    @After
    public void tearDown() {

        tokenizer = null;
    }

    /**
     * Tests the tokenizer with a <code>null</code> string.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullInput() {

        String input = null;

        tokenizer.split(input);
    }

    /**
     * Tests the tokenizer with an empty string.
     */
    @Test
    public void testEmptyInput() {

        String input = "";

        Sequence<String> result = tokenizer.split(input);

        assertEquals("The result is not empty!", 0, result.size());
        assertEquals("The result is not empty!", true, result.isEmpty());
    }

    /**
     * Tests the tokenizer with a string containing only of spaces.
     */
    @Test
    public void testSpacesOnlyInput() {

        String input = "    ";

        Sequence<String> result = tokenizer.split(input);

        assertEquals("The result is not empty!", 0, result.size());
        assertEquals("The result is not empty!", true, result.isEmpty());
    }

    /**
     * Tests the tokenizer with a simple sentence.
     */
    @Test
    public void testSentenceInput() {

        String input = "I am going to the library.";

        Sequence<String> result = tokenizer.split(input);

        assertEquals("The result is not empty!", 6, result.size());
        assertEquals("The result is not empty!", false, result.isEmpty());

        assertEquals("The element doesn't match!", "I", result.elementAt(0));
        assertEquals("The element doesn't match!", "am", result.elementAt(1));
        assertEquals("The element doesn't match!", "going", result.elementAt(2));
        assertEquals("The element doesn't match!", "to", result.elementAt(3));
        assertEquals("The element doesn't match!", "the", result.elementAt(4));
        assertEquals("The element doesn't match!", "library.", result.elementAt(5));
    }

    /**
     * Tests the tokenizer with two simple sentences.
     */
    @Test
    public void testTwoSentencesInput() {

        String input = "I am going to the library. It is raining today.";

        Sequence<String> result = tokenizer.split(input);

        assertEquals("The result is not empty!", 10, result.size());
        assertEquals("The result is not empty!", false, result.isEmpty());

        assertEquals("The element doesn't match!", "I", result.elementAt(0));
        assertEquals("The element doesn't match!", "am", result.elementAt(1));
        assertEquals("The element doesn't match!", "going", result.elementAt(2));
        assertEquals("The element doesn't match!", "to", result.elementAt(3));
        assertEquals("The element doesn't match!", "the", result.elementAt(4));
        assertEquals("The element doesn't match!", "library.", result.elementAt(5));
        assertEquals("The element doesn't match!", "It", result.elementAt(6));
        assertEquals("The element doesn't match!", "is", result.elementAt(7));
        assertEquals("The element doesn't match!", "raining", result.elementAt(8));
        assertEquals("The element doesn't match!", "today.", result.elementAt(9));
    }

}
