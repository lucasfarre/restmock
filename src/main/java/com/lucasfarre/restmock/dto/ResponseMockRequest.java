package com.lucasfarre.restmock.dto;

import spark.route.HttpMethod;

import java.util.Map;

public final class ResponseMockRequest {

    private final HttpMethod httpMethod;
    private final int httpStatus;
    private final Map<String, String> headers;
    private final String body;

    /* default */ ResponseMockRequest(final HttpMethod httpMethod, final int httpStatus,
            final Map<String, String> headers, final String body) {
        this.httpMethod = httpMethod;
        this.httpStatus = httpStatus;
        this.headers = headers;
        this.body = body;
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
            + "httpMethod=" + httpMethod
            + ", httpStatus=" + httpStatus
            + ", headers=" + headers
            + ", body='" + body + '\''
            + '}';
    }

}
