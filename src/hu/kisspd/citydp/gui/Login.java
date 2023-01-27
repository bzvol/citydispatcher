/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.kisspd.citydp.gui;

import hu.kisspd.citydp.MySQLConn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;

public class Login extends javax.swing.JFrame {
    static boolean loggedIn = false;
    static String username = null;

    public Login() {
        initComponents();

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginAction();
                }
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jNameLabel = new JLabel();
        jPasswordLabel = new JLabel();
        jNameField = new JTextField();
        jPasswordField = new JPasswordField();
        jLoginButton = new JButton();
        jRegisterButton = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jNameLabel.setFont(new Font("Dialog", Font.BOLD, 18)); // NOI18N
        jNameLabel.setText("Felhasználónév:");

        jPasswordLabel.setFont(new Font("Dialog", Font.BOLD, 18)); // NOI18N
        jPasswordLabel.setText("Jelszó:");

        jNameField.setFont(new Font("Dialog", Font.PLAIN, 18)); // NOI18N

        jPasswordField.setFont(new Font("Dialog", Font.PLAIN, 18)); // NOI18N

        jLoginButton.setFont(new Font("Dialog", Font.PLAIN, 18)); // NOI18N
        jLoginButton.setText("Belépés");
        jLoginButton.addActionListener(evt -> loginAction());

        jRegisterButton.setFont(new Font("Segoe UI", Font.PLAIN, 18)); // NOI18N
        jRegisterButton.setText("Új felhasználó");
        jRegisterButton.addActionListener(this::registerAction);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(59, 59, 59)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jNameLabel)
                                                        .addComponent(jPasswordLabel))
                                                .addGap(47, 47, 47))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jRegisterButton, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
                                                .addGap(31, 31, 31)))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLoginButton, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                                        .addComponent(jNameField, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                                        .addComponent(jPasswordField))
                                .addContainerGap(181, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jNameLabel)
                                        .addComponent(jNameField, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jPasswordLabel)
                                        .addComponent(jPasswordField, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                                .addGap(55, 55, 55)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLoginButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jRegisterButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(122, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginAction() {
        String name = jNameField.getText();
        String password = String.valueOf(jPasswordField.getPassword());

        /* debug mode */
        if (name.isBlank() && password.isBlank()) {
            loggedIn = true;
            username = "debug";
            EventQueue.invokeLater(() -> new MainMenu().setVisible(true));
            this.dispose();
            return;
        }

        try {
            ResultSet userRS;
            if (userExists(name) && (userRS = getUser(name)).getString("password").equals(password)) {
                userRS.close();

                loggedIn = true;
                username = name;

                JOptionPane.showMessageDialog(null,
                        "Sikeres bejelentkezés!");

                EventQueue.invokeLater(() -> new MainMenu().setVisible(true));
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Hibás felhasználónév vagy jelszó!",
                        "Hiba", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Adatbázis hiba: " + e.getMessage(),
                    "Hiba", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static boolean userExists(String name) {
        MySQLConn.connectDB();

        String query = String.format("SELECT * FROM user WHERE name = '%s'", name);
        try (ResultSet rs = MySQLConn.runQuery(query)) {
            if (rs != null && rs.next()) {
                return true;
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Adatbázis hiba: " + e.getMessage(),
                    "Hiba", JOptionPane.ERROR_MESSAGE);
        }

        MySQLConn.disconnectDB();

        return false;
    }

    public static ResultSet getUser(String name) {
        MySQLConn.connectDB();

        String query = String.format("SELECT * FROM user WHERE name = '%s'", name);
        try {
            ResultSet rs = MySQLConn.runQuery(query); // ALWAYS CLOSE THE RESULTSET
            if (rs != null && rs.next()) {
                return rs;
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Adatbázis hiba: " + e.getMessage(),
                    "Hiba", JOptionPane.ERROR_MESSAGE);
        }

        MySQLConn.disconnectDB();

        throw new IllegalArgumentException(String.format("No user found with given name %s", name));
    }

    private void registerAction(ActionEvent evt) {
        Register r = new Register();
        r.setVisible(true);
        setVisible(false);
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        EventQueue.invokeLater(() -> new Login().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton jLoginButton;
    private JButton jRegisterButton;
    private JLabel jNameLabel;
    private JLabel jPasswordLabel;
    private JTextField jNameField;
    private JPasswordField jPasswordField;
    // End of variables declaration//GEN-END:variables
}
