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

package test.jmul.text;


import java.util.ArrayList;
import java.util.Collection;

import jmul.test.classification.UnitTest;

import jmul.text.HorizontalTextAlignment;
import jmul.text.HorizontalTextAlignments;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import jmul.text.TextAlignmentHelper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests aligning texts.
 * 
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class TextAlignmentTest {

    /**
     * The expected result.
     */
    private final String expectedResult;

    /**
     * The input text.
     */
    private final String text;

    /**
     * The input text.
     */
    private final HorizontalTextAlignment alignment;

    /**
     * The size of the result string.
     */
    private final int length;

    /**
     * Creates a new test case according to the specified parameters.
     * 
     * @param expectedResult
     * @param text
     * @param alignment
     * @param length
     */
    public TextAlignmentTest(String expectedResult, String text, HorizontalTextAlignment alignment, int length) {

        super();

        this.expectedResult = expectedResult;
        this.text = text;
        this.alignment = alignment;
        this.length = length;
    }

    /**
     * Aligns text and checks the result.
     */
    @Test
    public void verifyTextAlignment() {

        String actualResult = TextAlignmentHelper. align(length, alignment, text);
        assertEquals(length, actualResult.length());
        assertTrue(actualResult.contains(text));
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { " test ", "test", HorizontalTextAlignments.CENTER, 6 });
        parameters.add(new Object[] { " test  ", "test", HorizontalTextAlignments.CENTER, 7 });
        parameters.add(new Object[] { "  test  ", "test", HorizontalTextAlignments.CENTER, 8 });
        parameters.add(new Object[] { "  test   ", "test", HorizontalTextAlignments.CENTER, 9 });

        parameters.add(new Object[] { "  test", "test", HorizontalTextAlignments.RIGHT, 6 });
        parameters.add(new Object[] { "   test", "test", HorizontalTextAlignments.RIGHT, 7 });

        parameters.add(new Object[] { "test  ", "test", HorizontalTextAlignments.LEFT, 6 });
        parameters.add(new Object[] { "test   ", "test", HorizontalTextAlignments.LEFT, 7 });

        return parameters;
    }

}
