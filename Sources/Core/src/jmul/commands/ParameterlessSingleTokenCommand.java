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


import java.util.Collections;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A command which consists only of a single token and doesn't require any parameters.
 *
 * @author Kristian Kutin
 */
public class ParameterlessSingleTokenCommand extends CommandBase {

    /**
     * The pattern to identify this command.
     */
    private final Pattern pattern;

    /**
     * The actual entity that matches the inpout and the pattern.<br>
     * <br>
     * The matcher may be <code>null</code> if no matching happened yet.
     * The matcher may become outdated.
     */
    private Matcher matcher;

    /**
     * The last input is cached in order to identify if the matcher is outdated.<br>
     * <br>
     * The last input is initially <code>null</code>.
     */
    private String previousInput;

    /**
     * Creates a new command according to the specified parameters.
     *
     * @param token
     *        the token representing this command
     * @param description
     *        a description for this command
     * @param categories
     *        all categories which are assoiated with this command
     */
    public ParameterlessSingleTokenCommand(String token, String description, String... categories) {

        super(token, description, categories);

        String regex = String.format("^%s.*$", token);
        pattern = Pattern.compile(regex);
        matcher = null;
    }

    /**
     * Checks if the specified input macthes this command.
     *
     * @param input
     *        some input which is to be processed
     *
     * @return <code>true</code> if the specified input matches this command,
     *         else <code>false</code>
     */
    @Override
    public boolean matches(String input) {

        updateMatcher(input);

        return matcher.matches();
    }

    /**
     * Updates the matcher according to the specified input.
     *
     * @param input
     *        some input which is to be processed
     */
    private void updateMatcher(String input) {

        checkInput(input);
        String normalizedInput = input.trim();

        if (previousInput != null) {

            if (previousInput.equals(normalizedInput)) {

                if (matcher != null) {

                    return;
                }
            }
        }

        matcher = pattern.matcher(normalizedInput);
        previousInput = normalizedInput;
    }

    /**
     * Checks the specified input.
     *
     * @param input
     *        input which needs to be processed
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
     * Parses the specified input and extracts information which are need to
     * execute this command.
     *
     * @param input
     *        some input
     *
     * @return a map containing informations extracted from the specified input
     */
    @Override
    public Map<String, String> parse(String input) {

        updateMatcher(input);

        if (!matcher.matches()) {

            throw new IllegalArgumentException("The specified command cannot be parsed!");
        }

        return Collections.emptyMap();
    }

}
