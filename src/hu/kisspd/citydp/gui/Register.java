/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hu.kisspd.citydp.gui;

import hu.kisspd.citydp.MySQLConn;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class Register extends javax.swing.JFrame {
    public Register() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        JLabel nameLabel = new JLabel();
        JLabel passwordLabel = new JLabel();
        JLabel passwordVerifyLabel = new JLabel();
        infoLabel = new javax.swing.JLabel();
        JButton insertBtn = new JButton();
        JButton backBtn = new JButton();
        nameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        passwordVerifyField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nameLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 18)); // NOI18N
        nameLabel.setText("Név:");

        passwordLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 18)); // NOI18N
        passwordLabel.setText("Jelszó:");

        passwordVerifyLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 18)); // NOI18N
        passwordVerifyLabel.setText("Jelszó ismétlés:");

        infoLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 18)); // NOI18N
        infoLabel.setText("Info:");

        insertBtn.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 18)); // NOI18N
        insertBtn.setText("Felvétel");
        insertBtn.addActionListener(this::insertAction);

        backBtn.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 18)); // NOI18N
        backBtn.setText("Vissza");
        backBtn.addActionListener(this::backBtnAction);

        nameField.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 18)); // NOI18N

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(infoLabel, GroupLayout.PREFERRED_SIZE, 538, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(21, 21, 21)
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addComponent(passwordVerifyLabel)
                                                                .addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(passwordLabel)))
                                                .addGap(21, 21, 21)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(passwordField, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(insertBtn, GroupLayout.Alignment.LEADING)
                                                        .addComponent(nameField, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(GroupLayout.Alignment.LEADING, layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                .addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(passwordVerifyField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
                                .addGap(88, 240, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, passwordField, passwordVerifyField, nameField);

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, nameLabel, passwordVerifyLabel, passwordLabel);

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, backBtn, insertBtn);

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nameField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(passwordLabel)
                                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(passwordVerifyLabel)
                                        .addComponent(passwordVerifyField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(insertBtn)
                                        .addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(infoLabel)
                                        .addGap(113, 113, 113))
                        ));

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, passwordField, passwordVerifyField, nameField);

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, nameLabel, passwordVerifyLabel, passwordLabel);

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, backBtn, insertBtn);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnAction(ActionEvent evt) {
        Login l = new Login();
        l.setVisible(true);
        setVisible(false);
    }

    private void insertAction(ActionEvent evt) {
        infoLabel.setText("");

        String name = nameField.getText();
        String password = String.valueOf(passwordField.getPassword());
        String passwordVerify = String.valueOf(passwordVerifyField.getPassword());

        if (name.contains(" ")) {
            infoLabel.setText("A név nem tartalmazhat szóközt!");
            return;
        } else if (name.isBlank() || password.isBlank() || passwordVerify.isBlank()) {
            infoLabel.setText("Minden mezőt ki kell tölteni!");
            return;
        } else if (!password.equals(passwordVerify)) {
            infoLabel.setText("A két jelszó nem egyezik meg!");
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

    private javax.swing.JLabel infoLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JPasswordField passwordVerifyField;
    private javax.swing.JTextField nameField;
}
