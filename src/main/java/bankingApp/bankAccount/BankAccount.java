package bankingApp.bankAccount;

import bankingApp.Transaction;
import bankingApp.user.CustomerDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BankAccount implements Serializable{
    public enum TypeOfAccount{
        SAVINGS, CHECKING, CREDIT
    }

    private CustomerDetails accountOwner;
    private String accountNumber;
    private Double balance;

    public BankAccount(CustomerDetails accountOwner, String accountNumber) {
        this.accountOwner = accountOwner;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        transactions = new ArrayList<>();
        creditTransactions = new ArrayList<>();
        debitTransactions = new ArrayList<>();
    }

    private TypeOfAccount typeOfAccount;
    private List<Transaction> transactions ;
    private List<Transaction> creditTransactions;
    private List<Transaction> debitTransactions;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<Transaction> getCreditTransactions() {
        return creditTransactions;
    }

    public void setCreditTransactions(List<Transaction> creditTransactions) {
        this.creditTransactions = creditTransactions;
    }

    public List<Transaction> getDebitTransactions() {
        return debitTransactions;
    }

    public void setDebitTransactions(List<Transaction> debitTransactions) {
        this.debitTransactions = debitTransactions;
    }

    public CustomerDetails getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(CustomerDetails accountOwner) {
        this.accountOwner = accountOwner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public TypeOfAccount getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(TypeOfAccount typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
