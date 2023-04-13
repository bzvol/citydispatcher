package hu.kisspd.citydp.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import hu.kisspd.citydp.MySQLConn;
import hu.kisspd.citydp.Util;
import hu.kisspd.citydp.gui.component.JPrettyButton;
import hu.kisspd.citydp.gui.component.JPrettyPasswordField;
import hu.kisspd.citydp.gui.component.JPrettyTextField;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.util.Locale;

public class LoginScreen {
    private JPanel mainPanel;
    private JPrettyButton registerBtn;
    private JPrettyButton loginBtn;
    private JPrettyTextField nameField;
    private JPrettyPasswordField passwordField;

    static boolean loggedIn = false;
    static String username = null;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bejelentkezés");
        frame.setContentPane(new LoginScreen().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        Util.centerFrame(frame);
        frame.pack();
        frame.setVisible(true);
    }

    private void loginAction(ActionEvent evt) {
        String name = nameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        try {
            ResultSet userRS;
            if (userExists(name) && (userRS = getUser(name)).getString("password").equals(password)) {
                userRS.close();

                loggedIn = true;
                username = name;

                JOptionPane.showMessageDialog(null,
                        "Sikeres bejelentkezés!");

                EventQueue.invokeLater(() -> MainScreen.main(null));
                SwingUtilities.getWindowAncestor(mainPanel).dispose();
            } else {
                Util.showError("Hibás felhasználónév vagy jelszó!", null);
            }
        } catch (Exception e) {
            Util.showDBError(e);
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
            Util.showDBError(e);
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
            Util.showDBError(e);
        }

        MySQLConn.disconnectDB();

        throw new IllegalArgumentException(String.format("No user found with given name %s", name));
    }

    private void registerAction(ActionEvent evt) {
        EventQueue.invokeLater(() -> RegisterScreen.main(null));
        SwingUtilities.getWindowAncestor(mainPanel).dispose();
    }

    //<editor-fold desc="Generated Code">

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(1, 1, new Insets(40, 40, 40, 40), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), 24, 24));
        mainPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(480, 240), null, null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$(null, -1, 18, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("Név");
        panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$(null, -1, 18, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setText("Jelszó");
        panel1.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        loginBtn.setArcSize(30);
        Font loginBtnFont = this.$$$getFont$$$(null, -1, 18, loginBtn.getFont());
        if (loginBtnFont != null) loginBtn.setFont(loginBtnFont);
        loginBtn.setText("Bejelentkezés");
        panel1.add(loginBtn, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameField.setArcSize(30);
        nameField.setPlaceholder("Név");
        panel1.add(nameField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        passwordField.setArcSize(30);
        passwordField.setPlaceholder("Jelszó");
        panel1.add(passwordField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        registerBtn.setArcSize(30);
        Font registerBtnFont = this.$$$getFont$$$(null, -1, 18, registerBtn.getFont());
        if (registerBtnFont != null) registerBtn.setFont(registerBtnFont);
        registerBtn.setText("Regisztrálás");
        panel1.add(registerBtn, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    private static CompoundBorder border(int borderSize, JComponent component) {
        return BorderFactory.createCompoundBorder(component.getBorder(),
                BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));
    }

    private void createUIComponents() {
        int arcSize = 30;

        nameField = new JPrettyTextField();
        nameField.setBorder(border(4, nameField));
        nameField.setArcSize(arcSize);
        nameField.setFont($$$getFont$$$(null, -1, 18, nameField.getFont()));
        nameField.setPlaceholder("Név");

        passwordField = new JPrettyPasswordField();
        passwordField.setBorder(border(4, passwordField));
        passwordField.setArcSize(arcSize);
        passwordField.setFont($$$getFont$$$(null, -1, 18, passwordField.getFont()));
        passwordField.setPlaceholder("Jelszó");

        loginBtn = new JPrettyButton("Bejelentkezés");
        loginBtn.setBorder(border(4, loginBtn));
        loginBtn.setArcSize(arcSize);
        loginBtn.setFont($$$getFont$$$(null, -1, 18, loginBtn.getFont()));
        loginBtn.addActionListener(this::loginAction);

        registerBtn = new JPrettyButton("Regisztrálás");
        registerBtn.setBorder(border(4, registerBtn));
        registerBtn.setArcSize(arcSize);
        registerBtn.setFont($$$getFont$$$(null, -1, 18, registerBtn.getFont()));
        registerBtn.addActionListener(this::registerAction);
    }
    //</editor-fold>
}
