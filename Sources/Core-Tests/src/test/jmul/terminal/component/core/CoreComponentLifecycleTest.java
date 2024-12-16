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


package test.jmul.terminal.component.core;


import jmul.concurrent.threads.ThreadHelper;

import jmul.messaging.Messagebus;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import jmul.terminal.component.SystemStates;
import jmul.terminal.component.core.CoreComponent;
import jmul.terminal.component.messages.StopMessage;


/**
 * This testsuite tests the lifecycle of the core component.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class CoreComponentLifecycleTest {

    /**
     * Tests the lifecycle of the core component and checks the system state
     * on several occasions. The core component runs in its own thread.
     * A second thread triggers the stop signal after some time.
     */
    @Test(timeout = 20000L)
    public void testLifecycleWithNoAdditionalComponents() {

        CoreComponent coreComponent = new CoreComponent(ComponentNames.CORE_COMPONENT_NAME);

        assertEquals(SystemStates.INITIALIZATION, coreComponent.systemState());

        Thread thread1 = new Thread(new CoreThread(coreComponent));
        Thread thread2 = new Thread(new SendStopSignalThread(coreComponent.messagebus(), 10000L));

        thread1.start();
        thread2.start();
        ThreadHelper.sleep(50L);

        ThreadHelper.sleep(1000L);

        assertEquals(SystemStates.RUNNING, coreComponent.systemState());

        try {

            thread1.join();

        } catch (InterruptedException e) {

            throw new RuntimeException(e);
        }

        assertEquals(SystemStates.STOPPED, coreComponent.systemState());
    }
}


class ComponentNames {

    public static final String CORE_COMPONENT_NAME = "core component";

    public static final String SIGINT_COMPONENT_NAME = "stop signal thread";

    public static final String LOGGING_COMPONENT_NAME = "logging component";

}


class CoreThread implements Runnable {

    private volatile CoreComponent component;

    public CoreThread(CoreComponent component) {

        this.component = component;
    }

    public void run() {

        component.run();
    }

}


class SendStopSignalThread implements Runnable {

    private volatile Messagebus messagebus;

    private final long sleepTime;

    public SendStopSignalThread(Messagebus messagebus, long sleepTime) {

        super();

        this.messagebus = messagebus;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {

        ThreadHelper.sleep(sleepTime);

        StopMessage message = new StopMessage(ComponentNames.SIGINT_COMPONENT_NAME, ComponentNames.CORE_COMPONENT_NAME);
        messagebus.send(message);
    }

}
