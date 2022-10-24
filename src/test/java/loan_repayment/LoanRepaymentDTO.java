package loan_repayment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static java.lang.Boolean.valueOf;

public class LoanRepaymentDTO {
    String amount;
    String credential;
    String location;
    String receiver;
    String fpAuth;
    String is_fp_auth;
    String requestId;
    String externalFI;
    String loanAccountNo;
    String loanCardNo;
    String note;

    public static Properties propMain = new Properties();

    public String propAmount;
    public String propCredential;
    public String propLocation;
    public String propReceiver;
    public String propFpAuth;
    public String propIs_Fp_Auth;
    public String propRequestId;
    public String propExternalFI;
    public String propLoanAccountNo;
    public String propLoanCardNo;
    public String propNote;

    public LoanRepaymentDTO() throws IOException {
        FileInputStream fisDev = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/loan_repayment/loan_repayment.properties");
        propMain.load(fisDev);

        propAmount = propMain.getProperty("amount");
        propCredential = propMain.getProperty("credential");
        propLocation = propMain.getProperty("location");
        propReceiver = propMain.getProperty("receiver");
        propFpAuth = propMain.getProperty("fpAuth");
        propIs_Fp_Auth = propMain.getProperty("is_fp_auth");
        propRequestId = propMain.getProperty("requestId");
        propExternalFI = propMain.getProperty("externalFI");
        propLoanAccountNo = propMain.getProperty("loanAccountNo");
        propLoanCardNo = propMain.getProperty("loanCardNo");
        propNote = propMain.getProperty("note");
    }



    public String getAmount() {
        return amount;
    }

    public void setAmount() {
        this.amount = propAmount;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential() {
        this.credential = propCredential;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation() {
        this.location = propLocation;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver() {
        this.receiver = propReceiver;
    }

    public String getFpAuth() {
        return fpAuth;
    }

    public void setFpAuth() {
        this.fpAuth = propFpAuth;
    }

    public String getIs_fp_auth() {
        return is_fp_auth;
    }

    public void setIs_fp_auth() {
        this.is_fp_auth = propIs_Fp_Auth;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId() {
        this.requestId = propRequestId;
    }

    public String getExternalFI() {
        return externalFI;
    }

    public void setExternalFI() {
        this.externalFI = propExternalFI;
    }

    public String getLoanAccountNo() {
        return loanAccountNo;
    }

    public void setLoanAccountNo() {
        this.loanAccountNo = propLoanAccountNo;
    }

    public String getLoanCardNo() {
        return loanCardNo;
    }

    public void setLoanCardNo() {
        this.loanCardNo = propLoanCardNo;
    }

    public String getNote() {
        return note;
    }

    public void setNote() {
        this.note = propNote;
    }
}
