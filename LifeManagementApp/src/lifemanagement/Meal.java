package lifemanagement;

import org.bson.Document;

public class Meal {
    private String username;
    private String date;
    private String type;
    private String meal;
    private int calories;

    public Meal(String username, String date, String type, String meal, int calories) {
        this.username = username;
        this.date = date;
        this.type = type;
        this.meal = meal;
        this.calories = calories;
    }

    public String getUsername() { return username; }
    public String getDate() { return date; }
    public String getType() { return type; }
    public String getMeal() { return meal; }
    public int getCalories() { return calories; }

    public Document toDocument() {
        return new Document("username", username)
                .append("date", date)
                .append("type", type)
                .append("meal", meal)
                .append("calories", calories);
    }
}
