package lifemanagement;

import javax.swing.*;
import java.awt.*;

public class Theme {
    private static final Color DARK_BG = new Color(36, 36, 36);
    private static final Color DARK_FG = Color.WHITE;


    private static final Color LIGHT_BG = new Color(225, 217, 209);
    private static final Color LIGHT_FG = Color.BLACK;

    public static void apply(JPanel panel) {
        String theme = ThemeManager.getCurrentTheme();
        boolean dark = !"light".equalsIgnoreCase(theme);

        Color bg = dark ? DARK_BG : LIGHT_BG;
        Color fg = dark ? DARK_FG : LIGHT_FG;

        applyToComponent(panel, bg, fg);
    }

    private static void applyToComponent(Component c, Color bg, Color fg) {
        if (c instanceof JComponent) {
            c.setBackground(bg);
            c.setForeground(fg);
        }

        if (c instanceof Container) {
            for (Component child : ((Container) c).getComponents()) {
                applyToComponent(child, bg, fg);
            }
        }
    }
}
