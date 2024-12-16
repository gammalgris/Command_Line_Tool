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

package test.jmul.language.types;


import jmul.language.types.Sequence;
import jmul.language.types.StringSequence;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suites tests a string sequence.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class StringSequenceTest {

    /**
     * Tests the properties of an empty string sequence.
     */
    @Test
    public void testEmptySequence() {

        Sequence<String> sequence = new StringSequence();

        assertEquals("The sequence is not empty!", 0, sequence.size());
        assertEquals("The sequence is not empty!", true, sequence.isEmpty());
    }

    /**
     * Tests accessing an element with a negative index.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGettingElementFromEmptySequenceWithNegativeIndex() {

        Sequence<String> sequence = new StringSequence();

        sequence.elementAt(-1);
    }

    /**
     * Tests accessing an element with an index of zero.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGettingElementFromEmptySequenceWithZeroIndex() {

        Sequence<String> sequence = new StringSequence();

        sequence.elementAt(0);
    }

    /**
     * Tests the properties of an string sequence with one element.
     */
    @Test
    public void testSequenceWithOneElement() {

        String firstElement = "Hello";
        String[] input = new String[] { firstElement };
        Sequence<String> sequence = new StringSequence(input);

        assertEquals("The sequence is not empty!", 1, sequence.size());
        assertEquals("The sequence is not empty!", false, sequence.isEmpty());
    }

    /**
     * Tests accessing an element with a negative index.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGettingElementFromOneElementSequenceWithNegativeIndex() {

        String firstElement = "Hello";
        String[] input = new String[] { firstElement };
        Sequence<String> sequence = new StringSequence(input);

        sequence.elementAt(-1);
    }

    /**
     * Tests accessing an element with an index of zero.
     */
    @Test
    public void testGettingElementFromOneElementSequenceWithZeroIndex() {

        String firstElement = "Hello";
        String[] input = new String[] { firstElement };
        Sequence<String> sequence = new StringSequence(input);

        String element = sequence.elementAt(0);
        assertEquals("The elements are not equals!", firstElement, element);
    }

    /**
     * Tests the properties of an string sequence with one element.
     */
    @Test
    public void testSequenceWithTwoElements() {

        String firstElement = "Hello";
        String secondElement = "World";
        String[] input = new String[] { firstElement, secondElement };
        Sequence<String> sequence = new StringSequence(input);

        assertEquals("The sequence is not empty!", 2, sequence.size());
        assertEquals("The sequence is not empty!", false, sequence.isEmpty());
    }

    /**
     * Tests accessing an element with a negative index.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGettingElementFromTwoElementsSequenceWithNegativeIndex() {

        String firstElement = "Hello";
        String secondElement = "World";
        String[] input = new String[] { firstElement, secondElement };
        Sequence<String> sequence = new StringSequence(input);

        sequence.elementAt(-1);
    }

    /**
     * Tests accessing an element with an index of zero.
     */
    @Test
    public void testGettingElementFromTwoElementsSequenceWithZeroIndex() {

        String firstElement = "Hello";
        String secondElement = "World";
        String[] input = new String[] { firstElement, secondElement };
        Sequence<String> sequence = new StringSequence(input);

        String element = sequence.elementAt(0);
        assertEquals("The elements are not equals!", firstElement, element);

        element = sequence.elementAt(1);
        assertEquals("The elements are not equals!", secondElement, element);
    }

    /**
     * Tests if the iterator is working properly.
     */
    @Test
    public void testIterator() {

        String firstElement = "Hello";
        String secondElement = "World";
        String thirdElement = "Hello";
        String fourthElement = "Gents";
        String[] input = new String[] { firstElement, secondElement, thirdElement, fourthElement };
        Sequence<String> sequence = new StringSequence(input);

        int index = 0;
        for (String element : sequence) {

            String message = String.format("The element at index %d is not matching!", index);
            assertEquals(message, input[index], element);

            index++;
        }
    }

}
