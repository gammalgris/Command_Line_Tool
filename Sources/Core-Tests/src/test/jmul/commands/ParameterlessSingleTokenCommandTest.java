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

package test.jmul.commands;


import java.util.Map;

import jmul.commands.Command;
import jmul.commands.ParameterlessSingleTokenCommand;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * This test suite tests the single token command.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class ParameterlessSingleTokenCommandTest {

    /**
     * Tests creating the command with valid parameters.
     */
    @Test
    public void testCreateCommand() {

        String token = "exit";
        String description = "Exists the current applications.";
        String category = "exit command";

        Command command = new ParameterlessSingleTokenCommand(token, description, category);

        assertEquals(token, command.token());
        assertEquals(description, command.description());
        assertTrue(command.isCategory(category));
    }

    /**
     * Tests creating the command with a <code>null</code> token.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateCommandWithNullToken() {

        String token = null;
        String description = "Exists the current applications.";
        String category = "exit command";

        new ParameterlessSingleTokenCommand(token, description, category);
    }

    /**
     * Tests creating the command with a <code>null</code> description.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateCommandWithNullDescription() {

        String token = "exit";
        String description = null;
        String category = "exit command";

        new ParameterlessSingleTokenCommand(token, description, category);
    }

    /**
     * Tests creating the command with a <code>null</code> category.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateCommandWithNullCategory() {

        String token = "exit";
        String description = "Exists the current applications.";
        String category = null;

        new ParameterlessSingleTokenCommand(token, description, category);
    }

    /**
     * Tests creating the command with <code>null</code> categories.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateCommandWithNullCategories() {

        String token = "exit";
        String description = "Exists the current applications.";
        String[] categories = null;

        new ParameterlessSingleTokenCommand(token, description, categories);
    }

    /**
     * Tests creating the command with no categories (i.e. empty array).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateCommandWithEmptyCategories() {

        String token = "exit";
        String description = "Exists the current applications.";
        String[] categories = { };

        new ParameterlessSingleTokenCommand(token, description, categories);
    }

    /**
     * Tests creating the command with two categories where one category is <code>null</code>.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateCommandWithInvalidCategories() {

        String token = "exit";
        String description = "Exists the current applications.";
        String[] categories = { "a", null };

        new ParameterlessSingleTokenCommand(token, description, categories);
    }

    /**
     * Creates a command.
     *
     * @return a command
     */
    private Command createCommand() {

        String token = "exit";
        String description = "Exists the current applications.";
        String category = "exit command";

        Command command = new ParameterlessSingleTokenCommand(token, description, category);

        assertEquals(token, command.token());
        assertEquals(description, command.description());
        assertTrue(command.isCategory(category));

        return command;
    }

    /**
     * Tests adding a category to a command.
     */
    @Test
    public void testAddCategory() {

        Command command = createCommand();

        String newCategory = "single token command";
        command.addCategory(newCategory);

        assertTrue(command.isCategory(newCategory));
    }

    /**
     * Tests adding a <code>null</code> category to a command.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNullCategory() {

        Command command = createCommand();

        String newCategory = null;
        command.addCategory(newCategory);
    }

    /**
     * Tests adding no categories (i.e. empty array) to a command.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNoCategories() {

        Command command = createCommand();

        String[] newCategories = { };
        command.addCategory(newCategories);
    }

    /**
     * Tests adding <code>null</code> categories to a command.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNullCategories() {

        Command command = createCommand();

        String[] newCategories = { "a", null };
        command.addCategory(newCategories);
    }

    /**
     * Tests removing a category;
     */
    @Test
    public void testRemoveCategory() {

        Command command = createCommand();

        String newCategory = "a";
        command.addCategory(newCategory);
        assertTrue(command.isCategory(newCategory));

        command.removeCategory(newCategory);
        assertFalse(command.isCategory(newCategory));
    }

    /**
     * Tests removing a <code>null</code> category;
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNullCategory() {

        Command command = createCommand();

        String newCategory = null;
        command.removeCategory(newCategory);
    }

    /**
     * Tests removing a an unknown category;
     */
    @Test
    public void testRemoveUnknownCategory() {

        Command command = createCommand();

        String newCategory = "a";
        command.removeCategory(newCategory);
        assertFalse(command.isCategory(newCategory));
    }

    /**
     * Tests removing empty categories;
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveEmptyCategories() {

        Command command = createCommand();

        String[] newCategories = { };
        command.removeCategory(newCategories);
    }

    /**
     * Tests removing <code>null</code> categories;
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNullCategories() {

        Command command = createCommand();

        String[] newCategories = { "a", null };
        command.removeCategory(newCategories);
    }

    /**
     * Test matching the command with a command line input matching the command.
     */
    @Test
    public void testMatch() {

        Command command = createCommand();

        String commandLine = command.token();
        boolean result = command.matches(commandLine);
        assertTrue(result);
    }

    /**
     * Test matching the command with a command line input containing the command.
     */
    @Test
    public void testMatchWithParameters() {

        Command command = createCommand();

        String commandLine = command.token() + " now";
        boolean result = command.matches(commandLine);
        assertTrue(result);
    }

    /**
     * Test matching the command with a command line input containing the command.
     */
    @Test
    public void testMatchWithEmbeddedCommand() {

        Command command = createCommand();

        String commandLine = "  " + command.token() + " now";
        boolean result = command.matches(commandLine);
        assertTrue(result);
    }

    /**
     * Test matching the command with a command line input that doesn't match the command.
     */
    @Test
    public void testMatchWrongCommand() {

        Command command = createCommand();

        String commandLine = "just " + command.token() + " now";
        boolean result = command.matches(commandLine);
        assertFalse(result);
    }

    /**
     * Tests matching the command with a <code>null</code> command line input.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMatchNull() {

        Command command = createCommand();

        String commandLine = null;
        command.matches(commandLine);
    }

    /**
     * Tests parsing a command line input.
     */
    @Test
    public void testParse() {

        Command command = createCommand();

        String commandLine = command.token();
        Map<String, String> result = command.parse(commandLine);
        assertEquals(0, result.size());
    }

    /**
     * Tests parsing a command line input.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testParseWrongCommand() {

        Command command = createCommand();

        String commandLine = "hello world!";
        command.parse(commandLine);
    }

    /**
     * Tests parsing a <code>null</code> command line input.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testParseNull() {

        Command command = createCommand();

        String commandLine = null;
        command.parse(commandLine);
    }

    /**
     * Tests parsing a command line input.
     */
    @Test
    public void testParseMultipleInputs() {

        Command command = createCommand();

        String commandLine1 = command.token() + " hello";
        Map<String, String> result1 = command.parse(commandLine1);
        assertEquals(0, result1.size());

        String commandLine2 = command.token() + " world";
        Map<String, String> result2 = command.parse(commandLine2);
        assertEquals(0, result2.size());
    }

}
