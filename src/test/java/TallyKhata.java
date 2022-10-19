import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TallyKhata {

    long requestId = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;

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
        System.out.println("token is: " + token);
        return token;
    }

    @Test (enabled = true, priority = 1)
    void test_loan_repayment() throws ParseException, IOException {
        JSONObject requestBody = new JSONObject();

        requestBody.put("amount", FileRead.envAndFile().get("amount"));
        requestBody.put("credential", FileRead.envAndFile().get("credential"));
        requestBody.put("location", FileRead.envAndFile().get("location"));
        requestBody.put("receiver", FileRead.envAndFile().get("receiver"));
        requestBody.put("fpAuth", FileRead.envAndFile().get("fpAuth"));
        requestBody.put("is_fp_auth", FileRead.envAndFile().get("is_fp_auth"));
        requestBody.put("requestId", FileRead.envAndFile().get("requestId"));
        requestBody.put("externalFI", FileRead.envAndFile().get("externalFI"));
        requestBody.put("loanAccountNo", FileRead.envAndFile().get("loanAccountNo"));
        requestBody.put("loanCardNo", FileRead.envAndFile().get("loanCardNo"));
        requestBody.put("note", FileRead.envAndFile().get("note"));

        System.out.println(" request body is : " + requestBody);

        given().
                header("content-type", "application/json").
                header("Authorization", login()).
                header("x-request-id", "1c2ea1a42bcd5c93:TK_ANDROID:15458807928059").
                header("np-uuid", "0069a6b1-b510-443b-b51b-db84889ef9f6").
                header("np-userref", "01621215877").
                header("np-devicemodel", "RMX1921").
                header("np-appversioncode", 110).
                body(requestBody.toJSONString()).
        when().
                post("https://stgnpapigw.nobopay.com/api/v1/transaction/tk/loan/repayment").
        then().
                statusCode(200).
                log().all();
    }

    @Test (enabled = true, priority = 2)
    void test_recharge() throws ParseException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("amount", 11);
        requestBody.put("credential", "9316");
        requestBody.put("location", null);
        requestBody.put("receiver", "01621215877");
        requestBody.put("mobile_operator", "GP");
        requestBody.put("fpAuth", false);
        requestBody.put("is_fp_auth", false);
        requestBody.put("request_id", requestId);
        requestBody.put("mobile_type", "PREPAID");
        requestBody.put("receiver_mobile", "01621215877");
        requestBody.put("receiver_name", "A.B.M.Shihab Uddin");

        given().
                header("content-type", "application/json").
                header("Authorization", login()).
                body(requestBody.toJSONString()).
        when().
                post("https://stgnpapigw.nobopay.com/api/v1/transaction/recharge-mobile").
        then().
                statusCode(200).
                log().all();
    }

    @Test (enabled = true, priority = 3)
    void test_loan_payment() throws ParseException {
        JSONObject requestBody = new JSONObject();

        requestBody.put("amount", 10);
        requestBody.put("credential", "9316");
        requestBody.put("location", null);
        requestBody.put("receiver", "01800000220");
        requestBody.put("fpAuth", false);
        requestBody.put("is_fp_auth", false);
        requestBody.put("request_id", requestId);
        requestBody.put("loan_amount", 10);
        requestBody.put("merchant_wallet_no", "01800000220");
        requestBody.put("external_customer", "01765841854");
        requestBody.put("note", "supplier payment with loan ammount");

        System.out.println("request body is : " + requestBody);

        given().
                header("content-type", "application/json").
                header("Authorization", login()).
                body(requestBody.toJSONString()).
        when().
                post("https://stgnpapigw.nobopay.com/api/v1/transaction/tk/payment/with/loan").
        then().
                statusCode(200).
                log().all();
    }

    @Test (enabled = true, priority = 4)
    void test_send_money() throws ParseException {
        JSONObject requestBody = new JSONObject();

        requestBody.put("amount", 10);
        requestBody.put("credential", "9316");
        requestBody.put("location", null);
        requestBody.put("note", null);
        requestBody.put("receiver", "01765841854");
        requestBody.put("fpAuth", false);
        requestBody.put("is_fp_auth", false);
        requestBody.put("request_id", requestId);
        requestBody.put("charge_flag", null);
        requestBody.put("receiver_wallet_no", "01765841854");

        System.out.println("request body is : " + requestBody);

        given().
                header("content-type", "application/json").
                header("Authorization", login()).
                body(requestBody.toJSONString()).
        when().
                post("https://stgnpapigw.nobopay.com/api/v1/transaction/tk/send-money").
        then().
                statusCode(200).
                log().all();
    }
}