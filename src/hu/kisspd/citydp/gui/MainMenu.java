/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.kisspd.citydp.gui;

import hu.kisspd.citydp.Shared;
import hu.kisspd.citydp.MySQLConn;
import hu.kisspd.citydp.model.City;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainMenu extends JFrame {

    public MainMenu() {
        initComponents();

        Shared.setMapPanel(jMapPanel);

        /* for testing
        City testCity = new City("Budapest", 100000);
        testCity.setLocX(0.5);
        testCity.setLocY(0.5);
        jMapPanel.addCity(testCity);
        end of testing */

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                super.windowClosing(evt);
                try {
                    MySQLConn.disconnectDB();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,
                            "Nem sikerült a kapcsolat bezárása!" + e.getMessage(),
                            "Hiba", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jMapPanel = new JMapPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMapPanel.setBackground(new java.awt.Color(153, 255, 255));
        jMapPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jMapPanelClickAction(evt);
            }
        });

        GroupLayout jMapPanelLayout = new GroupLayout(jMapPanel);
        jMapPanel.setLayout(jMapPanelLayout);
        jMapPanelLayout.setHorizontalGroup(
                jMapPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 959, Short.MAX_VALUE)
        );
        jMapPanelLayout.setVerticalGroup(
                jMapPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 413, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jMapPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jMapPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(78, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void jMapPanelClickAction(MouseEvent evt) {
        if (Shared.isCreatingLine() || !SwingUtilities.isRightMouseButton(evt)) {
            return;
        }

        double clickX = (double) evt.getX() / jMapPanel.getWidth(),
                clickY = (double) evt.getY() / jMapPanel.getHeight();

        City nearbyCity = jMapPanel.searchNearbyCity(clickX, clickY);
        if (nearbyCity != null) {
            CityContextMenu menu = new CityContextMenu(nearbyCity);
            menu.show(evt.getComponent(), evt.getX(), evt.getY());
            return;
        }

        ContextMenu menu = new ContextMenu(evt);
        menu.show(evt.getComponent(), evt.getX(), evt.getY());
    }

    public static void main(String[] args) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        MainMenu menu = new MainMenu();
        EventQueue.invokeLater(() -> menu.setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JMapPanel jMapPanel;
    // End of variables declaration//GEN-END:variables
}
