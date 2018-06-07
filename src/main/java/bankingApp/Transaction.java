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
    private String approver;
    private boolean critical;
    private Timestamp timestampTransactionCreated;
    private Timestamp timestampTransactionUpdated;


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

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public boolean isCritical() {
        return critical;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }

    public Timestamp getTimestampTransactionCreated() {
        return timestampTransactionCreated;
    }

    public void setTimestampTransactionCreated(Timestamp timestampTransactionCreated) {
        this.timestampTransactionCreated = timestampTransactionCreated;
    }

    public Timestamp getTimestampTransactionUpdated() {
        return timestampTransactionUpdated;
    }

    public void setTimestampTransactionUpdated(Timestamp timestampTransactionUpdated) {
        this.timestampTransactionUpdated = timestampTransactionUpdated;
    }
}
