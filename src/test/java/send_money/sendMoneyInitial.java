package send_money;

import io.restassured.response.Response;
import nagad_money_out.nagadMoneyOutDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class sendMoneyInitial {
    sendMoneyDTO send;

    {
        try {
            send = new sendMoneyDTO();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public sendMoneyInitial()
    {
        send.setAmount();
        send.setCredential();
        send.setReceiver();
        send.setReceiver_wallet_no();
        send.setRequest_id();
    }

    Response send_money(String loginToken) throws ParseException, IOException {

        JSONObject requestBody = new JSONObject();

        requestBody.put("amount", send.getAmount());
        requestBody.put("credential", send.getCredential());
        requestBody.put("receiver", send.getReceiver());
        requestBody.put("receiver_wallet_no", send.getReceiver_wallet_no());
        requestBody.put("request_id", send.getRequest_id());

        Response response = given().
                header("content-type", "application/json").
                header("Authorization", loginToken).
                body(requestBody).
                when().
                post("https://stgnpapigw.nobopay.com/api/v1/transaction/tk/send-money");

        return response;
    }
}
