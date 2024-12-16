package jmul.terminal;


import jmul.cmd.ParameterContainer;
import jmul.cmd.ParameterContainerImpl;

import jmul.terminal.component.core.CoreComponent;
import jmul.terminal.component.core.SigintHandler;
import jmul.terminal.component.core.WaitThread;


@Deprecated
public class Runner {

    private static final String CORE_COMPONENT_NAME;

    private static final String SIGINT_HANDLER_NAME;

    static {

        CORE_COMPONENT_NAME = "core";
        SIGINT_HANDLER_NAME = "sigint handler";
    }

    private Runner() {

        throw new UnsupportedOperationException();
    }

    public static void main(String... args) {

        ParameterContainer parameters = new ParameterContainerImpl(args);
        System.out.println(parameters);

        CoreComponent coreComponent = new CoreComponent(CORE_COMPONENT_NAME);
        Thread.currentThread().setName(coreComponent.name());

        // initialize the first shutdown hook
        SigintHandler sigintHandler = new SigintHandler(SIGINT_HANDLER_NAME);
        sigintHandler.attachToCoreComponent(coreComponent);

        Thread sigintThread = new Thread(sigintHandler);
        Runtime.getRuntime().addShutdownHook(sigintThread);

        // initialize the second shutdown hook
        Thread waitThread = new WaitThread(coreComponent);
        Runtime.getRuntime().addShutdownHook(waitThread);

        coreComponent.run();
    }

}
