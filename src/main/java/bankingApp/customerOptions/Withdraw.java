package bankingApp.customerOptions;

import bankingApp.InformationSerializer.CustomerDetailsSerializer;
import bankingApp.Transaction;
import bankingApp.TransactionIdUtils;
import bankingApp.TransactionUtil;
import bankingApp.user.CustomerDetails;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

public class Withdraw {
    private static Logger logger = Logger.getLogger(Withdraw.class);

    private String customerUserId;

    public Withdraw(String userId){
        this.customerUserId = userId;
    }

    public void withdraw(){
        CustomerDetails customer = CustomerDetailsSerializer.readCustomerDetails(customerUserId);

        if(customer == null){
            System.out.println("You do not have any account available! Choose option 1 to open account");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        int transactionId;
        try {
            transactionId = TransactionIdUtils.getTransactionId();
        } catch (IOException e) {
            logger.info("Error in getting transaction id from file!");
            e.printStackTrace();
            return;
        }

        System.out.println("Hello user, how are you today! \n Enter amount to withdraw from your account. " );
        Double amount = scanner.nextDouble();

        Transaction transaction = new Transaction();
        transaction.setTransactionId(Integer.toString(transactionId));

        String customerBankAccountNumber = customer.getCustomerAccountNumber();
        transaction.setPayerAccountNumber(customerBankAccountNumber);
        transaction.setTransactionAmount(amount);
        transaction.setPayerId(customerUserId);
        TransactionUtil.withdrawAmount(transaction);
        logger.info("Transaction completed!");

        try {
            TransactionIdUtils.saveTransactionId(transactionId + 1);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("Error in writing to file!");
        }

    }
}
