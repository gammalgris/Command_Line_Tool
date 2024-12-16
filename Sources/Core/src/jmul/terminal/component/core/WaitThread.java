package jmul.terminal.component.core;


import jmul.concurrent.threads.ThreadHelper;

import jmul.terminal.component.SystemStates;


/**
 * This entity forces the JVM to stay alive for some time after a sigint signal has been
 * sent (e.g. CTRL+C).
 *
 * @author Kristian Kutin
 */
public class WaitThread extends Thread {

    /**
     * A constant sleep time.
     */
    private static final long DEFAULT_SLEEP_TIME;

    /*
     * The static initializer.
     */
    static {

        DEFAULT_SLEEP_TIME = 500L;
    }

    /**
     * A reference to the core component.
     */
    private volatile CoreComponent coreComponent;

    /**
     * Creates a new component that rects to a sigint signal.
     *
     * @param component
     *        a core component
     */
    public WaitThread(CoreComponent component) {

        super("wait thread");

        coreComponent = component;
    }

    /**
     * This method is triggered upon receiving a sigint signal.
     */
    @Override
    public void run() {

        do {

            ThreadHelper.sleep(DEFAULT_SLEEP_TIME);

        } while (coreComponent.systemState() != SystemStates.STOPPED);

        for (int a = 0; a < 10; a++) {

            ThreadHelper.sleep(DEFAULT_SLEEP_TIME);
        }
    }

}
