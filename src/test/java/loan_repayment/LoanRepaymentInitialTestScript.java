package loan_repayment;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import loan_repayment.LoanRepaymentInitial;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoanRepaymentInitialTestScript {

    @Test
    public void test_loan_repayment() throws ParseException, IOException {
        LoanRepaymentInitial tk = new LoanRepaymentInitial();
        Response resp = tk.loan_repayment();

        //get specific node value from response
        JsonPath jsp = resp.jsonPath();
        Float jspAMount = jsp.get("amount");

        //get whole response body in a string readable format
        ResponseBody rspBody = resp.getBody();
        String finalRespBody = rspBody.asString();

        Assert.assertEquals(200, resp.getStatusCode());
    }
}
