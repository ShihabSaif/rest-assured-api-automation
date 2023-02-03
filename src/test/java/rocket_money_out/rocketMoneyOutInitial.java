package rocket_money_out;

import io.restassured.response.Response;
import nagad_money_out.nagadMoneyOutDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class rocketMoneyOutInitial {

    rocketMoneyOutDTO LR;

    {
        try {
            LR = new rocketMoneyOutDTO();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public rocketMoneyOutInitial() {
        LR.setAmount();
        LR.setCredential();
        LR.setLocation();
        LR.setNote();
        LR.setReceiver();
        LR.setFpAuth();
        LR.setIs_fp_auth();
        LR.setRequest_id();
        LR.setExternal_receiver();
        LR.setExternal_FI();
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

    Response rocket_money_out() throws ParseException, IOException {

        JSONObject requestBody = new JSONObject();

        requestBody.put("amount", LR.getAmount());
        requestBody.put("credential", LR.getCredential());
        requestBody.put("location", LR.getLocation());
        requestBody.put("note", LR.getNote());
        requestBody.put("receiver", LR.getReceiver());
        requestBody.put("fpAuth", LR.getFpAuth());
        requestBody.put("is_fp_auth", LR.getIs_fp_auth());
        requestBody.put("request_id", LR.getRequest_id());
        requestBody.put("external_receiver", LR.getExternal_receiver());
        requestBody.put("external_FI", LR.getExternal_FI());

        Response response = given().
                header("content-type", "application/json").
                header("Authorization", login()).
                body(requestBody).
        when().
                post("https://stgnpapigw.nobopay.com/api/v1/transaction/tk/send-money/external");

        return response;
    }
}