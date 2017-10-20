import com.alibaba.fastjson.JSON;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by Administrator on 2017/10/11.
 */
public class MongoUtilsTest {

    private String collection = "col";
    private MongoDatabase db = null;
    private MongoCollection<Document> col;

    @Before
    public void initTest() {
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

    @Test
    public void createCollectionTest() {
        db.createCollection(collection);
    }

    @Test
    public void getCollectionTest() {
        col = db.getCollection(collection);
    }

    @Test
    public void saveDocument() {
        getCollectionTest();
        Map<String, Object> map = new HashMap<>();
        String json = JSON.toJSONString(map);
        Document doc = Document.parse(json);
        col.insertOne(doc);
    }

    @Test
    public void findDocumentTest() {
        getCollectionTest();
        FindIterable<Document> documents = col.find();
        documents.forEach((Consumer<? super Document>) document
                -> System.out.println(document.toJson()));
    }

    @Test
    public void deleteDocumentTest() {
        getCollectionTest();
        String fieldName = "_id";
        ObjectId value = new ObjectId("59ddeb4b138dfc0e7820045e");
        DeleteResult deleteResult = col.deleteOne(Filters.eq(fieldName, value));
        System.out.println(deleteResult.getDeletedCount());
    }
}
