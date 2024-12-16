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

package test.jmul.cmd;


import jmul.cmd.ConstantValueParameter;
import jmul.cmd.FixedPositionParameter;
import jmul.cmd.ParameterOccurrence;
import jmul.cmd.ParameterOccurrences;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite tests various parameter implementations. See the corresponding package.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class CreateParameterTest {

    /**
     * Tests creating a constant value parameter.
     */
    @Test
    public void testCreateConstantValueParameter() {

        String name = "a";
        String value = "b";
        ParameterOccurrence occurrence = ParameterOccurrences.MANDATORY;

        ConstantValueParameter parameter = new ConstantValueParameter(name, value, occurrence);
        assertEquals(name, parameter.name());
        assertEquals(value, parameter.value());
        assertEquals(true, parameter.isMandatory());
        assertEquals(false, parameter.isOptional());
    }

    /**
     * Tests creating a constant value parameter with a <code>null</code> name.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateConstantValueParameterWithNullName() {

        String name = null;
        String value = "b";
        ParameterOccurrence occurrence = ParameterOccurrences.MANDATORY;

        new ConstantValueParameter(name, value, occurrence);
    }

    /**
     * Tests creating a constant value parameter with a <code>null</code> value.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateConstantValueParameterWithNullValue() {

        String name = "a";
        String value = null;
        ParameterOccurrence occurrence = ParameterOccurrences.MANDATORY;

        new ConstantValueParameter(name, value, occurrence);
    }

    /**
     * Tests creating a constant value parameter with a <code>null</code> occurrence.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateConstantValueParameterWithNullOccurrence() {

        String name = "a";
        String value = "b";
        ParameterOccurrence occurrence = null;

        new ConstantValueParameter(name, value, occurrence);
    }

    /**
     * Tests creating a fixed position parameter.
     */
    @Test
    public void testCreateFixedPositionParameter() {

        String name = "a";
        Integer index = 0;
        ParameterOccurrence occurrence = ParameterOccurrences.MANDATORY;

        FixedPositionParameter parameter = new FixedPositionParameter(name, index, occurrence);
        assertEquals(name, parameter.name());
        assertEquals(index.intValue(), parameter.index());
        assertEquals(true, parameter.isMandatory());
        assertEquals(false, parameter.isOptional());
    }

    /**
     * Tests creating a fixed position parameter with a <code>null</code> name.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateFixedPositionParameterWithNullName() {

        String name = null;
        Integer index = 0;
        ParameterOccurrence occurrence = ParameterOccurrences.MANDATORY;

        new FixedPositionParameter(name, index, occurrence);
    }

    /**
     * Tests creating a fixed position parameter with a <code>null</code> index.
     */
    @Test(expected = NullPointerException.class)
    public void testCreateFixedPositionParameterWithNullIndex() {

        String name = "a";
        Integer index = null;
        ParameterOccurrence occurrence = ParameterOccurrences.MANDATORY;

        new FixedPositionParameter(name, index, occurrence);
    }

    /**
     * Tests creating a fixed position parameter with a negative index.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateFixedPositionParameterWithNegativeIndex() {

        String name = "a";
        Integer index = -1;
        ParameterOccurrence occurrence = ParameterOccurrences.MANDATORY;

        new FixedPositionParameter(name, index, occurrence);
    }

    /**
     * Tests creating a fixed position parameter with a <code>null</code> occurrence.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateFixedPositionParameterWithNullOccurrence() {

        String name = "a";
        Integer index = 0;
        ParameterOccurrence occurrence = null;

        new FixedPositionParameter(name, index, occurrence);
    }

}
