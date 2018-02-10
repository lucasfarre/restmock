package com.lucasfarre.restmock.dto;

import com.lucasfarre.restmock.util.Either;
import org.eclipse.jetty.http.HttpStatus;
import spark.Response;

public final class ApiError implements EitherAlternativeResponse<ApiError> {

    public static final ApiError ID_NOT_FOUND = new ApiError("id_not_found",
        "There is no mock with the given id.", HttpStatus.NOT_FOUND_404);
    public static final ApiError WRONG_HTTP_METHOD = new ApiError("wrong_http_method",
        "This mock responds to other HTTP method.", HttpStatus.BAD_REQUEST_400);
    public static final ApiError INVALID_BODY = new ApiError("invalid_body",
        "The given body is invalid", HttpStatus.BAD_REQUEST_400);

    private final String code;
    private final String message;
    private final int status;

    private ApiError(final String code, final String message, final int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    @Override
    public <A> Either<A, ApiError> asEitherResponse(final Response response) {
        response.status(status);
        return Either.alternative(this);
    }

    @Override
    public String toString() {
        return "ApiError{"
            + "code='" + code + '\''
            + ", message='" + message + '\''
            + ", errorStatus=" + status
            + '}';
    }

}
