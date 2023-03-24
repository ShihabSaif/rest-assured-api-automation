package nagad_money_out;

import io.restassured.response.Response;
import mobile_recharge.mobileRechargeDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class nagadMoneyOutInitial {

    nagadMoneyOutDTO LR;

    {
        try {
            LR = new nagadMoneyOutDTO();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public nagadMoneyOutInitial() {
        LR.setFromAc();
        LR.setAmount();
        LR.setRequestId();
        LR.setFinancialInstitute();
        LR.setToAc();
        LR.setChannel();
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

    Response nagad_money_out(String loginToken) throws ParseException, IOException {

        JSONObject requestBody = new JSONObject();

        requestBody.put("fromAc", LR.getFromAc());
        requestBody.put("amount", LR.getAmount());
        requestBody.put("requestId", LR.getRequestId());
        requestBody.put("financialInstitute", LR.getFinancialInstitute());
        requestBody.put("toAc", LR.getToAc());
        requestBody.put("channel", LR.getChannel());

        Response response = given().
                header("content-type", "application/json").
                header("Authorization", loginToken).
                body(requestBody).
        when().
                post("http://10.9.0.77:6060/tallypay-to-fi-producer/api/v1/external/send/money");

        return response;
    }
}