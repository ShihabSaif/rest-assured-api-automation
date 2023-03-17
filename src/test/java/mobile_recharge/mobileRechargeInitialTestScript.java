package mobile_recharge;

import com.sun.org.glassfish.gmbal.Description;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import loan_repayment.LoanRepaymentInitial;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class mobileRechargeInitialTestScript {

    @Test
    public void test_top_up_producer() throws ParseException, IOException {
        mobileRechargeInitial tk = new mobileRechargeInitial();
        Response resp = tk.mobile_recharge();

        if(resp.getStatusCode() == 200)
        {
            System.out.println("top up producer up");
        }
        else if(resp.getStatusCode() == 404)
        {
            System.out.println("top up producer down");
        }

        //get specific node value from response
//        JsonPath jsp = resp.jsonPath();
//        Float jspAMount = jsp.get("amount");

        //get whole response body in a string readable format
//        ResponseBody rspBody = resp.getBody();
//        String finalRespBody = rspBody.asString();

//        System.out.println(finalRespBody);

//        Assert.assertEquals(200, resp.getStatusCode());
    }

    public Connection dbConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;

        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection("jdbc:postgresql://10.9.0.77:5432/topup_service", "shihab", "shihab@123");

        if (conn != null)
        {
            System.out.println("connection established" + "\n");
        }
        else {
            System.out.println("connection failed" + "\n");
        }
        return conn;
    }

    public String checkStatus(String txnID) throws SQLException, ClassNotFoundException {
        Connection conn = dbConnection();
        PreparedStatement statement = null;
        ResultSet rs;
        statement = conn.prepareStatement("select * from top_up_info where txn_id = ?");
        statement.setString(1, txnID);
        rs = statement.executeQuery();
        while (rs.next())
        {
            return rs.getString("status");
        }
        return "";
    }

    @Test
    public void topup_success_case() throws ParseException, IOException, SQLException, ClassNotFoundException, InterruptedException {
        mobileRechargeInitial tk = new mobileRechargeInitial();
        Response resp = tk.mobile_recharge();

        //get specific node value from response
        JsonPath jsp = resp.jsonPath();
        String txnNo = jsp.get("transactionNumber");
        System.out.println(txnNo);

        Thread.sleep(5000);

        String status = checkStatus(txnNo);
        System.out.println("status is : " + status);
    }
}