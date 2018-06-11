package bankingApp.dao;

import bankingApp.bankAccount.BankAccount;
import bankingApp.database.DatabaseUtil;

public class BankAccountDaoImplementation implements BankAccountDAO {

    @Override
    public BankAccount getBankAccount(String accountNumber) {
        return DatabaseUtil.getBankAccountDetailsFromDb(accountNumber);

    }

    @Override
    public void saveBankAccount(BankAccount bankAccount) {
        DatabaseUtil.storeBankAccountDetailsToDb(bankAccount);
    }

}
