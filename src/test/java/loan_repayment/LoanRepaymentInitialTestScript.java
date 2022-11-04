package loan_repayment;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import loan_repayment.LoanRepaymentInitial;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class LoanRepaymentInitialTestScript {

    @Test
    public void test_loan_repayment() throws ParseException, IOException {
        LoanRepaymentInitial tk = new LoanRepaymentInitial();
        Response resp = tk.loan_repayment();

        //get specific node value from response
        JsonPath jsp = resp.jsonPath();
        Float jspAMount = jsp.get("amount");

        //get whole response body in a string readable format
        ResponseBody rspBody = resp.getBody();
        String finalRespBody = rspBody.asString();

        System.out.println(finalRespBody);

//        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test
    public void test_loan_repayment_excel() throws ParseException, IOException {

        String excelPath = "E:/RestAssured/RestAssuredAPITesting/data/loan_repayment.xlsx";

        File excelFile = new File(excelPath);
        FileInputStream inputData = new FileInputStream(excelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(inputData);
        XSSFSheet firstSheet = workbook.getSheetAt(0);

        FileOutputStream resultFile;

        int totalRowNumber = firstSheet.getLastRowNum() + 1;

        LoanRepaymentInitial tk = new LoanRepaymentInitial();
        for(int i=1; i < totalRowNumber; i++)
        {
            Response resp = tk.loan_repayment_with_excel(i);

            //get whole response body in a string readable format
            ResponseBody rspBody = resp.getBody();
            String finalRespBody = rspBody.asString();

            System.out.println(finalRespBody);

            firstSheet.getRow(i).createCell(11).setCellType(CellType.STRING);
            firstSheet.getRow(i).createCell(12).setCellType(CellType.STRING);
            firstSheet.getRow(i).getCell(11).setCellValue(resp.statusCode());
            firstSheet.getRow(i).getCell(12).setCellValue(resp.asString());

            resultFile = new FileOutputStream(excelFile);
            workbook.write(resultFile);
        }

    }
}
