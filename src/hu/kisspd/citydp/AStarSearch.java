package hu.kisspd.citydp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AStarSearch<T extends AStarSearch.Node<T>> {
    public interface Node<T extends Node<T>> {
        double x();

        double y();

        Set<T> neighbors();

        default double distance(T other) {
            return Math.sqrt(Math.pow(x() - other.x(), 2) + Math.pow(y() - other.y(), 2));
        }
    }

    private final Set<T> nodes;
    private final T start, goal;

    public AStarSearch(Set<T> nodes, T start, T goal) {
        this.nodes = nodes;
        this.start = start;
        this.goal = goal;
    }

    protected double cost(T a, T b) {
        // default: return g + h
        // can be overriden to include other costs, e.g. time
        return a.distance(b) + b.distance(goal);
    }

    public List<T> search() {
        List<T> path = new ArrayList<>();
        T current = start;

        while (current != goal) {
            path.add(current);

            double minCost = Double.MAX_VALUE;
            T next = null;

            for (T neighbor : current.neighbors()) {
                if (path.contains(neighbor)) {
                    continue;
                }

                double cost = cost(current, neighbor);
                if (cost < minCost) {
                    minCost = cost;
                    next = neighbor;
                }
            }

            if (next == null) {
                return null;
            }
            current = next;
        }

        path.add(goal);

        return path;
    }

    public Set<T> getNodes() {
        return nodes;
    }

    public T getStart() {
        return start;
    }

    public T getGoal() {
        return goal;
    }
}
