package hu.kisspd.citydp.gui;

import hu.kisspd.citydp.GameManager;
import hu.kisspd.citydp.MySQLConn;
import hu.kisspd.citydp.Util;
import hu.kisspd.citydp.model.City;
import hu.kisspd.citydp.model.Line;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CityContextMenu extends JPopupMenu {
    private final City city;
    private final JMapPanel mapPanel;

    public CityContextMenu(City city) {
        this.city = city;
        this.mapPanel = GameManager.getMapPanel();

        JMenuItem statisticsBtn = new JMenuItem("Statisztika");
        JMenuItem addLineBtn = new JMenuItem("Vonal létrehozása...");
        JMenuItem deleteBtn = new JMenuItem("Város törlése");

        // statisticsBtn.addActionListener(evt -> city.showStatistics());
        addLineBtn.addActionListener(evt -> addLine());
        deleteBtn.addActionListener(evt -> deleteCity());

        add(statisticsBtn);
        add(addLineBtn);
        add(deleteBtn);
    }

    private void addLine() {
        GameManager.setCreatingLine(true);

        Line line = Line.fromDialog(this.city);
        if (line == null) {
            GameManager.setCreatingLine(false);
            return;
        }

        // Wait for the user to select the second city
        mapPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                double clickX = (double) evt.getX() / (double) mapPanel.getWidth(),
                        clickY = (double) evt.getY() / (double) mapPanel.getHeight();

                City cityTo = mapPanel.searchNearbyCity(clickX, clickY);
                if (cityTo == null || cityTo.getId() == city.getId()) {
                    return;
                }

                line.setCityTo(cityTo);

                String stmt = String.format("INSERT INTO route (name, vehicle_type, color, city_from, city_to) " +
                                "VALUES ('%s', '%s', '%s', %d, %d)",
                        line.getName(), line.getVehicleType().getName(), Util.toHex(line.getColor()),
                        line.getCityFrom().getId(), line.getCityTo().getId());

                int id;
                if (MySQLConn.runStatement(stmt) && (id = MySQLConn.lastInsertedId()) != -1) {
                    line.setId(id);
                    mapPanel.addLine(line);
                }

                mapPanel.removeMouseListener(this);
                GameManager.setCreatingLine(false);
            }
        });
    }

    private void deleteCity() {
        int confirm = JOptionPane.showConfirmDialog(null,
                "Biztosan törölni szeretnéd a várost?",
                "Város törlése", JOptionPane.YES_NO_OPTION);

        String stmt = String.format("DELETE FROM settlement WHERE name = '%s'", city.getName());
        if (confirm == JOptionPane.YES_OPTION &&
                MySQLConn.runStatement(stmt)) {
             mapPanel.removeCity(city);
        }
    }
}