package loan_repayment;

import loan_repayment.LoanRepaymentDTO;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import io.restassured.response.Response;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class LoanRepaymentInitial {

    long requestId = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;

    LoanRepaymentDTO LR;

    {
        try {
            LR = new LoanRepaymentDTO();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public LoanRepaymentInitial() {

        LR.setAmount();
        LR.setCredential();
        LR.setLocation();
        LR.setReceiver();
        LR.setFpAuth();
        LR.setIs_fp_auth();
        LR.setRequestId();
        LR.setExternalFI();
        LR.setLoanAccountNo();
        LR.setLoanCardNo();
        LR.setNote();
    }

    public String login() throws ParseException {
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
        return token;
    }

    Response loan_repayment() throws ParseException, IOException {

        JSONObject requestBody = new JSONObject();

        requestBody.put("amount", LR.getAmount());
        requestBody.put("credential", LR.getCredential());
        requestBody.put("location", LR.getLocation());
        requestBody.put("receiver", LR.getReceiver());
        requestBody.put("fpAuth", LR.getFpAuth());
        requestBody.put("is_fp_auth", LR.getIs_fp_auth());
        requestBody.put("requestId", LR.getRequestId());
        requestBody.put("externalFI", LR.getExternalFI());
        requestBody.put("loanAccountNo", LR.getLoanAccountNo());
        requestBody.put("loanCardNo", LR.getLoanCardNo());
        requestBody.put("note", LR.getNote());

        Response response = given().
                header("content-type", "application/json").
                header("Authorization", login()).
                header("x-request-id", "1c2ea1a42bcd5c93:TK_ANDROID:15458807928059").
                header("np-uuid", "0069a6b1-b510-443b-b51b-db84889ef9f6").
                header("np-userref", "01621215877").
                header("np-devicemodel", "RMX1921").
                header("np-appversioncode", 110).
                body(requestBody).
        when().
                post("https://stgnpapigw.nobopay.com/api/v1/transaction/tk/loan/repayment");

        return response;
    }

    Response loan_repayment_with_excel(int num) throws ParseException, IOException {

        JSONObject requestBody = new JSONObject();

        String excelPath = "E:/RestAssured/RestAssuredAPITesting/data/loan_repayment.xlsx";

        File excelFile = new File(excelPath);
        FileInputStream inputData = new FileInputStream(excelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(inputData);
        XSSFSheet firstSheet = workbook.getSheetAt(0);

        DataFormatter formatter = new DataFormatter();

        String amount = formatter.formatCellValue(firstSheet.getRow(num).getCell(0));
        System.out.println(amount);

        String credential = formatter.formatCellValue(firstSheet.getRow(num).getCell(1));
        System.out.println(credential);

        String location = firstSheet.getRow(num).getCell(2).getStringCellValue();
        System.out.println(location);

        String receiver = formatter.formatCellValue(firstSheet.getRow(num).getCell(3));
        System.out.println(receiver);

        String fpAuth = formatter.formatCellValue(firstSheet.getRow(num).getCell(4));
        System.out.println(fpAuth);

        String is_fp_auth = formatter.formatCellValue(firstSheet.getRow(num).getCell(5));
        System.out.println(is_fp_auth);

        String requestId = formatter.formatCellValue(firstSheet.getRow(num).getCell(6));
        System.out.println(requestId);

        String externalFI = firstSheet.getRow(num).getCell(7).getStringCellValue();
        System.out.println(externalFI);

        String loanAccountNo = formatter.formatCellValue(firstSheet.getRow(num).getCell(8));
        System.out.println(loanAccountNo);

        String loanCardNo = formatter.formatCellValue(firstSheet.getRow(num).getCell(9));
        System.out.println(loanCardNo);

        String note = firstSheet.getRow(num).getCell(10).getStringCellValue();
        System.out.println(note);


        requestBody.put("amount", amount);
        requestBody.put("credential", credential);
        requestBody.put("location", location);
        requestBody.put("receiver", receiver);
        requestBody.put("fpAuth", fpAuth);
        requestBody.put("is_fp_auth", is_fp_auth);
        requestBody.put("requestId", requestId);
        requestBody.put("externalFI", externalFI);
        requestBody.put("loanAccountNo", loanAccountNo);
        requestBody.put("loanCardNo", loanCardNo);
        requestBody.put("note", note);

        Response response = given().
                header("content-type", "application/json").
                header("Authorization", login()).
                header("x-request-id", "1c2ea1a42bcd5c93:TK_ANDROID:15458807928059").
                header("np-uuid", "0069a6b1-b510-443b-b51b-db84889ef9f6").
                header("np-userref", "01621215877").
                header("np-devicemodel", "RMX1921").
                header("np-appversioncode", 110).
                body(requestBody).
        when().
                post("https://stgnpapigw.nobopay.com/api/v1/transaction/tk/loan/repayment");

        return response;
    }
}