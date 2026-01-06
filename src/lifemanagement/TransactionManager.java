package lifemanagement;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

public class TransactionManager {

    private final MongoCollection<Document> collection;

    public TransactionManager() {
        MongoDatabase db = MongoDBConnection.getDatabase();
        collection = db.getCollection("transactions");
    }

    public void addTransaction(Transaction t) {
        collection.insertOne(t.toDocument());
    }

    public ArrayList<Transaction> getAllTransactions() {
        ArrayList<Transaction> list = new ArrayList<>();
        MongoCursor<Document> cursor = collection.find().iterator();

        while (cursor.hasNext()) {
            Document d = cursor.next();


            String type = d.getString("type");
            if (type == null) {

                type = d.getString("Vrsta");
            }
            if (type == null) {

                continue;
            }


            String description = d.getString("description");
            if (description == null) {
                description = d.getString("Opis");
            }
            if (description == null) description = "";


            double amount = 0.0;
            Object amountObj = d.get("amount");
            if (amountObj == null) {
                amountObj = d.get("Iznos");
            }

            if (amountObj instanceof Number) {
                amount = ((Number) amountObj).doubleValue();
            } else if (amountObj instanceof String) {
                try {
                    amount = Double.parseDouble((String) amountObj);
                } catch (NumberFormatException e) {

                    continue;
                }
            } else {

                continue;
            }

            list.add(new Transaction(type, amount, description));
        }

        return list;
    }

    public double getTotalIncome() {
        double total = 0;
        for (Transaction t : getAllTransactions()) {
            if (t.getType().equalsIgnoreCase("Income")) {
                total += t.getAmount();
            }
        }
        return total;
    }

    public double getTotalExpense() {
        double total = 0;
        for (Transaction t : getAllTransactions()) {
            if (t.getType().equalsIgnoreCase("Expense")) {
                total += t.getAmount();
            }
        }
        return total;
    }
}
