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

package jmul.commands;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * An implementation for a command catalogue.
 *
 * @author Kristian Kutin
 */
public class CommandCatalogueImpl implements CommandCatalogue {

    /**
     * A position index.
     */
    private static final int FIRST_ELEMENT_INDEX;

    /*
     * The static initializer.
     */
    static {

        FIRST_ELEMENT_INDEX = 0;
    }

    /**
     * The actual container for managing commands.
     */
    private Map<String, Command> tokenCommandMap;

    /**
     * The default constructor.
     */
    public CommandCatalogueImpl() {

        super();

        tokenCommandMap = new HashMap<>();
    }

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param commands
     *        one or more commands
     */
    public CommandCatalogueImpl(Command... commands) {

        this();

        checkCommands(commands);

        addCommands(commands);
    }

    /**
     * Returns the total count of commands within this container.
     *
     * @return a command count
     */
    @Override
    public int size() {

        return tokenCommandMap.size();
    }

    /**
     * Checks if this container for commands is empty.
     *
     * @return <code>true</code> if this container for commands is empty,
     *         else <code>false</code>
     */
    @Override
    public boolean isEmpty() {

        return tokenCommandMap.isEmpty();
    }

    /**
     * Adds the specified commands to this container for commands. The specified commands
     * won't override the commands that are already within this container.
     *
     * @param commands
     *        one or more commands
     */
    @Override
    public void addCommands(Command... commands) {

        checkCommands(commands);

        for (Command command : commands) {

            String token = command.token();
            if (!tokenCommandMap.containsKey(token)) {

                tokenCommandMap.put(token, command);
            }
        }
    }

    /**
     * Checks the specified parameter. If the parameter has an invalid value an exception
     * is thrown.
     *
     * @param commands
     *        one or more commands
     *
     * @return the specified parameter
     */
    private static Command[] checkCommands(Command[] commands) {

        if (commands == null) {

            throw new IllegalArgumentException("No commands (null) were specified!");
        }

        if (commands.length == 0) {

            throw new IllegalArgumentException("No commands (empty array) were specified!");
        }

        for (Command command : commands) {

            checkCommand(command);
        }

        return commands;
    }

    /**
     * Checks the specified parameter. If the parameter has an invalid value an exception
     * is thrown.
     *
     * @param command
     *        one command
     *
     * @return the specified parameter
     */
    private static Command checkCommand(Command command) {

        if (command == null) {

            throw new IllegalArgumentException("No command (null) was specified!");
        }

        return command;
    }

    /**
     * Removes the specified commands from this container for commands. If a specified command
     * doesn't exist within this container then the container will not be changed.
     *
     * @param commands
     *        one or more commands
     */
    @Override
    public void removeCommands(Command... commands) {

        checkCommands(commands);

        for (Command command : commands) {

            String token = command.token();
            if (tokenCommandMap.containsKey(token)) {

                tokenCommandMap.remove(token, command);
            }
        }
    }

    /**
     * Returns the command with the specified token.
     *
     * @param token
     *        the token representing a command
     *
     * @return a command
     */
    @Override
    public Command commandByToken(String token) {

        checkToken(token);

        Command command = tokenCommandMap.get(token);

        if (command == null) {

            throw new UnknownCommandException(token);
        }

        return command;
    }

    /**
     * Checks the specified token.
     *
     * @param token
     *        the token representing a command
     *
     * @return the specified token
     */
    private static String checkToken(String token) {

        if (token == null) {

            throw new IllegalArgumentException("No token (null) was specified!");
        }

        String normalizedToken = token.trim();
        ;
        if (normalizedToken.isEmpty()) {

            throw new IllegalArgumentException("No token (empty string) was specified!");
        }

        return token;
    }

    /**
     * Returns the commands which are associated with the specified category.
     *
     * @param category
     *        a category
     *
     * @return any number of commands that meet the specified criteria
     */
    @Override
    public List<Command> commandsByCategory(String category) {

        checkCategory(category);

        List<Command> matchingCommands = new ArrayList<>();

        for (Command command : this) {

            if (command.isCategory(category)) {

                matchingCommands.add(command);
            }
        }

        return matchingCommands;
    }

    /**
     * Checks the specified category.
     *
     * @param category
     *        a category
     *
     * @return the specified category
     */
    private static String checkCategory(String category) {

        if (category == null) {

            throw new IllegalArgumentException("No category (null) was spepcified!");
        }

        String normalizedCategory = category.trim();
        if (normalizedCategory.isEmpty()) {

            throw new IllegalArgumentException("No category (empty string) was spepcified!");
        }

        return category;
    }

    /**
     * Evaluates the specified input and identifies a matching command.
     *
     * @param input
     *        some input which is to be processed
     *
     * @return a command
     */
    @Override
    public Command match(String input) {

        checkInput(input);

        List<Command> matchingCommands = new ArrayList<>();

        for (Command command : this) {

            if (command.matches(input)) {

                matchingCommands.add(command);
            }
        }

        if (matchingCommands.size() == 0) {

            throw new InputNotParsableException();

        } else if (matchingCommands.size() > 1) {

            throw new AmbigousInputException(matchingCommands);
        }

        return matchingCommands.get(FIRST_ELEMENT_INDEX);
    }

    /**
     * Checks the specified input.
     *
     * @param input
     *        some input which is to be processed
     *
     * @return the specified input
     */
    private static String checkInput(String input) {

        if (input == null) {

            throw new IllegalArgumentException("No input (null) was specified!");
        }

        String normalizedInput = input.trim();
        if (normalizedInput.isEmpty()) {

            throw new IllegalArgumentException("No input (empty string) was specified!");
        }

        return input;
    }

    /**
     * Returns an iterator to iterate though all commands.
     *
     * @return an iterator
     */
    @Override
    public Iterator<Command> iterator() {

        return tokenCommandMap.values().iterator();
    }

}
