package lifemanagment;

import lifemanagement.FinanceTrackerForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Personal Finance Tracker");
        frame.setContentPane(new FinanceTrackerForm().getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 450);
        frame.setVisible(true);
    }
}
