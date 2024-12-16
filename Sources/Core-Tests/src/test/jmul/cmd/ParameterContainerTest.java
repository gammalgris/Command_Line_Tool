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
import jmul.cmd.DuplicateParameterNamesException;
import jmul.cmd.FixedPositionParameter;
import jmul.cmd.MissingMandatoryParametersException;
import jmul.cmd.MissingParameterException;
import jmul.cmd.ParameterContainer;
import jmul.cmd.ParameterContainerImpl;
import jmul.cmd.ParameterOccurrence;
import jmul.cmd.ParameterOccurrences;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;


/**
 * This test suite tests a parameter container.
 *
 * @author Kristian Kuti
 */
@UnitTest
public class ParameterContainerTest {

    /**
     * Creates a specific parameter description.
     *
     * @param name
     *        the parameter name
     * @param value
     *        the parameter value
     * @param occurrence
     *        the parameter occurrence
     *
     * @return a parameter description
     */
    private ConstantValueParameter createConstantValueParameter(String name, String value,
                                                                ParameterOccurrence occurrence) {

        ConstantValueParameter parameter = new ConstantValueParameter(name, value, occurrence);

        assertEquals(name, parameter.name());
        assertEquals(value, parameter.value());

        if (ParameterOccurrences.MANDATORY == occurrence) {

            assertEquals(true, parameter.isMandatory());
            assertEquals(false, parameter.isOptional());

        } else {

            assertEquals(false, parameter.isMandatory());
            assertEquals(true, parameter.isOptional());
        }

        return parameter;
    }

    /**
     * Creates a specific parameter description.
     *
     * @param name
     *        the parameter name
     * @param index
     *        the index position of the parameter
     * @param occurrence
     *        the parameter occurrence
     *
     * @return a parameter description
     */
    private FixedPositionParameter createFixedPositionParameter(String name, int index,
                                                                ParameterOccurrence occurrence) {

        FixedPositionParameter parameter = new FixedPositionParameter(name, index, occurrence);

        assertEquals(name, parameter.name());
        assertEquals(index, parameter.index());

        if (ParameterOccurrences.MANDATORY == occurrence) {

            assertEquals(true, parameter.isMandatory());
            assertEquals(false, parameter.isOptional());

        } else {

            assertEquals(false, parameter.isMandatory());
            assertEquals(true, parameter.isOptional());
        }

        return parameter;
    }

    /**
     * Tests the parameter container with duplicate parameter names.
     */
    @Test
    public void testCreateParameterContainerWithDuplicateParameterNames() {

        ConstantValueParameter parameter1 =
            createConstantValueParameter("v1", "blabla", ParameterOccurrences.MANDATORY);
        FixedPositionParameter parameter2 = createFixedPositionParameter("v1", 2, ParameterOccurrences.MANDATORY);

        String[] parameters = { "Hallo", "World!" };

        ParameterContainer container = null;
        try {

            container = new ParameterContainerImpl(parameters, parameter1, parameter2);
            fail();

        } catch (DuplicateParameterNamesException e) {

            String duplicateNames = String.format("([%s])", parameter1.name());
            assertTrue(e.getMessage().contains(duplicateNames));
        }
    }

    /**
     * Tests the parameter container with mandatory parmaters and no matching parameters.
     */
    @Test
    public void testCreateParameterContainerWithMandatoryParametersOnlyAndNoMatches() {

        ConstantValueParameter parameter1 =
            createConstantValueParameter("v1", "blabla", ParameterOccurrences.MANDATORY);
        FixedPositionParameter parameter2 = createFixedPositionParameter("v2", 2, ParameterOccurrences.MANDATORY);

        String[] parameters = { "Hallo", "World!" };

        ParameterContainer container = null;
        try {

            container = new ParameterContainerImpl(parameters, parameter1, parameter2);
            fail();

        } catch (MissingMandatoryParametersException e) {

            String missingParameters = String.format("([%s, %s])", parameter1.name(), parameter2.name());
            assertTrue(e.getMessage().contains(missingParameters));
        }
    }

    /**
     * Tests the parameter container with optional parameters and no matches.
     */
    @Test
    public void testCreateParameterContainerWithOptionalParametersOnlyAndNoMatches() {

        ConstantValueParameter parameter1 = createConstantValueParameter("v1", "blabla", ParameterOccurrences.OPTIONAL);
        FixedPositionParameter parameter2 = createFixedPositionParameter("v2", 2, ParameterOccurrences.OPTIONAL);

        String[] parameters = { "Hallo", "World!" };

        ParameterContainer container = new ParameterContainerImpl(parameters, parameter1, parameter2);
        assertEquals(0, container.names().size());
        assertFalse(container.existsParameter(parameter1.name()));
        assertFalse(container.existsParameter(parameter2.name()));
    }

    /**
     * Tests the parameter container with mixed parameters and one match.
     */
    @Test
    public void testCreateParameterContainerWithMixedParametersAndOneMatch() {

        ConstantValueParameter parameter1 =
            createConstantValueParameter("v1", "blabla", ParameterOccurrences.MANDATORY);
        FixedPositionParameter parameter2 = createFixedPositionParameter("v2", 2, ParameterOccurrences.OPTIONAL);

        String[] parameters = { "Hallo", "blabla" };

        ParameterContainer container = new ParameterContainerImpl(parameters, parameter1, parameter2);
        assertEquals(1, container.names().size());
        assertTrue(container.names().contains(parameter1.name()));
        assertTrue(container.existsParameter(parameter1.name()));
        assertEquals("blabla", container.getParameterValue(parameter1.name()));
        assertFalse(container.existsParameter(parameter2.name()));
    }

    /**
     * Tests the parameter container with mixed parameters and all match.
     */
    @Test
    public void testCreateParameterContainerWithMixedParametersAndAllMatch() {

        ConstantValueParameter parameter1 =
            createConstantValueParameter("v1", "blabla", ParameterOccurrences.MANDATORY);
        FixedPositionParameter parameter2 = createFixedPositionParameter("v2", 0, ParameterOccurrences.OPTIONAL);

        String[] parameters = { "Hallo", "blabla" };

        ParameterContainer container = new ParameterContainerImpl(parameters, parameter1, parameter2);
        assertEquals(2, container.names().size());
        assertTrue(container.names().contains(parameter1.name()));
        assertTrue(container.names().contains(parameter2.name()));
        assertTrue(container.existsParameter(parameter1.name()));
        assertEquals("blabla", container.getParameterValue(parameter1.name()));
        assertTrue(container.existsParameter(parameter2.name()));
        assertEquals("Hallo", container.getParameterValue(parameter2.name()));
    }

    /**
     * Tests retrieving the value of an unknown parameter (i.e. a parameter that doesn't match).
     */
    @Test(expected = MissingParameterException.class)
    public void testAccessUnknownParameter() {

        ConstantValueParameter parameter1 =
            createConstantValueParameter("v1", "blabla", ParameterOccurrences.MANDATORY);
        FixedPositionParameter parameter2 = createFixedPositionParameter("v2", 0, ParameterOccurrences.OPTIONAL);

        String[] parameters = { "Hallo", "blabla" };

        ParameterContainer container = new ParameterContainerImpl(parameters, parameter1, parameter2);
        assertEquals(2, container.names().size());

        container.getParameterValue("test");
    }

}
