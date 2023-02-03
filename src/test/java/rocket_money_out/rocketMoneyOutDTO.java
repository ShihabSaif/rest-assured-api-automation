package rocket_money_out;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class rocketMoneyOutDTO {
    String amount;
    String credential;
    String location;
    String note;
    String receiver;
    String fpAuth;
    String is_fp_auth;
    String request_id;
    String external_receiver;
    String external_FI;

    public static Properties propMain = new Properties();

    public String prop_amount;
    public String prop_credential;
    public String prop_location;
    public String prop_note;
    public String prop_receiver;
    public String prop_fpAuth;
    public String prop_is_fp_auth;
    public String prop_request_id;
    public String prop_external_receiver;
    public String prop_external_FI;

    public rocketMoneyOutDTO() throws IOException {
        FileInputStream fisDev = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/rocket_money_out/rocket_money_out.properties");
        propMain.load(fisDev);

        prop_amount = propMain.getProperty("amount");
        prop_credential = propMain.getProperty("credential");
        prop_location = propMain.getProperty("location");
        prop_note = propMain.getProperty("note");
        prop_receiver = propMain.getProperty("receiver");
        prop_fpAuth = propMain.getProperty("fpAuth");
        prop_is_fp_auth = propMain.getProperty("is_fp_auth");
        prop_request_id = propMain.getProperty("request_id");
        prop_external_receiver = propMain.getProperty("external_receiver");
        prop_external_FI = propMain.getProperty("external_FI");
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

    public String getLocation() {
        return location;
    }

    public void setLocation() {
        this.location = prop_location;
    }

    public String getNote() {
        return note;
    }

    public void setNote() {
        this.note = prop_note;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver() {
        this.receiver = prop_receiver;
    }

    public String getFpAuth() {
        return fpAuth;
    }

    public void setFpAuth() {
        this.fpAuth = prop_fpAuth;
    }

    public String getIs_fp_auth() {
        return is_fp_auth;
    }

    public void setIs_fp_auth() {
        this.is_fp_auth = prop_is_fp_auth;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id() {
        this.request_id = prop_request_id;
    }

    public String getExternal_receiver() {
        return external_receiver;
    }

    public void setExternal_receiver() {
        this.external_receiver = prop_external_receiver;
    }

    public String getExternal_FI() {
        return external_FI;
    }

    public void setExternal_FI() {
        this.external_FI = prop_external_FI;
    }
}
