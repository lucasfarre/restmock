package com.lucasfarre.restmock.router;

import com.lucasfarre.restmock.controller.ResponseMockController;
import com.lucasfarre.restmock.util.GsonWrapper;
import spark.servlet.SparkApplication;

import static spark.Spark.path;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.patch;
import static spark.Spark.delete;

public final class Router implements SparkApplication {

    private static final String CURRENT_PATH = "";

    @Override
    public void init() {
        post("/mock", ResponseMockController.INSTANCE::postResponseMock, GsonWrapper.INSTANCE::toJson);
        path("/mock/:id", () -> {
            get(CURRENT_PATH, ResponseMockController.INSTANCE::getResponseMock, ResponseMockController.RESPONSE_TRANSFORMER);
            post(CURRENT_PATH, ResponseMockController.INSTANCE::getResponseMock, ResponseMockController.RESPONSE_TRANSFORMER);
            put(CURRENT_PATH, ResponseMockController.INSTANCE::getResponseMock, ResponseMockController.RESPONSE_TRANSFORMER);
            patch(CURRENT_PATH, ResponseMockController.INSTANCE::getResponseMock, ResponseMockController.RESPONSE_TRANSFORMER);
            delete(CURRENT_PATH, ResponseMockController.INSTANCE::getResponseMock, ResponseMockController.RESPONSE_TRANSFORMER);
        });
    }

}
