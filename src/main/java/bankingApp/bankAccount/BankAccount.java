package bankingApp.bankAccount;

import bankingApp.Transaction;
import bankingApp.user.customer.Customer;

import java.util.List;

public class BankAccount {
    public enum TypeOfAccount{
        SAVINGS, CHECKING, CREDIT
    }

    private Customer accountOwner;
    private int accountNumber;
    private TypeOfAccount typeOfAccount;
    private List<Transaction> transactions ;
    private List<Transaction> creditTransactions;
    private List<Transaction> debitTransactions;


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
