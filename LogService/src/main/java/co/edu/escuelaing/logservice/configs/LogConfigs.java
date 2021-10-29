package co.edu.escuelaing.logservice.configs;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Map;

public class LogConfigs {

    private static MongoConfig getMongoConfig() {
        Map<String, String> envs = System.getenv();
        return new MongoConfig(
                envs.getOrDefault("MONGO_URI", "mongodb://localhost:27017"),
                envs.getOrDefault("MONGO_DB", "logs"),
                envs.getOrDefault("MONGO_LOGS_COLLECTION", "logs")
        );
    }

    public static int getServerPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    public static MongoCollection<Document> getMongoCollection() {
        MongoConfig config = getMongoConfig();
        return MongoClients.create(config.getUri())
                .getDatabase(config.getDatabase())
                .getCollection(config.getCollection());
    }
}

class MongoConfig {

    public MongoConfig(String uri, String database, String collection) {
        setUri(uri);
        setDatabase(database);
        setCollection(collection);
    }

    private String uri;
    private String database;
    private String collection;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }
}