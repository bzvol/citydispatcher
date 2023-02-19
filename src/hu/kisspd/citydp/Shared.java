package hu.kisspd.citydp;

import hu.kisspd.citydp.gui.JMapPanel;
import hu.kisspd.citydp.model.City;
import hu.kisspd.citydp.model.Line;

import java.util.*;

public class Shared {
    private static JMapPanel mapPanel;
    private static boolean isCreatingLine = false;

    private static Set<City> temporaryCitySet = new HashSet<>();
    private static Set<Line> temporaryLineSet = new HashSet<>();

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

    public static Set<City> getTemporaryCitySet() {
        return temporaryCitySet;
    }

    public static void setTemporaryCitySet(Set<City> temporaryCitySet) {
        Shared.temporaryCitySet = temporaryCitySet;
    }

    public static Set<Line> getTemporaryLineSet() {
        return temporaryLineSet;
    }

    public static void setTemporaryLineSet(Set<Line> temporaryLineSet) {
        Shared.temporaryLineSet = temporaryLineSet;
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

    public static boolean connectedGraphTest() { // Latitude travelsal
        var cities = Shared.getMapPanel().getCities();
        var lines = Shared.getMapPanel().getLines();
        var indexes = cities.keySet().toArray(new Integer[0]);

        int expectedSize = indexes.length;
        var seen = new HashSet<Integer>();

        for (int idx : indexes) {
            var linesForCity = lines
                    .values().stream()
                    .filter(l -> l.getCityFrom().getId() == idx || l.getCityTo().getId() == idx)
                    .toArray(Line[]::new);
            if (linesForCity.length == 0) {
                return false;
            }

            for (Line line : linesForCity) {
                seen.add(line.getCityFrom().getId());
                seen.add(line.getCityTo().getId());
            }
        }

        return seen.size() == expectedSize;
    }

    public static List<City> shortestPath(City start, City goal) {
        setTemporaryLineSet(new HashSet<>(getMapPanel().getLines().values()));

        var nodes = new HashSet<>(getMapPanel().getCities().values());
        var pathfinder = new AStarSearch<>(nodes, start, goal) {
            @Override
            protected double cost(City a, City b) { // time instead of distance
                return (a.distance(b) + b.distance(this.getGoal())) * 1000 / getLine(a, b).getVehicleType().getSpeed();
            }

            private Line getLine(City a, City b) {
                return getTemporaryLineSet().stream()
                        .filter(l -> (l.getCityFrom() == a && l.getCityTo() == b))
                        .max(Comparator.comparingInt(l -> l.getVehicleType().getSpeed()))
                        .orElseThrow();
            }
        };

        return pathfinder.search();
    }

    public static void syncDBToMap() {
        // TODO: implement
        // remove all data from map, and load from DB
    }

    public static void syncMapToDB() {
        // TODO: implement
        // remove all data from DB, and upload from map
    }
}
