package lifemanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class SleepTrackerForm {
    private JLabel sleepLabel;
    private JLabel dateLabel;
    private JTextField dateField;
    private JTextField hourField;
    private JPanel mainPanel;
    private JLabel hourLabel;
    private JLabel noteLabel;
    private JTextField noteField;
    private JButton addButton;
    private JTable sleepTabel;
    private JLabel averageLabel;

    private String currentUsername;
    private SleepManager sleepManager;

    public SleepTrackerForm(String currentUsername) {
        this.currentUsername = currentUsername;
        this.sleepManager = new SleepManager();

        Theme.apply(mainPanel);

        loadDataIntoTable();
        updateAverageLabel();

        addButton.addActionListener(e -> {
            String date = dateField.getText().trim();
            String hoursText = hourField.getText().trim();
            String note = noteField.getText().trim();

            if (date.isEmpty() || hoursText.isEmpty()) {
                JOptionPane.showMessageDialog(mainPanel,
                        "Date and hours must be filled.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            double hours;
            try {
                hours = Double.parseDouble(hoursText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainPanel,
                        "Hours must be a number.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Sleep entry = new Sleep(currentUsername, date, hours, note);
            sleepManager.addSleepEntry(entry);

            loadDataIntoTable();
            updateAverageLabel();

            dateField.setText("");
            hourField.setText("");
            noteField.setText("");
        });
    }

    private void loadDataIntoTable() {
        ArrayList<Sleep> list = sleepManager.getEntriesForUser(currentUsername);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Date");
        model.addColumn("Hours");
        model.addColumn("Note");

        for (Sleep e : list) {
            model.addRow(new Object[]{e.getDate(), e.getHours(), e.getNote()});
        }

        sleepTabel.setModel(model);
    }

    private void updateAverageLabel() {
        double avg = sleepManager.getAverageSleepForUser(currentUsername);
        if (averageLabel != null) {
            averageLabel.setText("Average sleep: " + String.format("%.2f", avg) + " h");
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
