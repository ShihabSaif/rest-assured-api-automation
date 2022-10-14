import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DataDrivenTesting extends DataProviderForDDT {

    @Test(dataProvider = "dataForPost")
    void test_POST(String name, String job) {

        JSONObject requestBody = new JSONObject();

        requestBody.put("name", name);
        requestBody.put("job", job);

        given().
                body(requestBody.toJSONString()).
                when().
                post("https://reqres.in/api/users").
                then().
                statusCode(201);
    }

    @Test(dataProvider = "dataForDelete")
    void test_DELETE(int id) {
        given().
                delete("https://reqres.in/api/users/" + id).
                then().
                statusCode(204);
    }
}
