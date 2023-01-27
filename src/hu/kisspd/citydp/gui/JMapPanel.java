package hu.kisspd.citydp.gui;

import hu.kisspd.citydp.MySQLConn;
import hu.kisspd.citydp.Util;
import hu.kisspd.citydp.model.City;
import hu.kisspd.citydp.model.CityType;
import hu.kisspd.citydp.model.Line;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList;

public class JMapPanel extends JPanel {
    private final ArrayList<City> cities = new ArrayList<>();
    private final ArrayList<Line> lines = new ArrayList<>();

    public JMapPanel() {
        super();
        loadCities();
        loadLines();
    }

    private void loadCities() {
        try (ResultSet rs = MySQLConn.runQuery("SELECT * FROM settlement")) {
            while (rs != null && rs.next()) {
                City city = new City(rs.getString("name"), rs.getInt("population"));
                city.setId(rs.getInt("id"));
                String type = rs.getString("type");
                city.setType(CityType.fromName(type));
                city.setLocX(rs.getDouble("coord_x"));
                city.setLocY(rs.getDouble("coord_y"));

                cities.add(city);
                revalidate();
                repaint();
            }
        } catch (Exception e) {
            Util.showError("Hiba történt a városok betöltése közben:", e);
        }
    }

    private void loadLines() {
        /*try (ResultSet rs = MySQLConn.runQuery("SELECT * FROM route")) {
            while
        } catch (Exception e) {
            Util.showError("Hiba történt a vonalak betöltése közben:", e);
        }*/ // TODO: implement
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Line line : lines) {
            City cityFrom = line.getCityFrom();
            int cityFromRadius = cityFrom.getType().getSize() / 2;
            int x1 = (int) (cityFrom.getLocX() * this.getWidth()) + cityFromRadius;
            int y1 = (int) (cityFrom.getLocY() * this.getHeight()) + cityFromRadius;

            City cityTo = line.getCityTo();
            int cityToRadius = cityTo.getType().getSize() / 2;
            int x2 = (int) (cityTo.getLocX() * this.getWidth()) + cityToRadius;
            int y2 = (int) (cityTo.getLocY() * this.getHeight()) + cityToRadius;

            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(3));
            g2d.setColor(line.getColor());
            g2d.drawLine(x1, y1, x2, y2);
        }

        g.setColor(Color.BLACK);
        for (City city : cities) {
            int x = (int) (city.getLocX() * this.getWidth());
            int y = (int) (city.getLocY() * this.getHeight());
            int size = city.getType().getSize();

            g.fillOval(x, y, size, size);
        }
    }

    public ArrayList<City> getCities() {
        return new ArrayList<>(cities);
    }

    public ArrayList<Line> getLines() {
        return new ArrayList<>(lines);
    }

    public void clearCities() {
        cities.clear();
        this.revalidate();
        this.repaint();
    }

    public void clearLines() { // TODO: implement menu action
        lines.clear();
        this.revalidate();
        this.repaint();
    }

    public void addCity(City city) {
        cities.add(city);
        this.revalidate();
        this.repaint();
    }

    public void addLine(Line line) {
        lines.add(line);
        this.revalidate();
        this.repaint();
    }

    public void removeCity(City city) {
        cities.remove(city);
        this.revalidate();
        this.repaint();
    }

    public void removeLine(Line line) {
        lines.remove(line);
        this.revalidate();
        this.repaint();
    }

    public City searchNearbyCity(double x, double y) {
        double searchGap = 3.0 / this.getWidth();

        for (City city : cities) {
            double cityX = city.getLocX(), cityY = city.getLocY();
            double distX = Math.abs(cityX - x), distY = Math.abs(cityY - y);
            double citySize = (double) city.getType().getSize() / this.getWidth();

            if (distX <= citySize + searchGap && distY <= citySize + searchGap) {
                return city;
            }
        }

        return null;
    }
}
