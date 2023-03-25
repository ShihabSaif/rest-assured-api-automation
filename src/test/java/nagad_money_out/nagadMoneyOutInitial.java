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