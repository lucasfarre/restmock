package com.lucasfarre.restmock.service;

import com.lucasfarre.restmock.dto.ResponseMock;
import com.lucasfarre.restmock.util.GsonWrapper;

import java.util.Optional;
import java.util.UUID;

public enum ResponseMockService {

    INSTANCE;

    private final JsonStoreService jsonStoreService = MongoJsonStoreService.INSTANCE;

    /**
     * Posts a new response mock
     *
     * @param responseMockRequest the response mock request
     * @return the new response mock
     */
    public ResponseMock postResponseMock(final ResponseMock responseMockRequest) {
        final ResponseMock responseMock = new ResponseMock.Builder(responseMockRequest)
            .withId(UUID.randomUUID().toString()).build();
        jsonStoreService.save(responseMock.getId(), GsonWrapper.INSTANCE.toJson(responseMock));
        return responseMock;
    }

    /**
     * Gets an existing response mock
     *
     * @param id the response mock id
     * @return the response mock
     */
    public Optional<ResponseMock> getResponseMock(final String id) {
        return jsonStoreService.get(id).map(json -> GsonWrapper.INSTANCE.fromJson(json, ResponseMock.class));
    }

}
