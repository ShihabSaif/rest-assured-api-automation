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

public class mobileRechargeInitialTestScript {

    @Test
    public void test_mobile_recharge() throws ParseException, IOException {
        mobileRechargeInitial tk = new mobileRechargeInitial();
        Response resp = tk.mobile_recharge();

        //get specific node value from response
        JsonPath jsp = resp.jsonPath();
        Float jspAMount = jsp.get("amount");

        //get whole response body in a string readable format
        ResponseBody rspBody = resp.getBody();
        String finalRespBody = rspBody.asString();

        System.out.println(finalRespBody);

//        Assert.assertEquals(200, resp.getStatusCode());
    }
}