package rocket_money_out;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import nagad_money_out.nagadMoneyOutInitial;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

public class rocketMoneyOutInitialTestScript {

    @Test
    public void test_rocket_money_out() throws ParseException, IOException {
        rocketMoneyOutInitial tk = new rocketMoneyOutInitial();
        Response resp = tk.rocket_money_out();

        //get whole response body in a string readable format
        ResponseBody rspBody = resp.getBody();
        String finalRespBody = rspBody.asString();

        System.out.println(finalRespBody);

    }
}