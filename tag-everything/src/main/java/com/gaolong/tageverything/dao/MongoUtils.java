package com.gaolong.tageverything.dao;

import com.alibaba.fastjson.JSON;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/11.
 */
public class MongoUtils {

    private static MongoDatabase db = null;

    static {
        MongoClientOptions options = new MongoClientOptions.Builder()
                .connectionsPerHost(400)
                .socketTimeout(5000)
                .socketKeepAlive(true)
                .connectTimeout(5000)
                .threadsAllowedToBlockForConnectionMultiplier(30)
                .readPreference(ReadPreference.secondary())
                .build();

        List<ServerAddress> seeds = Collections.singletonList(new ServerAddress("127.0.0.1", 27017));
        MongoClient mongoClient = new MongoClient(seeds, options);

        db = mongoClient.getDatabase("test");
    }

    public static void saveDocument(String collection, Map<String, Object> map) {
        MongoCollection<Document> col = db.getCollection(collection);
        String json = JSON.toJSONString(map);
        Document doc = Document.parse(json);
        col.insertOne(doc);
    }

    public static void saveDocument(String collection, Document doc) {
        MongoCollection<Document> col = db.getCollection(collection);
        col.insertOne(doc);
    }

    public static <T> Iterable<T> findAll(String collection, Class<T> clazz) {
        MongoCollection<Document> col = db.getCollection(collection);
        return col.find(clazz);
    }
}
