import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class testRestAssured {
    @Test
    void test01(){
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println("status code is : " + response.getStatusCode());
        System.out.println("response body is : " + response.getBody().asString());
    }
}
