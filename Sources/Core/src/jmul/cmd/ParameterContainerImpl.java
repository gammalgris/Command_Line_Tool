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


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jmul.language.types.Sequence;
import jmul.language.types.StringSequence;


/**
 * An implementation of a parameter container.
 *
 * @author Kristian Kutin
 */
public class ParameterContainerImpl implements ParameterContainer {

    /**
     * A sequence containing all parameters.
     */
    private Sequence<String> allParameters;

    /**
     * A map containing all identified parameters.
     */
    private Map<String, String> parameterMap;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param args
     *        an array of parameters
     * @param descriptions
     *        descriptions for all relevant parameters
     */
    public ParameterContainerImpl(String[] args, ParameterDescription... descriptions) {

        super();

        checkDescriptionsAndNames(descriptions);

        parameterMap = new HashMap<>();
        allParameters = new StringSequence(args);

        List<String> missingParameters = new ArrayList<>();

        for (ParameterDescription description : descriptions) {

            String name = description.name();

            try {

                String value = description.lookForParameter(allParameters);
                parameterMap.put(name, value);

            } catch (MissingParameterException e) {

                if (description.isMandatory()) {

                    missingParameters.add(name);
                }
            }
        }

        if (!missingParameters.isEmpty()) {

            throw new MissingMandatoryParametersException(missingParameters);
        }
    }

    /**
     * Checks the specified  parameter descriptions and names.
     *
     * @param descriptions
     *        any number of parameter description
     *
     * @return  the specified parameter
     */
    private static ParameterDescription[] checkDescriptionsAndNames(ParameterDescription[] descriptions) {

        if (descriptions == null) {

            throw new IllegalArgumentException("No parameter descriptions (null) were specified!");
        }

        Set<String> uniqueNames = new HashSet<>();
        Set<String> duplicateNames = new HashSet<>();

        for (ParameterDescription description : descriptions) {

            String name = description.name();

            if (uniqueNames.contains(name)) {

                duplicateNames.add(name);

            } else {

                uniqueNames.add(name);
            }
        }

        if (!duplicateNames.isEmpty()) {

            throw new DuplicateParameterNamesException(duplicateNames);
        }

        return descriptions;
    }

    /**
     * Returns the names of all found parameters (all mandatory and matching
     * optional parameters).
     *
     * @return a list of parameter names
     */
    @Override
    public List<String> names() {

        return new ArrayList<>(parameterMap.keySet());
    }

    /**
     * Checks if a parameter exists.
     *
     * @param name
     *        the unique name for a parameter
     *
     * @return <code>true</code> if a parameter with the specified name eixsts,
     *         else <code>false</code>
     */
    @Override
    public boolean existsParameter(String name) {

        return parameterMap.containsKey(name);
    }

    /**
     * Returns the value for the specified parameter.
     *
     * @param name
     *        the unique name for a parameter
     *
     * @return the parameter value
     */
    @Override
    public String getParameterValue(String name) {

        String value = parameterMap.get(name);

        if (value == null) {

            throw new MissingParameterException(name);
        }

        return value;
    }

    /**
     * Returns a string representation for this parameter container.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();
        buffer.append(allParameters);
        buffer.append(" ");
        buffer.append(parameterMap);

        return buffer.toString();
    }

}
