package lifemanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class MealForm {
    private JPanel mainPanel;
    private JLabel dateLabel;
    private JTextField dateField;
    private JLabel typeLabel;
    private JComboBox<String> typeCombo;
    private JLabel mealLabel;
    private JTextField mealField;
    private JLabel caloriesLabel;
    private JTextField caloriesField;
    private JButton addButton;
    private JLabel mealTitelLabel;
    private JTable mealTabel;
    private JLabel totalCaloriesLabel;

    private final String currentUsername;
    private final MealManager mealManager;

    public MealForm(String currentUsername) {
        this.currentUsername = currentUsername;
        this.mealManager = new MealManager();

        Theme.apply(mainPanel);

        loadDataIntoTable();
        updateSummary();

        addButton.addActionListener(e -> onAdd());
    }

    private void onAdd() {
        String date = dateField.getText().trim();
        String type = (String) typeCombo.getSelectedItem();
        String meal = mealField.getText().trim();
        String caloriesText = caloriesField.getText().trim();

        if (date.isEmpty() || type == null || meal.isEmpty()) {
            JOptionPane.showMessageDialog(mainPanel,
                    "Date, type and meal name are required.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int calories;
        try {
            calories = Integer.parseInt(caloriesText);
            if (calories < 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(mainPanel,
                    "Calories must be a non-negative integer.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Meal m = new Meal(currentUsername, date, type, meal, calories);
        mealManager.addMeal(m);

        loadDataIntoTable();
        updateSummary();

        mealField.setText("");
        caloriesField.setText("");
    }

    private void loadDataIntoTable() {
        ArrayList<Meal> list = mealManager.getAllMealsForUser(currentUsername);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Date");
        model.addColumn("Type");
        model.addColumn("Meal");
        model.addColumn("Calories");

        for (Meal m : list) {
            model.addRow(new Object[]{
                    m.getDate(),
                    m.getType(),
                    m.getMeal(),
                    m.getCalories()
            });
        }

        mealTabel.setModel(model);
    }

    private void updateSummary() {
        int total = mealManager.getTotalCalories(currentUsername);
        totalCaloriesLabel.setText("Total calories : " + total);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
