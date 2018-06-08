package bankingApp.InformationSerializer;


import bankingApp.bankAccount.BankAccount;
import org.apache.log4j.Logger;

import java.io.*;

public class BankAccountDetailsSerializer {
    private static Logger logger = Logger.getLogger(BankAccountDetailsSerializer.class);

    public static BankAccount readBankAccountDetails(String accountNumber){

        // Reads bank account details

        String bankAccountNumber = accountNumber;
        BankAccount bankAccountDetails;
        try {
            FileInputStream fileIn = new FileInputStream( bankAccountNumber);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            BankAccount bankAccount = (BankAccount) objectIn.readObject();
            bankAccountDetails = new BankAccount(bankAccount.getAccountOwner(),bankAccount.getAccountNumber());

            bankAccountDetails.setBalance(bankAccount.getBalance());
            bankAccountDetails.setTypeOfAccount(bankAccount.getTypeOfAccount());
            bankAccountDetails.setTransactions(bankAccount.getTransactions());
            bankAccountDetails.setCreditTransactions(bankAccount.getCreditTransactions());
            bankAccountDetails.setDebitTransactions(bankAccount.getDebitTransactions());

            fileIn.close();
            objectIn.close();

            logger.info("Successfully read bank account details for account number : " + bankAccountNumber);


        } catch (Exception e) {
            logger.info("Exception while reading bank account details for account number : " + bankAccountNumber);
            return null;
        }

        return bankAccountDetails;
    }

    public static void saveBankAccountDetails(BankAccount bankAccount){

        // Save bank account details

        String bankAccountNumber = bankAccount.getAccountNumber();

        try {

            FileOutputStream fileOut = new FileOutputStream(bankAccountNumber,false);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            BankAccount bankAccount1 = new BankAccount(bankAccount.getAccountOwner(),bankAccount.getAccountNumber());
            bankAccount1.setTypeOfAccount(bankAccount.getTypeOfAccount());
            bankAccount1.setBalance(bankAccount.getBalance());
            bankAccount1.setTransactions(bankAccount.getTransactions());
            bankAccount1.setCreditTransactions(bankAccount.getCreditTransactions());
            bankAccount1.setDebitTransactions(bankAccount.getDebitTransactions());

            objectOut.writeObject(bankAccount1);
            objectOut.close();
            fileOut.close();
            logger.info("Successfully serialized bank account number: "+bankAccountNumber);
        } catch (Exception e) {
            logger.info("Exception while serializing bank account : " + bankAccountNumber);
        }
    }



}
