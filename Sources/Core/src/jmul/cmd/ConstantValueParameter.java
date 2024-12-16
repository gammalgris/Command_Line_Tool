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

package jmul.cmd;


import jmul.language.types.Sequence;


/**
 * An implementation for a comtant value parameter.
 *
 * @author Kristian Kutin
 */
public class ConstantValueParameter extends ParameterDescriptionBase {

    /**
     * The parameter value.
     */
    private final String value;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param name
     *        a unique name for this parameter
     * @param value
     *        a parameter value
     * @param occurrence
     *        indicates how the parameter may occur (i.e. is optional or mandatory)
     */
    public ConstantValueParameter(String name, String value, ParameterOccurrence occurrence) {

        super(name, occurrence);

        this.value = checkValue(value);
    }

    /**
     * Checks the specified value.
     *
     * @param value
     *        a parameter value
     *
     * @return the specified value
     */
    private static String checkValue(String value) {

        if (value == null) {

            throw new IllegalArgumentException("No value (null) was specified!");
        }

        return value;
    }

    /**
     * Looks for this parameter within the specified parameters.
     *
     * @param parameterSequence
     *        a sequence of parameters
     *
     * @return the parameter value
     */
    @Override
    public String lookForParameter(Sequence<String> parameterSequence) {

        boolean found = false;
        for (String s : parameterSequence) {

            if (value.equals(s)) {

                found = true;
                break;
            }
        }

        if (!found) {

            throw new MissingParameterException(name());
        }

        return value;
    }

    /**
     * Returns the actual parameter value.
     *
     * @return the parameter value
     */
    public String value() {

        return value;
    }

    /**
     * Returns a string representation for this parameter description.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        String message =
            String.format("Parameter Description (name=\"%s\"; value=\"%s\"; mandatory=%s)", name(), value(),
                          isMandatory());
        return message;
    }

}
