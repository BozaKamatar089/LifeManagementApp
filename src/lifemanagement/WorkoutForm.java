package lifemanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class WorkoutForm {
    private JPanel mainPanel;
    private JButton addButton;
    private JTextField dateField;
    private JTextField durationField;
    private JComboBox<String> typeCombo;
    private JTable workoutTabel;
    private JLabel totalLabel;
    private JLabel workoutLabel;
    private JLabel dateLabel;
    private JLabel typeLabel;
    private JLabel durationLabel;

    private final String currentUsername;
    private final WorkoutManager workoutManager;

    public WorkoutForm(String currentUsername) {
        this.currentUsername = currentUsername;
        this.workoutManager = new WorkoutManager();

        Theme.apply(mainPanel);

        loadDataIntoTable();
        updateSummary();

        addButton.addActionListener(e -> onAdd());
    }

    private void onAdd() {
        String date = dateField.getText().trim();
        String type = (String) typeCombo.getSelectedItem();
        String durationText = durationField.getText().trim();

        if (date.isEmpty() || type == null) {
            JOptionPane.showMessageDialog(mainPanel,
                    "Date and type are required.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int duration = 0;
        if (!"Rest".equalsIgnoreCase(type)) {
            try {
                duration = Integer.parseInt(durationText);
                if (duration < 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainPanel,
                        "Duration must be a non-negative integer.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        Workout w = new Workout(currentUsername, date, type, duration);
        workoutManager.addWorkout(w);

        loadDataIntoTable();
        updateSummary();

        dateField.setText("");
        durationField.setText("");
    }

    private void loadDataIntoTable() {
        ArrayList<Workout> list = workoutManager.getAllWorkoutsForUser(currentUsername);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Date");
        model.addColumn("Type");
        model.addColumn("Duration (min)");

        for (Workout w : list) {
            model.addRow(new Object[]{
                    w.getDate(),
                    w.getType(),
                    w.getDurationMinutes()
            });
        }

        workoutTabel.setModel(model);
    }

    private void updateSummary() {
        int total = workoutManager.getTotalActiveMinutes(currentUsername);
        totalLabel.setText("Total workout minutes (Cardio + Gym): " + total);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
