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

package jmul.terminal.component;


/**
 * A custom exception when a component cannot be initialized (i.e. an ecxeption
 * is thrown in the constructor).
 *
 * @author Kristian Kutin
 */
public class ComponentInitializationException extends RuntimeException {

    /**
     * The default constructor.
     */
    public ComponentInitializationException() {

        super();
    }

    /**
     * Creates a new exception according to the specified parameter.
     *
     * @param message
     *        an error message
     */
    public ComponentInitializationException(String message) {

        super(message);
    }

    /**
     * Creates a new exception according to the specified parameter.
     *
     * @param cause
     *        an error cause
     */
    public ComponentInitializationException(Throwable cause) {

        super(cause);
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param message
     *        an error message
     * @param cause
     *        an error cause
     */
    public ComponentInitializationException(String message, Throwable cause) {

        super(message, cause);
    }

}
