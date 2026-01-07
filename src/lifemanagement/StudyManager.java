package lifemanagement;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

public class StudyManager {
    private final MongoCollection<Document> collection;

    public StudyManager() {
        MongoDatabase db = MongoDBConnection.getDatabase();
        collection = db.getCollection("study_entries");
    }

    public void addStudyEntry(Study entry) {
        collection.insertOne(entry.toDocument());
    }

    public ArrayList<Study> getEntriesForUser(String username) {
        ArrayList<Study> list = new ArrayList<>();
        Document query = new Document("username", username);
        MongoCursor<Document> cursor = collection.find(query).iterator();
        while (cursor.hasNext()) {
            Document d = cursor.next();
            list.add(Study.fromDocument(d));
        }
        return list;
    }

    public double getTotalStudyHoursForUser(String username) {
        ArrayList<Study> list = getEntriesForUser(username);
        double sum = 0;
        for (Study e : list) {
            sum += e.getHours();
        }
        return sum;
    }
}
