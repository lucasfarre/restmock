package com.lucasfarre.restmock.dto;

import com.lucasfarre.restmock.util.Either;
import spark.Response;

public interface EitherAlternativeResponse<B> {

    /**
     * Returns this object as an either and sets the
     * given response status with a suitable status
     *
     * @param response the Spark response
     * @param <A> the either value type
     * @return this as an either
     */
    <A> Either<A, B> asEitherResponse(final Response response);

}
