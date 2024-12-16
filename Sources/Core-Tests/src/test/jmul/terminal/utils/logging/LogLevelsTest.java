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

package test.jmul.terminal.utils.logging;


import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import jmul.terminal.utils.logging.LogLevel;
import jmul.terminal.utils.logging.LogLevels;


/**
 * This test suite tests the defined log levels and their properties.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class LogLevelsTest {

    /**
     * Tests the max length function which is used to align the log level names.
     */
    @Test
    public void testMaxLength() {

        int maxLength = LogLevels.getMaxLength();

        for (LogLevel logLevel : LogLevels.values()) {

            String s = logLevel.toString();

            String message = String.format("The log level %s is longer than the maximum length %d!", s, maxLength);
            boolean b = s.length() <= maxLength;
            assertTrue(message, b);
        }
    }

    /**
     * Tests the ordinal value for each log level.
     */
    @Test
    public void testOrdinals() {

        assertEquals(0, LogLevels.DEBUG.ordinal());
        assertEquals(1, LogLevels.WARNING.ordinal());
        assertEquals(2, LogLevels.INFO.ordinal());
        assertEquals(3, LogLevels.ERROR.ordinal());
    }

    /**
     * Checks the equality of the debug level with all other log levels.
     */
    @Test
    public void testEqualityDebugLevel() {

        LogLevel debugLevel = LogLevels.DEBUG;
        LogLevel[] allLevels = LogLevels.values();

        for (LogLevel logLevel : allLevels) {

            if (debugLevel == logLevel) {

                continue;

            } else {

                assertNotEquals(debugLevel, logLevel);
            }
        }
    }

    /**
     * Checks the equality of the warning level with all other log levels.
     */
    @Test
    public void testEqualityWarningLevel() {

        LogLevel warningLevel = LogLevels.WARNING;
        LogLevel[] allLevels = LogLevels.values();

        for (LogLevel logLevel : allLevels) {

            if (warningLevel == logLevel) {

                continue;

            } else {

                assertNotEquals(warningLevel, logLevel);
            }
        }
    }

    /**
     * Checks the equality of the info level with all other log levels.
     */
    @Test
    public void testEqualityInfoLevel() {

        LogLevel infoLevel = LogLevels.INFO;
        LogLevel[] allLevels = LogLevels.values();

        for (LogLevel logLevel : allLevels) {

            if (infoLevel == logLevel) {

                continue;

            } else {

                assertNotEquals(infoLevel, logLevel);
            }
        }
    }

    /**
     * Checks the equality of the error level with all other log levels.
     */
    @Test
    public void testEqualityErrorLevel() {

        LogLevel errorLevel = LogLevels.ERROR;
        LogLevel[] allLevels = LogLevels.values();

        for (LogLevel logLevel : allLevels) {

            if (errorLevel == logLevel) {

                continue;

            } else {

                assertNotEquals(errorLevel, logLevel);
            }
        }
    }

}
