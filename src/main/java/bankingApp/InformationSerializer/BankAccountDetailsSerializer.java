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
            FileInputStream fileIn = new FileInputStream( "BankAccountData/" + bankAccountNumber);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            BankAccount bankAccount = (BankAccount) objectIn.readObject();
            bankAccountDetails = new BankAccount(bankAccount.getAccountOwnerId(),bankAccount.getAccountNumber());

            bankAccountDetails.setBalance(bankAccount.getBalance());

            bankAccountDetails.setTransactions(bankAccount.getTransactions());

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

            FileOutputStream fileOut = new FileOutputStream("BankAccountData/" + bankAccountNumber,false);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            BankAccount bankAccount1 = new BankAccount(bankAccount.getAccountOwnerId(),bankAccount.getAccountNumber());

            bankAccount1.setBalance(bankAccount.getBalance());
            bankAccount1.setTransactions(bankAccount.getTransactions());

            objectOut.writeObject(bankAccount1);
            objectOut.close();
            fileOut.close();
            logger.info("Successfully serialized bank account number: "+bankAccountNumber);
        } catch (Exception e) {
            logger.info("Exception while serializing bank account : " + bankAccountNumber);
        }
    }



}
