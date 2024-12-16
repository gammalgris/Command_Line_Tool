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

package jmul.language.catalogues;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jmul.query.NoResultException;
import jmul.query.QueryResult;


/**
 * An implementation of a catalogue with string tokens.
 *
 * @author Kristian Kutin
 */
public class StringTokenCatalogue implements TokenCatalogue {

    /**
     * A data structure which associates a name with a token.
     */
    private Map<String, String> nameTokenMap;

    /**
     * A data structure which associates a name with context informations.
     */
    private Map<String, Set<String>> nameContextMap;

    /**
     * The default constructor.
     */
    public StringTokenCatalogue() {

        super();

        nameTokenMap = new HashMap<>();
        nameContextMap = new HashMap<>();
    }

    /**
     * Returns the count of tokens within this catalogue.
     *
     * @return a token count
     */
    @Override
    public int size() {

        return nameTokenMap.size();
    }

    /**
     * Checks if this catalogue is empty.
     *
     * @return <code>true</code> if this catalogue is empty, else <code>false</code>
     */
    @Override
    public boolean isEmpty() {

        return nameTokenMap.isEmpty();
    }

    /**
     * Adds the specified token with the specified properties (i.e. name and context informations).
     * A token may be known under different names. The context informations reflect the ambiguity of
     * tokens and serve as additional classification.
     *
     * @param name
     *        a unique name for the specified token
     * @param token
     *        the actual token (i.e. char sequence)
     * @param contexts
     *        one or more context informations
     */
    @Override
    public void addToken(String name, String token, String... contexts) {

        checkContexts(contexts);

        List<String> list = Arrays.asList(contexts);

        addToken(name, token, list);
    }

    /**
     * Adds the specified token with the specified properties (i.e. name and context informations).
     * A token may be known under different names. The context informations reflect the ambiguity of
     * tokens and serve as additional classification.
     *
     * @param name
     *        a unique name for the specified token
     * @param token
     *        the actual token (i.e. char sequence)
     * @param contexts
     *        one or more context informations
     */
    @Override
    public void addToken(String name, String token, Collection<String> contexts) {

        checkName(name);
        checkToken(token);
        checkContexts(contexts);

        if (nameTokenMap.containsKey(name)) {

            String message = String.format("A token with the name %s is already in this catalogue!", name);
            throw new IllegalArgumentException(message);
        }

        nameTokenMap.put(name, token);

        Set<String> subset = new HashSet<>(contexts);

        nameContextMap.put(name, subset);
    }

    /**
     * Checks the specified name parameter.
     *
     * @param name
     *        a name
     *
     * @return the specified name parameter
     */
    private static String checkName(String name) {

        if (name == null) {

            throw new IllegalArgumentException("No name (null) was specified!");
        }

        String normalizedName = name.trim();
        if (normalizedName.isEmpty()) {

            throw new IllegalArgumentException("No name (empty string) was specified!");
        }

        return name;
    }

    /**
     * Checks the specified token parameter.
     *
     * @param token
     *        a token
     *
     * @return the specified token parameter
     */
    private static String checkToken(String token) {

        if (token == null) {

            throw new IllegalArgumentException("No token (null) was specified!");
        }

        String normalizedToken = token.trim();
        if (normalizedToken.isEmpty()) {

            throw new IllegalArgumentException("No token (empty string) was specified!");
        }

        return token;
    }

    /**
     * Checks the specified context informations.
     *
     * @param contexts
     *        context informations
     *
     * @return the specified context informations
     */
    private static String[] checkContexts(String[] contexts) {

        if (contexts == null) {

            throw new IllegalArgumentException("No context informations (null) were specified!");
        }

        if (contexts.length < 1) {

            throw new IllegalArgumentException("No context informations (empty array) were specified!");
        }

        for (String context : contexts) {

            checkContext(context);
        }

        return contexts;
    }

    /**
     * Checks the specified context informations.
     *
     * @param contexts
     *        context informations
     *
     * @return the specified context informations
     */
    private static Collection<String> checkContexts(Collection<String> contexts) {

        if (contexts == null) {

            throw new IllegalArgumentException("No context informations (null) were specified!");
        }

        if (contexts.size() < 1) {

            throw new IllegalArgumentException("No context informations (empty array) were specified!");
        }

        for (String context : contexts) {

            checkContext(context);
        }

        return contexts;
    }

    /**
     * Checks the specified context parameter.
     *
     * @param context
     *        a context
     *
     * @return the specified context parameter
     */
    private static String checkContext(String context) {

        if (context == null) {

            throw new IllegalArgumentException("No context (null) was specified!");
        }

        String normalizedContext = context.trim();
        if (normalizedContext.isEmpty()) {

            throw new IllegalArgumentException("No context (empty string) was specified!");
        }

        return context;
    }

