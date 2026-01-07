package lifemanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class StudyTrackerForm {
    private JPanel mainPanel;
    private JTextField dateField;
    private JTextField subjectField;
    private JTextField hoursField;
    private JTextField noteField;
    private JLabel dateLabel;
    private JLabel subjectLabel;
    private JLabel hoursLabel;
    private JLabel noteLabel;
    private JLabel studyLabel;
    private JTable studyTable;
    private JButton addButton;
    private JLabel totalLabel;

    private String currentUsername;
    private StudyManager studyManager;

    public StudyTrackerForm(String currentUsername) {
        this.currentUsername = currentUsername;
        this.studyManager = new StudyManager();

        Theme.apply(mainPanel);

        loadDataIntoTable();
        updateTotalLabel();

        addButton.addActionListener(e -> {
            String date = dateField.getText().trim();
            String subject = subjectField.getText().trim();
            String hoursText = hoursField.getText().trim();
            String note = noteField.getText().trim();

            if (date.isEmpty() || subject.isEmpty() || hoursText.isEmpty()) {
                JOptionPane.showMessageDialog(mainPanel,
                        "Date, subject and hours must be filled.",
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

            Study entry = new Study(currentUsername, date, subject, hours, note);
            studyManager.addStudyEntry(entry);

            loadDataIntoTable();
            updateTotalLabel();

            dateField.setText("");
            subjectField.setText("");
            hoursField.setText("");
            noteField.setText("");
        });
    }

    private void loadDataIntoTable() {
        ArrayList<Study> list = studyManager.getEntriesForUser(currentUsername);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Date");
        model.addColumn("Subject");
        model.addColumn("Hours");
        model.addColumn("Note");

        for (Study e : list) {
            model.addRow(new Object[]{e.getDate(), e.getSubject(), e.getHours(), e.getNote()});
        }

        studyTable.setModel(model);
    }

    private void updateTotalLabel() {
        double total = studyManager.getTotalStudyHoursForUser(currentUsername);
        if (totalLabel != null) {
            totalLabel.setText("Total study hours: " + String.format("%.2f", total));
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
