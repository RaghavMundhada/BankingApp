package bankingApp;

import bankingApp.bankAccount.BankAccount;
import bankingApp.bankAccount.BankAccountSerializer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionUtil {
    private static Logger logger = Logger.getLogger(TransactionUtil.class);

    public static void depositAmount(Transaction transaction){

        BankAccount bankAccount = BankAccountSerializer.deSerializeBankAccount(transaction.getPayeeAccountNumber());

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        transaction.setTimestampTransactionCreated(timeStamp);
        transaction.setTransactionType("CREDIT");

        bankAccount.setBalance(bankAccount.getBalance() + transaction.getTransactionAmount());

        String timeStampUpdated = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        transaction.setTimestampTransactionCreated(timeStampUpdated);

        transaction.setTransactionStatus("SUCCESSFUL");

        bankAccount.getTransactions().add(transaction);
        BankAccountSerializer.serializeBankAccount(bankAccount);
    }

    public static void withdrawAmount(Transaction transaction){
        BankAccount bankAccount = BankAccountSerializer.deSerializeBankAccount(transaction.getPayerAccountNumber());

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        transaction.setTimestampTransactionCreated(timeStamp);
        transaction.setTransactionType("DEBIT");

        Double newBalance = bankAccount.getBalance() - transaction.getTransactionAmount();

        String timeStampUpdated = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        transaction.setTimestampTransactionCreated(timeStampUpdated);
        if(newBalance < 0){
            transaction.setTransactionStatus("FAILED! Withdraw amount is greater than available balance");
            return;
        }
        bankAccount.setBalance(newBalance);
        transaction.setTransactionStatus("SUCCESSFUL! Amount has been withdrawn");
        BankAccountSerializer.serializeBankAccount(bankAccount);
    }

    public static void transferFund(Transaction transaction){
        depositAmount(transaction);
        withdrawAmount(transaction);

    }
}
