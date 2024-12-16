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

package jmul.terminal.component.core;


import jmul.messaging.StandardMessage;

import jmul.terminal.Resources;
import jmul.terminal.component.IndependentComponentBase;
import jmul.terminal.component.messages.StopMessage;
import jmul.terminal.utils.logging.LogLevels;


/**
 * This component triggers a cleanup after a sigint signal has been sent (e.g. CTRL+C). In
 * this case a stop message is sent to the core component.
 *
 * @author Kristian Kutin
 */
public class SigintHandler extends IndependentComponentBase {

    /**
     * The component name for a sigint handler.
     */
    private static final String COMPONENT_NAME;

    /**
     * A constant sleep time.
     */
    private static final long DEFAULT_SLEEP_TIME;

    /*
     * The static initializer.
     */
    static {

        COMPONENT_NAME = "sigint handler";
        DEFAULT_SLEEP_TIME = 50L;
    }

    /**
     * A reference to the core component.
     */
    private volatile CoreComponent coreComponent;

    /**
     * Creates a new component that rects to a sigint signal.
     *
     * @param name
     *        the name for this component
     */
    public SigintHandler(String name) {

        super(name);
    }

    /**
     * Attach this sigint handler to a core component.
     *
     * @param component
     *        a core component
     */
    public void attachToCoreComponent(CoreComponent component) {

        if (component == null) {

            throw new IllegalArgumentException("No component (null) was specified!");
        }

        if (coreComponent != null) {

            throw new ComponentAlreadyConnectedException();
        }

        coreComponent = component;
        super.connect(coreComponent.messagebus());
    }

    /**
     * This method is triggered upon receiving a sigint signal.
     */
    @Override
    public void run() {

        Resources.LOGGER.log(LogLevels.INFO, COMPONENT_NAME, "A sigint signal was received.");

        StandardMessage message = new StopMessage(senderName(), coreComponent.name());
        sendMessage(message);

        Resources.LOGGER.log(LogLevels.INFO, COMPONENT_NAME, "A stop message was sent.");
    }

}
