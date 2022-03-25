package hr.atos.praksa.hrvojeskrbina.zadatak15;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static Connection con = null;

    static {
        String dbName = "atos_zadatak_15";
        String user = "root";
        String pass = "";
        try {
            con = DriverManager.getConnection("jdbc:mysql:///" + dbName, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return con;
    }

}
