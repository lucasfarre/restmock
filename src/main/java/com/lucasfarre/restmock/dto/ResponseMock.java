package com.lucasfarre.restmock.dto;

import spark.route.HttpMethod;
import java.util.Map;

public final class ResponseMock {

    private final String id;
    private final HttpMethod method;
    private final int status;
    private final Map<String, String> headers;
    private final String body;

    private ResponseMock(final String id, final HttpMethod method, final int status,
            final Map<String, String> headers, final String body) {
        this.id = id;
        this.method = method;
        this.status = status;
        this.headers = headers;
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public int getStatus() {
        return status;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    @SuppressWarnings("checkstyle:multiplestringliterals")
    @Override
    public String toString() {
        return "ResponseMock{"
            + "id='" + id
            + ", method=" + method
            + ", status=" + status
            + ", headers=" + headers
            + ", body='" + body + '\''
            + '}';
    }

    public static final class Builder {

        private String id;
        private final HttpMethod method;
        private final int status;
        private final Map<String, String> headers;
        private final String body;

        /**
         * Builder constructor from an existing ResponseMock
         *
         * @param responseMock the existing ResponseMock
         */
        public Builder(final ResponseMock responseMock) {
            method = responseMock.getMethod();
            status = responseMock.getStatus();
            headers = responseMock.getHeaders();
            body = responseMock.getBody();
        }

        /**
         * Sets the ResponseMock id
         *
         * @param id the ResponseMock id
         * @return this builder
         */
        public Builder withId(final String id) {
            this.id = id;
            return this;
        }

        /**
         * Builds the ResponseMock
         *
         * @return the new ResponseMock
         */
        public ResponseMock build() {
            return new ResponseMock(id, method, status, headers, body);
        }

        @SuppressWarnings("checkstyle:multiplestringliterals")
        @Override
        public String toString() {
            return "Builder{"
                + "id='" + id + '\''
                + ", method=" + method
                + ", status=" + status
                + ", headers=" + headers
                + ", body='" + body + '\''
                + '}';
        }
    }

}
