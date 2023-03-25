package mobile_recharge;

import com.sun.org.glassfish.gmbal.Description;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import javafx.scene.layout.Priority;
import loan_repayment.LoanRepaymentInitial;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

import static io.restassured.RestAssured.given;

public class mobileRechargeInitialTestScript {

    rechargeDBConnection rb = new rechargeDBConnection();
    mobileRechargeInitial mr = new mobileRechargeInitial();
    public String loginToken;

    @Test(priority = 0)
    public void login_01() throws ParseException {
        JSONObject requestBody = new JSONObject();

        requestBody.put("app_type", "TALLYKHATA");
        requestBody.put("device_id", "1c2ea1a42bcd5c93");
        requestBody.put("device_type", "ANDROID");
        requestBody.put("mobile_number", "01715321485");
        requestBody.put("password", "9876");
        requestBody.put("uuid", "0069a6b1-b510-443b-b51b-db84889ef9f6");

        Response response = given().
                header("x-auth-token", "V9ox1BcmpqGZWuAHoFJGm7ovq2eY42Ja1cyAvuwo").
                header("content-type", "application/json").
                header("Authorization", "Basic cHJvZ290aV9xYTpwcjBnMHQxQDIwMnR3bw==").
//                header("x-device-id", "30c3f35c-d6b2-47b4-ba86-b81eea6af4a2").
        body(requestBody.toJSONString()).
                when().
                post("http://10.9.0.77:9070/wallet/api/tp-proxy/user/login");

        String loginResponse = response.getBody().asString();
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(loginResponse);

        loginToken = "token " + (String) obj.get("token");

        Assert.assertEquals(response.getStatusCode(),  200);
    }
    Response resp;

    @Test(priority = 1)
    public void topupProducer_02() throws ParseException, IOException {
        resp = mr.mobile_recharge(loginToken);
        Assert.assertEquals(resp.getStatusCode(), 200);
    }

    Connection conn = null;

    @Test(priority = 2)
    public void topUpInfodbConnection_03() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection("jdbc:postgresql://10.9.0.77:5432/topup_service", "shihab", "shihab@123");
        Assert.assertTrue(conn != null);
    }

    String status;
    String txnNo;

    @Test(priority = 3)
    public void topupinfoStatus_04() throws InterruptedException, SQLException, ClassNotFoundException {
        JsonPath jsp = resp.jsonPath();
        txnNo = jsp.get("transactionNumber");
        Thread.sleep(5000);
        status = checktopUpInfoStatus(txnNo);
        System.out.println("top up info status : " + status);
        Assert.assertTrue(status != "");
    }

    Connection NpTxnLogConn = null;

    @Test(priority = 4)
    public void NpTxnLogdbConnection_05() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        NpTxnLogConn = DriverManager.getConnection("jdbc:postgresql://10.9.0.77:5432/backend_db", "shihab", "shihab@123");
        Assert.assertTrue(NpTxnLogConn != null);
    }

    @Test(priority = 5)
    public void NpTxnLogStatus_06() throws SQLException, ClassNotFoundException {
        String nptxnlogStatus = checkNpTxnLogStatus(txnNo);
        System.out.println("np txn log status : " + nptxnlogStatus);
        Assert.assertTrue(nptxnlogStatus != "");
    }

    public String checktopUpInfoStatus(String txnID) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        statement = conn.prepareStatement("select * from top_up_info where txn_id = ?");
        statement.setString(1, txnID);
        rs = statement.executeQuery();
        while (rs.next())
        {
            return rs.getString("status");
        }
        return "";
    }

    public String checkNpTxnLogStatus(String txnID) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = null;
        ResultSet rs;
        statement = NpTxnLogConn.prepareStatement("select * from np_txn_log where transaction_number = ?");
        statement.setString(1, txnID);
        rs = statement.executeQuery();
        while (rs.next())
        {
            return rs.getString("status");
        }
        return "";
    }


}