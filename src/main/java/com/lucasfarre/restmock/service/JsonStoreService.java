package com.lucasfarre.restmock.service;

import java.util.Optional;

public interface JsonStoreService {

    /**
     * Saves a JSON document with the given key
     *
     * @param key the storing key
     * @param json the JSON document
     */
    void save(final String key, final String json);

    /**
     * Gets a JSON document with the given key
     *
     * @param key the storing key
     * @return a JSON document optional
     */
    Optional<String> get(final String key);

}