    /**
     * Checks the specified names.
     *
     * @param names
     *        names
     *
     * @return the specified names
     */
    private static String[] checkNames(String[] names) {

        if (names == null) {

            throw new IllegalArgumentException("No names (null) were specified!");
        }

        if (names.length < 1) {

            throw new IllegalArgumentException("No names (empty array) were specified!");
        }

        return names;
    }

    /**
     * Checks the specified names.
     *
     * @param names
     *        names
     *
     * @return the specified names
     */
    private static Collection<String> checkNames(Collection<String> names) {

        if (names == null) {

            throw new IllegalArgumentException("No names (null) were specified!");
        }

        if (names.size() < 1) {

            throw new IllegalArgumentException("No names (empty array) were specified!");
        }

        return names;
    }

    /**
     * Adds context informations to a token.
     *
     * @param name
     *        a unique name for the specified token
     * @param contexts
     *        one or more context informations
     */
    @Override
    public void addContext(String name, String... contexts) {

        checkContexts(contexts);

        List<String> list = Arrays.asList(contexts);

        addContext(name, list);
    }

    /**
     * Adds context informations to a token.
     *
     * @param name
     *        a unique name for the specified token
     * @param contexts
     *        one or more context informations
     */
    @Override
    public void addContext(String name, Collection<String> contexts) {

        checkName(name);
        checkContexts(contexts);

        if (!nameTokenMap.containsKey(name)) {

            String message = String.format("No token with the name %s is in this catalogue!", name);
            throw new IllegalArgumentException(message);
        }

        Set<String> subset = nameContextMap.get(name);
        subset.addAll(contexts);
    }

    /**
     * Removes context information from a token.
     *
     * @param name
     *        a unique name for the specified token
     * @param contexts
     *        one or more context informations
     */
    @Override
    public void removeContext(String name, String... contexts) {

        checkContexts(contexts);

        List<String> list = Arrays.asList(contexts);

        removeContext(name, list);
    }

    /**
     * Removes context information from a token.
     *
     * @param name
     *        a unique name for the specified token
     * @param contexts
     *        one or more context informations
     */
    @Override
    public void removeContext(String name, Collection<String> contexts) {

        checkName(name);
        checkContexts(contexts);

        if (!nameTokenMap.containsKey(name)) {

            String message = String.format("No token with the name %s is in this catalogue!", name);
            throw new IllegalArgumentException(message);
        }

        Set<String> subset = nameContextMap.get(name);
        subset.removeAll(contexts);
    }

    /**
     * Removes the token with the specified name. Also removes the context informations associated
     * with this token.
     *
     * @param name
     *        the name of a token
     */
    @Override
    public void removeToken(String name) {

        checkName(name);

        if (!nameTokenMap.containsKey(name)) {

            String message = String.format("No token with the name %s is in this catalogue!", name);
            throw new IllegalArgumentException(message);
        }

        nameTokenMap.remove(name);
        nameContextMap.remove(name);
    }

    /**
     * Returns all names with the specified token.
     *
     * @param token
     *        a token
     *
     * @return all names with the specified token
     */
    @Override
    public QueryResult<List<String>> namesByToken(String token) {

        if (nameTokenMap.isEmpty()) {

            return new TokenSequenceResult();
        }

        checkToken(token);

        List<String> names = new ArrayList<>();

        for (String name : nameTokenMap.keySet()) {

            String actualToken = nameTokenMap.get(name);

            if (actualToken.equals(token)) {

                names.add(name);
            }
        }

        if (names.isEmpty()) {

            return new TokenSequenceResult();
        }

        return new TokenSequenceResult(names);
    }

    /**
     * Return all context information for the specified token
     *
     * @param token
     *        a token
     *
     * @return all context information for the specified token
     */
    @Override
    public QueryResult<List<String>> contextsByToken(String token) {

        if (nameTokenMap.isEmpty()) {

            return new TokenSequenceResult();
        }

        QueryResult<List<String>> queryResult = namesByToken(token);
        if (!queryResult.existsResult()) {

            return new TokenSequenceResult();
        }

        Set<String> contexts = new HashSet<>();
        for (String name : queryResult.result()) {

            Set<String> subset = nameContextMap.get(name);
            contexts.addAll(subset);
        }

        List<String> result = new ArrayList<>(contexts);

        if (result.isEmpty()) {

            return new TokenSequenceResult();
        }

        return new TokenSequenceResult(result);
    }

    /**
     * Return all tokens within this ctalogue.
     *
     * @return all tokens
     */
    @Override
    public QueryResult<List<String>> tokens() {

        if (nameTokenMap.isEmpty()) {

            return new TokenSequenceResult();
        }

        Set<String> set = new HashSet<>(nameTokenMap.values());
        List<String> result = new ArrayList<>(set);

        return new TokenSequenceResult(result);
    }

