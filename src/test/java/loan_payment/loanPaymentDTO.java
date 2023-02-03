package loan_payment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class loanPaymentDTO {
    String amount;
    String credential;
    String location;
    String receiver;
    String fpAuth;
    String is_fp_auth;
    String requestId;
    String loan_amount;
    String merchant_wallet_no;
    String external_customer;
    String note;

    public static Properties propLoanPayment = new Properties();

    public String prop_Amount;
    public String prop_Credential;
    public String prop_Location;
    public String prop_Receiver;
    public String prop_FpAuth;
    public String prop_Is_Fp_Auth;
    public String prop_RequestId;
    public String prop_loan_amount;
    public String prop_merchant_wallet_no;
    public String prop_external_customer;
    public String prop_Note;

    public loanPaymentDTO() throws IOException {
        FileInputStream fisDev = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/loan_payment/loan_payment.properties");
        propLoanPayment.load(fisDev);

        prop_Amount = propLoanPayment.getProperty("amount");
        prop_Credential = propLoanPayment.getProperty("credential");
        prop_Location = propLoanPayment.getProperty("location");
        prop_Receiver = propLoanPayment.getProperty("receiver");
        prop_FpAuth = propLoanPayment.getProperty("fpAuth");
        prop_Is_Fp_Auth = propLoanPayment.getProperty("is_fp_auth");
        prop_RequestId = propLoanPayment.getProperty("requestId");
        prop_loan_amount = propLoanPayment.getProperty("loan_amount");
        prop_merchant_wallet_no = propLoanPayment.getProperty("merchant_wallet_no");
        prop_external_customer = propLoanPayment.getProperty("external_customer");
        prop_Note = propLoanPayment.getProperty("note");
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount() {
        this.amount = prop_Amount;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential() {
        this.credential = prop_Credential;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation() {
        this.location = prop_Location;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver() {
        this.receiver = prop_Receiver;
    }

    public String getFpAuth() {
        return fpAuth;
    }

    public void setFpAuth() {
        this.fpAuth = prop_FpAuth;
    }

    public String getIs_fp_auth() {
        return is_fp_auth;
    }

    public void setIs_fp_auth() {
        this.is_fp_auth = prop_Is_Fp_Auth;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId() {
        this.requestId = prop_RequestId;
    }

    public String getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount() {
        this.loan_amount = prop_loan_amount;
    }

    public String getMerchant_wallet_no() {
        return merchant_wallet_no;
    }

    public void setMerchant_wallet_no() {
        this.merchant_wallet_no = prop_merchant_wallet_no;
    }

    public String getExternal_customer() {
        return external_customer;
    }

    public void setExternal_customer() {
        this.external_customer = prop_external_customer;
    }

    public String getNote() {
        return note;
    }

    public void setNote() {
        this.note = prop_Note;
    }
}
