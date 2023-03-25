package mobile_recharge;

import io.restassured.response.Response;
import loan_repayment.LoanRepaymentDTO;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class mobileRechargeInitial {

    mobileRechargeDTO LR;

    {
        try {
            LR = new mobileRechargeDTO();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public mobileRechargeInitial() {

        LR.setReceiver_mobile();
        LR.setMobile_operator();
        LR.setMobile_type();
        LR.setReceiver_name();
        LR.setWallet();
        LR.setIs_fp_auth();
        LR.setCredential();
        LR.setAmount();
        LR.setRequest_id();
    }

    public String login() throws ParseException {
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
                post("https://stgqa.tallykhata.com/wallet/api/tp-proxy/user/login");

        String loginResponse = response.getBody().asString();
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(loginResponse);

        String token = "token " + (String) obj.get("token");
        return token;
    }

    Response mobile_recharge(String loginToken) throws ParseException, IOException {

        JSONObject requestBody = new JSONObject();

        requestBody.put("receiver_mobile", LR.getReceiver_mobile());
        requestBody.put("mobile_operator", LR.getMobile_operator());
        requestBody.put("mobile_type", LR.getMobile_type());
        requestBody.put("receiver_name", LR.getReceiver_name());
        requestBody.put("wallet", LR.getWallet());
        requestBody.put("is_fp_auth", LR.getIs_fp_auth());
        requestBody.put("credential", LR.getCredential());
        requestBody.put("amount", LR.getAmount());
        requestBody.put("request_id", LR.getRequest_id());

        Response response = given().
                header("content-type", "application/json").
                header("Authorization", loginToken).
                body(requestBody).
        when().
                post("http://10.9.0.77:6060/topup-producer/api/v1/transaction/recharge-mobile");

        return response;
    }
}