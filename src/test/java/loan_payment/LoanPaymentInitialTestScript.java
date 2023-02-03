package loan_payment;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoanPaymentInitialTestScript {
    @Test
    public void test_loan_payment() throws IOException, ParseException {
        LoanPaymentInitial loanPayment = new LoanPaymentInitial();
        Response responseLoanPayment = loanPayment.loan_payment();

        //get whole response body in a string readable format
        ResponseBody rspBody = responseLoanPayment.getBody();
        String finalRespBody = rspBody.asString();

        System.out.println(finalRespBody);
    }
}
