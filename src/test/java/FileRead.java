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
        FileInputStream fisDev = new FileInputStream(System.getProperty("user.dir") + "/inputs/loan_repayment.properties");
        propMain.load(fisDev);

        fileAndEnv.put("amount", propMain.getProperty("amount"));
        fileAndEnv.put("credential", propMain.getProperty("credential"));
        fileAndEnv.put("location", propMain.getProperty("location"));
        fileAndEnv.put("receiver", propMain.getProperty("receiver"));
        fileAndEnv.put("fpAuth", propMain.getProperty("fpAuth"));
        fileAndEnv.put("is_fp_auth", propMain.getProperty("is_fp_auth"));
        fileAndEnv.put("requestId", propMain.getProperty("requestId"));
        fileAndEnv.put("externalFI", propMain.getProperty("externalFI"));
        fileAndEnv.put("loanAccountNo", propMain.getProperty("loanAccountNo"));
        fileAndEnv.put("loanCardNo", propMain.getProperty("loanCardNo"));
        fileAndEnv.put("note", propMain.getProperty("note"));

        return fileAndEnv;
    }

    public static void main(String args[]) throws IOException  //static method
    {
        Map<String, String> result = envAndFile();
        System.out.println("config is : " + result);
    }
}
