package bankingApp.user.customer;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String userId;
    private String name;
    private String emailId;
    private String mobileNumber;

    public Customer(String userId, String name, String emailId, String mobileNumber) {
        this.userId = userId;
        this.name = name;
        this.emailId = emailId;
        this.mobileNumber = mobileNumber;
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
