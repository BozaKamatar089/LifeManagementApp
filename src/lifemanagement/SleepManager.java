package lifemanagement;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

public class SleepManager {
    private final MongoCollection<Document> collection;

    public SleepManager() {
        MongoDatabase db = MongoDBConnection.getDatabase();
        collection = db.getCollection("sleep_entries");
    }

    public void addSleepEntry(Sleep entry) {
        collection.insertOne(entry.toDocument());
    }

    public ArrayList<Sleep> getEntriesForUser(String username) {
        ArrayList<Sleep> list = new ArrayList<>();
        Document query = new Document("username", username);
        MongoCursor<Document> cursor = collection.find(query).iterator();
        while (cursor.hasNext()) {
            Document d = cursor.next();
            list.add(Sleep.fromDocument(d));
        }
        return list;
    }

    public double getAverageSleepForUser(String username) {
        ArrayList<Sleep> list = getEntriesForUser(username);
        if (list.isEmpty()) return 0.0;
        double sum = 0;
        for (Sleep e : list) {
            sum += e.getHours();
        }
        return sum / list.size();
    }
}
