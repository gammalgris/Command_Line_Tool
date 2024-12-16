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


import jmul.language.catalogues.StringTokenCatalogue;
import jmul.language.catalogues.TokenCatalogue;
import jmul.language.tokenizers.SentenceTokenizer;
import jmul.language.tokenizers.StringTokenizer;
import jmul.language.tokenizers.Tokenizer;
import jmul.language.types.Sequence;
import jmul.language.types.StringSequence;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


/**
 * This test suites tests a sentence tokenizer.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class SentenceTokenizerTest {

    /**
     * A token catalogue.
     */
    private static TokenCatalogue TOKEN_CATALOGUE;

    /**
     * A token category.
     */
    private static String CATEGORY;

    /**
     * The static initializer.
     */
    static {

        CATEGORY = "punctuation mark";

        TOKEN_CATALOGUE = new StringTokenCatalogue();
        TOKEN_CATALOGUE.addToken("point", ".", CATEGORY);
        TOKEN_CATALOGUE.addToken("comma", ",", CATEGORY);
        TOKEN_CATALOGUE.addToken("exclamation mark", "!", CATEGORY);
        TOKEN_CATALOGUE.addToken("question mark", "?", CATEGORY);
        TOKEN_CATALOGUE.addToken("quote", "'", CATEGORY);
        TOKEN_CATALOGUE.addToken("double quote", "\"", CATEGORY);
        TOKEN_CATALOGUE.addToken("hyphen", "-", CATEGORY);
    }

    /**
     * The object that is tested.
     */
    private Tokenizer<Sequence<String>, String> tokenizer;

    /**
     * Prepares everythingthat is required for the test.
     */
    @Before
    public void setUp() {

        tokenizer = new SentenceTokenizer(TOKEN_CATALOGUE, CATEGORY);
    }

    /**
     * Cleans up after a test.
     */
    @After
    public void tearDown() {

        tokenizer = null;
    }

    /**
     * Tests the tokenizer with a <code>null</code> sequence.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullInput() {

        Sequence<String> input = null;

        tokenizer.split(input);
    }

    /**
     * Tests the tokenizer with an empty sequence.
     */
    @Test
    public void testEmptyInput() {

        Sequence<String> input = new StringSequence();

        Sequence<String> result = tokenizer.split(input);

        assertEquals("The result is not empty!", 0, result.size());
        assertEquals("The result is not empty!", true, result.isEmpty());
    }

    /**
     * Tests the tokenizer with a sentence.
     */
    @Test
    public void testSentenceInput() {

        String input = "Hello world, but it's evening already.";

        Tokenizer<String, String> firstLayer = new StringTokenizer();
        Sequence<String> first = firstLayer.split(input);

        Sequence<String> last = tokenizer.split(first);

        assertEquals("The sequence is not matching!", 10, last.size());
        assertEquals("The sequence is empty!", false, last.isEmpty());

        assertEquals("The element is not matching!", "Hello", last.elementAt(0));
        assertEquals("The element is not matching!", "world", last.elementAt(1));
        assertEquals("The element is not matching!", ",", last.elementAt(2));
        assertEquals("The element is not matching!", "but", last.elementAt(3));
        assertEquals("The element is not matching!", "it", last.elementAt(4));
        assertEquals("The element is not matching!", "'", last.elementAt(5));
        assertEquals("The element is not matching!", "s", last.elementAt(6));
        assertEquals("The element is not matching!", "evening", last.elementAt(7));
        assertEquals("The element is not matching!", "already", last.elementAt(8));
        assertEquals("The element is not matching!", ".", last.elementAt(9));
    }

}
