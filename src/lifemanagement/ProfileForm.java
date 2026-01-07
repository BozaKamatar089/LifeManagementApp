package lifemanagement;

import javax.swing.*;
import java.awt.*;

public class ProfileForm {
    private JTextField oldUsernameField;
    private JButton changeUsernameButton;
    private JButton changePasswordButton;
    private JComboBox<String> themeCombo;
    private JButton deleteProfileButton;
    private JButton logoutButton;
    private JTextField newUsernameField;
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private JButton saveButton;
    private JLabel themeLabel;
    private JLabel newPasswordLabel;
    private JLabel oldPasswordLabel;
    private JLabel newUsernameLabel;
    private JLabel oldUsernameLabel;
    private JLabel profileLabel;
    private JPanel mainPanel;

    private String currentUsername;
    private UserManager userManager;

    public ProfileForm(String currentUsername) {
        this.currentUsername = currentUsername;
        this.userManager = new UserManager();

        Theme.apply(mainPanel);

        oldUsernameField.setText(currentUsername);

        if (themeCombo.getItemCount() == 0) {
            themeCombo.addItem("dark");
            themeCombo.addItem("light");
        }
        themeCombo.setSelectedItem("dark");

        changeUsernameButton.addActionListener(e -> {
            String oldUsername = oldUsernameField.getText().trim();
            String newUsername = newUsernameField.getText().trim();
            String oldPassword = new String(oldPasswordField.getPassword());

            if (oldUsername.isEmpty() || newUsername.isEmpty() || oldPassword.isEmpty()) {
                JOptionPane.showMessageDialog(mainPanel,
                        "All fields must be filled!",
                        "Erorr",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean ok = userManager.updateUsername(oldUsername, newUsername, oldPassword);
            if (ok) {
                oldUsernameField.setText(newUsername);
                newUsernameField.setText("");
                JOptionPane.showMessageDialog(mainPanel,
                        "Username changed successfully!",
                        "Info",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(mainPanel,
                        "Old username or password is incorrect!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        changePasswordButton.addActionListener(e -> {
            String oldUsername = oldUsernameField.getText().trim();
            String oldPass = new String(oldPasswordField.getPassword());
            String newPass = new String(newPasswordField.getPassword());

            if (oldUsername.isEmpty() || oldPass.isEmpty() || newPass.isEmpty()) {
                JOptionPane.showMessageDialog(mainPanel,
                        "All fields must be filled!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (newPass.length() < 8) {
                JOptionPane.showMessageDialog(mainPanel,
                        "Password must be at least 8 characters!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean ok = userManager.updatePassword(oldUsername, oldPass, newPass);
            if (ok) {
                oldPasswordField.setText("");
                newPasswordField.setText("");
                JOptionPane.showMessageDialog(mainPanel,
                        "Password changed successfully!.",
                        "Info",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(mainPanel,
                        "Old username or password is incorrect!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        saveButton.addActionListener(e -> {
            String selected = (String) themeCombo.getSelectedItem();
            boolean ok = userManager.updateTheme(currentUsername, selected);

            if (ok) {
                ThemeManager.setCurrentTheme(selected);

                JOptionPane.showMessageDialog(mainPanel,
                        "Theme Saved: " + selected,
                        "Info",
                        JOptionPane.INFORMATION_MESSAGE);

                java.awt.Window thisWindow = SwingUtilities.getWindowAncestor(mainPanel);
                if (thisWindow != null) {
                    thisWindow.dispose();
                }

                JFrame frame = new JFrame("Life Management System - Main Menu");
                frame.setContentPane(new MenuForm(currentUsername).getMainPanel());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(600, 400);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(mainPanel,
                        "There was an error while updating the theme.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });


        logoutButton.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(mainPanel);
            if (window != null) {
                window.dispose();
            }

            JFrame frame = new JFrame("Login");
            frame.setContentPane(new LoginForm().getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        deleteProfileButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                    mainPanel,
                    "Are you sure you want to delete this profile?",
                    "Profile Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                boolean ok = userManager.deleteUser(currentUsername);
                if (ok) {
                    JOptionPane.showMessageDialog(mainPanel,
                            "Profile deleted successfully!",
                            "Info",
                            JOptionPane.INFORMATION_MESSAGE);

                    Window window = SwingUtilities.getWindowAncestor(mainPanel);
                    if (window != null) {
                        window.dispose();
                    }

                    JFrame frame = new JFrame("Login");
                    frame.setContentPane(new LoginForm().getMainPanel());
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(400, 300);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(mainPanel,
                            "Profile could not be deleted!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
