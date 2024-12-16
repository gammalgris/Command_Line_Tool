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


import java.util.Collection;
import java.util.List;

import jmul.query.QueryResult;


/**
 * This interface describes a catalogue for tokens. The tokens have a name and
 * are associated with one or more context informations.
 * <br>
 * This catalogue is mutable in order to change it as needed.
 *
 * TODO
 * Check which methods are really needed.
 * TODO
 * Clarify if seveal contexts can be specified how this has to be interpreted as
 * set operation (not and or xor ...).
 * TODO
 * Change query result type -gt; QueryResult in order to avoid throwing unnecessary
 * exceptions.
 *
 * @author Kristian Kutin
 */
public interface TokenCatalogue extends Iterable<String> {

    /**
     * Returns the count of tokens within this catalogue.
     *
     * @return a token count
     */
    int size();

    /**
     * Checks if this catalogue is empty.
     *
     * @return <code>true</code> if this catalogue is empty, else <code>false</code>
     */
    boolean isEmpty();

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
    void addToken(String name, String token, String... contexts);

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
    void addToken(String name, String token, Collection<String> contexts);

    /**
     * Adds context informations to a token.
     *
     * @param name
     *        a unique name for the specified token
     * @param contexts
     *        one or more context informations
     */
    void addContext(String name, String... contexts);

    /**
     * Adds context informations to a token.
     *
     * @param name
     *        a unique name for the specified token
     * @param contexts
     *        one or more context informations
     */
    void addContext(String name, Collection<String> contexts);

    /**
     * Removes context information from a token.
     *
     * @param name
     *        a unique name for the specified token
     * @param contexts
     *        one or more context informations
     */
    void removeContext(String name, String... contexts);

    /**
     * Removes context information from a token.
     *
     * @param name
     *        a unique name for the specified token
     * @param contexts
     *        one or more context informations
     */
    void removeContext(String name, Collection<String> contexts);

    /**
     * Removes the token with the specified name. Also removes the context informations
     * associated with this token.
     *
     * @param name
     *        the name of a token
     */
    void removeToken(String name);

    /**
     * Returns all names with the specified token.
     *
     * @param token
     *        a token
     *
     * @return all names with the specified token
     */
    QueryResult<List<String>> namesByToken(String token);

    /**
     * Return all context information for the specified token
     *
     * @param token
     *        a token
     *
     * @return all context information for the specified token
     */
    QueryResult<List<String>> contextsByToken(String token);

    /**
     * Return all tokens within this ctalogue.
     *
     * @return all tokens
     */
    QueryResult<List<String>> tokens();

    /**
     * Returns all tokens which are associated with the specified context informations.
     *
     * @param contexts
     *        a list of context informations
     *
     * @return all tokens which are associated with the specified context informations
     */
    QueryResult<List<String>> tokensByContexts(String... contexts);

    /**
     * Returns all tokens which are associated with the specified context informations.
     *
     * @param contexts
     *        a list of context informations
     *
     * @return all tokens which are associated with the specified context informations
     */
    QueryResult<List<String>> tokensByContexts(Collection<String> contexts);

    /**
     * Looks up all tokens by the specified names and returns them.
     *
     * @param names
     *        a list of names
     *
     * @return all tokens according to the specified names
     */
    QueryResult<List<String>> tokensByNames(String... names);

    /**
     * Looks up all tokens by the specified names and returns them.
     *
     * @param names
     *        a list of names
     *
     * @return all tokens according to the specified names
     */
    QueryResult<List<String>> tokensByNames(Collection<String> names);

    /**
     * Returns the token associated with the specified name.
     *
     * @param name
     *        the name of a token
     *
     * @return the token associated with the specified name
     */
    QueryResult<String> tokenByName(String name);

}
