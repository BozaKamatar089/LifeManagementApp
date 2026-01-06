package lifemanagement;

import javax.swing.*;

public class MenuForm {
    private JPanel mainPanel;
    private JButton viewProfile;
    private JButton financeApp;
    private JButton myTracker;
    private JLabel lifeManagementSystem;

    public MenuForm() {
        financeApp.addActionListener(e -> {
            JFrame frame = new JFrame("Personal Finance Tracker");
            frame.setContentPane(new FinanceTrackerForm().getMainPanel());
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