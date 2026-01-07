package lifemanagement;

public class ThemeManager {
    private static String currentTheme = "dark";

    public static String getCurrentTheme() {
        return currentTheme;
    }

    public static void setCurrentTheme(String theme) {
        if (theme != null) {
            currentTheme = theme;
        }
    }
}
