package co.edu.escuelaing.logservice.repositories;

import co.edu.escuelaing.logservice.entities.Log;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LogRepository {
    private final MongoCollection<Document> logCollection;

    public LogRepository(MongoCollection<Document> logCollection) {
        this.logCollection = logCollection;
    }

    public void save(String text) {
        Document document = new Document()
                .append("text", text)
                .append("created_at", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        logCollection.insertOne(document);
    }

    public List<Log> getLast(int n) {
        List<Log> result = new ArrayList<>();
        FindIterable<Document> queryResult = logCollection.find().sort(new Document("_id", -1)).limit(n);
        for (Document doc: queryResult) {
            result.add(new Log(doc.getString("text"), doc.getString("created_at")));
        }

        return result;
    }
}
