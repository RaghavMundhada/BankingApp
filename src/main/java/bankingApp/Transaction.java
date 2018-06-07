package bankingApp;

import java.sql.Timestamp;
import java.text.DecimalFormat;

public class Transaction {
    private int transactionId;
    private int payerId;
    private int payeeId;
    private int payerAccountNumber;
    private int payeeAccountNumber;

    private Double transactionAmount;
    private String transactionType;
    private String transactionStatus;
    private String timestampTransactionCreated;
    private String timestampTransactionUpdated;


    public int getPayerAccountNumber() {
        return payerAccountNumber;
    }

    public void setPayerAccountNumber(int payerAccountNmber) {
        this.payerAccountNumber = payerAccountNmber;
    }

    public int getPayeeAccountNumber() {
        return payeeAccountNumber;
    }

    public void setPayeeAccountNumber(int payeeAccountNumber) {
        this.payeeAccountNumber = payeeAccountNumber;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getPayerId() {
        return payerId;
    }

    public void setPayerId(int payerId) {
        this.payerId = payerId;
    }

    public int getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(int payeeId) {
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
