package com.lucasfarre.restmock.controller;

import com.google.gson.JsonParseException;
import com.lucasfarre.restmock.dto.ApiError;
import com.lucasfarre.restmock.dto.ResponseMock;
import com.lucasfarre.restmock.service.ResponseMockService;
import com.lucasfarre.restmock.util.Either;
import com.lucasfarre.restmock.util.GsonWrapper;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

import java.util.Locale;
import java.util.Optional;
import spark.ResponseTransformer;

public enum ResponseMockController {

    INSTANCE;

    private static final int MAX_BODY_SIZE = 16 * 1024;
    private static final String ID_PARAM = "id";

    private final ResponseMockService responseMockService = ResponseMockService.INSTANCE;

    public static final ResponseTransformer RESPONSE_TRANSFORMER = response -> {
        final Either<String, ApiError> either = (Either<String, ApiError>) response;
        return either.isValuePresent() ? either.getValue() : GsonWrapper.INSTANCE.toJson(either.getAlternative());
    };

    /**
     * Posts a new mock
     *
     * @param request the request
     * @param response the response
     * @return the new response mock
     */
    public Either<ResponseMock, ApiError> postResponseMock(final Request request, final Response response) {
        if (request.contentLength() > MAX_BODY_SIZE) {
            return ApiError.LARGE_BODY.asEitherAlternativeResponse(response);
        }
        final ResponseMock responseMockRequest;
        try {
            responseMockRequest = GsonWrapper.INSTANCE.fromJson(request.body(), ResponseMock.class);
        } catch (final JsonParseException e) {
            return ApiError.INVALID_BODY.asEitherAlternativeResponse(response);
        }
        response.status(HttpStatus.CREATED_201);
        return Either.value(responseMockService.postResponseMock(responseMockRequest));
    }

    /**
     * Gets an existing mock
     *
     * @param request the request
     * @param response the response
     * @return the response mock
     */
    public Either<String, ApiError> getResponseMock(final Request request, final Response response) {
        final Optional<ResponseMock> responseMockOptional = responseMockService.getResponseMock(request.params(ID_PARAM));
        if (!responseMockOptional.isPresent()) {
            return ApiError.ID_NOT_FOUND.asEitherAlternativeResponse(response);
        }
        final ResponseMock responseMock = responseMockOptional.get();
        if (!request.requestMethod().toUpperCase(Locale.US).equals(
                responseMock.getMethod().name().toUpperCase(Locale.US))) {
            return ApiError.WRONG_HTTP_METHOD.asEitherAlternativeResponse(response);
        }
        response.status(responseMock.getStatus());
        responseMock.getHeaders().entrySet().forEach(entry -> response.header(entry.getKey(), entry.getValue()));
        return Either.value(responseMock.getBody());
    }

}
