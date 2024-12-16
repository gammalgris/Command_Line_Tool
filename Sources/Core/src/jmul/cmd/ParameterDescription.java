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
 * This interface describes a parameter and specifies how to identify this parameter
 * within all provided parameters.
 *
 * @author Kristian Kutin
 */
public interface ParameterDescription {

    /**
     * Returns the name for this parameter.
     *
     * @return a name for this parameter
     */
    String name();

    /**
     * Looks for this parameter within the specified parameters.
     *
     * @param parameterSequence
     *        a sequence of parameters
     *
     * @return the parameter value
     */
    String lookForParameter(Sequence<String> parameterSequence);

    /**
     * Checks if this parameter is optional.
     *
     * @return <code>true</code> if this parameter is optional, else <code>false</code>
     */
    boolean isOptional();

    /**
     * Checks if this parameter is optional.
     *
     * @return <code>true</code> if this parameter is optional, else <code>false</code>
     */
    boolean isMandatory();

}
