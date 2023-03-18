package mobile_recharge;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class mobileRechargeDTO {
    String receiver_mobile;
    String mobile_operator;
    String mobile_type;
    String receiver_name;
    String wallet;
    String is_fp_auth;
    String credential;
    String amount;
    Long request_id;

    public static Properties propMain = new Properties();

    public String prop_receiver_mobile;
    public String prop_mobile_operator;
    public String prop_mobile_type;
    public String prop_receiver_name;
    public String prop_wallet;
    public String prop_is_fp_auth;
    public String prop_credential;
    public String prop_amount;
    public Long prop_request_id;

    Random r = new Random();

    public mobileRechargeDTO() throws IOException {
        FileInputStream fisDev = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/mobile_recharge/mobile_recharge.properties");
        propMain.load(fisDev);

        prop_receiver_mobile = propMain.getProperty("receiver_mobile");
        prop_mobile_operator = propMain.getProperty("mobile_operator");
        prop_mobile_type = propMain.getProperty("mobile_type");
        prop_receiver_name = propMain.getProperty("receiver_name");
        prop_wallet = propMain.getProperty("wallet");
        prop_is_fp_auth = propMain.getProperty("is_fp_auth");
        prop_credential = propMain.getProperty("credential");
        prop_amount = propMain.getProperty("amount");
        prop_request_id = r.nextInt(1_000_000_000) + (r.nextInt(90) + 10) * 1_000_000_000L;
    }

    public String getReceiver_mobile() {
        return receiver_mobile;
    }

    public void setReceiver_mobile() {
        this.receiver_mobile = prop_receiver_mobile;
    }

    public String getMobile_operator() {
        return mobile_operator;
    }

    public void setMobile_operator() {
        this.mobile_operator = prop_mobile_operator;
    }

    public String getMobile_type() {
        return mobile_type;
    }

    public void setMobile_type() {
        this.mobile_type = prop_mobile_type;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name() {
        this.receiver_name = prop_receiver_name;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet() {
        this.wallet = prop_wallet;
    }

    public String getIs_fp_auth() {
        return is_fp_auth;
    }

    public void setIs_fp_auth() {
        this.is_fp_auth = prop_is_fp_auth;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential() {
        this.credential = prop_credential;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount() {
        this.amount = prop_amount;
    }

    public Long getRequest_id() {
        return request_id;
    }

    public void setRequest_id() {
        this.request_id = prop_request_id;
    }
}
