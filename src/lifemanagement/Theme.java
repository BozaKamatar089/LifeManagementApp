package lifemanagement;

import javax.swing.*;
import java.awt.*;

public class Theme {
    private static final Color DARK_BG = new Color(36, 36, 36);
    private static final Color DARK_FG = Color.WHITE;

    private static final Color LIGHT_BG = new Color(225, 217, 209);
    private static final Color LIGHT_FG = Color.BLACK;


    private static final Color DEEP_BLUE_BG = new Color(15, 24, 48);
    private static final Color DEEP_BLUE_FG = new Color(220, 230, 255);

    public static void apply(JPanel panel) {
        String theme = ThemeManager.getCurrentTheme();

        Color bg;
        Color fg;

        if ("light".equalsIgnoreCase(theme)) {
            bg = LIGHT_BG;
            fg = LIGHT_FG;
        } else if ("Deep Blue".equalsIgnoreCase(theme)) {
            bg = DEEP_BLUE_BG;
            fg = DEEP_BLUE_FG;
        } else {
            bg = DARK_BG;
            fg = DARK_FG;
        }

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
