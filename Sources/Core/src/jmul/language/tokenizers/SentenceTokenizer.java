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

package jmul.language.tokenizers;


import java.util.ArrayList;
import java.util.List;

import jmul.language.catalogues.TokenCatalogue;
import jmul.language.types.Sequence;
import jmul.language.types.StringSequence;

import jmul.query.QueryResult;


public class SentenceTokenizer implements Tokenizer<Sequence<String>, String> {

    private final TokenCatalogue catalogue;

    private final String context;

    public SentenceTokenizer(TokenCatalogue catalogue, String context) {

        super();

        this.catalogue = checkCatalogue(catalogue);
        this.context = checkContext(context);
    }

    private static String checkContext(String context) {

        if (context == null) {

            throw new IllegalArgumentException("No context (null) was specified!");
        }

        return context;
    }

    private static TokenCatalogue checkCatalogue(TokenCatalogue catalogue) {

        if (catalogue == null) {

            throw new IllegalArgumentException("No catalogue (null) was specified!");
        }

        return catalogue;
    }

    @Override
    public Sequence<String> split(Sequence<String> input) {

        checkInput(input);

        QueryResult<List<String>> queryResult = catalogue.tokensByContexts(context);
        if (!queryResult.existsResult()) {

            return new StringSequence();
        }

        List<String> specialTokens = queryResult.result();
        List<String> tokens = new ArrayList<>();

        for (String token : input) {

            List<String> splittedToken = splitToken(specialTokens, token);
            tokens.addAll(splittedToken);
        }

        return new StringSequence(tokens);
    }

    private static Sequence<String> checkInput(Sequence<String> input) {

        if (input == null) {

            throw new IllegalArgumentException("No input (null) was specified!");
        }

        return input;
    }

    private List<String> splitToken(List<String> specialTokens, String input) {

        List<String> list = new ArrayList<>();

        if (input.isEmpty()) {

            return list;
        }


        String token = null;
        int index = 0;
        for (; index < input.length(); index++) {

            for (String specialToken : specialTokens) {

                if (input.startsWith(specialToken, index)) {

                    token = specialToken;
                    break;
                }
            }

            if (token != null) {

                break;
            }
        }

        if (token == null) {

            list.add(input);

        } else {

            String head = input.substring(0, index);
            String middle = token;
            String tail = input.substring(index + token.length());

            list.add(head);
            list.add(middle);
            list.addAll(splitToken(specialTokens, tail));
        }


        return list;
    }

}
