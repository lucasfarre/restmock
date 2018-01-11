package com.lucasfarre.restmock;

import com.lucasfarre.restmock.router.Router;
import org.apache.logging.log4j.LogManager;
import spark.Spark;

public final class Main {

    private static final int PORT = 8080;

    private Main() {
        throw new AssertionError("Can't instantiate Main class");
    }

    /**
     * Application startup
     * @param args The command line arguments.
     */
    public static void main(final String[] args) {
        Spark.port(PORT);
        new Router().init();
        LogManager.getLogger(Main.class).info("Listening on http://localhost:8080/");
    }

}
