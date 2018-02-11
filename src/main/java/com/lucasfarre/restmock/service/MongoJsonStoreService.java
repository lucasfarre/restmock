package com.lucasfarre.restmock.service;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Optional;

public enum MongoJsonStoreService implements JsonStoreService {

    INSTANCE;

    @SuppressWarnings("PMD.AvoidUsingHardCodedIP")
    private static final String HOST = "172.18.0.2";
    private static final int PORT = 27017;
    private static final String ID_FIELD = "_id";

    private final MongoCollection<Document> collection;

    MongoJsonStoreService() {
        collection = new MongoClient(HOST, PORT).getDatabase("db").getCollection("json");
    }

    @Override
    public void save(final String key, final String json) {
        if (findDocument(key) != null) {
            throw new IllegalArgumentException("Already used key.");
        }
        collection.insertOne(Document.parse(json).append(ID_FIELD, key));
    }

    @Override
    public Optional<String> get(final String key) {
        final Document document = findDocument(key);
        if (document == null) {
            return Optional.empty();
        }
        return Optional.of(document.toJson());
    }

    private Document findDocument(final String key) {
        return collection.find(new BasicDBObject(ID_FIELD, key)).first();
    }

}
