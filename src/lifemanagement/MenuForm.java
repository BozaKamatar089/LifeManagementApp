package lifemanagement;

import javax.swing.*;

public class MenuForm {
    private JPanel mainPanel;
    private JButton viewProfile;
    private JButton financeApp;
    private JButton myTracker;
    private JLabel lifeManagementSystem;

    private String currentUsername;

    public MenuForm(String currentUsername) {
        this.currentUsername = currentUsername;

        Theme.apply(mainPanel);

        financeApp.addActionListener(e -> {
            JFrame frame = new JFrame("Personal Finance Tracker");
            frame.setContentPane(new FinanceTrackerForm().getMainPanel());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        myTracker.addActionListener(e -> {
            JFrame frame = new JFrame("My Trackers");
            frame.setContentPane(new TrackersMenu(currentUsername).getMainPanel());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        viewProfile.addActionListener(e -> {
            JFrame frame = new JFrame("Profile");
            frame.setContentPane(new ProfileForm(currentUsername).getMainPanel());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(500, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
