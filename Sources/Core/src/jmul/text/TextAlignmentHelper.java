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

package jmul.text;


import static jmul.string.Constants.SPACE;


/**
 * A helper class for normalizing and aligning texts.
 *
 * @author Kristian Kutin
 */
public final class TextAlignmentHelper {

    /**
     * The default constructor.
     */
    private TextAlignmentHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Normalizes and aligns the specified log level string.
     *
     * @param targetLength
     *        the target string size for normalizing and aligning of the specified text
     * @param horizontalAlignment
     *        a horizotnal alignment
     * @param text
     *        a text
     *
     * @return a normalized and aligned log level string
     */
    public static String align(int targetLength, HorizontalTextAlignment horizontalAlignment, String text) {

        checkHorizontalTextAligment(horizontalAlignment);
        checkText(text);

        StringBuilder buffer = new StringBuilder(text);
        int missingBlanks = targetLength - buffer.length();

        if (missingBlanks < 0) {

            throw new IllegalArgumentException("The specified target length is smaller than the specified text length!");
        }

        if (horizontalAlignment == HorizontalTextAlignments.RIGHT) {

            for (int a = 0; a < missingBlanks; a++) {

                buffer.insert(0, SPACE);
            }

        } else if (horizontalAlignment == HorizontalTextAlignments.CENTER) {

            int equidistance = missingBlanks / 2;
            int remainder = missingBlanks % 2;

            for (int a = 0; a < equidistance; a++) {

                buffer.insert(0, SPACE);
                buffer.append(SPACE);
            }

            if (remainder > 0) {

                buffer.append(SPACE);
            }

        } else if (horizontalAlignment == HorizontalTextAlignments.LEFT) {

            for (int a = 0; a < missingBlanks; a++) {

                buffer.append(SPACE);
            }
        }

        return buffer.toString();
    }

    /**
     * Checks the specified text alignment.
     *
     * @param horizontalAlignment
     *        a text alignment
     *
     * @return the specified text alignment
     */
    private static HorizontalTextAlignment checkHorizontalTextAligment(HorizontalTextAlignment horizontalAlignment) {

        if (horizontalAlignment == null) {

            throw new IllegalArgumentException("No alignment (null) was specified!");
        }

        return horizontalAlignment;
    }

    /**
     * Checks the specified text.
     *
     * @param text
     *        a text
     *
     * @return the specified text
     */
    private static String checkText(String text) {

        if (text == null) {

            throw new IllegalArgumentException("No text (null) was specified!");
        }

        return text;
    }

}
