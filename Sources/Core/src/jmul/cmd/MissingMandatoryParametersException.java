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
 * A custom exception for missing mandatory parameters.
 *
 * @author Kristian Kutin
 */
public class MissingMandatoryParametersException extends RuntimeException {

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param parameterNames
     *        the names of all missing mandatory parameters
     */
    public MissingMandatoryParametersException(List<String> parameterNames) {

        super(createMessage(parameterNames));
    }

    /**
     * Creates an error message according to the specified parameter.
     *
     * @param parameterNames
     *        the names of all missing mandatory parameters
     *
     * @return an error message
     */
    private static String createMessage(List<String> parameterNames) {

        checkParameterNames(parameterNames);

        String message = String.format("Mandatory parameters (%s) are missing!", parameterNames.toString());

        return message;
    }

    /**
     * Checks the specified parameter names.
     *
     * @param parameterNames
     *        the names of all missing mandatory parameters
     *
     * @return the specified parameter names
     */
    private static List<String> checkParameterNames(List<String> parameterNames) {

        if (parameterNames == null) {

            throw new IllegalArgumentException("No parameter names (null) were provided!");
        }

        if (parameterNames.isEmpty()) {

            throw new IllegalArgumentException("No parameter names (empty list) were provided!");
        }

        return parameterNames;
    }

}
