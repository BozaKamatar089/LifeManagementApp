package lifemanagement;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class UserManager {
    private final MongoCollection<Document> collection;

    public UserManager() {
        MongoDatabase db = MongoDBConnection.getDatabase();
        collection = db.getCollection("users");
    }

    public boolean usernameExists(String username) {
        Document query = new Document("username", username);
        Document result = collection.find(query).first();
        return result != null;
    }

    public boolean registerUser(String username, String password) {
        if (usernameExists(username)) {
            return false;
        }
        Document d = new Document("username", username)
                .append("password", password);
        collection.insertOne(d);
        return true;
    }

    public int loginUser(String username, String password) {
        Document query = new Document("username", username);
        Document result = collection.find(query).first();

        if (result == null) {
            return -1;
        }

        String storedPassword = result.getString("password");
        if (!storedPassword.equals(password)) {
            return 0;
        }

        return 1;
    }
}
