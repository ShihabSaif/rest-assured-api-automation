import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TallyKhata {
    @Test
    void test_login() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("app_type", "TALLYKHATA");
        requestBody.put("device_id", "1c2ea1a42bcd5c93");
        requestBody.put("device_type", "ANDROID");
        requestBody.put("mobile_number", "01621215877");
        requestBody.put("password", "9316");
        requestBody.put("uuid", "0069a6b1-b510-443b-b51b-db84889ef9f6");

        given().
                header("x-auth-token", "4lSE5BTGIi9oiwjhLC5PDDXJ9Rdlof0Z3hpwMLZL").
                header("content-type", "application/json").
                header("Authorization", "Basic cHJvZ290aV9xYTpwcjBnMHQxQDIwMnR3bw==").
                header("x-device-id", "30c3f35c-d6b2-47b4-ba86-b81eea6af4a2").
                body(requestBody.toJSONString()).
        when().
                post("https://stgqa.tallykhata.com/wallet/api/tp-proxy/user/login").
        then().
                statusCode(200).
                body("name", equalTo("A.B.M.Shihab Uddin")).
                log().all();
    }

    @Test
    void test_recharge() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("amount", 21);
        requestBody.put("credential", "9316");
        requestBody.put("location", null);
        requestBody.put("receiver", "01621215877");
        requestBody.put("mobile_operator", "GP");
        requestBody.put("fpAuth", false);
        requestBody.put("is_fp_auth", false);
        requestBody.put("request_id", "2665774359566_4423");
        requestBody.put("mobile_type", "PREPAID");
        requestBody.put("receiver_mobile", "01621215877");
        requestBody.put("receiver_name", "A.B.M.Shihab Uddin");

        given().
                header("content-type", "application/json").
                header("Authorization", "token eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJUU0ROZVZtSlVmVFRvak96MHNQRU4zdnBfbXNWTk9JSUR0RHlrVzFaSS1ZIn0.eyJleHAiOjE2NjU3NzU3NjYsImlhdCI6MTY2NTc3NTQ2NiwianRpIjoiMmJmNTBmZTgtNDBkZC00YjE2LThlMWMtZGYwZGRkNjY4NDUxIiwiaXNzIjoiaHR0cDovLzEwLjkuMC43Nzo2MDcwL2F1dGgvcmVhbG1zL25vYm9wYXlfd2FsbGV0X2hvbGRlciIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiI2NWY3MTgxYi00YjExLTQ0MzctYTIyZC1iMTU3M2NjYTBkNTMiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJub2JvcGF5X2d3Iiwic2Vzc2lvbl9zdGF0ZSI6ImI3YWFmY2JlLWE5ZjUtNGI2MC1hYWRmLTc4MWM4NThhYzFiMSIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiZGVmYXVsdC1yb2xlcy1ub2JvcGF5X3dhbGxldF9ob2xkZXIiLCJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJlbWFpbCBwcm9maWxlIiwic2lkIjoiYjdhYWZjYmUtYTlmNS00YjYwLWFhZGYtNzgxYzg1OGFjMWIxIiwiZnBLZXlOYW1lIjoiIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJ3YWxsZXRUeXBlIjoiQ1VTVE9NRVIiLCJrY1VzZXJJZCI6IjY1ZjcxODFiLTRiMTEtNDQzNy1hMjJkLWIxNTczY2NhMGQ1MyIsInVzZXJUeXBlIjoiV0FMTEVUX1VTRVIiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiIwMTYyMTIxNTg3NyIsInVzZXJJZCI6NDQyMSwid2FsbGV0Tm8iOiIwMTYyMTIxNTg3NyJ9.HfGFC-kuwBuNZ3w_8d9aVKqSHnl9pOU75YQb5MtAdUrmT4TKkDcbjMeHh1nOYtns4VS5qBCCqn9qujdV0hPb6AzPfgyg_clhC-p2FasuVGOUFCcxyN8kTP60EFbK9NPyELsEvtwB307J-M-fmOOKh8Z0sByCqyQZZWbxCrZBk3V-HVq6gIv6b_FUQ4I9sa2Gnh4fMAFX1km7ABsHoOcsJv8B586en-d5bYKGsecuBJwS4ge5xJURr0P5q79y8dWEdE9LY-j1LNrocjrzlrFXXMtmegdlBkCYPrI7JdfJDcivc_5nQQ38D9KjzPuGnZB2J6FCfiOuayQg2L4fVBRf-g").
                body(requestBody.toJSONString()).
        when().
                post("https://stgnpapigw.nobopay.com/api/v1/transaction/recharge-mobile").
        then().
                statusCode(200).
                log().all();
    }

    @Test
    void test_loan_payment() {
        JSONObject requestBody = new JSONObject();

        requestBody.put("amount", 20);
        requestBody.put("credential", "9316");
        requestBody.put("location", null);
        requestBody.put("receiver", "01800000220");
        requestBody.put("fpAuth", false);
        requestBody.put("is_fp_auth", false);
        requestBody.put("request_id", "2665774359566_4430");
        requestBody.put("loan_amount", 20);
        requestBody.put("merchant_wallet_no", "01800000220");
        requestBody.put("external_customer", "01765841854");
        requestBody.put("note", "supplier payment with loan ammount");

        given().
                header("content-type", "application/json").
                header("Authorization", "token eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJUU0ROZVZtSlVmVFRvak96MHNQRU4zdnBfbXNWTk9JSUR0RHlrVzFaSS1ZIn0.eyJleHAiOjE2NjU4NDc0NTUsImlhdCI6MTY2NTg0NzE1NSwianRpIjoiZDllZDNhNDItNDU3My00NTg5LTlmMDMtODdiNDdkZDMzNTQzIiwiaXNzIjoiaHR0cDovLzEwLjkuMC43Nzo2MDcwL2F1dGgvcmVhbG1zL25vYm9wYXlfd2FsbGV0X2hvbGRlciIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiI2NWY3MTgxYi00YjExLTQ0MzctYTIyZC1iMTU3M2NjYTBkNTMiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJub2JvcGF5X2d3Iiwic2Vzc2lvbl9zdGF0ZSI6ImVhNWZiMTliLTUzYWItNDI5YS1hMWRmLWMzMmJhNTYwMTRjNSIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiZGVmYXVsdC1yb2xlcy1ub2JvcGF5X3dhbGxldF9ob2xkZXIiLCJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJlbWFpbCBwcm9maWxlIiwic2lkIjoiZWE1ZmIxOWItNTNhYi00MjlhLWExZGYtYzMyYmE1NjAxNGM1IiwiZnBLZXlOYW1lIjoiIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJ3YWxsZXRUeXBlIjoiQ1VTVE9NRVIiLCJrY1VzZXJJZCI6IjY1ZjcxODFiLTRiMTEtNDQzNy1hMjJkLWIxNTczY2NhMGQ1MyIsInVzZXJUeXBlIjoiV0FMTEVUX1VTRVIiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiIwMTYyMTIxNTg3NyIsInVzZXJJZCI6NDQyMSwid2FsbGV0Tm8iOiIwMTYyMTIxNTg3NyJ9.FOGUkPv8Us5oEMzH5QwfKfQBp3SNIWnGpB5D7gBCswBTWlvYqRvoKgpJyAPLUqBOxN9jggUdMFdt4SWIrUnxywU5wnpUPvlNqdHJFxyqvNJSYMadCEpOFgAFI6W00eVnGE0BR3RKVhkIIKDhxj5uznLqq8IVG3jCrgSj9x7Y46c2zrELSMBen2BlAMn6HcN6SqLM95wjdvHKy-0t4LtU0VFPn-P2MePYKb_Q0asMwZ2GuK5Yt-kq9DA9WkBQ9OR8WoT_dWiAhJ6Ql6vzHaqsiqAibAu4mOpivQ_WTaFWivIWf_CFbETa9Ljyozm6xewpaR-5pa5-UWIgwgCTUZyENw").
                body(requestBody.toJSONString()).
        when().
                post("https://stgnpapigw.nobopay.com/api/v1/transaction/tk/payment/with/loan").
        then().
                statusCode(200).
                log().all();
    }

    @Test
    void test_loan_repayment() {
        JSONObject requestBody = new JSONObject();

        requestBody.put("amount", 30);
        requestBody.put("credential", "9316");
        requestBody.put("location", null);
        requestBody.put("receiver", "460196000923");
        requestBody.put("fpAuth", false);
        requestBody.put("is_fp_auth", false);
        requestBody.put("request_id", "0261180152803_2469");
        requestBody.put("externalFI", "MTB");
        requestBody.put("loanAccountNo", "460196000923");
        requestBody.put("loanCardNo", "55667788");
        requestBody.put("note", null);

        given().
                header("content-type", "application/json").
                header("Authorization", "token eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJUU0ROZVZtSlVmVFRvak96MHNQRU4zdnBfbXNWTk9JSUR0RHlrVzFaSS1ZIn0.eyJleHAiOjE2NjU4NDk0NTMsImlhdCI6MTY2NTg0OTE1MywianRpIjoiNWE4NmZiYmYtN2I4Ni00YjM4LWE1YzAtYjU3NjkxZTQ2NGNmIiwiaXNzIjoiaHR0cDovLzEwLjkuMC43Nzo2MDcwL2F1dGgvcmVhbG1zL25vYm9wYXlfd2FsbGV0X2hvbGRlciIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiI2NWY3MTgxYi00YjExLTQ0MzctYTIyZC1iMTU3M2NjYTBkNTMiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJub2JvcGF5X2d3Iiwic2Vzc2lvbl9zdGF0ZSI6IjliNTY1MGViLWQyODktNGRjZS05OGJkLTZlMDUwNTU1M2RjYyIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiZGVmYXVsdC1yb2xlcy1ub2JvcGF5X3dhbGxldF9ob2xkZXIiLCJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJlbWFpbCBwcm9maWxlIiwic2lkIjoiOWI1NjUwZWItZDI4OS00ZGNlLTk4YmQtNmUwNTA1NTUzZGNjIiwiZnBLZXlOYW1lIjoiIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJ3YWxsZXRUeXBlIjoiQ1VTVE9NRVIiLCJrY1VzZXJJZCI6IjY1ZjcxODFiLTRiMTEtNDQzNy1hMjJkLWIxNTczY2NhMGQ1MyIsInVzZXJUeXBlIjoiV0FMTEVUX1VTRVIiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiIwMTYyMTIxNTg3NyIsInVzZXJJZCI6NDQyMSwid2FsbGV0Tm8iOiIwMTYyMTIxNTg3NyJ9.BA6AWP2MZPJ0PTjdBVeVf-AJZrH1aKhrGp36mc2ou34TPXUtYCzWyLN6_PvLqTExRNWs9zD_Z5bQrW5kZtro4p8brAvVJdxgIpNTB4mVZ3fQg-3wNCd6eTTE-SqRxy3nTwpnhmVpVzqMlWhAIDgdug8lyr4LVIw9qoYTNAaLtHKuEsxLq-2n1bERKbYq_WU29UzwvZXaqLGjT714-y3u5Cq5VxRKXoOpaJxFnJoDnZkBkzfYmSSrHjk81-wjDRYB41y7MsGCTO0oCjWmrHpkEoecXosUjCG0UBCxiiBot30I1KdhKLX8AjmguB1h_F6TF5jSUmg7PiBxHJir2L8Gmg").
                header("x-request-id", "1c2ea1a42bcd5c93:TK_ANDROID:15458807928059").
                header("np-uuid", "0069a6b1-b510-443b-b51b-db84889ef9f6").
                header("np-userref", "01621215877").
                header("np-devicemodel", "RMX1921").
                header("np-appversioncode", 110).
                body(requestBody.toJSONString()).
        when().
                post("https://stgnpapigw.nobopay.com/api/v1/transaction/tk/loan/repayment").
        then().
                statusCode(400).
                log().all();
    }
}
