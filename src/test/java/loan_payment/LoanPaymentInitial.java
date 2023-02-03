package loan_payment;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class LoanPaymentInitial {
    loanPaymentDTO loanPayment = new loanPaymentDTO();

    public LoanPaymentInitial() throws IOException {
        loanPayment.setAmount();
        loanPayment.setCredential();
        loanPayment.setLocation();
        loanPayment.setReceiver();
        loanPayment.setFpAuth();
        loanPayment.setIs_fp_auth();
        loanPayment.setRequestId();
        loanPayment.setLoan_amount();
        loanPayment.setMerchant_wallet_no();
        loanPayment.setExternal_customer();
        loanPayment.setNote();
    }

    public String login() throws ParseException {
        JSONObject requestBody = new JSONObject();

        requestBody.put("app_type", "TALLYKHATA");
        requestBody.put("device_id", "1c2ea1a42bcd5c93");
        requestBody.put("device_type", "ANDROID");
        requestBody.put("mobile_number", "01715321485");
        requestBody.put("password", "2040");
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

    Response loan_payment() throws ParseException, IOException {

        JSONObject requestBody = new JSONObject();

        requestBody.put("amount", loanPayment.getAmount());
        requestBody.put("credential", loanPayment.getCredential());
        requestBody.put("location", loanPayment.getLocation());
        requestBody.put("receiver", loanPayment.getReceiver());
        requestBody.put("fpAuth", loanPayment.getFpAuth());
        requestBody.put("is_fp_auth", loanPayment.getIs_fp_auth());
        requestBody.put("requestId", loanPayment.getRequestId());
        requestBody.put("loan_amount", loanPayment.getLoan_amount());
        requestBody.put("merchant_wallet_no", loanPayment.getMerchant_wallet_no());
        requestBody.put("external_customer", loanPayment.getExternal_customer());
        requestBody.put("note", loanPayment.getNote());

        System.out.println(requestBody);

        Response response = given().
                header("content-type", "application/json").
                header("Authorization", login()).
                body(requestBody).
        when().
                post("https://stgnpapigw.nobopay.com/api/v1/transaction/tk/payment/with/loan");

        return response;
    }

}
