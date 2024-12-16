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


import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

import jmul.text.HorizontalTextAlignments;
import jmul.text.TextAlignmentHelper;


/**
 * An implementation of a formatter.
 *
 * @author Kristian Kutin
 */
public class LogFormatter implements Formatter {

    /**
     * A date pattern.
     */
    private static final String DEFAULT_DATE_PATTERN;

    /**
     * A string pattern.
     */
    private static final String DEFAULT_PATTERN;

    /*
     * The static initializer.
     */
    static {

        DEFAULT_DATE_PATTERN = "yyyyMMdd-HH:mm:ss";
        DEFAULT_PATTERN = "%s::%s::%s::%s";
    }

    /**
     * A date format.
     */
    private DateFormat dateFormatter;

    /**
     * The default constructor.
     */
    public LogFormatter() {

        super();

        this.dateFormatter = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
    }

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
    @Override
    public String format(LogLevel logLevel, String componentName, String message) {

        checkLogLevel(logLevel);
        checkComponentName(componentName);
        checkMessage(message);

        String timestamp = createTimestamp();
        String normalizedLogLevel =
            TextAlignmentHelper.align(LogLevels.getMaxLength(), HorizontalTextAlignments.CENTER, logLevel.toString());

        return String.format(DEFAULT_PATTERN, timestamp, normalizedLogLevel, componentName, message);
    }

    /**
     * Creates a timestamp.
     *
     * @return a timestamp
     */
    private String createTimestamp() {

        Date date = new Date();
        String timestamp = dateFormatter.format(date);

        return timestamp;
    }

    /**
     * Checks the specified argument
     *
     * @param logLevel
     *        a log level
     *
     * @return the specified argument
     */
    private static LogLevel checkLogLevel(LogLevel logLevel) {

        if (logLevel == null) {

            throw new IllegalArgumentException("No log level (null) was specified!");
        }

        return logLevel;
    }

    /**
     * Checks the specified argument
     *
     * @param componentName
     *        a component name
     *
     * @return the specified argument
     */
    private static String checkComponentName(String componentName) {

        if (componentName == null) {

            throw new IllegalArgumentException("No component name (null string) was specified!");
        }

        if (componentName.trim().isEmpty()) {

            throw new IllegalArgumentException("No component name (empty string) was specified!");
        }

        return componentName;
    }

    /**
     * Checks the specified argument
     *
     * @param message
     *        a message
     *
     * @return the specified argument
     */
    private static String checkMessage(String message) {

        if (message == null) {

            throw new IllegalArgumentException("No message (null string) was specified!");
        }

        return message;
    }

}
