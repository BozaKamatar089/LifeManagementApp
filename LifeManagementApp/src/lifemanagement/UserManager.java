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
                .append("password", password)
                .append("theme", "dark"); // default tema
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

    public boolean updateUsername(String oldUsername, String newUsername, String oldPassword) {
        Document query = new Document("username", oldUsername)
                .append("password", oldPassword);
        Document update = new Document("$set", new Document("username", newUsername));
        return collection.updateOne(query, update).getModifiedCount() == 1;
    }

    public boolean updatePassword(String username, String oldPassword, String newPassword) {
        Document query = new Document("username", username)
                .append("password", oldPassword);
        Document update = new Document("$set", new Document("password", newPassword));
        return collection.updateOne(query, update).getModifiedCount() == 1;
    }

    public boolean updateTheme(String username, String theme) {
        Document query = new Document("username", username);
        Document update = new Document("$set", new Document("theme", theme));
        return collection.updateOne(query, update).getModifiedCount() == 1;
    }

    public boolean deleteUser(String username) {
        Document query = new Document("username", username);
        return collection.deleteOne(query).getDeletedCount() == 1;
    }

    public String getUserTheme(String username) {
        Document query = new Document("username", username);
        Document result = collection.find(query).first();
        if (result == null) return "dark";
        String theme = result.getString("theme");
        return (theme != null) ? theme : "dark";
    }

}
