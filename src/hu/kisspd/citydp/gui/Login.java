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

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        JPanel mainPanel = new JPanel();
        JLabel nameLabel = new JLabel();
        JLabel passwordLabel = new JLabel();
        nameField = new JTextField();
        jPasswordField = new JPasswordField();
        JButton loginBtn = new JButton();
        JButton registerBtn = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();

        {
            mainPanel.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.
            border.EmptyBorder(0,0,0,0), "JFormDesigner Evaluation",javax.swing.border.TitledBorder.CENTER
            ,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("Dialog",java.awt.Font
            .BOLD,12),java.awt.Color.red), mainPanel. getBorder()));
            mainPanel. addPropertyChangeListener(
                    e -> {if("border"
                    .equals(e.getPropertyName()))throw new RuntimeException();});

            nameLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
            nameLabel.setText("Felhasználónév:");

            passwordLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
            passwordLabel.setText("Jelszó:");

            nameField.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));

            jPasswordField.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));

            loginBtn.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
            loginBtn.setText("Belépés");
            loginBtn.addActionListener(e -> loginAction());

            registerBtn.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            registerBtn.setText("Új felhasználó");
            registerBtn.addActionListener(this::registerAction);

            GroupLayout jPanel1Layout = new GroupLayout(mainPanel);
            mainPanel.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup()
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup()
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addGroup(jPanel1Layout.createParallelGroup()
                                    .addComponent(nameLabel)
                                    .addComponent(passwordLabel))
                                .addGap(47, 47, 47))
                            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(registerBtn, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)))
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(loginBtn, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                            .addComponent(nameField, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                            .addComponent(jPasswordField))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup()
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(nameLabel)
                            .addComponent(nameField, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(passwordLabel)
                            .addComponent(jPasswordField, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(loginBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                            .addComponent(registerBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(59, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
    }// </editor-fold>

    private void loginAction() {
        String name = nameField.getText();
        String password = String.valueOf(jPasswordField.getPassword());

        /* debug mode */
        if (name.isBlank() && password.isBlank()) {
            loggedIn = true;
            username = "debug";
            EventQueue.invokeLater(() -> MainScreen.main(null));
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

                EventQueue.invokeLater(() -> MainScreen.main(null));
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

    private JTextField nameField;
    private JPasswordField jPasswordField;
}
