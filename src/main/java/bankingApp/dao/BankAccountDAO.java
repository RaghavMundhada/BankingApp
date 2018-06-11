package bankingApp.dao;

import bankingApp.bankAccount.BankAccount;

public interface BankAccountDAO {
    public BankAccount getBankAccount(String accountNumber);

    public void saveBankAccount(BankAccount bankAccount);
}
