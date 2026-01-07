package lifemanagement;

import javax.swing.*;

public class TrackersMenu {
    private JButton sleepTrackerButton;
    private JButton studyTrackerButton;
    private JButton mealPlannerButton;
    private JButton workoutTrackerButton;
    private JPanel mainPanel;

    private String currentUsername;

    public TrackersMenu(String currentUsername) {
        this.currentUsername = currentUsername;

        Theme.apply(mainPanel);

        sleepTrackerButton.addActionListener(e -> {
            JFrame frame = new JFrame("Sleep Tracker");
            frame.setContentPane(new SleepTrackerForm(currentUsername).getMainPanel());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        studyTrackerButton.addActionListener(e -> {
            JFrame frame = new JFrame("Study Tracker");
            frame.setContentPane(new StudyTrackerForm(currentUsername).getMainPanel());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
