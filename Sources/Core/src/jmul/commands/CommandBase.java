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


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * A common base implementation for commands.
 *
 * @author Kristian Kutin
 */
abstract class CommandBase implements Command {

    /**
     * The token representing this command.
     */
    private final String token;

    /**
     * The description for this command.
     */
    private final String description;

    /**
     * The categories which are associated with this command.
     */
    private Set<String> categories;

    /**
     * Creates a new command according to the specified parameters.
     *
     * @param token
     *        the token representing this command
     * @param description
     *        the description for this command
     * @param categories
     */
    public CommandBase(String token, String description, String... categories) {

        super();

        this.token = checkToken(token);
        this.description = checkDescription(description);

        this.categories = new HashSet<>(arrayToList(categories));
    }

    /**
     * Checks the specified parameter.
     *
     * @param token
     *        a token
     *
     * @return the specified parameter
     */
    private static String checkToken(String token) {

        if (token == null) {

            throw new IllegalArgumentException("No token was (null) specified!");
        }

        String normalizedToken = token.trim();
        if (normalizedToken.isEmpty()) {

            throw new IllegalArgumentException("No token was (empty string) specified!");
        }

        return token;
    }

    /**
     * Checks the specified description.
     *
     * @param description
     *        a description for a command
     *
     * @return the specified description
     */
    private static String checkDescription(String description) {

        if (description == null) {

            throw new IllegalArgumentException("No description was (null) specified!");
        }

        String normalizedDescription = description.trim();
        if (normalizedDescription.isEmpty()) {

            throw new IllegalArgumentException("No description was (empty string) specified!");
        }

        return description;
    }

    /**
     * Transforms the specified array of categories into a list of categories.
     *
     * @param categories
     *        the categories associated with a command
     *
     * @return a list of categories
     */
    private static List<String> arrayToList(String[] categories) {

        checkCategories(categories);

        return Arrays.asList(categories);
    }

    /**
     * Checks the specified categories.
     *
     * @param categories
     *        the categories associated with a command
     *
     * @return the specified categories
     */
    private static String[] checkCategories(String[] categories) {

        if (categories == null) {

            throw new IllegalArgumentException("No categories (null) were specified!");
        }

        if (categories.length == 0) {

            throw new IllegalArgumentException("No categories (empty array) were specified!");
        }

        for (String category : categories) {

            checkCategory(category);
        }

        return categories;
    }

    /**
     * Checks the specified category.
     *
     * @param category
     *        the category associated with a command
     *
     * @return the specified category
     */
    private static String checkCategory(String category) {

        if (category == null) {

            throw new IllegalArgumentException("No category (null) was specified!");
        }

        return category;
    }

    /**
     * The command token.
     *
     * @return the token (i.e. a character or string) representing this command
     */
    @Override
    public String token() {

        return token;
    }

    /**
     * A description for this command.
     *
     * @return a description
     */
    @Override
    public String description() {

        return description;
    }

    /**
     * Checks if this command belongs to the specified category or classification.
     *
     * @param category
     *        a category or classification for this command
     *
     * @return
     */
    @Override
    public boolean isCategory(String category) {

        return categories.contains(category);
    }

    /**
     * Associated this command with the specified categories.
     *
     * @param categories
     *        one or more categories
     */
    @Override
    public void addCategory(String... categories) {

        this.categories.addAll(arrayToList(categories));
    }

    /**
     * Removes the association with the specified categories for this command.
     *
     * @param categories
     *        one or more categories
     */
    @Override
    public void removeCategory(String... categories) {

        this.categories.removeAll(arrayToList(categories));
    }

    /**
     * Returns a string representation for this command.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.format("%s : %s - %s", token(), categories.toString(), description());
    }

}
