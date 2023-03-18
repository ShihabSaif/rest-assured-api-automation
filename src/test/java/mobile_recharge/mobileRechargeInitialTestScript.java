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

    rechargeDBConnection rb = new rechargeDBConnection();

    public String checktopUpInfoStatus(String txnID) throws SQLException, ClassNotFoundException {
        Connection conn = rb.topUpInfodbConnection();
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
        Connection conn = rb.NpTxnLogdbConnection();
        PreparedStatement statement = null;
        ResultSet rs;
        statement = conn.prepareStatement("select * from np_txn_log where transaction_number = ?");
        statement.setString(1, txnID);
        rs = statement.executeQuery();
        while (rs.next())
        {
            return rs.getString("status");
        }
        return "";
    }

    @Test
    public void topup_multiple_case() throws ParseException, IOException, SQLException, ClassNotFoundException, InterruptedException {
        mobileRechargeInitial tk = new mobileRechargeInitial();
        Response resp = tk.mobile_recharge();

        if(resp.getStatusCode() == 200)
        {
            System.out.println("top up producer up");

            //get transactionNumber node value from response
            JsonPath jsp = resp.jsonPath();
            String txnNo = jsp.get("transactionNumber");
            System.out.println(txnNo);
            Thread.sleep(5000);
            String status = checktopUpInfoStatus(txnNo);

            if(status.isEmpty()) // means no row exists in top_up_info table for this txn_id
            {
                System.out.println("topup consumer down");
            }

            else if (status.contentEquals("SUCCESS"))
            {
                System.out.println("recharge is : " + status); //topup succeeded
            }

            else if (status.contentEquals("FAILED"))
            {
                System.out.println("recharge is initially : " + status); //topup failed initially
                Thread.sleep(5000);
                String finalStatus = checkNpTxnLogStatus(txnNo); // now checking whether it has been reversed in np_txn_log table
                System.out.println("recharge later : " + finalStatus);
            }
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
}