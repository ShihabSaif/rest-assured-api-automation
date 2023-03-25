package send_money;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class sendMoneyDTO {
    String amount;
    String credential;
    String receiver;
    String receiver_wallet_no;
    Long request_id;

    public String prop_amount;
    public String prop_credential;
    public String prop_receiver;
    public String prop_receiver_wallet_no;
    public Long prop_request_id;

    Random r = new Random();

    public static Properties propMain = new Properties();

    public sendMoneyDTO() throws IOException {
        FileInputStream fisDev = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/send_money/sendMoney.properties");
        propMain.load(fisDev);

        prop_amount = propMain.getProperty("amount");
        prop_credential = propMain.getProperty("credential");
        prop_receiver = propMain.getProperty("receiver");
        prop_receiver_wallet_no = propMain.getProperty("receiver_wallet_no");
        prop_request_id = r.nextInt(1_000_000_000) + (r.nextInt(90) + 10) * 1_000_000_000L;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount() {
        this.amount = prop_amount;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential() {
        this.credential = prop_credential;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver() {
        this.receiver = prop_receiver;
    }

    public String getReceiver_wallet_no() {
        return receiver_wallet_no;
    }

    public void setReceiver_wallet_no() {
        this.receiver_wallet_no = prop_receiver_wallet_no;
    }

    public Long getRequest_id() {
        return request_id;
    }

    public void setRequest_id() {
        this.request_id = prop_request_id;
    }
}
