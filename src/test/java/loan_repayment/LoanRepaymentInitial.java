package loan_repayment;

import loan_repayment.LoanRepaymentDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import io.restassured.response.Response;

import java.io.IOException;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class LoanRepaymentInitial {

    long requestId = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;

    LoanRepaymentDTO LR;

    {
        try {
            LR = new LoanRepaymentDTO();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public LoanRepaymentInitial() {

        LR.setAmount();
        LR.setCredential();
        LR.setLocation();
        LR.setReceiver();
        LR.setFpAuth();
        LR.setIs_fp_auth();
        LR.setRequestId();
        LR.setExternalFI();
        LR.setLoanAccountNo();
        LR.setLoanCardNo();
        LR.setNote();
    }

    public String login() throws ParseException {
        JSONObject requestBody = new JSONObject();

        requestBody.put("app_type", "TALLYKHATA");
        requestBody.put("device_id", "1c2ea1a42bcd5c93");
        requestBody.put("device_type", "ANDROID");
        requestBody.put("mobile_number", "01621215877");
        requestBody.put("password", "9316");
        requestBody.put("uuid", "0069a6b1-b510-443b-b51b-db84889ef9f6");

        Response response = given().
                header("x-auth-token", "4lSE5BTGIi9oiwjhLC5PDDXJ9Rdlof0Z3hpwMLZL").
                header("content-type", "application/json").
                header("Authorization", "Basic cHJvZ290aV9xYTpwcjBnMHQxQDIwMnR3bw==").
                header("x-device-id", "30c3f35c-d6b2-47b4-ba86-b81eea6af4a2").
                body(requestBody.toJSONString()).
                when().
                post("https://stgqa.tallykhata.com/wallet/api/tp-proxy/user/login");

        String loginResponse = response.getBody().asString();
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(loginResponse);

        String token = "token " + (String) obj.get("token");
        return token;
    }

    Response loan_repayment() throws ParseException, IOException {

        JSONObject requestBody = new JSONObject();

        requestBody.put("amount", LR.getAmount());
        requestBody.put("credential", LR.getCredential());
        requestBody.put("location", LR.getLocation());
        requestBody.put("receiver", LR.getReceiver());
        requestBody.put("fpAuth", LR.getFpAuth());
        requestBody.put("is_fp_auth", LR.getIs_fp_auth());
        requestBody.put("requestId", LR.getRequestId());
        requestBody.put("externalFI", LR.getExternalFI());
        requestBody.put("loanAccountNo", LR.getLoanAccountNo());
        requestBody.put("loanCardNo", LR.getLoanCardNo());
        requestBody.put("note", LR.getNote());

//        requestBody.put("amount", FileRead.envAndFile().get("amount"));
//        requestBody.put("credential", FileRead.envAndFile().get("credential"));
//        requestBody.put("location", FileRead.envAndFile().get("location"));
//        requestBody.put("receiver", FileRead.envAndFile().get("receiver"));
//        requestBody.put("fpAuth", FileRead.envAndFile().get("fpAuth"));
//        requestBody.put("is_fp_auth", FileRead.envAndFile().get("is_fp_auth"));
//        requestBody.put("requestId", FileRead.envAndFile().get("requestId"));
//        requestBody.put("externalFI", FileRead.envAndFile().get("externalFI"));
//        requestBody.put("loanAccountNo", FileRead.envAndFile().get("loanAccountNo"));
//        requestBody.put("loanCardNo", FileRead.envAndFile().get("loanCardNo"));
//        requestBody.put("note", FileRead.envAndFile().get("note"));

//        System.out.println(" request body is : " + requestBody);

        Response response = given().
                header("content-type", "application/json").
                header("Authorization", login()).
                header("x-request-id", "1c2ea1a42bcd5c93:TK_ANDROID:15458807928059").
                header("np-uuid", "0069a6b1-b510-443b-b51b-db84889ef9f6").
                header("np-userref", "01621215877").
                header("np-devicemodel", "RMX1921").
                header("np-appversioncode", 110).
                body(requestBody).
        when().
                post("https://stgnpapigw.nobopay.com/api/v1/transaction/tk/loan/repayment");

        return response;
    }
}