    /**
     * Returns all tokens which are associated with the specified context informations.
     *
     * @param contexts
     *        a list of context informations
     *
     * @return all tokens which are associated with the specified context informations
     */
    @Override
    public QueryResult<List<String>> tokensByContexts(String... contexts) {

        if (nameTokenMap.isEmpty()) {

            return new TokenSequenceResult();
        }

        checkContexts(contexts);

        List<String> names = new ArrayList<>();

        for (String name : nameContextMap.keySet()) {

            Set<String> actualContexts = nameContextMap.get(name);
            for (String context : contexts) {

                if (actualContexts.contains(context)) {

                    names.add(name);
                    break;
                }
            }
        }

        String[] array = names.toArray(new String[] { });
        return tokensByNames(array);
    }

    /**
     * Returns all tokens which are associated with the specified context informations.
     *
     * @param contexts
     *        a list of context informations
     *
     * @return all tokens which are associated with the specified context informations
     */
    @Override
    public QueryResult<List<String>> tokensByContexts(Collection<String> contexts) {

        if (nameTokenMap.isEmpty()) {

            return new TokenSequenceResult();
        }

        checkContexts(contexts);

        List<String> names = new ArrayList<>();

        for (String name : nameContextMap.keySet()) {

            Set<String> actualContexts = nameContextMap.get(name);
            for (String context : contexts) {

                if (actualContexts.contains(context)) {

                    names.add(name);
                    break;
                }
            }
        }

        String[] array = names.toArray(new String[] { });
        return tokensByNames(array);
    }

    /**
     * Looks up all tokens by the specified names and returns them.
     *
     * @param names
     *        a list of names
     *
     * @return all tokens according to the specified names
     */
    @Override
    public QueryResult<List<String>> tokensByNames(String... names) {

        if (nameTokenMap.isEmpty()) {

            return new TokenSequenceResult();
        }

        checkNames(names);

        Set<String> set = new HashSet<>();

        for (String name : names) {

            String token = nameTokenMap.get(name);
            set.add(token);
        }

        if (set.isEmpty()) {

            return new TokenSequenceResult();
        }

        List<String> tokens = new ArrayList<>(set);

        return new TokenSequenceResult(tokens);
    }

    /**
     * Looks up all tokens by the specified names and returns them.
     *
     * @param names
     *        a list of names
     *
     * @return all tokens according to the specified names
     */
    @Override
    public QueryResult<List<String>> tokensByNames(Collection<String> names) {

        if (nameTokenMap.isEmpty()) {

            return new TokenSequenceResult();
        }

        checkNames(names);

        Set<String> set = new HashSet<>();

        for (String name : names) {

            String token = nameTokenMap.get(name);
            set.add(token);
        }

        if (set.isEmpty()) {

            return new TokenSequenceResult();
        }

        List<String> tokens = new ArrayList<>(set);
        return new TokenSequenceResult(tokens);
    }


    /**
     * Returns the token associated with the specified name.
     *
     * @param name
     *        the name of a token
     *
     * @return the token associated with the specified name
     */
    @Override
    public QueryResult<String> tokenByName(String name) {

        if (nameTokenMap.isEmpty()) {

            return new TokenResult();
        }

        checkName(name);

        String token = nameTokenMap.get(name);

        if (token == null) {

            return new TokenResult();
        }

        return new TokenResult(token);
    }

    /**
     * Returns an iterator for all names.
     *
     * @return an iterator
     */
    @Override
    public Iterator<String> iterator() {

        return nameTokenMap.keySet().iterator();
    }

}


class TokenResult implements QueryResult<String> {

    private final String token;

    public TokenResult() {

        super();

        token = null;
    }

    public TokenResult(String token) {

        super();

        this.token = token;
    }

    @Override
    public boolean existsResult() {

        return token != null;
    }

    @Override
    public String result() {

        if (!existsResult()) {

            throw new NoResultException();
        }

        return token;
    }

}


class TokenSequenceResult implements QueryResult<List<String>> {

    private final List<String> tokens;

    public TokenSequenceResult() {

        super();

        tokens = Collections.unmodifiableList(new ArrayList<>());
    }

    public TokenSequenceResult(Collection<String> collection) {

        super();

        this.tokens = new ArrayList<>(collection);
    }

    public TokenSequenceResult(String... tokens) {

        super();

        this.tokens = Arrays.asList(tokens);
    }

    @Override
    public boolean existsResult() {

        return !tokens.isEmpty();
    }

    @Override
    public List<String> result() {

        if (!existsResult()) {

            throw new NoResultException();
        }

        return tokens;
    }

}
