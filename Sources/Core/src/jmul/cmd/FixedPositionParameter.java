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
 * An implementation for a fixed position parameter.
 *
 * @author Kristian Kutin
 */
public class FixedPositionParameter extends ParameterDescriptionBase {

    /**
     * An index position.
     */
    private final int index;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param name
     *         unique name for this parameter
     * @param index
     *        an index position
     * @param occurrence
     *        indicates how the parameter may occur (i.e. is optional or mandatory)
     */
    public FixedPositionParameter(String name, int index, ParameterOccurrence occurrence) {

        super(name, occurrence);

        this.index = checkIndex(index);
    }

    /**
     * Checks the specified index.
     *
     * @param index
     *        an index
     *
     * @return the specified index
     */
    private static int checkIndex(int index) {

        if (index < 0) {

            throw new IllegalArgumentException("A negative index was specified!");
        }

        return index;
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

        if (parameterSequence.size() <= index) {

            throw new MissingParameterException(name());
        }

        return parameterSequence.elementAt(index);
    }

    /**
     * Returns the index position of this parameter.
     *
     * @return an index (i.e. a positive integer including zero)
     */
    public int index() {

        return index;
    }

    /**
     * Returns a string representation for this parameter description.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        String message =
            String.format("Parameter Description (name=\"%s\"; index=\"%s\"; mandatory=%s)", name(), index(),
                          isMandatory());
        return message;
    }

}
