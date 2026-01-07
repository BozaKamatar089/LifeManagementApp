package lifemanagement;

import javax.swing.*;

public class LoginForm {
    private JPanel mainPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    private UserManager userManager;

    public LoginForm() {
        userManager = new UserManager();

        Theme.apply(mainPanel);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in both fields");
                return;
            }

            if (password.length() < 8) {
                JOptionPane.showMessageDialog(null, "Password must be at least 8 characters");
                return;
            }

            int result = userManager.loginUser(username, password);

            if (result == -1) {
                JOptionPane.showMessageDialog(
                        null,
                        "Username does not exist. Try to register first"
                );
            } else if (result == 0) {
                JOptionPane.showMessageDialog(null, "Invalid password");
            } else {
                String theme = userManager.getUserTheme(username);
                ThemeManager.setCurrentTheme(theme);

                JFrame frame = new JFrame("Life Management System - Main Menu");
                frame.setContentPane(new MenuForm(username).getMainPanel());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(600, 400);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                java.awt.Window window = SwingUtilities.getWindowAncestor(mainPanel);
                if (window != null) {
                    window.dispose();
                }
            }
        });

        registerButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in both fields");
                return;
            }

            if (password.length() < 8) {
                JOptionPane.showMessageDialog(null, "Password must be at least 8 characters");
                return;
            }

            boolean ok = userManager.registerUser(username, password);
            if (ok) {
                JOptionPane.showMessageDialog(null, "Registration Successful. You can now login");
                passwordField.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Username already exists");
            }
        });

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
