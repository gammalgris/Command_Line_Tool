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

package jmul.language.types;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import jmul.math.hash.HashHelper;


/**
 * An implementation of an immutable sequence which contains string elements.
 *
 * @author Kristian Kutin
 */
public class StringSequence implements Sequence<String> {

    /**
     * The actual data structure that contains the sequence of elements.
     */
    private final List<String> sequence;

    /**
     * The default constructor.
     */
    public StringSequence() {

        super();

        sequence = new ArrayList<>();
    }

    /**
     * Creates a new sequence according to the specified parameter.
     *
     * @param elements
     *        an array of string elements
     */
    public StringSequence(String[] elements) {

        this(arrayToList(elements));
    }

    /**
     * Transforms the specified array into a list.
     *
     * @param elements
     *        an array of string elements
     *
     * @return an list of string elements
     */
    private static List<String> arrayToList(String[] elements) {

        checkElements(elements);

        List<String> list = Arrays.asList(elements);

        return list;
    }

    /**
     * Checks the specified array of string elements.
     *
     * @param elements
     *        an array of string elements
     *
     * @return the specified array of string elements
     */
    private static String[] checkElements(String[] elements) {

        if (elements == null) {

            throw new IllegalArgumentException("No elements (null) were specified!");
        }

        return elements;
    }

    /**
     * Creates a new sequence according to the specified parameter.
     *
     * @param elements
     *        a list of string elements
     */
    public StringSequence(List<String> elements) {

        super();

        sequence = listToUnmodifiableList(elements);
    }

    /**
     * Transforms the specified list into an unmodifiable list.
     *
     * @param list
     *        a list of string elements
     *
     * @return an unmodifiable list
     */
    private static List<String> listToUnmodifiableList(List<String> list) {

        checkElements(list);

        List<String> unmodifiableList = Collections.unmodifiableList(list);

        return unmodifiableList;
    }

    /**
     * Checks the specified list of string elements.
     *
     * @param elements
     *        a list of string elements
     *
     * @return the specified list of string elements
     */
    private static List<String> checkElements(List<String> elements) {

        if (elements == null) {

            throw new IllegalArgumentException("No elements (null) were specified!");
        }

        return elements;
    }

    /**
     * Returns the count of all elements within this sequence.
     *
     * @return the element count
     */
    @Override
    public int size() {

        return sequence.size();
    }

    /**
     * Checks if this sequence is empty (i.e. has no elements).
     *
     * @return <code>true</code> if this sequence is empty, else <code>false</code>
     */
    @Override
    public boolean isEmpty() {

        return sequence.isEmpty();
    }

    /**
     * Returns the element at the specified index position.
     *
     * @param index
     *        an index position
     *
     * @return the element at the specified index position
     */
    @Override
    public String elementAt(int index) {

        return sequence.get(index);
    }

    /**
     * Returns an iterator to iterate through this sequence.
     *
     * @return an iterator
     */
    @Override
    public Iterator<String> iterator() {

        return sequence.iterator();
    }

    /**
     * Returns a string representation for this sequence.
     *
     * @return a sequence
     */
    @Override
    public String toString() {

        return sequence.toString();
    }

    /**
     * Calculates a hash code for this sequence.
     *
     * @implNote
     * The calculated hash code will be the same during runtime but may vary between
     * different executions of the same program.
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {

        return HashHelper.calculateHashCode(List.class, sequence);
    }

    /**
     * Compares this sequence with another object or sequence.
     *
     * @param o
     *        another object or sequence
     *
     * @return <code>true</code> if this seqeunce is equal to the specified object or sequence,
     *         else <code>false</code>
     */
    @Override
    public boolean equals(Object o) {

        if (null == o) {

            return false;
        }

        if (this == o) {

            return true;
        }

        if (o instanceof StringSequence) {

            StringSequence other = (StringSequence) o;

            return this.sequence.equals(other.sequence);
        }

        return false;
    }

}
