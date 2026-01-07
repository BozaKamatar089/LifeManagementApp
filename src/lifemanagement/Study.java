package lifemanagement;

import org.bson.Document;

public class Study {
    private String username;
    private String date;
    private String subject;
    private double hours;
    private String note;

    public Study(String username, String date, String subject, double hours, String note) {
        this.username = username;
        this.date = date;
        this.subject = subject;
        this.hours = hours;
        this.note = note;
    }

    public String getUsername() { return username; }
    public String getDate() { return date; }
    public String getSubject() { return subject; }
    public double getHours() { return hours; }
    public String getNote() { return note; }

    public Document toDocument() {
        return new Document("username", username)
                .append("date", date)
                .append("subject", subject)
                .append("hours", hours)
                .append("note", note);
    }

    public static Study fromDocument(Document d) {
        String username = d.getString("username");
        String date = d.getString("date");
        String subject = d.getString("subject");
        double hours = d.getDouble("hours");
        String note = d.getString("note");
        return new Study(username, date, subject, hours, note);
    }
}
