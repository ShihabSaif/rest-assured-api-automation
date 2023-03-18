package nagad_money_out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FIDBConnetion {
    public Connection fidbConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;

        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection("jdbc:postgresql://10.9.0.77:5432/tallypay_to_fi_integration", "shihab", "shihab@123");

        if (conn != null)
        {
            System.out.println("connection established" + "\n");
        }
        else {
            System.out.println("connection failed" + "\n");
        }
        return conn;
    }

}
