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

package jmul.logging;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import jmul.concurrent.threads.ThreadHelper;

import jmul.io.SystemPaths;


/**
 * An implementation of an entity that prints the content of a log file to the console.
 *
 * @author Kristian Kutin
 */
public class LogViewer {

    /**
     * A constant sleep time.
     */
    private static final long DEFAULT_SLEEP_TIME;

    /*
     * The static initializer.
     */
    static {

        DEFAULT_SLEEP_TIME = 50L;
    }

    /**
     * A file path to a log file.
     */
    private final String logFile;

    /**
     * A flag indicating the internal state.
     */
    private volatile boolean running;

    /**
     * A reader entity.
     */
    private BufferedReader reader;

    /**
     * Creates a new instance according to the sprcified parameter.
     *
     * @param logFile
     *        a file path to a log file
     */
    public LogViewer(String logFile) {

        super();

        this.logFile = checkLogFile(logFile);
    }

    /**
     * Checks the specified parameter.
     *
     * @param logFile
     *        a file path to a log file
     *
     * @return the specified parameter
     */
    private static String checkLogFile(String logFile) {

        if (logFile == null) {

            throw new IllegalArgumentException("No file path (null) was specified!");
        }

        return logFile;
    }

    /**
     * Prints the content of a log file to the console.
     */
    public void printLog() {

        Runtime.getRuntime().addShutdownHook(new SigintHandler());

        openLogFile();

        while (running) {

            readAndPrintLine();

            ThreadHelper.sleep(DEFAULT_SLEEP_TIME);
        }

        closeLogFile();
    }

    /**
     * Opens the log file.
     */
    private void openLogFile() {

        try {

            FileReader fileReader = new FileReader(logFile);
            reader = new BufferedReader(fileReader);

        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

    /**
     * Reads a line from the log file and prints it to the console.
     */
    private void readAndPrintLine() {

        try {

            String logMessage = reader.readLine();
            if (logMessage != null) {

                System.out.println(logMessage);
            }

        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

    /**
     * Closes the log file.
     */
    private void closeLogFile() {

        try {

            reader.close();

        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

    /**
     * The main method.
     *
     * @param args
     *        all command line arguments
     */
    public static void main(String... args) {

        SystemPaths systemPaths = new SystemPaths();

        LogViewer logViewer = new LogViewer(systemPaths.logFile);
        logViewer.printLog();
    }


    class SigintHandler extends Thread {

        @Override
        public void run() {

            running = false;
        }
    }

}
