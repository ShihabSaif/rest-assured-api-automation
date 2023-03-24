package nagad_money_out;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import mobile_recharge.mobileRechargeInitial;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.*;

import static io.restassured.RestAssured.given;

public class nagadMoneyOutInitialTestScript {

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


    public String checknagadMoneyOutStatus(String reqID) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        statement = conn.prepareStatement("select * from transaction_info where request_id = ?");
        statement.setString(1, reqID);
        rs = statement.executeQuery();
        while (rs.next())
        {
            return rs.getString("status");
        }
        return "";
    }

    Response resp;
    @Test(priority = 1)
    public void nagadMoneyOutProducer_02() throws ParseException, IOException {
        nagadMoneyOutInitial tk = new nagadMoneyOutInitial();
        resp = tk.nagad_money_out(loginToken);
        Assert.assertEquals(resp.getStatusCode(), 200);
    }

    Connection conn = null;

    @Test(priority = 2)
    public void FIdbConnection_03() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection("jdbc:postgresql://10.9.0.77:5432/tallypay_to_fi_integration", "shihab", "shihab@123");
        Assert.assertTrue(conn != null);
    }

    @Test(priority = 3)
    public void txn_info_status() throws InterruptedException, SQLException, ClassNotFoundException {
        JsonPath jsp = resp.jsonPath();
        String reqId = jsp.get("requestId");

        Thread.sleep(20000);

        String status = checknagadMoneyOutStatus(reqId);
        Assert.assertTrue(status != null);
        System.out.println(status);
    }
//    @Test
//    public void test_nagad_money_out() throws ParseException, IOException, SQLException, ClassNotFoundException, InterruptedException {
//
//        if(resp.getStatusCode() == 200)
//        {
//            System.out.println("fi producer up");
//
//            JsonPath jsp = resp.jsonPath();
//            String reqId = jsp.get("requestId");      //get requestId from response
//
//            System.out.println(reqId);
//
//            Thread.sleep(20000);
//
//            String status = checknagadMoneyOutStatus(reqId);
//            if (status.contentEquals("PENDING"))
//            {
//                System.out.println("fi consumer is down");
//            }
//
//            else if (status.contentEquals("SUCCESS"))
//            {
//                System.out.println("nagad money out succeeded");
//            }
//
//            else if(status.contentEquals("REVERSE"))
//            {
//                System.out.println("nagad money out is being reversed");
//            }
//        }
//        else if(resp.getStatusCode() == 404)
//        {
//            System.out.println("fi producer down");
//        }
//    }

}