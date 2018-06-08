package bankingApp.customerOptions;

import bankingApp.InformationSerializer.CustomerDetailsSerializer;
import bankingApp.Transaction;
import bankingApp.TransactionIdUtils;
import bankingApp.TransactionUtil;
import bankingApp.user.CustomerDetails;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

public class TransferFunds {
    private static Logger logger = Logger.getLogger(TransferFunds.class);

    private String customerUserId;

    public TransferFunds(String userId){
        this.customerUserId = userId;
    }

    public void transferFunds(){
        CustomerDetails customer = CustomerDetailsSerializer.readCustomerDetails(customerUserId);
        if(customer == null){
            System.out.println("You do not have any account available! Choose option 1 to open account ");
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


        String payeeAccountNumber ;
        Double transferAmount;

        System.out.println("Hello user, how are you!\n");
        System.out.println("Enter account number to transfer funds to : ");
        payeeAccountNumber = scanner.nextLine();

        System.out.println("Enter amount to transfer: ");
        transferAmount = scanner.nextDouble();
        if(transferAmount < 0){
            System.out.println("Transfer amount cannot negative! ");
            return;
        }
        Transaction transaction = new Transaction();
        transaction.setTransactionId(Integer.toString(transactionId));

        transaction.setPayerId(customerUserId);
        transaction.setPayerAccountNumber(customer.getCustomerAccountNumber());
        transaction.setPayeeAccountNumber(payeeAccountNumber);
        transaction.setTransactionAmount(transferAmount);
        TransactionUtil.transferFund(transaction);

        logger.info("Transaction completed!");

        try {
            TransactionIdUtils.saveTransactionId(transactionId + 1);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("Error in writing to file!");
        }


    }
}
