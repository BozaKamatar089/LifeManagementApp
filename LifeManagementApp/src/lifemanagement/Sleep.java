package lifemanagement;

import org.bson.Document;

public class Sleep {
    private String username;
    private String date;
    private double hours;
    private String note;

    public Sleep(String username, String date, double hours, String note) {
        this.username = username;
        this.date = date;
        this.hours = hours;
        this.note = note;
    }

    public String getUsername() { return username; }
    public String getDate() { return date; }
    public double getHours() { return hours; }
    public String getNote() { return note; }

    public Document toDocument() {
        return new Document("username", username)
                .append("date", date)
                .append("hours", hours)
                .append("note", note);
    }

    public static Sleep fromDocument(Document d) {
        String username = d.getString("username");
        String date = d.getString("date");
        double hours = d.getDouble("hours");
        String note = d.getString("note");
        return new Sleep(username, date, hours, note);
    }
}
