package lifemanagement;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

public class MealManager {
    private final MongoCollection<Document> collection;

    public MealManager() {
        MongoDatabase db = MongoDBConnection.getDatabase();
        collection = db.getCollection("meals");
    }

    public void addMeal(Meal m) {
        collection.insertOne(m.toDocument());
    }

    public ArrayList<Meal> getAllMealsForUser(String username) {
        ArrayList<Meal> list = new ArrayList<>();
        Document query = new Document("username", username);
        MongoCursor<Document> cursor = collection.find(query).iterator();

        while (cursor.hasNext()) {
            Document d = cursor.next();
            String date = d.getString("date");
            String type = d.getString("type");
            String meal = d.getString("meal");
            Integer cal = d.getInteger("calories", 0);
            list.add(new Meal(username, date, type, meal, cal));
        }
        return list;
    }

    public int getTotalCalories(String username) {
        int total = 0;
        for (Meal m : getAllMealsForUser(username)) {
            total += m.getCalories();
        }
        return total;
    }
}
