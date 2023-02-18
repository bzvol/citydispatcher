package hu.kisspd.citydp.model;

import com.google.gson.JsonObject;
import hu.kisspd.citydp.AStarSearch;
import hu.kisspd.citydp.Shared;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.stream.Collectors;

public class City implements AStarSearch.Node<City> {
    private int id;
    private final String name;
    private final int population;
    private CityType type;
    private double locX, locY;

    private City(int id, String name, int population, CityType type, double locX, double locY) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.type = type;
        this.locX = locX;
        this.locY = locY;
    }

    public City(String name, int population) {
        this.name = name;
        this.population = population;
        this.type = CityType.CITY;
    }

    public static City fromJson(JsonObject json) {
        int id = json.get("id").getAsInt();
        String name = json.get("name").getAsString();
        int population = json.get("population").getAsInt();
        CityType type = CityType.fromName(json.get("type").getAsString());
        double locX = json.get("coord_x").getAsDouble();
        double locY = json.get("coord_y").getAsDouble();
        return new City(id, name, population, type, locX, locY);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("id", id);
        json.addProperty("name", name);
        json.addProperty("population", population);
        json.addProperty("type", type.getName());
        json.addProperty("coord_x", locX);
        json.addProperty("coord_y", locY);
        return json;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public CityType getType() {
        return type;
    }

    public void setType(CityType type) {
        this.type = type;
    }

    public double getLocX() {
        return locX;
    }

    public double getLocY() {
        return locY;
    }

    public void setLocX(double locX) {
        this.locX = locX;
    }

    public void setLocY(double locY) {
        this.locY = locY;
    }

    public String toString() {
        return name + " (" + population + " ppl)";
    }

    public static City fromDialog() {
        JPanel form = new JPanel();
        form.setLayout(new GridLayout(2, 2));

        form.add(new JLabel("Név:"));
        JTextField nameField = new JTextField();
        form.add(nameField);
        form.add(new JLabel("Lakosság:"));
        JTextField populationField = new JTextField();
        form.add(populationField);

        int option = JOptionPane.showConfirmDialog(
                null, form, "Település létrehozása", JOptionPane.OK_CANCEL_OPTION);

        String name = nameField.getText();
        int population = -1;
        if (option == JOptionPane.OK_OPTION) {
            String populationInput = populationField.getText();
            try {
                population = Integer.parseInt(populationInput);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "A lakosság mezőben csak egész szám lehet!", "Hiba", JOptionPane.WARNING_MESSAGE);
            }

            if (name != null && !name.isBlank() && population >= 0) {
                return new City(name, population);
            }

            JOptionPane.showMessageDialog(null,
                    "A név és a lakosság mezők nem lehetnek üresek!", "Hiba", JOptionPane.WARNING_MESSAGE);
        }

        return null;
    }

    public static City fromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int population = rs.getInt("population");
        CityType type = CityType.fromName(rs.getString("type"));
        double locX = rs.getDouble("coord_x");
        double locY = rs.getDouble("coord_y");
        return new City(id, name, population, type, locX, locY);
    }

    @Override
    public double x() {
        return locX;
    }

    @Override
    public double y() {
        return locY;
    }

    @Override
    public Set<City> neighbors() {
        return Shared.getTemporaryLineSet().stream()
                .filter(line -> line.getCityFrom() == this)
                .map(Line::getCityTo)
                .collect(Collectors.toSet());
    }
}