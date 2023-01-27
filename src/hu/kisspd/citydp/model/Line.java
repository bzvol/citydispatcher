package hu.kisspd.citydp.model;

import hu.kisspd.citydp.Shared;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class Line {
    private int id;
    private final String name;
    private Color color;
    private final VehicleType vehicleType;
    private final City from;
    private City to;

    private Line(int id, String name, Color color, VehicleType vehicleType, City from, City to) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.vehicleType = vehicleType;
        this.from = from;
        this.to = to;
    }

    public Line(String name, VehicleType vehicleType, Color color, City from) {
        this.name = name;
        this.vehicleType = vehicleType;
        this.color = color;
        this.from = from;
    }

    public static Line fromDialog(City cityFrom) {
        JPanel form = new JPanel();
        form.setLayout(new GridLayout(3, 2));

        form.add(new JLabel("Név:"));
        JTextField nameField = new JTextField();
        form.add(nameField);

        form.add(new JLabel("Jármű típusa:"));
        VehicleType[] availableTypes = cityFrom.getType().getAvailableVehicleTypes();
        JComboBox<VehicleType> vehicleTypeBox = new JComboBox<>(availableTypes);
        form.add(vehicleTypeBox);

        form.add(new JLabel("Szín:"));
        JButton colorBtn = new JButton("Válassz színt...");
        colorBtn.addActionListener(e -> {
            Color color = JColorChooser.showDialog(null, "Szín választása", Color.BLUE);
            colorBtn.setBackground(color);
        });
        form.add(colorBtn);

        if (JOptionPane.showConfirmDialog(null, form, "Vonal létrehozása",
                JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) {
            return null;
        }

        String name = nameField.getText();
        VehicleType vehicleType = (VehicleType) vehicleTypeBox.getSelectedItem();
        Color color = colorBtn.getBackground();

        if (name != null && !name.isBlank() && vehicleType != null && color != null) {
            return new Line(name, vehicleType, color, cityFrom);
        }

        JOptionPane.showMessageDialog(null,
                "A név mező nem lehet üres!", "Hiba", JOptionPane.WARNING_MESSAGE);

        return null;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public City getCityFrom() {
        return from;
    }

    public City getCityTo() {
        return to;
    }

    public void setCityTo(City to) {
        this.to = to;
    }

    public static Line fromResultSet(ResultSet rs, Map<Integer, City> cities) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        Color color = Color.decode(rs.getString("color"));
        VehicleType vehicleType = VehicleType.fromName(rs.getString("vehicle_type"));
        City cityFrom = cities.get(rs.getInt("city_from"));
        City cityTo = cities.get(rs.getInt("city_to"));
        return new Line(id, name, color, vehicleType, cityFrom, cityTo);
    }
}
