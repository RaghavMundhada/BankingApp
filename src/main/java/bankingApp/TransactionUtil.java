package bankingApp;

import bankingApp.bankAccount.BankAccount;
import bankingApp.InformationSerializer.BankAccountDetailsSerializer;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionUtil {
    private static Logger logger = Logger.getLogger(TransactionUtil.class);

    public static void depositAmount(Transaction transaction){

        // Pass a transaction object which has the the account number to transfer funds to( Payee account number)
        // Read BankAccount object from disk using the account number to get the current balance and deposit the amount requested to be deposited!

        BankAccount bankAccount = BankAccountDetailsSerializer.readBankAccountDetails(transaction.getPayeeAccountNumber());

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        transaction.setTimestampTransactionCreated(timeStamp);
        transaction.setTransactionType("CREDIT");

        bankAccount.setBalance(bankAccount.getBalance() + transaction.getTransactionAmount());

        String timeStampUpdated = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        transaction.setTimestampTransactionCreated(timeStampUpdated);

        transaction.setTransactionStatus("SUCCESSFUL");
        System.out.println("Amount deposited successfully!");
        bankAccount.getTransactions().add(transaction);
        BankAccountDetailsSerializer.saveBankAccountDetails(bankAccount);
    }

    public static void withdrawAmount(Transaction transaction){

        // Pass a transaction object which has the account number to withdraw from ( Payer account number)
        // Reads BankAccount object from disk using the account number to get the current balance and withdraw the amount from it,
        // raised an error if the withdrawn amount is greater than the available balance


        BankAccount bankAccount = BankAccountDetailsSerializer.readBankAccountDetails(transaction.getPayerAccountNumber());

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        transaction.setTimestampTransactionCreated(timeStamp);
        transaction.setTransactionType("DEBIT");

        Double newBalance = bankAccount.getBalance() - transaction.getTransactionAmount();

        String timeStampUpdated = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        transaction.setTimestampTransactionUpdated(timeStampUpdated);
        if(newBalance < 0){
            transaction.setTransactionStatus("FAILED");
            System.out.println("Requested amount cannot be withdrawn due to lack of funds!, current balance : " + bankAccount.getBalance());
            return;
        }
        System.out.println("Amount withdrawn successfully, new balance: " + newBalance);
        bankAccount.setBalance(newBalance);
        transaction.setTransactionStatus("SUCCESSFUL");
        bankAccount.getTransactions().add(transaction);
        BankAccountDetailsSerializer.saveBankAccountDetails(bankAccount);
    }

    public static void transferFund(Transaction transaction){

        // TransferFunds funds from one account to another account, meaning withdraw from payer account and deposit to payee account.
        // Withdraw first to check if the amount can be withdrawn or not
        withdrawAmount(transaction);

        if(transaction.getTransactionStatus().equals("FAILED")){
            System.out.println("Amount cannot be transferred due to lack of funds !");
            return;
        }

        depositAmount(transaction);

    }
}
