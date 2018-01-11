package com.lucasfarre.restmock.router;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;

import static io.restassured.RestAssured.get;
import static org.junit.Assert.assertEquals;

public final class RouterTest {

    @BeforeClass
    public static void before() {
        Spark.port(8080);
        new Router().init();
        Spark.awaitInitialization();
    }

    @Test
    public void ping() {
        final Response response = get("http://localhost:8080/ping");
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals("pong", response.body().asString());
    }

}
