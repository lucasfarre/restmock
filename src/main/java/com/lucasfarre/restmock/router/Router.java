package com.lucasfarre.restmock.router;

import spark.servlet.SparkApplication;

import static spark.Spark.get;

public final class Router implements SparkApplication {

    @Override
    public void init() {
        get("/ping", (request, response) -> "pong");
    }

}
