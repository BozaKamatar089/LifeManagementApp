package lifemanagement;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

public class WorkoutManager {
    private final MongoCollection<Document> collection;

    public WorkoutManager() {
        MongoDatabase db = MongoDBConnection.getDatabase();
        collection = db.getCollection("workouts");
    }

    public void addWorkout(Workout w) {
        collection.insertOne(w.toDocument());
    }

    public ArrayList<Workout> getAllWorkoutsForUser(String username) {
        ArrayList<Workout> list = new ArrayList<>();
        Document query = new Document("username", username);
        MongoCursor<Document> cursor = collection.find(query).iterator();

        while (cursor.hasNext()) {
            Document d = cursor.next();
            String date = d.getString("date");
            String type = d.getString("type");
            Integer dur = d.getInteger("durationMinutes", 0);
            list.add(new Workout(username, date, type, dur));
        }
        return list;
    }

    public int getTotalActiveMinutes(String username) {
        int total = 0;
        for (Workout w : getAllWorkoutsForUser(username)) {
            if (!"Rest".equalsIgnoreCase(w.getType())) {
                total += w.getDurationMinutes();
            }
        }
        return total;
    }
}
