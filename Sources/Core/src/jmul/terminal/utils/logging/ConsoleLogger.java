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
 * This class implements a simple console logger.
 *
 * @author Kristian Kutin
 */
public class ConsoleLogger implements Logger {

    /**
     * A custom formatter for log messages.
     */
    private Formatter formatter;

    /**
     * The log level which determines which log entries are displayed.
     */
    private LogLevel logLevel;

    /**
     * The default constructor.
     *
     * @param logLevel
     *        the log level which determines which log entries are displayed
     */
    public ConsoleLogger(LogLevel logLevel) {

        super();

        this.formatter = new LogFormatter();
        this.logLevel = check(logLevel);
    }

    /**
     * Checks the specified parameter. Throws an exception the parameter has an invalid value.
     *
     * @param logLevel
     *        a log level
     *
     * @return the specified parameter
     */
    private static LogLevel check(LogLevel logLevel) {

        if (logLevel == null) {

            throw new IllegalArgumentException("No log level (null) was specified!");
        }

        return logLevel;
    }

    /**
     * Logs the specified message.
     *
     * @param logLevel
     *        a log level (i.e. the category of the message), depending on the configuration
     *        of the logger some messages will be filtered based on the log level
     * @param componentName
     *        the name of the component which sent the log message
     * @param message
     *        a log message
     */
    @Override
    public void log(LogLevel logLevel, String componentName, String message) {

        if (!showLogEntry(logLevel)) {

            return;
        }

        String formattedMessage = formatter.format(logLevel, componentName, message);
        System.out.println(formattedMessage);
    }

    /**
     * Checks the specified log level with the configured log level and decides if a log entry should
     * be showed.
     *
     * @param logLevel
     *        the log level of a log entry
     *
     * @return <code>true</code> if a log entry should be shown,else <code>false</code>
     */
    private boolean showLogEntry(LogLevel logLevel) {

        return (this.logLevel.ordinal() <= logLevel.ordinal());
    }

    /**
     * Changes the log level of this logger. The log level determines which log entries
     * are shown.
     *
     * @param logLevel
     *        a log level
     *
     * @return the previous log level
     */
    public LogLevel changeLogLevel(LogLevel logLevel) {

        LogLevel previousLogLevel = logLevel;
        this.logLevel = logLevel;

        return previousLogLevel;
    }

    /**
     * Returns the current log level.
     *
     * @return a log level
     */
    public LogLevel logLevel() {

        return logLevel;
    }

}
