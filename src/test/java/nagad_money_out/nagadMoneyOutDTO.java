package nagad_money_out;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class nagadMoneyOutDTO {
    String fromAc;
    String amount;
    Long requestId;
    String financialInstitute;
    String toAc;
    String channel;

    public static Properties propMain = new Properties();

    public String prop_fromAc;
    public String prop_amount;
    public Long prop_requestId;
    public String prop_financialInstitute;
    public String prop_toAc;
    public String prop_channel;

    Random r = new Random();

    public nagadMoneyOutDTO() throws IOException {
        FileInputStream fisDev = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/nagad_money_out/nagad_money_out.properties");
        propMain.load(fisDev);

        prop_fromAc = propMain.getProperty("fromAc");
        prop_amount = propMain.getProperty("amount");
        prop_requestId = r.nextInt(1_000_000_000) + (r.nextInt(90) + 10) * 1_000_000_000L;
        prop_financialInstitute = propMain.getProperty("financialInstitute");
        prop_toAc = propMain.getProperty("toAc");
        prop_channel = propMain.getProperty("channel");
    }

    public String getFromAc() {
        return fromAc;
    }

    public void setFromAc() {
        this.fromAc = prop_fromAc;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount() {
        this.amount = prop_amount;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId() {
        this.requestId = prop_requestId;
    }

    public String getFinancialInstitute() {
        return financialInstitute;
    }

    public void setFinancialInstitute() {
        this.financialInstitute = prop_financialInstitute;
    }

    public String getToAc() {
        return toAc;
    }

    public void setToAc() {
        this.toAc = prop_toAc;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel() {
        this.channel = prop_channel;
    }
}