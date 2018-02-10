package com.lucasfarre.restmock;

import com.lucasfarre.restmock.dto.ResponseMock;
import com.lucasfarre.restmock.router.Router;
import com.lucasfarre.restmock.util.GsonWrapper;
import io.restassured.response.Response;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;
import spark.utils.IOUtils;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public final class RouterTest {

    private static final String MOCK_BODY = "{\"message\":\"Hello World!\"}";

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

    @Test
    public void postAndGetMock() throws Exception {
        final String body = IOUtils.toString(getClass().getResourceAsStream("/mock_request.json"));
        final Response response = given().body(body).post("http://localhost:8080/mock");
        assertEquals(HttpStatus.SC_CREATED, response.statusCode());
        final ResponseMock responseMock = GsonWrapper.INSTANCE.fromJson(response.body().asString(), ResponseMock.class);
        assertNotNull(responseMock.getId());
        assertEquals(HttpStatus.SC_OK, responseMock.getHttpStatus());
        assertEquals(ContentType.APPLICATION_JSON.getMimeType(), responseMock.getHeaders().get(HttpHeaders.CONTENT_TYPE));
        assertEquals(MOCK_BODY, responseMock.getBody());

        final Response getResponse = given().get("http://localhost:8080/mock/" + responseMock.getId());
        assertEquals(HttpStatus.SC_OK, getResponse.statusCode());
        assertEquals(ContentType.APPLICATION_JSON.getMimeType(), getResponse.getHeader(HttpHeaders.CONTENT_TYPE));
        assertEquals(MOCK_BODY, getResponse.body().asString());
    }

}
