package mobile_recharge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class rechargeDBConnection {

    public Connection NpTxnLogdbConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;

        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection("jdbc:postgresql://10.9.0.77:5432/backend_db", "shihab", "shihab@123");

        if (conn != null)
        {
            System.out.println("connection established for np txn log" + "\n");
        }
        else {
            System.out.println("connection failed" + "\n");
        }
        return conn;
    }
}
