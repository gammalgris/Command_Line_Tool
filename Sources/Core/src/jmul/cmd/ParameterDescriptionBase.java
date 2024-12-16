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


/**
 * A common base implementation.
 *
 * @author Kristian Kutin
 */
abstract class ParameterDescriptionBase implements ParameterDescription {

    /**
     * The unique name for this parameter.
     */
    private final String name;

    /**
     * Indicates how the parameter may occur (i.e. is optional or mandatory).
     */
    private final ParameterOccurrence occurrence;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param name
     *        the unique name for this parameter
     * @param occurrence
     *        indicates how the parameter may occur (i.e. is optional or mandatory)
     */
    public ParameterDescriptionBase(String name, ParameterOccurrence occurrence) {

        super();

        this.name = checkName(name);
        this.occurrence = checkOccurrence(occurrence);
    }

    /**
     * Checks the specified name.
     *
     * @param name
     *        a name
     *
     * @return the specified name
     */
    private static String checkName(String name) {

        if (name == null) {

            throw new IllegalArgumentException("No name (null) was specified!");
        }

        return name;
    }

    /**
     * Checks the specified occurrence.
     *
     * @param occurrence
     *        the occurrence of a parameter
     *
     * @return the specified parameter
     */
    private static ParameterOccurrence checkOccurrence(ParameterOccurrence occurrence) {

        if (occurrence == null) {

            throw new IllegalArgumentException("No occurrence (null) was specified!");
        }

        return occurrence;
    }

    /**
     * Returns the name for this parameter.
     *
     * @return a name for this parameter
     */
    @Override
    public String name() {

        return name;
    }

    /**
     * Checks if this parameters is optional.
     *
     * @return <code>true</code> if this parameter is optional, else <code>false</code>
     */
    @Override
    public boolean isOptional() {

        return ParameterOccurrences.OPTIONAL == occurrence;
    }

    /**
     * Checks if this parameters is optional.
     *
     * @return <code>true</code> if this parameter is optional, else <code>false</code>
     */
    @Override
    public boolean isMandatory() {

        return ParameterOccurrences.MANDATORY == occurrence;
    }

}
