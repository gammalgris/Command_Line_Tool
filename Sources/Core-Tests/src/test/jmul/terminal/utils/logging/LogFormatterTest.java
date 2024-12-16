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


import jmul.terminal.utils.logging.Formatter;
import jmul.terminal.utils.logging.LogFormatter;
import jmul.terminal.utils.logging.LogLevel;
import jmul.terminal.utils.logging.LogLevels;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * This test suite tests a log formatter.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class LogFormatterTest {

    /**
     * Tests creating a formatter.
     */
    @Test
    public void testCreatingFormatter() {

        new LogFormatter();
    }

    /**
     * Tests using a formater.
     */
    @Test
    public void testFormatting() {

        Formatter formatter = new LogFormatter();

        LogLevel logLevel = LogLevels.INFO;
        String componentName = "core";
        String message = "Hello World!";

        String result = formatter.format(logLevel, componentName, message);
        assertTrue(result.contains(logLevel.toString()));
        assertTrue(result.contains(componentName));
        assertTrue(result.contains(message));
    }

    /**
     * Tests using a formater.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFormattinWithNullLogLevelg() {

        Formatter formatter = new LogFormatter();

        LogLevel logLevel = null;
        String componentName = "core";
        String message = "Hello World!";

        formatter.format(logLevel, componentName, message);
    }

    /**
     * Tests using a formater.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFormattingWithNullComponentName() {

        Formatter formatter = new LogFormatter();

        LogLevel logLevel = LogLevels.INFO;
        String componentName = null;
        String message = "Hello World!";

        formatter.format(logLevel, componentName, message);
    }

    /**
     * Tests using a formater.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFormattingWithEmptyComponentName() {

        Formatter formatter = new LogFormatter();

        LogLevel logLevel = LogLevels.INFO;
        String componentName = " ";
        String message = "Hello World!";

        formatter.format(logLevel, componentName, message);
    }

    /**
     * Tests using a formater.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFormattingWithNullMessage() {

        Formatter formatter = new LogFormatter();

        LogLevel logLevel = LogLevels.INFO;
        String componentName = "core";
        String message = null;

        formatter.format(logLevel, componentName, message);
    }

}
