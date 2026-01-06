package lifemanagment;

import lifemanagement.FinanceTrackerForm;
import lifemanagement.LoginForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Life Management System - Login");
        frame.setContentPane(new LoginForm().getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}