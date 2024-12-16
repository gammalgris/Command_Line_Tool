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


import java.util.List;

import jmul.commands.Command;
import jmul.commands.CommandCatalogue;
import jmul.commands.CommandCatalogueImpl;
import jmul.commands.CommandCategories;
import static jmul.commands.CommandCategories.EXIT_COMMAND;
import static jmul.commands.CommandCategories.PARAMETERLESS;
import static jmul.commands.CommandCategories.SINGLE_TOKEN;
import static jmul.commands.CommandCategories.VERSION_COMMAND;
import jmul.commands.InputNotParsableException;
import jmul.commands.ParameterlessSingleTokenCommand;
import jmul.commands.UnknownCommandException;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * This test suite tests a command catalogue.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class CommandCatalogueTest {

    /**
     * Creates a specific command.
     *
     * @return a command
     */
    private Command createExitCommand() {

        String token = "exit";
        String description = "Exists the current applications.";
        String[] categories = { EXIT_COMMAND, PARAMETERLESS, SINGLE_TOKEN };

        Command command = new ParameterlessSingleTokenCommand(token, description, categories);

        assertEquals(token, command.token());
        assertEquals(description, command.description());
        assertTrue(command.isCategory(EXIT_COMMAND));
        assertTrue(command.isCategory(PARAMETERLESS));
        assertTrue(command.isCategory(SINGLE_TOKEN));

        return command;
    }

    /**
     * Creates a specific command.
     *
     * @return a command
     */
    private Command createVersionCommand() {

        String token = "version";
        String description = "Shows the version of this application.";
        String[] categories = { VERSION_COMMAND, PARAMETERLESS, SINGLE_TOKEN };

        Command command = new ParameterlessSingleTokenCommand(token, description, categories);

        assertEquals(token, command.token());
        assertEquals(description, command.description());
        assertTrue(command.isCategory(VERSION_COMMAND));
        assertTrue(command.isCategory(PARAMETERLESS));
        assertTrue(command.isCategory(SINGLE_TOKEN));

        return command;
    }

    /**
     * Tests creating an empty command catalogue.
     */
    @Test
    public void testCreateEmptyCatalogue() {

        CommandCatalogue catalogue = new CommandCatalogueImpl();
        assertEquals(0, catalogue.size());
        assertTrue(catalogue.isEmpty());
    }

    /**
     * Tests creating a command catalogue with a single command.
     */
    @Test
    public void testCreateCatalogue() {

        Command command = createExitCommand();
        CommandCatalogue catalogue = new CommandCatalogueImpl(command);
        assertEquals(1, catalogue.size());
        assertFalse(catalogue.isEmpty());
    }

    /**
     * Tests creating a command catalogue with a <code>null</code> command.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateCatalogueWithNullCommand() {

        Command command = null;
        new CommandCatalogueImpl(command);
    }

    /**
     * Tests creating a command catalogue with no commands (i.e. <code>null</code> array).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateCatalogueWithNullCommandArray() {

        Command[] commands = null;
        new CommandCatalogueImpl(commands);
    }

    /**
     * Tests creating a command catalogue with no commands (i.e. empty array).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateCatalogueWithNoCommands() {

        Command[] commands = { };
        new CommandCatalogueImpl(commands);
    }

    /**
     * Tests creating a command catalogue with <code>null</code> commands.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateCatalogueWithNullCommands() {

        Command command = createExitCommand();
        Command[] commands = { command, null };
        new CommandCatalogueImpl(commands);
    }

    /**
     * Tests creating a command catalogue with duplicate commands.
     */
    @Test
    public void testCreateCatalogueWithDuplicateCommands() {

        Command command = createExitCommand();
        Command[] commands = { command, command };

        CommandCatalogue catalogue = new CommandCatalogueImpl(commands);
        assertEquals(1, catalogue.size());
    }

    /**
     * Creates a new command catalogue.
     *
     * @return a command catalogue
     */
    private CommandCatalogue createEmptyCatalogue() {

        CommandCatalogue catalogue = new CommandCatalogueImpl();
        assertEquals(0, catalogue.size());
        assertTrue(catalogue.isEmpty());

        return catalogue;
    }

    /**
     * Tests adding a command.
     */
    @Test
    public void testAddCommand() {

        Command command = createExitCommand();
        CommandCatalogue catalogue = createEmptyCatalogue();

        catalogue.addCommands(command);
        assertEquals(1, catalogue.size());
        assertFalse(catalogue.isEmpty());
    }

    /**
     * Tests adding duplicate command.
     */
    @Test
    public void testAddDuplicateCommands() {

        Command command = createExitCommand();
        CommandCatalogue catalogue = createCatalogueWithTwoCommands();

        catalogue.addCommands(command);
        assertEquals(2, catalogue.size());
    }

    /**
     * Tests adding a <code>null</code> command.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNullCommand() {

        Command command = null;
        CommandCatalogue catalogue = createEmptyCatalogue();

        catalogue.addCommands(command);
    }

    /**
     * Tests adding no commands (i.e. a <code>null</code> array).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNullCommandArray() {

        Command[] commands = null;
        CommandCatalogue catalogue = createEmptyCatalogue();

        catalogue.addCommands(commands);
    }

    /**
     * Tests adding no commands (i.e. empty array).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNoCommands() {

        Command[] commands = { };
        CommandCatalogue catalogue = createEmptyCatalogue();

        catalogue.addCommands(commands);
    }

    /**
     * Tests adding commands where one command is <code>null</code>.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNullCommands() {

        Command command = createExitCommand();
        Command[] commands = { command, null };
        CommandCatalogue catalogue = createEmptyCatalogue();

        catalogue.addCommands(commands);
    }

    /**
     * Tests removing a command.
     */
    @Test
    public void testRemoveCommand() {

        Command command = createExitCommand();
        CommandCatalogue catalogue = createEmptyCatalogue();

        catalogue.addCommands(command);
        assertEquals(1, catalogue.size());
        assertFalse(catalogue.isEmpty());

        catalogue.removeCommands(command);
        assertEquals(0, catalogue.size());
        assertTrue(catalogue.isEmpty());
    }

    /**
     * Tests removing a <code>null</code> command.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNullCommand() {

        Command command = null;
        CommandCatalogue catalogue = createEmptyCatalogue();

        catalogue.removeCommands(command);
    }

    /**
     * Tests removing no commands (i.e. a <code>null</code> array).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNullCommandArray() {

        Command[] commands = null;
        CommandCatalogue catalogue = createEmptyCatalogue();

        catalogue.removeCommands(commands);
    }

    /**
     * Tests removing no commands (i.e. empty array).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNoCommands() {

        Command[] commands = { };
        CommandCatalogue catalogue = createEmptyCatalogue();

        catalogue.removeCommands(commands);
    }

    /**
     * Tests removing commands where one command is <code>null</code>.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNullCommands() {

        Command command = createExitCommand();
        Command[] commands = { command, null };
        CommandCatalogue catalogue = createEmptyCatalogue();

        catalogue.removeCommands(commands);
    }

    /**
     * Creates a command catalogue with one command.
     *
     * @return a command catalogue
     */
    private CommandCatalogue createCatalogueWithOneCommand() {

        CommandCatalogue catalogue = createEmptyCatalogue();

        Command command = createExitCommand();
        catalogue.addCommands(command);
        assertEquals(1, catalogue.size());
        assertFalse(catalogue.isEmpty());

        return catalogue;
    }

    /**
     * Tests querying a command by a token.
     */
    @Test
    public void testGetCommandByKnownToken() {

        Command command = createExitCommand();
        String token = command.token();

        CommandCatalogue catalogue = createCatalogueWithOneCommand();

        Command foundCommand = catalogue.commandByToken(token);
        assertEquals(command.token(), foundCommand.token());
        assertEquals(command.description(), foundCommand.description());
    }

    /**
     * Tests querying a command by a <code>null</code> token.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetCommandByNullToken() {

        String token = null;

        CommandCatalogue catalogue = createCatalogueWithOneCommand();

        catalogue.commandByToken(token);
    }

    /**
     * Tests querying a command that is unknown to the catalogue.
     */
    @Test(expected = UnknownCommandException.class)
    public void testGetCommandByUnknownToken() {

        Command command = createVersionCommand();
        String token = command.token();

        CommandCatalogue catalogue = createCatalogueWithOneCommand();

        catalogue.commandByToken(token);
    }

    /**
     * Creates a command catalogue with one commands.
     *
     * @return a command catalogue
     */
    private CommandCatalogue createCatalogueWithTwoCommands() {

        CommandCatalogue catalogue = createEmptyCatalogue();

        Command command1 = createExitCommand();
        catalogue.addCommands(command1);
        assertEquals(1, catalogue.size());
        assertFalse(catalogue.isEmpty());

        Command command2 = createVersionCommand();
        catalogue.addCommands(command2);
        assertEquals(2, catalogue.size());
        assertFalse(catalogue.isEmpty());

        return catalogue;
    }

    /**
     * Tests querying commands by category and get one result.
     */
    @Test
    public void testGetCommandByKnownCategoryGetOneResult() {

        String category = CommandCategories.EXIT_COMMAND;
        CommandCatalogue catalogue = createCatalogueWithTwoCommands();
        List<Command> results = catalogue.commandsByCategory(category);
        assertEquals(1, results.size());
    }

    /**
     * Tests querying commands by category and get two results.
     */
    @Test
    public void testGetCommandByKnownCategoryGetTwoResults() {

        String category = CommandCategories.SINGLE_TOKEN;
        CommandCatalogue catalogue = createCatalogueWithTwoCommands();
        List<Command> results = catalogue.commandsByCategory(category);
        assertEquals(2, results.size());
    }

    /**
     * Tests querying commands by category and get no results.
     */
    @Test
    public void testGetCommandByCategoryGetNoResult() {

        String category = "unknown category";
        CommandCatalogue catalogue = createCatalogueWithTwoCommands();
        List<Command> results = catalogue.commandsByCategory(category);
        assertEquals(0, results.size());
    }

    /**
     * Tests querying commands by <code>null</code> category.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetCommandByNullCategory() {

        String category = null;
        CommandCatalogue catalogue = createCatalogueWithTwoCommands();
        catalogue.commandsByCategory(category);
    }

    /**
     * Checks matching the input and expecting a command as result.
     */
    @Test
    public void testMatch() {

        String input = "exit application";
        CommandCatalogue catalogue = createCatalogueWithTwoCommands();
        Command command = catalogue.match(input);
        assertNotNull(command);
    }

    /**
     * Checks matching the <code>null</code>input.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMatchWithNullCommandLine() {

        String input = null;
        CommandCatalogue catalogue = createCatalogueWithTwoCommands();
        catalogue.match(input);
    }

    /**
     * Checks matching the input and expecting no command as result.
     */
    @Test(expected = InputNotParsableException.class)
    public void testMatchWithUnknownCommand() {

        String input = "quit application";
        CommandCatalogue catalogue = createCatalogueWithTwoCommands();
        catalogue.match(input);
    }

}
