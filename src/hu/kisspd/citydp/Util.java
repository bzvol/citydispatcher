package hu.kisspd.citydp;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Util {
    public static String toHex(Color color) {
        return String.format("#%06X", (color.getRGB() & 0xFFFFFF));
    }

    public static void showError(String message, Exception e) {
        if (e != null) {
            message += " " + e.getMessage();
        }
        JOptionPane.showMessageDialog(null,
                message,
                "Hiba", JOptionPane.ERROR_MESSAGE);
    }

    public static void showDBError(Exception e) {
        JOptionPane.showMessageDialog(null,
                "Adatbázis hiba: " + e.getMessage(),
                "Hiba", JOptionPane.ERROR_MESSAGE);
    }

    public static void showWarning(String message, String title) {
        JOptionPane.showMessageDialog(null,
                message,
                title != null ? title : "Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
    }

    public static <T> boolean containsArray(ArrayList<T[]> list, T[] item) {
        for (T[] i : list) {
            if (Arrays.equals(i, item)) {
                return true;
            }
        }
        return false;
    }

    public static int[] centerText(int x, int y, String text, FontMetrics fm) {
        return new int[]{x - fm.stringWidth(text) / 2, y + fm.getHeight() / 2};
    }
}
