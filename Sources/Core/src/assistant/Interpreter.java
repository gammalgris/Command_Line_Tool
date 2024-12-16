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

package assistant;


import java.io.Console;

import java.util.ArrayList;
import java.util.List;

import jmul.cmd.FixedPositionParameter;
import jmul.cmd.ParameterContainer;
import jmul.cmd.ParameterContainerImpl;

import jmul.commands.AmbigousInputException;
import jmul.commands.Command;
import jmul.commands.CommandCatalogue;
import jmul.commands.CommandCatalogueImpl;
import jmul.commands.InputNotParsableException;
import jmul.commands.ParameterlessSingleTokenCommand;

import jmul.language.catalogues.StringTokenCatalogue;
import jmul.language.catalogues.TokenCatalogue;
import jmul.language.tokenizers.SentenceTokenizer;
import jmul.language.tokenizers.StringTokenizer;
import jmul.language.tokenizers.Tokenizer;
import jmul.language.types.Sequence;

import jmul.query.QueryResult;


/**
 * This class implements an interpreter which can be modified and customized through
 * its own console.
 *
 * @author Kristian Kutin
 */
@Deprecated
public class Interpreter {
/*
    private static CommandCatalogue COMMAND_CATALOGUE;

    private static final String EXIT_COMMAND_CATEGORY;

    private static TokenCatalogue CATALOGUE;

    private static final String PUNCTUATION_MARK;

    private static final String EXIT_COMMAND;

    private static final String INPUT_PROPMPT;

    private static final String OUTPUT_PROPMPT;

    /*
     * The static initializer.
     * /
    static {

        PUNCTUATION_MARK = "punctuation mark";
        EXIT_COMMAND = "exit command";
        INPUT_PROPMPT = "input prompt";
        OUTPUT_PROPMPT = "output prompt";

        EXIT_COMMAND_CATEGORY = "exit command";

        COMMAND_CATALOGUE =
            new CommandCatalogueImpl(new ParameterlessSingleTokenCommand("quit", "Stops the interpreter.",
                                                                         EXIT_COMMAND_CATEGORY),
                                     new ParameterlessSingleTokenCommand("exit", "Stops the interpreter.",
                                                                         EXIT_COMMAND_CATEGORY),
                                     new ParameterlessSingleTokenCommand("stop", "Stops the interpreter.",
                                                                         EXIT_COMMAND_CATEGORY),
                                     new ParameterlessSingleTokenCommand("help", "Stops the interpreter.",
                                                                         "info command"),
                                     new ParameterlessSingleTokenCommand("catalogue", "Stops the interpreter.",
                                                                         "info command"));
    }

    / **
     * Creates a new catalogue with an initial token set.
     *
     * @return a catalogue
     * /
    public static TokenCatalogue newCatalogue() {

        TokenCatalogue catalogue = new StringTokenCatalogue();

        catalogue.addToken(INPUT_PROPMPT, "<", "console output");
        catalogue.addToken(OUTPUT_PROPMPT, ">", "console output");

        catalogue.addToken("point", ".", PUNCTUATION_MARK);
        catalogue.addToken("comma", ",", PUNCTUATION_MARK);
        catalogue.addToken("exclamation mark", "!", PUNCTUATION_MARK);
        catalogue.addToken("question mark", "?", PUNCTUATION_MARK);
        catalogue.addToken("quote", "'", PUNCTUATION_MARK);
        catalogue.addToken("double quote", "\"", PUNCTUATION_MARK);
        catalogue.addToken("hyphen", "-", PUNCTUATION_MARK);

        return catalogue;
    }

    /**
     * Returns an instance of a token catalogue.
     *
     * @return an instance of a token catalogue
     * /
    private static TokenCatalogue getCatalogue() {

        if (CATALOGUE == null) {

            CATALOGUE = newCatalogue();
        }

        return CATALOGUE;
    }

    public static void main(String[] args) {

        ParameterContainer parameters =
            new ParameterContainerImpl(args, new FixedPositionParameter("one", 0, true),
                                       new FixedPositionParameter("two", 1, true));
        System.out.println(parameters);

        Console console = System.console();

        String inputPrompt = "";
        {
            QueryResult<String> queryResult = getCatalogue().tokenByName(INPUT_PROPMPT);
            if (queryResult.existsResult()) {
                inputPrompt = queryResult.result();
            }
        }
        String outputPrompt = "";
        {
            QueryResult<String> queryResult = getCatalogue().tokenByName(OUTPUT_PROPMPT);
            if (queryResult.existsResult()) {
                outputPrompt = queryResult.result();
            }
        }
        List<String> exitCommands;
        {
            QueryResult<List<String>> queryResult = getCatalogue().tokensByContexts(EXIT_COMMAND);
            if (queryResult.existsResult()) {

                exitCommands = queryResult.result();

            } else {

                exitCommands = new ArrayList<>();
            }
        }

        Tokenizer<String, String> firstLayer = new StringTokenizer();
        Tokenizer<Sequence<String>, String> secondLayer = new SentenceTokenizer(getCatalogue(), PUNCTUATION_MARK);

        while (true) {

            console.printf(inputPrompt);
            String input = console.readLine();

            String normalizedInput = input.trim();

            Command command = null;
            try {

                command = COMMAND_CATALOGUE.match(normalizedInput);

            } catch (InputNotParsableException e) {

                // ignore this exception

            } catch (AmbigousInputException e) {

                System.out.println(outputPrompt + "" + e.getMessage());
            }

            if (command != null) {

                if (command.isCategory(EXIT_COMMAND_CATEGORY)) {

                    break;

                } else if (command.name().equals("help")) {

                    System.out.println(outputPrompt + COMMAND_CATALOGUE);

                } else if (command.name().equals("catalogue")) {

                    System.out.println(outputPrompt + getCatalogue());

                } else {

                    System.out.println(outputPrompt + "The command " + command.name() + " is not implemented yet!");
                }

            } else {

                System.out.println(outputPrompt + input);

                Sequence<String> first = firstLayer.split(normalizedInput);
                System.out.println(outputPrompt + first);

                Sequence<String> second = secondLayer.split(first);
                System.out.println(outputPrompt + second);
            }
        }
    }*/

}
