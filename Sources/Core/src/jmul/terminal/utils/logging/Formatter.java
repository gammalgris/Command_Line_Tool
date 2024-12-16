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

package jmul.terminal.utils.logging;


/**
 * This interface describes a formatter entity (i.e. an entity which creates a formated string
 * with the specified arguments).
 *
 * @author Kristian Kutin
 */
public interface Formatter {

    /**
     * Formats the specified arguments.
     *
     * @param logLevel
     *        the log level of the message
     * @param componentName
     *        the name of the component which sent the log message
     * @param message
     *        a log message
     *
     * @return a string containing all specified arguments
     */
    String format(LogLevel logLevel, String componentName, String message);

}
