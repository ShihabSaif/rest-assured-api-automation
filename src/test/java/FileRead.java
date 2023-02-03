import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FileRead {
    public static Map<String, String> fileAndEnv = new HashMap<String ,String>();
    public static Properties propMain = new Properties();
    public static Map<String, String> envAndFile() throws IOException {
        FileInputStream fisDev = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/loan_payment/loan_payment.properties");
        propMain.load(fisDev);

        fileAndEnv.put("amount", propMain.getProperty("amount"));
        fileAndEnv.put("credential", propMain.getProperty("credential"));
        fileAndEnv.put("location", propMain.getProperty("location"));
        fileAndEnv.put("receiver", propMain.getProperty("receiver"));
        fileAndEnv.put("fpAuth", propMain.getProperty("fpAuth"));
        fileAndEnv.put("is_fp_auth", propMain.getProperty("is_fp_auth"));
        fileAndEnv.put("requestId", propMain.getProperty("requestId"));
        fileAndEnv.put("loan_amount", propMain.getProperty("loan_amount"));
        fileAndEnv.put("merchant_wallet_no", propMain.getProperty("merchant_wallet_no"));
        fileAndEnv.put("external_customer", propMain.getProperty("external_customer"));
        fileAndEnv.put("note", propMain.getProperty("note"));

        return fileAndEnv;
    }

    public static void main(String args[]) throws IOException  //static method
    {
        Map<String, String> result = envAndFile();
        System.out.println("config is : " + result);
    }
}
