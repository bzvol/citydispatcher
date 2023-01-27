package hu.kisspd.citydp;

import javax.swing.*;
import java.awt.Color;

public class Util {
    public static String toHex(Color color) {
        return String.format("#%06X", (color.getRGB() & 0xFFFFFF));
    }

    public static Color fromHex(String hex) {
        return new Color(Integer.parseInt(hex.substring(1), 16));
    }

    public static void showError(String message, Exception e) {
        JOptionPane.showMessageDialog(null,
                message + " " + e.getMessage(),
                "Hiba", JOptionPane.ERROR_MESSAGE);
    }

    public static void showWarning(String message) {
        JOptionPane.showMessageDialog(null,
                message,
                "Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
    }
}
