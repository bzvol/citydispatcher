package hu.kisspd.citydp;

import hu.kisspd.citydp.gui.JMapPanel;
import hu.kisspd.citydp.model.City;
import hu.kisspd.citydp.model.Line;

import java.util.Collection;
import java.util.Optional;

public class Shared {
    private static JMapPanel mapPanel;
    private static boolean isCreatingLine = false;

    public static JMapPanel getMapPanel() {
        return mapPanel;
    }

    public static void setMapPanel(JMapPanel mapPanel) {
        Shared.mapPanel = mapPanel;
    }

    public static boolean isCreatingLine() {
        return isCreatingLine;
    }

    public static void setCreatingLine(boolean isCreatingLine) {
        Shared.isCreatingLine = isCreatingLine;
    }

    @SuppressWarnings("unused")
    public static int[][] getAdjacencyMatrix() {
        Collection<Line> lines = Shared.getMapPanel().getLines().values();

        Optional<Integer> maxIndex_ = Shared.getMapPanel().getCities().keySet().stream().max(Integer::compare);
        if (maxIndex_.isEmpty()) {
            throw new RuntimeException("Cannot get max index of cities.");
        }
        int maxIndex = maxIndex_.get();

        int[][] matrix = new int[maxIndex + 1][maxIndex + 1];

        for (Line line : lines) {
            City cityFrom = line.getCityFrom();
            int indexFrom = cityFrom.getId();
            City cityTo = line.getCityTo();
            int indexTo = cityTo.getId();

            matrix[indexFrom][indexTo] = 1;
        }

        return matrix;
    }
}
