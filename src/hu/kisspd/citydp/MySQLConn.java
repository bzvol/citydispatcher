
package hu.kisspd.citydp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class MySQLConn {
    public static Connection conn = null;

    public static boolean connectDB() {
        if (conn != null) {
            return true;
        }

        String host = "jdbc:mysql://citydispatcher.mysql.database.azure.com:3306/citydispatcher?useSSL=true";
        String user = "KPeterD4759";
        String password = "Kecske4759";

        try {
            // Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(host, user, password);
            return true;
        } catch (Exception e) {
            Util.showError("Nem sikerült csatlakozni az adatbázishoz!", e);
        }

        return false;
    }

    public static void disconnectDB() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (Exception e) {
                Util.showError("Nem sikerült a kapcsolat bezárása!", e);
            }
        }
    }

    public static boolean runStatement(String sql) {
        if (!connectDB()) {
            return false;
        }

        try {
            conn.createStatement().execute(sql);
            return true;
        } catch (Exception e) {
            Util.showError("Adatbázis hiba:", e);
        }

        return false;
    }

    public static ResultSet runQuery(String sql) {
        if (!connectDB()) {
            return null;
        }

        try {
            return conn.createStatement().executeQuery(sql);
        } catch (Exception e) {
            Util.showError("Adatbázis hiba:", e);
        }

        return null;
    }

    public static int lastInsertedId() {
        if (!connectDB()) {
            return -1;
        }

        try (ResultSet rs = conn.createStatement().executeQuery("SELECT LAST_INSERT_ID() AS id")) {
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            Util.showError("Adatbázis hiba:", e);
        }

        return -1;
    }
}
