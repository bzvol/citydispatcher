package hu.kisspd.citydp;

import javax.swing.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

public class Util {
    public static String toHex(Color color) {
        return String.format("#%06X", (color.getRGB() & 0xFFFFFF));
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

    public static <T> boolean containsArray(ArrayList<T[]> list, T[] item) {
        for (T[] i : list) {
            if (Arrays.equals(i, item)) {
                return true;
            }
        }
        return false;
    }
}
