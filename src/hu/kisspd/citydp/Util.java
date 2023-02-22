package hu.kisspd.citydp;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Arrays;

public class Util {
    public static String hexString(Color color) {
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
        return list.stream().anyMatch(i -> Arrays.equals(i, item));
    }

    public static int[] centerText(int x, int y, String text, FontMetrics fm) {
        return new int[]{x - fm.stringWidth(text) / 2, y + fm.getHeight() / 2};

        // TODO: respect font transform!!!
    }

    public static int[] centerText(int x, int y, String text, FontMetrics fm, AffineTransform transform) {
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();

        Shape shape = transform.createTransformedShape(fm.getStringBounds(text, null));
        Rectangle bounds = shape.getBounds();

        int centerX = x - bounds.width / 2 + textWidth / 2;
        int centerY = y + bounds.height / 2 - textHeight / 2;
        return new int[]{centerX, centerY};
    }

    public static int[] lerpPoint(int[] a, int[] b, float t) {
        return new int[]{(int) (a[0] + (b[0] - a[0]) * t), (int) (a[1] + (b[1] - a[1]) * t)};
    }

    public static int[] quadLerpPoint(int[] p1, int[] controlPoint, int[] p2, float t) {
        int[] lerp1 = lerpPoint(p1, controlPoint, t);
        int[] lerp2 = lerpPoint(controlPoint, p2, t);
        return lerpPoint(lerp1, lerp2, t);
    }
}
