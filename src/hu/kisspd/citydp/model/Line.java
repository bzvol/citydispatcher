package hu.kisspd.citydp.model;

import javax.swing.*;
import java.awt.*;

public class Line {
    private int id;
    private final String name;
    private Color color;
    private final VehicleType vehicleType;
    private final City from;
    private City to;

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
}
