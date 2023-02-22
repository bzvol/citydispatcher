package hu.kisspd.citydp.gui;

import hu.kisspd.citydp.MySQLConn;
import hu.kisspd.citydp.Shared;
import hu.kisspd.citydp.Util;
import hu.kisspd.citydp.model.City;
import hu.kisspd.citydp.model.Line;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("DuplicatedCode")
public class CityContextMenu extends JPopupMenu {
    private final City city;
    private final JMapPanel mapPanel;

    public CityContextMenu(City city) {
        this.city = city;
        this.mapPanel = Shared.getMapPanel();

        JMenuItem nameItem = new JMenuItem(city.getName());
        nameItem.setEnabled(false);
        add(nameItem);
        addSeparator();

        JMenuItem statisticsBtn = new JMenuItem("Statisztika");
        JMenuItem addLineBtn = new JMenuItem("Vonal létrehozása...");
        JMenuItem deleteBtn = new JMenuItem("Város törlése");
        JMenuItem shortestPathBtn = new JMenuItem("Legrövidebb út...");

        // statisticsBtn.addActionListener(evt -> city.showStatistics());
        addLineBtn.addActionListener(evt -> addLine());
        deleteBtn.addActionListener(evt -> deleteCity());
        shortestPathBtn.addActionListener(evt -> shortestPathAction());

        add(statisticsBtn);
        add(addLineBtn);
        add(deleteBtn);
        add(shortestPathBtn);
    }

    private void addLine() {
        Shared.setCanInteractMap(true);

        Line line = Line.fromDialog(this.city);
        if (line == null) {
            Shared.setCanInteractMap(false);
            return;
        }

        Shared.getInfoLabel().setText("Válaszd ki a végállomást!");

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
                        line.getName(), line.getVehicleType().getName(), Util.hexString(line.getColor()),
                        line.getCityFrom().getId(), line.getCityTo().getId());

                int id;
                if (MySQLConn.runStatement(stmt) && (id = MySQLConn.lastInsertedId()) != -1) {
                    line.setId(id);
                    mapPanel.addLine(line);
                }

                mapPanel.removeMouseListener(this);
                Shared.setCanInteractMap(false);
                Shared.getInfoLabel().setText("");
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

    private void shortestPathAction() {
        City cityFrom = city;

        Shared.setCanInteractMap(true);
        Shared.getInfoLabel().setText("Válaszd ki a végpontot!");
        mapPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                double clickX = (double) e.getX() / (double) mapPanel.getWidth(),
                        clickY = (double) e.getY() / (double) mapPanel.getHeight();

                City cityTo = mapPanel.searchNearbyCity(clickX, clickY);
                if (cityTo == null || cityTo.getId() == cityFrom.getId()) {
                    return;
                }

                var path = Shared.shortestPath(cityFrom, cityTo);

                if (path == null) {
                    Util.showWarning("Nem található útvonal a két város között!", null);
                } else {
                    mapPanel.setAStarPath(path);
                }

                mapPanel.removeMouseListener(this);
                Shared.setCanInteractMap(false);
                Shared.getInfoLabel().setText("");
            }
        });
    }
}
