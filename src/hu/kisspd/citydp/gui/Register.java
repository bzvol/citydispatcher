/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hu.kisspd.citydp.gui;

import hu.kisspd.citydp.MySQLConn;

import java.sql.ResultSet;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class Register extends javax.swing.JFrame {
    public Register() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jNameLabel = new javax.swing.JLabel();
        jPasswordLabel = new javax.swing.JLabel();
        jPasswordVerifyLabel = new javax.swing.JLabel();
        jInfoLabel = new javax.swing.JLabel();
        jInsertButton = new javax.swing.JButton();
        jBackButton = new javax.swing.JButton();
        jNameField = new javax.swing.JTextField();
        jPasswordField = new javax.swing.JPasswordField();
        jPasswordVerifyField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jNameLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jNameLabel.setText("Név:");

        jPasswordLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPasswordLabel.setText("Jelszó:");

        jPasswordVerifyLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPasswordVerifyLabel.setText("Jelszó ismétlés:");

        jInfoLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jInfoLabel.setText("Info:");

        jInsertButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jInsertButton.setText("Felvétel");
        jInsertButton.addActionListener(this::insertAction);

        jBackButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBackButton.setText("Vissza");
        jBackButton.addActionListener(this::backBtnAction);

        jNameField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(jInfoLabel, GroupLayout.PREFERRED_SIZE, 538, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(21, 21, 21)
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addComponent(jPasswordVerifyLabel)
                                                                .addComponent(jNameLabel, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jPasswordLabel)))
                                                .addGap(21, 21, 21)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jPasswordField, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jInsertButton, GroupLayout.Alignment.LEADING)
                                                        .addComponent(jNameField, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(GroupLayout.Alignment.LEADING, layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                .addComponent(jBackButton, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jPasswordVerifyField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
                                .addGap(88, 240, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, jPasswordField, jPasswordVerifyField, jNameField);

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, jNameLabel, jPasswordVerifyLabel, jPasswordLabel);

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, jBackButton, jInsertButton);

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jNameLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jNameField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jPasswordLabel)
                                        .addComponent(jPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jPasswordVerifyLabel)
                                        .addComponent(jPasswordVerifyField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jInsertButton)
                                        .addComponent(jBackButton, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(jInfoLabel)
                                        .addGap(113, 113, 113))
                        ));

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, jPasswordField, jPasswordVerifyField, jNameField);

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, jNameLabel, jPasswordVerifyLabel, jPasswordLabel);

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, jBackButton, jInsertButton);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnAction(ActionEvent evt) {
        Login l = new Login();
        l.setVisible(true);
        setVisible(false);
    }

    private void insertAction(ActionEvent evt) {
        jInfoLabel.setText("");

        String name = jNameField.getText();
        String password = String.valueOf(jPasswordField.getPassword());
        String passwordVerify = String.valueOf(jPasswordVerifyField.getPassword());

        if (name.contains(" ")) {
            jInfoLabel.setText("A név nem tartalmazhat szóközt!");
            return;
        } else if (name.isBlank() || password.isBlank() || passwordVerify.isBlank()) {
            jInfoLabel.setText("Minden mezőt ki kell tölteni!");
            return;
        } else if (!password.equals(passwordVerify)) {
            jInfoLabel.setText("A két jelszó nem egyezik meg!");
            return;
        } else if (Login.userExists(name)) {
            JOptionPane.showMessageDialog(null,
                    "A felhasználó már létezik!",
                    "Létező felhasználó", JOptionPane.WARNING_MESSAGE);
            return;
        }

        MySQLConn.connectDB();
        String query = String.format("INSERT INTO user (name, password) VALUES ('%s', '%s')", name, password);
        if (MySQLConn.runStatement(query)) {
            JOptionPane.showMessageDialog(null, "Sikeres regisztráció!");
        }
        MySQLConn.disconnectDB();
    }

    public static void main(String[] args) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> new Register().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBackButton;
    private javax.swing.JButton jInsertButton;
    private javax.swing.JLabel jInfoLabel;
    private javax.swing.JLabel jNameLabel;
    private javax.swing.JLabel jPasswordVerifyLabel;
    private javax.swing.JLabel jPasswordLabel;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JPasswordField jPasswordVerifyField;
    private javax.swing.JTextField jNameField;
    // End of variables declaration//GEN-END:variables
}
