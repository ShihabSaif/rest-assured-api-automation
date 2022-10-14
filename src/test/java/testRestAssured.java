import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
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
                statusCode(200);
    }

    @Test
    void test_06_DELETE() {
        given().
                delete("https://reqres.in/api/users/2").
        then().
                statusCode(204);
    }
}
