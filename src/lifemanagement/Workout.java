package lifemanagement;

import org.bson.Document;

public class Workout {
    private String username;
    private String date;
    private String type;
    private int durationMinutes;

    public Workout(String username, String date, String type, int durationMinutes) {
        this.username = username;
        this.date = date;
        this.type = type;
        this.durationMinutes = durationMinutes;
    }

    public String getUsername() { return username; }
    public String getDate() { return date; }
    public String getType() { return type; }
    public int getDurationMinutes() { return durationMinutes; }

    public Document toDocument() {
        return new Document("username", username)
                .append("date", date)
                .append("type", type)
                .append("durationMinutes", durationMinutes);
    }
}
