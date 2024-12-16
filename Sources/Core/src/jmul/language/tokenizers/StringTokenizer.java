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

package jmul.language.tokenizers;


import java.util.ArrayList;
import java.util.List;

import jmul.language.types.Sequence;
import jmul.language.types.StringSequence;


/**
 * A implementation of a tokenizer that splits a string into a sequence of
 * non whitespace strings.
 *
 * @author Kristian Kutin
 */
public class StringTokenizer implements Tokenizer<String, String> {

    /**
     * The default constructor.
     */
    public StringTokenizer() {

        super();
    }

    /**
     * Splits the specifiec string into a sequence of non whitespace strings.
     *
     * @param input
     *        a string
     *
     * @return a sequence of non whitespace strings
     */
    @Override
    public Sequence<String> split(String input) {

        checkString(input);

        List<String> list = splitString(input);
        Sequence<String> sequence = new StringSequence(list);

        return sequence;
    }

    /**
     * Splits the specified string into a list of non whitespace strings.
     *
     * @param s
     *        a string
     *
     * @return a list of non whitespace strings
     */
    private List<String> splitString(String s) {

        String normalizedString = s.trim();

        List<String> list = new ArrayList<>();

        if (normalizedString.isEmpty()) {

            return list;
        }

        int index = 0;
        for (; index < normalizedString.length(); index++) {

            char c = normalizedString.charAt(index);

            if (Character.isWhitespace(c)) {

                break;
            }
        }

        String head = normalizedString.substring(0, index);
        String tail = normalizedString.substring(index);

        list.add(head);
        list.addAll(splitString(tail));

        return list;
    }

    /**
     * Checks the specified string.
     *
     * @param s
     *        a string
     *
     * @return the specified string
     */
    private static String checkString(String s) {

        if (s == null) {

            throw new IllegalArgumentException("No string (null) was specified!");
        }

        return s;
    }

}
