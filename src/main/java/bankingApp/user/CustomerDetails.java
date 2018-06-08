package bankingApp.user;

import java.io.Serializable;

public class CustomerDetails implements Serializable {

    private String customerUserId;
    private String customerName;
    private String customerEmailId;
    private String customerMobileNumber;
    //TODO change this to list of account numbers
    private String customerAccountNumber;

    public CustomerDetails(String customerUserId, String customerName, String customerEmailId, String customerMobileNumber) {
        this.customerUserId = customerUserId;
        this.customerName = customerName;
        this.customerEmailId = customerEmailId;
        this.customerMobileNumber = customerMobileNumber;
    }

    public String getCustomerAccountNumber() {
        return customerAccountNumber;
    }

    public void setCustomerAccountNumber(String customerAccountNumber) {
        this.customerAccountNumber = customerAccountNumber;
    }


    public String getCustomerUserId() {
        return customerUserId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmailId() {
        return customerEmailId;
    }

    public String getCustomerMobileNumber() {
        return customerMobileNumber;
    }

    public void setCustomerUserId(String customerUserId) {
        this.customerUserId = customerUserId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerEmailId(String customerEmailId) {
        this.customerEmailId = customerEmailId;
    }

    public void setCustomerMobileNumber(String customerMobileNumber) {
        this.customerMobileNumber = customerMobileNumber;
    }
}
