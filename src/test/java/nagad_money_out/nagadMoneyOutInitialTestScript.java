package nagad_money_out;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import mobile_recharge.mobileRechargeInitial;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class nagadMoneyOutInitialTestScript {

    FIDBConnetion fi = new FIDBConnetion();
    public String checknagadMoneyOutStatus(String reqID) throws SQLException, ClassNotFoundException {
        Connection conn = fi.fidbConnection();
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

    @Test
    public void test_nagad_money_out() throws ParseException, IOException, SQLException, ClassNotFoundException, InterruptedException {
        nagadMoneyOutInitial tk = new nagadMoneyOutInitial();
        Response resp = tk.nagad_money_out();

        if(resp.getStatusCode() == 200)
        {
            System.out.println("fi producer up");

            JsonPath jsp = resp.jsonPath();
            String reqId = jsp.get("requestId");      //get requestId from response

            System.out.println(reqId);

            Thread.sleep(20000);

            String status = checknagadMoneyOutStatus(reqId);
            if (status.contentEquals("PENDING"))
            {
                System.out.println("fi consumer is down");
            }

            else if (status.contentEquals("SUCCESS"))
            {
                System.out.println("nagad money out succeeded");
            }

            else if(status.contentEquals("REVERSE"))
            {
                System.out.println("nagad money out is being reversed");
            }
        }
        else if(resp.getStatusCode() == 404)
        {
            System.out.println("fi producer down");
        }
    }
}