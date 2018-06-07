package bankingApp.bankAccount;

import bankingApp.Transaction;
import bankingApp.user.customer.Customer;

import java.io.Serializable;
import java.util.List;

public class BankAccount implements Serializable{
    public enum TypeOfAccount{
        SAVINGS, CHECKING, CREDIT
    }

    private Customer accountOwner;
    private int accountNumber;
    private Double balance;


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

    public Customer getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(Customer accountOwner) {
        this.accountOwner = accountOwner;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
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
