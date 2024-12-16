package assistant;

import java.util.Date;

import jmul.concurrent.threads.ThreadHelper;

public class SimpleRunner {

    public static void main(String... args) {

        Counter counter = new Counter();
        SigintHandler sigintHandler = new SigintHandler(counter);

        Runtime.getRuntime().addShutdownHook(sigintHandler);

        counter.run();
    }

}

class Counter {

    private volatile int a;

    public Counter() {

        a = 0;
    }

    public void run() {

        while (a < 10) {

            a++;
            System.out.println(new Date());
            ThreadHelper.sleep(500L);
        }
    }

    public boolean isRunning() {

        return a < 10;
    }

}

class SigintHandler extends Thread {

    private final Counter counter;

    public SigintHandler(Counter counter) {

        this.counter = counter;
    }

    public void run() {

        System.out.println("sigint signal was received!");

        do {

            ThreadHelper.sleep(500L);

        } while (counter.isRunning());

        ThreadHelper.sleep(1000L);
    }

}
