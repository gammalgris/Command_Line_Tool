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

package jmul.terminal.utils.logging;


/**
 * This enumeration describes a simple classification of log messages.
 *
 * @author Kristian Kutin
 */
public enum LogLevels implements LogLevel {


    DEBUG,
    WARNING,
    INFO,
    ERROR;


    /**
     * Returns the max string length for all log levels.
     *
     * @return the max string length for all log levels
     */
    public static int getMaxLength() {

        int maxLength = 0;

        for (LogLevel logLevel : values()) {

            maxLength = Math.max(maxLength, logLevel.toString().length());
        }

        return maxLength;
    }

}