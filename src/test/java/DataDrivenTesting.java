import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;

import static io.restassured.RestAssured.*;

public class DataDrivenTesting extends DataProviderForDDT {

//    @Test(dataProvider = "dataForPost")
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

//    @Test(dataProvider = "dataForDelete")
    static void test_DELETE(int id) {
        given().
                delete("https://reqres.in/api/users/" + id).
        then().
                statusCode(204);
    }

    static Response test_excel_POST() throws IOException {

        JSONObject requestBody = new JSONObject();

        String excelPath = "E:/RestAssured/RestAssuredAPITesting/data/payload.xlsx";

        String sheetName = "sheet1";

        ExcelUtils excel = new ExcelUtils(excelPath,sheetName);

        requestBody.put("name", excel.getCellData(1,0));
        requestBody.put("job", excel.getCellData(1, 1));

        Response response = given().
                body(requestBody.toJSONString()).
        when().
                post("https://reqres.in/api/users");
        return response;
    }

    static Response test_excel_post02() throws IOException {
        JSONObject requestBody = new JSONObject();

        String excelPath = "E:/RestAssured/RestAssuredAPITesting/data/payload.xlsx";

        File excelFile = new File(excelPath);
        FileInputStream inputData = new FileInputStream(excelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(inputData);
        XSSFSheet firstSheet = workbook.getSheetAt(0);

        String name = firstSheet.getRow(1).getCell(0).getStringCellValue();
        System.out.println(name);
        String job = firstSheet.getRow(1).getCell(1).getStringCellValue();
        System.out.println(job);

        requestBody.put("name", name);
        requestBody.put("job", job);

        Response response = given().
                body(requestBody.toJSONString()).
        when().
                post("https://reqres.in/api/users");
        if (response.statusCode() == 201)
        {
            firstSheet.getRow(1).createCell(2).setCellType(CellType.STRING);
            firstSheet.getRow(1).createCell(3).setCellType(CellType.STRING);
            firstSheet.getRow(1).getCell(2).setCellValue(response.statusCode());
            firstSheet.getRow(1).getCell(3).setCellValue(response.asString());
        }
        FileOutputStream resultFile = new FileOutputStream(excelFile);
        workbook.write(resultFile);
        workbook.close();
        resultFile.close();

        return response;
    }
    public static void main(String[] args) throws IOException {
        Response resp = test_excel_post02();
        System.out.println("response is " + resp.getBody().asString());

    }
}
