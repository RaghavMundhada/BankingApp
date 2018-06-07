package bankingApp;

import bankingApp.bankAccount.BankAccount;
import bankingApp.bankAccount.BankAccountSerializer;
import bankingApp.user.customer.Customer;
import bankingApp.user.customer.CustomerSerializer;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Scanner;

public class CustomerOperationsUtkarsh {
    private static Logger logger = Logger.getLogger(CustomerOperationsUtkarsh.class);

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("1.Register\n2.Login");

            choice = sc.nextInt();

            if (choice == 1) {
                registerUser(sc);
            } else if (choice == 2) {
                login(sc);
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    private static void login(Scanner sc) {
        System.out.println("Enter userId : ");
        String userId = sc.nextLine();
        System.out.println("Enter password : ");
        String password = sc.nextLine();

        if (isRegisteredUser(userId, password)) {
            int choice;
            boolean flag = true;
            while (flag) {
                System.out.println("1.Open Account\n2.Deposit\n3.Withdraw\n4.Transfer Funds\n5.Logout");

                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        openAccount();
                    case 2:
                        deposit(userId);
                        break;
                    case 3:
                        withDraw(userId);
                        break;
                    case 4:
                        transferFunds(userId);
                        break;
                    case 5:
                        flag = false;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } else {
            System.out.println("Invalid Credentials!");
        }
    }

    private static void openAccount() {
        //write your code here;
    }

    private static void transferFunds(String userId) {
        //write your code here;
        int transactionId;
        try {
            transactionId = getTransactionId();
        } catch (IOException e) {
            logger.info("Error in getting transaction id from file!");
            e.printStackTrace();
            return;
        }


        String payeeAccountNumber ;
        Double transferAmount;

        System.out.println("Hello user, how are you!\n");
        System.out.println("Enter account number to transfer funds to : ");
        Scanner scanner = new Scanner(System.in);
        payeeAccountNumber = scanner.nextLine();

        System.out.println("Enter amount to transfer: ");
        transferAmount = scanner.nextDouble();

        Transaction transaction = new Transaction();
        transaction.setTransactionId(Integer.toString(transactionId));

        Customer customer = CustomerSerializer.deSerializeCustomer(userId);
        transaction.setPayerId(userId);
        transaction.setPayerAccountNumber(customer.getAccountNumber());
        transaction.setPayeeAccountNumber(payeeAccountNumber);

        TransactionUtil.transferFund(transaction);

        logger.info("Transaction completed!");

        try {
            saveTransactionId(transactionId + 1);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("Error in writing to file!");
        }


    }

    private static void withDraw(String userId) {
        //write your code here;
        int transactionId;
        try {
             transactionId = getTransactionId();
        } catch (IOException e) {
            logger.info("Error in getting transaction id from file!");
            e.printStackTrace();
            return;
        }

        System.out.println("Hello user, how are you today! \n Enter amount to withdraw from your account. " );
        Scanner scanner = new Scanner(System.in);
        Double amount = scanner.nextDouble();

        Transaction transaction = new Transaction();
        transaction.setTransactionId(Integer.toString(transactionId));

        Customer customer = CustomerSerializer.deSerializeCustomer(userId);
        String customerBankAccountNumber = customer.getAccountNumber();
        transaction.setPayerAccountNumber(customerBankAccountNumber);
        transaction.setTransactionAmount(amount);
        transaction.setPayerId(userId);
        TransactionUtil.withdrawAmount(transaction);
        logger.info("Transaction completed!");

        try {
            saveTransactionId(transactionId + 1);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("Error in writing to file!");
        }


    }

    private static void deposit(String userId) {
        //write your code here;
        int transactionId;
        try {
            transactionId = getTransactionId();
        } catch (IOException e) {
            logger.info("Error in getting transaction id from file!");
            e.printStackTrace();
            return;
        }

        System.out.println("Hello user, how are you today! \n Enter amount to deposit in your account." );
        Scanner scanner = new Scanner(System.in);
        Double amount = scanner.nextDouble();

        System.out.println("Depositing amount in your account .... ");

        Transaction transaction = new Transaction();
        transaction.setTransactionId(Integer.toString(transactionId));
        Customer customer = CustomerSerializer.deSerializeCustomer(userId);
        String customerBankAccountNumber = customer.getAccountNumber();
        transaction.setPayeeAccountNumber(customerBankAccountNumber);
        transaction.setTransactionAmount(amount);
        transaction.setPayeeId(userId);

        TransactionUtil.depositAmount(transaction);

        logger.info("Transaction completed!");

        try {
            saveTransactionId(transactionId + 1);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("Error in writing to file!");
        }


    }

    private static boolean isRegisteredUser(String userId, String password) {
        //write your code here;
        return true;
    }

    private static void registerUser(Scanner sc) throws IOException {
        HashMap<String, String> registeredUsers = getRegisteredUser();
        while (true) {
            System.out.println("Enter userId : ");
            String userId = sc.nextLine();

            if (registeredUsers.containsKey(userId)) {
                System.out.println("UserId Already Exists");
                continue;
            }

            System.out.println("Enter password : ");
            String password = sc.nextLine();

            saveUser(userId, password);

            break;
        }
    }

    private static void saveUser(String userId, String password) {
        try {
            Files.write(Paths.get("registeredUsers.txt"), (userId + "\t" + password + "\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    public static HashMap<String, String> getRegisteredUser() throws IOException {
        HashMap<String, String> registeredUser = new HashMap<String, String>();
        File file = new File("registeredUsers.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null) {
            String[] details = st.split("\t");
            registeredUser.put(details[0], details[1]);
        }

        return registeredUser;
    }

    private static int getTransactionId() throws IOException {
        File file = new File("transactionsIds.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        int id = 0;
        String line;
        while((line = reader.readLine())!=null){
            id = Integer.parseInt(line.trim());
        }
        return id;
    }

    private static void saveTransactionId(int id) throws IOException {
        File file = new File("transactionsIds.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        writer.write(id);
        writer.close();
    }
}
