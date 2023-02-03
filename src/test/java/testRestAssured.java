import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;

public class testRestAssured {
    @Test
    void test01() {
        Response response = get("https://reqres.in/api/users?page=2");
        System.out.println("status code is : " + response.getStatusCode());
        System.out.println("response body is : " + response.getBody().asString());
    }

    @Test
    void test02_GET() {
        given()
                .header("content-type", "application/json")
                .get("https://reqres.in/api/users/2")
        .then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.first_name", equalTo("Janet"))
                .log().all();
    }

    @Test
    void test03_01_POST() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "shihab");
        requestBody.put("job", "SQA");

        Response response = post("https://reqres.in/api/users");

        given().
                body(requestBody.toJSONString()).
        when().
                post("https://reqres.in/api/users").
        then().
                statusCode(201);
    }

    @Test
    void test03_POST() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "shihab");
        requestBody.put("job", "SQA");

        given().
                body(requestBody.toJSONString()).
        when().
                post("https://reqres.in/api/users").
        then().
                statusCode(201);
    }

    @Test
    void test04_PUT() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "shihab");
        requestBody.put("job", "SQA");

        given().
                body(requestBody.toJSONString()).
        when().
                put("https://reqres.in/api/users/2").
        then().
                statusCode(200);
    }

    @Test
    void test05_PATCH() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "shihab");
        requestBody.put("job", "SQA");

        given().
                body(requestBody.toJSONString()).
        when().
                patch("https://reqres.in/api/users/2").
        then().
                statusCode(201);
    }

    @Test
    void test_06_DELETE() {
        given().
                delete("https://reqres.in/api/users/2").
        then().
                statusCode(205);
    }

    @Test
    void login() throws ParseException {
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
        System.out.println(token);
    }
}
