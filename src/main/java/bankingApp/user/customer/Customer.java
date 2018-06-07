package bankingApp.user.customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {

    private String userId;
    private String name;
    private String emailId;
    private String mobileNumber;
    //TODO change this to list of account numbers
    private int accountNumber;



    public Customer(String userId, String name, String emailId, String mobileNumber) {
        this.userId = userId;
        this.name = name;
        this.emailId = emailId;
        this.mobileNumber = mobileNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }


    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
