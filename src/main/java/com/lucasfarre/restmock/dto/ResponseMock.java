package com.lucasfarre.restmock.dto;

import spark.route.HttpMethod;
import java.util.Map;

public final class ResponseMock {

    private final String id;
    private final HttpMethod httpMethod;
    private final int httpStatus;
    private final Map<String, String> headers;
    private final String body;

    /**
     * ResponseMock constructor
     *
     * @param id the id
     * @param responseMockRequest the response mock request
     */
    public ResponseMock(final String id, final ResponseMockRequest responseMockRequest) {
        this.id = id;
        this.httpMethod = responseMockRequest.getHttpMethod();
        this.httpStatus = responseMockRequest.getHttpStatus();
        this.headers = responseMockRequest.getHeaders();
        this.body = responseMockRequest.getBody();
    }

    public String getId() {
        return id;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "ResponseMock{"
            + "id='" + id
            + ", httpMethod=" + httpMethod
            + ", httpStatus=" + httpStatus
            + ", headers=" + headers
            + ", body='" + body + '\''
            + '}';
    }

}
