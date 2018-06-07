package bankingApp.bankAccount;


import org.apache.log4j.Logger;

import java.io.*;

public class BankAccountSerializer {
    private static Logger logger = Logger.getLogger(BankAccountSerializer.class);

    public static void serializeBankAccount(BankAccount bankAccount){
        String bankAccountNumber = Integer.toString(bankAccount.getAccountNumber());

        try {
            File newFile = new File(bankAccountNumber);
            FileOutputStream fileOutputStream = new FileOutputStream(bankAccountNumber,false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            BankAccount bankAccount1 = new BankAccount();
            bankAccount1.setAccountNumber(bankAccount.getAccountNumber());
            bankAccount1.setAccountOwner(bankAccount.getAccountOwner());
            bankAccount1.setTypeOfAccount(bankAccount.getTypeOfAccount());
            bankAccount1.setTransactions(bankAccount.getTransactions());
            bankAccount1.setCreditTransactions(bankAccount.getCreditTransactions());
            bankAccount1.setDebitTransactions(bankAccount.getDebitTransactions());

            objectOutputStream.writeObject(bankAccount1);
            objectOutputStream.close();
            fileOutputStream.close();
            logger.info("Successfully serialized bank account number: "+bankAccountNumber);
        } catch (Exception e) {
            logger.info("Exception while serializing bank account : " + bankAccountNumber);
        }
    }


    public static BankAccount deSerializeBankAccount(int accountNumber){
        String bankAccountNumber = Integer.toString(accountNumber);
        BankAccount bankAccount1;
        try {
            FileInputStream fileInputStream = new FileInputStream(bankAccountNumber);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            BankAccount bankAccount = (BankAccount) objectInputStream.readObject();
             bankAccount1 = new BankAccount();
            bankAccount1.setAccountNumber(bankAccount.getAccountNumber());
            bankAccount1.setAccountOwner(bankAccount.getAccountOwner());
            bankAccount1.setTypeOfAccount(bankAccount.getTypeOfAccount());
            bankAccount1.setTransactions(bankAccount.getTransactions());
            bankAccount1.setCreditTransactions(bankAccount.getCreditTransactions());
            bankAccount1.setDebitTransactions(bankAccount.getDebitTransactions());

            fileInputStream.close();
            objectInputStream.close();

            logger.info("Successfully de serialized bank account number: "+bankAccountNumber);


        } catch (Exception e) {
            logger.info("Exception while de serializing bank account : " + bankAccountNumber);
            return null;
        }

        return bankAccount1;
    }
}
