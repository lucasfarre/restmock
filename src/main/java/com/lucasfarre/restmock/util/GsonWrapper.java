package com.lucasfarre.restmock.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lucasfarre.restmock.util.gson.EitherTypeConverter;

public enum GsonWrapper {

    INSTANCE;

    private final Gson gson;

    GsonWrapper() {
        gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(Either.class, new EitherTypeConverter()).create();
    }

    /**
     * Serializes the given object into a JSON string
     *
     * @param object the object to be serialized
     * @return the JSON string representing the object
     */
    public String toJson(final Object object) {
        return gson.toJson(object);
    }

    /**
     * Deserializes the given JSON into a object
     *
     * @param json the JSON string
     * @param clazz the class of the object that the JSON represents
     * @param <T> the type of the object
     * @return the object represented by the JSON string
     */
    public <T> T fromJson(final String json, final Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

}
