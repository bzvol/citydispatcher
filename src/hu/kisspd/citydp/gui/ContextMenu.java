package hu.kisspd.citydp.gui;

import hu.kisspd.citydp.JsonSaver;
import hu.kisspd.citydp.Shared;
import hu.kisspd.citydp.MySQLConn;
import hu.kisspd.citydp.model.City;
import hu.kisspd.citydp.model.CityType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Locale;

class ContextMenu extends JPopupMenu {
    private final MouseEvent mouseEvt;
    private final JMapPanel mapPanel;

    public ContextMenu(MouseEvent mouseEvt) {
        this.mouseEvt = mouseEvt;
        this.mapPanel = Shared.getMapPanel();

        for (CityType cityType : CityType.values()) {
            JMenuItem item = new JMenuItem(String.format("%s hozzáadása...", cityType));
            item.addActionListener(evt -> btnAction(cityType));
            add(item);
        }

        JMenuItem clearCities = new JMenuItem("Városok törlése");
        clearCities.addActionListener(this::clearCitiesAction);
        add(clearCities);

        JMenuItem connectedTest = new JMenuItem("Összefüggőség teszt");
        connectedTest.addActionListener(this::connectedTestAction);
        add(connectedTest);

        JMenuItem saveData = new JMenuItem("Adatok mentése");
        saveData.addActionListener(this::saveDataAction);
        add(saveData);
    }

    private void btnAction(CityType cityType) {
        City city = City.fromDialog();
        if (city == null) {
            return;
        }

        city.setType(cityType);
        double locX = (double) mouseEvt.getX() / (double) mapPanel.getWidth(),
                locY = (double) mouseEvt.getY() / (double) mapPanel.getHeight();
        city.setLocX(locX);
        city.setLocY(locY);

        String stmt = String.format(Locale.US, "INSERT INTO settlement " +
                        "(name, type, population, coord_x, coord_y) " +
                        "VALUES ('%s', '%s', %d, %f, %f)",
                city.getName(), city.getType().getName(), city.getPopulation(),
                city.getLocX(), city.getLocY());
        int id;
        if (MySQLConn.runStatement(stmt) && (id = MySQLConn.lastInsertedId()) != -1) {
            city.setId(id);
            mapPanel.addCity(city);
        }
    }

    private void clearCitiesAction(ActionEvent evt) {
        int confirm = JOptionPane.showConfirmDialog(null,
                "Biztosan törölni akarod a városokat?",
                "Városok törlése", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION
                && MySQLConn.runStatement("DELETE FROM route")
                && MySQLConn.runStatement("DELETE FROM settlement")) {
            mapPanel.clearCities();
        }
    }

    private void connectedTestAction(ActionEvent evt) {
        boolean isFullConnected = Shared.connectedGraphTest();
        JOptionPane.showMessageDialog(null,
                String.format("A városok gráfja %sösszefüggő.", isFullConnected ? "" : "nem "),
                "Összefüggőség teszt", JOptionPane.INFORMATION_MESSAGE);
    }

    public void saveDataAction(ActionEvent evt) {
    	JFileChooser folderChooser = new JFileChooser();
        folderChooser.setDialogTitle("Adatok mentése");
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = folderChooser.showSaveDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        String folderPath = folderChooser.getSelectedFile().getAbsolutePath();
        if (!folderPath.endsWith("/")) {
            folderPath += "/";
        }

        String fileName = JOptionPane.showInputDialog(
                null,
                "Fájl neve:", "Adatok mentése",
                JOptionPane.QUESTION_MESSAGE);

        if (fileName == null || fileName.isEmpty()) {
            return;
        }
        fileName = fileName.trim() + ".json";

        String filePath = folderPath + fileName;
        JsonSaver.saveData(filePath);
    }
}
