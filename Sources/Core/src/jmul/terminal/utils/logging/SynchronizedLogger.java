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
 * This class is a synchronized wrapper for loggers.
 *
 * @author Kristian Kutin
 */
public class SynchronizedLogger implements Logger {

    /**
     * The wrapped logger.
     */
    private final Logger logger;

    /**
     * Create a new instance according to the specified parameters.
     *
     * @param logger
     *        a logger
     */
    public SynchronizedLogger(Logger logger) {

        super();

        this.logger = checkLogger(logger);
    }

    /**
     * Checks the specified logger.
     *
     * @param logger
     *        a logger
     *
     * @return the specified logger
     */
    private static Logger checkLogger(Logger logger) {

        if (logger == null) {

            throw new IllegalArgumentException("No logger (null) was specified!");
        }

        return logger;
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

        synchronized (logger) {

            logger.log(logLevel, componentName, message);
        }
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
    @Override
    public LogLevel changeLogLevel(LogLevel logLevel) {

        synchronized (logger) {

            return logger.changeLogLevel(logLevel);
        }
    }

    /**
     * Returns the current log level.
     *
     * @return a log level
     */
    @Override
    public LogLevel logLevel() {

        synchronized (logger) {

            return logger.logLevel();
        }
    }

}
