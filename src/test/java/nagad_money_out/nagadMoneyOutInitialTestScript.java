package nagad_money_out;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import mobile_recharge.mobileRechargeInitial;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

public class nagadMoneyOutInitialTestScript {

    @Test
    public void test_nagad_money_out() throws ParseException, IOException {
        nagadMoneyOutInitial tk = new nagadMoneyOutInitial();
        Response resp = tk.nagad_money_out();

        //get whole response body in a string readable format
        ResponseBody rspBody = resp.getBody();
        String finalRespBody = rspBody.asString();

        System.out.println(finalRespBody);

    }
}