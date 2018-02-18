package com.lucasfarre.restmock;

import com.lucasfarre.restmock.dto.ResponseMock;
import com.lucasfarre.restmock.router.Router;
import com.lucasfarre.restmock.service.JsonStoreService;
import com.lucasfarre.restmock.service.ResponseMockService;
import com.lucasfarre.restmock.util.GsonWrapper;
import io.restassured.response.Response;
import java.lang.reflect.Field;
import java.util.Optional;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;
import spark.utils.IOUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class RouterTest {

    private static final String MOCK_BODY = "{\"message\":\"Hello, World!\"}";

    @BeforeClass
    public static void before() {
        Spark.port(8080);
        new Router().init();
        Spark.awaitInitialization();
    }

    @Test
    public void postAndGetMock() throws Exception {
        final JsonStoreService jsonStoreServiceMock = mock(JsonStoreService.class);
        final Field jsonStoreServiceField = ResponseMockService.class.getDeclaredField("jsonStoreService");
        jsonStoreServiceField.setAccessible(true);
        jsonStoreServiceField.set(ResponseMockService.INSTANCE, jsonStoreServiceMock);

        doNothing().when(jsonStoreServiceMock).save(any(), any());
        final String body = IOUtils.toString(getClass().getResourceAsStream("/mock_request.json"));
        final Response response = given().body(body).post("http://localhost:8080/mock");
        assertEquals(HttpStatus.SC_CREATED, response.statusCode());
        final ResponseMock responseMock = GsonWrapper.INSTANCE.fromJson(response.body().asString(), ResponseMock.class);
        assertNotNull(responseMock.getId());
        assertEquals(HttpStatus.SC_OK, responseMock.getStatus());
        assertEquals(ContentType.APPLICATION_JSON.getMimeType(), responseMock.getHeaders().get(HttpHeaders.CONTENT_TYPE));
        assertEquals(MOCK_BODY, responseMock.getBody());

        final String mockResponseId = "73f01ced-48be-46ff-ad68-5f4103517d3b";
        final String mockResponseJson = IOUtils.toString(getClass().getResourceAsStream("/mock_response.json"));
        when(jsonStoreServiceMock.get(any())).thenReturn(Optional.of(mockResponseJson));
        final Response getResponse = given().get("http://localhost:8080/mock/" + mockResponseId);
        assertEquals(HttpStatus.SC_OK, getResponse.statusCode());
        assertEquals(ContentType.APPLICATION_JSON.getMimeType(), getResponse.getHeader(HttpHeaders.CONTENT_TYPE));
        assertEquals(MOCK_BODY, getResponse.body().asString());
    }

}
