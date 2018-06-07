package bankingApp;

import java.sql.Timestamp;
import java.text.DecimalFormat;

public class Transaction {
    private String transactionId;
    private String payerId;
    private String payeeId;
    private String payerAccountNumber;
    private String payeeAccountNumber;

    private Double transactionAmount;
    private String transactionType;
    private String transactionStatus;
    private String timestampTransactionCreated;
    private String timestampTransactionUpdated;


    public String getPayerAccountNumber() {
        return payerAccountNumber;
    }

    public void setPayerAccountNumber(String payerAccountNmber) {
        this.payerAccountNumber = payerAccountNmber;
    }

    public String getPayeeAccountNumber() {
        return payeeAccountNumber;
    }

    public void setPayeeAccountNumber(String payeeAccountNumber) {
        this.payeeAccountNumber = payeeAccountNumber;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTimestampTransactionCreated() {
        return timestampTransactionCreated;
    }

    public void setTimestampTransactionCreated(String timestampTransactionCreated) {
        this.timestampTransactionCreated = timestampTransactionCreated;
    }

    public String getTimestampTransactionUpdated() {
        return timestampTransactionUpdated;
    }

    public void setTimestampTransactionUpdated(String timestampTransactionUpdated) {
        this.timestampTransactionUpdated = timestampTransactionUpdated;
    }
}
