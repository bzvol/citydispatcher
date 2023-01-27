package hu.kisspd.citydp;

import hu.kisspd.citydp.gui.JMapPanel;

public class GameManager {
    private static JMapPanel mapPanel;
    private static boolean isCreatingLine = false;

    public static JMapPanel getMapPanel() {
        return mapPanel;
    }

    public static void setMapPanel(JMapPanel mapPanel) {
        GameManager.mapPanel = mapPanel;
    }

    public static boolean isCreatingLine() {
        return isCreatingLine;
    }

    public static void setCreatingLine(boolean isCreatingLine) {
        GameManager.isCreatingLine = isCreatingLine;
    }
}
