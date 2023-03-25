package send_money;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.*;

import static io.restassured.RestAssured.given;

public class sendMoneyInitialTestScript {
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
    String txnNo;

    @Test(priority = 1)
    public void sendMoney() throws ParseException, IOException {
        sendMoneyInitial ST = new sendMoneyInitial();
        resp = ST.send_money(loginToken);
        JsonPath jsp = resp.jsonPath();
        txnNo = jsp.get("transactionNumber");
        System.out.println(txnNo);
        Assert.assertEquals(resp.getStatusCode(), 200);
    }

    Connection NpTxnLogConn = null;
    @Test(priority = 2)
    public void NpTxnLogConnectionCheck() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        NpTxnLogConn = DriverManager.getConnection("jdbc:postgresql://10.9.0.77:5432/backend_db", "shihab", "shihab@123");
        Assert.assertTrue(NpTxnLogConn != null);
    }

    Connection conn = null;
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

    @Test(priority = 3)
    public void NpTxnLogStatus() throws SQLException, ClassNotFoundException {
        String nptxnlogStatus = checkNpTxnLogStatus(txnNo);
        System.out.println("np txn log status : " + nptxnlogStatus);
        Assert.assertTrue(nptxnlogStatus != "");
    }

    Connection TransfersConn = null;
    @Test(priority = 4)
    public void TransfersConnectionCheck() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        TransfersConn = DriverManager.getConnection("jdbc:postgresql://10.9.0.77:5432/core_pg", "shihab", "shihab@123");
        Assert.assertTrue(TransfersConn != null);
    }

    public String checkTransfersStatus(String txnID) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = null;
        ResultSet rs;
        statement = TransfersConn.prepareStatement("select * from transfers where transaction_number = ?");
        statement.setString(1, txnID);
        rs = statement.executeQuery();
        while (rs.next())
        {
            return rs.getString("status");
        }
        return "";
    }

    @Test(priority = 5)
    public void TransfersStatus() throws SQLException, ClassNotFoundException {
        String transfersStatus = checkTransfersStatus(txnNo);
        System.out.println("transfers status : " + transfersStatus);
        Assert.assertTrue(transfersStatus != "");
    }
}
