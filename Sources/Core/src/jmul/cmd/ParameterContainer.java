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


import java.util.List;


/**
 * This interface is a container for parameters. For each parameter a parameter
 * description has to be provided which provides details about the parameter (e.g.
 * parameter is optional or mandatory, how the parameter is identified, how the
 * parameter value is identified, etc.).
 * The container is also provided with a list of parameters. Acording to the
 * parameter descriptions the parameters are identified. The container contains
 * the parameters and their correpsonding values.
 *
 * @author Kristian Kutin
 */
public interface ParameterContainer {

    /**
     * Returns the names of all found parameters (all mandatory and matching
     * optional parameters).
     *
     * @return a list of parameter names
     */
    List<String> names();

    /**
     * Checks if the parameter with the specified name exists.
     *
     * @param name
     *        the unique name for a parameter
     *
     * @return <code>true</code> if a parameter with the specified name exists,
     *         else <code>false</code>
     */
    boolean existsParameter(String name);

    /**
     * Returns the value for the specified parameter.
     *
     * @param name
     *        the unique name for a parameter
     *
     * @return the parameter value
     */
    String getParameterValue(String name);

}
