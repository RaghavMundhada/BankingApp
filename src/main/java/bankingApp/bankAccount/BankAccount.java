package bankingApp.bankAccount;

import bankingApp.Transaction;
import bankingApp.user.CustomerDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BankAccount implements Serializable{

    private String accountOwnerId;
    private String accountNumber;
    private Double balance;

    public BankAccount(String accountOwnerId, String accountNumber) {
        this.accountOwnerId = accountOwnerId;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        transactions = new ArrayList<>();

    }


    private List<Transaction> transactions ;


    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getAccountOwnerId() {
        return accountOwnerId;
    }

    public void setAccountOwnerId(String accountOwnerId) {
        this.accountOwnerId = accountOwnerId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
