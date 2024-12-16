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

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import jmul.terminal.component.ComponentRoles;
import jmul.terminal.component.SystemStates;
import jmul.terminal.component.core.CoreComponent;
import jmul.terminal.component.logging.ConsoleLoggingComponent;
import jmul.terminal.component.misc.NameRoleEntry;

import jmul.terminal.utils.logging.LogLevels;


/**
 * This testsuite tests the lifecycle of the core component.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class CoreComponentLifecycle2Test {

    /**
     * Tests the lifecycle of the core component and checks the system state on
     * several occasions. The core component runs in its own thread. A logger
     * component runs in a seperate thread. Another thread triggers the stop signal
     * after some time.
     */
    @Test(timeout = 30000L)
    public void testLifecycleWithLogger() {

        NameRoleEntry[] knownComponentsToCore = {
            new NameRoleEntry(ComponentNames.LOGGING_COMPONENT_NAME, ComponentRoles.LOGGER) };
        NameRoleEntry[] knownComponentsToLogger = {
            new NameRoleEntry(ComponentNames.CORE_COMPONENT_NAME, ComponentRoles.CORE) };

        CoreComponent coreComponent = new CoreComponent(ComponentNames.CORE_COMPONENT_NAME, knownComponentsToCore);
        ConsoleLoggingComponent loggingComponent =
            new ConsoleLoggingComponent(ComponentNames.LOGGING_COMPONENT_NAME, LogLevels.DEBUG, knownComponentsToLogger);
        loggingComponent.connect(coreComponent.messagebus());

        assertEquals(SystemStates.INITIALIZATION, coreComponent.systemState());

        Thread thread1 = new Thread(new CoreThread(coreComponent));
        Thread thread2 = new Thread(new SendStopSignalThread(coreComponent.messagebus(), 10000L));
        Thread thread3 = new Thread(loggingComponent);

        thread1.start();
        thread2.start();
        thread3.start();
        ThreadHelper.sleep(50L);

        ThreadHelper.sleep(1000L);

        System.out.println("Test:" + coreComponent.systemState().getStateName());
        assertEquals(SystemStates.RUNNING, coreComponent.systemState());

        try {

            thread1.join();

        } catch (InterruptedException e) {

            throw new RuntimeException(e);
        }

        System.out.println("Test:" + coreComponent.systemState().getStateName());
        assertEquals(SystemStates.STOPPED, coreComponent.systemState());
    }

}
